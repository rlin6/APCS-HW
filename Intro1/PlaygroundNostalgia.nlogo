;Thet Htay Zaw
;1.11.17 Added Jailbreak Command to Cops and Robbers
;1.12.17 Added Stamina Mechanic and Added "Rest-Wiggle" Command
;1.13.17 Added EndGame/GameOver Requirements and Messages for Freeze Tag and Cops and Robbers
;1.13.17 Added Speed Modifier Variable
;1.14.17 Eliminated Wiggle Commands, Inserted Disperse Command. Revised Stamina/Rest Commanands. Made a separate section for Stamina/Rest Commands.
;1.14.17 Revised Cops and Robbers Code and Variables to make it share common code with the others
;1.15.17 Fixed JailBreak and DTJ Commands for Cops and Robbers

;Ricky Lin
;1.11.17 Wrote SetupCTF, Defender Behavior ( reset-flag and tag ) and Attack Behavior ( flaghunt and return )
;1.12.17 Altered rules of Capture the Flag: added taken to flag, changed Attackers to taggers, changed Defenders to runners, added state to Defenders, and tagged? to Attackers
;1.12.17 Added function flagmove, FlagBehavior, and flagsteal. Updated reset-flag, flaghunt, and return to fit in with new variables and new roles. Removed tag.
;1.13.17 Added Endgame behavior and GameOver Messages for Capture the Flag
;1.13.17 Added parameters to chase, rest-chase, run-away, and rest-runaway functions to allow for their use among different game modes
;1.14.17 Condensed various sections of the code and added variables to make code more fluid and readable
;1.15.17 Altered Speed-Modifier into SpeedModified, added global variables and updateSpeedModifier to allow for change in speed, and updated all of movement functions to reflect this change
;1.16.17 Condensed setup so there are no repeats and removed flag behavior
;1.16.17 Added roles to each breed for differentiation and added rest versions of return and flaghunt

globals [ meanSpeed meanDeviation ]

turtles-own [ MaxStamina Stamina Tired? SpeedModified Role ]
;MaxStamina is the maximum amount of stamina a turtle has that it cannot surpass
;Stamina is its current stamina that it uses up
;Tired? activate once they use up their stamina and moves slower
;SpeedModified is the amount they move for their movement functions
;Role is whether they are a tagger or a runner

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;Freeze Tag Breeds;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


breed [ Its It ]               ;Taggers
breed [ NotIts NotIt ]         ;Runners

Notits-own [ tagged? ]

;Tagged determines if the turtle is tagged or not

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;Cops and Robbers Breeds;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


breed [ Cops Cop ]             ;Taggers
breed [ Robbers Robber ]       ;Runners

Robbers-own [ Tagged? Jailed? State By ]

;By is to determine which cop arrested which robber
;Jailed? determines if they are in jail or not
;State determines their actions based on what state they're in

Cops-own [ State ]

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;Capture The Flag;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


breed [ Attackers Attacker ]   ;Taggers
breed [ Defenders Defender ]   ;Runners
breed [ Flags Flag ]

attackers-own [ State tagged? ]
defenders-own [ State tagged? ]
flags-own [ By Taken ]

; Taken tells if the flag is not taken, taken by Defenders, or taken by Attackers

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;General Code;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


to setup  ; Sets up the world based on the different modes
  ca

  reset-ticks

  if TagMode = "FreezeTag" [
    setupFT ]

  if TagMode = "CopsAndRobbers" [
   setupCR ]

  if TagMode = "CaptureTheFlag" [
   setupCTF ]

  updateSpeedModified

  ; Taggers = red

  ask turtles with [ role = "Tagger" ] [
    set MaxStamina Max-Stamina-of-Taggers
    set stamina Max-Stamina-of-Taggers
    set color red
    set Tired? false ]

  ; Attackers are special taggers than can be tagged by Defenders in certain cases

  ask Attackers [
    set tagged? false ]

  ; Runners = blue

  ask turtles with [ role = "Runner" ] [
    set MaxStamina Max-Stamina-of-Runners
    set stamina Max-Stamina-of-Runners
    set color blue
    set Tired? false
    set tagged? false ]

  ask turtles [
    set size 3 ]

end


