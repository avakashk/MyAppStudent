<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="asearch" method="post">
		<fieldset>
			<legend>Search for an item</legend>
			<label>Keyword : <br /> <input type="text" name="keyword"></label><br>

			<input type="radio" name="provider" value="Google">Google</input><br>

			<input type="radio" name="provider" value="Yahoo">Yahoo</input><br> 
			<input	type="radio" name="provider" value="Bing">Bing</input><br> 
			<input 	type="submit" value="Search" />
		</fieldset>
	</form>

</body>
</html>