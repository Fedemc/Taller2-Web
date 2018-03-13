<jsp:useBean id='mensajeError' scope='request' class='java.lang.String' />

<html>
<head>
<title>Error</title>
</head>
<body>
<p>Ocurrió un error!</p>
<p><b>${mensajeError}</b></p>
<p><a href='Principal.jsp'>Volver al ingreso</a></p>
</body>
</html>