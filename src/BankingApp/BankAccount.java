package BankingApp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class BankAccount {
	String iban;
	int bankID;
	double balance;
	int userID; // User class still needs to be added
	static String currUserID;
	static int numBA = 0; // Keeps track of number of bank accounts

	static BankAccount[] bankAccounts = new BankAccount[100];

	static Scanner userInputInt = new Scanner(System.in);
	static Scanner userInputString = new Scanner(System.in);
	static Scanner userInputDouble = new Scanner(System.in);

	public static void showOverview() {
		// TODO Auto-generated method stub
		System.out.println("***************************************");
		System.out.println("*************** Overview **************");
		System.out.println("***************************************");
		System.out.printf("BankID: %s\n", bankAccounts[0].bankID);
		System.out.printf("BankID: %s\n", bankAccounts[0].iban);
		System.out.printf("Bank balance: %.2f\n", bankAccounts[0].balance);
	}

	// Setter and getter methods
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getBankID() {
		return bankID;
	}

	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	// Creating new account when user wants to sign-up
	public static void writeNewAccount(int newUserID) {
		// TODO Auto-generated method stub
		BankAccount[] bankArray1 = new BankAccount[1];
		String newIban;
		int newBankID;
		double newBalance = 0;

		// Create new IBAN
		DecimalFormat dfIban = new DecimalFormat("0000000000");
		newIban = "NL01PFMB" + dfIban.format(returnIndex() + 1);
		System.out.printf("Your new IBAN: %s\n", newIban); 
		System.out.println("Account creation succesfull");

		// Create new bankID 
		newBankID = returnIndex() + 1;

		bankArray1[0] = new BankAccount(newIban, newBankID, newBalance, Interface.TempNewUserID);
		writeFile(bankArray1);
	}

	// Writing new account to bankaccounts file
	public static void writeFile(BankAccount[] bankArray1) {
		// TODO Auto-generated method stub
		try{ 
			PrintWriter myFile = new PrintWriter ( 
					new BufferedWriter ( new FileWriter ("bankaccounts.txt", true))); 
			myFile.printf("%s,%d,%f,%d",bankArray1[0].iban, bankArray1[0].bankID,
					bankArray1[0].balance, bankArray1[0].userID); 


			myFile.close(); 
		} catch(IOException e){ 
			System.out.print("Wrong! (writing)"); 
		} 
	}

	// Reading from the bankaccounts file
	public static void returnBalance(int loggedInUserID){ 
		BankAccount[] my_ba_local = new BankAccount[100]; // 100 here is an upper bound 
		String localIban, loggedInIban = "";
		int localBankID, localUserID;
		double localBalance, loggedInBalance = 0;
		String[] current_line = new String[3]; 

		try{ 
			BufferedReader myFile = new BufferedReader (new FileReader("bankaccounts.txt")); 
			String input_line; 

			while ((input_line = myFile.readLine()) != null){ 
				current_line = input_line.split(","); 

				localIban = current_line[0];
				localBankID = Integer.parseInt(current_line[1]);
				localBalance = Double.parseDouble(current_line[2]);
				localUserID = Integer.parseInt(current_line[3]);

				my_ba_local[BankAccount.numBA] = new BankAccount(localIban, localBankID, localBalance, localUserID);
				BankAccount.numBA++;
			} 

			for(int i = 0; i < BankAccount.numBA; i++) {
				if(my_ba_local[i].userID == loggedInUserID){
					loggedInBalance = my_ba_local[i].balance;
					loggedInIban = my_ba_local[i].iban;
				}
			}
			myFile.close(); 

		} catch(IOException e){ 
			System.out.print("Wrong! (Reading)"); 
		} 

		System.out.println("\n***************************************");
		System.out.println("*************** Overview **************");
		System.out.println("***************************************");
		System.out.printf("IBAN: %s\n", loggedInIban);
		System.out.printf("Bank balance: €%.2f\n\n", loggedInBalance);
	}
	
	// Returning the index of the latest line, so that I can create a new file
	public static int returnIndex(){ 
		BankAccount[] stTemp = new BankAccount[100]; // 100 here is an upper bound 
		int stIndex = 0; // keeps track of the line number 
		try{ 
			BufferedReader myFile = new BufferedReader (new FileReader("bankaccounts.txt")); 
			String sCurrentLine; 

			while ((sCurrentLine = myFile.readLine()) != null){ 
				stIndex++; 
			} 
			myFile.close(); 

		} catch(IOException e){ 
			System.out.print("Wrong! (Reading)"); 
		} 
		BankAccount[] baArray = new BankAccount[stIndex]; 
		System.arraycopy(stTemp, 0, baArray, 0, stIndex); 
		return stIndex; 

	}


	// Constructors
	public BankAccount(String iban, int bankID, double balance, int userID) {
		this.iban = iban;
		this.bankID = bankID;
		this.balance = balance;
		this.userID = userID;
	}

	public BankAccount() {
		// TODO Auto-generated constructor stub
	} 
} 