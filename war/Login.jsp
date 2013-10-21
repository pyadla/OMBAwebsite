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
<body>
	<div id="page">
	<div id="page-bgtop">


<div id="sidebar">
<ul>
<h2>Customer Login</h2>
<li>
<%if(session.getAttribute("login").toString()=="0"||session.getAttribute("login")==""){ 
     if(Integer.parseInt(session.getAttribute("loginAttempt").toString())<2||session.getAttribute("loginAttempt").toString()==""){%>
<form name="login" method="get" action="/login">
<table>

<tr>
<td class="status" colspan="2" name="cidstatus" id="cidstatus"><%=session.getAttribute("cs").toString()%></td>			
</tr>

<tr>
<td><label>CustomerID</label></td>
<td><input type="text" name="customerId" id="customerId" size="10"/></td>
</tr>

<tr>
<td class="status" colspan="2" name="pwdstatus" id="pwdstatus"><%=session.getAttribute("ps").toString()%></td>			
</tr>

<tr>
<td><label>Password</label></td>
<td><input type="password" name="password" id="password" size="10"/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Submit"/></td>		
</tr>

</table>
</form>
<%} else{ %>
<form name="login" method="get" action="/SQ">
<table>

<tr>
<td class="status" colspan="2" name="status" id="status"><%=session.getAttribute("cs").toString() %></td>			
</tr>
<tr>
<td colspan="2"><label><%=session.getAttribute("q").toString() %></label></td>
</tr>
<tr>
<td><label>Enter Answer</label></td>
<td><input type="text" name="answer" id="answer" size="10"/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Submit"/></td>		
</tr>

</table>
</form>
<%} %>
<%} else{ %>
   <a href="afterlogin.html" target="_top"><h2>Go To Menu</h2></a><br><br>
	<form method="get" action="/logout">
	<table><tr><td>
	        <input type="submit" value="Logout" />
	       </td></tr>
	</table>
	</form>
<% } %>
</li>

<h2>Locate Us</h2>
	<li>
		<ul>
		<li><a href="atmbranch.html">ATM</a></li>
		<li><a href="atmbranch.html">Branch</a></li>
		</ul>
	</li>
	<li>
	<p>For downloading mobile application-  <a href="">click here</a></p>
	</li>

</ul>
</div>
		<!-- end #sidebar -->    
</div>
</div>

</body>
</html>


