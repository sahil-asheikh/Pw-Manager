package com.pwmanager.models;

public class PwManager {
	private int id;
	private int userid;
	private String user_username;
	private String user_password;
	private int key;
	private String username;
	private String password;
	private String user_email;
	private String app_name;
	private String url;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUser_username() {
		return user_username;
	}
	public void setUser_username(String user_usrname) {
		this.user_username = user_usrname;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String usr_password) {
		this.user_password = usr_password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
