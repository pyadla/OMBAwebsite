package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.*;


import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Session;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.DaoFiles.CustomerDao;
import com.ombawebsite.DaoFiles.TransactionDao;
import com.ombawebsite.ItemFiles.Account;
import com.ombawebsite.ItemFiles.Customer;

import java.util.Date;


@SuppressWarnings("serial")
public class TransferFundsUpdate extends HttpServlet {
	
	String fromaccount,toaccount,amount,strTo;
	DateFormat dateFormat,timeFormat;
	Date date,time;
	int flag;
	int ben;	
	HttpSession session;
	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		    resp.setContentType("text/xml");
		    session=req.getSession(true);
		    
			flag=0;
			ben=0;
			strTo="";
			
			String customerId=req.getParameter("customerId");
			session.setAttribute("customerId",customerId);
		    fromaccount=(req.getParameter("fromaccount"));
            toaccount=(req.getParameter("toaccount"));
			checkBankCode(toaccount);
			amount=req.getParameter("amount");			
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    date = new Date();
		    timeFormat = new SimpleDateFormat("hh-mm-ss");
		    time = new Date();
		    String bal= checkBalance(amount,fromaccount,toaccount);
		    	if(flag==0){
	                  resp.getWriter().println("<StatusUpdate><Status>"+amount+" has been transfered from"+
	                                                  fromaccount+" to "+toaccount+
	                                                  "</Status></StatusUpdate>");
	                  }
		    	  
		    	else resp.getWriter().println("<StatusUpdate><Status>"+bal+"</Status></StatusUpdate>");
		    	
		    	String tocustomer="",fromcustomer="",strCallResult="",fstrTo="",tstrTo="";
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
                            if (strTo == null)
								try {
									throw new Exception("To field cannot be empty.");
								} catch (Exception e) {
									// TODO Auto-generated catch block
								//	e.printStackTrace();
								}
                            try {
                               //Trim the stuff
                               strTo = strTo.trim();
                               if (strTo.length() == 0) throw new Exception("To field cannot be empty.");
                               //Call the GAEJ Email Service
                               Properties props = new Properties();
                               Session session1 = Session.getDefaultInstance(props, null);
                               MimeMessage msg = new MimeMessage(session1);
                               msg.setFrom(new InternetAddress("purnima891@gmail.com"));
                               msg.addRecipient(MimeMessage.RecipientType.TO,
                               new InternetAddress(strTo));
                               msg.setSubject("Transfer alert");
                               msg.setText(amount+" has been transfered from"+
                                                fromaccount+" to "+toaccount);
                               Transport.send(msg);
                               strCallResult = "Success: " + "Email has been delivered.";
                            }
                       catch (Exception e1) {
								// TODO Auto-generated catch block
							//	e1.printStackTrace();
							}}                      
                   
                   
             else{
                for(Account acc:accounts){
                   
                  if(acc.getAccountNo().equals(fromaccount))
                  {
                          
                              String customer=acc.getCustomerId();
                         //  List<Customer> cus=CustomerDao.INSTANCE.listCustomers();
                           for(Customer c:cus){
                               if(c.getCustomerId().equals(customer))
                               {
                                    fstrTo=c.getEmailAddress();
                                    if (fstrTo == null)
										try {
											throw new Exception("To field cannot be empty.");
										} catch (Exception e) {
											// TODO Auto-generated catch block
										//	e.printStackTrace();
										}
                                      
                                     //Trim the stuff
                                     fstrTo = fstrTo.trim();
                                     if (fstrTo.length() == 0)
										try {
											throw new Exception("To field cannot be empty.");
										} catch (Exception e) {
											// TODO Auto-generated catch block
										//	e.printStackTrace();
										}
                                     //Call the GAEJ Email Service
                                     Properties props = new Properties();
                                     Session session1 = Session.getDefaultInstance(props, null);
                                     MimeMessage msg = new MimeMessage(session1);
                                     try {
										msg.setFrom(new InternetAddress("purnima891@gmail.com"));
									
                                     msg.addRecipient(MimeMessage.RecipientType.TO,
                                     new InternetAddress(fstrTo));
                                     msg.setSubject("email alert");
                                     msg.setText(amount+" has been transfered from"+
                                                fromaccount+" to "+toaccount);
                                     Transport.send(msg);
                                     } catch (AddressException e) {
											// TODO Auto-generated catch block
											//e.printStackTrace();
										} catch (MessagingException e) {
											// TODO Auto-generated catch block
										//	e.printStackTrace();
										}
                                     strCallResult = "Success: " + "Email has been delivered.";
                                    
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
                                    if (tstrTo == null)
										try {
											throw new Exception("To field cannot be empty.");
										} catch (Exception e) {
											// TODO Auto-generated catch block
									//		e.printStackTrace();
										}
                                      
                                     //Trim the stuff
                                     tstrTo = tstrTo.trim();
                                     if (tstrTo.length() == 0)
										try {
											throw new Exception("To field cannot be empty.");
										} catch (Exception e) {
											// TODO Auto-generated catch block
										//	e.printStackTrace();
										}
                                     //Call the GAEJ Email Service
                                     Properties props = new Properties();
                                     Session session2 = Session.getDefaultInstance(props, null);
                                     MimeMessage msg = new MimeMessage(session2);
                                     try {
										msg.setFrom(new InternetAddress("purnima891@gmail.com"));
									
                                     msg.addRecipient(MimeMessage.RecipientType.TO,
                                     new InternetAddress(tstrTo));
                                     msg.setSubject("email alert");
                                     msg.setText(amount+" has been transfered from"+
                                                fromaccount+" to "+toaccount);
                                     Transport.send(msg);
                                     } catch (AddressException e) {
											// TODO Auto-generated catch block
											//e.printStackTrace();
										} catch (MessagingException e) {
											// TODO Auto-generated catch block
											//e.printStackTrace();
										}
                                     
                                    
                               }
                           }                    
                           
                  }
               }
           
             }
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
        				                TransactionDao.INSTANCE.add((String)session.getAttribute("customerId"),fromaccount,
        				                		  (dateFormat.format(date)).toString(),
        				                		  (timeFormat.format(time)).toString(),
        				                		  toaccount, amount,Bal,toBal,"mobile transfer"); 
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
                        	   TransactionDao.INSTANCE.add((String)session.getAttribute("customerId"),fromaccount,
                        			   (dateFormat.format(date)).toString(),(timeFormat.format(time)).toString(),
                        			   toaccount, amount,Bal,"unavailable","mobile transfer"); 
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

	



