package Banking;//general package storing all the classes used for banking
import java.util.ArrayList; //arraylist package

import java.io.File;


import java.time.LocalDate; //package for date  
import java.time.LocalTime; //package for time  

import java.security.SecureRandom; //used for random
import java.util.regex.Pattern; //  package used for creating  patterns 


import java.util.HashMap; //new data structure I am learning to use (like a dictionary in python) 


//backend part of the application (Profile class) 

 //profile class 
/*
 * 
 * The template for a profile
 * no user input/output will be conducted in this class
 */
 
public class Profile {

	
	//Components of a Profile
	private HashMap <String,String> login = new HashMap <String,String>(); //Username/ password
	// Trying to use hashmap, this will store the User's Username and password <Username,Password>
	//kind of like a dictionary in python and initialized like an arraylist in java
	
	private String dob = "N/A";  //date of birth
	private String sq = "N/A"; //security question (if they forget their username or password)
	private String sa ="N/A"; // answer to security question
	private String race = "N/A"; // their race(s)
	private String sex = "N/A"; //store's user's biological sex (male or female)
	
	
	//arraylist of each type of account 
	private ArrayList<savingsaccount> savingsaccounts = new ArrayList<savingsaccount>();
	private ArrayList<checkingaccount> checkingaccounts = new ArrayList<checkingaccount>();
	private ArrayList<retirementaccount> retirementaccounts = new ArrayList<retirementaccount>();
	
	
	private int maxaccounts = 2; //(amount of each type of account) 2 is the default. Depending on the account type the number of max accounts per profile is 2,4,10

	
	//money pattern
	
	public static Pattern money = Pattern.compile("^\\d{1,}.\\d{2,2}$"); //money format 1+ amount of numerical digits.two numerical digits, money pattern
	
	
	//create arraylists of savings,checking, retirement accounts.
	
	private int numofaccounts = 0; //number of bank accounts total
	//use size property of the arraylists to get amount for each individual accounttype 
	
	private String profiletype = "Brickbronze"; // type of profile the user has (Brickbronze, soulsilver, heartgold)
	private String firstname = "N/A"; //user's first name
	private String lastname = "N/A"; //user's lastname
	private String profilenum = "N/A"; //user's profile number
	private String ssn = "N/A"; //social security number 
	

	
	
	private String profilehistory = ""; //stores what the user does on which date (creating the profile, adding an account , modifying fields)
	
	private static Pattern date = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$"); //date format (dd/mm/yyyy)
	
	private static Pattern ss = Pattern.compile("^\\d{3}-\\d{2}-\\d{4}$"); //social security number format (nnn-nn-nnnn) (number n)
	
	static SecureRandom rand = new SecureRandom(); //random generator to generate profile number
	
	public static LocalDate today = LocalDate.now(); // initializes current date 
	public static LocalTime time = LocalTime.now(); // initializes current time 
	
	
	static start start = new start(); // used for interaction between static and non static methods
	
	
	//this previous info is only used in account re-initialization/ the filing and reloading process
		private String[] previousfileinfo;
		private int[] prevfileinfo; // stores number of previously used accounts (position0 stores number of checking , 1 is num of saving, 2 is num of retire) 
		
	
	
	public Profile() { //default, created before user's profile is created (the program won't know if the user is a prior user or new user ) 
		
    
		this.firstname = "No First Name.";
		this.lastname = "No Last Name.";
		
		
		this.login.put("No Username", "No Password");
		this.dob = "No Date Of Birth has been Inputted.";
		this.sq = "No Security Question has been Inputted.";
		this.sa = "No Security Answer has been Inputted.";
		this.race = "No Race has been Inputted.";
		this.sex = "No Biological Gender has been Inputted.";
		this.profiletype = "Brickbronze";
		this.profilenum = "No profile number generated.";
		this.ssn = "No social security number entered for this profile.";
		
	}
	
	
	
	
	
	
	//Constructor (Requires a legitimate Username and Password to create a Profile, used for a new user
	public Profile(String key) { //constructor used for new users (this is their first profile)
		

		//updates date and time
		today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		// adds the change to the account history
		addhistory("\nDay: " + today + " (year-month-day):" +
				 "\n - " + time + " (Hour:minute:second:nanosecond): " + "Profile created."); //adds this action to history
		
		
		
		
		//Input the user's first name
		String n = start.input("Input your (or the profile owner's) first name:");
		
		
		while(!(namechecker(n,1))) { //checks name input for only letters and hiphans
			start.output("Your name must only contain letters.\n");
			 n = start.input("Input your (or the profile owner's) first name:");
			
		}
		
		
		//capitalizes the first letter in the user's name and makes the rest lowercase
		n.substring(1,n.length()).toLowerCase();
		n.substring(0,1).toUpperCase();
		
		this.firstname = n;
		
		
	
		
		//Input the user's last name
		String l = start.input("Input your (or the Profile Owner's) last name:");
		while(!(namechecker(l,2))) {
			start.output("Your name must only contain letters.\n");
			 l = start.input("Input your (or the Profile Owner's) last name:");
			
		}
		
		//capitalizes the first letter in the user's name and makes the rest lowercase
		l.substring(0,1).toUpperCase();
		l.substring(1,l.length()).toLowerCase();
	
		this.lastname = l;
		
		
		
		
		//This is where the user will create their profile (on default it will just create the username/password)
		
		//Creates a Username (***can't be the same as another user's username(This functionality will be added later ***)
		String username = loginchecker(0);
		
		
		//Create a password (Must have a Special character and be at least 8 characters long to work)
		String password = loginchecker(1);
		
		this.login.put(username, password);
		this.dob = "No Date Of Birth has been Inputted.";
		this.sq = "No Security Question has been Inputted.";
		this.sa = "No Security Answer has been Inputted.";
		this.race = "No Race has been Inputted.";
		this.sex = "No Biological Gender has been Inputted.";
		this.profiletype = "Brickbronze";
		this.profilenum = "No profile number generated.";
		this.ssn = "No social security number entered for this profile.";
		
	
		
		
		
	}
	
	
	
	
//constructor for a returning user
	public Profile(String u, String p, String dob, String sq, String sa, 
			String r, String s,String fn, String ln, String ssn, 
			String pnum, String ptype , String phistory, int numofaccounts, int maxaccounts,
			int numofcheck, int numofsave, int numofretire,
			String allbankaccountinfo) {
		//This is a constructor, used if the user already has account information, make a way for user to modify
		// parameters in order (username, password, date of birth, security question, security question answer,
		// race, sex, first name, last name, social security number, 
		//profile ID number, profile type, profile history, number of accounts, maximum number of bank accounts,
		//number of checking accounts, number of saving accounts, number of retirement accounts 
		//all of the users bank account information
	
		this.profilehistory = phistory;
	
		//updates date and time
	    today = LocalDate.now(); // updates current date 
		time = LocalTime.now(); //updates current time
		
		
		// adds the change to the account history
		addhistory("\nDay: " + today + " (year-month-day):\n" +
				" - " + time + " (Hour:minute:second:nanose"
						+ "cond): " + "Profile was logged into and initialized.\n"); //adds this action to history

		
		this.login.put(u, p);
		this.dob = dob;
		this.sq = sq;
		this.sa = sa;
		this.race = r;
		this.sex = s;
		
		this.firstname = fn;
		
		this.lastname = ln;
		this.ssn = ssn;
		this.profilenum = pnum;
		this.profiletype = ptype;
		this.numofaccounts = numofaccounts;
		this.maxaccounts = maxaccounts;
		
		
		start.output("Your Profile Information has been retrieved! Thank you for being a loyal, returning customer!\n");
		//RELOAD ACCOUNT INFO HERE BASED ON STRING allbankaccountinfo
		
		
		String[] oldaccountinfo = allbankaccountinfo.split("\"");
		
	
		int prevaccountinfo[] = {numofcheck, numofsave,numofretire};
		
		this.previousfileinfo = oldaccountinfo;
		this.prevfileinfo = prevaccountinfo;
		
		
		

		
	}
	
