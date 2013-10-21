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
<script>
function toggle(){
	 var s3 = document.getElementById("s3").value;
	 var e3 = document.getElementById("e3").value;
	 var ddllist= document.getElementById("s2");
	 var s2= ddllist.options[ddllist.selectedIndex].value;
	 ddllist= document.getElementById("s1");
	 var s1= ddllist.options[ddllist.selectedIndex].value;
	 ddllist= document.getElementById("e1");
	 var e1= ddllist.options[ddllist.selectedIndex].value;
	 ddllist= document.getElementById("e2");
	 var e2= ddllist.options[ddllist.selectedIndex].value;
	 var sd=s3+s2+s1;
	 var ed=e3+e2+e1;
	 var ele = document.getElementsByClassName("ele");
	 var regexp=/^[12][0-9]{3}/;
	 var i=0;
	 var flag=0;
	 if(s3.match(regexp)==null||e3.match(regexp)==null){
		    flag=1;
			document.getElementById("error").innerHTML="Please enter valid Dates";
	    }
	 if(((e1=="29"||e1=="30"||e1=="31")&&e2=="02")||((s1=="29"||s1=="30"||s1=="31")&&s2=="02")){
			flag=1;
			document.getElementById("error").innerHTML="Please enter valid Dates";
		}
	 if((e1=="31"&&(e2=="04"||e2=="06"||e2=="09"||e2=="11"))||(s1=="31"&&(s2=="04"||s2=="06"||s2=="09"||s2=="11")))
	    {
			flag=1;
			document.getElementById("error").innerHTML="Please enter valid Dates";
		}
	 if(sd>ed){
			flag=1;
			document.getElementById("error").innerHTML="Please enter valid Dates";
		}
	 if(flag==0){
	   document.getElementById("error").innerHTML="";
	   document.getElementById("check").style.visibility="hidden";
	   document.getElementById("acc").style.visibility="visible";
	 }
}
function makeVisible(){
	document.getElementById("error").innerHTML="";
	document.getElementById("check").style.visibility="visible";
	document.getElementById("acc").style.visibility="hidden";
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

<h2 class="title">Transaction History</h2>

<div class="entry">

<h3 class="title">To have a transaction history select start and end dates and click on the account number</h3>
<br>
<form name="form1" action="/transaction" method="get">
<table>
<tr>
<td class="status" colspan="2"><label id="error" name="error"></label></td>	
</tr>
<tr>		<td><label >Start Date: </label></td>
			<td >  
			<select name="s1" id="s1" onChange="makeVisible();">
        	<option selected>01</option><option>02</option><option>03</option>
           	<option>04</option><option>05</option><option>06</option>
            <option>07</option><option>08</option><option>09</option>
            <option>10</option><option>11</option><option>12</option>
            <option>13</option><option>14</option><option>15</option>
           	<option>16</option><option>17</option><option>18</option>
            <option>19</option><option>20</option><option>21</option>
            <option>22</option><option>23</option><option>24</option>
            <option>25</option><option>26</option><option>27</option>
           	<option>28</option><option>29</option><option>30</option>
            <option>31</option>
        </select>
        <select name="s2" id="s2" onChange="makeVisible();">
        	<option selected>01</option><option>02</option><option>03</option>
           	<option>04</option><option>05</option><option>06</option>
            <option>07</option><option>08</option><option>09</option>
            <option>10</option><option>11</option><option>12</option>      
        </select>
        <input type="text" id="s3" name="s3" size="4" onFocus="makeVisible();"/>
     </td>  
</tr>
<tr>
	<td><label >End Date  : </label></td>
		<td>  
			<select name="e1" id="e1" onChange="makeVisible();">
        	<option selected>01</option><option>02</option><option>03</option>
           	<option>04</option><option>05</option><option>06</option>
            <option>07</option><option>08</option><option>09</option>
            <option>10</option><option>11</option><option>12</option>
            <option>13</option><option>14</option><option>15</option>
           	<option>16</option><option>17</option><option>18</option>
            <option>19</option><option>20</option><option>21</option>
            <option>22</option><option>23</option><option>24</option>
            <option>25</option><option>26</option><option>27</option>
           	<option>28</option><option>29</option><option>30</option>
            <option>31</option>
        </select>
        <select name="e2" id="e2" onChange="makeVisible();">
        	<option selected>01</option><option>02</option><option>03</option>
           	<option>04</option><option>05</option><option>06</option>
            <option>07</option><option>08</option><option>09</option>
            <option>10</option><option>11</option><option>12</option>      
        </select>
        <input type="text" id="e3" name="e3" size="4" onFocus="makeVisible();"/>
     </td>  
</tr>
<tr><td colspan=2><input id="check" name="check" type="button" 
                 value="Submit" onclick="toggle();"/></td></tr>

<tr>
<td><input name="acc" id="acc" type="submit" value="Submit" style="visibility: hidden;"/></td>	
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



