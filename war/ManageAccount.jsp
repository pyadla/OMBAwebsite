<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Business Bank</title>
<script>
function verify(){
	var vis1=document.getElementById('phoneno').style.visibility;
	var val = document.getElementById('phoneno').value;
	var regexp=/\d{10}/;
	var flag=0;
	if(vis1=='visible'&&val.match(regexp)==null){
		document.getElementById("error").innerHTML="Please enter valid phone number";
		flag=1;}
	var vis2=document.getElementById('address').style.visibility;
	val = document.getElementById('address').value;
	if(vis2=='visible'&&val==""){
		document.getElementById("error").innerHTML="Please enter valid address";
		flag=1;}
	var vis3=document.getElementById('email').style.visibility;
	val = document.getElementById('email').value;
	regexp=/^[a-zA-Z]\w*[\._]*\w*@[a-zA-Z]+\.[a-zA-Z]+/;
	if(vis3=='visible'&&val.match(regexp)==null){
		document.getElementById("error").innerHTML="Please enter valid email address";
		flag=1;}
	if(vis1=='hidden'&&vis2=='hidden'&&vis3=='hidden'){
		document.getElementById("error").innerHTML="Please choose one of the options";
		flag=1;}
	if(flag==0){
		   document.getElementById("error").innerHTML="";
		   document.getElementById("check").style.visibility="hidden";
	       document.getElementById("change").style.visibility="visible";
	}
}
function toggle(value){
	 var vis=document.getElementById(value).style.visibility;
	 if(vis=='hidden')
	 document.getElementById(value).style.visibility='visible';
	 else document.getElementById(value).style.visibility='hidden';
	 document.getElementById("check").style.visibility="visible";
     document.getElementById("change").style.visibility="hidden";
	}
</script>
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

<h2 class="title">Change Contact Details</h2>

<div class="entry">

<h3 class="title">To edit your contact information fill the details and click on submit</h3>

<form name="form1" action="/manageAccount" method="get">

<table>
<tr>
<td class="status" colspan="2">
<label id="error" name="error"></label></td></tr>
<tr>
<td><input type="checkbox" name="cbg" onclick="toggle('phoneno');">Phone Number</td>
<td><input type="text" name="phoneno" id="phoneno" size="10" style="visibility: hidden;"/></td>
</tr>

<tr>
<td><input type="checkbox" name="cbg" onclick="toggle('address');">Address</td>
<td><textarea id="address" name="address" rows="4" cols="25" style="visibility: hidden;"></textarea></td>
</tr>

<tr>
<td><input type="checkbox" name="cbg" onclick="toggle('email');">Email id</td>
<td><input type="text" id="email" name="email" id="email" size="20" style="visibility: hidden;"/></td>
</tr>

<tr>
<td colspan="2"><input id="check" name="check" type="button" 
                 value="Check" onclick="verify();"/>
                 <input id="change" name="change" type="submit" value="Submit"
                       style="visibility:hidden;"/></td>
</tr>

</table>
</form>
</div>
</div>
</div>
<!-- end #content -->    
</div>
</div>

</body>
</html>