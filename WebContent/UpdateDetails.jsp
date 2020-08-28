<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exchange Your Products</title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>

<style type="text/css">
		input[type=text], select {
			width:50%;
			color:blue;
			background-color: pink;	
			padding: 12px 20px;
			display: inline-block;
			border: 3px solid green;
			border-radius: 4px;
			box-sizing:border-box;
		}
		input[type=text]:focus {
			width:60%;
			color:lightpink;
			background-color: blue;
		}
		input[type=submit], select {
		width: 50%;
		color:blue;
		background-color:pink;
		padding:12px 20px;
		}
		input[type=submit]:hover {
			width:60%;
			color:lightpink;
			background-color: violet;
			
		}

</style>

<body>

<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
</div>

	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	<br><br>	

<center>
<h2 style="color: blue">Update Your Details</h2>
</center>
	
	<div>
		<% session=request.getSession(false); 
			%>
	</div>
<h5 style="color:red"><div>${msg}</div></h5>	

	<form action="<%=application.getContextPath()%>/updateDetails" method="post">
	
		<label for="username"> Enter Name: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="userName" value="<%=session.getAttribute("user")%>"><br><br>
		
		<label for="email"> Enter Email: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="email" value="<%=session.getAttribute("Email")%>"><br><br>
		
		<label for="mobileNo"> Mobileno: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="mobileNo" value="<%=session.getAttribute("Mobile_No")%>"><br><br>
		
		<input type="submit" name="submit" value="Update Details">
		
	</form>

</body>
</html>