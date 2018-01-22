<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<ol>
<% 
	String[] ops = (String[])request.getAttribute("ops");
	for(int i=0;i<ops.length;i++){
%>
<li><a href="OpController?p=<%=i%>"><%=ops[i]%></a></li>
<% } %>
</ol>
</body>
</html>