<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Page</title>
<style>
input:required:focus {
  border: 1px solid red;
  outline: none;
}
</style> 
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<form action="payment.htm" method="post">
	<pre>
	Credit Card Number <input type="text" name="creditCardNumber" value='${cookie.ccn.value}' required />
	Bank Name          <input type="text" name="bankName" required />
	Name as on card    <input type="text" name="fullName" required/>
	Expiration Month   <input type="number" name="expiration_month" required />
	Expiration Year    <input type="number" name="expiration_year" required/> 
	<input type="submit" value="Book Ticket" />
</pre>
</form>
</body>
</html>