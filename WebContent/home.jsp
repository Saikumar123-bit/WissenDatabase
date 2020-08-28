<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home Page of Product Exchange </title>

	<link rel="Stylesheet" href="css/bootstrap.css">
	<link rel="StyleSheet" href="css/validationEngine.jquery.css">
	<script type="text/javascript" src="jquery/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="jquery/jquery.validationEngine-en.js"></script>
	<script type="text/javascript" src="jquery/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="jquery/bootstrap.js"></script>

	<script type="text/javascript">
        history.pushState(null,null, '');
        window.addEventListener('popstate',function(event){
        	history.pushState(null,null,' ');
        });
	</script> 


<style type="text/css">
.header{
	float:left;
}
</style>

<script type="text/javascript">
jQuery("document").ready(function() {

		$.get("<%=application.getContextPath()%>/geteligible", function(data) {

							var pid = null;
							var pname = null;
							var price = null;
							var pdesc = null;
							var pdate = null;
							var pqua = null;
							var img = null;
							var ownerId = null;
							var j=1;
									$.each(
											data,
											function(k, v) {
												pid = v.productId;
												pname = v.productname;
												price = v.productprice;
												pdesc = v.productdescription;
												pdate = v.purchasedDate;
												pqua = v.productAvalibleStatus;
												ownerId = v.ownerId;
												img = v.productImage;

												var table =

												"<div id='divbox1' style='height:auto; width:auto;'><center><table id='myTable' border='1px' style='padding-top: 5px'; width:'auto;'><tr><td>Product ID : </td><td width='auto'>"
														+ pid
														+ "<tr><td>Product Image: </td><td width='auto' >"
														+ "<img src='data:image/jpeg;base64,"+v.productImage+"'width='200px' 'height='200px'>"
														+ "</td></tr><tr><td>Product Name : </td><td>"
														+ pname
														+ "</td></tr><tr><td>Price :  </td><td>"
														+ price
														+ "</td></tr><tr><td>Description : </td><td>"
														+ pdesc
														+ "</td></tr><tr><td>Purchaced Date : </td><td>"
														+ pdate
														+ "</td></tr><tr><td><button onclick=exchange(this); style='width:auto;background-color: green; font-size: 22px;'><a href='Login.jsp'>Exchange</a></button></td></tr></table></center></div><br><br>";
												
												if (j == 1) {
													$("#tab1").append(table);
													j++;
												} else if (j == 2) {
													
													$("#tab2").append(table);
													j++;
												} else if (j == 3) {
												
													$("#tab3").append(table);
													j = 1;
												}	
									});
					});
	});
						
	</script>

<body>

	<div>	
		<div class="heading">
			<h1 style="color:blue; text-shadow: 2px 3px red;">WELCOME TO PRODUCT EXCHANGING </h1>
		</div>
	
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
	
		<h2 style="color:red">${msg}</h2>	
		<h3 style="color: red">${message }</h3>	

		<div style="float:left;color:blue;padding: 30px" id="tab1"></div>
		<div style="float:left;color:blue;padding: 30px" id="tab2"></div>
		<div style="float:left;color:blue;padding: 30px" id="tab3"></div>
	</div>
					
	</body>
</head>
</html>