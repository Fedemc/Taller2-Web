<html><head>
<title>Listado de Egresados</title>
</head><body bgcolor="#FFFFCC">
<h2><b><ins>Listado de egresados</ins></b></h2>
<p/>

<table border="1" cellspacing="1" width="30%">
	<c:forEach items="${listadoEgresado}" var="i">
		<tr>
			<td>Cedula: ${i.cedula}</td>
			<td>Nombre: ${i.nombre}</td>
			<td>Apellido: ${i.apellido}</td>
		</tr>
	</c:forEach>
</table>

<p><a href="Principal.jsp">Volver al ingreso</a></p>
</body></html>