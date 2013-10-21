package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.UserDao;
import com.ombawebsite.ItemFiles.SecurityQuestion;
import com.ombawebsite.ItemFiles.User;


@SuppressWarnings("serial")
public class AddSecurityQuestion extends HttpServlet {
	
	int flag=0;
	String message="";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		
		    resp.setContentType("text/xml");
		    HttpSession session=req.getSession(true);
			String customerId= req.getParameter("customerId");
			session.setAttribute("customerId",customerId);
			String ques=req.getParameter("questionId");
			String ans=req.getParameter("answer");
			List<SecurityQuestion> sq= new ArrayList<SecurityQuestion>();
			List<SecurityQuestion> q= new ArrayList<SecurityQuestion>();
			
        	List<User> c=UserDao.INSTANCE.listCustomers();
           
        	for(User cus:c){
        		if((cus.getCustomerId()).equals(customerId)){
        				    q=cus.getSQ();
        				    for(SecurityQuestion j:q){
        				    	sq.add(new SecurityQuestion(j.getQuestionId(),j.getAnswer()));
        				    }        					
        					sq.add(new SecurityQuestion(ques,ans));
                            UserDao.INSTANCE.updateSQ(cus.getId(),sq);
          			       resp.getWriter().println("<StatusUpdate>"+
                            "<Status>Selected question and answer are added</Status>"+
          			        "</StatusUpdate>");        				
        		}
        	}        	
    }
}
		