to go   ;Runs everything

  if meanSpeed != ( speed-of-Taggers + speed-of-Runners ) / 2 or meanDeviation != ( deviation-of-Taggers + deviation-of-Runners ) / 2 [
    updateSpeedModified ]  ; If the slider-variables differ from the global variables that were recorded, give everyone a new speed based on this new slider amounts

  ItBehavior
  NotItBehavior

  CopsBehavior
  RobbersBehavior

  DefenderBehavior
  AttackerBehavior

  ask turtles [
    if Stamina-Label? [ set label round stamina ] ]

  tick

  end-game

end

to updateSpeedModified       ; Gives each turtle their speedModified and stores the current slider amount as global variables

  ask turtles with [ role = "Tagger" ] [
    set speedModified ( abs ( random-normal speed-of-Taggers deviation-of-Taggers ) ) ]

  ask turtles with [ role = "Runner" ] [
    set speedModified ( abs ( random-normal speed-of-Runners deviation-of-Runners ) ) ]

  set meanSpeed ( speed-of-Taggers + speed-of-Runners ) / 2

  set meanDeviation ( deviation-of-Taggers + deviation-of-Runners ) / 2

end

to end-game   ;Determines the victory conditions for each game mode

  if ticks = Time-Limit [
    ask NotIts [
      if Tagged? = True [
        die ] ]
    ask Robbers [
      if State != "Free" [
        die ] ]
    ask its [
      if any? NotIts with [ Tagged? = False ] [
        die ] ]
    ask Cops [
      if any? Robbers with [ State = "Free" ]  [
        die ] ]
    GameOver-FTCR ]

  GameOver-CTF

end


to GameOver-FTCR     ;Displays game over messages for freeze tag and cops and robbers
  if TagMode = "FreezeTag" [
    ifelse any? NotIts
      [ show "Game Over NotIts Won" ]
      [ show "Game Over Its Won" ] ]
  if TagMode = "CopsAndRobbers" [
    ifelse any? Robbers
      [ show "Game over Robbers Won" ]
      [ show "Game over Cops Won" ] ]
end


to GameOver-CTF     ;Displays game over messages for capture the flag
  if TagMode = "CaptureTheFlag" [
    ask Defenders [
      if any? flags with [ [ pcolor ] of patch-here = cyan ] [
        show "Game over Attackers Won"
        die ] ]
    if ticks = Time-Limit [
      ask Attackers [
        if any? Defenders [
          show "Game Over Defenders Won"
          die ] ] ] ]
end

to chase [ Runners Allies ] ;Chase Runners if not, Disperse from Allies
  if Tired? = False [
    ; Its will face the nearest NotIts that are not tagged yet and chase them at their set speed. If there are none in radius, it will just wiggle the It way
      ifelse any? Runners with [ tagged? = false ] in-radius radius-of-taggers [
        face min-one-of Runners with [ tagged? = false ] [ distance myself ]
        fd SpeedModified
        reduce-stamina ( random-float 1 ) ]
       [ disperse-taggers Allies ] ]
end


to run-away [ Taggers Allies ] ;Run Away from Taggers if not, Disperse from Allies
  if Tired? = False [
    ; Runners  will turn away from the Taggers in its radius and run away from them at their speed. Otherwise they will wiggle the Runner way
    ifelse any? Taggers in-radius radius-of-runners
    [ face min-one-of Taggers [ distance myself ]
      rt 180
      fd speedModified
      reduce-stamina ( random-float 1 ) ]
    [ disperse-runners Allies ] ]
end


to disperse-taggers [ Allies ] ; If there are no enemies in sight, spread out from your allies
  if Tired? = False [
    if any? other Allies [
    ; Taggers will face their allies and turn 90 degrees and move away from them, losing stamina in process
    face one-of other Allies
    rt 90
    fd speedModified / 3
    reduce-stamina ( random-float 0.5 ) ] ]
end


to disperse-runners [ Allies ] ; Runners version of disperse
  if Tired? = False [
    if any? other Allies with [ tagged? = false ] [
      face one-of other Allies with [ tagged? = false ]
      rt 90
      fd speed-of-Runners / 3
      reduce-stamina ( random-float 0.5 ) ] ]
end


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;Stamina/Rest Code;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


to reduce-stamina [ amount ]    ;Doing normal movement motions will reduce stamina by a certain amount. Once it hits zero, the turtle is now tired
  set Stamina Stamina - amount
  if Stamina <= 0 [
    set Stamina  0
    set Tired? true ]
