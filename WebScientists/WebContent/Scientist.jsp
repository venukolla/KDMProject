<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scientist</title>
</head>
<body style="background-color: #CC66FF">

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

	<form action="./AddScientist" method="post">
		<table align="center">
			<tr>
				<td>ScientistName :</td>
				<td><input name="name" size=68 type="text" id="name" /></td>
			</tr>
			<tr>
				<td>Qualification :</td>
				<td><textarea rows="5" cols="50" name= "qualifications">Enter Qualifications</textarea>
				</td>
			</tr>
			<tr>
				<td>Research Interests :</td>
				<td><textarea rows="5" cols="50" name= "researchInterest">Enter research interests</textarea>
				</td>
			</tr>
			<tr>
				<td>Bio-Inforamation :</td>
				<td><textarea rows="10" cols="50" name= "bioinformation">Enter atleast 100 characters</textarea>
				</td>
			</tr>
			
			<tr>
				<td>Image URL :</td>
				<td><input type ="text" size = 68 name="url" id = "url"/>
				</td>
			</tr>
			
			<tr>
				<td>Research IN Field :</td>
				<td><input type = "text" size = 68 name = "filedsOfInterests" id = "fieldsOfInterests">
				</td>
			</tr>
			
			<tr>
				<td>Awards Received :</td>
				<td><textarea rows="10" cols="50" name= "awardsReceived">Enter awards received</textarea>
				</td>
			</tr>
			
			

			<tr align="center">
				<td></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

<% if(request.getAttribute("message")!= null){ %>

	Message Status:=<%=request.getAttribute("message") %>

<%} %>
</body>
</html>