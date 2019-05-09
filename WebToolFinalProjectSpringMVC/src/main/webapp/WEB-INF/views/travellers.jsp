<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travellers Information</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<form action="passenger.htm" action="get">
Number of Travellers : <input type="number" name="noOfTravellers" required />
<input type="submit" value="Go"/>
</form>
</body>
</html>