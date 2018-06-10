package finalProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class emailMessaging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean doNotTerminateProgram = true;
		boolean switchUser = true;
		int n = 1;
		int numberofLines = 5;
		ArrayList <File> receivedMailPRIMARY = new ArrayList <File>();
		ArrayList <File> sentMailPRIMARY = new ArrayList <File>();
		ArrayList<String> composedMessageNamesPRIMARY = new ArrayList <String>();
		ArrayList <File> receivedMailSECONDARY = new ArrayList <File>();
		ArrayList <File> sentMailSECONDARY = new ArrayList <File>();
		ArrayList<String> composedMessageNamesSECONDARY = new ArrayList <String>();


		System.out.println("Welcome to the Email Messaging program.\nAnswers to questions are usually 'yes' or 'no'.\nPlease provide a user ID to log-in with (No Spaces):");
		Scanner keyboard = new Scanner (System.in);
		String activeUser;
		String otherUser;
		String userInput = keyboard.next();
		String primaryUserID = "'" + userInput + "'";
		String recipientUserID = "'@Your#1FanForSure!!!'";
		System.out.println("\n\nThank you. Your user ID is:\n" + primaryUserID + "\n");
		activeUser = primaryUserID;
		otherUser = recipientUserID;
		//-------------------------------------------------------------------------------------------------------------------------------------------------------

		do {
			do {
				if (activeUser.equals(primaryUserID)) {

					switchUser = true;
					System.out.println("\nYou are signed in as: " + primaryUserID + "\n");
					System.out.println("Would you like to view your received messages?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						readReceivedMail(receivedMailPRIMARY, composedMessageNamesSECONDARY);
					}
					System.out.println("Would you like to compose a message?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						n = composeMessage(n, numberofLines, sentMailPRIMARY, composedMessageNamesPRIMARY, receivedMailSECONDARY, activeUser, otherUser);
					}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------
					System.out.println("Would you like to view your sent messages?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						readSentMail(sentMailPRIMARY, composedMessageNamesPRIMARY);
					}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------

					System.out.println("Would you like to log-out now?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						switchUser = false;
						activeUser = recipientUserID;
						otherUser = primaryUserID;
						System.out.println("User: " + primaryUserID + " has been successfully signed out.\n");
					}
				}
			} while(switchUser);

			do {

				if (activeUser.equals(recipientUserID)) {
					switchUser = true;
					System.out.println("\nYou are signed in as: " + recipientUserID + "\n");
					System.out.println("Would you like to view your received messages?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") ||userInput.equals("Yes")) {
						readReceivedMail(receivedMailSECONDARY, composedMessageNamesPRIMARY);
					}
					System.out.println("Would you like to compose a message?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") ||userInput.equals("Yes")) {
						n = composeMessage(n, numberofLines, sentMailSECONDARY, composedMessageNamesSECONDARY, receivedMailPRIMARY, activeUser, otherUser);
					}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------
					System.out.println("Would you like to view your sent messages?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						readSentMail(sentMailSECONDARY, composedMessageNamesSECONDARY);
					}
					//-------------------------------------------------------------------------------------------------------------------------------------------------------

					System.out.println("Would you like to log-out now?");
					userInput = keyboard.next();
					if (userInput.equals("yes") || userInput.equals("YES") || userInput.equals("Yes") || userInput.equals("Y") || userInput.equals("y")) {
						switchUser = false;
						activeUser = primaryUserID;
						otherUser = recipientUserID;
						System.out.println("User: " + recipientUserID + " has been successfully signed out.\n");
					}
				}
			} while(switchUser);


			System.out.println("Are you done using the email service?");
			userInput = keyboard.next();
			if (userInput.equals("yes") || userInput.equals("YES") ||userInput.equals("Yes")) {
				doNotTerminateProgram = false;
				System.out.println("Thanks for using the Email Messaging program.");
			}
			

		} while(doNotTerminateProgram);
	}


	public static int composeMessage(int n, int numberofLines, ArrayList<File> sentMail, ArrayList<String> composedMessageNames, ArrayList <File> receivedMail, String activeUser, String otherUser) {
		boolean redoMessage = true;
		do {
			String subUserInput;

			File myFile = new File ("composedMessage" + n + "_" + activeUser +".txt.");
			Scanner subKeyboard = new Scanner (System.in);

			System.out.println("You are currently emailing user: " + otherUser);
			System.out.println("Sent from user: " + activeUser);

			System.out.println("Please begin writing, you have " + numberofLines + " lines");

			try {
				PrintWriter out = new PrintWriter(myFile, "UTF-8");
				for (int i = 1 ; i <= numberofLines; i++) {
					out.println(subKeyboard.nextLine());

				}
				out.close();
				readFile(myFile);
				System.out.println("\nAre you okay with sending this message?");
				subUserInput = subKeyboard.next();
				if (subUserInput.equals("yes") || subUserInput.equals("YES") ||subUserInput.equals("Yes") || subUserInput.equals("Y") || subUserInput.equals("y")) {
					composedMessageNames.add(myFile.toString());
					sentMail.add(myFile);
					receivedMail.add(myFile);
					System.out.println("Message Sent.\nMessage Saved.");
					n++;

					System.out.println("Would you like to compose another message?");
					subUserInput = subKeyboard.next();
					if (subUserInput.equals("no") || subUserInput.equals("NO") || subUserInput.equals("No") || subUserInput.equals("N") || subUserInput.equals("n")) {
						redoMessage = false;	
					}

				}
				else {
					System.out.println("Okay, The message will be overwritten.\n");

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} while(redoMessage);
		return n;
	}



	public static void readFile (File myFile) {

		try {
			System.out.println("Here are the contents of your file:\n");
			FileReader fileReader = new FileReader(myFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine()) != null)  System.out.println(line);

			bufferedReader.close();

		}  catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + myFile + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file'" + myFile + "'");                  
			// Or we could just do this: 
			// ex.printStackTrace();
		}

	}

	public static void readSentMail (ArrayList<File> sentMail, ArrayList<String> composedMessageNames ) {

		Scanner subKeyboard = new Scanner (System.in);
		boolean didNotReadMessage = true;
		String subUserInput = null;

		do { 
			try {
				System.out.println("Here is a list of messages you have sent during this instance:");

				if (composedMessageNames.size() < 1) {
					System.out.println("You have not sent a message yet.\n");
					didNotReadMessage = false;
				}

				else {
					for (int i = 1; (i-1)<composedMessageNames.size() ; i++) {
						System.out.print("\n" + i + ": ");
						System.out.print(composedMessageNames.get(i-1));
					}
					System.out.println("\nWhich message would you like to view?");
					subUserInput = subKeyboard.next();
					int userIndex = Integer.parseInt(subUserInput.trim());
					readFile(sentMail.get(userIndex-1));

					System.out.println("Would you like to read another message?");
					subUserInput = subKeyboard.next();

					if (subUserInput.equals("yes") || subUserInput.equals("YES") ||subUserInput.equals("Yes") || subUserInput.equals("Y") || subUserInput.equals("y")) {
						didNotReadMessage = true;
					}
					else {
						didNotReadMessage = false;
					}
				}
			}
			catch (Exception e) {
				System.out.println("Your input did not match a message in your Sent Mailbox.\nPlease try one of the numbers listed above.\n");
			}
		} while(didNotReadMessage);
	}

	public static void readReceivedMail(ArrayList <File> receivedMail, ArrayList<String> composedMessageNames) {

		Scanner subKeyboard = new Scanner (System.in);
		boolean didNotReadMessage = true;
		String subUserInput = null;

		do { 
			try {
				System.out.println("Here is a list of messages you have received during this instance:");

				if (composedMessageNames.size() < 1) {
					System.out.println("You have not received a message yet.\n");
					didNotReadMessage = false;
				}

				else {
					for (int i = 1; (i-1)<composedMessageNames.size() ; i++) {
						System.out.print("\n" + i + ": ");
						System.out.print(composedMessageNames.get(i-1));
					}
					System.out.println("\nWhich message would you like to view?");
					subUserInput = subKeyboard.next();
					int userIndex = Integer.parseInt(subUserInput.trim());
					readFile(receivedMail.get(userIndex-1));

					System.out.println("Would you like to read another message?");
					subUserInput = subKeyboard.next();

					if (subUserInput.equals("yes") || subUserInput.equals("YES") ||subUserInput.equals("Yes") || subUserInput.equals("Y") || subUserInput.equals("y")) {
						didNotReadMessage = true;
					}
					else {
						didNotReadMessage = false;
					}
				}
			}
			catch (Exception e) {
				System.out.println("Your input did not match a message in your Received Mailbox.\nPlease try one of the numbers listed above.\n");
			}
		} while(didNotReadMessage);
		
	}

}
