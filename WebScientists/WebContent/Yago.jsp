<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YAGO LIVE</title>
</head>
<body style="background-color: #3399FF">
	<div class="header">
		<h1 align="center">Scientists Web Page</h1>
		<table>
			<tr>
				<div style="clear: right; float: right; text-align: right;">
					<a href="Start.jsp"><strong>HOME</strong></a>
					<a href="Scientist.jsp"><strong>AddScientist</strong></a>
					<a href="ListScientists.jsp"><strong>Group</strong></a>
					<a href="addquestion.html"><strong>AddQuestion</strong></a>
					<a href="searchanswer.html"><strong>SearchAnswer</strong></a>
					<a href="Yago.jsp"><strong>YAGO</strong></a>
				</div>
			</tr>
		</table>
	</div>

	<div align="left">
		<table>
			<tr>
				<td><img src="images/galileo.jpg" width="212" height="246" /></td>
				<td><img src="images/darwin.jpeg" width="212" height="246" /></td>
				<td><img src="images/einstein.jpeg" width="212" height="246" />
				<td><img src="images/grahambell.jpg" width="229" height="246" /></td>
				<td><img src="images/newton.jpeg" width="229" height="246" /></td>
				<td><img src="images/nikola.jpg" width="212" height="246" /></td>
			</tr>
		</table>
	</div>

	<form action="./YagoConsumer" method="get">
		<table align="center">
			<tr>
				<td>Enter the name to find:</td>
				<td><input type="text" name="inputSentense" id="input"></td>
			</tr>
			<tr align="right">
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>

	<%
		if (request.getParameter("inputSentense") != null) {
	%>

	<c:forEach items="${hashmap}" var="entry">
    Key = ${entry.key}, value = ${entry.value}<br>
	</c:forEach>
	<%} %>
</body>
</html>