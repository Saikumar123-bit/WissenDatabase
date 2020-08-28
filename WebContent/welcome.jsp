<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="Stylesheet" href="css/forgotPassword.css">
<link rel="Stylesheet" href="css/styleAddProduct.css">
<link rel="StyleSheet" href="css/validationEngine.jquery.css">
 
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="jquery/bootstrap.js"></script>

</head>

 <script type="text/javascript">
        history.pushState(null,null, '');
        window.addEventListener('popstate',function(event){
        	history.pushState(null,null,' ');
        });
</script> 


<script type="text/javascript">
$("document").ready(function() {
		$.get("<%=application.getContextPath()%>/product", function(data) {
			var pid = null;
			var pname = null;
			var price = null;
			var pdesc = null;
			var pdate = null;
			var pqua = null;
			var img = null;
			var ownerId = null;
			var j=1;

			if(data.success=='yes'){
				data= data.data;
			}
			$.each(data, function(k, v) {
					pid = v.productId;
					pname = v.productname;
					price = v.productprice;
					pdesc = v.productdescription;
					pdate = v.purchasedDate;
					pqua = v.productAvalibleStatus;
					ownerId = v.ownerid;
					img = v.productImage;

					var table =	"<div class='product-wrapper' ><center><table id='myTable' border='1px' style='padding-top: 5px'; width:'auto;'><tr><td>Product ID : </td><td width='auto'>"
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
								+ "</td></tr><tr><td>Exchange Status : </td><td>"
								+ pqua
								+ "</td></tr><tr><td>Owner Id : </td><td>"
								+ ownerId
								+ "</table></center></div>";
												
								if(j==1){
									$("#tab1").append(table);
									j++;
								}else if(j==2){
									$("#tab2").append(table);
								}	
						});

				});
	});
						
		</script>
		
		<style type="text/css">
			#header{
			padding-top: 10px;
		}
		</style>
	

	<body>
	
		 <div id="header">
			<jsp:include page="inboxHeader.jsp"></jsp:include>
		</div> 
	
	 <div class="heading">
	 <h1 style="color:blue; text-decoration: underline"> YOUR PRODUCTS</h1>
	</div>

	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	<br><br>

	<div class="products" >

	<div style="float:left;color:blue;padding: 30px" id="tab1"></div>
	<div style="float:left;color:blue;padding: 30px" id="tab2"></div>

<%-- <%
	//allow access only is session exists
	String user=null;
	
	if(request.getAttribute("user")==null)
	{
		
	}
	else
	{
		user = (String)request.getAttribute("user");	
	}
	String userName=null;
	String sessionId=null;
	
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("user"))
				userName=cookie.getValue();
			
			if(cookie.getName().equals("JSESSIONID"))
				sessionId=cookie.getValue();
	}
	}
	else{
		sessionId = session.getId();
	} 
 %>
 
WelcoME:::<%= userName%>
</div>

<div>
	<h2>${message }</h2>
	
</div>


 --%>
  
</body>
</html>
