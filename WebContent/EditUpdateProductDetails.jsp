<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit update your product details</title>
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>

</head>

<body>
	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<div class="heading">
		<center><h2 style="color:blue; text-shadow: 2px 4px violet;"> Edit And Update Your Product Details</h2></center>
	</div>
	
			<form action="<%=application.getContextPath()%>/UpdateProduct" method="post" enctype="multipart/form-data">
			  
			  <input type="text" class="form-control" name="productId" value="<%=request.getParameter("prodId") %>" />
			  <input type="text" class="form-control" name="productName" value="<%=request.getParameter("prodName") %>" /> <br>
			  <input type="text" class="form-control" name="producPrice" value="<%=request.getParameter("prodPrice")%>"/> <br>    		  
			  <input type="text" class="form-control" name="productDescription" value="<%=request.getParameter("prodDesc")%>"/> <br>
			  <input type="date" class="form-control" name="purchageDate" value="<%=request.getParameter("prodDate")%>"/> <br> 
			  <input type="file" class="form-control" name="productImage" value="<%=request.getParameter("prodImg") %>" /> <br>

			  <button type="Submit" class="btn btn-lg btn-primary btn-block"  name="s1" >Product-Update  </button>
			
			</form>
	</body>
</html>