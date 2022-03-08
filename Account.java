package Banking;

import java.time.LocalDate;




import java.time.LocalTime;

import java.util.regex.Pattern; //  package used for creating  patterns 





//backend part of the application (account class)


//try to make an abstract class/ interface of a general account

//then do savings, checking, retirement




public abstract class Account { //general account class structure (abstract class)
	
	static start start = new start(); //start object to access start functions

	
	public static LocalDate today = LocalDate.now(); // initializes current date 
	public static LocalTime time = LocalTime.now(); // initializes current time 
	
	//abstract functions (functions all the classes (savings account, checking account, retirement account) will impliment)
	
	abstract void addhistory(String add); // will add to the current account's history
	abstract String gethistory(); //will get the current account's history 
	abstract boolean transfer(Profile user, Account a, Account b,double amount); // transfers money between accounts
	abstract void deposit(double amount); // deposit money (function)
	abstract boolean withdrawal(double amount); //withdraws money from an account
	abstract String getamount(); //returns the amount of money currently in the user's account
	abstract String getaccountinfo(); //gets account information
	abstract String getaccounttype(); //returns accounttype
	abstract String getname();

	
	

	
	 
   
	 
}









//more specific classes (savings, checking, retirement)

class checkingaccount extends Account{ //inherits from abstract account class
	
	//needed variables
	private double funds = 0; // amount of money in the User's account
	private String type = "Checking Account"; // type of account opened (retirement, savings, checking) (r,s,c)
	private String info = ""; //account information, basically toString
	private String name = ""; // account name 
	private String accounthistory = ""; //account history (if anything is changed in the account, it is added to this String variable)
	
	
	
	private static Pattern money = Pattern.compile("^\\d{1,}.\\d{2,2}$"); //money format 1+ amount of numerical digits.two numerical digits, money pattern
	
	
	
	//  constructor to create checking account (for the first time)

