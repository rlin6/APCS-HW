( define ( calcGrade n ) 
   ( if ( >= n 90 ) 
        "Pass" 
        ( if ( >= n 70 ) 
             "Fail"
             "Disown" ) ) ) 
( calcGrade 99) 
( calcGrade 71) 
( calcGrade 55) 

             