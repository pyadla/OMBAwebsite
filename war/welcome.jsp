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


<% if(session.getAttribute("login").toString()=="0"||
     session.getAttribute("login")==""||
     session.getAttribute("login")==null){
     response.sendRedirect("Login.jsp");} %>
	<div id="content">
	<div class="post">
		
	<h3 style="font-size: 30px;
	           color: #488ac7;">Hello <%=session.getAttribute("gender").toString()%> <%=session.getAttribute("customerName").toString()%>! <br>
	Welcome to our online portal! <br>
	Choose the bank operation you want to perform from the sidebar on the left.
	</h3>
	
	</div>
	</div>
<!-- end #content -->
</div>
</div>

</body>
</html>