	//restore user's account info
	
    public void ressurectaccountinfo(Profile user) {
    	ArrayList <checkingaccount> c = new ArrayList<checkingaccount>();
    	ArrayList <savingsaccount> s = new ArrayList<savingsaccount>();
    	ArrayList <retirementaccount> r = new ArrayList<retirementaccount>();
    

    	
    	
    	int pos = 0; // this will store where in the array (of previous info) the user is
    	
    	
    	
    	try {
    	
    	//recreating all checking accounts
    			//accessing the data and adding it to the checking account arraylist
    			for(int i = 0; i < user.prevfileinfo[0]; i++) {
    				
    				
    			
    				checkingaccount temp = new checkingaccount(user, user.previousfileinfo[pos], Double.valueOf( user.previousfileinfo[pos + 1]) , user.previousfileinfo[pos +2 ], user.previousfileinfo[pos + 3], user.previousfileinfo[pos + 4]);
    				c.add(temp);
    				pos +=5;
    				
    			}
    			
    			
    			
    			
    			
    			//recreating all savings accounts
    			
    			//accessing the data and adding it to the checking account arraylist
    			for(int i= 0; i < user.prevfileinfo[1]; i++) {
    				
    				
    				savingsaccount tymp = new savingsaccount(user, user.previousfileinfo[pos], Double.valueOf( user.previousfileinfo[pos + 1]) , user.previousfileinfo[pos +2 ], user.previousfileinfo[pos+ 3], user.previousfileinfo[pos + 4]);
    				s.add(tymp);
    				pos += 5;
    			}
    			

    			
    			//recreating all retirement accounts
    			
    			//accessing the data and adding it to the checking account arraylist
    			for(int i = 0; i < user.prevfileinfo[2]; i++) {
    				
    				retirementaccount text = new retirementaccount(user, user.previousfileinfo[pos], Double.valueOf( user.previousfileinfo[pos + 1]) , user.previousfileinfo[pos +2 ], user.previousfileinfo[pos + 3], user.previousfileinfo[pos + 4],Boolean.valueOf(user.previousfileinfo[pos +5]) );
    				r.add(text);
    				pos +=6;
    				
    			}
    			
    			
    			
    			//repopulates this new account information based on filed data
    			this.savingsaccounts = s;
    			this.retirementaccounts = r;
    			this.checkingaccounts = c;
    			
    			
    			
    			
    	}
    	catch(Exception anyexception) {
    		
    		
    		start.output("An error occured with the file, contact tech support for assistance");
    		System.exit(0);
    	}
    }
	
	//checks username and password 
//  password (Must have a Special character and be at least 8 characters long to work)
	// username (***can't be the same as another user's username(This functionality will be added later ***)
	//key determines the field being checked (0 = username , 1 = password)
	public String loginchecker(int key) {
	 
		String test = "";
		
		if (key == 0) { //creates username
			
			test = start.input("Input your desired username: ");
			
			while(test.length() < 8) {
				start.output("Your username must be at least 8 characters long");
				test = start.input("Input your desired username: ");
			}
			
			
			
		   
			time = LocalTime.now(); //updates current time
			
			//adds the username change to history
			addhistory( "\n - " + time + " (Hour-minute-second-nanosecond): " +
					"Username changed from {" + this.login.keySet() + "} to {" + 
			test + "}."); 
			
			
			 
		}
		else if (key == 1) { //creates password
			
		    
			test = start.input("Input your desired password:"); // in the start class, the user will input their desired username/password
			
		    
		    while( !(passwordchecker(test)) ) { //check password 
		    	
		    	//obtains potential password input
		    		start.output("Your Previous input was invalid!\n");
		    		test = start.input("Input your desired password:");  // in the start class, the user will input their desired username/password
					
				
		    }
		    
		    
		    
			
		  
			time = LocalTime.now(); //updates current time
			
		  //adds the password change to history
			addhistory("\n - " + time + " (Hour-minute-second-nanosecond): " +  "Password changed from {" + this.login.values() + "} to {" + 
			test + "}."); 
		    		
			
			
			
		}
		else {
			start.output("Illegitimate key, Security Breach detected.");//incase a breach occurs and the key is changed during runtime
			
			//plays team plasma encounter theme
			
			System.exit(0); //ends program
		}
		
		
		
		
		return test; //returns string

	}
	
	
	
	//modifies username and password
	public void modifylogin(HashMap<String,String> newlogin) { //key determines what is changed. 0 - new username, 1 - new password
		
		
		this.login = newlogin; //binds the properties of this login hashmap object and the newlogin object
		
	}
	
	
	
	
	
	
	// get/set methods for user's login information 
	
	//checks if the user's password works
	
	public boolean passwordchecker(String ps) {
	//  password (Must have a Special character and be at least 8 characters long to work)
	
		//numerical characters
		char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		//letters
		char[] lletters = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h','i','j',
				'k', 'l', 'm', 'n', 'o' , 'p', 'q' , 'r', 's', 
				't', 'u', 'v', 'w', 'x', 'y', 'z'}; //array of lowercase letters 
		
