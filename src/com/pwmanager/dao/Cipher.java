package com.pwmanager.dao;

public class Cipher {
	
//	method to encrypt the password
	public static String encPass(String msg, int key) {
		String secret_message = "";
		int a;
		char[] ch = msg.toCharArray();					//		conversion to char array
		
		for (int i = 0; i < msg.length(); i++) {
			if (ch[i] != 32) {
				a = ch[i] - key;							//		shifting char value key times
			} else {
				a = ch[i];
			}
			secret_message += (char) a;					//		cast int to char and store into string
		}
		return secret_message;
	}
	
//	method to decrypt the password
	public static String decrypt(String secret_message, int key) {
		String msg = "";
		int a;
		char[] ch = secret_message.toCharArray();					//		conversion to char array
		
		for (int i = 0; i < secret_message.length(); i++) {
			if (ch[i] != 32) {
				a = ch[i] + key;							//		shifting char value key times
			} else {
				a = ch[i];
			}
			msg += (char) a;
		}
		return msg;
	}
	
}
