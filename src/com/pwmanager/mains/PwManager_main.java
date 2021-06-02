package com.pwmanager.mains;
import java.util.List;
import java.util.Scanner;

import com.pwmanager.dao.DatabaseTable_DAO;
import com.pwmanager.dao.PwManager_dao;
import com.pwmanager.models.PwManager;


public class PwManager_main {

	public static void main(String[] args) {
				
		Scanner scan = new Scanner(System.in);
		PwManager manager = new PwManager();

		/*
		 * default username = default_username
		 * default password = default_password
		 */
		
//		check for user login database and table
		try {
			
			if ((DatabaseTable_DAO.databaseExists("passwordmanager"))) {
				System.out.println("Database Found");
				System.out.println("Checking for Password Table");
//				check user table
				if ((DatabaseTable_DAO.tableExists("passwordmanager", "users"))) {
					System.out.println("User Table Found");
				} else {
					System.out.println("User Table Not Found");
					System.out.println("Creating User Table");
//					if database exists and table not exists then create table
//					creating user table
					int i = DatabaseTable_DAO.createUserTable("passwordmanager", "users");
					if (i == 0) {
						System.out.println("User Table Created");
						System.out.println("Inserting Default Row");
						
						int j = PwManager_dao.insertUser();
						if (j == 1) {
							System.out.println("Row inserted");
						} else {
							System.out.println("Error while inserting row");
						}
					} else {
						System.out.println("Error While Creating User Table");
					}
				}
				
				System.out.println("Checking for Password Table");
//				check password table
				if ((DatabaseTable_DAO.tableExists("passwordmanager", "passwords"))) {
					System.out.println("Password Table Found");
				} else {
					System.out.println("Password Table Not Found");
					System.out.println("Creating Password Table");
//					if database exists and table not exists then create table
//					creating Password table
					int i = DatabaseTable_DAO.createPasswordTable("passwordmanager", "passwords");
					if (i == 0) {
						System.out.println("Password Table Created");
					} else {
						System.out.println("Error While Creating Password Table");
					}
				}
				
				
			} else if (!DatabaseTable_DAO.databaseExists("passwordmanager")) {
				System.out.println("Database Not Found");
//				if database not exists then table exists hence create database and table
				int i = DatabaseTable_DAO.createDatabase("passwordmanager");
				if (i == 1) {
					System.out.println("Database created");
//					creating user table
					System.out.println("User Table Not Found\nCreating User Table");
					i = DatabaseTable_DAO.createUserTable("passwordmanager", "users");
					if (i == 0) {
						System.out.println("User Table Created");
					} else {
						System.out.println("Error While Creating User Table");
					}
//					creating password table
					System.out.println("User Table Not Found\nCreating User Table");
					i = DatabaseTable_DAO.createPasswordTable("passwordmanager", "passwords");
					if (i == 0) {
						System.out.println("Password Table Created");
					} else {
						System.out.println("Error While Creating Password Table");
					}
				} else {
					System.out.println("Error While Creating Database");
				}
			} else {
				System.out.println("Random Error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n===================================================================\n");
		System.out.println("Username");
		String uname = scan.nextLine();

		System.out.println("Password");
		String pword = scan.nextLine();
		
		manager.setUser_username(uname);
		manager.setUser_password(pword);
		
		boolean valid = PwManager_dao.validate(manager);
		
		if (valid) {
			System.out.println("Login Success");
			while (true) {
				System.out.println();
				System.out.println("----------------MENU----------------");
				System.out.println("1. create new password");
				System.out.println("2. show saved passwords");
				System.out.println("3. delete password");
				System.out.println("4. logout");
				System.out.println("------------------------------------");

				int user_input = scan.nextInt();
				String temp0 = scan.nextLine();

				if (user_input == 1) {
					// create new password by invoking the insert method
					
					try{
						System.out.println("Enter Password");
						String password = scan.nextLine();
						
						System.out.println("Enter Key");
						int key = scan.nextInt();
						String temp1= scan.nextLine();					// to skip this line
						
						System.out.println("Enter Username");
						String username = scan.nextLine();
						
						System.out.println("Enter Email");
						String email = scan.nextLine();
						
						System.out.println("Enter App/Site name");
						String app_name = scan.nextLine();
						
						System.out.println("Enter App/Site url");
						String app_url = scan.nextLine();
						
						manager.setPassword(password);
						manager.setKey(key);
						manager.setUsername(username);
						manager.setUser_email(email);
						manager.setApp_name(app_name);
						manager.setUrl(app_url);
						
						int i = PwManager_dao.insert(manager);
						
						if (i == 1) {
							System.out.println("Password has been saved.");
						} else {
							System.out.println("Failed");
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (user_input == 2) {
					// retrieve the whole table
					try{
						
						List<PwManager> passwords = PwManager_dao.retrievePwTable();

						System.out.println("");
						
						System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------+");		// for padding
						System.out.printf("|%-10s|%-26s|%-20s|%-30s|%-30s|%3s|%10s|\n", "App Name", "Username", "Password", "User Email", "App Url", "Key", "Delete Key");
						System.out.println("+----------+--------------------------+--------------------+------------------------------+------------------------------+---+----------+");		// for padding
						for (PwManager pass: passwords) {							
							System.out.printf("|%-10s|%-26s|%-20s|%-30s|%-30s|%03d|%10d|\n", pass.getApp_name(), pass.getUsername(), pass.getPassword(), pass.getUser_email(), pass.getUrl(), pass.getKey(), pass.getId());
							System.out.println("+----------+--------------------------+--------------------+------------------------------+------------------------------+---+----------+");		// for padding
						}
						System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");		// for padding
						
						System.out.println("All data retrieved");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (user_input == 3) {
					System.out.println("Enter Delete Key");
					int delete_key = scan.nextInt();
					
					try {
						int delete_status = PwManager_dao.deletePassword(delete_key);
						if (delete_status == 1) {
							System.out.println("Password Deleted");
						} else {
							System.out.println("Password didn't deleted");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else if (user_input == 4) {
					break;				// break the loop
				} else {
					System.out.println("Invalid Input");
				}
			}
			System.out.println("--------------Loged Out-------------");
			
		} else {
			System.out.println("login failed");
		}
		
		scan.close();
		
	}

}
