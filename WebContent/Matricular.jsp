<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="MatricularController" method="POST">
Nombre:<input type="text" name="nm"/><br>
Apellidos:<input type="text" name="sn"/><br>
Nif:<input type="text" name="nif"/><br>
Fecha Nac.:<input type="date" name="fn"/><br>
<input type="submit" value="GUARDAR"/>
</form>
</body>
</html>