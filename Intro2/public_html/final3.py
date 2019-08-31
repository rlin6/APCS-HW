#! /usr/bin/python

import cgi

import cgitb
cgitb.enable()

HTML_HEADER = 'Content-type: text/html\n\n'

Top_HTML = '''
<html>
<head>
<title>Pokemon Battle-Sim</title>
<link rel="icon" type="image/png" href="icon.png"/>
<link href='style1.css' type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
<style>
body {
    background-image: url("background.png");
    margin: 10;
} 

*{
    font-family: 'Oswald', sans-serif;
}

tr{
    font-size:24px;
    color:white;
}

header{
    font-family: 'Oswald', sans-serif;
    font-size:64px;
    color:white;
}    

option{
    font-family: 'Oswald', sans-serif;
    font-size:24px;
}

input{
    font-family: 'Oswald', sans-serif;
    font-size:24px;
}

}
</style>
</head>
<body>
<form method="get" action="final3.py">
<center><header> Battle! </header></center>
<center><table border="1"><tr><td>
'''

Bottom_HTML = '</table></center> <center> <input type="submit" value="GO!" name="pushit2"></center></form></body></html>'

movestats = {'Nothing': 'Nothing,1,0,100,0,2,1', 'Strength': 'Strength,1,80,100,0,2,1', 'Leer': 'Leer,1,0,100,0,1,20', 'Rock Throw': 'Rock Throw,6,50,90,0,2,1', 'Fissure': 'Fissure,5,1,30,0,2,39', 'Ice Punch': 'Ice Punch,15,75,100,0,2,6', 'Aurora Beam': 'Aurora Beam,15,65,100,0,3,69', 'Transform': 'Transform,1,0,0,0,1,58', 'Lick': 'Lick,8,20,100,0,2,7', 'Crabhammer': 'Crabhammer,11,90,90,0,2,1', 'Cut': 'Cut,1,50,95,0,2,1', 'Amnesia': 'Amnesia,14,0,0,0,1,14', 'Wing Attack': 'Wing Attack,3,60,100,0,2,1', 'Smog': 'Smog,4,20,70,0,3,3', 'Super Fang': 'Super Fang,1,1,90,0,2,41', 'Spike Cannon': 'Spike Cannon,1,20,100,0,2,30', 'Fire Blast': 'Fire Blast,10,120,85,0,3,5', 'Thunder Wave': 'Thunder Wave,13,0,100,0,1,68', 'Agility': 'Agility,14,0,0,0,1,53', 'Confuse Ray': 'Confuse Ray,8,0,100,0,1,50', 'Skull Bash': 'Skull Bash,1,100,100,0,2,40', 'Growl': 'Growl,1,0,100,0,1,19', 'Poisonpowder': 'Poisonpowder,4,0,75,0,1,67', 'Absorb': 'Absorb,12,20,100,0,3,4', 'Stomp': 'Stomp,1,65,100,0,2,32', 'Slam': 'Slam,1,80,75,0,2,1', 'Razor Leaf': 'Razor Leaf,12,55,95,0,2,1', 'Surf': 'Surf,11,95,100,0,3,1', 'Acid Armor': 'Acid Armor,4,0,0,0,1,12', 'Waterfall': 'Waterfall,11,80,100,0,2,32', 'Razor Wind': 'Razor Wind,1,80,100,0,3,40', 'Fury Swipes': 'Fury Swipes,1,18,80,0,2,30', 'Barrier': 'Barrier,14,0,0,0,1,12', 'Tackle': 'Tackle,1,50,100,0,2,1', 'Sing': 'Sing,1,0,55,0,1,2', 'Explosion': 'Explosion,1,250,100,0,2,8', 'Psywave': 'Psywave,14,1,80,0,3,88', 'Earthquake': 'Earthquake,5,100,100,0,2,1', 'Jump Kick': 'Jump Kick,2,100,95,0,2,46', 'Fly': 'Fly,3,90,95,0,2,156', 'Quick Attack': 'Quick Attack,1,40,100,1,2,1', 'Mega Kick': 'Mega Kick,1,120,75,0,2,1', 'Vicegrip': 'Vicegrip,1,55,100,0,2,1', 'Bind': 'Bind,1,15,85,0,2,43', 'Dig': 'Dig,5,80,100,0,2,156', 'Double Kick': 'Double Kick,2,30,100,0,2,45', 'Headbutt': 'Headbutt,1,70,100,0,2,32', 'Fire Punch': 'Fire Punch,10,75,100,0,2,5', 'Double Edge': 'Double Edge,1,120,100,0,2,49', 'Bonemerang': 'Bonemerang,5,50,90,0,2,45', 'Fury Attack': 'Fury Attack,1,15,85,0,2,30', 'Withdraw': 'Withdraw,11,0,0,0,1,12', 'Hi Jump Kick': 'Hi Jump Kick,2,130,90,0,2,46', 'Psybeam': 'Psybeam,14,65,100,0,3,77', 'Take Down': 'Take Down,1,90,85,0,2,49', 'Rolling Kick': 'Rolling Kick,2,60,85,0,2,32', 'Drill Peck': 'Drill Peck,3,80,100,0,2,1', 'Thunderpunch': 'Thunderpunch,13,75,100,0,2,7', 'Clamp': 'Clamp,11,35,85,0,2,43', 'Bubblebeam': 'Bubblebeam,11,65,100,0,3,71', 'Pound': 'Pound,1,40,100,0,2,1', 'Glare': 'Glare,1,0,90,0,1,68', 'Dizzy Punch': 'Dizzy Punch,1,70,100,0,2,77', 'Blizzard': 'Blizzard,15,120,70,0,3,6', 'Spore': 'Spore,12,0,100,0,1,2', 'Softboiled': 'Softboiled,1,0,0,0,1,33', 'Rest': 'Rest,14,0,0,0,1,38', 'Leech Seed': 'Leech Seed,12,0,90,0,1,85', 'Tail Whip': 'Tail Whip,1,0,100,0,1,20', 'Pin Missile': 'Pin Missile,7,14,85,0,2,30', 'Lovely Kiss': 'Lovely Kiss,1,0,75,0,1,2', 'Sludge': 'Sludge,4,65,100,0,3,3', 'Recover': 'Recover,1,0,0,0,1,33', 'Twineedle': 'Twineedle,7,25,100,0,2,78', 'Stun Spore': 'Stun Spore,12,0,75,0,1,68', 'Karate Chop': 'Karate Chop,2,50,100,0,2,1', 'String Shot': 'String Shot,7,0,95,0,1,21', 'Slash': 'Slash,1,70,100,0,2,1', 'Supersonic': 'Supersonic,1,0,55,0,1,50', 'Thunder': 'Thunder,13,120,70,0,3,7', 'Psychic': 'Psychic,14,90,100,0,3,73', 'Doubleslap': 'Doubleslap,1,15,85,0,2,30', 'Mega Punch': 'Mega Punch,1,80,85,0,2,1', 'Dragon Rage': 'Dragon Rage,16,40,100,0,3,42', 'Light Screen': 'Light Screen,14,0,0,0,1,14', 'Meditate': 'Meditate,14,0,0,0,1,11', 'Bite': 'Bite,17,60,100,0,2,32', 'Swords Dance': 'Swords Dance,1,0,0,0,1,11', 'Toxic': 'Toxic,4,0,90,0,1,34', 'Solarbeam': 'Solarbeam,12,120,100,0,3,40', 'Hyper Fang': 'Hyper Fang,1,80,90,0,2,32', 'Vine Whip': 'Vine Whip,12,35,100,0,2,1', 'Bubble': 'Bubble,11,20,100,0,3,71', 'Egg Bomb': 'Egg Bomb,1,100,75,0,2,1', 'Flamethrower': 'Flamethrower,10,95,100,0,3,5', 'Seismic Toss': 'Seismic Toss,2,100,100,0,2,88', 'Horn Drill': 'Horn Drill,1,1,30,0,2,39', 'Bone Club': 'Bone Club,5,65,85,0,2,32', 'Thunderbolt': 'Thunderbolt,13,95,100,0,3,7', 'Submission': 'Submission,2,80,80,0,2,49', 'Ice Beam': 'Ice Beam,15,95,100,0,3,6', 'Confusion': 'Confusion,14,50,100,0,3,77', 'Hydro Pump': 'Hydro Pump,11,120,80,0,3,1', 'Sky Attack': 'Sky Attack,3,140,90,0,2,40', 'Reflect': 'Reflect,14,0,0,0,1,12', 'Night Shade': 'Night Shade,8,100,100,0,3,88', 'Barrage': 'Barrage,1,15,85,0,2,30', 'Thundershock': 'Thundershock,13,40,100,0,3,7', 'Gust': 'Gust,3,40,100,0,3,1', 'Peck': 'Peck,3,35,100,0,2,1', 'Swift': 'Swift,1,60,100,0,3,18', 'Poison Gas': 'Poison Gas,4,0,80,0,1,67', 'Leech Life': 'Leech Life,7,20,100,0,2,4', 'Fire Spin': 'Fire Spin,10,35,85,0,3,43', 'Sonicboom': 'Sonicboom,1,40,90,0,3,131', 'Sharpen': 'Sharpen,1,0,0,0,1,11', 'Sleep Powder': 'Sleep Powder,12,0,75,0,1,2', 'Ember': 'Ember,10,40,100,0,3,5', 'Body Slam': 'Body Slam,1,85,100,0,2,7', 'Screech': 'Screech,1,0,85,0,1,20', 'Horn Attack': 'Horn Attack,1,65,100,0,2,1', 'Dream Eater': 'Dream Eater,14,100,100,0,3,9', 'Harden': 'Harden,1,0,0,0,1,12', 'Wrap': 'Wrap,1,15,90,0,2,43', 'Low Kick': 'Low Kick,2,60,100,0,2,1', 'Selfdestruct': 'Selfdestruct,1,200,100,0,2,8', 'Constrict': 'Constrict,1,10,100,0,2,71', 'Comet Punch': 'Comet Punch,1,18,85,0,2,30', 'Acid': 'Acid,4,40,100,0,3,73', 'Defense Curl': 'Defense Curl,1,0,0,0,1,12', 'Hypnosis': 'Hypnosis,14,0,60,0,1,2', 'Scratch': 'Scratch,1,40,100,0,2,1', 'Mega Drain': 'Mega Drain,12,40,100,0,3,4', 'Rock Slide': 'Rock Slide,6,75,90,0,2,32', 'Pay Day': 'Pay Day,1,40,100,0,2,1', 'Growth': 'Growth,1,0,0,0,1,317', 'Water Gun': 'Water Gun,11,40,100,0,3,1', 'Guillotine': 'Guillotine,1,1,30,0,2,39', 'Hyper Beam': 'Hyper Beam,1,150,90,0,3,81', 'Poison Sting': 'Poison Sting,4,15,100,0,2,3'}
typetoid={"Normal":"1","Fighting":"2",'Flying':"3","Poison":"4","Ground":'5',"Rock":"6","Bug":"7","Ghost":"8","Fire":"10","Water":"11","Grass":"12","Electric":"13","Psychic":"14","Ice":"15","Dragon":"16"}
weaknesses={"1":["2"],"2":["3","14"],"3":["6","13","15"],"4":["5","14"],"5":["11","12","15"],"6":["2","5","11","12"],"7":["3","6","10"],"8":["8"],"10":["5","6","11"],"11":["12","13"],"12":["3","7","10","4","15"],"13":["5"],"14":["8","7"],"15":["2","6","10"],"16":["15","16"]}
immunity={"1":["8"],"2":[],"3":["5"],"4":[],"5":[],"6":[],"7":[],"8":["1","2"],"10":[],"11":[],"12":[],"13":[],"14":[],"15":[],"16":[]}
pokemontoid={'charmeleon': '5', 'krabby': '98', 'spearow': '21', 'arcanine': '59', 'venusaur': '3', 'charmander': '4', 'articuno': '144', 'pinsir': '127', 'weezing': '110', 'mewtwo': '150', 'cloyster': '91', 'dragonite': '149', 'tauros': '128', 'fearow': '22', 'paras': '46', 'kadabra': '64', 'jigglypuff': '39', 'sandslash': '28', 'abra': '63', 'hitmonlee': '106', 'lickitung': '108', 'machamp': '68', 'haunter': '93', 'wigglytuff': '40', 'kangaskhan': '115', 'machop': '66', 'goldeen': '118', 'gengar': '94', 'poliwhirl': '61', 'hitmonchan': '107', 'clefable': '36', 'ditto': '132', 'gloom': '44', 'tentacool': '72', 'primeape': '57', 'magnemite': '81', 'squirtle': '7', 'vulpix': '37', 'koffing': '109', 'bellsprout': '69', 'kingler': '99', 'dratini': '147', 'nidoqueen': '31', 'magneton': '82', 'psyduck': '54', 'omastar': '139', 'persian': '53', 'raichu': '26', 'electrode': '101', 'golem': '76', 'farfetchd': '83', 'moltres': '146', 'mew': '151', 'gyarados': '130', 'vaporeon': '134', 'doduo': '84', 'muk': '89', 'marowak': '105', 'wartortle': '8', 'gastly': '92', 'slowpoke': '79', 'rhydon': '112', 'cubone': '104', 'chansey': '113', 'geodude': '74', 'parasect': '47', 'pidgeotto': '17', 'venomoth': '49', 'victreebel': '71', 'nidorino': '33', 'nidorina': '30', 'rhyhorn': '111', 'tentacruel': '73', 'lapras': '131', 'grimer': '88', 'ninetales': '38', 'mankey': '56', 'scyther': '123', 'dodrio': '85', 'ekans': '23', 'seadra': '117', 'nidoking': '34', 'zapdos': '145', 'kabutops': '141', 'voltorb': '100', 'jolteon': '135', 'metapod': '11', 'growlithe': '58', 'rattata': '19', 'eevee': '133', 'snorlax': '143', 'kabuto': '140', 'poliwrath': '62', 'electabuzz': '125', 'ivysaur': '2', 'omanyte': '138', 'drowzee': '96', 'graveler': '75', 'caterpie': '10', 'diglett': '50', 'hypno': '97', 'machoke': '67', 'horsea': '116', 'exeggutor': '103', 'sandshrew': '27', 'golduck': '55', 'aerodactyl': '142', 'pidgeot': '18', 'exeggcute': '102', 'nidoran-f': '29', 'nidoran-m': '32', 'onix': '95', 'clefairy': '35', 'meowth': '52', 'dugtrio': '51', 'porygon': '137', 'slowbro': '80', 'alakazam': '65', 'zubat': '41', 'staryu': '120', 'weedle': '13', 'seel': '86', 'blastoise': '9', 'oddish': '43', 'flareon': '136', 'bulbasaur': '1', 'beedrill': '15', 'arbok': '24', 'ponyta': '77', 'butterfree': '12', 'tangela': '114', 'venonat': '48', 'charizard': '6', 'magikarp': '129', 'pikachu': '25', 'pidgey': '16', 'jynx': '124', 'seaking': '119', 'dewgong': '87', 'dragonair': '148', 'kakuna': '14', 'starmie': '121', 'shellder': '90', 'magmar': '126', 'weepinbell': '70', 'golbat': '42', 'mr-mime': '122', 'poliwag': '60', 'vileplume': '45', 'rapidash': '78', 'raticate': '20'}

