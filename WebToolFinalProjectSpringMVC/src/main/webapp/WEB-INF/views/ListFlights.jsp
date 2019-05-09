<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Flights</title>
</head>
<body>
 <jsp:include page="menu.jsp"/>
 <a href="addflights.htm"> Add flight </a>


 <h3>List of flights are:</h3>
  <form action="updateFlights.htm" method="get">
<table border="1">
<tr>
					<th>Flight Id </th>
					<th>Airplane Id</th>
					<th>From</th>
					<th>Dest</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Travel Class</th>
					<th>Total Seats</th>
					<th>Amount</th>
					<th>Departure Date</th>
					<th>Arrival Date</th>
</tr>

<c:forEach var="flight" items="${sessionScope.listOfFlights}">
<tr>
					<td>${flight.flight_id}</td>
                    <td>${flight.airplane_id}</td>
                    <td>${flight.from}</td>
                    <td>${flight.dest}</td>
                    <td>${flight.deptTime}</td>
                    <td>${flight.arrivalTime}</td>
                    <td>${flight.travelClass}</td>
                    <td>${flight.totalSeats}</td>
                    <td>${flight.amount}</td>
                    <td>${flight.deptDate}</td>
                    <td>${flight.arrDate}</td>
                    <td><a href="updateFlights.htm?id=${flight.flight_id}&action=update">Update Flight</a></td>
                    <td><a href="deleteFlights.htm?id=${flight.flight_id}">Delete Flight</a></td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>