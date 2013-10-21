package com.ombawebsite.Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Account;
import java.util.Date;


@SuppressWarnings("serial")
public class TransferFunds extends HttpServlet {
	
	String fromaccount,toaccount,amount,customerId;
	DateFormat dateFormat,timeFormat;
	Date date,time;
	int flag;
	int ben;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/html");
		    HttpSession session;
		    session=req.getSession(true);
		    customerId=session.getAttribute("customerId").toString();
		    if((session.getAttribute("login").toString()).equals("1")){
			
			flag=0;
			ben=0;
			
		    fromaccount=(req.getParameter("fromaccount")); 
		    String s1=checkFromAccountNo(fromaccount);
            toaccount=(req.getParameter("toaccount"));
			checkBankCode(toaccount);
			amount=req.getParameter("amount");			
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    date = new Date();
		    timeFormat = new SimpleDateFormat("hh-mm-ss");
		    time = new Date();
		    String bal= checkBalance(amount,fromaccount,toaccount);
		    resp.getWriter().println("<html><body style=\"border-style:solid;"+
                "border-width: 55px;"+
                "border-color:#FFFFFF;\">");	
		    	if(flag==0){
	                  resp.getWriter().println("<h3 style=\"color:#1975D1\">"+amount+" has been transfered from"+
	                                                  fromaccount+" to "+toaccount+
	                                                  "</h3>");
                }
		    	else resp.getWriter().println("<h3 style=\"color:#1975D1\">"+s1+" "+bal+"");
		    	resp.getWriter().println("<a href=\"TransferFunds.jsp\">"+
             			 "<h3 style=\"color:#1975D1\">Back</h3></a>"+	
           			 "</body></html>");
	      }else resp.sendRedirect("Login.jsp");
    }
	private String checkFromAccountNo(String s) {
            List<Account> a=AccountDao.INSTANCE.listAccounts();
               for(Account acc:a)
                if((acc.getAccountNo()).equals(s))                       
                            return ""; 
             flag=1;
             return "Account does not exist";
      }
           private void checkBankCode(String s) {
        	   List<Account> a=AccountDao.INSTANCE.listAccounts();
               for(Account acc:a){
                if((acc.getAccountNo()).equals(s))                       
                            ben=1;
               }
          }

           private String checkBalance(String s,String f,String t)
           {
            	   List<Account> a=AccountDao.INSTANCE.listAccounts();
                   for(Account acc:a){
                    if((acc.getAccountNo()).equals(f)){   
        				Long id1=acc.getId();
                        if(Double.parseDouble(acc.getBalance())>Double.parseDouble(s))
                        	if(ben==1){
                        		for(Account ac:a)
    	       					if((ac.getAccountNo()).equals(t)){
    		       				Long id2=ac.getId();
        				                double b=Double.parseDouble(ac.getBalance());
        				                double am=Double.parseDouble(s);
        				                String toBal=Double.toString(b+am);
        				                AccountDao.INSTANCE.updateBalance(id2,toBal);//toaccount updation
        				                b=Double.parseDouble(acc.getBalance());
        				                String Bal=Double.toString(b-am);
        				                TransactionDao.INSTANCE.add(customerId,fromaccount,
        				                		  (dateFormat.format(date)).toString(),
        				                		  (timeFormat.format(time)).toString(),
        				                		  toaccount, amount,Bal,toBal,"online transfer"); 
    		       						AccountDao.INSTANCE.updateBalance(id1,Bal);//from account updation
    		       						AccountDao.INSTANCE.updateTransactionDate(id1,
    		       								(dateFormat.format(date)).toString());
    		       						return "";	
    	                        }
                           }//if ben
                           else {
                        	   double b=Double.parseDouble(acc.getBalance());
                        	   double am=Double.parseDouble(s);
                        	   String Bal=Double.toString(b-am);
                        	   TransactionDao.INSTANCE.add(customerId,fromaccount,
                        			   (dateFormat.format(date)).toString(),(timeFormat.format(time)).toString(),
                        			   toaccount, amount,Bal,"unavailable","online transfer"); 
                        	   AccountDao.INSTANCE.updateBalance(id1,Bal);//from account updation
                        	   AccountDao.INSTANCE.updateTransactionDate(id1,
                        			   (dateFormat.format(date)).toString());
                        	   return "";
                           }
                    }}
             flag=1;
             return "Insufficient Balance";
      }
 }

	



