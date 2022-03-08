package Banking;
import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

public class Filing {
//This is the code that will save the user's new bank account information in addition to reloading it for the future.
	
	static start start = new start();
	static Profile profile = new Profile();
	
	//Saving , Writes the information into a text file 
	public static boolean saveinfo(Profile user) { //the user's profile containing their info and their account info is needed to complete this process
		boolean success = false;// false if the filing process failed
		
		
		
		
		 //login info is stored in arraylists
	    ArrayList<String> u = new ArrayList<String>(user.getlogin().keySet());
	    ArrayList<String> p = new ArrayList<String>(user.getlogin().values());
		
	    //username and password are obtained by retrieving the first data field within those arraylists
	    String username = u.get(0);
	    String password = p.get(0);
	    
	    
	    
	    
		
		String bc = "~"; //separates the checking account info from the rest of info
		String cd = "~"; // separates savings account info from rest of info
		String de = "~"; //separates retirement account info from the rest
		String as = "\""; //separates account information in code 
	   
	    

		String ab = "~"; // string to separate account information from the rest of the info 
		
		
		
		
        String cc = "\""; // this is the sequence of characters that separates information in filing
		String end = "ENDOFFILE";
		
		
	    //how the user's account info will be stored in code 
	    //
	    String accountfiling = ""; //user's bank account information, stores the amoung of this account type first
	    
	    
	    //stores checking account information in this string first 
	    for(int x = 0; x < user.getcheckingaccounts().size(); x++) {
	    	
	    	accountfiling = accountfiling + user.getcheckingaccounts().get(x).getname() + as 
	         + user.getcheckingaccounts().get(x).getamount() + as +
	    	user.getcheckingaccounts().get(x).getaccounttype() + as + user.getcheckingaccounts().get(x).getaccountinfo() + as + 
	    	user.getcheckingaccounts().get(x).gethistory() + as;
	    	
	    	
	    	
	    }
	    
	    
	    
	    //stores separators
	    
	    //accounttype separator
	    accountfiling += bc;
	    

	    
	    
	    //stores savings account information next
	    
	    
	    for(int x = 0; x < user.getsavingsaccounts().size(); x++) {
	    	
	    	
	    	accountfiling = accountfiling + user.getsavingsaccounts().get(x).getname() + as 
	   	         + user.getsavingsaccounts().get(x).getamount() + as +
	   	    	user.getsavingsaccounts().get(x).getaccounttype() + as + user.getsavingsaccounts().get(x).getaccountinfo() + as + 
	   	    	user.getsavingsaccounts().get(x).gethistory() + as;
	    	
	    	
	    }
	  //accounttype separator
	    accountfiling += cd;
	    
	  //stores amount of savingsaccounts
	   
	    
	    
	    
	    //stores retirement account information last
	    
        for(int x = 0; x < user.getretirementaccounts().size(); x++) {
	    	
	    	
        	accountfiling = accountfiling  + user.getretirementaccounts().get(x).getname() + as 
   	   	         + user.getretirementaccounts().get(x).getamount() + as +
   	   	    	user.getretirementaccounts().get(x).getaccounttype() + as + user.getretirementaccounts().get(x).getaccountinfo() + as + 
   	   	    	user.getretirementaccounts().get(x).gethistory() + as + user.getretirementaccounts().get(x).getroth();
	    	
	    	
	    }
	    
	    
	    
       
	    
	  
	    //contains how the information will be filed in code 
	    //ORDER username, password, date of birth, security question, security question answer, race, gender, maximum accounts, number of accounts, 
	    //profile payment plan,first name, last name , profile number, social  security number, user's profile history, account information 
	    String tobefiled =  username + cc + password + cc + user.getdob() + cc + user.getsq() + cc + user.getsa() + cc
	    	  + user.getrace() + cc + user.getsex() + cc + user.getmaxaccounts() + cc + user.getnumofaccounts() + cc +
	    	  user.getprofiletype() + cc + user.getfirstname() +cc + user.getlastname() + cc + user.getprofilenum() + cc +
	    	 user.getsocialsecuritynumber() + cc + user.gethistory() + cc + user.getcheckingaccounts().size() + cc + user.getsavingsaccounts().size() 
	    	 + cc + user.getretirementaccounts().size() + cc
	    	 
	    	 
	    	 
	    	 
	    	 + ab + accountfiling ; 
		 
	   String filename = user.getprofilenum() + ".txt";
	   
	  
	 
	   
	   // encryption algorithm (shifts ascii value to prevent hackers from deciphering information)
	   
	   //default values
	   
	   char temp2 = 'e';
	  
	   String finaltemp = "";
	   
	   

	   for(int i = 0; i < tobefiled.length(); i++) {
		   
		   
		   temp2 =  (char) (tobefiled.charAt(i) - 30);
		   finaltemp += temp2;
	   }
	   
	   
	   
	   tobefiled = finaltemp;


	   
	 //makes a file, writes into the file, closes the file, try catch present in the case of any exceptions 
	   try {
		   
		   
			   File profilefile = new File(filename); 
				
			   FileWriter writer = new FileWriter(profilefile);
			   
			   
			   writer.write(tobefiled);
			   
			   writer.close();
			    	 
	   }
	   catch(Exception anyexception) {
		   
		   success = false;
		   
		   return success;
		   
	   }
			    	 
	    
	    
	    
	    	
		
		
		success = true;
		
		
		return success; // returns true if filing process was successful, false if not successful
	}
	
	
	
	
	

	
	
	
	//Reloading	
	public static Profile reloadinfo( String name) { // olduser is the template for setting up the profile of the previous user
		//name is the user's profile number input. This is the name of the file where their information is stored (num + ".txt")
		
		//olduser's information will be reloaded and their profile olduser will be re-initialized here
		
	
	   
		
			char x = '~'; //separates the checking account info from the rest of info
			
			
			
	        char y = '"'; // this is the sequence of characters that separates information in filing
			
			String bc = String.valueOf(x);
			String cc = String.valueOf(y);
			
		

		
		//first read the file as a string (store it into string info)
		String info = "";
		
		
		//Reload profile information from file
		
		try {
			File oldinfo = new File(name);
			Scanner reloader = new Scanner(oldinfo);
			
			while(reloader.hasNext()) {
				
				info += reloader.nextLine();
			}
			
			
			
			reloader.close();
		}
		catch (Exception anyexception) {
			
			start.output("An Exception occured, Your file information could not be reloaded, please retry the application again\n");
			
			System.exit(0);
		}
		
		
			//decryption (Decode the data so it can be used 
		
		  
		   
		   char temp1 = '0';
		  
		   String finaltemp = "";
		   
		   

		   for(int i = 0; i < info.length(); i++) {
			   
			  
			   temp1 =  (char) (info.charAt(i) + 30);
		       finaltemp += temp1;
			   
		   }
		   
		   
		   
		   info = finaltemp;


		  //then split it up to get the data fields
		
		
  		//then split the data fields up into variables
  		
  	     
  		
  		//then make them into a profile 
  		
  		//then extend this process for reloading the user's accounts 
  		
  		
  		
  		
  		// Use file stuff to get the user's previous profile info
    
		
	  
					
	    //this will split up the file info based on the '~' symbol, it will separate account info from profile info
	    String[] data = info.split(bc);
	    
	 
	    
	    //this will split up the profile info based on the '"' symbol. 
	    String[] profiledata = data[0].split(cc);
	    
	    
	    //this string will contain all other bank account information
	    String accountdata = "";
	    
	    for (int o = 1; o < data.length; o++) {
	    	
	    	accountdata += data[o];
	    }
	   
	    

	    
	   // all of the values obtained from the new array
	    String username = profiledata[0];
	
	    String password = profiledata[1];
	    
	    String dob = profiledata[2];
	    
	    String sq = profiledata[3];
	    
	    String sa = profiledata[4];
	    
	    String race = profiledata[5];
	    
	    String gender = profiledata[6];
	    
	    String max = profiledata[7];
	    
	
	    
	    
	    String num = profiledata[8];
	    
	
	    
	    String profiletype = profiledata[9];
	    
	    
	    String firstname = profiledata[10];
	    
	    String lastname = profiledata[11];
	    
	    String pnum = profiledata[12];
	    
	    

	    String ssn = profiledata[13];
	    
	    String history = profiledata[14];
	    
	    String numofcheck = profiledata[15];
	    
	    String numofsave = profiledata[16];
	    
	    String numofretire = profiledata[17];
	    		
	    
	    
	    int maxaccounts=0,numofaccounts=0 , profilenum =0,numcheck=0,numsave =0, numretire = 0;
	    
	    
	    
	  //asks user to input their username and password in order to regain access to their account 
		String verify1 = start.input("\nInput your username (You have 3 tries to input it correctly):\n");

		int numofattempts = 1;
		 
		boolean check = true;
		
		
		while(     check  ) {
			if(verify1.equals(username)) {
				
				break;
			}
			
			
			if(numofattempts >= 3) {
				
				start.output("You have misinput the username 3 times, program execution will now stop.\nHacker Alert");
				profile.recordbreach();
				System.exit(0);
				
			}

			
			
			//increases number of attempts by 1
			numofattempts++;
			
			 verify1 = start.input("\nInput your username (attempt " + numofattempts + " of 3):\n");
			 
		
			
			
			
		}
			
		
		
		
		String verify2 = start.input("\nInput your password:\n");
		
		 numofattempts = 1;
		 
		 check = true;
		
	
		while(     check  ) {
			if(verify2.equals(password)) {
				
				break;
			}
			
			
			if(numofattempts >= 3) {
				
				start.output("You have misinput your password 3 times, program execution will now stop.\nHacker Alert");
				profile.recordbreach();
				System.exit(0);
				
			}

			
			
			//increases number of attempts by 1
			numofattempts++;
			
			 verify2 = start.input("\nInput your password (attempt " + numofattempts + " of 3):\n");
			 
		
			
			
			
		}
				
		
		
		

		
		
		
		
		
		
	    //converts maxaccounts and other number variables to numbers
	    try {
	     maxaccounts = Integer.valueOf(max);
	     numofaccounts = Integer.valueOf(num);
	     profilenum = Integer.valueOf(pnum);
	     numcheck = Integer.valueOf(numofcheck);
	     numsave = Integer.valueOf(numofsave);
	     numretire = Integer.valueOf(numofretire);
	    }
	    catch(Exception ex) {
	    	
	    		start.output("This file is corrupted, information is not aligned, please contact tech support immedietly");
	    		System.exit(0);
	    }
	    
	    
	   
	    
	    // 471829473 - test id 
	    
	//creates a profile based on a returning user
	    Profile z = new Profile(username, password, dob, sq, sa, 
	    		race, gender,  firstname, lastname, ssn,
	    		pnum, profiletype, history, numofaccounts, maxaccounts,
	    		numcheck, numsave, numretire, accountdata
	    		); //general profile for a previous user, fields will be filled based on file info
	    
	    
	    /*
	     * (String u, String p, String dob, String sq, String sa, 
			String r, String s,String fn, String ln, String ssn, 
			String pnum, String ptype , String phistory, int numofaccounts, int maxaccounts,
			int numofcheck, int numofsave, int numofretire,
			String allbankaccountinfo)
			
	     */
		  
	    
	    z.ressurectaccountinfo(z); //goes to function where account info will be restored
	    
		return z; // returns true if reloading process was successful, false if not successful
	}
	
	

	
	public static boolean filechecker(String name) {
		
		File olduser = new File(name);
		
		return olduser.exists(); //returns true if the file exists, false if it does not exist
		
		
	}
	
	
	
	
	
}
