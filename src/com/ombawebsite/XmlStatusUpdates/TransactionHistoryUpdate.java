package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Transaction;


@SuppressWarnings("serial")
public class TransactionHistoryUpdate extends HttpServlet {


	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/xml");
		    PrintWriter out=resp.getWriter();
		    HttpSession session;
		    session=req.getSession(true);
            
			String sDate=req.getParameter("sDate");
			String eDate=req.getParameter("eDate");
	    	String accountNo=req.getParameter("acc");
	    	
	    	out.println("<TransactionHistory>");
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
                
				j=tnum;
				int ct=0;
				for(i=0;i<date.size();i++){
					for(Transaction t:transactions){				  
					   if(date.get(i).substring(0,10).equals(t.getDateOfTransaction())&&
						  date.get(i).substring(11,19).equals(t.getTimeOfTransaction())&&j>0){
					    j--;
					  if(accountNo.equals(t.getToAccount())){
						out.println("<Transaction>");
						out.println("<Date>"+t.getDateOfTransaction()+"</Date>");
						out.println("<ToFrom>from "+t.getAccountNo()+"</ToFrom>");
						out.println("<Amount>"+t.getAmount()+"</Amount>");
						out.println("<Description>"+t.getDescription()+"</Description>");
                        out.println("<Balance>"+t.getToAccBalance()+"</Balance>");
                        out.println("</Transaction>");}
					  else if(t.getAccountNo().equals(accountNo)){
						  out.println("<Transaction>");
							out.println("<Date>"+t.getDateOfTransaction()+"</Date>");
							out.println("<ToFrom>from "+t.getToAccount()+"</ToFrom>");
							out.println("<Amount>"+t.getAmount()+"</Amount>");
							out.println("<Description>"+t.getDescription()+"</Description>");
	                        out.println("<Balance>"+t.getBalance()+"</Balance>");
	                        out.println("</Transaction>");}
					  ct++;
				   }}	}
	out.println("</TransactionHistory>");
  }
}