from random import randint
from math import floor

data=[]
one=[]
two=[]
three=[]
four=[]
five=[]
six=[]
a1=[]
a2=[]
a3=[]
a4=[]
a5=[]
a6=[]
maxone=0
maxa1=0

def getdata ():
    global data
    global one
    global two
    global three
    global four
    global five
    global six
    global a1
    global a2
    global a3
    global a4
    global a5
    global a6
    global maxone
    global maxa1
    data=open("data.txt","rU")
    data=data.read()
    data=data.split('\n')
    one=data[0].split(',')
    two=data[1].split(',')
    three=data[2].split(',')
    four=data[3].split(',')
    five=data[4].split(',')
    six=data[5].split(',')
    a1=data[6].split(',')
    a2=data[7].split(',')
    a3=data[8].split(',')
    a4=data[9].split(',')
    a5=data[10].split(',')
    a6=data[11].split(',')
    maxone=int(one[3])
    maxa1=int(a1[3])

def statuslose():
    global data
    global one
    global two
    global three
    global four
    global five
    global six
    global a1
    global a2
    global a3
    global a4
    global a5
    global a6
    global maxone
    global maxa1
    if one[2] != 'None':
        if randint(0,9) > 7:
            one[2] = 'None'
    if a1[2] != 'None':
        if randint(0,9) > 7:
            a1[2] = 'None'

