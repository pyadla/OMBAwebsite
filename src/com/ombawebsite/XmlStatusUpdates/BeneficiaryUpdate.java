package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;

import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.ItemFiles.Account;
import com.ombawebsite.ItemFiles.Beneficiary;
import com.ombawebsite.DaoFiles.BeneficiaryDao;

@SuppressWarnings("serial")
public class BeneficiaryUpdate extends HttpServlet {
	
	String s1,strTo;
	int flag;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/xml");
		flag=0;
		    strTo="";
            HttpSession session=req.getSession(true);
            String customerId=req.getParameter("customerId");
            session.setAttribute("customerId",customerId);
			String beneficiaryAccountNo=req.getParameter("benacc");
			s1=checkBeneficiaryAccountNo(beneficiaryAccountNo,(String)session.getAttribute("customerId"));
			String beneficiaryName=req.getParameter("name");
			String beneficiaryBankCode =req.getParameter("bcode");
			
			List<Beneficiary> beneficiaries=BeneficiaryDao.INSTANCE.listBeneficiaries();
        	for(Beneficiary b:beneficiaries){
        		if((b.getCustomerId()).equals((String)session.getAttribute("customerId")))
        			if(b.getBeneficiaryAccountNo().equals(beneficiaryAccountNo)){
        				s1="Beneficiary already exists";
        				flag=1;
        			}
        	}
        	
        	resp.getWriter().println("<StatusUpdate><Status>");
        if(flag==0){
			BeneficiaryDao.INSTANCE.add((String)session.getAttribute("customerId"),beneficiaryAccountNo,
					beneficiaryName,beneficiaryBankCode);
			resp.getWriter().println("Beneficiary details are added");
			if (strTo == null)
				try {
					throw new Exception("To field cannot be empty.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
            //Trim the stuff
            strTo = strTo.trim();
            if (strTo.length() == 0)
				try {
					throw new Exception("To field cannot be empty.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            //Call the GAEJ Email Service
            Properties props = new Properties();
            Session session1 = Session.getDefaultInstance(props, null);
            MimeMessage msg = new MimeMessage(session1);
            try {
				msg.setFrom(new InternetAddress("purnima891@gmail.com"));			
            msg.addRecipient(MimeMessage.RecipientType.TO,
            new InternetAddress(strTo));
            msg.setSubject("New Beneficiary added");
            msg.setText(beneficiaryName+" with account number "+beneficiaryAccountNo+" has been added to your list of beneficiaries");
            Transport.send(msg);
            } catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        else resp.getWriter().println(s1);
        resp.getWriter().println("</Status></StatusUpdate>");
  }

	
	private String checkBeneficiaryAccountNo(String s,String c) {
		List<Account> a=AccountDao.INSTANCE.listAccounts();
	       	for(Account acc:a){
        		if((acc.getAccountNo()).equals(s)){	        			
        			if((acc.getCustomerId()).equals(c))
        			{
        				flag=1;
        				return "Corresponding account belongs to same customer";
        			}
        			else return "";
        		}
        	}
  		   return "";		
	}
}
		

