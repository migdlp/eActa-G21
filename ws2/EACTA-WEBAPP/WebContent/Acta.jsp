<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<link href="fondo.css"      rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Consulta de Actas</title>
</head>
<body>
<h1>${alumno}</h1>
<table border="1">
<tr>
<th>Profesor</th>
<th>Asignatura</th>
<th>Nota Provisional</th>
<th>Estado</th>
</tr>

<c:forEach items="${actas}" var="actai">
<c:if test="${actai.emailalumno.equals(alumno)}">
        <tr>
        <td>${actai.emailcoordinador}</td>
        <td>${actai.asignatura}</td>
        <td>${actai.notaprovisional}</td>
        <td>${actai.status}</td>
<%--         <td><input type="email" id="advisor" name="advisor" value="${actai.advisor}" /></td> --%>
<%--         <td><input type="number" id="status" name="status" value="${actai.status}" min="0" max="8"/></td> --%>
<%--         <td><input type="text" id="notaprovisional" name="notaprovisional" value="${actai.notaprovisional}" /></td> --%>
<!--         <td><input type="submit">Actualizar valores</input></td> -->
        

</c:if>
</tr>
</c:forEach>

</table>

<c:if test="${acta.status == 3}">
  <form action="FormSubeMemoriaServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="actaemail" value="${acta.email}" />
            <input type="file" name="file" />
        <input type="submit">Subir memoria</input>
  </form>
</c:if>

<c:if test="${acta.status > 3}">
  <form action="FormBajaMemoriaServlet" method="get">
      <input type="hidden" name="actaemail" value="${acta.email}" />
        <button type="submit">Descargar memoria</button>
  </form>
</c:if>
</body>
</html>