Attacker1 = True
Attackera = True

def status():
    global data
    global one
    global two
    global three
    global four
    global five
    global six
    global a1
    global a2
    global a3
    global a4
    global a5
    global a6
    global maxone
    global maxa1
    global Attacker1
    global Attackera
    if one[2] == 'Asleep':
        Attacker1 = False
    if a1[2] == 'Asleep':
        Attackera = False
    if one[2] == 'Paralyzed':
        if randint(0,1) == 0:
            Attacker1 = False
    if a1[2] == 'Paralyzed':
        if randint(0,1) == 0:
            Attackera = False
    if one[2] == 'Poisoned':
        one[3] = str(int(floor(int(one[3])) - 10))
    if a1[2] == 'Poisoned':
        a1[3] = str(int(floor(int(a1[3])) - 10))
    if one[2] == 'Badly Poisoned':
        one[3] = str(int(floor(int(one[3])) - 15))
    if a1[2] == 'Badly Poisoned':
        a1[3] = str(int(floor(int(a1[3])) - 15 ))
    if one[2] == 'Confused':
        one[3] = str(int(floor(int(one[3])) - 20 ))
    if a1[2] == 'Confused':
        a1[3] = str(int(floor(int(a1[3])) - 20 ))
    if one[2] == 'Leeched':
        one[3] = str(int(floor(int(one[3])) - 10 ))
        a1[3] = str(int(floor(int(a1[3])) + 10 ))
    if a1[2] == 'Leeched':
        a1[3] = str(int(floor(int(a1[3])) - 10 ))
        one[3] = str(int(floor(int(one[3])) + 10 ))

