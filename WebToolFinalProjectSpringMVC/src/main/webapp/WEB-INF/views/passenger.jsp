<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passenger Details</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<style>
input:required:focus {
  border: 1px solid red;
  outline: none;
}
</style> 
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 
	    <script>
	   		$(function(){
	   	    $("#dob").datepicker({
	   	        numberOfMonths: 2, 
	   	        maxDate:'0'
	   	})
	   		});

	   		function validateEmail(email) {
	   		  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	   		  return re.test(email);
	   		}

	   		function validate() {
	   		  $("#result").text("");
	   		  var email = $("#email").val();
	   		  if (validateEmail(email)) {
	   		    $("#result").text(email + " is valid :)");
	   		    $("#result").css("color", "green");
	   		  } else {
	   		    $("#result").text(email + " is not valid :(");
	   		    $("#result").css("color", "red");
	   		  }
	   		  return false;
	   		}

	   		$("form").bind("submit", validate);
	   		
	   		
	   		
	   </script>

</head>
<body>
<h1>Please enter all details correctly</h1>
<form:form action="passenger.htm" commandName="passenger" method="post">
<table>
<c:forTokens items="i" delims="${sessionScope.noOfTravellers}">
<tr>
    <td>First Name:</td>
    <td><form:input type="text" path="firstName" size="30" /> <font color="red"><form:errors path="firstName"/></font></td>
</tr>
<tr>
    <td>Last Name:</td>
    <td><form:input type="text" path="lastName" size="30"/> <font color="red"><form:errors path="lastName"/></font></td>
</tr>
<tr>
    <td>Gender</td>
    <td><form:radiobutton value="M" path="gender" checked="checked"/>Male
    	<form:radiobutton value="F" path="gender" />Female
     </td>
</tr>
<tr>
    <td>Email:</td>
    <td><form:input type="text" path="email" id='email' size="30"/> <font color="red"><form:errors path="email"/></font></td>
</tr>
<tr>
    <td>Date of Birth:</td>
    <td><form:input type="text" path="dob" id="dob" size="30"/> <font color="red"><form:errors path="dob"/></font></td>
</tr>
<tr>
    <td>Phone Number:</td>
    <td><form:input type="text" path="phonenum" size="30" /> <font color="red"><form:errors path="phonenum"/></font></td>
</tr>
<tr>
    <td>Address:</td>
    <td><form:input type="textarea" path="address" size="30"/> <font color="red"><form:errors path="address"/></font></td>
</tr>
</c:forTokens>
<tr>
    <td colspan="2"><input type="submit" value="Save Details and Enter payment details" /></td>
</tr>
</table>
</form:form>
</body>
</html>