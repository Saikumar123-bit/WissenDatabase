<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notification Request Reply</title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">
<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/displayimg.css">
<link rel="StyleSheet" href="<%=application.getContextPath()%>/css/validationEngine.jquery.css">
 
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-ui.min.js"></script>

<script type="text/javascript">

	jQuery("document").ready(function(){
	
		var prodReqId = <%= request.getParameter("product_request_id") %>;
		var reqStatus = <%= request.getParameter("reqStatus")%>;
		
		alert(prodReqId+"-------"+reqStatus);
		
		$.ajax({"url":"<%= application.getContextPath()%>/GetRequestNotificationDetailsAction","data":{"prodReqId":prodReqId,"reqStatus":reqStatus},"success":function(d){
			
				alert(d.success=='yes');
				alert("->Product Requested Id-------"+prodReqId);
				//if(d.success=='yes'){
					alert("Request Product Id--->"+d.reqProdId+"----");
					alert("Exchange product----"+d.excProdId);	
					var products = d.data;
					
					var row = $("#products-wrapper");
					
					var div2 = $("<div class='thumbnail' style='width:35%; float: left;'>");
					
					var imgTag = $("<img class='prod-image' src='data:image/jpeg;base64,"+d.reqProdImg+"' width='250px' height='250px'>");
					
			    	imgTag.appendTo(div2);
		    		   
		    		var div3 = $("<div class='caption'>");
		    		     		   
		    		$("<h4 class='pull-right'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d.reqProdPrice).appendTo(div3);
		    		$("<p class='pull-left' name='reqProdId'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d.reqProdId).appendTo(div3);
		    		   
		    		$("<h4>").html(d.reqProdName).appendTo(div3);
		    		   
		    		$("<p>").css("color", "green").html(d.reqProdDesc+"<br>Purchased Date:-"+d.reqProdPurchasedDate).appendTo(div3);
		    		   
		    		div3.appendTo(div2);
                    
                    div2.appendTo(row);
                   //div1.appendTo(row);
                   
                    var div2 = $("<div style='float: left;'>");
                    
                    $("<i class='fa fa-exchange' aria-hidden='true' style='font-size:250px; padding:20px'></i>").appendTo(div2);
                    div2.appendTo(row);
                    
         	        //var div1 = $("<div class='col-sm-4 col-lg-4 col-md-4'>");
					
				    var div2 = $("<div class='thumbnail' style='width:35%; float: left;'>");
					
					//var imgTag = $("<img class='img img-rounded' src='one plus.jpeg' width='200px'>");
					var imgTag = $("<img class='prod-image' src='data:image/jpeg;base64,"+d.excProdImg+"' width='250px' height='250px'>");
					
			    	imgTag.appendTo(div2);
		    		   
		    		var div3 = $("<div class='caption'>");
		    		     		   
		    		$("<h4 class='pull-right'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d.excProdPrice).appendTo(div3);
		    		$("<p class='pull-left' name='excProdId'>").css("color", "red").html("<i style='font-size:17px;' class='fa fa-inr'></i>"+d.excProdId).appendTo(div3);
		    		   
		    		$("<h4>").html(d.excProdName).appendTo(div3);
		    		   
		    		$("<p>").css("color", "green").html(d.excProdDesc+"<br>Purchased Date:-"+d.excProdPurchasedDate).appendTo(div3);
		    		   		    		
		    		div3.appendTo(div2);
		    		
                    div2.appendTo(row);
                    
                    var div4 = $("<div class='submit-wrapper form-group'>");
		    		var myForm = $("<form method='get' action='<%=application.getContextPath()%>/ApproveRejectAction'>");
		    		
		    		var textarea = $("<textarea name='rejectedReason' placeholder='Enter Rejected Reason Here' class='form-control' style='font-size:15px; margin-bottom:10px;'></textarea>");
		            textarea.appendTo(myForm);
		    		
		    	    var approveBtn = $('<input>').attr({
                     "type": "submit",
                     "name":"reqReplyBtn",
                     "value":"Approve",
                     "class":"myinp btn btn-success"
                    }).css({"width":"50%","margin-left":"3px"});
		    	      
                   approveBtn.appendTo(myForm);
                   
                   var rejectBtn = $('<input>').attr({
	                     "type": "submit",
	                     "name": "reqReplyBtn",
	                     "value":"Reject",
	                     "class":"myinp btn btn-danger"
	                   }).css({"width":"49%","margin-left":"3px"});
			    		   
	               rejectBtn.appendTo(myForm);
	               
	               $("<input type='hidden' name='prodReqId' value='"+prodReqId+"'>").appendTo(myForm);
	               $("<input type='hidden' name='reqProdId' value='"+d.reqProdId+"'>").appendTo(myForm);
	               $("<input type='hidden' name='excProdId' value='"+d.excProdId+"'>").appendTo(myForm);
	              // $("<input type='hidden' name='dateOfApproval' value='"+d.dateOfApproval+"'>").appendTo(myForm);
	               $("<input type='hidden' name='statusId' value='"+d.statusId+"'>").appendTo(myForm);
	               
	               myForm.appendTo(div4);
                   div4.appendTo(row);
                 	
		/* 	}else//{
				alert(d.data);
				alert("else block-----");
			} */
		}
	});	
});

	</script>

<!-- <style type="text/css">
 .thumbnail{        
    width: 200px; 
    height: 425px;
    overflow: auto;
}

.thumbnail img{
    width: 100%;
    height: 308px;
    display: block;
}
</style>
 -->
</head>
<body>

	<div id="header">
		<jsp:include page="inboxHeader.jsp"></jsp:include>
	</div>
	
	<div class="heading">
		<h1 style="color:blue; text-shadow: 2px 3px orange;">Requested products Exchnaging Information</h1>
	</div>
	

	<div class="container">
		<div class="row" id="products-wrapper" style="padding: 10px;"></div>
	</div>
	
	<div>
		<%  session=request.getSession(false); %>
	</div>
	
</body>
</html>