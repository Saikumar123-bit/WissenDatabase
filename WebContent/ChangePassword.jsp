<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/forgotPassword.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/styleAddProduct.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>
</head>

<body>

<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
</div>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	<br><br>
	
<div class="heading">
		<center><h2 style="color: blue;">Welcome to Password Change</h2></center>
</div>

<div class = "container">
	<div class="wrapper">
		<form action="changepassword" method="post" name="Login_Form" class="form-signin">       
		   <div style="color: red">${msg}</div>
		    <h3 class="form-signin-heading"> Change Password</h3>
			  <hr class="colorgraph"><br>
			  <input type="text" class="form-control" name="email" placeholder="Enter Email "   disabled value="${email}"><br>
                <input id="password" name="oldpwd" placeholder="Enter Old Password " class="form-control"  type="password" required="" autofocus=""><br>
                 <input id="password" name="newpwd" placeholder="Enter New Password " class="form-control"  type="password" required="" autofocus=""><br>
                  <input id="password" name="renewpwd" placeholder="ReEnter Password " class="form-control"  type="password" required="" autofocus=""><br>
                  <input name="submit" class="btn btn-lg btn-primary btn-block" value="Change Password" type="submit"> 
        </form>    
	</div>      
</body>
</html>