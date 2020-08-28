<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notification Approve Reply</title>

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
		
			$.ajax({"url":"<%=application.getContextPath()%>/GetApprovedNotificationDetailsAction","success":function(d){
					
				alert("GetApprovedNotificationsAction------Successful msgs");
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
		<h1 style="color:blue; text-shadow: 2px 3px orange;">Congratulations Products Exchanged</h1>
	</div>
	
	<a href="ExchangedCompleted.jsp">Check here</a>
	
	<div class="container">
		<div class="row" id="products-wrapper" style="padding: 10px;"></div>
	</div>
	
</body>
</html>