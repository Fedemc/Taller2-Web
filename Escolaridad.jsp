<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='datosAlumno' scope='request' class='sistema.logica.valueObjects.VOAlumno' />
<<jsp:useBean id='escolaridadAlumno' scope='request' class='java.util.ArrayList'></jsp:useBean>

<html>
<head>
<title>Consulta de Escolaridad</title>
</head>
<body bgcolor="#FFFFCC">
<h2><b><ins>Consulta de Escolaridad</ins></b></h2>

<p>Escolaridad para ${datosAlumno.nombre} ${datosAlumno.apellido}
<p>
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

<p><a href='Principal.jsp'>Volver al ingreso</a></p>

</body>
</html>