<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ventana web para Alumnos</title>
</head>
<body>

<form action="Ingrese CI" method="POST">
<p>Cedula: <input type='text' name = ' cedula' size = 30></p>
<p> Seleccione la opcion <select name='consulta'>
							<option value='Escolaridad'> Escolaridad
							<option value='Listado de Egresados'> Listado de Egresados
						</select></p>
<p> < input type='submit' value= 'INGRESAR'></p>

	
</form>
</body>
</html>