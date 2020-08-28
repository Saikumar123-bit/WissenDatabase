<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exchange Your Products</title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>

<script type="text/javascript">
	
	jQuery("document").ready(function(){
		$.ajax({"url":"<%=application.getContextPath()%>/product", "success":function(d){
			
			if(d.success='yes'){
				
				for(var item = 0;item < d.length;item++){

					var row = $("#products-wrapper");
					var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
					var div2 = $("<div class='thumbnail'>");
					
					var imgTag = $("<img class='prod-image' name='prodImg' src='data:image/jpeg;base64,"+d[item].productImage+"'width='200px'>");
					imgTag.appendTo(div2);
					
					var div3 = $("<div class='caption'>");
					
					$("<h4 class='pull-right' name='prodPrice'>").css("color", "red").html(" Price:"+"<i style='font-size:17px;' class='fa fa-inr'></i>"+d[item].productprice).appendTo(div3);

					$("<p name='prodeId'>").css("color", "blue").html("<br>Product Id:-"+d[item].productId).appendTo(div3);
					
		    		$("<h4>").html(d[item].productname).appendTo(div3);
		    		   
		    		$("<p>").css("color", "green").html(d[item].productdescription+"<br>Purchased Date:-"+d[item].purchasedDate).appendTo(div3);
		    		div3.appendTo(div2);
		    		   
		    		var div4 = $("<div class='submit-wrapper'>");
		    		var myForm = $("<form method='post' action='<%=application.getContextPath()%>/ProductRequestAction'>");
		    		
		    		var exchangeBtn = $('<input>').attr({
                     "type":"submit",
                     "name":d[item].productId,
                     "value":"Select-To-Exchange",
                     "class":"myinp btn btn-primary btn-block"
                    });
		    		
		    		var hidden =$('<input>').attr({
			    		"type":"hidden",
			    		"name":"eId",
			    		"value":d[item].productId,
			    	});	   
	                hidden.appendTo(myForm);
	                
	                var hidden=$('<input>').attr({
	                	"type":"hidden",
	                	"name":"excprice",
	                	"value":d[item].productprice,
	                });
					hidden.appendTo(myForm);
	                
			   		hidden =$('<input>').attr({
			    		"type":"hidden",
				   		"name":"rpid",
			    		"value":"<%=request.getParameter("rpid")%>"
			    	});	   
	               hidden.appendTo(myForm);
	               
	               hidden=$('<input>').attr({
	            	  "type":"hidden",
	            	  "name":"reqprice",
	            	  "value":"<%=request.getParameter("reqprice")%>"
	               });
	               hidden.appendTo(myForm);

                   exchangeBtn.appendTo(myForm);
                   myForm.appendTo(div4);
                   div4.appendTo(div2);
                   
                   div2.appendTo(div1);
                   div1.appendTo(row);		    		 
				}
			}else {
				alert(d.data);
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

<center>
<h2 style="color: blue"> Your Products</h2>
</center>
<input type="text" name="reqpid" value="<%=request.getParameter("rpid")%>">
<div class="container">
		<div class="row" id="products-wrapper" style=" color:blue; padding: 10px;"></div>
	</div>

</body>
</html>