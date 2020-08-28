<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Products Details</title>
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/register.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/styleAddProduct.css">

<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>

<script type="text/javascript">
        history.pushState(null,null, '');
        window.addEventListener('popstate',function(event){
        	history.pushState(null,null,' ');
        });
</script> 
	<style type="text/css">
			#header{
			padding-top: 10px;
		}
		</style>
	
	<style type="text/css">
		h3 {
		color: white;
		text-shadow: 1px 1px 2px pink, 0 0 25px blue, 0 0 5px darkblue;
		}
	</style>
</head>
<body>
	
	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>

	<div class = "container">
		<div class="wrapper">
	<div>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	
		<h2 style="color: red">${sessionScope.message}</h2>
		<h2 style="color: red">${sessionScope.msg}</h2>
	
	</div>
	
		<form action="add" method="post" name="Login_Form" class="form-signin" enctype="multipart/form-data">       
		    <h3 class="form-signin-heading" > Add Your Products  </h3>
			 <hr class="colorgraph"><br>
						  
			 <label for="pdocutName"> Enter Your Product Name: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input type="text" class="form-control" name="productName" placeholder="Enter ProductName " required="" autofocus="" /> <br>
			  
			 <label for="productPrice"> Enter Your productPrice: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input type="text" class="form-control" name="productPrice" placeholder="Enter ProductPrice" required=""/> <br>    		  
			  
			 <label for="productDescription"> Enter Your ProductDescription: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input type="text" class="form-control" name="productDescription" placeholder="Enter ProductDescription" required=""/> <br>
			  
			 <label for="purchaseDate"> Enter Your Purchase date: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		  
			 <input type="date" class="form-control" name="purchaseDate" placeholder="Enter PurchageDate" required=""/> <br> 
			  
	 		 <label for="productImage"> Upload Your ProductImage: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input type="file" class="form-control" name="productImage"  required=""/> <br>
			  
			  <button class="btn btn-lg btn-primary btn-block"  name="s1" value="UPLOAD PRODUCTS" type="submit">Add Product Details</button>  			
		</form>			
	</div>
</div>
</body>
</html>