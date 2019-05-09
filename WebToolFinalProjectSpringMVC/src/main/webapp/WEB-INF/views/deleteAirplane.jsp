<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Airplane</title>
<style>
input:required:focus {
  border: 1px solid red;
  outline: none;
}
</style> 
</head>
<body>
<form action="deleteAirplane.htm" method="post">
Please enter airplane_id : 
<input type="number" name="airplane_id" required/>
<input type="submit" value="Go" />
</form>
</body>
</html>