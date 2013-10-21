package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.CustomerDao;
import com.ombawebsite.ItemFiles.Customer;

@SuppressWarnings("serial")
public class CustomerDetailsUpdate extends HttpServlet {

         int flag;

		 public void doGet(HttpServletRequest req, HttpServletResponse resp)
        		 throws IOException {
        	 		resp.setContentType("text/xml");
        	 		flag=0;
        	 		PrintWriter out=resp.getWriter();
        	 		HttpSession session;
        	 		session=req.getSession(true);
        	 		String customerId=req.getParameter("customerId");
        	 		session.setAttribute("customerId",customerId);
        	 		out.println("<CustomerDetails>");        			
        			List<Customer> customers=CustomerDao.INSTANCE.listCustomers();
                	for(Customer c:customers){
                		if((c.getCustomerId()).equals(customerId)){
                			out.println("<Customer>");
                			out.println("<Phoneno>"+c.getMobileNo()+"</Phoneno>");
                			out.println("<Address>"+c.getCurrentAddress()+"</Address>");
                			out.println("<Email>"+c.getEmailAddress()+"</Email>");
                			out.println("</Customer>");}
                	}
                	out.println("</CustomerDetails>");
         }
}