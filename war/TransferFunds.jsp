<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Business Bank</title>
<script>
function toggle(){
	 var fa = document.getElementById("fromaccount").value;
	 var ta = document.getElementById("toaccount").value;
	 var am = document.getElementById("amount").value;
	 var regexp=/\d{12}/;
	 var patt=/^[1-9]\d*(\.\d)?$/;
	 if(fa.match(regexp)==null)
		 document.getElementById("error").innerHTML="Please enter valid Account numbers";
	 else if(ta.match(regexp)==null)
		 document.getElementById("error").innerHTML="Please enter valid Account numbers";
	 else if(fa==ta)
		 document.getElementById("error").innerHTML="Please enter valid Account numbers";
	 else if(am.match(patt)==null)
		 document.getElementById("error").innerHTML="Please enter valid Amount";
	 else {document.getElementById("error").innerHTML="";
		   document.getElementById("check").style.visibility="hidden";
	       document.getElementById("transfer").style.visibility="visible";}
	}
function makeVisible(){
	document.getElementById("error").innerHTML="";
	document.getElementById("check").style.visibility="visible";
    document.getElementById("transfer").style.visibility="hidden";
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

<h2 class="title">Fund Transfer</h2>

<div class="entry">

<h3 class="title">To transfer money from your account fill the details carefully and click on submit</h3>
<form name="form1" action="/transferFunds" method="get"  >

<table>
<tr>
<td class="status" colspan="2">
<label id="error" name="error"></label></td></tr>
<tr>
<td><label>From Account</label></td>
<td><input type="text" name="fromaccount" id="fromaccount" size="12" onFocus="makeVisible();"/></td>
</tr>
<td><label>To Account</label></td>
<td><input type="text" name="toaccount" id="toaccount" size="12" onFocus="makeVisible();"/></td>
</tr>
<tr>
<td><label>Amount</label></td>
<td><input type="text" name="amount" id="amount" size="12" onFocus="makeVisible();"/></td>
</tr>
<tr>
<td colspan="2"><input id="check" name="check" type="button" 
                 value="Submit" onclick="toggle();"/>
                 <input id="transfer" name="transfer" type="submit" value="Transfer"
                       style="visibility:hidden;"/></td>			
</tr>
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





