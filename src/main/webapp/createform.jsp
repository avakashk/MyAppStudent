<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1> Fill The Details to Create Profile</h1>
<pre>
<form action="createprofile" method="post">

Regno: <input type="text" name="regno"/><br>
First Name: <input type="text" name="fname"/><br>
Last Name: <input type="text" name="lname"/><br>
Guardian First Name: <input type="text" name="gfname"/><br>
Guardian Last Name: <input type="text" name="glname"/><br>
Password: <input type="text" name="password"/><br>
Admin : <select name="admin">
<option value="N">No</option>
<option value="Y">Yes</option>
</select><br>

<input type="submit" value="Register"/><br>
<input type="reset" value="Reset"/>




</form>




</pre>

</body>
</html>