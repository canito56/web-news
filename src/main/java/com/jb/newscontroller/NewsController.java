package com.jb.newscontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jb.newsdao.NewsDAO;
import com.jb.newsdao.UserDAO;
import com.jb.newsdao.UserEnabledDAO;
import com.jb.newsmodel.News;
import com.jb.newsmodel.User;

@WebServlet("/NewsController")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewsController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String opt = req.getParameter("opt");
		RequestDispatcher requestDispatcher = null;
		
		switch(opt) {
		
			case "signin":
				req.getSession().setAttribute("nuser", "");
				requestDispatcher = req.getRequestDispatcher("/views/signin.jsp");
				requestDispatcher.forward(req, res);
				req.getSession().setAttribute("message", "");
				break;
				
			case "signup":
				req.getSession().setAttribute("nuser", "");
				requestDispatcher = req.getRequestDispatcher("/views/signup.jsp");
				requestDispatcher.forward(req, res);
				req.getSession().setAttribute("message", "");
				break;

			case "chgpwd":
				requestDispatcher = req.getRequestDispatcher("/views/chgpwd.jsp");
				requestDispatcher.forward(req, res);
				req.getSession().setAttribute("message", "");
				break;
			
			case "search":
				requestDispatcher = req.getRequestDispatcher("/views/search.jsp");
				requestDispatcher.forward(req, res);
				req.getSession().setAttribute("message", "");
				break;
			
			case "add":
				requestDispatcher = req.getRequestDispatcher("/views/add.jsp");
				requestDispatcher.forward(req, res);
				req.getSession().setAttribute("message", "");
				break;
			
			case "edit":
				try {
					int id = Integer.parseInt(req.getParameter("id"));
					NewsDAO newsDAO = new NewsDAO();
					News news = new News();
					news = newsDAO.edit(id);
					req.setAttribute("news", news);
					requestDispatcher = req.getRequestDispatcher("/views/edit.jsp");
					requestDispatcher.forward(req, res);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "delete":
				try {
					int id = Integer.parseInt(req.getParameter("id"));
					NewsDAO newsDAO = new NewsDAO();
					newsDAO.delete(id);
					opt = "list";
					res.sendRedirect("NewsController?opt=" + opt);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "list":
				try {
					String orderby = req.getParameter("orderby");
					String searchTitle = (String) req.getSession().getAttribute("searchTitle");
					if (orderby == "") {
						req.getSession().setAttribute("searchTitle", "");
						searchTitle = "";
					}
					NewsDAO newsDAO = new NewsDAO();
					List<News> listNews = null;
					listNews = newsDAO.getNews(searchTitle, orderby);
					req.setAttribute("listNews", listNews);
					requestDispatcher = req.getRequestDispatcher("/views/list.jsp");
					requestDispatcher.forward(req, res);					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;
				
			default:
				
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String opt = req.getParameter("opt");
		RequestDispatcher requestDispatcher = null;	
		
		switch(opt) {
		
			case "signin":
				String user = req.getParameter("user");
				String pssw = req.getParameter("password");
				boolean userOK = false;
				UserDAO userDAO = new UserDAO();
				try {
					userOK = userDAO.getUser(user, pssw);
					if (userOK) {
						boolean userEnabled = false;
						UserEnabledDAO userEnabledDAO = new UserEnabledDAO();
						userEnabled = userEnabledDAO.getUserEnabled(user);
						if (userEnabled) {
							req.getSession().setAttribute("nuser", user);							
							res.sendRedirect("NewsController?opt=list&orderby= ");
						} else {
							req.getSession().setAttribute("message", "User not enabled to this app, "
									+ "please contact the administrator");							
							res.sendRedirect("NewsController?opt=signin");
						}
					} else {
						req.getSession().setAttribute("message", "Invalid User or Password");
						res.sendRedirect("NewsController?opt=signin");
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "signup":
				UserDAO signupDAO = new UserDAO();
				User newUser = new User();
				newUser.setUser_first_name(req.getParameter("firstname"));
				newUser.setUser_last_name(req.getParameter("lastname"));
				newUser.setUser_email(req.getParameter("email"));
				newUser.setUser_id(req.getParameter("user"));
				newUser.setUser_password(req.getParameter("password"));
				try {
					boolean signupStatus = signupDAO.setUser(newUser);
					if (signupStatus) {
						opt = "signin";
						req.getSession().setAttribute("message", "User successfully registered, "
								+ "please contact the administrator to use this app");
					} else {
						opt = "signup";
						req.getSession().setAttribute("message", "User already exists");
					}
					res.sendRedirect("NewsController?opt=" + opt);															

				} catch (SQLException e) {
					e.printStackTrace();
				} 
				break;
				
			case "chgpwd":
				String chgpwdUser = req.getParameter("userchg");
				String pwdold = req.getParameter("pwdold");
				String pwdnew1 = req.getParameter("pwdnew1");
				String pwdnew2 = req.getParameter("pwdnew2");
				UserDAO chgpwdDAO = new UserDAO();
				try {
					String pwdMsg = chgpwdDAO.setPassword(chgpwdUser, pwdold, pwdnew1, pwdnew2);
					if (pwdMsg.substring(0, 8).equals("Password")) {
						opt = "signin";
					} else {
						opt = "chgpwd";
					}
					req.getSession().setAttribute("message", pwdMsg);
					res.sendRedirect("NewsController?opt=" + opt);															
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
				
			case "search":
				try {
					NewsDAO searchDAO = new NewsDAO();
					List<News> listNews = null;
					String title = req.getParameter("title");
					req.getSession().setAttribute("searchTitle", title);
					listNews = searchDAO.getNews(title, "");
					req.setAttribute("listNews", listNews);
					requestDispatcher = req.getRequestDispatcher("/views/list.jsp");
					requestDispatcher.forward(req, res);					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				break;

			case "add":
				News newsAdd = new News();
				NewsDAO newsAddDAO = new NewsDAO();
				newsAdd.setTitle(req.getParameter("title"));
				newsAdd.setNews(req.getParameter("news"));
			try {
				String addStatus = null;
				addStatus = newsAddDAO.add(newsAdd);
				if (addStatus == "OK") {
					opt = "list";
					res.sendRedirect("NewsController?opt=" + opt);									
				}
				if (addStatus == "already exists") {
					opt = "add";
					req.getSession().setAttribute("message", "News with this Title already exist");
					res.sendRedirect("NewsController?opt=" + opt);									
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
				break;
				
			case "edit":
				News newsEdit = new News();
				NewsDAO newsEditDAO = new NewsDAO();
				newsEdit.setNews_id(Integer.parseInt(req.getParameter("id")));
				newsEdit.setNews(req.getParameter("news"));
				try {
					newsEditDAO.update(newsEdit);
					opt = "list";
					res.sendRedirect("NewsController?opt=" + opt);				
				} catch (SQLException e) {
					e.printStackTrace();
				}				
				break;
				
			default:
				
		}

	}

}
