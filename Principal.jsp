<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='diccAlumnos' scope='application' class='sistema.logica.alumnos.Alumnos' />

<html>
<head>
<title>Ventana web para Alumnos</title>
</head>
<body>

<form action="Principal" method="POST">
<p>Cedula: <input type='text' name='cedula' size = 30></p>
<p> Seleccione la opcion <select name='consulta'>
							<option value='Escolaridad'> Escolaridad
							<option value='Listado de Egresados'> Listado de Egresados
						</select></p>
<p> <input type='submit' value= 'INGRESAR'></p>

</form>
</body>
</html>