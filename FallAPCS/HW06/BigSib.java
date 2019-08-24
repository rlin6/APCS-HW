/* Ricky Lin
   APCS1 pd2
   HW06 -- On BigSib Individuality and the Elimination of Radio Fuzz
   2017-09-25 */ 

public class BigSib 
{
    
    private static String helloMsg;  //private instance variable of hello message

    public static void setHelloMsg(String msg) 
    { //mutator that changes instance variable
        helloMsg = msg;
    }

    public static String greet(String name) 
    { //returns the set helloMsg with the given name
        return helloMsg + name;
    }
}