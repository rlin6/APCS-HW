( define ( to24hourtime hour ampm ) 
   ( cond
        ( ( and ( = hour 12 ) ( = ampm 0 ) ) 0 ) 
        ( ( and ( = hour 12 ) ( = ampm 1 ) ) 12 ) 
        ( else ( + hour ( * 12 ampm ) ) ) ) )  
 
( to24hourtime 12 0 )
( to24hourtime 12 1 )
( to24hourtime 1 0 )
( to24hourtime 1 1 ) 

( define ( to24hourtimeShort hour ampm ) 
   ( + hour ( * 12 ampm ) ) )
( to24hourtimeShort 12 0 ) 
( to24hourtimeShort 12 1 ) 
( to24hourtimeShort 1 0 ) 
( to24hourtimeShort 1 1 ) 

( define ( Leapyear year ) 
   ( cond
      ( ( and ( = 0 ( remainder y 100 ) ) 
              ( > 0 ( remainder y 400 ) ) ) #f )
( LeapYear 1600 ) 

   
        