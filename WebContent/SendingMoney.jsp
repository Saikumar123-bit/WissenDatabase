<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sending Balance</title>
</head>
<body>
		<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	<br><br>
	
	<div class="heading" >
		<h1 style="color:violet; text-shadow: 2px 3px blue;">Confirmation Page To Send Money</h1>
	</div>
	
	<h2 style="color:red">${msg}</h2>	
	
</body>

</html>