damage1=0
damagea1=0
attack=""
attacka1=""

def effects ():
    global damage1
    global damagea1
    global attack
    global attacka1
    form=cgi.FieldStorage()
    attack= form.getvalue('move','Nothing')
    attacka1= a1[(randint(1,4) * -1)]
    damagea1 = int(movestats[attacka1].split(',')[2])
    damage1 = int(movestats[attack].split(',')[2])
    if movestats[attack].split(',')[-2] == '2':
        damage1 = (42 * damage1 * int(one[4]) / int(a1[5])) / 50
        modifier = 1
        if movestats[attack].split(',')[1] == typetoid[one[1]]:
            modifier *= 1.5
        if movestats[attack].split(',')[1] in weaknesses[typetoid[a1[1]]]:
            modifier *= 2
        if movestats[attack].split(',')[1] in immunity[typetoid[a1[1]]]:
            modifier *= 0
        damage1 = int(floor((damage1 + 2) * modifier))
    if movestats[attack].split(',')[-2] == '3':
        damage1 = (42 * damage1 * int(one[6]) / int(a1[6])) / 50
        modifier = 1
        if movestats[attack].split(',')[1] == typetoid[one[1]]:
            modifier *= 1.5
        if movestats[attack].split(',')[1] in weaknesses[typetoid[a1[1]]]:
            modifier *= 2
        if movestats[attack].split(',')[1] in immunity[typetoid[a1[1]]]:
            modifier *= 0
        damage1 = int(floor((damage1 + 2) * modifier))
    if movestats[attacka1].split(',')[-2] == '2':
        damagea1 = (42 * damagea1 * int(a1[4]) / int(one[5])) / 50
        modifier = 1
        if movestats[attacka1].split(',')[1] == typetoid[a1[1]]:
            modifier *= 1.5
        if movestats[attacka1].split(',')[1] in weaknesses[typetoid[one[1]]]:
            modifier *= 2
        if movestats[attacka1].split(',')[1] in immunity[typetoid[one[1]]]:
            modifier *= 0
        damagea1 = int(floor((damagea1 + 2) * modifier))
    if movestats[attacka1].split(',')[-2] == '3':
        damagea1 = (42 * damagea1 * int(a1[6]) / int(one[6])) / 50
        modifier = 1
        if movestats[attacka1].split(',')[1] == typetoid[a1[1]]:
            modifier *= 1.5
        if movestats[attacka1].split(',')[1] in weaknesses[typetoid[one[1]]]:
            modifier *= 2
        if movestats[attacka1].split(',')[1] in immunity[typetoid[one[1]]]:
            modifier *= 0
        damagea1 = int(floor((damagea1 + 2) * modifier))

