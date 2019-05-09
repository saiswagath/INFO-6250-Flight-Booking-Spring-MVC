<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<h3>Admin Page</h3>
<a href = "addAirplane.htm"> Add Airplane</a><br/><br/>
<a href = "deleteAirplane.htm">Delete Airplane</a><br/><br/>
<a href="ListFlights.htm">View and Edit Flights</a><br/><br/>
<a href="viewpassengers.htm">View Passenger List </a><br/><br/>

</body>
</html>