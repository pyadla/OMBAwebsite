package com.ombawebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Account;
import com.ombawebsite.ItemFiles.Transaction;


@SuppressWarnings("serial")
public class MiniStatement extends HttpServlet {
	
	int flag;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/html");
		    PrintWriter out=resp.getWriter();
		    flag=0;
		    HttpSession session;
		    session=req.getSession(true);
		    if((session.getAttribute("login").toString()).equals("1")){
	    	String accountNo=req.getParameter("acc");
	    	String balance="";

	    	if(flag==0){
	    		List<Account> accounts=AccountDao.INSTANCE.listAccounts();
	    		for(Account a:accounts){
	    			if(a.getAccountNo().equals(accountNo)){
	    				balance=a.getBalance();
	    				Long id=a.getId();
	    				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    			    Date date = new Date();
	    				AccountDao.INSTANCE.updateStatementDate(id,
	    						(dateFormat.format(date)).toString());
	    			}
	    		}
                
	    		List<Transaction> transactions=TransactionDao.INSTANCE.listTransactions();
                ArrayList<String> date=new ArrayList<String>();
                String d;
             
                int i,j;
                int tnum=0;
                
                for(Transaction t:transactions){
                    if(accountNo.equals(t.getToAccount())||accountNo.equals(t.getAccountNo())){
                	    date.add(t.getDateOfTransaction()+"-"+t.getTimeOfTransaction());
                	    tnum++;}
                }
                
                for(i=0;i<date.size();i++){
                	d=date.get(i).substring(6,10)+"-"+date.get(i).substring(3,5)+"-"+date.get(i).substring(0,2)
                	  +"-"+date.get(i).substring(11,19);
                    date.set(i,d);
                }
                
                Collections.sort(date);
                                
                for(i=0;i<date.size();i++){
                	d=date.get(i).substring(8,10)+"-"+date.get(i).substring(5,7)+"-"+
                      date.get(i).substring(0,4)+"-"+date.get(i).substring(11,19);
                    date.set(i,d);
                }
                
				out.println("<html>");
				out.println("<body style=\"border-style:solid;"+
	                                      "border-width: 55px;"+
	                                      "border-color:#FFFFFF;\" scrolling=\"auto\">");
				out.println("<h3 style=\"color:#1975D1\">"+
				            "Mini Statement for account number: "+accountNo+"</h3>");
				
				out.println("<table style=\"border-spacing:4px;\"><tr>"+
				            "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Date</label></th>"+
						                "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Amount</label></th>"+
						                "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Debit/Credit</label></th></tr>");
				Collections.reverse(date);
				int ct=0;
				j=tnum;
				
				for(i=0;i<date.size();i++){	
				  for(Transaction t:transactions){
					if(ct<5&&j>0){
						if(date.get(i).substring(0,10).equals(t.getDateOfTransaction())&&
								  date.get(i).substring(11,19).equals(t.getTimeOfTransaction())&&j>0){
						if(accountNo.equals(t.getToAccount())){
							out.println("<tr><td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDateOfTransaction()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getAmount()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">Credit</td></tr>");}
						else if(t.getAccountNo().equals(accountNo)){
							out.println("<tr><td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDateOfTransaction()+"</td>"+
									    "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getAmount()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">Debit</td></tr>");}
						ct++;j--;}
				}}
			}
				out.println("<tr><td colspan=\"3\""+
			                "style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						    "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                            "background-color:#EBF0FF;color:#1975D1;\""+
						    ">Account Balance is : "+balance+"</td></tr>");
				out.println("</table>");
				out.println("<a href=\"/accountBalance\">"+
			            "<h3 style=\"color:#1975D1\">Back</h3></a>");
				out.println("</body>");
				out.println("</html>");	
        }
        else resp.sendRedirect("Ministatement.jsp");
	} else resp.sendRedirect("Login.jsp");
  }
}


