( define ( or35 n )  
         ( or ( = 0 ( remainder n 3 ) ) 
              ( = 0 ( remainder n 5 ) ) ) ) 
( or35 3 )
( or35 10 )
( or35 8 ) 

( define ( xor x y ) 
   ( and 
     ( or x y )
          ( not
            ( and x y ) ) ) ) 

( define ( old35 n ) 
         ( xor ( = 0 ( remainder n 3 ) ) 
               ( = 0 ( remainder n 5 ) ) ) ) 

( old35 3 ) 
( old35 15 ) 
( old35 30 )
( old35 5 ) 

( define ( more20 n )
   ( or
   ( = 1
   ( remainder n 20 ) )
   ( = 2 
       ( remainder n 20 ) ) ) ) 

( more20 20 ) 
( more20 21 )
( more20 22 ) 
( more20 19 ) 
( more20 18 )

( define ( less20 n )
   ( or 
     ( = 19 
         ( remainder n 20 ) )
     ( = 18 
         ( remainder n 20 ) ) ) ) 
( less20 19 ) 
( less20 39 ) 
( less20 20 )
( less20 18 ) 

( define ( nearten n )
   ( or 
     ( = 1
         ( remainder n 10 ) )   
     ( = 2 
         ( remainder n 10 ) )
     ( = 8 
         ( remainder n 10 ) )
     ( = 9 
         ( remainder n 10 ) ) ) ) 