package Banking;
import java.util.Scanner; //Imports scanner for user input
import java.util.ArrayList;
import java.util.HashMap;


//Front end part of the application so to speak 


//start.output()/output() is a function that outputs text and will eventually play a sound effect when the user does so

/*Programmer:Nevin Ndonwi
 Started:January 1 2022
 Finished: March 7 2022
 Email: nevinfonndonwi@gmail.com 
 Ndonwi-Bank-Simulator
 A simple bank simulator where a user can create a profile, create bank accounts and store virtual money, 
 //information stored in encrypted text files for later use
 
 * Program starts from here
 * This class contains all user input/ user interaction
 *  
 */

/*
starting class (main class)
All User Input and Output will be done from this class
No break statements 


profiles --> the hub where users can make bank accounts. 
accounts, bank accounts that the user can check on 


 */

//3 input statements labeled i, ii, iii respectively


public class start implements Runnable{ //accesses threads through usage of interfaces
	
	//scanner for handling user input
	static Scanner scan = new Scanner(System.in);
	
	public static volatile boolean begin = true;
	public static volatile boolean mid = false;
	public static volatile boolean reinitialize = false;
	public static volatile boolean accountcreated = false;
	public static volatile boolean end = false;
	
	public static volatile boolean endsongs = false;
	
	public sound abutton = new sound("a-button-soundeffect.wav"); //this is the sound file based on the file name (on the a button theme)
	public sound loggingoff = new sound("Windows-sound-effect.wav"); //this is the sound file based on the file name (on the logging out theme)
	public sound transaction = new sound("money.wav"); //this is the sound file based on the file name (on the transaction sound effect theme)
	
	
	

	
	
	//obtains general string input  
	public String input(String prompt) { //
		String input = "";
		
		
	
		while(  ( (input.equals("")) &&  !(prompt.equals(""))  ) || input.contains("\"") || input.contains("!") || input.contains("~"))    { //input can be nothing if the prompt is nothing
		
		
		//output prompt
		output("\n" + prompt + "\n");
		
		
		output("(The input must contain at least one character and cannot contain exclamation marks (!), this symbol (~), or this symbol (\")");
		abutton.play();

		input = scan.nextLine(); //i
		
		abutton.play();
		//play sound effect 
				
		
		}
		
		
		
		
		if(prompt.equals("")) {
			
			scan.nextLine();
			abutton.play();
		}
		
		
		
		
		//return input
		return input;
		
	}
	
	

	
	public void output(String output) {
		
		//output prompt
		System.out.println(output); 
		abutton.play();
		
		//play a sound effect
		
	}

	//This is a function that will check y/n responses (the user must have y or n as the first character in their input in order to continue)
	//This will check strings for y/n characters 
	public boolean responsechecker(String test) {
		
		
		return  ((test.charAt(0) != 'y') && (test.charAt(0) != 'n') ) ;// (checks if the user must have y or n as the first character in their input in order to continue)

	}
	
	
	
	
	
	//checks strings for number only characters 
	public boolean numberchecker(String test) {
		char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		int right = 0; 
		
		for(int i = 0; i < test.length(); i++) {
			
			
			for(int j= 0; j < nums.length; j++) {
				
				
				if(test.charAt(i) == nums[j]) {
					right++; //counts the amount of numerical characters in the string
				}
			}
			
			
		
		}
		
		return (right == test.length()); //checks if the amount of numerical characters is equal to the size of the string
		
		
		
	}
	
	
	
	//checks for decimal number characters in a number
	public boolean decimalnumberchecker(String test) {
		char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
		int right = 0; 
		
		for(int i = 0; i < test.length(); i++) {
			
			
			for(int j= 0; j < nums.length; j++) {
				
				
				if(test.charAt(i) == nums[j]) {
					right++; //counts the amount of numerical characters in the string
				}
			}
			
			
		
		}
		
		return (right == test.length()); //checks if the amount of numerical characters is equal to the size of the string
		
		
		
	}
	
	
	
	//  checks strings for alphabet only characters 
		public boolean letterchecker(String test) {
			char[] letters = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h','i','j',
					'k', 'l', 'm', 'n', 'o' , 'p', 'q' , 'r', 's', 
					't', 'u', 'v', 'w', 'x', 'y', 'z'};
			
			int right = 0; 
			
			for(int i = 0; i < test.length(); i++) {
				
				
				for(int j= 0; j < letters.length; j++) {
					
					
					if(test.charAt(i) == letters[j]) {
						right++; //counts the amount of letter- characters in the string
					}
				}
				
				
			
			}
			