def final():
    global Attacker1
    global Attackera
    AISpeed = a1[7]
    ISpeed = one[7]
    if int(AISpeed) > int(ISpeed):
        if randint(1,100) <= int(movestats[attacka1].split(',')[3]) and Attackera != False:
            one[3] = str(int(one[3]) - damagea1)
            if movestats[attacka1].split(',')[-1] == '2':
                one[2] = 'Asleep'
            if movestats[attacka1].split(',')[-1] == '4':
                a1[3] = str((int(a1[3])) + int(floor((damagea1 * 0.5))))
            if movestats[attacka1].split(',')[-1] == '68':
                one[2] = 'Paralyzed'
            if movestats[attacka1].split(',')[-1] == '8':
                a1[3] = str(0)
            if movestats[attacka1].split(',')[-1] == '9' and one[2] == 'Asleep':
                a1[3] = str((int(a1[3])) + int(floor((damagea1 * 0.5))))
            if movestats[attacka1].split(',')[-1] == '30':
                one[3] = str((int(one[3])) - ((randint(1,4)) * damagea1))
            if movestats[attacka1].split(',')[-1] == '39':
                one[3] = str(0)
            if movestats[attacka1].split(',')[-1] == '45':
                one[3] = str((int(one[3])) - damagea1)
            if movestats[attacka1].split(',')[-1] == '50':
                one[2] = 'Confused'
            if movestats[attacka1].split(',')[-1] in ['131','88','42']:
                one[3] = str((int(one[3])) - 100)
            if movestats[attacka1].split(',')[-1] == '85':
                one[2] = 'Leeched'
            if movestats[attacka1].split(',')[-1] == '67':
                one[2] = 'Poisoned'
            if movestats[attacka1].split(',')[-1] == '34':
                one[2] = 'Badly Poisoned'
            if movestats[attacka1].split(',')[-1] == '33':
                a1[3] = str((int(a1[3])) + 20)
            if movestats[attacka1].split(',')[-1] == '38':
                a1[3] = str(maxa1)
                a1[2] = 'Asleep'
            if movestats[attacka1].split(',')[-1] == '41':
                one[3] = str(int(floor(int(one[3])) * 0.5))
            if movestats[attacka1].split(',')[-1] == '49':
                a1[3] = str((int(a1[3])) - int(floor((damagea1 * 0.25))))
        else:
            if movestats[attacka1].split(',')[-1] == '46' and Attackera != False:
                a1[3] = str(int(floor(0.5 * (int(a1[3])))))
        if randint(1,100) <= int(movestats[attack].split(',')[3]) and Attacker1 != False:
            a1[3] = str(int(a1[3]) - damage1)    
            if movestats[attack].split(',')[-1] == '2':
                a1[2] = 'Asleep'
            if movestats[attack].split(',')[-1] == '4':
                one[3] = str((int(one[3])) + int(floor((damage1 * 0.5))))
            if movestats[attack].split(',')[-1] == '68':
                a1[2] = 'Paralyzed'
            if movestats[attack].split(',')[-1] == '8':
                one[3] = str(0)
            if movestats[attack].split(',')[-1] == '9' and a1[2] == 'Asleep':
                one[3] = str((int(one[3])) + int(floor((damage1 * 0.5))))
            if movestats[attack].split(',')[-1] == '30':
                a1[3] = str((int(a1[3])) - ((randint(1,4)) * damage1))
            if movestats[attack].split(',')[-1] == '39':
                a1[3] = str(0)
            if movestats[attack].split(',')[-1] == '45':
                a1[3] = str((int(a1[3])) - damage1)
            if movestats[attack].split(',')[-1] == '50':
                a1[2] = 'Confused'
            if movestats[attack].split(',')[-1] in ['131','88','42']:
                a1[3] = str((int(al[3])) - 100)
            if movestats[attack].split(',')[-1] == '85':
                a1[2] = 'Leeched'
            if movestats[attack].split(',')[-1] == '67':
                a1[2] = 'Poisoned'
            if movestats[attack].split(',')[-1] == '34':
                a1[2] = 'Badly Poisoned'
            if movestats[attack].split(',')[-1] == '33':
                one[3] = str((int(one[3])) + 20)
            if movestats[attack].split(',')[-1] == '38':
                one[3] = str(maxone)
                one[2] = 'Asleep'
            if movestats[attack].split(',')[-1] == '41':
                a1[3] = str(int(floor((int(a1[3])) * 0.5)))
            if movestats[attack].split(',')[-1] == '49':
                one[3] = str((int(one[3])) - int(floor((damage1 * 0.25))))
        else:
            if movestats[attack].split(',')[-1] == '46' and Attacker1 != False:
                one[3] = str(floor(0.5 * (int(one[3]))))
    else:
        if randint(1,100) <= int(movestats[attack].split(',')[3]) and Attacker1 != False:
            a1[3] = str(int(a1[3]) - damage1)    
            if randint(1,100) <= int(movestats[attack].split(',')[3]):
                a1[3] = str(int(a1[3]) - damage1)    
            if movestats[attack].split(',')[-1] == '2':
                a1[2] = 'Asleep'
            if movestats[attack].split(',')[-1] == '4':
                one[3] = str((int(one[3])) + int(floor((damage1 * 0.5))))
            if movestats[attack].split(',')[-1] == '68':
                a1[2] = 'Paralyzed'
            if movestats[attack].split(',')[-1] == '8':
                one[3] = str(0)
            if movestats[attack].split(',')[-1] == '9' and a1[2] == 'Asleep':
                one[3] = str((int(one[3])) + int(floor((damage1 * 0.5))))
            if movestats[attack].split(',')[-1] == '30':
                a1[3] = str((int(a1[3])) - ((randint(1,4)) * damage1))
            if movestats[attack].split(',')[-1] == '39':
                a1[3] = str(0)
            if movestats[attack].split(',')[-1] == '45':
                a1[3] = str((int(a1[3])) - damage1)
            if movestats[attack].split(',')[-1] == '50':
                a1[2] = 'Confused'
            if movestats[attack].split(',')[-1] in ['131','88','42']:
                a1[3] = str((int(al[3])) - 100)
            if movestats[attack].split(',')[-1] == '85':
                a1[2] = 'Leeched'
            if movestats[attack].split(',')[-1] == '67':
                a1[2] = 'Poisoned'
            if movestats[attack].split(',')[-1] == '34':
                a1[2] = 'Badly Poisoned'
            if movestats[attack].split(',')[-1] == '33':
                one[3] = str((int(one[3])) + 20)
            if movestats[attack].split(',')[-1] == '38':
                one[3] = str(maxone)
                one[2] = 'Asleep'
            if movestats[attack].split(',')[-1] == '41':
                a1[3] = str(int(floor((int(a1[3])) * 0.5)))
            if movestats[attack].split(',')[-1] == '49':
                one[3] = str((int(one[3])) - int(floor((damage1 * 0.25))))
        else:
            if movestats[attack].split(',')[-1] == '46' and Attacker1 != False:
                one[3] = str(floor(0.5 * (int(one[3]))))
        if randint(1,100) <= int(movestats[attacka1].split(',')[3]) and Attackera != False:
            one[3] = str(int(one[3]) - damagea1)
            if movestats[attacka1].split(',')[-1] == '2':
                one[2] = 'Asleep'
            if movestats[attacka1].split(',')[-1] == '4':
                a1[3] = str((int(a1[3])) + int(floor((damagea1 * 0.5))))
            if movestats[attacka1].split(',')[-1] == '68':
                one[2] = 'Paralyzed'
            if movestats[attacka1].split(',')[-1] == '8':
                a1[3] = str(0)
            if movestats[attacka1].split(',')[-1] == '9' and one[2] == 'Asleep':
                a1[3] = str((int(a1[3])) + int(floor((damagea1 * 0.5))))
            if movestats[attacka1].split(',')[-1] == '30':
                one[3] = str((int(one[3])) - ((randint(1,4)) * damagea1))
            if movestats[attacka1].split(',')[-1] == '39':
                one[3] = str(0)
            if movestats[attacka1].split(',')[-1] == '45':
                one[3] = str((int(one[3])) - damagea1)
            if movestats[attacka1].split(',')[-1] == '50':
                one[2] = 'Confused'
            if movestats[attacka1].split(',')[-1] in ['131','88','42']:
                one[3] = str((int(one[3])) - 100)
            if movestats[attacka1].split(',')[-1] == '85':
                one[2] = 'Leeched'
            if movestats[attacka1].split(',')[-1] == '67':
                one[2] = 'Poisoned'
            if movestats[attacka1].split(',')[-1] == '34':
                one[2] = 'Badly Poisoned'
            if movestats[attacka1].split(',')[-1] == '33':
                a1[3] = str((int(a1[3])) + 20)
            if movestats[attacka1].split(',')[-1] == '38':
                a1[3] = str(maxa1)
                a1[2] = 'Asleep'
            if movestats[attacka1].split(',')[-1] == '41':
                one[3] = str(int(floor(int(one[3])) * 0.5))
            if movestats[attacka1].split(',')[-1] == '49':
                a1[3] = str((int(a1[3])) - int(floor((damagea1 * 0.25))))
        else:
            if movestats[attacka1].split(',')[-1] == '46' and Attacker1 != False:
                a1[3] = str(int(floor(0.5 * (int(a1[3])))))
    data = open('data.txt','w')
    if int(one[3]) <= 0 and int(a1[3]) <= 0:
        data.write(','.join(two) + '\n' + ','.join(three) + '\n' + ','.join(four) + '\n' + ','.join(five) + '\n' + ','.join(six) + '\n' + ','.join(one) + '\n' + ','.join(a2) + '\n' + ','.join(a3) + '\n' + ','.join(a4) + '\n' + ','.join(a5) + '\n' + ','.join(a6) + '\n' + ','.join(a1))
    elif int(one[3]) <= 0:
        data.write(','.join(two) + '\n' + ','.join(three) + '\n' + ','.join(four) + '\n' + ','.join(five) + '\n' + ','.join(six) + '\n' + ','.join(one) + '\n' + ','.join(a1) + '\n' + ','.join(a2) + '\n' + ','.join(a3) + '\n' + ','.join(a4) + '\n' + ','.join(a5) + '\n' + ','.join(a6))
    elif int(a1[3]) <= 0:
        data.write(','.join(one) + '\n' + ','.join(two) + '\n' + ','.join(three) + '\n' + ','.join(four) + '\n' + ','.join(five) + '\n' + ','.join(six) + '\n' + ','.join(a2) + '\n' + ','.join(a3) + '\n' + ','.join(a4) + '\n' + ','.join(a5) + '\n' + ','.join(a6) + '\n' + ','.join(a1))
    else:
        data.write(','.join(one) + '\n' + ','.join(two) + '\n' + ','.join(three) + '\n' + ','.join(four) + '\n' + ','.join(five) + '\n' + ','.join(six) + '\n' + ','.join(a1) + '\n' + ','.join(a2) + '\n' + ','.join(a3) + '\n' + ','.join(a4) + '\n' + ','.join(a5) + '\n' + ','.join(a6))
    data.close()

