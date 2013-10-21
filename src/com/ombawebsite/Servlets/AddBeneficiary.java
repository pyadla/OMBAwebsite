package com.ombawebsite.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.ItemFiles.Account;
import com.ombawebsite.ItemFiles.Beneficiary;
import com.ombawebsite.DaoFiles.AccountDao;
import com.ombawebsite.DaoFiles.BeneficiaryDao;

@SuppressWarnings("serial")
public class AddBeneficiary extends HttpServlet {
	
	public static String s1="";
	int flag;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		flag=0;
		HttpSession session;
		session=req.getSession(true);
		if((session.getAttribute("login").toString()).equals("1")){ 
            
			String beneficiaryAccountNo=req.getParameter("beneficiaryAccountNo");
			s1=checkBeneficiaryAccountNo(beneficiaryAccountNo,(String)session.getAttribute("customerId"));
			String beneficiaryName=req.getParameter("beneficiaryName");
			String beneficiaryBankCode =req.getParameter("beneficiaryBankCode");
			
			List<Beneficiary> beneficiaries=BeneficiaryDao.INSTANCE.listBeneficiaries();
        	for(Beneficiary b:beneficiaries){
        		if((b.getCustomerId()).equals((String)session.getAttribute("customerId")))
        			if(b.getBeneficiaryAccountNo().equals(beneficiaryAccountNo)){
        				s1="Beneficiary already exists";
        				flag=1;
        			}
        	}
        	
        	resp.getWriter().println("<html><body style=\"border-style:solid;"+
                    "border-width: 55px;"+
                    "border-color:#FFFFFF;\">");
        if(flag==0){
			BeneficiaryDao.INSTANCE.add((String)session.getAttribute("customerId"),beneficiaryAccountNo,
					beneficiaryName,beneficiaryBankCode);
			resp.getWriter().println("<h3 style=\"color:#1975D1\">"+
					                 "Beneficiary details are added</h3>");
        }

        else resp.getWriter().println(" "+s1);
        resp.getWriter().println("</body></html>");
	} else resp.sendRedirect("/Login.jsp"); 
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
		

