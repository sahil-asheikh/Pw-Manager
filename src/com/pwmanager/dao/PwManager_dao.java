package com.pwmanager.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pwmanager.mains.PwManager_main;
import com.pwmanager.modal.PwManager;

public class PwManager_dao {
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

//	method for validate the user
	public static boolean validate(PwManager pwmanager){
		boolean valid = false;
		try {
			Connection connection = PwManager_dao.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from userlogin_001 where username = ? and password = ?");
			ps.setString(1, pwmanager.getUser_username());
			ps.setString(2, pwmanager.getUser_password());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	
//	method to generate max id
	public static int max_id () {
		int id = 0;
		try {
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select max(id) from pwmanager_001");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
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
	
//	method to insert password into an database
	public static int insert(PwManager pwmanager){
		int status = 0;
		try {
			int max_id = PwManager_dao.max_id();
			String enc_pass = PwManager_dao.encPass(pwmanager.getPassword(), pwmanager.getKey());
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into pwmanager_001 values(?,?,?,?,?,?,?)");
			ps.setInt(1, max_id);
			ps.setString(2, enc_pass);
			ps.setInt(3, pwmanager.getKey());
			ps.setString(4, pwmanager.getUsername());
			ps.setString(5, pwmanager.getUser_email());
			ps.setString(6, pwmanager.getApp_name());
			ps.setString(7, pwmanager.getUrl());
			
			status = ps.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}

//	method to retrieve password table from database
	public static List<PwManager> retrievePwTable(){
		List<PwManager> passwords = new ArrayList<PwManager>();
		try {
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from pwmanager_001");
			ResultSet rs = ps.executeQuery();
			String pass = null;
			while (rs.next()) {
				
				pass = PwManager_dao.decrypt(rs.getString(2), rs.getInt(3));
				
				PwManager password = new PwManager();
				password.setApp_name(rs.getString(6));
				password.setUsername(rs.getString(4));
				password.setPassword(pass);
				password.setUser_email(rs.getString(5));
				password.setUrl(rs.getString(7));
				password.setKey(rs.getInt(3));
				password.setId(rs.getInt(1));
				
				passwords.add(password);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return passwords;
	}
	
//	method to delete password
	public static int deletePassword (int delete_key) {
		int i = 0;
		
		try {
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete pwmanager_001 where id = ?");
			ps.setInt(1, delete_key);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
}
