package com.ombawebsite.XmlStatusUpdates;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ombawebsite.DaoFiles.CustomerDao;
import com.ombawebsite.DaoFiles.UserDao;
import com.ombawebsite.ItemFiles.Customer;
import com.ombawebsite.ItemFiles.User;

@SuppressWarnings("serial")
public class LoginSQ extends HttpServlet    
{ 
    private String customerId="";
    private String password="";
    private String customerName="";
    private String gender="";
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		
		resp.setContentType("text/xml");
		PrintWriter out=resp.getWriter(); 
		HttpSession session;
		session=req.getSession(true);
		customerId=req.getParameter("customerId");
		password=req.getParameter("password");
	    out.println("<Login>");
		List<User> users=UserDao.INSTANCE.listCustomers();
		for(User u:users){
   		 if(customerId.equals(u.getCustomerId())){
   			 session.setAttribute("customerId",customerId);
   				Long id=u.getId();
                UserDao.INSTANCE.changePassword(id,password);
                session.setAttribute("password",password);
                List<Customer> customers=CustomerDao.INSTANCE.listCustomers();
   				for(Customer c:customers)
   					if(customerId.equals(c.getCustomerId())){
   				           customerName=c.getName();
   				           gender=c.getSex();
   				           session.setAttribute("customerName",customerName);
   				           session.setAttribute("gender",gender);
   				           }
   		   }
         }
		
			out.println("<Status>Password successfully changed</Status>");
			out.println("<CustomerId>"+session.getAttribute("customerId")+"</CustomerId>");
			  out.println("<CustomerName>"+session.getAttribute("customerName")+"</CustomerName>");	
			  out.println("<Gender>"+session.getAttribute("gender")+"</Gender>");
			  out.println("<Password>"+session.getAttribute("password")+"</Password>");
		    out.println("</Login>");
		out.close();  
	  	  
	 }

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		doGet(req, resp);  
	}
	
}  

