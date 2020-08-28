<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Info about product-exchange</title>

<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="StyleSheet" href="css/validationEngine.jquery.css">
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>

<style type="text/css">
.header{
	float:left;
}
</style>

</head>
<body>
	
	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<h1 style="color: blue; text-align: center;">Information about Product-Exchanging</h1>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	
</body>
</html>