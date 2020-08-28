<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exchanged Products</title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">
 
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-ui.min.js"></script>

	<style type="text/css">
		#product-wrapper{
		float: left;
		}
	</style>

<script type="text/javascript">
	jQuery("document").ready(function(){
				
		$.ajax({"url":"<%=application.getContextPath()%>/GetExchangedProducts","success":function(d){
				alert("Exchanged products---");
				alert(d.success=='yes');
				//if(d.success=='yes'){
				
					alert("length--->"+d.length);
					
					for(var item = 0; item < d.length; item++) {
						
					alert("Product Id--->"+d[item].reqProdId);
					alert("Product Request id new one---"+d[item].prodreqid);					
					var row = $("#products-wrapper");
					var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
					var div2 = $("<div class='thumbnail' style='width:35%; float: left;'>");
					
					var imgTag = $("<img class='prod-image' name='reqProdImg' src='data:image/jpeg;base64,"+d[item].reqProdImg+"' width='250px' height='250px'>");
			    	imgTag.appendTo(div2);
		    		   
		    		var div3 = $("<div class='caption'>");
		    		     		   
		    		$("<h4 class='pull-right' name='reqProdPrice'>").css("color", "red").html(" Price:"+"<i style='font-size:17px;' class='fa fa-inr'></i>"+d[item].reqProdPrice).appendTo(div3);
		    		
		    		$("<p name='reqProdId'>").css("color", "blue").html("<br>Product Id:-"+d[item].reqProdId).appendTo(div3);
								    		
		    		$("<h4 name='reqProdName'>").css("color","blue").html("ProductName:-"+d[item].reqProdName).appendTo(div3);
		    		   
		    		$("<p name='reqProdDesc'>").css("color", "blue").html("Product Description:-"+d[item].reqProdDesc).appendTo(div3);
		    		
		    		$("<p name='reqProdDate'>").css("color","blue").html("Purchased Date:-"+d[item].reqProdPurchasedDate).appendTo(div3);
		    		
		    		div3.appendTo(div2);			    		   
		    		div2.appendTo(row);
		    		
         	       // var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
					
				    var div2 = $("<div class='thumbnail' style='width:35%; float: right;'>");
					
		         	var imgTag = $("<img class='prod-image' name='excProdImg' src='data:image/jpeg;base64,"+d[item].excProdImg+"' width='250px' height='250px'>");
					imgTag.appendTo(div2);
		    		   
		    		var div3 = $("<div class='caption'>");
		    		     		   
		    		$("<h4 class='pull-right' name='excProdPrice'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d[item].excProdPrice).appendTo(div3);
		    		   
		    		$("<p name='excProdId'>").css("color", "blue").html("<br>Product Id:-"+d[item].excProdId).appendTo(div3);
		    		
		    		$("<h4 name='excProdName'>").css("color","blue").html("ProductName:-"+d[item].excProdName).appendTo(div3);
		    		   
		    		$("<p name='excProdDesc'>").css("color", "blue").html("Product Description:-"+d[item].excProdDesc).appendTo(div3);
		    		
		    		$("<p name='excProdDate'>").css("color","blue").html("Purchased Date:-"+d[item].excProdPurchasedDate).appendTo(div3);
		    		   		    		
		    		div3.appendTo(div2);		    		
                    div2.appendTo(row);
                    
                    var div4 = $("<div class='submit-wrapper form-group'>");
 
	                var myForm = $("<form method='get' action='<%=application.getContextPath()%>/DownloadPdfAction'>");
	                var download = $('<input>').attr({
                     "type": "submit",
                     "name":"download",
                     "value":"Download Product Details in Pdf",
                     "class":"myinp btn btn-primary btn-block"
                  	});
		    			
		    		   download.appendTo(myForm);
	                   
		    		   $("<input type='hidden' name='prodReqId' value='"+d[item].prodreqid+"'>").appendTo(myForm);
			             
	                   myForm.appendTo(div4);	                   
	                   /* div4.appendTo(div2);
	                   div2.appendTo(div1); */
	                   div4.appendTo(row);
				}
			}
		});
	});
	
</script>

</head>

<body>

	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<h5 style="float: right;color: blue;"><u><b><div>Rs.${sessionScope.Ac_Balance} /-</b></u></div></h5>	
	User Name: <h5 style="color:blue"><div>${sessionScope.Email}</div></h5>
	
	<div class="heading">
		<h1 style="color:blue; text-shadow: 2px 3px orange;">Exchanged Products</h1>
	</div>
	
	<div class="container">
		<div class="row" id="products-wrapper" style="padding: 10px;"></div>
	</div>

</body>
</html>