<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
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

<h2 class="title">Account Balance</h2>

<div class="entry">

<h3 class="title">To check your account balance select one of your account numbers</h3>

<form name="form1" action="/accountBalance" method="get">
<table>
<%! List<String> acc=new ArrayList<String>(); %>
<% acc=(List<String>)session.getAttribute("accList");
   for(String a:acc){
	   %>
<tr>
<td><input name="acc" type="submit" value="<%= a %>"/></td>	
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