		//capital letters
		char[] cletters = {'A','B', 'C', 'D', 'E', 'F', 'G', 'H','I','J',
				'K', 'L', 'M', 'N', 'O' , 'P', 'Q' , 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //array of capital letters 
		
		
		//special characters
	    char[] special = { '@', '#', '$', '%', '^', '&', '*', '(', ')', 
	    		'`', ':', '<', '>', '/', '?',  '[', ']', '{', '}', 
	    		'|', '=', '-', '_'};
	    
	    //checks if the password is at least 8 characters long
	    if(ps.length() < 8) {
	    	start.output("\n***Your password must be at least 8 characters long.***\n");
    		return false;
	    }
	    
	    //checks if the first character is a number
	    for(int x = 0; x < nums.length; x++) {
	    	
	    	if(ps.charAt(0) == nums[x]) {
	    		start.output("\n***Your password must not start with a Number.***\n");
	    		return false;
	    	}
	    	
	    }
	    
	    
	    
	    
	    
	    //checks if the password has a numerical character
	   int numofchars = 0;
	    for(int x = 0; x < ps.length(); x++) {
	    	
	    	
		    for(int y = 0; y < nums.length; y++) {
		    	
		    	
			    
		    	if(ps.charAt(x) == nums[y]) {
		    		numofchars++;
		    		
		    	}
		    	
	    	
		    }
		    
		    
	    }
	    
	    if(!(numofchars >= 1)) {
	    	
	    	start.output("\n***Your Password must have a number (0-9).***\n");
	    	return false;
	    }
 
 
	    
	    
	    
	    
	    
	    
	    //checks if the password has a special character
	  numofchars = 0;
	    for(int x = 0; x < ps.length(); x++) {
	    	
	    	
		    for(int y = 0; y < special.length; y++) {
		    	
		    	
			    
		    	if(ps.charAt(x) == special[y]) {
		    		numofchars++;
		    		
		    	}
		    	
	    	
		    }
		    
		    
	    }
	    
	    if(!(numofchars >= 1)) {
	    	
	    	start.output("\n***Your Password must have a special character.***\n");
	    	return false;
	    }
 
	    
	    
	    
	    
	    
	    
	    //checks if the password has a lowercase letter
	    numofchars = 0;
	    for(int x = 0; x < ps.length(); x++) {
	    	
	    	
		    for(int y = 0; y < lletters.length; y++) {
		    	
		    	
			    
		    	if(ps.charAt(x) == lletters[y]) {
		    		numofchars++;
		    		
		    	}
		    	
	    	
		    }
		    
		    
	    }
	    
	    if(!(numofchars >= 1)) {
	    	
	    	start.output("\n***Your Password must have a lowercase letter***\n");
	    	return false;
	    }
	    
	    
	    
	    
	    
	    
	    
	    //checks if the password has a capital letter
	   numofchars = 0;
	    for(int x = 0; x < ps.length(); x++) {
	    	
	    	
		    for(int y = 0; y < cletters.length; y++) {
		    	
		    	
			    
		    	if(ps.charAt(x) == cletters[y]) {
		    		numofchars++;
		    		
		    	}
		    	
	    	
		    }
		    
		    
	    }
	    
	    if(!(numofchars >= 1)) {
	    	
	    	start.output("\n***Your Password must have a capital letter.***\n");
	    	return false;
	    }
 
 
	    
	    
	    
	    
	    
	    return true;
	    
	    
	    
	    
	    
	    
	    
	} //returns true if password was set successfully, false if not
	
	
	
	

	//checks strings for alphabet only characters 
	public boolean namechecker(String test, int key) { //string being tested, key (1 = firstname, 2 = last name )
		char[] letters = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h','i','j',
				'k', 'l', 'm', 'n', 'o' , 'p', 'q' , 'r', 's', 
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A','B', 'C', 'D', 'E', 'F', 'G', 'H','I','J',
				'K', 'L', 'M', 'N', 'O' , 'P', 'Q' , 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-'};
	
		
		
		int right = 0; 
		
		for(int i = 1; i < test.length(); i++) {
			
			
			for(int j= 0; j < letters.length; j++) {
				
				
				if(test.charAt(i) == letters[j]) {
					right++; //counts the amount of letter- characters in the string
					
				}
			}
			
			
		
		}
		
			if(right == test.length() - 1) {
				
				if(key == 1) {
					
					
				   
					time = LocalTime.now(); //updates current time
					
					//adds the first name change to history
					addhistory( "\n - " + time + " (Hour-minute-second-nanosecond): " + "The first name this profile is under has been changed from {" + this.firstname +
				"} to {" + test + "}."); 
			
							
							
					
				}
				else if (key == 2) {
					
					
					time = LocalTime.now(); //updates current time
					
					
					//adds the last name change to history
					addhistory("\n - " + time + " (Hour-minute-second-nanosecond): " + "The last name this profile is under has been changed from {" + this.lastname +
				"} to {" + test + "}."); 
			
							
							
				}
				else {
					
					start.output("Invalid key, Hacker alert");
					
					//play team plasma encounter theme
					
					System.exit(0);
				}
				
				
			}

		
		return (right == test.length() - 1); //checks if the amount of letter characters is equal to the size of the string
		
		
		
		
	}

	
	
	
	public HashMap getlogin() { 
		
		
		return this.login;
	} // returns user's login information
	
	
	
	
	
	
	
	
	
	
	
	//getmethods for the user's name (firstname and lastname) 
	public String getfirstname() {
		
		return this.firstname;
	}
	
	
	public String getlastname() {
		
		
		return this.lastname;
		
	}
	
	
	

	
	 
// get/set methods for user's date of birth
	public boolean modifydob(String birth) {  //resets birth

		boolean output = (date.matcher(birth).matches()) ? true : false;  //checks if the user's birth date matches date format
	//mm-dd-yyyy
		if(output) {
			//month
			if( (Integer.valueOf(birth.substring(0,2)) > 0) && (Integer.valueOf(birth.substring(0,2)) < 13)    ) {
				
				//day
				if( (Integer.valueOf(birth.substring(3,5))  > 0) && (Integer.valueOf(birth.substring(3,5)) < 32)  ) {
					
					//year
					if( (Integer.valueOf(birth.substring(6, birth.length())) > 0) ) {
					
						
						
						time = LocalTime.now(); //updates current time
						
					//adds the change to history
					addhistory("\n - " +  time + " (Hour-minute-second-nanosecond): "  + "The date of birth of the owner of this profile has been changed from {" + this.dob +
				"} to {" + birth + "}."); 

					
					this.dob = birth;
					
					}
					else {
						start.output("\nAll fields must be greater than 0 and conform to date input standards.\n");
						return false;
					}
					
				}
				else {
					start.output("\nAll fields must be greater than 0 and conform to date input standards.\n");
					return false;
				}
			}
			else {
				start.output("\nAll fields must be greater than 0 and conform to date input standards.\n");
				return false;
			}
			
	
		}
		

		return output;
		
		
	} //returns true if date of birth was set successfully, false if not
	
