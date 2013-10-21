package com.ombawebsite.XmlStatusUpdates;

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
public class MiniStatementUpdate extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/xml");
		    PrintWriter out=resp.getWriter();
		    
		    HttpSession session=req.getSession(true);
		    
		    String customerId=req.getParameter("customerId");
		    session.setAttribute("customerId",customerId);
		    
	    	String accountNo=req.getParameter("accountNo");
	    	session.setAttribute("accountNo",accountNo);

	    		List<Account> accounts=AccountDao.INSTANCE.listAccounts();
	    		for(Account a:accounts){
	    			if(a.getAccountNo().equals(accountNo)){
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
                	d=date.get(i).substring(6,10)+"-"+date.get(i).substring(3,5)+"-"+
                      date.get(i).substring(0,2)+"-"+date.get(i).substring(11,19);
                    date.set(i,d);
                }
                
                Collections.sort(date);
                                
                for(i=0;i<date.size();i++){
                	d=date.get(i).substring(8,10)+"-"+date.get(i).substring(5,7)+"-"+
                      date.get(i).substring(0,4)+"-"+date.get(i).substring(11,19);
                    date.set(i,d);
                }
                
				Collections.reverse(date);
				int ct=0;
				j=tnum;
				
				out.println("<MiniStatement>");
				for(i=0;i<date.size();i++){	
				  for(Transaction t:transactions){
					if(ct<5&&j>0){
						if(date.get(i).substring(0,10).equals(t.getDateOfTransaction())&&
								  date.get(i).substring(11,19).equals(t.getTimeOfTransaction())&&j>0){
						if(accountNo.equals(t.getToAccount())){
							out.println("<Transaction>");
							out.println("<Date>"+t.getDateOfTransaction()+"</Date>");
							out.println("<Amount>"+t.getAmount()+"</Amount>");
                            out.println("<Desc>Credit</Desc>");
                            out.println("</Transaction>");}
						else if(t.getAccountNo().equals(accountNo)){
							out.println("<Transaction>");
							out.println("<Date>"+t.getDateOfTransaction()+"</Date>");
							out.println("<Amount>"+t.getAmount()+"</Amount>");
                            out.println("<Desc>Debit</Desc>");
                            out.println("</Transaction>");}
						ct++;j--;}
				}}
			}
				out.println("</MiniStatement>");	
        }
}