end


to recover-stamina [ StaminaBar ]  ; Performing rest movement motions will regenerate the stamina bar slowly but never above the maxstamina. Once the stamina reaches a certain threshold, it will no longer be tired
  if Tired? = true [
    set stamina stamina + ( random-float 3 ) ]
  if stamina >= ( StaminaBar * Energy-Threshold ) [
    set Tired? False ]
  if stamina >= MaxStamina [
    set Stamina MaxStamina ]
end


to rest-chase [ Runners Allies ]  ; Rest version of chase where the turtles move slower and recover stamina
  if Tired? = true [
    ifelse any? Runners with [ tagged? = false ] in-radius radius-of-taggers [
      face min-one-of Runners with [ tagged? = false ] [ distance myself ]
      fd ( speedModified ) / 3
      recover-stamina Max-Stamina-of-Taggers ]
    [ rest-disperse-taggers Allies ] ]
end

to rest-run-away [ Taggers Allies ]  ; Rest version of run-away where the turtles move slower and recover stamina
  if Tired? = true [
   ifelse any? Taggers in-radius radius-of-runners
    [ face min-one-of Taggers [ distance myself ]
      rt 180
      fd ( speedModified ) / 3
      rt random 361 - 180
      recover-stamina Max-Stamina-of-Runners  ]
    [ rest-disperse-runners Allies ] ]
end


to rest-disperse-taggers [ Allies ]  ; Rest version of taggers dispersing where they move slower and recover stamina
  if Tired? = True [
    if any? other Allies [
      face one-of other Allies
      rt 90
      fd speedModified / 3
      recover-stamina Max-Stamina-of-Taggers ] ]
end

to rest-disperse-runners [ Allies ]  ; Rest version of runners dispersing where they move slower and recover stamina
  if Tired? = True [
    if any? other Allies with [ tagged? = false ] [
      face one-of other Allies with [ tagged? = false ]
      rt 90
      fd speedModified / 3
      recover-Stamina Max-Stamina-of-Runners ] ]
end

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;Freeze Tag Code;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

to setupFT
  set-default-shape Its "person"
  set-default-shape NotIts "person"

  ;The total of both NotIts and Its number of patches are asked to sprout Its until the number of Its spawned exceeds the number of Its the slider says to spawn
  ;Then, the rest of the patches will spawn NotIts to make the right amount of Its and NotIts that are not on top of each other

  ask n-of ( Number-Of-Taggers  + Number-Of-Runners ) patches [
    let x ( count Its )
    ifelse x < Number-Of-Taggers  [
    sprout-Its 1 [
      set role "Tagger" ] ]
  [ sprout-NotIts 1 [
      set role "Runner" ] ] ]
end

to ItBehavior  ; Its will chase/rest chase NotIts and freeze them when they reach them
  ask its [
    chase NotIts Its
    rest-chase NotIts Its
    freeze
    set MaxStamina Max-Stamina-of-Taggers ]
end

to NotItBehavior  ; It will run/rest run away from Its and unfreeze their frozen allies when they reach them

  ask NotIts [
  ; NotIts can only do their behavior if they are not tagged

    set MaxStamina Max-Stamina-of-Runners
    if Tagged? = false
    [ run-away Its NotIts
      rest-run-away Its NotIts
      unfreeze ]

; NotIts will recover stamina when frozen
  if Tagged? = true [
    set stamina stamina + 1
    if Stamina >= maxStamina [
      set Stamina MaxStamina ] ] ]
end

to freeze
  ; Its will tag NotIts By asking one of the Its here to set their tagged? to true
  if any? NotIts-here [
    ask one-of NotIts-here [
      set tagged? true ] ]
end

to unfreeze
  ; NotIts that are untagged will untag one of the tagged NotIts at its location

  if Tagged? = False [
    ask other NotIts-here with [ tagged? = true ] [
      set Tagged? False
    if count its in-radius Radius-of-Runners < count Notits with [ Tagged? = true ] in-radius Radius-of-Runners [
      face min-one-of NotIts with [ tagged? = true ] [ distance myself ]
      fd speedModified ] ] ]
end



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;Cops and Robbers Code;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


to setupCR

  set-default-shape Cops "person police"

  set-default-shape Robbers "person"

  make-jail

