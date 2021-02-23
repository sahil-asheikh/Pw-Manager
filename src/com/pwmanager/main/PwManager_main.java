package com.pwmanager.main;
import java.util.Scanner;

import com.pwmanager.dao.PwManager_dao;
import com.pwmanager.modal.PwManager;


public class PwManager_main {

	public static void main(String[] args) {
				
		Scanner scan = new Scanner(System.in);
		PwManager manager = new PwManager();

		System.out.print("Username");
		String uname = scan.next();

		System.out.print("\nPassword");
		String pword = scan.next();
		
		manager.setUser_username(uname);
		manager.setUser_password(pword);
		
		
		if (PwManager_dao.validate(manager)) {
			System.out.println("Login Success");
			for (; ; ) {
				System.out.println("----------------------------------------------------");
				System.out.println("------------------------MENU------------------------");
				System.out.println("1. create new password");
				System.out.println("2. find all sites and passwords");
				System.out.println("3. find a password for a site or app");
				System.out.println("4. logout");
				System.out.println("----------------------------------------------------");

				int user_input = scan.nextInt();

				if (user_input == 1) {
					// create new password by invoking the insert method
					try{

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (user_input == 2) {
					// retrieve the whole table

				} else if (user_input == 3) {
					// take input from user and print the password of perticular site/app

				} else if (user_input == 4) {
					break;				// break the loop
				} else {
					System.out.println("Invalid Input");
				}
			}
			
		} else {
			System.out.println("login failed");
		}

		scan.close();
	}

}
