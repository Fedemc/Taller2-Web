<html><head>
<title>Listado de Egresados</title>
</head><body>
<p>Listado de Egresado</p>

<table>
	<c:forEach items="${listadoEgresado}" var="i">
		<tr>
			<td>Cedula: ${listadoEgresado[i].cedula}</td>
			<td>Nombre: ${listadoEgresado[i].nombre</td>
			<td>Apellido: ${listadoEgresado[i].apellido</td>
		</tr>
	</c:forEach>
</table>

<p><a href="Principal.jsp">Volver al ingreso</a></p>
</body></html>