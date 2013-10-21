package com.ombawebsite;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		HttpSession session=req.getSession(true);
		session.invalidate();
		resp.getWriter().println("<script>parent.location='http://code.google.codeat2.appspot.com';</script>");
	} 
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{
		doGet(req,resp);
	}
}