			return (right == test.length()); //checks if the amount of letter characters is equal to the size of the string
			
			
			
		}
	
	
	
		
		
		
		

	public static void main(String[] args) { //helps me prevent the mix of static and non static elements 
	
		//play themes
		
		//starts playing themes 
		Runnable start = new start();
		Thread b = new Thread(start);
		b.start();
		
		start x = new start();
		
		
		x.lead(); //leading funtion 
		
		
	}

	
	public void run() {
		
		
		 sound sound = new sound();
		 sound.playthemes();
		
	}

	

	
	
	
	
	public void lead (){ //main function
		
	

	 
		
		//starts banking code 
		output("Welcome to the Ndonwi Bank Simulator.\n - Created by Nevin Ndonwi (email: nevinfonndonwi@gmail.com)!!\n"
				+ "In this Banking simulator, one can create a profile and then make savings, checking, and retirement bank accounts.\n"
				+ "In addition to this one can save their data whenever needed and reload it later.\n"
				+ "Here at Ndonwi-Banking Applications, we strive for top-notch security:\n"
				+ "-  Your data will be encrypted in a file on your computer whenever you with to reload your data at another location.\n"
				+ "This is similar to how our competitors will store your data in the cloud or in their servers.\n"
				+ "There will also be background music in this program so please turn on your sound.\n\n");
		
		
	
	
		
		 
		String input = input("Do you have a profile with us? (Input y for yes and n for no)\n"); //obtains the input from a programmed input function 
		
		
	
				
		
		
		
		while( responsechecker(input) ) {
			
			
			input = input("Your previous response was invalid. Please try again.\nDo you have a profile with us? (Input y for yes and n for no):\n"); //obtains the input
		}
		
		
		
			
		
		
		Profile user = new Profile(); // A general user profile 
		
		
		if( (input.substring(0,1).toLowerCase().equals("y")) ) {
			
			output("Great. Let's help you access to your profile!\n");

			String tryagain = ""; 
			
			String oldnum = input("Input your profile number: ");
			
			while( (!Filing.filechecker(oldnum + ".txt")) ) { //while loop, either the username will work or the user will give up and not try again
				
				tryagain = input("That profile number is invalid. Would you like to try inputting it again? (input n for no):\n");
				
				if(tryagain.equals("n")) {
					break;
				}
				
				oldnum = input("Re-Input your profile number: ");
				
				
			}
			
			
			if(tryagain.equals("n")) {
				
						output("Ndonwi-Bank is sorry for the inconvenience, Please make our administrators aware that you could not "
								+ "re-access your profile.");
						
						System.exit(0);
			}
			
			
			
			
			
			// Use file stuff to get the user's previous profile info
			//general profile for a previous user, fields will be filled based on file info
			//filing process takes place in Filing class
			Profile olduser = Filing.reloadinfo(  (oldnum + ".txt")   ); 
			
		
		
			user = olduser; //binds the properties of the user and olduser. (user now has olduser's properties based on reloaded information) 
	
			
			
			
			
			
			
		}
		else if((input.substring(0,1).toLowerCase().equals("n"))) {
			
			output("\nOkay, Let's help you create a profile!\n");
			
			//general profile for an old user
			Profile newuser = new Profile("new");// default constructor (Creates a new profile based on a new user with default fields)
			
			//make a way to modify the user's profile information (dob,sq,sq,race,sex,profile number, social security number, type of profile )
			
			
			
			//outputs the template for the user's account
			
	
			
			output("Here is a template of your account\n\n" + newuser.toString() + "\n");
			
			
			  
			output("Some fields need to be modified before your account is complete:\n");
			
			
			
			//gets/updates date of birth
			
			
			
			String date = input("Input your(or the profile owner's) date of birth (dd/mm/yyyy): "); //obtains date of birth input
			
			while(newuser.modifydob(date) == false) {
				
				date = input("Your previous input was invalid, please re-input the date of birth in the following format (dd/mm/yyyy): \n"); //obtains date of birth input
				
			}
			
			output("\nYour date of birth has been updated to: " + newuser.getdob() +  "\n");
		
			
			
			//modify the security question
			
			
			newuser.modifysq();
			
			output("\nYour security question has been updated to: " + newuser.getsq() + "\n");
		
	
			
			//modify security question answer
			
			
			newuser.modifysa();
			
			output("\nYour security question has been updated to: " + newuser.getsa() + "\n");
		
			
			//modify biological sex of the user 
			
			newuser.modifysex();
			
			output("\nYour biological sex has been updated to: " + newuser.getsex() + "\n");
			
			
			
			
			//modify the race of the user
			newuser.modifyrace();
			
			output("\nYour biological race has been updated to: " + newuser.getrace() + "\n");
			
			
			
			// modify profile type,  
			  
			
			newuser.modifyprofiletype();
			
			output("\nYour profile type has been updated to: " + newuser.getprofiletype());
			
			
			
			// modify social security number.
			
			
			


			
			String social = input("Input your or the profile owner's social security number (000-00-0000): "); //obtains social security number input
			
			while(newuser.modifysocialsecuritynumber(social) == false) {
				
				social = input("Your previous input was invalid, please re-input the social security number in the following format (000-00-0000): \n");;//obtains social security number input
				
			}
			
			
			output("\nYour social security number has been updated to: " + newuser.getsocialsecuritynumber() + "\n");
			
			
			
			// generate profile number
			
			
			newuser.modifyprofilenum();
			
			output("\nYour profile number has been generated to be: " + newuser.getprofilenum() + "\n");
			
			
			
			
			
			
			//output
		
			output("Here is your current account information:\n\n");
			
			//outputs user's profile information
			output(newuser.toString());
			
			//makes the user pause what they are doing and press enter to continue
			output("Press enter to continue");
			input("");
			
			
			
			
			user = newuser; //binds the properties of the user and newuser. (user now has newuser's properties) 
			
	
			
			
			
		}
		else { //If the user is able to bypass while loop
			output("Instructions unclear. Hacker Alert!!! Ending the Program.");
			
	
			
			System.exit(0);//Ends Program
		}
		
		//changes the songs that are played
		begin = false;
		sound.introtheme.play= false;
		 reinitialize = true;
		 sound.reinitialize.play= true;
		
		
		//the user's profile (newuser/olduser) can now be reffered to as user
		
	
		//output main menu where the user can actually do bank stuff 
		//make it run using a while loop so the program doesn't constantly close
		   
		boolean useagain = true; 
		
		
		String customername = user.getfirstname() + " " + user.getlastname(); //name of customer
		
		output("YOUR PROFILE IS NOW REGISTERED FOR THIS SESSION!!!!!");
		
		output("\n\nPress enter to continue");
		
		input("");
		
		
		
		while(useagain) { // no break statements (cause security issues)
			
			

			
			mid = true;
			 reinitialize = false;
			 
			 endsongs = false;
			//Shows what the user can do
			String[] options = {
					"\n* Modify Profile Fields (Input 0)\n",
					"* Conduct Bank Account tasks [Create, Delete, Edit Bank Accounts, and more] (Input 1)\n",
					"* Save Profile Information and Conclude your session (Input 2)\n"};
			 
			
			
			//Gets the user's input (0, 1, or 2) 
			output("\n Now that your account status has been confirmed " + customername
			+ " here are the things that you can do (Input 0, 1, or 2):");
			
			for(int i = 0; i < options.length; i++) {
				
				output(options[i]);
				
			}
			
			
			
			String in = input("Input 0, 1, or 2 based on what you would like to do " + customername);
			
				while( !(  (in.equals("0"))   ||  (in.equals("1"))      || (in.equals("2"))      ) ) {
						//will not leave while loop until 0,1,2 is entered
						
						output("\nYour Previous Input Was Invalid (Input 0, 1, or 2)\n");
						
						//output the options
						for(int i = 0; i < options.length; i++) {
							
							output(options[i]);
							
						}
						
						
						
						 in = input("Input 0, 1, or 2 based on what you would like to do " + customername);
						
						
						
					}
					
					
					
					//an if statement takes place based on what the user chooses 
					if(  (in.equals("0"))   ) {
								 
						ArrayList<String> password = new ArrayList<String>(user.getlogin().values()); //arraylist of the old password
						
						ArrayList<String> username = new ArrayList<String>(user.getlogin().keySet()); //arraylist of the old username

						
								//asks user to input their username and password 
								String verify1 = input("\nInput your username (You have 3 tries to input it correctly):\n");
						
								int numofattempts = 1;
								 
								boolean check = true;
								
								
								while(     check  ) {
									if(verify1.equals(username.get(0))) {
										
										break;
									}
									
									
									if(numofattempts >= 3) {
										
										output("You have misinput your username 3 times, program execution will now stop.\nHacker Alert");
										user.recordbreach();
										System.exit(0);
										
									}
	
									
									
									//increases number of attempts by 1
									numofattempts++;
									
									 verify1 = input("\nInput your username (attempt " + numofattempts + " of 3):\n");
									 
								
									
									
									
								}
									
								
								
								
								String verify2 = input("\nInput your password:\n");
								
								 numofattempts = 1;
								 
								 check = true;
								
							
								while(     check  ) {
									if(verify2.equals(password.get(0))) {
										
										break;
									}
									
									
									if(numofattempts >= 3) {
										
										output("You have misinput your password 3 times, program execution will now stop.\nHacker Alert");
										user.recordbreach();
										System.exit(0);
										
									}
	
									
									
									//increases number of attempts by 1
									numofattempts++;
									
									 verify2 = input("\nInput your password (attempt " + numofattempts + " of 3):\n");
									 
								
									
									
									
								}
										
								
								
								
					
								
								
								
								
								
								
								 
						
						
								//code for modifying profile fields (Accessed after inputting one's username and password correctly)
								
								
								
								
								//possible profile fields to modify
								
								String[] proffields = {
									  "\n* Back to main menu (Input 0)\n",
										"* Modify your Security Question (Input 1)\n",
										"* Modify your Security Question Answer (Input 2)\n",
										"* Modify Race (Input 3)\n",
										"* Modify Profile Payment Plan (Input 4)\n",
										"* Modify your Social Security Number (Input 5)\n",
										"* Modify your Biological Gender (Input 6)\n",
										"* Modify Date of Birth (Input 7)\n",
										"* Modify Username (Input 8)\n",
										"* Modify Password (Input 9)\n"
								};
								
								//output the options
								for(int i = 0; i < proffields.length; i++) {
									
									output(proffields[i]);
									
								}
								
								
								String modin = input("Which Profile field would you like to modify?");
								
					
								
								while( !(  (modin.equals("0"))   ||  (modin.equals("1"))      
										|| (modin.equals("2")) || (modin.equals("3"))   ||  (modin.equals("4"))  
										|| (modin.equals("5")) || (modin.equals("6"))   ||  (modin.equals("7")) 
										|| (modin.equals("8"))   ||  (modin.equals("9")) 
									     ) ) {
									
									//will not leave while loop until a number (0-9) is entered
									
									output("\nYour Previous Input Was Invalid\n");
									
									//output the options
									for(int i = 0; i < proffields.length; i++) {
										
										output(proffields[i]);
										
									}
									
									
									 in = input("Input 0, 1, 2, 3, 4, 5, 6, 7, 8, or 9 based on which profile field you wish to modify " + customername);
									
									
									
								}
								
								
								if(modin.equals("0")) {
									   	//Back to main Menu
		
									useagain = true; 
									
								}
								else if(modin.equals("1")) {
										//Modify Security Question
									
									
									
	
									//asks user to input their answer to their current security question
								   verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() );
							
									 numofattempts = 1;
									 
									 check = true;
									
									
									while(     check  ) {
										if(verify1.equals(user.getsa())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Security Question Answer 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() + "\n(attempt " + numofattempts + " of 3)" );
										 
									
										
										
										
									}
									
									
									
									//asks the user to input their social security number
									
									String verify3 = input("\nInput your Social Security Number:\n");
									
									 numofattempts = 1;
									 
									 check = true;
									
								
									while(     check  ) {
										if(verify3.equals(user.getsocialsecuritynumber())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Social Security Number 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify3 = input("\nInput your Social Security Number (attempt " + numofattempts + " of 3):\n");
										 
									
										
										
										
									}
									
									
									
										//modifies security question	
									user.modifysq();
									
									output("Your Security Question has been updated.\n");
									
									
									
									
								}
								else if(modin.equals("2")) {
									
									
									//asks user to input their current security question answer 
									
									//asks user to input their answer to their current security question
								   verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() );
							
									 numofattempts = 1;
									 
									 check = true;
									
									
									while(     check  ) {
										if(verify1.equals(user.getsa())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Security Question Answer 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() + "\n(attempt " + numofattempts + " of 3)");
										 
									
										
										
										
									}
											
									


									
									
									
									
									//asks the user to input their social security number
									
									String verify3 = input("\nInput your Social Security Number:\n");
									
									 numofattempts = 1;
									 
									 check = true;
									
								
									while(     check  ) {
										if(verify3.equals(user.getsocialsecuritynumber())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Social Security Number 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify3 = input("\nInput your Social Security Number (attempt " + numofattempts + " of 3):\n");
										 
									
										
										
										
									}
									
									
									
									
										//Modify Security Question Answer
									
									user.modifysa();
									
									output("Your Security Question Answer has been updated.\n");
									
									
									
								}
								else if(modin.equals("3")) {
										//Modify Race
									
									user.modifyrace();
									
									output("Your Race has been updated.\n");
									
								}
								else if(modin.equals("4")) {
										//Modify Profile Type
		
									user.modifyprofiletype();
									
									output("Your Profile Type has been updated.\n");
									
									
								}
								else if(modin.equals("5")) {
										//Modify Social Security Number
	
							
									

									//asks user to input their answer to their current security question before modifying datafield
								   verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() );
							
									 numofattempts = 1;
									 
									 check = true;
									
									
									while(     check  ) {
										if(verify1.equals(user.getsa())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Security Question Answer 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() + "\n(attempt " + numofattempts + " of 3)" );
										 
									
										
										
										
									}
											
									
									
									
									
									//asks the user to input their social security number
									
									String verify3 = input("\nInput your current Social Security Number:\n");
									
									 numofattempts = 1;
									 
									 check = true;
									
								
									while(     check  ) {
										if(verify3.equals(user.getsocialsecuritynumber())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Social Security Number 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify3 = input("\nInput your current Social Security Number (attempt " + numofattempts + " of 3):\n");
										 
									
										
										
										
									}
									
									
									//modifies social security number
									
									
									while(  !(user.modifysocialsecuritynumber(input("Input your new preffered Social Security Number (in the format 000-00-0000): ")))   ) {
										
										
										output("Your Previous Input was invalid. Please try again.");
										
									}
									
									
									output("Your Social Security Number has been updated.\n");
									
									
									
									
									
								}
								else if(modin.equals("6")) {
										//Modify Biological Gender
		
									user.modifysex();
									
									output("Your Biological Gender has been updated.\n");
								
				
								}
								else if(modin.equals("7")) {
										//Modify Date of Birth
		
									
									
									
									while(  !(user.modifydob(input("Input your new preffered Date of Birth (in the format of (00/00/0000) (month/day/year)):")))   ) {
										
										
										output("Your Previous Input was invalid. Please try again.");
										
									}
									
									
									output("Your Date of Birth has been updated.\n");
									
									
									
				
								}
								else if(modin.equals("8")) {
									//Modify username (modifying the hashmap)
									
									
									
									
									//asks the user to input their social security number
									
									String verify3 = input("\nInput your Social Security Number:\n");
									
									 numofattempts = 1;
									 
									 check = true;
									
								
									while(     check  ) {
										if(verify3.equals(user.getsocialsecuritynumber())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Social Security Number 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify3 = input("\nInput your Social Security Number (attempt " + numofattempts + " of 3):\n");
										 
									
										
										
										
									}
									
									
									
									

									//asks user to input their answer to their current security question before modifying datafield
								   verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() );
							
									 numofattempts = 1;
									 
									 check = true;
									
									
									while(     check  ) {
										if(verify1.equals(user.getsa())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Security Question Answer 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() + "\n(attempt " + numofattempts + " of 3)" );
										 
									
										
										
										
									}
											
									
									
									
									try {
										
											
			
											HashMap<String,String> temp = new HashMap<String,String>(); //new hashmap of login
											
											temp.put(user.loginchecker(0), password.get(0)); //makes a temporary hashmap that gets a new username and uses the old password
											
											user.modifylogin(temp);
											
											output("Your Username has been updated.");
											
											
									}
									catch(Exception ex) { //handles all exceptions
										
										
											output("Username unable to be changed. Sorry for the inconvenience.");
									}
				
								
									
									
									
							}
								else if(modin.equals("9")) {
									
									
									//asks the user to input their social security number
									
									String verify3 = input("\nInput your Social Security Number:\n");
									
									 numofattempts = 1;
									 
									 check = true;
									
								
									while(     check  ) {
										if(verify3.equals(user.getsocialsecuritynumber())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Social Security Number 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify3 = input("\nInput your Social Security Number (attempt " + numofattempts + " of 3):\n");
										 
									
										
										
										
									}

									//asks user to input their answer to their current security question
								   verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() );
							
									 numofattempts = 1;
									 
									 check = true;
									
									
									while(     check  ) {
										if(verify1.equals(user.getsa())) {
											
											break;
										}
										
										
										if(numofattempts >= 3) {
											
											output("You have misinput your Security Question Answer 3 times, program execution will now stop.\nHacker Alert");
											user.recordbreach();
											System.exit(0);
											
										}
		
										
										
										//increases number of attempts by 1
										numofattempts++;
										
										 verify1 = input("\nAnswer your current Security Question: \n" + user.getsq() + "\n(attempt " + numofattempts + " of 3)");
										 
									
										
										
										
									}
											
									
									
									//Modify password
	

									try {
									
											HashMap<String,String> temp = new HashMap<String,String>(); //new hashmap of login
											
											temp.put(username.get(0), user.loginchecker(1)); //makes a temporary hashmap that gets a new username and uses the old password
											
											user.modifylogin(temp); // goes to function, modifies login, binds properties of the newlogin with old login
											
											output("Your Password has been updated.\n");
											
									}
									catch(Exception ex) { //handles all exceptions
										
										
											output("Password unable to be changed. Sorry for the inconvenience.");
									}
				
									
								
							
			
							}
								else {
									
									
									output("Hacker Alert");
									System.exit(0);
								}
								
								
								
										
								
								
								useagain = true; // continues while loop and goes back to menu when finished 
								
								
						
						
					}
					else if  ((in.equals("1"))  ) {			
								//code for modifying bank account fields
											
						
								output("What would you like to do concerning bank accounts within this profile " + user.getfirstname() + " " + user.getlastname() + "? ");
								
								
								String[] accounttasks = {
										" - [Input 0] Create a bank account.", 
										" - [Input 1] Delete a bank account.", 
										" - [Input 2] Display bank account information.",
										" - [Input 3] Deposit money into a bank account.",
										" - [Input 4] Withdraw money from a bank account.",
										" - [Input 5] Transfer funds between accounts.",
										" - [Input 6] Check bank account history.",
										" - [Input 7] Check available bank accounts.",
										" - [Input 8] Check currently used bank accounts.",
										" - [Input 9] Check the current number of used bank accounts.",
										
										
								};
								
								
								//outputs the options
								output("Here are your options:\n");
								
								for(int t = 0; t < accounttasks.length; t++) {
									
									output(accounttasks[t]);
									
								}
								
								
								//accepts input
								String task = input("\nInput a number (0-9) based on what you would like to do " + user.getfirstname() + " " + user.getlastname());
						
								while(   !( numberchecker(task) &&( ((Integer.valueOf(task)) >= 0) && ((Integer.valueOf(task)) < accounttasks.length)  )  ) ) {
									
									//outputs the options
									
									output("Here are your options:\n");
									
									for(int t = 0; t < accounttasks.length; t++) {
										
										output(accounttasks[t]);
										
									}
									
									 task = input("\nYour previous input was invalid. Try again!\nInput a number (0-9) based on what you would like to do " + user.getfirstname() + " " + user.getlastname());
									
									 
								}
								
								
								
								
								output("***WARNING: INPUT MONEY IN THE FOLLOWING FORMAT (examples: 0.00, 200.22, 55555555555.00) (make sure that there are numbers two places past the decimal point)***");
								
								
								
									if(task.equals("0")) { //create a bank account
											
														String type = input("What type of bank account would you like to create?\n[You can create a Savings (input 's'), Checking (input 'c'), or retirement (input 'r')]");
														
														
																
			
														while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
														
															
															 type = input("Your previous input was invalid. Please try again!\nWhat type of bank account would you like to create?\n[You can create a Savings (input 's'), Checking (input 'c'), or retirement (input 'r')]");
															
															 	
															 
														}
														
														
														//creates an account from a function in the profile class that has access to the account class
														user.createaccount(user,type);
														
														
												
														
														
									}
									 else if(task.equals("1")) { // delete a bank account
														
														
														
														
														String type = input("What type of bank account would you like to delete?\n"
																+ "If you would like to delete a:"
																+ "\n - Savings Account (input 's') "
																+ "\n - Checking Account (input 'c')"
																+ "\n - Retirement Account (input 'r')");
														
														
														
			
														while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
														
															
															 type = input("Your previous input was invalid. Please try again!\n"
															 		+ "If you would like to delete a:"
																	+ "\n - Savings Account (input 's') "
																	+ "\n - Checking Account (input 'c')"
																	+ "\n - Retirement Account (input 'r')");
															
															 	
															 
														}
														
														
														    String n = input("Input the name of the account you want to delete:");
														
														//creates an account from a function in the profile class that has access to the account class
														user.deleteaccount(type, n);
														
														
														
														
														
														
														
										}
										else if(task.equals("2")) { // display bank account information
											
	
														
														String type = input("What is the type of bank account you wish to display?\n" 
																+ "If you would like to display a:"
																+ "\n - Savings Account (input 's') "
																+ "\n - Checking Account (input 'c')"
																+ "\n - Retirement Account (input 'r')");
														
														
			
														while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
														
															 type = input("Your previous input was invalid.\nWhat is the type of bank account you wish to display?\n" 
																	+ "If you would like to display a:"
																	+ "\n - Savings Account (input 's') "
																	+ "\n - Checking Account (input 'c')"
																	+ "\n -Retirement Account (input 'r')");
															
															 	
															 
														}
														
														
														if(type.equals("c")) {
																	
															
																	if(user.getcheckingaccounts().size() == 0) {
																		
																		
																		
																		output("You have no checking accounts to look at!");
																	}
																	else {
																	
																		output("Here are your checking account(s): ");
																		
																		//outputs accounts
																			for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																				
																					output(user.getcheckingaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			
																		
																			
																			String d = input("Input the number next to the account you wish to look at: ");
																			
																			
																			while(  !(    (numberchecker(d))  &&   (( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getcheckingaccounts().size() ) )   ) ) {
																				
																				//outputs accounts
																				for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																					
																						output(user.getcheckingaccounts().get(x).getname() + " " + x);
																					
																				}
																				
																				
																				 d = input("Your previous input was invalid.\nInput the number next to the account you wish to look at: ");
																				
																			}
																			
																			
																			
																			output("Here is the Account information you seek:\n");
																			output(user.getcheckingaccounts().get(Integer.parseInt(d)).getaccountinfo());
																		
																	
																	}
															
															
														}
														else if(type.equals("r")) {
																
																	if(user.getretirementaccounts().size() == 0) {
																		
																		output("You have no retirement accounts to look at!");
																	}
																	else{
																		
																		output("Here are your retirement account(s): ");
																		
																		for(int x = 0; x < user.getretirementaccounts().size(); x++) {
			
																			output(user.getretirementaccounts().get(x).getname() + " " + x);
																			
																		}
																		
																		
																		
																		
																		
																		String d = input("Input the number next to the account you wish to look at: ");
																		
																		
																		while(  !( (numberchecker(d))  && (  ( (Integer.parseInt(d)) >=0   && ( (Integer.parseInt(d)) < user.getretirementaccounts().size() ) ) ) ) ) {
																			
																			for(int x = 0; x < user.getretirementaccounts().size(); x++) {
			
																				output(user.getretirementaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			 d = input("Your previous input was invalid.\nInput the number next to the account you wish to look at: ");
																			
																		}
																		
																		
																		
																		output("Here is the Account information you seek:\n");
																		output(user.getretirementaccounts().get(Integer.parseInt(d)).getaccountinfo());
																	
																		
																		
																		
																	}
																	
															
															
															
														}
														else if(type.equals("s")) {
															
															
																  if(user.getsavingsaccounts().size() == 0) {
																		
																	  output("You have no savings accounts to look at!");
																		
																	}
																  else {
																	  
																	  output("Here are your savings account(s): ");
																	  
																	  
																	    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
																			
																	    	output(user.getsavingsaccounts().get(x).getname() + " " + x);
																	    	
																	    	
																		}
																	    
																	    
			
																		String d = input("Input the number next to the account you wish to look at: ");
																		
																		
																		while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getsavingsaccounts().size() ) ) ) ) {
																			
																			for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
			
																				output(user.getsavingsaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			 d = input("Your previous input was invalid.\nInput the number next to the account you wish to look at: ");
																			
																		}
																		
																		
																		
																		output("Here is the Account information you seek:\n");
																		output(user.getsavingsaccounts().get(Integer.parseInt(d)).getaccountinfo());
																	
																		
																		
																	  
																	  
																  }
																  
														  
														  
			
			
														}
														else {
															//incase of hacker
															
															output("Invalid, Hacker alert");
															System.exit(0);
														}
														
											
											
											
											
											
										}
										else if(task.equals("3")) { //deposit money into a bank account
											
											
													
													
													
													
													
													
													String type = input("What is the type of bank account you wish to deposit money into?\n" 
															+ "If it is a:"
															+ "\n - Savings Account (input 's') "
															+ "\n - Checking Account (input 'c')"
															+ "\n - Retirement Account (input 'r')");
													
													
		
													while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
													
														 type = input("Your previous input was invalid.\nWhat is the type of bank account you wish to deposit money into?\n" 
																+ "If it is a:"
																+ "\n - Savings Account (input 's') "
																+ "\n - Checking Account (input 'c')"
																+ "\n - Retirement Account (input 'r')");
														
														 	
														 
													}
													
													
													if(type.equals("c")) {
																
														
																if(user.getcheckingaccounts().size() == 0) {
																	
																	
																	
																	output("You have no checking accounts to deposit money into!");
																}
																else {
																
																	output("Here are your checking account(s): ");
																	
																	//outputs accounts
																		for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																			
																				output(user.getcheckingaccounts().get(x).getname() + " " + x);
																			
																		}
																		
																		
																	
																		
																		String d = input("Input the number next to the account you wish to deposit money into: ");
																		
																		
																		while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getcheckingaccounts().size() ) ) ) ){
																			
																			//outputs accounts
																			for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																				
																					output(user.getcheckingaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			
																			 d = input("Your previous input was invalid.\nInput the number next to the account you wish to deposit money into: ");
																			
																		}
																		
																		
																		
																		String fund = input("Input how much money you would like to deposit to this account:\n");
																		
																		while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)   ) {
																			
																			
																			fund = input("Your previous input was invalid.\nInput how much money you would like to deposit in this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
																		}
																		
																		
																		
																		double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
																		
																		user.getcheckingaccounts().get(Integer.parseInt(d)).deposit(dollars);
																	
																
																}
														
														
													}
													else if(type.equals("r")) {
															
																if(user.getretirementaccounts().size() == 0) {
																	
																	output("You have no retirement accounts to deposit money into!");
																}
																else{
																	
																	output("Here are your retirement account(s): ");
																	
																	for(int x = 0; x < user.getretirementaccounts().size(); x++) {
		
																		output(user.getretirementaccounts().get(x).getname() + " " + x);
																		
																	}
																	
																	
																	
																	
																	
																	String d = input("Input the number next to the account you wish to deposit money into: ");
																	
																	
																	while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getretirementaccounts().size() ) ) ) ) {
																		
																		for(int x = 0; x < user.getretirementaccounts().size(); x++) {
		
																			output(user.getretirementaccounts().get(x).getname() + " " + x);
																			
																		}
																		
																		 d = input("Your previous input was invalid.\nInput the number next to the account you wish to deposit money into (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.): ");
																		
																	}
																	
																	
																	
																	String fund = input("Input how much money you would like to deposit to this account:\n");
																	
																	while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)  ) {
																		
																		
																		fund = input("Your previous input was invalid.\nInput how much money you would like to deposit in this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
																	}
																	
																	
																	
																	double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
																	
																	user.getretirementaccounts().get(Integer.parseInt(d)).deposit(dollars);
																
																	
																	
																	
																}
																
														
														
														
													}
													else if(type.equals("s")) {
														
														
															  if(user.getsavingsaccounts().size() == 0) {
																	
																  output("You have no savings accounts to deposit money into!");
																	
																}
															  else {
																  
																  output("Here are your savings account(s): ");
																  
																  
																    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
																		
																    	output(user.getsavingsaccounts().get(x).getname() + " " + x);
																    	
																    	
																	}
																    
																    
		
																	String d = input("Input the number next to the account you wish to deposit money into: ");
																	
																	
																	while( !(  ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getsavingsaccounts().size() ) ) ) ) {
																		
																		for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
		
																			output(user.getsavingsaccounts().get(x).getname() + " " + x);
																			
																		}
																		
																		 d = input("Your previous input was invalid.\nInput the number next to the account you wish to deposit money into: ");
																		
																	}
																	
																	
																	
																
																
																
																	
																	String fund = input("Input how much money you would like to deposit to this account:\n");
																	
																	while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)  ) {
																		
																		
																		fund = input("Your previous input was invalid.\nInput how much money you would like to deposit in this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
																	}
																	
																	
																	
																	double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
																	
																	user.getsavingsaccounts().get(Integer.parseInt(d)).deposit(dollars);
																	
																	
																  
																  
															  }
															  
													  
													  
		
		
													}
													else {
														//incase of hacker
														
														output("Invalid, Hacker alert");
														System.exit(0);
													}
													
										
										
													
											
											
											
											
											
										
											
											
											
											
											
										}
										else if(task.equals("4")) { //withdraw money from a bank account
											
											
											
											
											
											
											
											String type = input("What is the type of bank account you wish to withdraw money from?\n" 
													+ "If it is a:"
													+ "\n - Savings Account (input 's') "
													+ "\n - Checking Account (input 'c')"
													+ "\n - Retirement Account (input 'r')");
											
											

											while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
											
												 type = input("Your previous input was invalid.\nWhat is the type of bank account you wish to withdraw money from?\n" 
														+ "If it is a:"
														+ "\n - Savings Account (input 's') "
														+ "\n - Checking Account (input 'c')"
														+ "\n - Retirement Account (input 'r')");
												
												 	
												 
											}
											
											
											if(type.equals("c")) {
														
												
														if(user.getcheckingaccounts().size() == 0) {
															
															
															
															output("You have no checking accounts to withdraw money from!");
														}
														else {
														
															output("Here are your checking account(s): ");
															
															//outputs accounts
																for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																	
																		output(user.getcheckingaccounts().get(x).getname() + " " + x);
																	
																}
																
																
															
																
																String d = input("Input the number next to the account you wish to withdraw money from: ");
																
																
																while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getcheckingaccounts().size() ) ) ) ) {
																	
																	//outputs accounts
																	for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																		
																			output(user.getcheckingaccounts().get(x).getname() + " " + x);
																		
																	}
																	
																	
																	 d = input("Your previous input was invalid.\nInput the number next to the account you wish to withdraw money from: ");
																	
																}
																
																
																
																String fund = input("Input how much money you would like to withdraw from this account:\n");
																
																while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)   ) {
																	
																	
																	fund = input("Your previous input was invalid.\nInput how much money you would like to withdraw from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
																}
																
																
																
																double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
																
																user.getcheckingaccounts().get(Integer.parseInt(d)).withdrawal(dollars);
															
														
														}
												
												
											}
											else if(type.equals("r")) {
													
														if(user.getretirementaccounts().size() == 0) {
															
															output("You have no retirement accounts to withdraw money from!");
														}
														else{
															
															output("Here are your retirement account(s): ");
															
															for(int x = 0; x < user.getretirementaccounts().size(); x++) {

																output(user.getretirementaccounts().get(x).getname() + " " + x);
																
															}
															
															
															
															
															
															String d = input("Input the number next to the account you wish to withdraw money from: ");
															
															
															while( !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getretirementaccounts().size() ) ) ) ) {
																
																for(int x = 0; x < user.getretirementaccounts().size(); x++) {

																	output(user.getretirementaccounts().get(x).getname() + " " + x);
																	
																}
																
																 d = input("Your previous input was invalid.\nInput the number next to the account you wish to withdraw money from: ");
																
															}
															
															
															
															String fund = input("Input how much money you would like to withdraw from this account:\n");
															
															while (  !(user.money.matcher(fund).matches() &&  Double.parseDouble(fund) > 0)  ) {
																
																
																fund = input("Your previous input was invalid.\nInput how much money you would like to withdraw from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
															}
															
															
															
															double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
															
															user.getretirementaccounts().get(Integer.parseInt(d)).withdrawal(dollars);
														
															
															
															
														}
														
												
												
												
											}
											else if(type.equals("s")) {
												
												
													  if(user.getsavingsaccounts().size() == 0) {
															
														  output("You have no savings accounts to withdraw money from!");
															
														}
													  else {
														  
														  output("Here are your savings account(s): ");
														  
														  
														    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
																
														    	output(user.getsavingsaccounts().get(x).getname() + " " + x);
														    	
														    	
															}
														    
														    

															String d = input("Input the number next to the account you wish to withdraw money from: ");
															
															
															while( !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getsavingsaccounts().size() ) ) ) ) {
																
																for(int x = 0; x < user.getsavingsaccounts().size(); x++) {

																	output(user.getsavingsaccounts().get(x).getname() + " " + x);
																	
																}
																
																 d = input("Your previous input was invalid.\nInput the number next to the account you wish to withdraw money from: ");
																
															}
															
															
															
														
														
														
															
															String fund = input("Input how much money you would like to withdraw from this account:\n");
															
															while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)   ) {
																
																
																fund = input("Your previous input was invalid.\nInput how much money you would like to withdraw from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
															}
															
															
															
															double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
															
															user.getsavingsaccounts().get(Integer.parseInt(d)).withdrawal(dollars);
															
															
														  
														  
													  }
													  
											  
											  


											}
											else {
												//incase of hacker
												
												output("Invalid, Hacker alert");
												System.exit(0);
											}
											
											
											
											
											
											
											
											
											
										}
										else if(task.equals("5")) { //transfer money between accounts
											
											
											
											//get account A
											
											boolean transfered = false; //stores if the money transfer has taken place successfully or not
											
											
											
											String type = input("What is the type of bank account you wish to transfer money from?\n" 
													+ "If it is a:"
													+ "\n - Savings Account (input 's') "
													+ "\n - Checking Account (input 'c')"
													+ "\n - Retirement Account (input 'r')");
											
											

											while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
											
												 type = input("Your previous input was invalid.\nWhat is the type of bank account you wish to transfer money from?\n" 
														+ "If it is a:"
														+ "\n - Savings Account (input 's') "
														+ "\n - Checking Account (input 'c')"
														+ "\n - Retirement Account (input 'r')");
												
												 	
												 
											}
											
											
											if(type.equals("c")) {
														
												
														if(user.getcheckingaccounts().size() == 0) {
															
															
															
															output("You have no checking accounts to transfer money from!");
														}
														else {
														
															output("Here are your checking account(s): ");
															
															//outputs accounts
																for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																	
																		output(user.getcheckingaccounts().get(x).getname() + " " + x);
																	
																}
																
																
															
																
																String d = input("Input the number next to the account you wish to transfer money from: ");
																
																
																while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getcheckingaccounts().size() ) ) ) ) {
																	
																	//outputs accounts
																	for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																		
																			output(user.getcheckingaccounts().get(x).getname() + " " + x);
																		
																	}
																	
																	
																	 d = input("Your previous input was invalid.\nInput the number next to the account you wish to transfer money from: ");
																	
																}
																
																
																
																String fund = input("Input how much money you would like to transfer from this account:\n");
																
																while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)   ) {
																	
																	
																	fund = input("Your previous input was invalid.\nInput how much money you would like to transfer from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
																}
																
																
																
																double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
																
																
															
																
																
																
																
																//getting account b
																
																
																
																
																String ty = input("What type of bank account would you like to transfer money to?\n"
																		+ "If it is a:"
																		+ "\n - Savings Account (input 's') "
																		+ "\n - Checking Account (input 'c')"
																		+ "\n - Retirement Account (input 'r')");
																
																
																
					
																while(   !(  ty.equals("c") || ty.equals("s") || ty.equals("r")   )  ) {
																
																	
																	 ty = input("Your previous input was invalid. Please try again!\n"
																	 		+ "If you would like to send money to a:"
																			+ "\n - Savings Account (input 's') "
																			+ "\n - Checking Account (input 'c')"
																			+ "\n - Retirement Account (input 'r')");
																	
																	 	
																	 
																}
																
																
																    String n = input("Input the name of the account you want to transfer money to:");
																
																    
																    if(ty.equals("c")) {
																    	
																    	for(int o = 0; o < user.getcheckingaccounts().size(); o++) {
																    		
																    		if((n.equals(user.getcheckingaccounts().get(o).getname()))   &&  !(n.equals(user.getcheckingaccounts().get(Integer.parseInt(d)).getname()))){
																    			
																    			user.getcheckingaccounts().get(Integer.parseInt(d)).transfer(user, user.getcheckingaccounts().get(Integer.parseInt(d)), user.getcheckingaccounts().get(o), dollars);
																    		
																    			transfered = true;
																    		
																    		}
																    		
																    	}
																    	
																    	
																    	
																    }
																    else if(ty.equals("s")) {
																    	
																    	
																    	 for(int o = 0; o < user.getsavingsaccounts().size(); o++) {
																	    		
																    		 if((n.equals(user.getsavingsaccounts().get(o).getname()))  ){
																	    			
																    				//transfer money
																    			 
																    			 user.getcheckingaccounts().get(Integer.parseInt(d)).transfer(user, user.getcheckingaccounts().get(Integer.parseInt(d)), user.getsavingsaccounts().get(o), dollars);
																					
																    			 
																    			 transfered = true;
																	    		}
																	    	}
																    	
																    	
																    	
																    	
																    }
																    else if(ty.equals("r")) {
																    	
																    	for(int o = 0; o < user.getretirementaccounts().size(); o++) {
																    		
																    		 if((n.equals(user.getretirementaccounts().get(o).getname())) ){
																	    			
																	    			
																    				//transfer money
																    			 
																    			 
																    			 user.getcheckingaccounts().get(Integer.parseInt(d)).transfer(user, user.getcheckingaccounts().get(Integer.parseInt(d)), user.getretirementaccounts().get(o), dollars);
																					
																    			 transfered = true;
																    			 
																	    	 }
																    	}
																    	
																    	
																    	
																    	
																    	
																    }else {
																    	output("Hacker Alert!\n");
																    	System.exit(0);
																    	
																    
																    }
																
																
																
																
																
																
																
															
														
														}
												
												
											}
											else if(type.equals("r")) {
													
														if(user.getretirementaccounts().size() == 0) {
															
															output("You have no retirement accounts to transfer money from!");
														}
														else{
															
															output("Here are your retirement account(s): ");
															
															for(int x = 0; x < user.getretirementaccounts().size(); x++) {

																output(user.getretirementaccounts().get(x).getname() + " " + x);
																
															}
															
															
															
															
															
															String d = input("Input the number next to the account you wish to transfer money from: ");
															
															
															while( !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getretirementaccounts().size() ) ) ) ){
																
																for(int x = 0; x < user.getretirementaccounts().size(); x++) {

																	output(user.getretirementaccounts().get(x).getname() + " " + x);
																	
																}
																
																 d = input("Your previous input was invalid.\nInput the number next to the account you wish to transfer money from: ");
																
															}
															
															
															
															String fund = input("Input how much money you would like to transfer from this account:\n");
															
															while (  !(user.money.matcher(fund).matches() && Double.parseDouble(fund) > 0)  ) {
																
																
																fund = input("Your previous input was invalid.\nInput how much money you would like to transfer from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
															}
															
															
															
															double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
															
														
															
															
															
															
															String ty = input("What type of bank account would you like to transfer money to?\n"
																	+ "If it is a:"
																	+ "\n - Savings Account (input 's') "
																	+ "\n - Checking Account (input 'c')"
																	+ "\n - Retirement Account (input 'r')");
															
															
															
				
															while(   !(  ty.equals("c") || ty.equals("s") || ty.equals("r")   )  ) {
															
																
																 ty = input("Your previous input was invalid. Please try again!\n"
																 		+ "If you would like to send money to a:"
																		+ "\n - Savings Account (input 's') "
																		+ "\n - Checking Account (input 'c')"
																		+ "\n - Retirement Account (input 'r')");
																
																 	
																 
															}
															
															
															    String n = input("Input the name of the account you want to transfer money to:");
															
															    
															    if(ty.equals("c")) {
															    	
															    	for(int o = 0; o < user.getcheckingaccounts().size(); o++) {
															    		
															    		if((n.equals(user.getcheckingaccounts().get(o).getname()))  ){
															    			
															    			user.getretirementaccounts().get(Integer.valueOf(d)).transfer(user, user.getretirementaccounts().get(Integer.valueOf(d)), user.getcheckingaccounts().get(o), dollars);
															    		
															    			transfered = true;
															    		
															    		}
															    		
															    	}
															    	
															    	
															    	
															    }
															    else if(ty.equals("s")) {
															    	
															    	
															    	 for(int o = 0; o < user.getsavingsaccounts().size(); o++) {
																    		
															    		 if((n.equals(user.getsavingsaccounts().get(o).getname()))   ){
																    			
															    				//transfer money
															    			 
															    			 user.getretirementaccounts().get(Integer.valueOf(d)).transfer(user, user.getretirementaccounts().get(Integer.valueOf(d)), user.getsavingsaccounts().get(o), dollars);
																				
															    			 
															    			 transfered = true;
																    		}
																    	}
															    	
															    	
															    	
															    	
															    }
															    else if(ty.equals("r")) {
															    	
															    	for(int o = 0; o < user.getretirementaccounts().size(); o++) {
															    		
															    		 if((n.equals(user.getretirementaccounts().get(o).getname()))   &&  !(n.equals(user.getretirementaccounts().get(Integer.valueOf(d)).getname()))){
																    			
																    			
															    				//transfer money
															    			 
															    			 
															    			 user.getretirementaccounts().get(Integer.valueOf(d)).transfer(user, user.getretirementaccounts().get(Integer.valueOf(d)), user.getretirementaccounts().get(o), dollars);
																				
															    			 transfered = true;
															    			 
																    	 }
															    	}
															    	
															    	
															    	
															    	
															    	
															    }else {
															    	output("Hacker Alert!\n");
															    	System.exit(0);
															    	
															    
															    }
															
															
															
														}
														
												
												
												
											}
											else if(type.equals("s")) {
												
												
													  if(user.getsavingsaccounts().size() == 0) {
															
														  output("You have no savings accounts to transfer money from!");
															
														}
													  else {
														  
														  output("Here are your savings account(s): ");
														  
														  
														    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
																
														    	output(user.getsavingsaccounts().get(x).getname() + " " + x);
														    	
														    	
															}
														    
														    

															String d = input("Input the number next to the account you wish to transfer money from: ");
															
															
															while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getsavingsaccounts().size() ) ) ) ) {
																
																for(int x = 0; x < user.getsavingsaccounts().size(); x++) {

																	output(user.getsavingsaccounts().get(x).getname() + " " + x);
																	
																}
																
																 d = input("Your previous input was invalid.\nInput the number next to the account you wish to transfer money from: ");
																
															}
															
															
															
														
														
														
															
															String fund = input("Input how much money you would like to transfer from this account:\n");
															
															while (  !(user.money.matcher(fund).matches() &&  Double.parseDouble(fund) > 0)  ) {
																
																
																fund = input("Your previous input was invalid.\nInput how much money you would like to transfer from this account (must be greater than 0) with 2 decimal points after (For Example:  1.00, 22222.00 or 5.55.):\n");
															}
															
															
															
															double dollars = Double.parseDouble(fund); // gives funds the double value within the fund string variable 
															
															
															
															
															
														  
														  
															
															

															
															
															String ty = input("What type of bank account would you like to transfer money to?\n"
																	+ "If it is a:"
																	+ "\n - Savings Account (input 's') "
																	+ "\n - Checking Account (input 'c')"
																	+ "\n - Retirement Account (input 'r')");
															
															
															
				
															while(   !(  ty.equals("c") || ty.equals("s") || ty.equals("r")   )  ) {
															
																
																 ty = input("Your previous input was invalid. Please try again!\n"
																 		+ "If you would like to send money to a:"
																		+ "\n - Savings Account (input 's') "
																		+ "\n - Checking Account (input 'c')"
																		+ "\n - Retirement Account (input 'r')");
																
																 	
																 
															}
															
															
															    String n = input("Input the name of the account you want to transfer money to:");
															
															    
															    if(ty.equals("c")) {
															    	
															    	for(int o = 0; o < user.getcheckingaccounts().size(); o++) {
															    		
															    		if((n.equals(user.getcheckingaccounts().get(o).getname()))  ){
															    			
															    			user.getsavingsaccounts().get(Integer.parseInt(d)).transfer(user, user.getsavingsaccounts().get(Integer.parseInt(d)), user.getcheckingaccounts().get(o), dollars);
															    		
															    			transfered = true;
															    		
															    		}
															    		
															    	}
															    	
															    	
															    	
															    }
															    else if(ty.equals("s")) {
															    	
															    	
															    	 for(int o = 0; o < user.getsavingsaccounts().size(); o++) {
																    		
															    		 if((n.equals(user.getsavingsaccounts().get(o).getname()))  && !(user.getsavingsaccounts().get(Integer.parseInt(d)).getname().equals(n))   ){
																    			
															    				//transfer money
															    			 
															    			 user.getsavingsaccounts().get(Integer.parseInt(d)).transfer(user, user.getsavingsaccounts().get(Integer.parseInt(d)), user.getsavingsaccounts().get(o), dollars);
																				
															    			 
															    			 transfered = true;
																    		}
																    	}
															    	
															    	
															    	
															    	
															    }
															    else if(ty.equals("r")) {
															    	
															    	for(int o = 0; o < user.getretirementaccounts().size(); o++) {
															    		
															    		 if((n.equals(user.getretirementaccounts().get(o).getname()))  ){
																    			
																    			
															    				//transfer money
															    			 
															    			 
															    			 user.getsavingsaccounts().get(Integer.parseInt(d)).transfer(user, user.getsavingsaccounts().get(Integer.parseInt(d)), user.getretirementaccounts().get(o), dollars);
																				
															    			 transfered = true;
															    			 
																    	 }
															    	}
															    	
															    	
															    	
															    	
															    	
															    }
															    else {
															    	output("Hacker Alert!\n");
															    	System.exit(0);
															    	
															    
															    }
													  
														  
														  }
														  
												  
												  
	
			
														}
														else {
															//incase of hacker
															
															output("Invalid, Hacker alert");
															System.exit(0);
														}
														
											
											
											
													if( !(transfered) ) {
														
												
														output("Transfer not successful, An account could have been mispelled or there was not enough money to transfer, the account names and types could have overlapped, or something went wrong with the server");
													}
														
														
											
											
											
										}
										else if(task.equals("6")) { //check bank account history
											
											
											
														
														
			
														
														String type = input("What is the type of the bank account whose history you wish to display?\n" 
																+ "If it is a:"
																+ "\n - Savings Account (input 's') "
																+ "\n - Checking Account (input 'c')"
																+ "\n - Retirement Account (input 'r')");
														
														
			
														while(   !(  type.equals("c") || type.equals("s") || type.equals("r")   )  ) {
														
															 type = input("Your previous input was invalid.\nWhat is the type of the bank account whose history you wish to display?\n" 
																	+ "If it is a:"
																	+ "\n - Savings Account (input 's') "
																	+ "\n - Checking Account (input 'c')"
																	+ "\n - Retirement Account (input 'r')");
															
															 	
															 
														}
														
														
														if(type.equals("c")) {
																	
															
																	if(user.getcheckingaccounts().size() == 0) {
																		
																		
																		
																		output("You have no checking accounts to look at!");
																	}
																	else {
																	
																		output("Here are your checking account(s): ");
																		
																		//outputs accounts
																			for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																				
																					output(user.getcheckingaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			
																		
																			
																			String d = input("Input the number next to the account whose history you wish to look at: ");
																			
																			
																			while( !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getcheckingaccounts().size() )  ) ) ) {
																				
																				//outputs accounts
																				for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
																					
																						output(user.getcheckingaccounts().get(x).getname() + " " + x);
																					
																				}
																				
																				
																				 d = input("Your previous input was invalid.\nInput the number next to the account whose history you wish to look at: ");
																				
																			}
																			
																			
																			
																			output("Here is the Account history you seek:\n");
																			output(user.getcheckingaccounts().get(Integer.parseInt(d)).gethistory());
																		
																	
																	}
															
															
														}
														else if(type.equals("r")) {
																
																	if(user.getretirementaccounts().size() == 0) {
																		
																		output("You have no retirement accounts to look at!");
																	}
																	else{
																		
																		output("Here are your retirement account(s): ");
																		
																		for(int x = 0; x < user.getretirementaccounts().size(); x++) {
			
																			output(user.getretirementaccounts().get(x).getname() + " " + x);
																			
																		}
																		
																		
																		
																		
																		
																		String d = input("Input the number next to the account whose history you wish to look at: ");
																		
																		
																		while( !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getretirementaccounts().size() ) ) ) ) {
																			
																			for(int x = 0; x < user.getretirementaccounts().size(); x++) {
			
																				output(user.getretirementaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			 d = input("Your previous input was invalid.\nInput the number next to the account whose history you wish to look at: ");
																			
																		}
																		
																		
																		
																		output("Here is the Account history you seek:\n");
																		output(user.getretirementaccounts().get(Integer.parseInt(d)).gethistory());
																	
																		
																		
																		
																	}
																	
															
															
															
														}
														else if(type.equals("s")) {
															
															
																  if(user.getsavingsaccounts().size() == 0) {
																		
																	  output("You have no savings accounts to look at!");
																		
																	}
																  else {
																	  
																	  output("Here are your savings account(s): ");
																	  
																	  
																	    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
																			
																	    	output(user.getsavingsaccounts().get(x).getname() + " " + x);
																	    	
																	    	
																		}
																	    
																	    
			
																		String d = input("Input the number next to the account whose history you wish to look at: ");
																		
																		
																		while(  !( ( (numberchecker(d)) ) && (  ( (Integer.parseInt(d)) >=0 )  && ( (Integer.parseInt(d)) < user.getsavingsaccounts().size() ) ) ) ) {
																			
																			for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
			
																				output(user.getsavingsaccounts().get(x).getname() + " " + x);
																				
																			}
																			
																			 d = input("Your previous input was invalid.\nInput the number next to the account whose history you wish to look at: ");
																			
																		}
																		
																		
																		
																		output("Here is the Account history you seek:\n");
																		output(user.getsavingsaccounts().get(Integer.parseInt(d)).gethistory());
																	
																		
																		
																	  
																	  
																  }
																  
														  
														  
			
			
														}
														else {
															//incase of hacker
															
															output("Invalid, Hacker alert");
															System.exit(0);
														}
														
														
														
											
										}
										else if (task.equals("7")) {
											
											
											
											
											//display available accounts
											
											output("\n\nHere are available accounts for you " + user.getfirstname() + " " + user.getlastname());
											
											output(user.getavailableaccounts());
											
											
											
										}
										else if(task.equals("8")) {
											
											//display currently used accounts
											
											output("\n\nHere are your currently used accounts " + user.getfirstname() + " " + user.getlastname());
											
											output(user.getcurrentlyusedaccounts());
											
											
										}
										else if(task.equals("9")) {
											
											//display number of accounts 
											output("\n\nHere is your current number of accounts " + user.getfirstname() + " " + user.getlastname());
											
										    output("You have " + user.getnumofaccounts() + " accounts.");
											
											
											
										}
										
								
								
								
								
								
								
								
								useagain = true; // continues while loop and goes back to menu when finished 
							
								

						
			
					}
					else if  (in.equals("2"))   { //filing data and ending session
							
						
								String input1 = input("Are you sure you wish to save your data and end your session? (input y for yes and n for no) \n");
								
							//checks for yes/no input
								while( responsechecker(input1) ) {
									
								
										input1 = input("Your previous response was invalid. Please try again.\nAre you sure you wish to save your data and end your session? (input y for yes and n for no)\n"); //obtains the input //iii
								}
								
								
								
								
								if(input1.substring(0,1).toLowerCase().equals("y")) { // if the user wants to save and exit the application 
						
									

									
									mid = false;
								
									 end = true;
									
								
									
									//outputs user's current information
										output("Here is your current profile information: \n\n"
										+ user.toString());
										
										
										
										
										
										//makes the user pause what they are doing and press enter to continue
										output("Press enter to continue");
										input("");
										
										
										
										
										
										
										//gives user their profile number (which is their save key/filename)
										output("Remember your profile number. It is " + user.getprofilenum());
										
										output("\nYou will need it to re-access your information\n");
										
										
										
										
										//makes the user pause what they are doing and press enter to continue
										output("Press enter to continue");
										input("");
										
										
										//files info in filing class
										if(Filing.saveinfo(user)) { // returns true if successful, false if a failure occured.
											
											//filename is going to be profilenum.txt
											
											//code for outputting filing and saving your data message 
											output("Your profile and account information has been filed!");
											output("\nYour data has been saved in a file, remember to input the file key when you wish to reaccess your account again!\n");
										
											//filename is going to be profilenum.txt
											
											useagain = false; //ends whileloop and program when executed
										
										}
										else {
											
											output("Something went wrong with the filing process, an unpredictable error occured, sorry for the inconvenience,"
													+ "\n - try contacting administrators/tech support about this issue at 'nevinfonndonwi@gmail.com'.");
											
											useagain = true; // loop will keep running so the user's data is not lost 
											
										}
								
										
										
									
									
								}
							    //if the user does not want to save and exit the application 
								//the loop will just continue running.
									
					
								
						
						
					}
					else {
						
						output("Hacker Alert!!!");
						
						//play team plasma encounter theme
						
						System.exit(0);
						
						
					} // end if else 
					
			
			
			
			
			
			
			
			
		} //end of main while loop (useagain)
		
		
		
		
		
		
		 end = false;
			
		 endsongs = true;
		
		 loggingoff.play();
		


		//End of program
		
		output("Thank you for using Ndonwi-Banking Systems. Have a Beautiful Day!\n\n");
		
		
		//makes the user pause what they are doing and press enter to continue
		output("Press enter to end the program");
		
		
		 
		input("");
		
		
	
		
		
	
		
		
	}
	
	

	
	
	
	
	
	
}
