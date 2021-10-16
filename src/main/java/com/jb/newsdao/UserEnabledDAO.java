package com.jb.newsdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jb.newsconnection.*;

public class UserEnabledDAO {
	
	private Connection connection;
	private PreparedStatement stmt;
	private boolean userEnabled;
	
	public boolean getUserEnabled(String user_enabled_id) throws SQLException {
		
		ResultSet resultSet = null;
		String sql = null;
		userEnabled = false;
		connection = NewsConnection.getNewsConnection();
		try {
			sql = "SELECT * FROM user_enabled WHERE user_enabled_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user_enabled_id);
			resultSet = stmt.executeQuery(); 
			if (resultSet.next()) {
				userEnabled = true;
			} else {
				userEnabled = false;
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();			
		}
		
		return userEnabled;		
	}
	
}
