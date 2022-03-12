package BankingApp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.time.LocalDate;

public class transaction {

	static Scanner userInputInt = new Scanner(System.in);
	static Scanner userInputString = new Scanner(System.in);
	static Scanner userInputDouble = new Scanner(System.in);
	 
	String fromIban;
	String toIban;
	String date;
	
	double amount;
	double balance;
	
	static transaction[] transactions = new transaction[100];
	
	public static void depositFunds() {
		transaction[] transactions1 = new transaction[1];
		double balance = 0; //logged in user's balance
		
		LocalDate date = LocalDate.now();
		String fromIban = "-";
		String toIban = "NL01PFMB0000000001"; //iban of loggedin userId needs to be added!
		System.out.print("Please enter the amount to be deposited (+): ");
		double amount = userInputDouble.nextDouble();
		
		double newBalance = balance + amount;
		
		transactions1[0] = new transaction(date, fromIban, toIban, amount);
		writeFile(transactions1);
		}
	
	public static void withdrawFunds() {
		transaction[] transactions1 = new transaction[1];
		double balance = 0; //logged in user's balance
		
		LocalDate date = LocalDate.now();
		String fromIban = "NL01PFMB0000000001"; //iban of loggedin userId needs to be added!
		String toIban = "-";
		System.out.print("Please enter the amount to be withdrawn: ");
		double amount = userInputDouble.nextDouble();
		
			while (amount > balance) {
				System.out.println("Withdrawal value exceeded your balance!");
				System.out.print("Please enter the amount to be withdrawn: ");
				amount = userInputDouble.nextDouble();
			}
		
			//edit the balance on the loggedin user's account NEED TO ADD CODE TO EDIT USER'S BALANCE
			double newBalance = balance - amount;
		
		transactions1[0] = new transaction(date, fromIban, toIban, amount);
		writeFile(transactions1);
	}
	
	public static void transferFunds() {
		transaction[] transactions1 = new transaction[1];
		double fromBalance = 0;
		double toBalance = 0;
		
		LocalDate date = LocalDate.now();
		String fromIban = "NL01PFMB0000000001"; //iban of loggedin userId needs to be added!
		System.out.print("Please enter the iban of recepient: ");
		String toIban = userInputString.nextLine();
		System.out.print("Please enter the amount to be transferred: ");
		double amount = userInputDouble.nextDouble();
			
			while (amount > fromBalance) {
				System.out.println("Withdrawal value exceeded your balance!");
				System.out.print("Please enter the amount to be transferred: ");
				amount = userInputDouble.nextDouble();
		}
		
			//local variables that represent new balances of both accounts 
			double updatedFromBalance = fromBalance - amount;
			double updatedToBalance = toBalance + amount;
			
		transactions1[0] = new transaction(date, fromIban, toIban, amount);
		writeFile(transactions1);
	}
	
	// Gets an array of transaction objects and appends it to the file 
	 public static void writeFile(transaction[] transactions1){ 
		 try{ 
				PrintWriter myFile = new PrintWriter ( 
						new BufferedWriter ( new FileWriter ("/Users/sairithvik/eclipse-workspace/pfmgroupprj/transactions.txt", true))); 
				myFile.printf("%s,%s,%s,%f %n",transactions1[0].date, transactions1[0].fromIban,
						transactions1[0].toIban, transactions1[0].amount); 


				myFile.close(); 
			} catch(IOException e){ 
				System.out.print("Wrong! (writing)"); 
			} 
		}
	  // Constructors 
		public transaction(LocalDate date, String fromIban, String toIban, double amount) {
		/*	this.date = date;
			this.fromIban = fromIban;
			this.toIban = toIban;
			this.amount = amount; */
		}

	//public transaction(LocalDate date2, String fromIban2, String toIban2, double amount2) {
		// TODO Auto-generated constructor stub
	//}
	 } 
	