	public checkingaccount(Profile user, String name) {
	

		this.name = name; // gives the name property of this account object the  value of the test string variable. 
		
		String fund = start.input("Input the initial deposit for this account:\n");
		
		while (  !(money.matcher(fund).matches() && Double.parseDouble(fund) > 0)   ) {
			
			
			fund = start.input("Your previous input was invalid.\nInput the initial deposit for this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
		}
		
		
		
		this.funds = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
		
		


		start.mid = false;
		 
		 start.accountcreated = true;
		
		
		
		
		//updates date and time
		today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		// adds the change to the account history
		addhistory("\nDay: " + today + " (year-month-day):" +
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New Checking Account named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to account history
				
		
		//adds the change to the user's history
	
		user.addhistory(
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New Checking Account named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to user history
				
		
		start.output("Your " + this.type + " named " + this.name + " has been created with an initial deposit of " + this.funds +"$!!!!");
		
		start.output("YOU HAVE CREATED A NEW BANK ACCOUNT!!!!!");
		
		start.output("\n\nPress enter to continue");
		
		start.input("");
		
		start.accountcreated = false;
		start.mid = true;
		
	}
	
	//This is the function for an account already created and being recovered based on a file
	public checkingaccount(Profile user, String name, double startingfunds, String t, String info, String accounthist) {
		//reloads previous information
		this.type = t;
		this.accounthistory = accounthist;
		this.info = info;
		
		this.name = name;
		this.funds = startingfunds;
		
		//updates date and time
		today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		// adds the change to the account history
		addhistory("\nDay: " + today + " (year-month-day):" +
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "Previously Used Checking Account named: " + this.name + " initialized with a balance of " + this.funds + "$" + ".\n"); //adds this action to account history
				
	
		// adds the change to the user history
		user.addhistory(
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "Previously Used Checking Account named: " + this.name + " initialized with a balance of " + this.funds + "$" + ".\n"); //adds this action to profile history
			
		
		start.output("Your " + this.type + " named " + this.name + " has been re-initialized with a balance of " + this.funds +"$.");
	}
	
	
	//Related functions for checking account
	public String gethistory() {
	
		
		return this.accounthistory;
	}
	

	
	public void addhistory(String add) {
	// will add to the current account's history
		
		this.accounthistory += add ;
		
	}

	
	public boolean transfer(Profile user, Account a, Account b,double amount){
		// transfers money between accounts (from account a to account b)
		
		
		
		if( !(a.withdrawal(amount )) ) {
			
			start.output("Insufficient funds for this transfer.");
			return false;
			
			
		}//reduces the amount of money in account a by amount 
		
		b.deposit(amount); //increases the amount of money in account b by amount
		
		start.output("Transfer successful: " + amount + "$ was transferred from " + a.getaccounttype() + " "  + a.getname() + " to "  + b.getaccounttype() + " " +  b.getname() + ".");
		
		
		
		
		//updates time
		// the user's profile history will contain this
		time = LocalTime.now(); //updates current time
		user.addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + amount + "$ was transferred from " + a.getaccounttype() + " "  + a.getname() + " to " + b.getaccounttype() + " " +  b.getname() + ".");
		
	
		
		
		
		return true;
		
		
		
	}
	
	
	
	public void deposit(double amount) { // deposit money (function)
		
		
		this.funds += amount; // increases funds by amount (money deposit)
		
		start.output("Your deposit of " + amount + "$ into account " + this.name + " was successful!\n");
		
		
		start.transaction.play();
		//adds the change to history
		
			//updates time
				
		    time = LocalTime.now(); //updates current time
			addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A deposit of " + amount + "$ was made into " + this.getaccounttype() + " "+ this.name + ".\n");
			
		
	}
	
	
	public boolean withdrawal(double amount) {
	
		
		boolean p = (this.funds - amount >= 0);
	
		
		
		if  (this.funds - amount >= 0) {
			//subtracts funds by amount sent
			//withdraws money from an account
			this.funds = (this.funds - amount); 
			
			
		start.output("Your withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was successful.\n");
		
		
		//adds the change to history
		
		//updates time
		start.transaction.play();
		
	    time = LocalTime.now(); //updates current time
		addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was made.\n");
		
		} 
		else {
			start.output("You have insufficient funds for this withdrawal.");
		}
		
		
		return p;
		
		
		
		
		
	}
	public String getamount() {
		//returns the amount of money currently in the user's account
		
		return String.valueOf(this.funds);
	}
	
	
	public String getaccountinfo() {
		//gets account information
		
		
		this.info =  "\nAccount: " + this.name + " is a " + this.type + "."+
				":\n" + "This account has " + this.funds + "$." + "\n"+
		       "\nAccount History:\n" + this.gethistory() + "\n";
		
		
		return this.info;
	}
	public String getaccounttype() {
		//returns accounttype
		
		
		return this.type;
	}
	public String getname() {
		
		return this.name;
	}
	
	
	
	
	
}











class savingsaccount extends Account{ //inherits from abstract Account class
	//needed variables 
	 protected double funds = 0; // amount of money in the User's account
	 protected String type = "Savings Account"; // type of account opened (retirement, savings, checking) (r,s,c)
	 protected String info = ""; //account information
	 protected String name = ""; // account name 
	 protected String accounthistory = ""; //account history (if anything is changed in the account, it is added to this String variable)
	 protected static Pattern money = Pattern.compile("^\\d{1,}.\\d{2,2}$"); //money format 1+ amount of numerical digits.two numerical digits
	
	
	
	
	// constructor to create savings account
	public savingsaccount(Profile user, String name) {
	
		
		this.name = name; // gives the name property of this account object the  value of the test string variable. 
		
		String fund = start.input("Input the initial deposit for this account:\n");
		
		while (  !(money.matcher(fund).matches() && Double.parseDouble(fund) > 0 )   ) {
			
			
			fund = start.input("Your previous input was invalid.\nInput the initial deposit for this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
		}
		
		
		
		this.funds = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
		
		
		
		

		
		start.mid = false;
	
		 start.accountcreated = true;
		 
		
		
		
		
		//updates date and time
		today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		// adds the change to the account history
		addhistory("\nDay: " + today + " (year-month-day):" +
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New Savings Account named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to account history
				
		
		//adds the change to the user's history
	
		user.addhistory(
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New Savings Account named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to user history
		
		
		
		start.output("Your " + this.type + " named " + this.name + " has been created with an initial deposit of " + this.funds + "$!!!!");
		
		start.output("YOU HAVE CREATED A NEW BANK ACCOUNT!!!!!");
		
		start.output("\n\nPress enter to continue");
		
		start.input("");
		
		start.accountcreated = false; 
		start.mid = true;
		
	}
	
	
	
	
	// constructor to create retirement account
		public savingsaccount(Profile user, String type, String name ) { //to create a retirement account
			
			this.type = type;
			
			
			this.name = name; // gives the name property of this account object the  value of the test string variable. 
			
			String fund = start.input("Input the initial deposit for this account:\n");
			
			while (  !(money.matcher(fund).matches() && Double.parseDouble(fund) > 0)  ) {
				
				
				fund = start.input("Your previous input was invalid.\nInput the initial deposit for this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.) :\n");
			}
			
			
			
			this.funds = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
			
			
			
			
			

			
			start.mid = false;
			 
			 start.accountcreated = true;
		
			
			
			
			//updates date and time
			today = LocalDate.now(); // updates current date 
			time = LocalTime.now(); //updates current time
			
			// adds the change to the account history
			addhistory("\nDay: " + today + " (year-month-day):" +
					 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New "+ this.type+ " named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to account history
					
			
			//adds the change to the user's history
		
			user.addhistory(
					 "\n - " + time + " (Hour:minute:second:nanosecond): " + "New " + this.type + " named: " + this.name + " created with an initial deposit of " + this.funds + "$" + ".\n"); //adds this action to user history
			
			
			
			
			start.output("Your " + this.type + " named " + this.name + " has been created with an initial deposit of " + this.funds + "$!!!!");
			
			start.output("YOU HAVE CREATED A NEW BANK ACCOUNT!!!!!");
			
			start.output("\n\nPress enter to continue");
			
			start.input("");
			
			start.accountcreated = false;
			start.mid = true;
			
		}
		
		
		
		
	//This is the function for a savings account already created and being recovered based on a file
	public savingsaccount(Profile user, String name, double startingfunds, String t, String i, String accounthist) {
		
		//reload previous info
		this.type = t;
		this.accounthistory = accounthist;
		this.info = i;
		
		
		this.name = name;
		this.funds = startingfunds;
		
		//updates date and time
		today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		// adds the change to the account history
				addhistory("\nDay: " + today + " (year-month-day):" +
						 "\n - " + time + " (Hour:minute:second:nanosecond): " + "Previously Used " + this.type + " named: " + this.name + " initialized with a balance of " + this.funds + "$" + ".\n"); //adds this action to account history
						
			
				// adds the change to the user history
				user.addhistory(
						 "\n - " + time + " (Hour:minute:second:nanosecond): " + "Previously Used " + this.type + " named: " + this.name + " initialized with a balance of " + this.funds + "$" + ".\n"); //adds this action to profile history
					
		
		start.output("Your " + this.type + " named " + this.name + " has been re-initialized with a balance of " + this.funds + "$.");
	}
	
	
	
	//Related functions for Savings account
	
		public String gethistory() {
		
			
			return this.accounthistory;
		}
		

		
		public void addhistory(String add) {
		// will add to the current account's history
			
			this.accounthistory += add;
			
		}

		
		public boolean transfer(Profile user, Account a, Account b,double amount){
			// transfers money between accounts (from account a to account b)
			
			
			
			if( !(a.withdrawal(amount )) ) {
				
				start.output("Insufficient funds for this transfer.");
				return false;
				
				
			}//reduces the amount of money in account a by amount 
			
			b.deposit(amount); //increases the amount of money in account b by amount
			
			
			start.output("Transfer successful: " + amount + "$ was transferred from " + a.getname() + " to " + b.getname() + ".");
			
			

			//adds the change to history
		
			//updates time
				// the user's profile history will contain this
		    time = LocalTime.now(); //updates current time
			user.addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + amount + "$ was transferred from " + a.getaccounttype() + " "  + a.getname() + " to " + b.getaccounttype() + " " +  b.getname() +".");
			
			return true;
			
			
			
		}
		
		
		
		public void deposit(double amount) { // deposit money (function)
			
			
			this.funds += amount; // increases funds by amount (money deposit)
			
			start.output("Your deposit of " + amount + "$ into account " + this.name + " was successful!\n");
			
			
			//adds the change to history
			
			//updates time
			
			start.transaction.play();
				
		    time = LocalTime.now(); //updates current time
			addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A deposit of " + amount + "$ was made into " + this.getaccounttype() + " "+ this.name + ".\n");
			
		
			
			
		}
		
		
		public boolean withdrawal(double amount) {

			
			boolean p = (this.funds - amount >= 0);
					
			if  (this.funds - amount >= 0) {
				//subtracts funds by amount sent
				//withdraws money from an account
				this.funds = (this.funds - amount); 
				
				
			start.output("Your Withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was successful.\n");
			
			start.transaction.play();
			//adds the change to history
			
			//updates time
				
		    time = LocalTime.now(); //updates current time
			addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A Withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was made.\n");
			
			} 
			else {
				start.output("You have insufficient funds for this withdrawal.");
			}
			
			
			return p;
			
			
			
			
			
			
		}
		public String getamount() {
			//returns the amount of money currently in the user's account
			
			return String.valueOf(this.funds);
		}
		
		
		public String getaccountinfo() {
			//gets account information
			
			
			this.info =  "\nAccount: " + this.name + " is a " + this.type + "."+
					":\n" + "This account has " + this.funds + "$." + "\n"+
			       "\nHistory:\n" + this.gethistory() + "\n";
			
			return this.info;
		}
		public String getaccounttype() {
			//returns accounttype
			
			
			return this.type;
		}
		
		
		public String getname() {
			
			return this.name;
		}
		
		
		
}












class retirementaccount extends savingsaccount{ //inherits from savings account which inherits from abstract account class
	
	//can access needed variables from savings account class
	 private boolean roth = false; //if it is a roth retirement account 
	 private String type = "Retirement Account";// r is retirement account
	 private static Pattern money = Pattern.compile("^\\d{1,}.\\d{2,2}$"); //money format 1+ amount of numerical digits.two numerical digits
	 
		
	
	// constructor to create retirement account
	 
	 //for the first time
	public retirementaccount(Profile user, String name) {
	
		super(user, "Retirement Account", name);
		
		
		if(user.getprofiletype().equals("Soulsilver")) {
			
			if(  (user.calcroth(user.getretirementaccounts()) <  2) ) {
				
				String r = start.input("Would you like to make this retirement account a roth account (one of the maximum 2 you can own under a soulsilver plan)? (input y for yes)");
				
				if(r.equals("y")){
					this.roth = true;
				}
				else {
					this.roth = false;
				}
				
				
			}
		}
		else if (user.getprofiletype().equals("Heartgold")) {
			
			
		
				
				String r = start.input("Would you like to make this retirement account a roth account? (input y for yes)");
				
				if(r.equals("y")){
					this.roth = true;
				}
				else {
					this.roth = false;
				}
				
				
			
			
			
		}
		else {
			
			this.roth = false;
		}
		
		
		
	}
	
	 //based on a file
		public retirementaccount(Profile user, String name, double startingfunds, String t, String i,String accounthist, boolean roth) {
			//reloads previous information
			super(user, name, startingfunds,t,i,accounthist);
			this.roth = roth;
	

			
			
		}
		
	
	
	//returns if the account is a roth account or not.
	public boolean getroth() {
		
		return this.roth;
	}
	
	
	
	public void deposit(double amount) { // deposit money (function)
		
		
		if(this.roth) { //roth pays a 10% tax on money deposited.
			
			amount *= 0.9;
			Math.round(amount);
			start.output("You have to pay taxes on a roth account when you deposit, so after taxes and fees, the amount deposited into your account is " + amount + "$.(about 10% lower)");
		}
		
		this.funds += amount; // increases funds by amount (money deposit)
		
		start.output("Your deposit of " + amount + "$ into account " + this.name + " was successful!\n");
		
		start.transaction.play();
		
		//adds the change to history
		
		//updates time
			
	    time = LocalTime.now(); //updates current time
		addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A deposit of " + amount + "$ was made into " + this.getaccounttype() + " "+ this.name + ".\n");
		
	
		
		
	}
	
	
	public boolean withdrawal(double amount) {

		
		boolean p = (this.funds - amount >= 0);
		
		if  (this.funds - amount >= 0) {
			
			//subtracts funds by amount sent
			//withdraws money from an account
			this.funds = (this.funds - amount); 
			
			double with = amount * 0.8;
			Math.round(with);
			if( !(this.roth) ) { //roth pays a 10% tax on money deposited.
				
				
				
				start.output("You have to pay taxes on a non-roth account when you withdraw money, so after taxes and fees, the amount you get is " + with + "$.(about 20% lower)" + " " + 
				"\nThe amount taken from this account is " + amount);
			}
			
			
		start.output("Your Withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was successful.\n");
		
		start.transaction.play();
		//adds the change to history
		
		//updates time
			
	    time = LocalTime.now(); //updates current time
		addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): " + "A Withdrawal of " + amount + "$ from " + this.getaccounttype() + " "+ this.name + " was made.\n");
		
		} 
		else {
			start.output("You have insufficient funds for this withdrawal.");
		}
		
		
		return p;
		
		
		
		
		
		
	}
	
}























