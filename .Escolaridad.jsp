<html><body bgcolor="#FFFFCC">
<h2><b><ins>Consulta de Escolaridad</ins></b></h2>

<p/>Escolaridad para ${datosAlumno.nombre} ${datosAlumno.apellido}
<p/>
<table border="1" cellspacing="1" width="30%">
	<c:forEach items="${escolaridadAlumno}" var="i">
		<tr>
			<td>Nro Inscripcion: ${i.numero}</td>
			<td>Asignatura: ${i.nombreAsignatura}</td>
			<td>Año lectivo: ${i.anioLectivo}</td>
			<td>Calificacion: ${i.calificacion}</td>
		</tr>
	</c:forEach>
</table>

<p><a href="Principal.jsp">Volver al ingreso</a></p>

</body></html>