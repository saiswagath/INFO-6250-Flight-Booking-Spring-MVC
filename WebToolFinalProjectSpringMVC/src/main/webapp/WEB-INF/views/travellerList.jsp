<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3> Below are the passengers details</h3>
<table border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>Passenger Name</th>
	<th>Passenger Email</th>
	<th>Flight Name</th>
	<th>Travelling From</th>
	<th>Destination</th>
	<th>Date of travel</th>
</tr>
<c:forEach var="passenger" items="${sessionScope.ticketList}">
<tr>
	<td>${passenger.traveller.firstName}</td>
	<td>${passenger.traveller.email}</td>
	<td>${passenger.flightInformation.flight_name}</td>
	<td>${passenger.flightInformation.from}</td>
	<td>${passenger.flightInformation.dest}</td>
    <td>${passenger.flightInformation.deptDate}</td>
</tr>
</c:forEach>
</table>
<h3><a href="adminHome.htm">Go Back to Menu Page</a></h3>
</body>
</html>