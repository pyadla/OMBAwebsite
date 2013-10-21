<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Business Bank</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body bgcolor="488ac7">
	<div id="page">
	<div id="page-bgtop">
<div id="sidebar1">


<% if(session.getAttribute("login").toString()=="0"||
     session.getAttribute("login")==""||
     session.getAttribute("login")==null){
     response.sendRedirect("Login.jsp");} %>


<form method="get" action="/logout">
	<input type="submit" value="Logout" />
</form>


<ul>
<h2>Check my account</h2>
<li>
<table>
<tr><td><form method="get" action="/accountBalance" target="dest">
	<input type="submit" value="Account Summary" />
</form></td></tr>
<tr><td><a href="TransactionHistory.jsp" target="dest">Transaction History</a></td></tr>
</table>
</li>
<h2>Fund Transfers</h2>
<li>
<table>
<tr><td><a href="AddBeneficiary.jsp" target="dest">Add Beneficiary</a></td></tr>
<tr><td><a href="TransferFunds.jsp" target="dest">Transfer money</a></td></tr>
</table>
</li>
<h2>Manage Account</h2>
<li>
<table>
<tr><td><a href="ManageAccount.jsp" target="dest">Change Contact Info</a></td></tr>
</table>
</li>
<h2>Locate us</h2>
<li>
<table>
<tr><td><a href="atmbranch.html" target="dest">ATM & Branch</a></td></tr>
</table>
</li>
</ul>
</div>
<!-- end #sidebar -->    
</div>
</div>


</body>
</html>