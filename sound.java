package Banking;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


// This section of the program plays music and other sound effects throughout program execution

public class sound {

	static start start = new start();
			
	private String filename = "";
	
	File music;
	
	
	
	public static boolean play = true;
	
	//sounds used by threads
	public static sound introtheme = new sound("intro-credits-theme.wav"); //this is the sound file based on the file name (on the intro theme)
	
	public static sound pokemart = new sound("pokemart-theme.wav"); //this is the sound file based on the file name (on the pokemart theme)
	public static	sound reinitialize = new sound("Profile-reinitialiation-theme.wav"); //this is the sound file based on the file name (on the reinitialization theme)
	public static	sound accountcreated = new sound("account-created-theme.wav"); //this is the sound file based on the file name (on the account creation theme)
	
	public static	sound endtheme = new sound("outro-credits-theme.wav"); //this is the sound file based on the file name (on the outro theme)
	

	public sound() {
		
	}
	
	public sound(String filename) {
		
		
		//makes a cound object
		this.filename = filename;
		this.music = new File(filename);
		this.play = true;
	
		
		
	}
	
	public void play() {
		
		
		
		
		
				try {
					 AudioInputStream player = AudioSystem.getAudioInputStream(this.music);// song 
						
					 Clip clip = AudioSystem.getClip();
						
						clip.open(player); // clip uses audio input stream
						
						clip.start();
						
					
						
				
				}
				catch(Exception x) {
					
					start.output("The sound file is unable to be played, an error likely occured. Please contact support to solve this issue.");
				}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public void playthemes() {
	
		
							
							while(!start.endsongs) {
					
								//plays intro theme 
										try {
								
											
											
											File noise = introtheme.music; // song file as the variable resist in the program
											
											
											if(noise.exists()) {
												
												
												AudioInputStream player = AudioSystem.getAudioInputStream(noise);// song 
												
												Clip clip = AudioSystem.getClip();
												clip.open(player); // clip uses audio input stream
												
												clip.start(); // thread plays song
												clip.loop(clip.LOOP_CONTINUOUSLY); // hopefully plays song until unneeded
												
												while(true) {
													
													if( start.begin != true) {
														
														clip.stop();
														clip.close();
														break;
													}
													
												
												}
												
												
										
											}
											else {
												
												start.output("The music file can't be found!!");
											}
										}	
										catch(Exception whatever) { // Catch any exception
											
											whatever.printStackTrace();// (is like whatever.getmessage())  (both output the error/exception)
											
											start.output("A glitch occures");
										}
										
							
							
							
							
							
							
							
						//plays reinitialize theme 	
									
								try {
							
										
										
										File x = reinitialize.music; // song file as the variable resist in the program
										
										
										if(x.exists()) {
											
											
											AudioInputStream player = AudioSystem.getAudioInputStream(x);// song 
											
											Clip clip = AudioSystem.getClip();
											clip.open(player); // clip uses audio input stream
											
											clip.start(); // thread plays song
											clip.loop(clip.LOOP_CONTINUOUSLY); // hopefully plays song until unneeded
											
											while(true) {
												
												if( start.reinitialize != true) {
													
													clip.stop();
													clip.close();
													break;
												}
												
											
											}
											
											
									
										}
										else {
											
											start.output("The music file can't be found!!");
										}
									}	
									catch(Exception whatever) { // Catch any exception
										
										whatever.printStackTrace();// (is like whatever.getmessage())  (both output the error/exception)
										
										start.output("A glitch occured ");
									}
								
						
						
						
						
						
						//play pokemart theme 
						
						
						
						
									
									
								try {
								
										
										
										File x = pokemart.music; // song file as the variable resist in the program
										
										
										if(x.exists()) {
											
											
											AudioInputStream player = AudioSystem.getAudioInputStream(x);// song 
											
											Clip clip = AudioSystem.getClip();
											clip.open(player); // clip uses audio input stream
											
											clip.start(); // thread plays song
											clip.loop(clip.LOOP_CONTINUOUSLY); // hopefully plays song until unneeded
											
											while(true) {
												
												if( start.mid != true) {
													
													clip.stop();
													clip.close();
													break;
												}
												
											
											}
											
											
									
										}
										else {
											
											start.output("The music file can't be found!!");
										}
									}	
									catch(Exception whatever) { // Catch any exception
										
										whatever.printStackTrace();// (is like whatever.getmessage())  (both output the error/exception)
										
										start.output("A glitch occured ");
									}
									
									
									
						
						//plays account created theme
					
					
								
								
								
								
								try {
								
									
									
									File x = accountcreated.music; // song file as the variable resist in the program
									
									
									if(x.exists()) {
										
										
										AudioInputStream player = AudioSystem.getAudioInputStream(x);// song 
										
										Clip clip = AudioSystem.getClip();
										clip.open(player); // clip uses audio input stream
										
										clip.start(); // thread plays song
										clip.loop(clip.LOOP_CONTINUOUSLY); // hopefully plays song until unneeded
										
										while(true) {
											
											if( start.accountcreated != true) {
												
												clip.stop();
												clip.close();
												break;
											}
											
										
										}
										
										
								
									}
									else {
										
										start.output("The music file can't be found!!");
									}
								}	
								catch(Exception whatever) { // Catch any exception
									
									whatever.printStackTrace();// (is like whatever.getmessage())  (both output the error/exception)
									
									start.output("A glitch occures (Whoever created this is stoopid lol)");
								}
								
								
					
					
					
					
					
								//endtheme
								
								
								
								
								
								try {
					
										
										
										File x = endtheme.music; // song file as the variable resist in the program
										
										
										if(x.exists()) {
											
											
											AudioInputStream player = AudioSystem.getAudioInputStream(x);// song 
											
											Clip clip = AudioSystem.getClip();
											clip.open(player); // clip uses audio input stream
											
											clip.start(); // thread plays song
											clip.loop(clip.LOOP_CONTINUOUSLY); // hopefully plays song until unneeded
											
											while(true) {
												
												if( start.end != true) {
													
													clip.stop();
													clip.close();
													break;
												}
												
											
											}
											
											
									
										}
										else {
											
											start.output("The music file can't be found!!");
										}
									}	
									catch(Exception whatever) { // Catch any exception
										
										whatever.printStackTrace();// (is like whatever.getmessage())  (both output the error/exception)
										
										start.output("A glitch occures (Whoever created this is stoopid lol)");
									}
					
						
						
						
							
						}
						
						
						
						
	
	}
	
}
