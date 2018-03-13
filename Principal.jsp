<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='diccAlumnos' scope='application' class='sistema.logica.alumnos.Alumnos' />

<html>
<head>
<title>Gourmet Chef Uruguay</title>
</head>
<body bgcolor="#FFFFCC">
<h2><b><ins>Consultas para la escuela Gourmet Chef Uruguay</ins></b></h2>
<p>
<p>Ingrese su cedula, debe ser un alumno registrado en la escuela.<br>Luego seleccione el tipo de consulta que desea realizar.</p>
<p></p>
<form action="Principal" method="POST">
<p>Cedula: <input type='text' name='cedula' size = 30></p>
<p> Seleccione la consulta <select name='consulta'>
							<option value='Escolaridad'> Escolaridad
							<option value='Listado de Egresados'> Listado de Egresados
						</select></p>
<p> <input type='submit' value= 'Consultar'></p>

</form>
</body>
</html>