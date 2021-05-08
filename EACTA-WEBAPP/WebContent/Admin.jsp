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
			<th>Título</th>
			<th>Estudiante</th>
			<th>Email del estudiante</th>
			<th>Email del tutor</th>
			<th>Estado</th>
			<!--<th>Memoria</th>-->
			<th>Nota</th>
			<th>Actualizar</th>
		</tr>

		<c:forEach items="${actas}" var="actai">
			<form action="FormAdminServlet" method="post">
				<tr>
					<td><input type="text" id="name" name="name"
						value="${actai.name}" /></td>
					<td><input type="hidden" id="email" name="email"
						value="${actai.email}" />${actai.email}</td>
					<td><input type="hidden" id="email" name="email"
						value="${actai.email}" />${actai.email}</td>
					<td><input type="email" id="advisor" name="advisor"
						value="${actai.advisor}" /></td>
					<td><input type="number" id="status" name="status"
						value="${actai.status}" min="0" max="8" /></td>
					<td><input type="text" id="mark" name="mark"
						value="${actai.mark}" /></td>
					<td><input type="submit">Actualizar valores</input></td>
				</tr>

			</form>
		</c:forEach>
	</table>

	<h2>Crear TFG</h2>
	<form action="FormCreaTFGServlet">
		<input type="email" id="email" name="email" placeholder="Email">
		<input type="password" id="password" name="password"
			placeholder="Password"> <input type="text" id="name"
			name="name" placeholder="Nombre y apellidos"> <input
			type="text" id="titulo" name="titulo"
			placeholder="Título del proyecto"> <input type="email"
			id="profesor" name="profesor" placeholder="Profesor tutor">
		<button type="submit">Registrar</button>
	</form>

</body>
</html>