	public String getdob() {
		
		return this.dob;
	}
	
	
	 
	
	

	
	
	
	
	//get/set methods for user's security question
	public void modifysq() { //modifies the security question based on user input 
		
		//asks user for security question, asks if user wishes to reinput the security question, updates the security question accordingly
		
		String question = start.input("Input your desired security question (it will be asked whenever you wish to change your username or password):"); 
		//obtains  input for security question
		
		start.output("\nWould you like to re-input your security question?");
		start.output("--> Your current security question is: " + question);
		
		String response = start.input("Input your response (Input y for yes and n for no):" );
		
		while( start.responsechecker(response) ) {
			
			start.output("Your previous response was invalid. Please try again.\nWould you like to re-input your security question?\n");
			 response = start.input("Input your response (Input y for yes and n for no):" );//obtains the input
		}
		
		
		boolean retry = false;
		
		if(response.substring(0,1).toLowerCase().equals("y")) { //if the user wishes to re-input their security question
			
			retry = true;
		}
		
		while(retry) {
			
			
			
			question = start.input("Input your desired security question:"); //obtains new security question input
			
			start.output("\\nWould you like to re-input your security question? (Input y for yes and n for no)");
			start.output("--> Your current security question is: " + question);
			
			response = start.input("Input your response (Input y for yes and n for no):" );
			
			while( start.responsechecker(response) ) {
				
				start.output("Your previous response was invalid. Please try again.\nWould you like to re-input your security question? (Input y for yes and n for no)\n");
				response = start.input("Input your response (Input y for yes and n for no):" );//obtains the input
			}
			
			
			if(response.substring(0,1).toLowerCase().equals("n")) { //if the user wishes to re-input their security question
				
				retry = false;
			}
			
			
			
		}
		
		
		
		//adds a question mark to the end of the question
		
		
		if(  !(question.substring(question.length() - 1, question.length()).equals("?"))   ) {
			
			question = question + "?";
		}
		
		
		

		// adds the change to the account history
		
		
		time = LocalTime.now(); //updates current time
		
		addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The security question this profile is under has been changed from {" + this.sq +
				"} to {" + question + "}."); 
		
		
		//makes the user's input the new question
		this.sq = question;
			
		
		
		
	} 
	
	public String getsq() {
		
		
		return this.sq;
	} //gets security question
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// get/set methods for user's security question answer
	
	public void modifysa() {
	
		
	//asks user for security question answer, asks if user wishes to reinput the security question answer , updates the security question answer accordingly
		
		String answer = start.input("Input your desired security question answer(answer to the security question) "
				+ "(it will be required whenever you wish to change your password, username, or profile owner)"); 
		//obtains  input for security question answer
		
		start.output("\\nWould you like to re-input your security question answer?");
		start.output("--> Your current security question answer is: " + answer);
		
		String response = start.input("Input your response (Input y for yes and n for no)" );
		
		while( start.responsechecker(response) ) {
			
			start.output("Your previous response was invalid. Please try again.\nWould you like to re-input your security question answer?\n");
			 response = start.input("Input your response (Input y for yes and n for no):" );//obtains the input
		}
		
		
		boolean retry = false;
		
		if(response.substring(0,1).toLowerCase().equals("y")) { //if the user wishes to re-input their security question
			
			retry = true;
		}
		
		while(retry) {
			
			
			
			answer = start.input("Input your desired security question answer"); //obtains new security question input
			
			start.output("\\nWould you like to re-input your security question answer? (Input y for yes and n for no)");
			start.output("--> Your current security question answer is: " + answer);
			
			response = start.input("Input your response (Input y for yes and n for no):" );
			
			while( start.responsechecker(response) ) {
				
				start.output("Your previous response was invalid. Please try again.\nWould you like to re-input your security question answer? (Input y for yes and n for no)\n");
				response = start.input("Input your response (Input y for yes and n for no):" );//obtains the input
			}
			
			
			if(response.substring(0,1).toLowerCase().equals("n")) { //if the user wishes to re-input their security question
				
				retry = false;
			}
			
			
			
		}
		
		

	
		time = LocalTime.now(); //updates current time
	
		// adds the change to the account history
		addhistory("\n - " + time + " (Hour-minute-second-nanosecond): "+ "The security question answer this profile is under has been changed from {" + this.sa +
				"} to {" + answer + ".}."); 
		
		
		this.sa = answer;
		
		
		
	} 
	
	public String getsa() {
		
		
		return this.sa;
	} //returns security question answer datafield
	
	
	
	
	
	

	
	
	
	//get/set methods for user's biological sex
	public void modifysex() {
		String s = start.input("Input your biological gender (m for male and f for female):");
		
		while( !((s.equals("m")) || ( s.equals("f"))  ) )  {
			start.output("Your previous input was invalid\n");
			 s = start.input("Input your biological gender (m for male and f for female):");
		}
		
		
	
		
		if (s.equals("m")) {
			
			s = "male";
		}
		else if(s.equals("f")) {
			
			s = "female";
		}
		else {
			start.output("Hacker Alert");
			//play team plasma encounter theme
		}
		
		
		
		time = LocalTime.now(); //updates current time

		// adds the change to the account history
		addhistory("\n - " + time + " (Hour-minute-second-nanosecond): " + "The biological gender of the profile owner has been changed from {" + this.sex +
				"} to {" + s + "}." ); 
		
		
		this.sex = s;
		
	} //returns true if sex was set successfully, false if not
	
	public String getsex() {
		
		
		
		return this.sex;
	}// returns user's sex
	
	
	
	
	
	
	
	//get/set methods for user's race
	public void modifyrace() {
		
		
		
		String r = start.input("Input your biological race");
		
		while( start.letterchecker(r) == false) {
			start.output("Your previous input was invalid\n");
			 r = start.input("Input your biological race");
		}
		
		
	
		time = LocalTime.now(); //updates current time

		// adds the change to the account history
		addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The biological race this profile is under has been changed from {" + this.race +
				"} to {" + r + "}."); 
		
		
		this.race = r;
		
	} //returns true if race was set successfully, false if not
	