;Cops spawn in jail
  ask n-of Number-of-Taggers patches with [ pcolor = grey ] [
    sprout-cops 1 [
      set State "Chasing"
      set role "Tagger" ]  ]

  ask n-of Number-of-Runners patches with [ pcolor = black ] [
    sprout-robbers 1 [
      set Jailed? false
      set role "Runner" ] ]

end

to make-jail ; makes the jail area
  ask patch -12 0 [
    ask neighbors [
      set pcolor grey
      ask neighbors [
        set pcolor grey
        ask neighbors [
          set pcolor grey
          ask neighbors [
            set pcolor grey
            ask neighbors [
              set pcolor grey ] ] ] ] ] ]
end


to CopsBehavior  ; Cops will chase/rest chase and arrest robbers when chasing, and drag robbers to jail when they are arresting
  ask cops with [ State  = "Chasing" ] [
    Chase Robbers Cops
    Rest-Chase Robbers Cops
    arrest-robbers ]
  ask cops with [ State = "Arresting" ] [
    DTJ
    Rest-DTJ ]
  ask cops [
    set MaxStamina Max-Stamina-of-Taggers ]
end


to RobbersBehavior   ; Robbers will run/rest run away from cops and try to jail break their jailed allies
  ask robbers with [ Tagged? = False ] [
    Run-Away Cops Robbers
    Rest-Run-Away Cops Robbers
    jailbreak ]

; When tagged, they will follow cops to be jailed
  ask robbers with [ Tagged? = True and Jailed? = False ] [
    Be-Jailed ]

; Recover stamina in jail
  ask robbers with [ Jailed? = True ] [
    set stamina stamina + 1
    if Stamina >= maxStamina [
      set Stamina MaxStamina ] ]

  ask robbers [
    set MaxStamina Max-Stamina-of-Runners ]

end

to DTJ ;Drag to Jail, asks tagged robbers to follow the cop who arrested them to jail. Once in jail, the robber cannot move and the cop goes back to catching robbers
  if Tired? = false [
    ask robbers with [ By = [ who ] of myself and ( Tagged? = True and Jailed? = false )  ] [
      face patch -12 0
      fd [ speedModified ] of myself ]
    face patch -12 0
    fd speedModified
    reduce-stamina ( random-float 1 )
    if ( [pcolor] of patch-here = grey ) and ( State  = "Arresting" ) [
      set State  "Chasing" ] ]
end


to Rest-DTJ  ; rest version
  if Tired? = true [
    ask robbers with [ By = [ who ] of myself and ( Tagged? = True and Jailed? = false )  ] [
      face patch -12 0
      fd [ speedModified ] of myself / 3 ]
    face patch -12 0
    fd speedModified / 3
    recover-stamina Max-Stamina-of-Taggers
    if ( [pcolor] of patch-here = grey ) and ( State  = "Arresting" ) [
      set State  "Chasing" ] ]
end


to arrest-robbers   ; asks robbers here to be jailed
  if any? robbers-here [
    ask robbers-here with [ Tagged? = False ] [
      set By [who] of myself
      set Tagged? True  ]
    set state "Arresting" ]
end


to Be-Jailed       ; once robber is in grey jail area, they are jailed
  if ( [pcolor] of patch-here = grey ) [
    set By " "
    set Jailed? True ]
end


to jailbreak     ; unjailed and untired robbers will charge at the jail to try to jail break a fellow robber if there aren't that many cops in its radius. Once they reach a jailed robber, they set that robber free
  if Tagged? = False and Tired? = False [
    if count cops in-radius Radius-of-Runners < 5 and any? Robbers with [ Jailed? = true ] [
      face min-one-of Robbers with [ Jailed? = True ] [ distance myself ]
      fd speedModified
      reduce-stamina (random-float 1) ]
    ask robbers-here with [ Jailed? = True ] [
      set Tagged? False
      set Jailed? False
      set By " " ] ]
end


to rest-jailbreak   ; rest version
  if Tagged? = False and Tired? = True [
    if count cops in-radius Radius-of-Runners < 5 and any? Robbers with [ Jailed? = True ] [
      face min-one-of Robbers with [ Jailed? = True ] [ distance myself ]
      fd ( speedModified) / 3
      recover-stamina Max-Stamina-of-Runners ]
    ask robbers-here with [ State = "Jailed" ] [
      set Tagged? False
      set Jailed? False
      set By " " ] ]
