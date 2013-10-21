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
public class LoginUpdate extends HttpServlet    
{ 
    private int login=0;
    private String cs="";
	private String ps="";
    private String customerId="";
    private String customerName="";
    private String gender="";
    int flag;
    
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		
		resp.setContentType("text/xml");
		PrintWriter out=resp.getWriter(); 
		HttpSession session;
		session=req.getSession(true);
		flag=0;
		login=0;
		int cu=0;
		customerId=req.getParameter("customerId");
		String password=req.getParameter("password");
		cs=checkCustomerId(customerId);
		session.setAttribute("cs",cs);
		ps=checkPassword(password);
		List<User> users=UserDao.INSTANCE.listCustomers();
		for(User u:users){
   		 if(customerId.equals(u.getCustomerId())){
   			 session.setAttribute("customerId",customerId);
   			 cu=1;
   			 if(password.equals(u.getPassword())){
   				List<Customer> customers=CustomerDao.INSTANCE.listCustomers();
   				for(Customer c:customers)
   					if(customerId.equals(c.getCustomerId())){
   				           customerName=c.getName();
   				           gender="Mr.";
   				           if(c.getSex().equals("Female"))
   				        	   gender="Ms.";
   				           session.setAttribute("password",password);
   				           session.setAttribute("customerName",customerName);
   				           session.setAttribute("gender",gender);
   				           }
   				login=1;
   				 session.setAttribute("login","1");
   			 }
   			 else {session.setAttribute("ps","Invalid Password");
   			       ps="Invalid password";
   			 }
   		   }
         }
		
		if(cu==0) cs="Invalid Customer Id";
		
		out.println("<Login>");
		if(login==1){
			out.println("<Status>SuccessfulLogin</Status>");
			out.println("<CustomerId>"+session.getAttribute("customerId")+"</CustomerId>");
			  out.println("<CustomerName>"+session.getAttribute("customerName")+"</CustomerName>");	
			  out.println("<Gender>"+session.getAttribute("gender")+"</Gender>");
			  out.println("<Password>"+session.getAttribute("password")+"</Password>");
		}
		else {
			if(cs.equals("Invalid Customer Id"))
			out.println("<Status>"+cs+"</Status>");
			else out.println("<Status>"+ps+"</Status>");
		}
		out.println("</Login>");
		out.close();  
	  	  
	 }
	
	private String checkCustomerId(String s) {
		if (s != null && s.matches("\\d{8}") ) {
				return "";
		}
		else{
			flag=1;
		}
		return "Invalid CustomerId";
	}
	
	private String checkPassword(String s) {
		if (s != null && s.matches("^[a-zA-Z]\\w*[\\.@$!_]*\\w*") ) {
				return "";
		}
		else{
			flag=1;
		}
		return "Invalid Password";
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  
	{  
		doGet(req, resp);  
	}
	
}  