	public String getrace() {
		
		
		
		return this.race;
	}// returns user's sex
	
	
	
	
	//profile type functions 
	public void modifyprofiletype() {
		
		
		
		//if the user has a heartgold payment plan then they can downgrade
		if(this.profiletype.equals("Heartgold")) {
			//determines if the user can downgrade their profile payment plan to brickbronze or soulsilver
			boolean ss = false;
			boolean bb = false;
			
			
			//checks if the user has a small enough number of accounts to downgrade to the soulsilver plan
			
			//checks for soulsilver
				if(this.savingsaccounts.size() <= 4) {
					
					if(this.checkingaccounts.size() <= 4) {
						
						if(this.retirementaccounts.size() <= 4) {
							

							//checks roth accounts
							int f = 0;
							for(int r = 0; r < this.retirementaccounts.size(); r++) {
								if(this.retirementaccounts.get(r).getroth()) {
									f++;
								}
							}
							
							if(f < 3) {
							//user is able to downgrade to soulsilver
							ss = true;
							}
							
							
							
						}
						
						
					}
					
				}
				
				
				
				//checks for brickbronze
				if(this.savingsaccounts.size() <= 2) {
					
					if(this.checkingaccounts.size() <= 2) {
						
						if(this.retirementaccounts.size() <= 2) {
							
							
							//checks roth accounts
							int f = 0;
							for(int r = 0; r < this.retirementaccounts.size(); r++) {
								if(this.retirementaccounts.get(r).getroth()) {
									f++;
								}
							}
							
							if(f == 0) {
							//user is able to downgrade to brickbronze
							bb = true;
							}
							
						}
						
						
					}
				
				}
			
				
				String q = "";
				String choice = "";
				
				if(( (bb == true) && (ss == true))) {
					
					 q =  "Input your preffered profile payment plan. Your options are:"
							+ "\n* Brickbronze: a 0$ per month subscription where the user can have a maximum of 2 checking accounts, 2 savings accounts, and 2 retirement accounts. [User may not open roth IRAs or roth 401ks]"
							+ "\n* Soulsilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may  open 2 roth accounts if they are not above the maximum amount of accounts]"
							+ "\n* Heartgold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
							+ "*all money is not paid in this program, this is only a simulation* (money would normally be taken out of checking account)"
							+ "\n\nInput b for Brickbronze, s for Soulsilver, and h for Heartgold:";
					 
					 
						String in = start.input(q);
						
						
						
						
						while(  !(in.substring(0,1).toLowerCase().equals("b") || in.substring(0,1).toLowerCase().equals("h") || in.substring(0,1).toLowerCase().equals("s")    ) ) {
							start.output("Your previous input was invalid. Input b (Brickbronze), s(Soulsilver, or h(Heartgold).\n");
							 in = start.input("Input your preffered profile type.");
						}
						
					 
						 choice = (in.substring(0,1).toLowerCase().equals("b")) ?  "Brickbronze" : (in.substring(0,1).toLowerCase().equals("s")) ? "Soulsilver" : (in.substring(0,1).toLowerCase().equals("h")) ? "Heartgold" :  "N/A";
					 
						 
					
						 
				
				}
				else if((ss == true)) {
					
					  q =	"Input your preffered profile payment plan. Your current options are:"
								+ "\n* Soulsilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may  open 2 roth accounts if they are not above the maximum amount of accounts]"
								+ "\n* Heartgold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
								+ "*all money is not paid in this program, this is only a simulation* (money would normally be taken out of checking account)"
								+ "\n\nInput s for Soulsilver, and h for Heartgold:";
	
						
					  
					  
						String in = start.input(q);
						
						
						
						
						while(  !( in.substring(0,1).toLowerCase().equals("h") || in.substring(0,1).toLowerCase().equals("s")    ) ) {
							start.output("Your previous input was invalid. Input s(Soulsilver), or h(Heartgold).\n");
							 in = start.input("Input your preffered profile type.");
						}
						
						
						 choice =  (in.substring(0,1).toLowerCase().equals("s")) ? "Soulsilver" : "Heartgold" ;
					  
						 
						
				}
				else {
					
					start.output("You have too many accounts/roth accounts to be eligible for a downgrade to soulsilver or brickbronze!!");
					return;
				}
				
				
			
				
				
				
				
				
				//determines number of max accounts. 
				
				this.maxaccounts = choice.equals("Brickbronze") ? 2 : choice.equals("Soulsilver") ? 3 : choice.equals("Heartgold") ? 10 : -1; //-1 is an error or if a hacker occurs
			
				
			
				
				
				
				time = LocalTime.now(); //updates current time

				// adds the change to the account history
				addhistory("\n - " + time + " (Hour-minute-second-nanosecond): "  + "The profile type (profile payment plan) this profile is under has been changed from {" + this.profiletype +
						"} to {" + choice + "}." ); 
				
				 this.profiletype = choice;
				 
				 
				 return;
				
				
			
		}
		else if(this.profiletype.equals("Soulsilver")) {
			
	
			
			
			
			
			//determines if the user can downgrade their profile payment plan to brickbronze
			boolean bb = false;
			
			
		
				//checks for brickbronze
				if(this.savingsaccounts.size() <= 2) {
					
					if(this.checkingaccounts.size() <= 2) {
						
						if(this.retirementaccounts.size() <= 2) {
							
							
							//checks roth accounts
							int f = 0;
							for(int r = 0; r < this.retirementaccounts.size(); r++) {
								if(this.retirementaccounts.get(r).getroth()) {
									f++;
								}
							}
							
							if(f == 0) {
							//user is able to downgrade to brickbronze
							bb = true;
							}
							
						}
						
						
					}
				
				}
			
				
				String q = "";
				String choice = "";
				
				if(( (bb == true) )) {
					
					 q =  "Input your preffered profile payment plan. Your options are:"
							+ "\n* Brickbronze: a 0$ per month subscription where the user can have a maximum of 2 checking accounts, 2 savings accounts, and 2 retirement accounts. [User may not open roth IRAs or roth 401ks]"
							+ "\n* Soulsilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may  open 2 roth accounts if they are not above the maximum amount of accounts]"
							+ "\n* Heartgold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
							+ "*all money is not paid in this program, this is only a simulation* (money would normally be taken out of checking account)"
							+ "\n\nInput b for Brickbronze, s for Soulsilver, and h for Heartgold:";
					 
					 
						String in = start.input(q);
						
						
						
						
						while(  !(in.substring(0,1).toLowerCase().equals("b") || in.substring(0,1).toLowerCase().equals("h") || in.substring(0,1).toLowerCase().equals("s")    ) ) {
							start.output("Your previous input was invalid. Input b (Brickbronze), s(Soulsilver, or h(Heartgold).\n");
							 in = start.input("Input your preffered profile type:");
						}
						
					 
						 choice = (in.substring(0,1).toLowerCase().equals("b")) ?  "Brickbronze" : (in.substring(0,1).toLowerCase().equals("s")) ? "Soulsilver" : (in.substring(0,1).toLowerCase().equals("h")) ? "Heartgold" :  "N/A";
					 
				}
				else  {
					
					  q =	"Input your preffered profile payment plan. Your current options are:"
								+ "\n* Soulsilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may  open 2 roth accounts if they are not above the maximum amount of accounts]"
								+ "\n* Heartgold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
								+ "*all money is not paid in this program, this is only a simulation* (money would normally be taken out of checking account)"
								+ "\n\nInput s for Soulsilver, and h for Heartgold:";
	
						
					  
					  
						String in = start.input(q);
						
						
						
						
						while(  !( in.substring(0,1).toLowerCase().equals("h") || in.substring(0,1).toLowerCase().equals("s")    ) ) {
							start.output("Your previous input was invalid. Input s(Soulsilver, or h(Heartgold).\n");
							 in = start.input("Input your preffered profile type.");
						}
						
						
						 choice =  (in.substring(0,1).toLowerCase().equals("s")) ? "Soulsilver" : "Heartgold" ;
					  
						
				}
			
				
			
				
				
				
				
				
				//determines number of max accounts. 
				
				this.maxaccounts = choice.equals("Brickbronze") ? 2 : choice.equals("Soulsilver") ? 3 : choice.equals("Heartgold") ? 10 : -1; //-1 is an error or if a hacker occurs
			
				
			
				
				
				
				time = LocalTime.now(); //updates current time

				// adds the change to the account history
				addhistory("\n - " + time + " (Hour-minute-second-nanosecond): "  + "The profile type (profile payment plan) this profile is under has been changed from {" + this.profiletype +
						"} to {" + choice + "}." ); 
				
				 this.profiletype = choice;
				 
				 
				 return;
				
			
			
			
			
			
			
		}
		
		
		String in = start.input("Input your preffered profile payment plan. The options are:"
				+ "\n* Brickbronze: a 0$ per month subscription where the user can have a maximum of 2 checking accounts, 2 savings accounts, and 2 retirement accounts. [User may not open roth IRAs or roth 401ks]"
				+ "\n* Soulsilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may  open 2 roth accounts if they are not above the maximum amount of accounts]"
				+ "\n* Heartgold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
				+ "*all money is not paid in this program, this is only a simulation* (money would normally be taken out of checking account)"
				+ "\n\nInput b for Brickbronze, s for Soulsilver, and h for Heartgold:");
		
		
		while(  !(in.substring(0,1).toLowerCase().equals("b") || in.substring(0,1).toLowerCase().equals("h") || in.substring(0,1).toLowerCase().equals("s")    ) ) {
			start.output("Your previous input was invalid. Input b (Brickbronze), s(Soulsilver, or h(Heartgold)\n");
			 in = start.input("Input your preffered profile type:");
		}
		
		
		
		
		String choice = (in.substring(0,1).toLowerCase().equals("b")) ?  "Brickbronze" : (in.substring(0,1).toLowerCase().equals("s")) ? "Soulsilver" : (in.substring(0,1).toLowerCase().equals("h")) ? "Heartgold" :  "N/A";
		
		//determines number of max accounts. 
		
		this.maxaccounts = choice.equals("Brickbronze") ? 2 : choice.equals("Soulsilver") ? 3 : choice.equals("Heartgold") ? 10 : -1; //-1 is an error or if a hacker occurs
	
		
		
		
		time = LocalTime.now(); //updates current time

		// adds the change to the account history
		addhistory("\n - " + time + " (Hour-minute-second-nanosecond): "  + "The profile type (profile payment plan) this profile is under has been changed from {" + this.profiletype +
				"} to {" + choice + "}." ); 
		
		 this.profiletype = choice;
		 
		 
		 return;
	}
	public String getprofiletype() {
		
		return this.profiletype;
				
	}
	
	
	
	
	
	
// profile number functions
	public void modifyprofilenum() {
		
		int num = rand.nextInt(2147483647);  // max value of an integer in java
		
		
		if(num < 0) {
			num += 2147483647;
		}
		
		
	
		while(!testprofilenum(num)) {
			
			num = rand.nextInt(2147483647);  // max value of an integer in java
			
			
			if(num < 0) {
				num += 2147483647;
			}
			
			
		
		}
		
		
		
		time = LocalTime.now(); //updates current time

		// adds the change to the account history
		addhistory("\n - " + time + " (Hour-minute-second-nanosecond): " + "The profile number generated for this account is {" + num + "}."); 
		
		
		this.profilenum = String.valueOf(num); //converts random integer to string 
		
		
		
		
	}
	
