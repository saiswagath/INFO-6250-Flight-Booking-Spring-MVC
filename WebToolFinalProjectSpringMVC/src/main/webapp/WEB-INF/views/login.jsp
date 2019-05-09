<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<style>
input:required:focus {
  border: 1px solid red;
  outline: none;
}
</style> 
</head>
<body>
<h3>Please enter your credentials</h3>
<form action="login.htm" method="post">

<table>
<tr>
    <td>UserName:</td>
    <td><input type="text" name="username" size="30" required/></td>
</tr>

<tr>
    <td>Password:</td>
    <td><input type="password" name="password" size="30"  required/></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="Login" /></td>
</tr>

</table>
</form>

<font color="red"><a href="index.htm"> Go Back to Main Page</a></font>

</body>
</html>