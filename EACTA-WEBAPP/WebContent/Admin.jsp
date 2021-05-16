<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<link href="fondo.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<div class="container"></div>
	<table border="1">

		<tr>
	
			<th>Alumno</th>
			<th>Email del alumno</th>
			<th>Nombre del Coordinador</th>
			<th>Email del Coordinador</th>
			<th>Nombre del Vocal</th>
			<th>Email del Vocal</th>
			<th>Nombre del Secretario</th>
			<th>Email del Secretario</th>
			<th>Nombre del Presidente</th>
			<th>Email del Presidente</th>
			<th>Estado</th>
			<!--<th>Memoria</th>-->
			<th>Nota</th>
			<th>Actualizar</th>
		</tr>

		<c:forEach items="${actas}" var="actai">
			<form action="FormAdminServlet" method="post">
				<tr>
					<td><input type="text" id="nombre_alumno" name="nombre_alumno"
						value="${actai.nombre_alumno}" /></td>
					<td><input type="email" id="email_alumno" name="email_alumno"
						value="${actai.email_alumno}" />${actai.email_alumno}</td>
					<td><input type="text" id="nombre_coordinador" name="nombre_coordinador"
						value="${actai.nombre_coordinador}" /></td>
					<td><input type="email" id="email_coordinador" name="email_coordinador"
						value="${actai.email_coordinador}" /></td>
					<td><input type="text" id="nombre_vocal" name="nombre_vocal"
						value="${actai.nombre_vocal}" /></td>
					<td><input type="email" id="email_vocal" name="email_vocal"
						value="${actai.email_vocal}" /></td>
					<td><input type="text" id="nombre_secretario" name="nombre_secretario"
						value="${actai.nombre_secretario}" /></td>
					<td><input type="email" id="email_secretario" name="email_secretario"
						value="${actai.email_secretario}" /></td>
					<td><input type="text" id="nombre_presidente" name="nombre_presidente"
						value="${actai.nombre_presidente}" /></td>
					<td><input type="email" id="email_presidente" name="email_presidente"
						value="${actai.email_presidente}" /></td>
					<td><input type="number" id="status" name="status"
						value="${actai.status}" min="0" max="8" /></td>
					<td><input type="text" id="nota" name="nota"
						value="${actai.nota}" /></td>
					<td><input type="submit">Actualizar valores</input></td>
				</tr>

			</form>
		</c:forEach>
	</table>

	<h2>Crear Acta</h2>
	<form action="FormCreaACTAServlet">
					<input type="text" id="nombre_alumno" name="nombre_alumno"
						placeholder="Alumno" />
					<input type="email" id="email_alumno" name="email_alumno"
						placeholder="Email Alumno" />
					<input type="text" id="nombre_coordinador" name="nombre_coordinador"
						placeholder="Coordinador" />
					<input type="email" id="email_coordinador" name="email_coordinador"
						placeholder="Email Coordinador"  />
					<input type="text" id="nombre_vocal" name="nombre_vocal"
						placeholder="Vocal"  />
					<input type="email" id="email_vocal" name="email_vocal"
						placeholder="Email Vocal" />
				<input type="text" id="nombre_secretario" name="nombre_secretario"
						placeholder="Secretario" />
					<input type="email" id="email_secretario" name="email_secretario"
						placeholder="Email Secretario" />
					<input type="text" id="nombre_presidente" name="nombre_presidente"
						placeholder="Presidente"/>
					<input type="email" id="email_presidente" name="email_presidente"
						placeholder="Email Presidente" />
					<input type="number" id="status" name="status"
						placeholder="Status" min="0" max="8" />
					<input type="text" id="nota" name="nota"
						placeholder="Nota" />
		<button type="submit">Registrar</button>
	</form>

<%@ include file="FormLogout.jsp"%>

</body>
</html>