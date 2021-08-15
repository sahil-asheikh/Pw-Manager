package com.pwmanager.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pwmanager.models.PwManager;

public class PwManager_dao {
	
	public static Connection getConnection(){
		Connection connection = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/passwordManager", "root", "admin");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

//	method for validate the user
	public static int validate(PwManager pwmanager){
		int valid = 0;
		try {
			Connection connection = PwManager_dao.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from users where username = ? and password = ?");
			ps.setString(1, pwmanager.getUser_username());
			ps.setString(2, pwmanager.getUser_password());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				valid = rs.getInt(1);
				
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
			PreparedStatement ps = con.prepareStatement("select max(id) from passwords");
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
	
//	method to insert password into an database
	public static int insert(PwManager pwmanager){
		int status = 0;
		try {
			int max_id = PwManager_dao.max_id();
			String enc_pass = Cipher.encPass(pwmanager.getPassword(), pwmanager.getKey());
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into passwords values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, max_id);
			ps.setInt(2, pwmanager.getUserid());
			ps.setString(3, enc_pass);
			ps.setInt(4, pwmanager.getKey());
			ps.setString(5, pwmanager.getUsername());
			ps.setString(6, pwmanager.getUser_email());
			ps.setString(7, pwmanager.getApp_name());
			ps.setString(8, pwmanager.getUrl());
			
			status = ps.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}

//	method to retrieve password table from database
	public static List<PwManager> retrievePwTable(int userID){
		List<PwManager> passwords = new ArrayList<PwManager>();
		PwManager manager = new PwManager();
		String pass = "";
		
		try {
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from passwords where userid = ?");
			ps.setInt(1, userID);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				pass = Cipher.decrypt(rs.getString(3), rs.getInt(4));
				
				PwManager password = new PwManager();
				password.setApp_name(rs.getString(7));
				password.setUsername(rs.getString(5));
				password.setPassword(pass);
				password.setUser_email(rs.getString(6));
				password.setUrl(rs.getString(8));
				password.setKey(rs.getInt(4));
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
			PreparedStatement ps = con.prepareStatement("delete from passwords where id = ?");
			ps.setInt(1, delete_key);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	
//	method to insert user into an database
	public static int insertUser(){
		int status = 0;
		try {
			Connection con = PwManager_dao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into users (username, password, email) values(?,?,?)");
			ps.setString(1, "default_username");
			ps.setString(2, "default_password");
			ps.setString(3, "default@mail.com");
			
			status = ps.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
}
