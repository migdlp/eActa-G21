<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<link href="fondo.css"      rel="stylesheet" type="text/css"> 
<title>Insert title here</title>
</head>
<body>
<h1>${profesor.nombre}</h1>
<table border="1">

<tr>
<th>Id</th>
<th>Alumno</th>
<th>Asignatura</th>
<th>Nota Provisional</th>
<th>Estado</th>
</tr>

<%-- <c:forEach items="${acta}" var="actai"> --%>
<!-- <form action="FormAdminServlet" method="post"> -->
<!--         <tr> -->
<%--         <td>${actai.id}</td> --%>
<%--         <td>${actai.emailalumno}</td> --%>
<%--         <td>${actai.asignatura}</td> --%>
<%--         <td>${actai.notaprovisional}</td> --%>
<%--         <td>${actai.status}</td> --%>
<%-- <%--         <td><input type="email" id="advisor" name="advisor" value="${actai.advisor}" /></td> --%>
<%-- <%--         <td><input type="number" id="status" name="status" value="${actai.status}" min="0" max="8"/></td> --%> 
<%-- <%--         <td><input type="text" id="notaprovisional" name="notaprovisional" value="${actai.notaprovisional}" /></td> --%> 
<!-- <!--         <td><input type="submit">Actualizar valores</input></td> -->
<!--         </tr> -->

<!-- </form> -->
<%-- </c:forEach> --%>




<c:forEach items="${actas}" var="actai">

<c:if test="${actai.emailcoordinador.equals(profesor)}">

<tr>

<td>${actai.id}</td>

<td>${actai.emailalumno}</td>

<td>${actai.asignatura}</td>

<td>${actai.notaprovisional}</td>

<td>${actai.status}</td>

</c:if>

<%-- <td><c:if test="${actai.status > 3}"> --%>

<!--                 <form action="FormBajaMemoriaServlet"> -->

<%--                 <input type="hidden" name="actaemail" value="${actai.email}" /> --%>

<!--                 <input type="submit">Memoria TFG</input> -->

<!--                 </form> -->

<%--         </c:if> --%>

<!-- </td> -->

<%-- <td>${actai.notaprovisional}</td> --%>

<%-- <c:if test="${actai.status == 1}"> --%>

<!--         <td> -->

<!--         <form action="FormProfesorServlet"> -->

<%--         <input type="hidden" id="actaemail" name="actaemail" value="${actai.email}" /> --%>

<!--         <input type="submit">Aceptar Tutela</input> -->

<!--         </form> -->

<!--         </td> -->

<%-- </c:if> --%>

<%-- <c:if test="${actai.status == 4}"> --%>

<!--         <td> -->

<!--         <form action="FormProfesorServlet"> -->

<%--         <input type="hidden" id="actaemail" name="actaemail" value="${actai.email}" /> --%>

<!--         <input type="submit">Proponer defensa</input> -->

<!--         </form> -->

<!--         </td> -->

<%-- </c:if> --%>

<%-- <c:if test="${actai.status == 6}"> --%>

<!--         <td> -->

<!--         <form action="FormProfesorServlet"> -->

<%--         <input type="hidden" id="actaemail" name="actaemail" value="${actai.email}" /> --%>

<%--         <input type="text" id="mark" name="mark" value="${actai.notaprovisional}"  placeholder="Nota de la defensa" /> --%>

<!--         <input type="submit">Poner nota</input> -->

<!--         </form> -->

<!--         </td> -->

<%-- </c:if> --%>

<%-- <c:if test="${actai.status != 1 and actai.status != 4 and actai.status != 6}"> --%>

<!--         <td></td> -->

<%-- </c:if> --%>

</tr>

</c:forEach>

</table>

<h2>Crear ACTA</h2>

<form action="FormCreaACTAServlet">
		<input type="email" id="emailalumno" name="emailalumno" placeholder="Email">
        <input type="password" id="password" name="password" placeholder="Password">
        <input type="text" id="asignatura" name="asignatura" placeholder="Asignatura">
        <input type="number" id="notaprovisional" name="notaprovisional" placeholder="Nota">
        <button type="submit">Registrar</button>

</form>


<%@ include file = "FormLogout.jsp" %>
</body>
</html>