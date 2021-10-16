package com.jb.newsdao;

public class NewsAscDesc {
	
	private static String titleAscDesc;
	private static String dateAscDesc;

	public static void setTitleAscDesc() {
		titleAscDesc = "";
	}

	public static void setDateAscDesc() {
		dateAscDesc = "";
	}

	public static String getTitleAscDesc () {
		if (titleAscDesc == "" || titleAscDesc == "DESC") {
			titleAscDesc = "ASC";
		} else {
			titleAscDesc = "DESC";
		}
		return titleAscDesc;
	}

	public static String getDateAscDesc () {
		if (dateAscDesc == "" || dateAscDesc == "DESC") {
			dateAscDesc = "ASC";
		} else {
			dateAscDesc = "DESC";
		}
		return dateAscDesc;
	}

}
