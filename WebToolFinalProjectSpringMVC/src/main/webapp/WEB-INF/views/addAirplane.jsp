<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Airplane</title>
<style>
input:required:focus {
  border: 1px solid red;
  outline: none;
}
</style> 
</head>
<body>
<form:form action="addAirplane.htm" commandName="airplane" method="post">
<pre>
Please enter the details

Airline Name: <form:input type="text" path="airlineName" name="airlineName" size="30" required="required"/>

Owner:        <form:input type="text" path="owner" name="owner" size="30" required="required" />

			  <input type="submit" value="Add Airplane" />
</pre>
</form:form>
</body>
</html>