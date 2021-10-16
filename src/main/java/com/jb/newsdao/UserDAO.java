package com.jb.newsdao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jb.newsconnection.UserConnection;
import com.jb.newsmodel.User;

public class UserDAO {
	
	private Connection connection;
	private PreparedStatement stmt;
	private boolean userOK;
	private static String salt = "vamos river todavia CARAJO boquita puto!";
	 
	public boolean getUser(String puser, String pssw) throws SQLException {
		
		ResultSet resultSet = null;
		String sql = null;
		userOK = false;
		connection = UserConnection.getUserConnection();
		try {
			sql = "SELECT * FROM user WHERE user_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, puser);
			resultSet = stmt.executeQuery(); 
			if (resultSet.next()) {
				String generatedPassword = getSecurePassword(pssw, salt);
				if (resultSet.getString(2).equals(generatedPassword)) {
					userOK = true;					
				} else {
					userOK = false;
				}
			} else {
				userOK = false;
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();			
		}
		
		return userOK;
	}

	public boolean setUser(User user) throws SQLException {
		
		ResultSet resultSet = null;
		String sql = null;
		boolean operationStatus = false;
		connection = UserConnection.getUserConnection();
		try {
			sql = "SELECT * FROM user WHERE user_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUser_id());
			resultSet = stmt.executeQuery(); 
			if (resultSet.next()) {
				operationStatus = false;
			} else {
				String pwd = getSecurePassword(user.getUser_password(), salt);
				connection.setAutoCommit(false);
				sql = "INSERT INTO user (user_id, user_password, user_first_name, user_last_name, "
						+ "user_email) VALUES(?,?,?,?,?) ";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getUser_id());
				stmt.setString(2, pwd);
				stmt.setString(3, user.getUser_first_name());
				stmt.setString(4, user.getUser_last_name());
				stmt.setString(5, user.getUser_email());
				operationStatus = stmt.executeUpdate() > 0;
				connection.commit();
				stmt.close();
			}
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();			
		}
		
		return operationStatus;
	}
	
	public String setPassword(String user, String pwdold, String pwdnew1, String pwdnew2) throws SQLException {
		
		String pwdMsg = null;
		String sql = null;
		ResultSet resultSet = null;
		connection = UserConnection.getUserConnection();
		try {
			if (pwdnew1.equals(pwdnew2)) {
				sql = "SELECT * FROM user WHERE user_id = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, user);
				resultSet = stmt.executeQuery(); 
				if (resultSet.next()) {
					String hashpwdold = getSecurePassword(pwdold, salt);
					if (resultSet.getString(2).equals(hashpwdold)) {
						String hashpwdnew = getSecurePassword(pwdnew1, salt);						
						if (!hashpwdold.equals(hashpwdnew)) {
							connection.setAutoCommit(false);
							sql = "UPDATE user SET user_password = ? WHERE user_id = ? ";
							stmt = connection.prepareStatement(sql);
							stmt.setString(1, hashpwdnew);
							stmt.setString(2, user);
							stmt.executeUpdate(); 
							pwdMsg = "Password changed successfully, login with new password";
							connection.commit();
							stmt.close();
						} else {
							pwdMsg = "The password to change is the same as the original";
						}
					} else {
						pwdMsg = "Invalid password";
					}

				} else {
					pwdMsg = "Invalid user name or password";
				}			
			} else {
				pwdMsg = "New passwords are different";
			}
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();					
		}
		
		return pwdMsg;
	}
	
	public String getSecurePassword(String passwordToHash, String salt){
		
		String generatedPassword = null;
		    try {
		         MessageDigest md = MessageDigest.getInstance("SHA-512");
		         md.update(salt.getBytes(StandardCharsets.UTF_8));
		         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
		         StringBuilder sb = new StringBuilder();
		         for(int i=0; i< bytes.length ;i++){
		            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		         }
		         generatedPassword = sb.toString();
		        } 
		       catch (NoSuchAlgorithmException e){
		        e.printStackTrace();
		       }
		    
		return generatedPassword;
	}

}
