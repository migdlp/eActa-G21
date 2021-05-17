<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="fondo.css" rel="stylesheet" type="text/css">
<title>Registro Profesor</title>
</head>
<body>
	<h2>Registro</h2>
	<form action="FormCreaAsignaturaServlet">

		<div class="user-box">
			<label>Username</label>
			<input type="text" name="nombre" placeholder="Introduzca su Nombre">
		</div>
		<p>
			<label >Elige Asignatura existente <select type="text" name="asignatura-existe">
					<c:forEach items="${asignaturas}" var="i">
					<option value="${i.nombre}">${i.nombre}</option>
					</c:forEach>
					<option value="nohay">No está mi asignatura</option>
			</select>
			</label>
		</p>
		<div class="user-box">
			<label>o cree asignatura</label>
			<input type="text" name="asignatura-nueva" placeholder="Introduzca su Asignatura">
		</div>

		<p>
			<label type="text" name="rol">Elige rol <select type="text" name="rol">
					<option value="coordinador">Coordinador</option>
					<option value="vocal">Vocal</option>
					<option value="secretario">Secretario</option>
					<option value="presidente">Presidente</option>
			</select>
			</label>
		</p>
		<div class="user-box">
		
			<label>Su correo es: </label>
			<input type="hidden" name="profesor" value="${profesor}">${profesor}</input>
		</div>
		<a>
			<button type="submit">Registrarse</button>
		</a>
	</form>

	<%@ include file="FormLogout.jsp"%>

</body>
</html>