end

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;Capture The Flag;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


to setupCTF
  set-default-shape Attackers "person"
  set-default-shape Defenders "person"
  set-default-shape Flags "flag"

; sets up the battlefield territory colors
  ask patches with [ pxcor < 0 ] [
    set pcolor yellow ]

  ask patches with [ pxcor > 0 ] [
    set pcolor green ]

; capture point
   ask one-of patches with [ pcolor = yellow ] [
    set pcolor cyan ]

; flag location
  ask one-of patches with [ pcolor = green ] [
    set pcolor violet
    sprout-flags 1 [
      set color white
      set By ""
      set Taken "" ] ]

; Attackers on yellow side, Defenders on green
  ask n-of Number-of-Taggers patches with [ pcolor = yellow ] [
    sprout-Attackers 1 [
      set State "Hunting"
      set role "Tagger" ] ]

  ask n-of Number-of-Runners patches with [ pcolor = green ] [
    sprout-Defenders 1 [
      set State "Searching"
      set role "Runner" ] ]

end


to DefenderBehavior     ; Defenders will look for the flag and move with the flag when searching and will chase after Attackers to reset the flag when defending
  ask defenders [
    set MaxStamina Max-stamina-of-Taggers
    flagcheck
    if state = "Searching" [
      flaghunt
      rest-flaghunt
      flagmove ]
    if state = "Defending" [
    chase Attackers Defenders
    rest-chase Attackers Defenders
    reset-flag ] ]
end


to AttackerBehavior  ; Attackers will chase after and tag Defenders to try to steal the flag when hunting and will return to capture point when they have the flag when returning
  ask attackers [
    set MaxStamina Max-stamina-of-Runners
    if State = "Hunting" [
      chase Defenders Attackers
      rest-chase Defenders Attackers
      flagsteal ]
    if State = "Returning" [
      return
      rest-return ] ]

end


to flaghunt   ; Defenders look for an untaken flag and takes it for themselves. Once the flag is taken by a defender, all of them runs away from Attackers
  if tired? = false [
    ifelse any? flags in-radius radius-of-runners with [ taken = "" and By = "" ] [
      face one-of flags in-radius radius-of-runners with [ taken = "" and By = "" ]
      fd speedModified
      reduce-stamina ( random-float 1 )
      if any? flags-here with [ By = "" and taken = "" ] [
      ask flags-here [
        set By [ who ] of myself
        set taken "Defenders" ] ] ]
      [ run-away Attackers Defenders
        rest-run-away Attackers Defenders ] ]
end

to rest-flaghunt  ; Rest version
  if tired? = true [
    ifelse any? flags in-radius radius-of-runners with [ taken = "" and By = "" ] [
      face one-of flags in-radius radius-of-runners with [ taken = "" and By = "" ]
      fd speedModified / 3
      recover-stamina Max-Stamina-of-Runners
      if any? flags-here with [ By = "" and taken = "" ] [
      ask flags-here [
        set By [ who ] of myself
        set taken "Defenders" ] ] ]
      [ run-away Attackers Defenders
        rest-run-away Attackers Defenders ] ]
end

to flagmove  ; Defender asks flag to set its coordinates to its own coordinates to get the flag to move with him
  ask flags with [ By = [who] of myself and taken = "Defenders" ] [
    let x [pxcor] of myself
    let y [pycor] of myself
    setxy x y ]
end

to flagcheck ; If the flag is taken by Attackers, the Defenders will start defending. If it is neutral or taken by defenders, they will be searching
  if any? flags with [ taken = "Attackers" ] [
    set State "Defending" ]
  if any? flags with [ taken = "" or taken = "Defenders" ] [
    set state "Searching" ]
end

to return  ; Once the Attackers have the flag, they will face the capture point and carry the flag with them home
  if tired? = false [
    face one-of patches with [ pcolor = cyan ]
    fd speedModified
    reduce-stamina ( random-float 1 )
    ask flags with [ By = [ who ] of myself and taken = "Attackers" ] [
      if [ pcolor ] of patch-here != cyan [
      let x [pxcor] of myself
      let y [pycor] of myself
      setxy x y ] ] ]
end

