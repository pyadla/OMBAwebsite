package com.ombawebsite.XmlStatusUpdates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;

import com.ombawebsite.ItemFiles.Beneficiary;
import com.ombawebsite.DaoFiles.BeneficiaryDao;

@SuppressWarnings("serial")
public class GetBeneficiaryList extends HttpServlet {
	
	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		    resp.setContentType("text/xml");
		    PrintWriter out=resp.getWriter();
		    
            HttpSession session=req.getSession(true);
			String customerId=req.getParameter("customerId");
			
			out.println("<Beneficiaries>");
			
			List<Beneficiary> beneficiaries=BeneficiaryDao.INSTANCE.listBeneficiaries();
        	for(Beneficiary b:beneficiaries){
        		if((b.getCustomerId()).equals(customerId)){
        			out.println("<Beneficiary>");
        			out.println("<Name>"+b.getBeneficiaryName()+"</Name>");
        			out.println("<AccountNo>"+b.getBeneficiaryAccountNo()+"</AccountNo>");
        			out.println("<BankCode>"+b.getBeneficiaryBankCode()+"</BankCode>");
        			out.println("</Beneficiary>");
        		}
        	}
        	out.println("</Beneficiaries>");
        	out.close();
     }
}
		

