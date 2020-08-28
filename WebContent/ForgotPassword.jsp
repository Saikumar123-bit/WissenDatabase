<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>

<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="Stylesheet" href="css/forgotPassword.css.css">
<link rel="Stylesheet" href="css/styleAddProduct.css">

<link rel="StyleSheet" href="css/validationEngine.jquery.css">
<script type="text/javascript" src="jquery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="jquery/bootstrap.js"></script>
</head>
<body>

<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
</div>
	
	<div class="heading">
		<center><h2 style="color: buttontext;">Product Exchange Application</h2></center>
	</div>

<div class = "container">
	<div class="wrapper">
		<form action="forgot" method="post" name="Login_Form" class="form-signin">       
		   <div style="color: red">${msg}</div>
		    <h3 class="form-signin-heading"> Reset Password</h3>
			  <hr class="colorgraph"><br>
			  Enter Email:<input type="text" class="form-control" name="email" placeholder="Enter Email " required="" autofocus="" /><br>               
              <input name="submit" class="btn btn-lg btn-primary btn-block" value="Generate Password" type="submit"> 
                  </form>
                </div>
            </div>
      </body>
</html>
