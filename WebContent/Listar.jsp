<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,model.cursos.Alumno"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><form action="ListarController" method="GET"><input type="text" name="valuesearchin" value="<%= request.getAttribute("valuesearch") %>"/><input type="submit" value="BUSCAR" /></form></p>
<table>
<thead>
<tr><th>Nombre</th><th>Apellidos</th><th>Dni</th><th>F. Nac</th><th>Acciones</th><th>Asistencia</th></tr>
</thead>
<tbody>
<% 
List<Alumno> alumnos = (List<Alumno>)request.getAttribute("alumnos");
for(int i=0;i<alumnos.size();i++){
	Alumno a = alumnos.get(i);
%>
<tr>
	<td><%= a.getNombre() %></td><td><%= a.getApellidos() %></td>
	<td><%= a.getNif() %></td><td><%= a.getBeatyDate() %></td><td><a href="RemoveController?nif=<%= a.getNif() %>">ELIMINAR</a></td><td></td>
</tr>
<% } %>
</tbody>
</table>
</body>
</html>