/* Team #4, Team Lin^3 : Victor Lin, Ricky Lin, Susan Lin
   APCS1 pd2
   HW10 -- Mo Money Mo Problems
   2017-10-03 */

public class BankAccount
{
	public String accName; //inst variables
	private String accPassword;
	private int accPin;
	private int accNum;
	public double balance;

	public BankAccount()
	{
		accName = "Lin^3";
		accPassword = "uhh_crippling";
		accPin = 1234;
		accNum = 123456789;
		//default values
	}

	public BankAccount(String name, String pw, int pin, int num, double bal)
	{
		accName = name;
		accPassword = pw;
		accPin = pin;
		accNum = num;
		balance = bal;
		//overloaded constructor
	}

	//mutators
	public static String setName(String newName)
	{
		String old = accName;
		accName = newName;
		return old;
	}

	public static String setPW(String newPW)
	{
		String old = accPassword;
		accPassword = newPW;
		return old;
	}

	public static int setPin(int newPin)
	{
		int old = accPin;
		accPin = newPin;
		return old;
	}

	public static int setNum(int newNum)
	{
		int old = accNum;
		accNum = newNum;
		return old;
	}

	public static double setBal(double newBal)
	{
		double old = balance;
		balance = newBal;
		return old;
	}

	public void deposit(double input)
	{
		balance += input;
		//deposit money into balance
	}

	public void withdrawal(double input)
	{
		balance -= input;
		//withdraw money from balance
	}

	public void info()
	{
		System.out.println("Name: " + accName);
		System.out.println("Password: " + accPassword);
		System.out.println("PIN: " + accPin);
		System.out.println("Account Number: " + accNum);
		System.out.println("Balance: " + balance);
		//print out account information
	}

	public String toString()
	{
		String ret = "\nAccount Info:\n";
		ret += "\nName: " + accName;
		ret += "\nPassword: " + accPassword;
		ret += "\nPIN: " + accPin;
		ret += "\nAccount Number: " + accNum;
		ret += "\nBalance: " + balance;
		return ret;
	}

	public static void main(String[] args)
	{
		BankAccount steve = new BankAccount();
		steve.info();
		steve.deposit(300.21);
		steve.info();
		steve.withdrawal(300.21);
		steve.info();
		//prints account info after every change in balance
	}
}
