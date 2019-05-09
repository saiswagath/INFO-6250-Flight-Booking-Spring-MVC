<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

var xmlHttp;
xmlHttp = GetXmlHttpObject();

function checkSeats()
{
	if (xmlHttp == null)
    {
        alert("Your browser does not support AJAX!");
        return;
    }
	//alert("hi");
    var check = document.getElementById("link").href;   
    var flightId = check.split('=');
    //alert(flightId[1]);
    var query = "flid="+flightId[1];
    
    xmlHttp.onreadystatechange = function stateChanged()
    {
    	
        if (xmlHttp.readyState == 4)
        {
        	document.getElementById("error").innerHTML=xmlHttp.responseText;
			var node = document.getElementById("error");
    		
    		var txtContent = node.textContent;
    		
    		//alert(txtContent);
    		
        	if(txtContent=="Seats are available")
        	{
        		location.href=("viewCart.htm");
        	}
        }
    };
    xmlHttp.open("POST", "addToCart.htm", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(query);
 	return false;
 	
	
}

	
	
	 function GetXmlHttpObject()
     {
         var xmlHttp = null;
         try
         {
             // Firefox, Opera 8.0+, Safari
             xmlHttp = new XMLHttpRequest();
         } catch (e)
         {
             // Internet Explorer
             try
             {
                 xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
             } catch (e)
             {
                 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             }
         }
         return xmlHttp;
     }
     


</script>
</head>
<body>
<div id="content">

<c:choose>
            <c:when test="${!empty sessionScope.username}">
                <jsp:include page="menu.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu2.jsp"/>
            </c:otherwise>
</c:choose>

        <h2>Following flights are available</h2>
            <table border="1">
            <tr>
            	<th>Flight Number</th>
                <th>Flight Name</th>
                <th>Airplane Id</th>
                <th>Price</th>
                <th>Departure time</th>
                <th>Destination arrival time </th>
                <th>Available Seats</th>
            </tr>
		<c:forEach var="flight" items="${sessionScope.flightlist}">
                <tr>
                    <td>${flight.flight_id}</td>
                    <td>${flight.flight_name}</td>
                    <td>${flight.airplane_id}</td>
                    <td>$${flight.amount}</td>
                    <td>${flight.deptTime}</td>
                    <td>${flight.arrivalTime}</td>
                    <td>${flight.availableSeats}</td>
                    <td><a href="addToCart.htm?fid=${flight.flight_id}"  id="link">Book Ticket</a></td>
                </tr>   
            </c:forEach>
        </table>
                    <div id="error" style="color:red"></div>
</div>       
</body>
</html>