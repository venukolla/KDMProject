<%@page import="java.net.URI"%>
<%@page import="com.ibm.cloudoe.samples.DemoServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="css/watson-bootstrap-dark.css">
	<link rel="stylesheet" href="css/style.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<title>Scientist</title>
<style type="text/css"></style>
</head>
<body style="background-color: #9494B8">

	<div class="header">
		<h1 align="center">Scientists Web Page</h1>
		<table>
			<tr>
				<div style="clear: right; float: right; text-align: right;">
					<a href="Start.jsp"><strong>HOME</strong></a> <a
						href="Scientist.jsp"><strong>AddScientist</strong></a> <a
						href="ListScientists.jsp"><strong>Groups</strong></a> <a
						href="addquestion.html"><strong>AddQuestion</strong></a> <a
						href="searchanswer.html"><strong>SearchAnswer</strong></a> <a
						href="Yago.jsp"><strong>YAGO</strong></a>
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
	
	

	<form action="./ScientistList" method="get">

		<table align="center">
			<tr>
				<td>Enter the field of Interest:</td>
				<td><input type="text" name="group" id="group"></td>
			</tr>
			<tr align="right">
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<!-- <input type="text" name = "group" id = "group">
<input type="submit" value="Submit">
</form> -->

	<%
		if (request.getAttribute("listScientists") != null) {
	%>
<script type="text/javascript">

function enAudio(name)
{
	alert(name);
	var form = document.getElementById("nameSubmit");
	form.action = "./DemoServlet.java?name="+name;
	form.submit();
	/*var ele = document.getElementById("balu");
	var source = document.getElementById('oggSource');
	source.src="abc";

    ele.load(); 
    ele.play();*/
    
    
	}

</script>

<form id="nameSubmit" method=post>
	<c:forEach var="user" items="${listScientists}">
		<table align="left">
			<tr>
				<td><a href="#" onclick="enAudio('${user.key}')"><img class="scientist" src="${user.value}" width="200" height="200" align="middle" id="${user.key }"></a></td>
			</tr>
		</table>
	</c:forEach>
	</form>

	<%
		}
	%>


<audio id="balu" controls="controls">
    <source id="oggSource" src="" type="audio/ogg"></source>
    <source id="mp3Source" type="audio/mp3"></source>
</audio>	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			console.log("Yo");

			$(".scientist").click(function(event) {
				console.log(event.target.id);
				
				<% DemoServlet demoservlet = new DemoServlet();
					
				// URI uri = demoservlet.getSpeechTextForScientist("Test");
				 
				 
				%>
				
				
			});
		});
	</script>
</body>
</html>