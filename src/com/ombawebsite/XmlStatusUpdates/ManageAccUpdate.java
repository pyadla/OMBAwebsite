package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.CustomerDao;
import com.ombawebsite.ItemFiles.Customer;

@SuppressWarnings("serial")
public class ManageAccUpdate extends HttpServlet {

		 public void doGet(HttpServletRequest req, HttpServletResponse resp)
        		 throws IOException {
        	 		resp.setContentType("text/xml");
        	 		PrintWriter out=resp.getWriter();
        	 		HttpSession session;
        	 		session=req.getSession(true);
        	 		String customerId=req.getParameter("customerId");
        	 		String phoneno=req.getParameter("phoneno");
        	 		String address=req.getParameter("address");
        	 		String email=req.getParameter("email");
        	 		session.setAttribute("customerId",customerId);
        	 		List<Customer> customers=CustomerDao.INSTANCE.listCustomers();
                	for(Customer c:customers){
                		if((c.getCustomerId()).equals(customerId)){
                			Long id=c.getId();
                			CustomerDao.INSTANCE.updateDetails(id,phoneno,address,email);
                			}
                	}
                	out.println("<StatusUpdate><Status>You have successfully changed your details</Status></StatusUpdate>");
         }
}