	public boolean testprofilenum(int num) {
		//returns true if a valid num and no other account of that number exists in code/false if an invalid num
		
		File x = new File(num + ".txt");
		
		//returns false if the file exists
		if(x.exists()) {
			return false;
		}
		
		//returns true if the file does not exist
		return true;
		
	}
	public String getprofilenum() {
		
		return this.profilenum;
	}
	
	
	
	
	
	
	
	
	
// social security number functions
	public boolean modifysocialsecuritynumber(String sstest) {
		
		//checks if the user's social security number matches social security number format
		boolean output = (ss.matcher(sstest).matches()) ? true : false; 
	
		if(output) {
			
			

			time = LocalTime.now(); //updates current time
			
			// adds the change to the account history
			addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The social security number of the profile owner has been changed from {" + this.ssn +
					"} to {" + sstest + "}." ); 
			
			this.ssn = sstest;
			
			return output;
			
	
		}
		else {
			return output;
		}
	
		
		
		
	}
	
	
	public String getsocialsecuritynumber() {
		
		return this.ssn;
	}
	
	
	
	
	public void recordbreach() { //if the user causes a security breach
		time  = LocalTime.now();
		today = LocalDate.now();
		
		// adds the change to the account history
		start.output( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "User misinput their information more than 3 times"
				+ " and was kicked from the program as a result." );
		
	}
	
	
	
	
	//adds to the user's profile history
	public void addhistory(String add) {
		
		this.profilehistory += "\n" + add;  //adds to profiles history from whatever is stored in the string add variable
		
		
	} 

	
	public String gethistory() {
		
		return this.profilehistory;
	}
	
	
	
	
	
	
	
	
	

	//account aspects in profile class 
				
	//create and delete account need to go in profile class
	
