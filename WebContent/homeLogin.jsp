<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page of Product Exchange </title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
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
.header{
	float:left;
}
</style>

<script type="text/javascript">
		 
		 jQuery("document").ready(function () {
				
				$.ajax({"url":"<%=application.getContextPath()%>/geteligible", "success":function(d){
					
					 var  products = $.getJSON(d);
					 
					if(d.success='yes'){
							alert("Data loaded success--inboxLogin");
							for (var item = 0; item < d.length; item++) {
										
								var row = $("#products-wrapper");
								var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
									
								var div2 = $("<div class='thumbnail'>");
								var imgTag = $("<img class='prod-image' src='data:image/jpeg;base64,"+d[item].productImage+"'>");
						    	imgTag.appendTo(div2);
					    		   
					    		var div3 = $("<div class='caption'>");
					    		     		   
					    		$("<h4 class='pull-right'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d[item].productprice).appendTo(div3);
					    		   
					    		$("<p name='prodId'>").css("color", "blue").html("<br>Product Id:-"+d[item].productId).appendTo(div3);
					    		
					    		$("<h4>").html(d[item].productname).appendTo(div3);
					    		$("<p>").css("color", "green").html(d[item].productdescription+"<br>Purchased Date:-"+d[item].purchasedDate).appendTo(div3);
					    		   
					    		$("<p name='prodDate'>").css("color","blue").html("Purchased Date:-"+d[item].purchasedDate).appendTo(div3);
					    		div3.appendTo(div2);			    		   
					    		   
					    		var div4 = $("<div class='submit-wrapper'>");
					    		
					    		var myForm = $("<form method='get' action='ExchangeProduct.jsp'>");
					    		var exchangeBtn = $('<input>').attr({
			                     	"type": "submit",
			                     	"name":d[item].productId,
			                     	"value":"Exchange",
			                  		"class":"myinp btn btn-primary btn-block"
			                  	});
					 	   		
					    		var hidden =$('<input>').attr({
					    		"type":"hidden",
					    		"name":"rpid",
					    		"value":d[item].productId
					    		});	
					    		hidden.appendTo(myForm);
					    		var hidden=$('<input>').attr({
					    			"type":"hidden",
					    			"name":"reqprice",
					    			"value":d[item].productprice
					    		});
					    		hidden.appendTo(myForm);
					               
					    		exchangeBtn.appendTo(myForm);

			                   	myForm.appendTo(div4);
			                   	div4.appendTo(div2);
			                   
			                   	div2.appendTo(div1);
			                   	div1.appendTo(row);					    		 
							}
						}else{
							alert(d.data);
						}
					}
				});
			 });
</script>
<body>
	<div> 	
	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<h5 style="float: right;color: blue;">Rs.${sessionScope.Ac_Balance} /-</h5>	
	User Name: <h5 style="color:blue">${sessionScope.Email}</h5>
	<br><br>
		
	<div class="heading" >
		<h1 style="color:red; text-shadow: 2px 3px blue;">All Availible Products</h1>
	</div>
	
	<h2 style="color:red">${msg}</h2>	
	<h3 style="color: red">${message }</h3>	
	
	<div class="container">
		<div class="row" id="products-wrapper" color:blue; padding: 10px;"></div>
	</div>
		
	<div style="float:left;color:blue;padding: 30px" id="tab1"></div>
	<div style="float:left;color:blue;padding: 30px" id="tab2"></div>
	<div style="float:left;color:blue;padding: 30px" id="tab3"></div>

</div>
	
</body>
</html>