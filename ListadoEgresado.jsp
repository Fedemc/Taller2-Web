<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id='listadoEgresado' scope='request' class='java.util.ArrayList' />

<html>
<head>
<title>Listado de Egresados</title>
</head>
<body>
<p>Listado de Egresado</p>

<table border="1" cellspacing="1" width="30%">
	<c:forEach items="${listadoEgresado}" var="i">
		<tr>
			<td>Cedula: ${i.cedula}</td>
			<td>Nombre: ${i.nombre}</td>
			<td>Apellido: ${i.apellido}</td>
		</tr>
	</c:forEach>
</table>

<p><a href='Principal.jsp'>Volver al ingreso</a></p>
</body>
</html>