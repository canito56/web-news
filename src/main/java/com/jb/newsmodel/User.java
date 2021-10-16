package com.jb.newsmodel;

import java.sql.Timestamp;

public class User {
	
	private String user_id;
	private String user_password;
	private String user_first_name;
	private String user_last_name;
	private String user_email;
	private Timestamp user_date_created;
	
	public User(String user_id, String user_password, String user_first_name, 
			String user_last_name, String user_email, Timestamp user_date_created) {
		super();
		this.setUser_id(user_id);
		this.setUser_password(user_password);
		this.setUser_first_name(user_first_name);
		this.setUser_last_name(user_last_name);
		this.setUser_email(user_email);
		this.setUser_date_created(user_date_created);
	}
	
	public User() {
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Timestamp getUser_date_created() {
		return user_date_created;
	}

	public void setUser_date_created(Timestamp user_date_created) {
		this.user_date_created = user_date_created;
	}

}
