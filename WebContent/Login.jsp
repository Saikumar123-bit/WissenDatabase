<!DOCTYPE html>		
<html>
	<head>
		<title>Login page</title>
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
		
		input[type=password] {
		width: 50%;
		color:blue;
		background-color:orange;
		padding:12px 20px;
		display:inline-block;
		border:3px solid yellow;
		border-radius:4px;
		box-sizing:border-box;
		}
		input[type=password]:focus {
			width:60%;
			background-color: green;
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
		
		div {
			border-radius :5px;
			background-color: lightblue;
			padding:20px;
			width:50%;
		}
	</style>
	<script type="text/javascript">
		function mOver(obj){
			obj.innerHTML="Go to Login Page";
		}
	</script>
</head>
	<body>
	
		<h1><font style="color:blue;text-align: center;">Login To Your Account</font></h1>
	
		<div style="background-color:yellow; color:blue; width:600px; height:200; font-style:French Script MT; font-size:30px;">
	
		Login: <BR><BR>
		Get access to your Orders
		,Wishlists and Information

		</div>
	
<h2 style="color:red">${sessionScope.msg}</h2>	
	<div>
		<form action="<%=application.getContextPath()%>/login" method="post">
			
			<label for="email"> Enter Email: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="email" placeholder="Enter Your Email"> <br><br>

			<label for="password"> Enter Password:&nbsp;&nbsp;&nbsp;</label>
			<input type="password" name="password" placeholder="Enter Your Password"><br><br> 
		
			<a href="<%=application.getContextPath()%>/ForgotPassword.jsp">ForgotPassword</a><br><br>
		
			<input type="submit" onmouseover="mOver(this)" name="login" value ="Login To Your Account">
		</form>
		
	</div>
</body>
</html>