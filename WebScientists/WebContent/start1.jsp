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

<body style="background-color: #3399FF">
<%
String suri=null;
	try{
		
		Object url = request.getAttribute("speak");
		if(url!=null)
		{
			
			
			suri = url.toString();
			
			suri = suri.replaceAll("%20", "  ");
			
			
			
			out.println("Helooooo"+suri);
			%> <script>
			$('.result').show();
			$('.error').hide();
			var ad = document.getElementById("balu");
			ad.setAttribute('src','<%=suri %>');
			ad.load();
			ad.play();
			</script> <%
		}
	}
	catch(Exception e){}
	
	
%>
	


	<div class="header">

		<h1 align="center">Scientists Web Page</h1>
		<table>
			<tr>
				<div style="clear: right; float: right; text-align: right;">
					<a href="Start.jsp"><strong>HOME</strong></a> <a
						href="Scientist.jsp"><strong>AddScientist</strong></a> <a
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

	<div class="container">
		<div class="row">
			<div class="col-lg-7 col-md-7 col-xs-12">
				<h2>Scientist WebPage</h2>
				<div class="well">
					<form method="get" id="demo"  class="form-horizontal" action="./DemoServlet">
						<fieldset>
							<div class="row" width="40%">
								<div class="col-lg-12 col-xs-12">
									<table>
										<tr>
											<td>Enter the Name of Scientist:</td>
											<td><input type="text" name="text" id="text"></td>
										</tr>
									</table>

								</div>
							</div>
							<div style="margin-bottom: 30px;" class="row">
								<label for="voice" class="col-lg-12 col-xs-12 control-label">
									Select the Voice model: </label>
								<div class="col-lg-12 col-xs-12">
									<select id="voice" style="width: 100%" name="voice" required
										class="form-control">
										<option value="VoiceEnUsMichael">English male voice</option>
										<option value="VoiceEnUsLisa">English female voice</option>
										<option value="VoiceEsEsEnrique">Spanish male voice</option>
									</select>
								</div>
							</div>
							
							<div class="row">
								<div class="col-lg-4 col-xs-4 download-container">
								<a href="<%=suri %>">	<input class="btn btn-block download-button" value="Download"></a>
								</div>
								<div class="col-lg-4 col-xs-4 text-center"></div>
								<div class="col-lg-4 col-xs-4 ie-speak">
									<input value="Speak" type="submit" class="btn btn-block speak-button" >
									<div class="arrow-box">
										<p>This browser cannot play the ogg audio format. Use
											Chrome or Firefox to play audio.</p>
									</div>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="col-lg-5 col-md-5 col-xs-12">
				<h2>Output</h2>
				<div class="row">
					<div class="col-lg-12 col-xs-12">
						<div  class="well result">
							<div class="text-center">
								<audio autoplay preload="auto" autobuffer controls class="audio" src="<%=suri %>" id="balu">
								
								</audio>
							</div>
							<div>
								<span class="help-block"> The synthesized audio is
									streamed to the client as it is being produced, using the HTTP
									chunked encoding. The audio is returned in the <a
									href="http://www.vorbis.com/">Ogg Vorbis</a> format which can
									be played using <a
									href="http://www.videolan.org/vlc/index.html">VLC</a>, <a
									href="http://audacity.sourceforge.net/">Audacity</a> and many
									other players.
								</span>
							</div>
						</div>
						<div style="display: none" class="well error">
							<div class="form-group row">
								<div class="col-lg-12 col-xs-12">
									<p class="errorMsg">Error processing the request.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="row">
			<div class="help-block text-center">
				<small>
					Audio output only plays on browsers supporting ogg audio format, including recent versions of <a href="https://www.google.com/intl/en/chrome/browser/desktop/" target="_blank">Chrome </a>and <a href="https://www.mozilla.org/en-US/firefox/new/" target="_blank">Firefox.</a>
				</small>
			</div>
		</div> -->
	</div>
	<!-- <form action="./TestWebpage" method="get">
		<table align="center">
			<tr>
				<td>Scientist Name:</td>
				<td><input type="text" name="Input" id="input" /></td>
				<br>
			</tr>
			<tr align="right">
				<td>
				<td>
				<td><input type="submit" name="Submit" value="Submit"></td>
			</tr>
		</table>
	</form> -->
	
	
	
	<script type="text/javascript" src="js/browser-detect.js"></script>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/demo.js"></script>
</body>
</html>