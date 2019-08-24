/* Team #4, Team Lin^3 : Victor Lin, Ricky Lin, Susan Lin
   APCS1 pd2
   HW11 -- Breaking the Bank
   2017-10-04 */

public class Teller
{
	public static void main(String[] args)
	{
		BankAccount fred = new BankAccount();
		System.out.println("Default Account Info:");
		System.out.println(fred);
		fred.setBalance(1000);
		System.out.println("New balance = 1000");
		System.out.println(fred);
		fred.setName("fred");
		fred.setPW("password");
		fred.setPin(1111);
		fred.setNum(123456789);
		fred.withdraw(5);
		fred.deposit(10);
		System.out.println("New Account Info:");
		System.out.println(fred);
	}
}
