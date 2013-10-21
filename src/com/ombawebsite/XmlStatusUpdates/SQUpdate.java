package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ombawebsite.DaoFiles.UserDao;
import com.ombawebsite.ItemFiles.SecurityQuestion;
import com.ombawebsite.ItemFiles.User;

@SuppressWarnings("serial")
public class SQUpdate extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		    resp.setContentType("text/xml");
		    PrintWriter out=resp.getWriter();
		    HttpSession session=req.getSession(true);
		    List<SecurityQuestion> sq;
		    String customerId=req.getParameter("customerId");
		    session.setAttribute("customerId",customerId);
		    out.println("<SecurityQuestion>");		  
			  ResourceBundle qu=ResourceBundle.getBundle("SecurityQuestion");
			  List<User> customers = UserDao.INSTANCE.listCustomers();
			    for (User c:customers)  
		        {
			    	if(c.getCustomerId().equals(customerId)){
			    		sq = c.getSQ();
			       for(SecurityQuestion q:sq){
			    	   out.println("<SQ>");
				  out.println("<Q>"+qu.getString("Q"+q.getQuestionId())+"</Q>");
				  out.println("<A>"+q.getAnswer()+"</A>");
				  out.println("</SQ>");}}
			  }
		  
		  out.println("</SecurityQuestion>");
	}
}
