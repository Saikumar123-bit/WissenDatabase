<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product-Exchanging</title>

<link rel="Stylesheet" href="<%=application.getContextPath()%>/css/bootstrap.css">

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/bootstrap.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/bootstrap.min.js"></script>

<style type="text/css">
	#header{
		padding:10px;
	}
</style>

<script type="text/javascript">
	jQuery("document").ready(function(){
		var notiCount=0;
		$.ajax({ "url":"<%=application.getContextPath()%>/GetUserReqNotificationsAction","success":function(d){
				if(d.success=='yes'){
				var notifications=d.data;
				var notiArea = $("#notiArea");
				
				if(notifications.length>0){
					notiCount=notiCount+notifications.length;
			   
					var notiBadge = $("#notiBadge");
					notiBadge.html("<span class='badge badge-notify' id='notiCount' style='font-size:15px'></span>");	
					$("#notiCount").html(notiCount);
					
					var reqStatus;
					
					for(var item=0;item<notifications.length;item++) {
						reqStatus=notifications[item].product_request_status.status_id;
						
						var noti=$("<li><a href='<%=application.getContextPath()%>/NotificationReplyDeligateAction?product_request_id="+notifications[item].product_request_id+"&reqStatus="+notifications[item].product_request_status.status_id+"' class='alert alert-warning' style='margin: 5px'> Your Product "+notifications[item].request_product_id.product_name+" is Requested to<br>Exchange with "+notifications[item].exchange_product_id.product_name+".<br>On Date:- "+notifications[item].date_of_request+".</a></li>");
						noti.appendTo(notiArea);
					}
				}
			}else {
				var notiList=$("<li></li>");
				notiList.html("<a class='alert alert-warning' style='margin:5px'>Error in Fetching The Notifications</a>");
				notiList.appendTo(notiArea);
				alert(d.data);
				alert("GetUserRequestNotificationAction else block");
			}
		}			
	});
		$.ajax({			
			"url":"<%=application.getContextPath()%>/GetUserRejectedNotificationAction", "success":function(d){
				if(d.success=='yes'){
					var  notifications = d.data;
					var notiArea = $("#notiArea");
					
					if(notifications.length > 0){
						notiCount = notiCount+notifications.length;
						var notiBadge = $("#notiBadge");
						notiBadge.html("<span class='badge badge-notify' id='notiCount' style='font-size:15px'></span>")	
						$("#notiCount").html(notiCount);
						
						var reqStatus;
						for (var item = 0; item < notifications.length; item++) {							
							reqStatus = notifications[item].reqStatus;							
							var noti = $("<li><a href='<%=application.getContextPath()%>/NotificationReplyDeligateAction?product_request_id="+notifications[item].product_request_id+"&reqStatus="+notifications[item].product_request_status.status_id+"' class='alert alert-warning' style='margin: 5px'>Request Rejected to Exchange "+notifications[item].request_product_id.product_name+" with<br>your Product "+notifications[item].exchange_product_id.product_name+".<br>Click to see Reason.</a></li>");
								noti.appendTo(notiArea);		
						}
					}
				}else{
					var notiList = $("<li></li>");
					notiList.html("<a class='alert alert-warning' style='margin: 5px'>Error in Fething Notifications</a>");
					notiList.appendTo(notiArea);
					alert(d.data);
					alert("GetUserRejectedNotificationAction else block ");
				}
			}			
		});
});
</script>

</head>
<body>

<div class="nav navbar-inverse">
	<div class="contanier-fluid">
			
			<ul class="nav navbar-nav" >
				<li><a href="about.jsp" >About Product-Exchage</a></li>
				<li><a href="homeLogin.jsp" >Home Page</a></li>
				<li><a href="AddProduct.jsp" >Add Products</a></li>
				<li><a href="ViewProduct.jsp" >View Products</a></li>
				<li><a href="UpdateDetails.jsp" >Edit Your Details</a></li>
				<li><a href="ExchangedCompleted.jsp" >Exchanged Products</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="ChangePassword.jsp" >Change Password</a></li>
						
				<li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					 <span class="glyphicon glyphicon-comment" style="font-size:25px;" id="notiBadge">  </span> </a>				
 				<ul class="dropdown-menu" id="notiArea" style="padding: 5px">
 					</ul>
 				</li> 
				
				<li><a href="<%=application.getContextPath()%>/logout" class="glyphicon glyphicon-log-out">Logout</a></li>
			</ul>
	</div>
</div>
</body>
</html>