package com.jb.newsmodel;

import java.sql.Timestamp;

public class News {
	
	private int id_news;
	private String title;
	private String news;
	private Timestamp date_created;
	
	public News (int id_news, String title, String news, Timestamp date_created) {
		super();
		this.setNews_id(id_news);
		this.setTitle(title);
		this.setNews(news);
		this.setDate_created(date_created);
	}
	
	public News() {
	}

	public int getNews_id() {
		return id_news;
	}

	public void setNews_id(int id_news) {
		this.id_news = id_news;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Timestamp getDate_created() {
		return date_created;
	}

	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

	@Override
	public String toString() {
		return "News [id_news=" + id_news + ", title=" + title + ", news=" + news + 
				", date_created=" + date_created + "]";
	}

}