	//to be implimented
	public boolean deleteaccount(String type,String name) { // type will either be s,r,t, name is account name
	
		if(type.equals("s")) { //delete a savings account
			
			for (int i = 0; i < this.savingsaccounts.size(); i++) {
				
				if (  (this.savingsaccounts.get(i).getname().equals(name))  && (Double.parseDouble((this.savingsaccounts.get(i).getamount())) == 0)  ) { 
					//if the savingsaccount is located in the arraylist and the funds in it are equal to 0 then it can be deleted

					// adds the change to the account history
					addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The " + this.savingsaccounts.get(i).getaccounttype() + " " + this.savingsaccounts.get(i).getname() + 
							" has been deleted." ); 
					
				this.savingsaccounts.remove(i); //deletes account from arraylist
					
				start.output("\nThe account deletion was successful\n");
				
					return true ;// ends excecution when complete and leaves function 
				}
				
				
			}
			
			
			
		}
		else if(type.equals("r")) { //delete a retirement account
			
			
			
			
			//deletes a retirement account
		for (int i = 0; i < this.retirementaccounts.size(); i++) {
				
				if (  (this.retirementaccounts.get(i).getname().equals(name))  && (Double.parseDouble((this.retirementaccounts.get(i).getamount())) == 0)  ) { 
					//if the retirement account is located in the arraylist and the funds in it are equal to 0 then it can be deleted

					// adds the change to the account history
					addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The " + this.retirementaccounts.get(i).getaccounttype() + " " + this.retirementaccounts.get(i).getname() + 
							" has been deleted." ); 
					
					
				this.retirementaccounts.remove(i); //deletes account from arraylist
					
				
				start.output("\nThe account deletion was successful\n");
				
					return true ;// ends excecution when complete and leaves function 
				}
				
				
			}
			
			
			
			
			
		}
		else if(type.equals("c")) { //delete a checking account
			
			
			
			
	for (int i = 0; i < this.checkingaccounts.size(); i++) {
				
				if (  (this.checkingaccounts.get(i).getname().equals(name))  && (Double.parseDouble((this.checkingaccounts.get(i).getamount())) == 0)  ) { 
					//if the checking account is located in the arraylist and the funds in it are equal to 0 then it can be deleted

					
					// adds the change to the account history
					addhistory( "\n - "+ time + " (Hour-minute-second-nanosecond): " + "The " + this.checkingaccounts.get(i).getaccounttype() + " " + this.checkingaccounts.get(i).getname() + 
							" has been deleted." ); 
					
					
				this.checkingaccounts.remove(i); //deletes account from arraylist
					
				start.output("\nThe account deletion was successful\n");
				
			
					return true ;// ends excecution when complete and leaves function 
				}
				
				
			}
			
			
			
			
			
			
		}
		else {
			
			start.output("Invalid key. Hacker alert");
			System.exit(0);
		}
		
		
		start.output("Account unable to be deleted. The deleted account must have 0 dollars. Try again, maybe the name was inputted wrong or the account does not exist.");
		return false; //if the process is not completed without errors/exceptions
	}
	
	
	
	
	public boolean createaccount(Profile user, String type) { // type will either be s,r,t, name is account name
		
		

		String name = start.input("Input the name you wish to use to refer to this new account:");
	
		while(!(accountnamechecker(name,type))) {
			
				name = start.input("Your previous input was invalid.\nInput the name you wish to use to refer to this new account.\nMake sure that there is no other account with the same name and type.");
			
		}
		
		
		
		
		if(type.equals("s")) { //create a savings account
			
			if(this.savingsaccounts.size() + 1 > this.maxaccounts) {
				
				start.output("You have too many savings accounts. " + this.maxaccounts + 
						" is the maximum amount of savings accounts for your " + this.profiletype + " profile payment plan.");
				
				
				return false;
			}
			
		  savingsaccount x = new savingsaccount(user, name);
		  
		  this.savingsaccounts.add(x);
		  
			return true;
			
			
		}
		else if(type.equals("r")) { //create a retirement account
			
			if(this.retirementaccounts.size() + 1 > this.maxaccounts) {
				
				start.output("You have too many retirement accounts. " + this.maxaccounts + 
						" is the maximum amount of retirement accounts for your " + this.profiletype + " profile payment plan.");
				
				
				return false;
			}
			
			retirementaccount x = new retirementaccount(user,name);
			
			
			this.retirementaccounts.add(x);
			
			return true;
			
			
		}
		else if(type.equals("c")) { //create a checking account
			
			if(this.checkingaccounts.size() + 1 > this.maxaccounts) {
				
				start.output("You have too many checking accounts. " + this.maxaccounts + 
						" is the maximum amount of checking accounts for your " + this.profiletype + " profile payment plan.");
				
				
				return false;
			}
			
			
			checkingaccount x = new checkingaccount(user, name);
		    
			this.checkingaccounts.add(x);
			
			return true;
			
			
		}
		else {
			
			start.output("Invalid key. Hacker alert");
			System.exit(0);
		}
		
		return false;//if the process is completed without errors/exceptions
	}

	
	
	//returns the amount of bank accounts currently owned by the user
	public int getnumofaccounts() {
		
		return this.savingsaccounts.size() + this.checkingaccounts.size() + this.retirementaccounts.size();  //returns the sum of the length of the arrays storing the types of bank accounts
	}
	
	
	public String getcurrentlyusedaccounts() { // gets currently used accounts
		
		int x = 0;
		
		for(int i = 0; i < this.retirementaccounts.size(); i++) {
			
			if(this.retirementaccounts.get(i).getroth()) {
				
				x++;
				
			}
			
		}
		
		
		
		return "\nCurrently used Accounts:\n" +
				"\n - " + this.checkingaccounts.size() + " checking account(s) are in use." +
				"\n - " + this.savingsaccounts.size() + " savings account(s) are in use." +
				"\n - " + this.retirementaccounts.size() + " retirement account(s) are in use." +
				"\n - " + x + " roth retirement account(s) are in use.";
				
				
		
		
		
		
	}
	
	
	
	//returns the amount of accounts and types of accounts available to the user depending on the payment plan
	public String getavailableaccounts() { //gets available accounts for the user depending on the payment plan
		
		String availableaccounts = "\nAvailable Accounts:";
		if (this.profiletype.equals("Brickbronze")) {
			//available accounts for brickbronze
			//Brickbronze: a 0$ per month subscription where the user can have a maximum of 2 checking accounts, 2 savings accounts, and 2 retirement accounts. [User may not open roth IRAs or roth 401ks]"
			
			availableaccounts +="\n - " + (2 - this.checkingaccounts.size()) + " checking account(s) are available." + 
								"\n - " + (2 - this.savingsaccounts.size()) + " savings account(s) available." +
								"\n - " + (2 - this.retirementaccounts.size()) + " retirement account(s) available." +
								"\n - " + "0" + " roth retirement account(s) are currently available for your BrickBronze payment plan."; 
			
			
			
			
			
			
		}
		else if (this.profiletype.equals("Soulsilver")) {
			//available accounts for soulsilver
			//SoulSilver: a 5$ per month subscription where the user can have a maximum of 4 checking accounts, 4 savings accounts, and 4 retirement accounts.[User may open 1 roth account if they are not above the maximum amount of accounts]"
			
			int x = 0;
			
			for(int i = 0; i < this.retirementaccounts.size(); i++) {
				
				if(this.retirementaccounts.get(i).getroth()) {
					
					x++;
					
				}
				
			}
			availableaccounts +="\n - " + (4 - this.checkingaccounts.size()) + " checking account(s) are available." + 
					"\n - " + (4 - this.savingsaccounts.size()) + " savings account(s) available." +
					"\n - " + (4 - this.retirementaccounts.size()) + " retirement account(s) available." +
					"\n - " + (2 - x) + " roth retirement account(s) are currently available for your Soulsilver payment plan. (" + x + " in use)." ; 
			
			
			
			
		}
		else if (this.profiletype.equals("Heartgold")) {
			//avaliable accounts for heartgold
			// HeartGold: a 20$ per month subscription where the user can have 10 checking accounts, 10 savings accounts , and 10 retirement accounts. [may open both roth IRAs and roth 401ks]"
			
			availableaccounts +="\n - " + (10 - this.checkingaccounts.size()) + " checking account(s) are available." + 
					"\n - " + (10 - this.savingsaccounts.size()) + " savings account(s) available." +
					"\n - " + (10 - this.retirementaccounts.size()) + " retirement account(s) available." +
					"\n - " + (10 - this.retirementaccounts.size()) + " roth retirement account(s) are currently available for your Heartgold payment plan. (" + this.retirementaccounts.size() + " retirement accounts are in use)." ; 
			
			
			
		}
		else {
			start.output("Hacker Alert, invalid payment plan\n\n");
			
			//team plasma theme 
			
			System.exit(0);
			
			
		}
		
		
		
		
		
		return availableaccounts;
		
		
		
		
		
	}
	
	
	
	//account name checker
	public boolean accountnamechecker(String test, String type) { //sends a test name and the type of account.
		
		boolean result = true; 
		
		
			if(type.equals("s")) {
				
				
				for(int i = 0; i < this.savingsaccounts.size(); i++) {
					
					if(this.savingsaccounts.get(i).getname().equals(test)) {
						
						start.output("This is an invalid name, there is already a savings account using it.");
						
						result = false;
					}
					
					
					
				}
				
				
				
				
			}
			else if(type.equals("r")) {
				
				
				for(int i = 0; i < this.retirementaccounts.size(); i++) {
					
					
					if(this.retirementaccounts.get(i).getname().equals(test)) {
						
						start.output("This is an invalid name, there is already a retirement account using it.");
						
						result = false;
					}
					
				}
				
				
	
	
			}
			else if(type.equals("c")) {
				
				
				for(int i = 0; i < this.checkingaccounts.size(); i++) {
					
					if(this.checkingaccounts.get(i).getname().equals(test)) {
						
						start.output("This is an invalid name, there is already a checking account using it.");
						
						result = false;
					}
					
				}
				
				
	
				
			}
			else {
				
				start.output("invalid key, hacker alert.");
			}
			
			
			return result;
	}
	 
	//returns value for maxaccounts
	
	public int getmaxaccounts(){
		return this.maxaccounts;
	}
	
	//returns the savings accounts (accounts stored in the arraylist)
	public  ArrayList<savingsaccount> getsavingsaccounts() {
		
		return this.savingsaccounts;
	}

	
	//returns the checking accounts (accounts stored in the arraylist)
	public  ArrayList<checkingaccount> getcheckingaccounts() {
		
		return this.checkingaccounts;
	}
	
	
	//returns the retirement accounts (accounts stored in the arraylist)
	public  ArrayList<retirementaccount> getretirementaccounts() {
		
		return this.retirementaccounts;
	}
	
	
	
	//toString method
	
	public int calcroth (ArrayList <retirementaccount> x ) {
		
		int numroth = 0;
		for(int u = 0; u < x.size(); u++) {
			
			if(x.get(u).getroth()) {
				
				numroth++;
			}
		}
		
		return numroth;
	}
	
	public String toString() {
		
		//outputs the users account information
		return "\n\n*****Profile Information*****\n\n" + "\nName: " + this.firstname
				+ " " + this.lastname +  "\n\nUsername: "
				+ this.login.keySet() + "\n" + "\nPassword: " 
				+ this.login.values() + "\n\nDate of Birth: " + this.dob + 
				"\n\nSecurity Question: " + this.sq + "\n\nSecurity Question Answer: " + this.sa + "\n\nRace: " + 
				this.race + "\n\nSex: " + this.sex + "\n"+
				 "\nAmount of Bank Accounts created: " + this.getnumofaccounts() + this.getavailableaccounts() +  this.getcurrentlyusedaccounts() + "\n\nProfile Number: " + this.profilenum 
				 + "\n\nSocial Security Number: " + this.ssn 
				 + "\n\nProfile Type: " + this.profiletype + "\n\n" + "History: " + "\n" + this.profilehistory + "\n\n*****End of Profile Information*****\n\n";
		
	}
	
	
	
	
	
	
	
	  
	
	
	//old code 
	/*
	//capital letters
	char[] cletters = {'A','B', 'C', 'D', 'E', 'F', 'G', 'H','I','J',
			'K', 'L', 'M', 'N', 'O' , 'P', 'Q' , 'R', 'S', 
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //array of capital letters 
	*/
	
	/*
	 *char[] letters = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h','i','j',
			'k', 'l', 'm', 'n', 'o' , 'p', 'q' , 'r', 's', 
			't', 'u', 'v', 'w', 'x', 'y', 'z'}; 
	 */
	
	/*boolean c = false;
	for(int i = 0; i < cletters.length; i++) {
		
			if(test.charAt(0) == cletters[i]) {
				
				c = true;
			}
			if( (i == cletters.length - 1)  && ( c == false) ) {
				start.output("The first character of your name must be a capital letter.");
				return false;
			}
	
	}
	
	
	*/
	
}
