package com.jb.newsconnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class NewsConnection {
	
	private static BasicDataSource dataSource = null;
	
	private static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mysql://localhost:3306/news_portal" +
                    "?useUnicode=true&use" +
                    "JDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15); 
			dataSource.setMaxTotal(20);
			dataSource.setMaxWaitMillis(5000);
		}
		return dataSource;
	}
	
	public static Connection getNewsConnection() throws SQLException {
		return getDataSource().getConnection();
	}
	
	public static void desconnectionNews() throws SQLException {
		try {
			if (dataSource != null) {
				dataSource.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