def printer ():
    data = open('data.txt','rU').read().split('\n')
    if int(one[3]) <= 0 and int(two[3]) <= 0 and int(three[3]) <= 0 and int(four[3]) <= 0 and int(five[3]) <= 0 and int(six[3]) <= 0:
        print HTML_HEADER + '<html><head><html><head> <title>Pokemon Generation 1 Battle Simulator</title><script language="javascript" type="text/javascript">alert("You lost to a mindless machine. You should be ashamed of yourself. Get better ya filthy casual.")</script><link href="style1.css" type="text/css" rel="stylesheet"><link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"><style>*{font-size:64px;}</style></head><body><center><a href="final.html"><button>I indeed have no life</button></a></center></body></html>'
    elif int(a1[3]) <= 0 and int(a2[3]) <= 0 and int(a3[3]) <= 0 and int(a4[3]) <= 0 and int(a5[3]) <= 0 and int(a6[3]) <= 0:
        print HTML_HEADER + '<html><head><html><head> <title>Pokemon Generation 1 Battle Simulator</title><script language="javascript" type="text/javascript">alert("Wow arent you proud of yourself for beating a defenseless AI? Glad you feel SOOO happy about that. Play against some real people ya tryhard.")</script><link href="style1.css" type="text/css" rel="stylesheet"><link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"><style>*{font-size:64px;}</style></head><body><center><a href="final.html"><button>I find pleasure in this pointless sport</button></a></center></body></html>'
    else:
        print HTML_HEADER + Top_HTML + one[0] + '</td><td>'+a1[0]+'</td></tr><tr><td><img src="' +pokemontoid[one[0].lower()]+'.png"/></td><td><img src="'+pokemontoid[a1[0].lower()]+'.png"/></td></tr><td>Type='+ one[1] + '</td><td>Type='+a1[1]+'</td></tr><tr><td>Status=' + one[2] + '</td><td>Status='+a1[2]+'</td></tr><tr><td>HP=' + one[3] + '</td><td>HP='+a1[3]+'</td></tr></table></center><br>'     
        print '<center><table border="1"><tr><td>' + '<input type="radio" value="' + one[-4] + '" name="move">'+one[-4]+'<br><input type="radio" value="' + one[-3] + '" name="move">'+one[-3]+'<br><input type="radio" value="' + one[-2] + '" name="move">'+one[-2]+'<br><input type="radio" value="' + one[-1] + '" name="move">'+one[-1] + '</td></tr>' + Bottom_HTML    

getdata()
statuslose()
status()
effects()
final()
getdata()
printer()
