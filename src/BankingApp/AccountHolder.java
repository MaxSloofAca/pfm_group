package BankingApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccountHolder extends User {
	static int Num_account_holders=0;
	double Balance=0;

	public AccountHolder(int UserID, String FirstName, String LastName, String Role, String UserName, String Password) {
		super(UserID, FirstName, LastName, Role, UserName, Password); 

		// TODO Auto-generated constructor stub
	}
	
	// Account holder database 
	public static AccountHolder[] accountHolderIDArray(User[] my_users_local){ 
		String LocalFirstName, LocalLastName, LocalUserName, LocalPassword, LocalRole; 
		int LocalID;
		AccountHolder[] my_account_holders_local = new AccountHolder[NumUser]; 				// The maximum number will be the number of account holders 
		String[] current_line = new String[5];
		int count = 0;
		try{
			BufferedReader my_reader = new BufferedReader(new FileReader("userdata.txt"));  // Declaring the reader object
			String input_line; 																// Variable to read line by line
			while( (input_line=my_reader.readLine()) != null){
				current_line = input_line.split(","); 										// Split the line at the tab
				
				// Current_line is an array:
				// Order in the database: FirstName, LastName, UserName, Password, ID, Role 
				LocalFirstName = current_line[0];
				LocalLastName = current_line[1];
				LocalUserName = current_line[2];
				LocalPassword = current_line[3];
				LocalID = Integer.parseInt(current_line[4]);
				LocalRole = current_line[5];

				for(int i=0; i<User.NumUser;i++){ 
					if(LocalRole.equals("AccountHolder")) {
						my_account_holders_local[count] = new 
						AccountHolder(LocalID, LocalFirstName, LocalLastName, LocalRole, LocalUserName, LocalPassword); 
						count++;
						break;
					}
				}

			}
			my_reader.close();
		}
		catch(IOException e){ 
			System.out.println("can't read file"); 
		}
		return(my_account_holders_local);
	}
}