to rest-return  ; Rest version
  if tired? = true [
    face one-of patches with [ pcolor = cyan ]
    fd speedModified / 3
    recover-stamina Max-Stamina-of-Taggers
    ask flags with [ By = [ who ] of myself and taken = "Attackers" ] [
      if [ pcolor ] of patch-here != cyan [
      let x [pxcor] of myself
      let y [pycor] of myself
      setxy x y ] ] ]
end

to flagsteal  ; If the Attackers tags a Defender, the Defender teleports to a random patch in the world. Also, if the defender is holding a flag, it is taken by the attacker and the attacker will start to return
  if any? flags-here with [ taken = "Defenders" or taken = "" ] [
    ask flags-here [
      set By [ who ] of myself
      set taken "Attackers" ]
    set state "Returning" ]
  ask Defenders-here [
    setxy random-pxcor random-pycor ]
end

to reset-flag  ; Defenders who are defending will try to tag Attackers and if the Attacker is holding the flag, the flag returns to its original starting position and the Attacker goes back to hunting
  ask flags-here [
    let x ( [ pxcor ] of one-of patches with [ pcolor = violet ] )
    let y ( [ pycor ] of one-of patches with [ pcolor = violet ] )
    set By ""
    set taken ""
    setxy x y ]
  ask Attackers-here [
    set State "Hunting" ]
end
@#$#@#$#@
GRAPHICS-WINDOW
521
10
1001
511
35
35
6.62
1
10
1
1
1
0
1
1
1
-35
35
-35
35
1
1
1
ticks
30.0

CHOOSER
13
10
194
55
TagMode
TagMode
"FreezeTag" "CopsAndRobbers" "CaptureTheFlag"
1

SLIDER
15
60
201
93
Number-of-Taggers
Number-of-Taggers
1
40
20
1
1
NIL
HORIZONTAL

BUTTON
201
11
275
44
Setup
setup
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

SLIDER
225
60
402
93
Number-of-Runners
Number-of-Runners
1
40
40
1
1
NIL
HORIZONTAL

SLIDER
15
97
202
130
Speed-of-Taggers
Speed-of-Taggers
0
1
1
0.01
1
NIL
HORIZONTAL

SLIDER
225
98
403
131
Speed-of-Runners
Speed-of-Runners
0
1
1
0.01
1
NIL
HORIZONTAL

SLIDER
16
133
201
166
Radius-of-Taggers
Radius-of-Taggers
10
50
50
1
1
NIL
HORIZONTAL

SLIDER
225
136
404
169
Radius-of-Runners
Radius-of-Runners
10
50
50
1
1
NIL
HORIZONTAL

BUTTON
298
11
361
44
NIL
Go
T
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

