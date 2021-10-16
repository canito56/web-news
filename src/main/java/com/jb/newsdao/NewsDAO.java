package com.jb.newsdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jb.newsconnection.NewsConnection;
import com.jb.newsmodel.News;

public class NewsDAO {
	
	private Connection connection;
	private PreparedStatement stmt;
	private boolean operationStatus;
	
	public News edit(int id) throws SQLException {
		ResultSet resultSet = null;
		News news = new News();
		String sql = null;
		connection = NewsConnection.getNewsConnection();
		try {
			sql = "SELECT * FROM news WHERE id_news = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				news.setNews_id(resultSet.getInt(1));
				news.setTitle(resultSet.getString(2));
				news.setNews(resultSet.getString(3));
				news.setDate_created(resultSet.getTimestamp(4));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return news;
	}

	public List<News> getNews(String title, String orderby) throws SQLException {
		ResultSet resultSet = null;
		List<News> listNews = new ArrayList<>();
		String sql = null;
		connection = NewsConnection.getNewsConnection();
		try {
			if (title == "" || title == null) {
				sql = "SELECT * FROM news ";
				if (orderby == "" || orderby == null) {
					NewsAscDesc.setTitleAscDesc();
					NewsAscDesc.setDateAscDesc();					
				}
			} else {
				sql = "SELECT * FROM news WHERE title like ? ";
			}
			if (orderby != "" && orderby != null) {
				sql = sql + "ORDER BY " + orderby;				
				if (orderby.equals("title")) {
					NewsAscDesc.setDateAscDesc();
					sql = sql + " " + NewsAscDesc.getTitleAscDesc();
				}
				if (orderby.equals("date_created")) {
					NewsAscDesc.setTitleAscDesc();
					sql = sql + " " + NewsAscDesc.getDateAscDesc();
				}
			}
			stmt = connection.prepareStatement(sql);
			if (title != "" && title != null) {
				String likeTitle = "%" + title + "%";
				stmt.setString(1, likeTitle);
			}
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				News news = new News();
				news.setNews_id(resultSet.getInt(1));
				news.setTitle(resultSet.getString(2));
				news.setNews(resultSet.getString(3));
				news.setDate_created(resultSet.getTimestamp(4));
				listNews.add(news);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return listNews;
	}
	
	public boolean update(News news) throws SQLException {
		String sql = null;
		operationStatus = false;
		connection = NewsConnection.getNewsConnection();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE news " +
					"set news = ? " +
					"WHERE id_news = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, news.getNews());
			stmt.setInt(2, news.getNews_id());
			operationStatus = stmt.executeUpdate() > 0;
			connection.commit();
			stmt.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return operationStatus;
	}

	public String add(News news) throws SQLException {
		ResultSet resultSet = null;
		String addStatus = "NOK";
		String sql = null;
		operationStatus = false;
		connection = NewsConnection.getNewsConnection();
		try {
			sql = "SELECT * FROM news WHERE title = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, news.getTitle());
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				addStatus = "already exists";
			} else {
				connection.setAutoCommit(false);
				sql = "INSERT INTO news (title, news) VALUES(?,?) ";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, news.getTitle());
				stmt.setString(2, news.getNews());
				operationStatus = stmt.executeUpdate() > 0;
				connection.commit();
				addStatus = "OK";
			}
			stmt.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return addStatus;
	}
	
	public boolean delete(int id) throws SQLException {
		String sql = null;
		operationStatus = false;
		connection = NewsConnection.getNewsConnection();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM news WHERE id_news = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			operationStatus = stmt.executeUpdate() > 0;
			connection.commit();
			stmt.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return operationStatus;
	}

}
