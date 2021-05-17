<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="fondo.css" rel="stylesheet" type="text/css">
<title>Registro Alumno</title>
</head>
<body>
	<h2>Registro</h2>
	<form action="FormCreaACTAServlet">

		<div class="user-box">
			<label>Username:</label> <input type="text" name="nombre_alumno"
				placeholder="Introduzca su Nombre">
		</div>

		<fieldset>
			<c:forEach items="${asignaturas}" var="i">
				<label for="${i.nombre}">${i.nombre} <input type="radio"
					name="${i.nombre}">
				</label>
			</c:forEach>
		</fieldset>

		<div class="user-box">

			<label>Su correo es: </label> <input type="text"
				name="email_alumno" value="${alumno}">
		</div>
		<a> <input type="hidden" name="es_alumno" value="on">
			<button type="submit">Registrarse</button>
		</a>
	</form>

	<%@ include file="FormLogout.jsp"%>

</body>
</html>