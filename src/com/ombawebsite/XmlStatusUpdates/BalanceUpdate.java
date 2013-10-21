package com.ombawebsite.XmlStatusUpdates;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.ItemFiles.Account;


@SuppressWarnings("serial")
public class BalanceUpdate extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/xml");
		    PrintWriter out=resp.getWriter();	    
		     String customerId=req.getParameter("customerId");		     
			HttpSession session=req.getSession(true);
			session.setAttribute("customerId",customerId);
			List<String> accList=new ArrayList<String>();
			List<Account> accounts=AccountDao.INSTANCE.listAccounts();
			for(Account a:accounts)
				if(session.getAttribute("customerId").equals(a.getCustomerId()))
		           accList.add(a.getAccountNo());
			    Collections.sort(accList);
			out.println("<AccountBalance>");
			for(String ac:accList)
			     for(Account a:accounts)
					if(a.getAccountNo().equals(ac))	{
						out.println("<Account>");
				       out.println("<AccountNo>"+a.getAccountNo()+"</AccountNo>");
				       out.println("<Balance>"+a.getBalance()+"</Balance>");
				out.println("</Account>");}
			out.println("</AccountBalance>");
	}
		
}

