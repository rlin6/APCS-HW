( define ( makeEven x ) 
   ( if 
     ( = 0 ( remainder x 2 ) )  
         x
         ( + x 1 ) ) )
( makeEven -1 ) 
( makeEven 4 ) 
( makeEven 3 ) 

( define ( checkSign x ) 
   ( cond 
      ( ( > x 0 ) 1 ) 
      ( ( < x 0 ) 2 ) 
      ( ( = x 0 ) 3 ) ) ) 

( checkSign 4.5 ) 
( checkSign -3 )
( checkSign 0 ) 

( define ( myQuotient a b ) 
  ( / ( - a ( remainder a b ) ) b ) )  
( myQuotient 10 3 ) 
( myQuotient 10 4 ) 
( myQuotient 9 3 ) 
( myQuotient 9 12 ) 

( define ( whichQuadrant x y )
( cond 
( ( and ( > x 0 ) ( > y 0 ) ) 1 ) 
( ( and ( < x 0 ) ( > y 0 ) ) 2 )
( ( and ( < x 0 ) ( < y 0 ) ) 3 ) 
( ( and ( > x 0 ) ( < y 0 ) ) 4 ) 
( ( or ( = x 0 ) ( = y 0 ) ) 0 ) ) ) 