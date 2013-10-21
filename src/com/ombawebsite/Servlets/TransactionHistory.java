package com.ombawebsite.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Transaction;


@SuppressWarnings("serial")
public class TransactionHistory extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/html");
		    PrintWriter out=resp.getWriter();
		    int flag=0;
		    String s="";
		    HttpSession session;
		    session=req.getSession(true);
		    if((session.getAttribute("login").toString()).equals("1")){ 
            
			String sDate=session.getAttribute("sDate").toString();
			String eDate=session.getAttribute("eDate").toString();
			
	    	String accountNo=req.getParameter("acc");
	    	
	    	out.println("<html>");
			out.println("<body style=\"border-style:solid;"+
                                      "border-width: 55px;"+
                                      "border-color:#FFFFFF;\" scrolling=\"auto\">");
	    	
			if(flag==0){
        	
				List<Transaction> transactions=TransactionDao.INSTANCE.listTransactions();
                ArrayList<String> date=new ArrayList<String>();
                String d;
             
                int i,j;
                int tnum=0;
                
                for(Transaction t:transactions){
                    if(accountNo.equals(t.getToAccount())||accountNo.equals(t.getAccountNo())){
                    	String g=t.getDateOfTransaction();
                    	String gr=g.substring(6,10)+"-"+g.substring(3,5)+"-"+g.substring(0,2);
                        if(gr.compareTo(sDate)>=0 && gr.compareTo(eDate)<=0){  
                	    date.add(t.getDateOfTransaction()+"-"+t.getTimeOfTransaction());
                	    tnum++;}}
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
                
				out.println("<h3 style=\"color:#1975D1\">"+
	                        "Transaction History for account number: "+accountNo+"</h3>");
				
				out.println("<table style=\"border-spacing:4px;\"><tr>"+
				            "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Date</label></th>"+
							"<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>To/From Account</label></th>"+
				            "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Amount</label></th>"+
						    "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Description</label></th>"+
				            "<th style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "><label>Balance</label></th>"+
				            "</tr>");
				j=tnum;
				for(i=0;i<date.size();i++){
					for(Transaction t:transactions){				  
					   if(date.get(i).substring(0,10).equals(t.getDateOfTransaction())&&
						  date.get(i).substring(11,19).equals(t.getTimeOfTransaction())&&j>0){
					    j--;
					  if(accountNo.equals(t.getToAccount())){
						out.println("<tr><td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDateOfTransaction()+"</td>"+
								        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "> from "+t.getAccountNo()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getAmount()+"</td>"+
						                "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDescription()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getToAccBalance()+"</td></tr>");}
					  else if(t.getAccountNo().equals(accountNo)){
						out.println("<tr><td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDateOfTransaction()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                "> to "+t.getToAccount()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getAmount()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getDescription()+"</td>"+
                                        "<td style=\"PADDING-RIGHT: 10px; PADDING-LEFT: 10px;"+
						                "PADDING-BOTTOM: 10px; PADDING-TOP: 10px;"+
                                        "background-color:#EBF0FF;color:#1975D1;\""+
						                ">"+t.getBalance()+"</td></tr>");}
				   }}
				}
				out.println("</table>");	
        }
        else out.println("<h3 style=\"color:#1975D1\">"+s+"</h3>");
			out.println("<a href=\"TransactionHistory.jsp\">"+
		            "<h3 style=\"color:#1975D1\">Back</h3></a>");
			out.println("</body>");
			out.println("</html>");
	} else resp.sendRedirect("Login.jsp");
  }
	
}


