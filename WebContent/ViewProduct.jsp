<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Products</title>
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
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
		#product-wrapper{
		float: left;
		}
	</style>

<script type="text/javascript">
 
jQuery("document").ready(function () {
	
 	 $.ajax({"url":"<%=application.getContextPath()%>/view","success":function(d){
 			 
			var  products = $.getJSON(d);
			 	
			//if(d.success=='yes'){
					
				for(var item = 0; item < d.length; item++) {
								
						var row = $("#products-wrapper");
						var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
							
						var div2 = $("<div class='thumbnail'>");
						
						//var imgTag = $("<img class='img img-rounded' src='one plus.jpeg' width='200px'>");
						var imgTag = $("<img class='prod-image' name='prodImg' src='data:image/jpeg;base64,"+d[item].productImage+"' width='200px'>");
				    	imgTag.appendTo(div2);
			    		   
			    		var div3 = $("<div class='caption'>");
			    		     		   
			    		$("<h4 class='pull-right' name='prodPrice'>").css("color", "red").html(" Price:"+"<i style='font-size:17px;' class='fa fa-inr'></i>"+d[item].productprice).appendTo(div3);
			    		
			    		$("<p name='prodId'>").css("color", "blue").html("<br>Product Id:-"+d[item].productId).appendTo(div3);
									    		
			    		$("<h4 name='prodName'>").css("color","blue").html("ProductName:-"+d[item].productname).appendTo(div3);
			    		   
			    		$("<p name='prodDesc'>").css("color", "blue").html("Product Description:-"+d[item].productdescription).appendTo(div3);
			    		
			    		$("<p name='prodDate'>").css("color","blue").html("Purchased Date:-"+d[item].purchasedDate).appendTo(div3);
			    		
			    		div3.appendTo(div2);			    		   
			    		
			    		var div4 = $("<div class='submit-wrapper'>");
			
		                var myForm = $("<form method='get' action='EditUpdateProductDetails.jsp'>");
			    		var editBtn = $('<input>').attr({
	                     "type": "submit",
	                     "name":"editBtn",
	                     "value":"Edit Product Details",
	                     "class":"myinp btn btn-primary btn-block"
	                  	});
						
			    		var hidden1 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodId",
			    			"value":d[item].productId,
			    		});
			    		var hidden2 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodName",
			    			"value":d[item].productname,
			    		});
			    		var hidden3 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodPrice",
			    			"value":d[item].productprice,
			    		});
			    		var hidden4 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodDesc",
			    			"value":d[item].productdescription,
			    		});
			    		var hidden5 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodDate",
			    			"value":d[item].purchasedDate,
			    		});
			    		var hidden6 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodImage",
			    			"value":d[item].productImage,
			    		});
			    		
			    		var myForm1 = $("<form method='get' action='<%=application.getContextPath()%>/removeproduct'>");
			    		var deleteBtn = $('<input>').attr({
		                  "type": "submit",
		                  "name":"deleteBtn",
		                  "value":"Remove Product",
		                  "class":"myinp btn btn-danger btn-block"
		                });
			    		
			    		var hidden8 = $('<input>').attr({
			    			"type":"hidden",
			    			"name":"prodId",
			    			"value":d[item].productId,
			    		});
			   
				   	   hidden1.appendTo(myForm);
				   	   hidden2.appendTo(myForm);
				   	   hidden3.appendTo(myForm);
				   	   hidden4.appendTo(myForm);
				   	   hidden5.appendTo(myForm);
				   	   
	                   hidden8.appendTo(myForm1);
	
	                   editBtn.appendTo(myForm);
	                   deleteBtn.appendTo(myForm1);
	                   
	                   myForm.appendTo(div4);
	                   myForm1.appendTo(div4);
	                   
	                   div4.appendTo(div2);
	                   div2.appendTo(div1);
	                   div1.appendTo(row);   		
					}
			/*  }else{
					alert(d.data);
				}    */
			}
 	 	});		
	 });
</script>
 
</head>

<body>
	
	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<div class="heading">
		<center><h2 style="color:blue; text-shadow: 2px 4px violet;">  Updating And Removing Products</h2></center>
	</div>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	<br><br>
	
	<div class="container">
		<div class="row" id="products-wrapper" color:blue; padding: 3px;"></div>
	</div>
	
	<br><br><br>
	<div style="color:red;">${msg}</div>
	
</body>
</html>