MONITOR
56
324
128
369
Taggers
count turtles with [ role = \"Tagger\" ]
17
1
11

MONITOR
295
325
354
370
Runners
count turtles with [ role = \"Runner\" ]
17
1
11

SLIDER
16
168
200
201
Max-Stamina-of-Taggers
Max-Stamina-of-Taggers
50
100
100
1
1
NIL
HORIZONTAL

SLIDER
223
172
405
205
Max-Stamina-of-Runners
Max-Stamina-of-Runners
50
100
100
1
1
NIL
HORIZONTAL

SWITCH
123
264
262
297
Stamina-Label?
Stamina-Label?
0
1
-1000

SLIDER
15
206
187
239
Deviation-of-Taggers
Deviation-of-Taggers
0.01
0.25
0.25
0.01
1
NIL
HORIZONTAL

SLIDER
224
208
397
241
Deviation-of-Runners
Deviation-of-Runners
0.01
0.25
0.25
0.01
1
NIL
HORIZONTAL

SLIDER
11
406
183
439
Time-Limit
Time-Limit
800
2000
800
10
1
NIL
HORIZONTAL

SLIDER
247
410
419
443
Energy-Threshold
Energy-Threshold
0.2
0.8
0.8
0.1
1
NIL
HORIZONTAL

@#$#@#$#@
## WHAT IS IT?

This project models different variations of the game Tag. The general concept is for the Taggers to tag all the Runners, what happens as a result will depend on each individual variation.

Tag is a very popular recreational game, especially among younger demographics. There are many different versions though and some more similar to the mother game than others (e.g.: Freeze Tag vs Capture the Flag). Although there are many variations of the game existing, the project will only focus on three being, Freeze Tag, Cops and Robbers, and Capture the Flag.

## HOW IT WORKS

A Time Limit will be present to dictate Win Conditions within the different modes.

In _**Freeze Tag**_, the Its (Taggers) will chase after the NotIts (Runners). As the game is in real life, once tagged by an It, NotIts will freeze and can only be unfrozen after being tagged by an allied NotIt. NotIts will attempt to balance freeing their allies while running away.

**Win Condition for Its:** Freeze all NotIts within the Time Limit
**Win Condition for NotIts:** Have atleast one NotIt not frozen by the end of the Time Limit

In _**Cops and Robbers**_, the Cops (Taggers) will start within the jail and chase after the Robbers (Runners). After tagging the Robber, the cop will drag the robber to jail where the robber must stay in jai; until he is jailbroken by an ally. The Robbers will attempt to balance jailbreaking allies and avoiding arrest.

**Win Condition for Cops:** Arrest or Jail all Robbers within the Time Limit
**Win Conditions for Robbers:** Have at least one Free Robbern by the end of the Time Limit

In _**Capture the Flag**_, there will be two sides. The Attackers will spawn on the yellow side along with a random "capture point" (a random yellow patch turned cyan). The Defenders will spawn on the green side along with a single white flag spawning on a random green patch turned violet. The Defenders will look for the flag and the first one to reach the flag will carry it. After the flag is taken by a defender, all of the defenders will then proceed to run away from the Attackers with the flag carried by the defender who captured it. The Attackers will start off by chasing after the Defenders. Once they reach and tagged them, they will send the Defender to a random coordinate in the world and steal ( if any ) the flag that they were carrying. The Attacker who steals the flag will then try to run back to the capture point to plant the flag there. Once the flag is taken by an Attacker, the Defenders will begin to defend the flag by chasing after the Attackers and attempting to "reset" the flag. If a Defender tags the Attacker holding the flag before they reach the capture point, the flag goes back to its starting location where the Defenders will then try to take it and run away from the Attackers. The other Attackers that are not holding the flag will still proceed to tag the Defenders even when they are chasing after them.

**Win Condition for Defenders:** Stall out until the time is up by making sure the Attackers doesn't bring the flag to their capture point and killing all the Defenders
**Win Condition for Attackers:** Steal the flag from the Defenders and bring it back to the capture point before the time limit is up

_There are also other variables present within the models that will change how scenarios play out:_

**Stamina and Resting:** People cannot run endlessly and will eventually get tired. As a result, each turtle has a innate Stamina variable that will determine how long they can run at max speed. Once their stamina drops to zero, they must rest until they regain 80% of their stamina back. However, they will still perform standard actions, just at a much slower pace. The turtles will also gain and lose stamina at random intervals to prevent everyone from getting tired and stop being tired at the same time, as the same does not tend to occur in real life.

**Speed-Modifier/Deviation:** Not everyone will run at the same pace, people tend to have differing athletic abilities that will decide their speed. As a result, each turtle will spawn with a modified speed determined by the average speed and the maximum amount of deviation each member has that will edit their speed. As a result, the speed sliders on the interfact do not dictate actual speed, but more of an average speed.

## HOW TO USE IT

**Buttons:**

* **TagMode:** Allows you to select the simulation you have to use

* **Setup:** Creates the world. This includes all turtles and any patch alterations if needed. Can be used after to reset the world

* **Go:** Starts the model and runs it forever until it is turned off.

**Sliders:**
_Note: Some may affect/change the model while it is running and others may not_

* **Number-of-Taggers/Runners:** Numer of Taggers and Runners spawned respectively

* **Speed-of-Taggers/Runners:** Baseline of Speed/Average Speed that will be affected by the deviation

* **Radius-of-Taggers/Runners:** The vision radius of Taggers/Runners respectively. What they see will affect their actions and the size of their vision radius can change the behavior of the turtles drastically.

* **Max-Stamina-of-Taggers/Runners:** The Maximum Stamina that a Tagger or Runner can have respectively

* **Deviation-of-Taggers/Runners:** The maximum amount of deviation, both positive and negative, that each turtle is allowed to have from the baseline speed

* **Time Limit:** The amount of ticks needed for the model to end and win conditions via time are met.

* **Energy-Threshold:** The multiplier that decides how much of their stamina (in relation to their maximum stamina) a turtle needs to exit a Tired/Resting State.

**Other Interface Tools:**

* **Stamina-Label Switch:** Shows the Stamina of each turtle rounded to the nearest integer

* **Taggers/Runners Monitors:** Shows the number of Taggers/Runners present. Should never change until the timer runs out

## THINGS TO NOTICE

Pay attention to how different radius will affect their actions. For example, larger radius for runners allows them to see taggers faster, but in turn, seeing all those runners deters them from actively seeking out to unfreeze allies in Freeze Tag and Cops and Robbers.

Stamina is also the same. Large Max Stamina means it takes longer for them to drain their stamina, but it also means it takes them longer to stay in a resting period before turning back to normal. The same applies the other way around, lower stamina means that they drain their energy faster, but they recover to an amount that allows them to return to normal again faster too. (Assuming that the Energy threshold is constant throughout.)

## CREDITS AND REFERENCES

Credits to Lab N10 (Zombies Lab) and AIDS Netlogo Model for Info Tab Setup

## BREAKDOWN

Parts done by Thet Htay Zaw:

* Interface
* Go Command for Freeze Tag
* End Game and Game Over Commands/Mechanics for Freeze Tag and Cops and Robbers
* Run Away Command
* Disperse Commands
* Stamina and Rest Commands
* Unfreeze Command for Freeze Tag
* Everything Related to Cops and Robbers but the Setup
* Most of the Info Tab

Parts done by Ricky Lin:

* Breeds and Names
* Setup Commands (All of them)
* Chase Command
* Count Taggers/Runner Reporters
* Speed Modifier/Deviation
* Freeze Command for Freeze Tag
* Everything Related to Capture the Flag (Setup, End Game, Game Over, CTF Exclusives, etc.)
* Condensing the Code and various Bugfixes
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

person police
false
0
Polygon -1 true false 124 91 150 165 178 91
Polygon -13345367 true false 134 91 149 106 134 181 149 196 164 181 149 106 164 91
Polygon -13345367 true false 180 195 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285
Polygon -13345367 true false 120 90 105 90 60 195 90 210 116 158 120 195 180 195 184 158 210 210 240 195 195 90 180 90 165 105 150 165 135 105 120 90
Rectangle -7500403 true true 123 76 176 92
Circle -7500403 true true 110 5 80
Polygon -13345367 true false 150 26 110 41 97 29 137 -1 158 6 185 0 201 6 196 23 204 34 180 33
Line -13345367 false 121 90 194 90
Line -16777216 false 148 143 150 196
Rectangle -16777216 true false 116 186 182 198
Rectangle -16777216 true false 109 183 124 227
Rectangle -16777216 true false 176 183 195 205
Circle -1 true false 152 143 9
Circle -1 true false 152 166 9
Polygon -1184463 true false 172 112 191 112 185 133 179 133
Polygon -1184463 true false 175 6 194 6 189 21 180 21
Line -1184463 false 149 24 197 24
Rectangle -16777216 true false 101 177 122 187
Rectangle -16777216 true false 179 164 183 186

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
15
Circle -1 true true 203 65 88
Circle -1 true true 70 65 162
Circle -1 true true 150 105 120
Polygon -7500403 true false 218 120 240 165 255 165 278 120
Circle -7500403 true false 214 72 67
Rectangle -1 true true 164 223 179 298
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Circle -1 true true 3 83 150
Rectangle -1 true true 65 221 80 296
Polygon -1 true true 195 285 210 285 210 240 240 210 195 210
Polygon -7500403 true false 276 85 285 105 302 99 294 83
Polygon -7500403 true false 219 85 210 105 193 99 201 83

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

wolf
false
0
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true 2 194 13 197 30 191 38 193 38 205 20 226 20 257 27 265 38 266 40 260 31 253 31 230 60 206 68 198 75 209 66 228 65 243 82 261 84 268 100 267 103 261 77 239 79 231 100 207 98 196 119 201 143 202 160 195 166 210 172 213 173 238 167 251 160 248 154 265 169 264 178 247 186 240 198 260 200 271 217 271 219 262 207 258 195 230 192 198 210 184 227 164 242 144 259 145 284 151 277 141 293 140 299 134 297 127 273 119 270 105
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270

@#$#@#$#@
NetLogo 5.0.2
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180

@#$#@#$#@
0
@#$#@#$#@
