<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ombawebsite.ItemFiles.*" %>
<%@page import="com.ombawebsite.DaoFiles.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Collections" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Business Bank</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<div id="page">
	<div id="page-bgtop">

<% if(session.getAttribute("login").toString()=="0"||
     session.getAttribute("login")==""||
     session.getAttribute("login")==null){
     response.sendRedirect("Login.jsp");} %>

<div id="content">
<div class="post">

<h2 class="title">Mini Statement</h2>

<div class="entry">

<h3 class="title">To have a Mini Statement select an account number</h3>

<form name="form1" action="/ministatement" method="get">
<table>
<% List<String> accList=new ArrayList<String>(); 
List<Account> accounts=AccountDao.INSTANCE.listAccounts(); 
for(Account a:accounts)
	if((session.getAttribute("customerId")).toString()==(a.getCustomerId()))
       accList.add(a.getAccountNo());
    Collections.sort(accList);
    for(String s:accList){ %>
<tr>
<td><input name="acc" type="submit" value="<%=s%>"/></td>	
</tr>
<% } %>
</table>
</form>


</div>

		<!-- end #content -->    

</div>
</div>
</div>
</div>

</body>
</html>



