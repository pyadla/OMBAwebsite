package com.ombawebsite.XmlStatusUpdates;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ombawebsite.DaoFiles.SchedTransDao;

@SuppressWarnings("serial")
public class ScheduleTransferUpdate extends HttpServlet {
	String fromaccount,toaccount,amount,customerId,times,strTo,fstrTo,tstrTo,fromcustomer,tocustomer;
	DateFormat dateFormat,timeFormat;
	Date date,time;
	String date1;
	int flag,update=0;
	int ben;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/xml");
		 HttpSession session;
		    session=req.getSession(true);
		    String customerId=req.getParameter("customerId");
            session.setAttribute("customerId",customerId);
		      date1=req.getParameter("date");
		    fromaccount=(req.getParameter("fromaccount")); 
            toaccount=(req.getParameter("toaccount"));
			amount=req.getParameter("amount");	
			//strTo=req.getParameter("toaddress");	
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    date = new Date();
             resp.getWriter().println("<StatusUpdate><Status>");
     			SchedTransDao.INSTANCE.add((String)session.getAttribute("customerId"),fromaccount,toaccount,amount,date1);
     			resp.getWriter().println("Your transfer will be updated soon");
             resp.getWriter().println("</Status></StatusUpdate>");
             
		    }
}	