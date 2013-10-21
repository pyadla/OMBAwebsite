package com.ombawebsite.Servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.DaoFiles.CustomerDao;
import com.ombawebsite.DaoFiles.SchedTransDao;
import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Account;
import com.ombawebsite.ItemFiles.Customer;
import com.ombawebsite.ItemFiles.ScheduleTransaction;


@SuppressWarnings("serial")
public class ScheduleTransfer extends HttpServlet {
	DateFormat dateFormat,timeFormat;
	Date date,time;
	int confirm=0;
	int flag=0,ben=0,update=0;
	String fromaccount,toaccount,amount,customerId,times,strTo,fstrTo,tstrTo,fromcustomer,tocustomer;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");		
		try {
			flag=0;
			ben=0;		      
			//strTo=req.getParameter("toaddress");	
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    date = new Date();
		timeFormat = new SimpleDateFormat("HH-mm-ss");
		   time = new Date();     
	
         List<Account> a=AccountDao.INSTANCE.listAccounts();
            for(Account acc:a)
             if((acc.getAccountNo()).equals(fromaccount))
            	flag=1; 
            if(flag==0)
            resp.getWriter().println("invalid");
            List<Account> a1=AccountDao.INSTANCE.listAccounts();

  
            for(Account acc:a1){
             if((acc.getAccountNo()).equals(toaccount))                       
                         ben=1;
            }
      
            List<ScheduleTransaction> sch=SchedTransDao.INSTANCE.listAccounts();
            for(ScheduleTransaction schacc:sch){
                
	     if(dateFormat.format(date).equals(schacc.getDate()))
	     {
	    	fromaccount=schacc.getFromAccount();
	    	toaccount=schacc.getToAccount();
	    	amount=schacc.getAmount();
             List<Account> a2=AccountDao.INSTANCE.listAccounts();
                for(Account acc:a2){
                 if((acc.getAccountNo()).equals(fromaccount)){
     				Long id1=acc.getId();
                     if(Double.parseDouble(acc.getBalance())>Double.parseDouble(amount))
                     	if(ben==1){
                     		for(Account ac:a2)
 	       					if((ac.getAccountNo()).equals(toaccount)){
 		       				Long id2=ac.getId();
     				                double b=Double.parseDouble(ac.getBalance());
     				                double am=Double.parseDouble(amount);
     				                String toBal=Double.toString(b+am);
     				                AccountDao.INSTANCE.updateBalance(id2,toBal);//toaccount updation
     				                b=Double.parseDouble(acc.getBalance());
     				                String Bal=Double.toString(b-am);
     				                TransactionDao.INSTANCE.add(schacc.getCustomerId(),fromaccount,
     				                		  (dateFormat.format(date)).toString(),
     				                		  (timeFormat.format(time)).toString(),
     				                		  toaccount, amount,Bal,toBal,"schedule transfer"); 
 		       						AccountDao.INSTANCE.updateBalance(id1,Bal);//from account updation
 		       						AccountDao.INSTANCE.updateTransactionDate(id1,
 		       								(dateFormat.format(date)).toString());
 		       					update=1;
 	                        }
                        }//if ben
                        else {
                     	   double b=Double.parseDouble(acc.getBalance());
                     	   double am=Double.parseDouble(amount);
                     	   String Bal=Double.toString(b-am);
                     	   TransactionDao.INSTANCE.add(schacc.getCustomerId(),fromaccount,
                     			   (dateFormat.format(date)).toString(),(timeFormat.format(time)).toString(),
                     			   toaccount, amount,Bal,"unavailable","schedule transfer"); 
                     	   AccountDao.INSTANCE.updateBalance(id1,Bal);//from account updation
                     	   AccountDao.INSTANCE.updateTransactionDate(id1,
                     			   (dateFormat.format(date)).toString());
                          update=1;
                        }
                  }
                 flag=1;
                 }
           }
          
            }
            if(update==1){
                List<Customer> cus=CustomerDao.INSTANCE.listCustomers();
                List<Account> accounts=AccountDao.INSTANCE.listAccounts();
                for(Account acc:accounts){
              	  if(acc.getAccountNo().equals(fromaccount))
              	  {
              		   fromcustomer=acc.getCustomerId();}
              		  if(acc.getAccountNo().equals(toaccount))
              			   tocustomer=acc.getCustomerId();
                  }
          			  if(fromcustomer.equals(tocustomer))
          		     {
          				for(Customer c:cus){
          					if(c.getCustomerId().equals(fromcustomer))
          				       strTo=c.getEmailAddress();
          				}
          				  if (strTo == null) throw new Exception("To field cannot be empty.");
   	               		 
    	      	     		//Trim the stuff
    	      	     		strTo = strTo.trim();
    	      	     		if (strTo.length() == 0) throw new Exception("To field cannot be empty.");
    	      	     		//Call the GAEJ Email Service
    	      	     		Properties props = new Properties();
    	      	     		Session session1 = Session.getDefaultInstance(props, null);
    	      	     		MimeMessage msg = new MimeMessage(session1);
    	      	     		msg.setFrom(new InternetAddress("nehaphani@gmail.com"));
    	      	     		msg.addRecipient(MimeMessage.RecipientType.TO,
    	      	     		new InternetAddress(strTo));
    	      	     		msg.setSubject("email alert");
    	      	     		msg.setText("succesfully transfered");
    	      	     		Transport.send(msg);
   	            	 }else{
                for(Account acc:accounts){
          	    if(acc.getAccountNo().equals(fromaccount))
          	    {
          	       		 String customer=acc.getCustomerId();
          	             for(Customer c:cus){
          	            	 if(c.getCustomerId().equals(customer))
          	            	 {
          	            		  fstrTo=c.getEmailAddress();
          	            		  if (fstrTo == null) throw new Exception("To field cannot be empty.");
          	               		 
          	      	     		//Trim the stuff
          	      	     		fstrTo = fstrTo.trim();
          	      	     		if (fstrTo.length() == 0) throw new Exception("To field cannot be empty.");
          	      	     		//Call the GAEJ Email Service
          	      	     		Properties props = new Properties();
          	      	     		Session session1 = Session.getDefaultInstance(props, null);
          	      	     		MimeMessage msg = new MimeMessage(session1);
          	      	     		msg.setFrom(new InternetAddress("nehaphani@gmail.com"));
          	      	     		msg.addRecipient(MimeMessage.RecipientType.TO,
          	      	     		new InternetAddress(fstrTo));
          	      	     		msg.setSubject("email alert");
          	      	     		msg.setText("succesfully transfered");
          	      	     		Transport.send(msg);
          	            	 }
          	             }  
          	    }
          	    if(acc.getAccountNo().equals(toaccount))
          	    {
          	       		 String customer=acc.getCustomerId();
          	           //  List<Customer> cus=CustomerDao.INSTANCE.listCustomers();
          	             for(Customer c:cus){
          	            	 if(c.getCustomerId().equals(customer))
          	            	 {
          	            		  tstrTo=c.getEmailAddress();
          	            		  if (tstrTo == null) throw new Exception("To field cannot be empty.");
          	               		 
          	      	     		//Trim the stuff
          	      	     		tstrTo = tstrTo.trim();
          	      	     		if (tstrTo.length() == 0) throw new Exception("To field cannot be empty.");
          	      	     		//Call the GAEJ Email Service
          	      	     		Properties props = new Properties();
          	      	     		Session session2 = Session.getDefaultInstance(props, null);
          	      	     		MimeMessage msg = new MimeMessage(session2);
          	      	     		msg.setFrom(new InternetAddress("nehaphani@gmail.com"));
          	      	     		msg.addRecipient(MimeMessage.RecipientType.TO,
          	      	     		new InternetAddress(tstrTo));
          	      	     		msg.setSubject("email alert");
          	      	     		msg.setText("ur account has been updated");
          	      	     		Transport.send(msg);
          	            	 }
          	             }
          	        }
               }
             }
           }//update    
		   
			//Put your logic here
			//BEGIN
			//END
             }//try
			catch (Exception ex) {
			//Log any exceptions in your Cron Job
				
			}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	doGet(req, resp);
	}

}