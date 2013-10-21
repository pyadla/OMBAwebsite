<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Business Bank</title>
<script>
function toggle(){
	 var bn = document.getElementById("beneficiaryName").value;
	 var bc = document.getElementById("beneficiaryBankCode").value;
	 var ba = document.getElementById("beneficiaryAccountNo").value;
	 var regexp=/\d{12}/;
	 var regex=/\d{8}/;
	 var patt=/^[A-Za-z ]+/;
	 if(bn.match(patt)==null)
		 document.getElementById("error").innerHTML="Please enter valid name";
	 else if(bc.match(regex)==null)
		 document.getElementById("error").innerHTML="Please enter valid bank code";
	 else if(ba.match(regexp)==null)
		 document.getElementById("error").innerHTML="Please enter valid Account number";
	 else {document.getElementById("error").innerHTML="";
		   document.getElementById("check").style.visibility="hidden";
	       document.getElementById("add").style.visibility="visible";}
	}
function makeVisible(){
	document.getElementById("error").innerHTML="";
	document.getElementById("check").style.visibility="visible";
    document.getElementById("add").style.visibility="hidden";
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
<h2 class="title">Add New Beneficiary account</h2>

<div class="entry">

<h3 class="title">To add a new beneficiary to your account fill the details and click on submit</h3>

<form name="form1" action="/addBeneficiary" method="get" >

	<table>
		<tr>
			<td class="status" colspan="2">
			<label name="error" id="error"></label>
			</td>
		</tr>
		<tr>
			<td><label>Beneficiary Name</label></td>
			<td><input type="text" name="beneficiaryName" id="beneficiaryName" size="20"
			           onFocus="makeVisible();"/></td>
		</tr>
		<tr><td><label>Bank Code</label></td>
			<td><input type="text" name="beneficiaryBankCode" id="beneficiaryBankCode" size="20"
			           onFocus="makeVisible();"/></td>
		</tr>
		<tr>
			<td><label>Beneficairy Account No</label></td>
			<td><input type="text" name="beneficiaryAccountNo" id="beneficiaryAccountNo" size="20"
			           onFocus="makeVisible();"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" value="Submit" name="check" id="check" onclick="toggle();"/>
			<input type="submit" value="Add" name="add" id="add" style="visibility:hidden;"/></td>			
		</tr>
	</table>
</form>
</div>
</div>

</div>
</div>

</body>
</html>