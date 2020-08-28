<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductExchange--Project</title>

<script type="text/javascript" src="<%=application.getContextPath()%>/jquery/jquery-1.11.3.js"></script>
<script type="text/javascript">
        history.pushState(null,null, '');
        window.addEventListener('popstate',function(event){
        	history.pushState(null,null,' ');
        });
</script> 

<script type="text/javascript">

	jQuery("document").ready(function() {
			var cnt =$("#country");
			var st = $("#state");
			var ct = $("#city");
			var status = $("#status");
			
			$.ajax({"url":"<%=application.getContextPath()%>/status", "success":function(d){
				if(d.success=='yes'){
					var cData = d.data;
					cnt.html("<option value=''>Select One</option>");
					for(var ind=0;ind<cData.length;ind++) {
						cnt.append("<option value='"+cData[ind].status_id+"'>"+cData[ind].status+"</option>");
					}
					}else{
						alert(d.data);
					}
				}
			});
			
			$.ajax({ "url":"<%=application.getContextPath()%>/country", "success":function(d){
					if(d.success=='yes'){
					var cData = d.data;			
						cnt.html("<option value=''>Select One</option>");
						for (var ind = 0; ind < cData.length; ind++) {
							cnt.append("<option value='"+cData[ind].country_Id+"'>"+
							cData[ind].country_Name+"</option>");
							
						}
					}else{
						alert(d.data);			
					}	
				}
			});
			cnt.change(function(){
				var cid = $(this).val();
				if(cid!=''){
					$.ajax({
						"url":"<%=application.getContextPath()%>/state",
						"data":{"cid":cid},
						"success":function(d){
							if(d.success=='yes'){
								var cData = d.data;
								st.html("<option value=''>Select One</option>");
								for(var ind=0;ind<cData.length;ind++){
									st.append("<option value='"+cData[ind].state_Id+"'>"+cData[ind].state_name+"</option>");
								}
							}else{
								alert(d.data);
							}
						},
						"error":function(){
							alert("ERROR");	
						}
					});
				}
				});
			
			//call on change of state button click
			st.change(function(){
			//storing the value of current click into cityid
				   var cityid = $(this).val();
			if(cityid!=''){
						$.ajax({
								//url of destination page
								"url":"<%=application.getContextPath()%>/city",
								//sending name and values in data
								"data":{"state":cityid},
								"success":function(d){
									if(d.success=='yes'){
									var cData = d.data;
									console.log(cData);
										ct.html("<option value=''>Select One</option>");
										for (var ind = 0; ind < cData.length; ind++) {
											ct.append("<option value='"+cData[ind].city_id+"'>"+cData[ind].city_name+"</option>");
										}
									}else{
										alert(d.data);
										
									}	
			
						},
						"error":function(){
							alert("city error");
						}
					});
			}
			});
			//$("#form").validationEngine
		});
			</script>
			
	<style type="text/css">
		input[type=text], select {
			width:50%;
			color:blue;
			background-color: pink;	
			padding: 12px 20px;
			display: inline-block;
			border: 3px solid green;
			border-radius: 4px;
			box-sizing:border-box;
		}
		input[type=text]:focus {
			width:60%;
			color:lightpink;
			background-color: blue;
		}
		
		input[type=password] {
		width: 50%;
		color:blue;
		background-color:yellow;
		padding:12px 20px;
		display:inline-block;
		border:3px solid red;
		border-radius:4px;
		box-sizing:border-box;
		}
		input[type=password]:focus {
			width:60%;
			color:red;
			background-color: blue;
		}
		
		input[type=submit], select {
		width: 50%;
		color:blue;
		background-color:pink;
		padding:12px 20px;
		}
		input[type=submit]:hover {
			width:60%;
			color:lightpink;
			background-color: blue;
			color:red;
			font-size:20px;
		}
		
		input[type=file], select {
		width:50%;
		color:blue;
		background-color: pink;
		padding: 12px 20px;
		border: 3px solid green;
		border-radius:4px;
		box-sizing:border-box;
		}
		
		input[type=file]:hover {
		width:60%;
		
		}
		
		div {
			border-radius :5px;
			background-color: lightblue;
			padding:20px;
		}
	</style>		
</head>
<body style="background-color: violet">
	 <h5 style="color:red"><div>${message}</div></h5>
<h1><center><font style="color:blue">Fill Your Registration Details</font></center></h1>

<div>
	<form action="registration" name="register" method="post" style="float: center">
	
		<label for="name">Name</label><br>
		 <input type="text" name="name" placeholder="Enter Your Name"><br><br>
	
		<label for="email">Email</label><br>
		 <input type="text" name="email" placeholder="Enter Your Email"><br><br>
	
		<label for="password">Password</label><br>
		 <input type="password" name="password" placeholder="Enter Your Password"><br><br>
	 
		 <label for="mobileno">Mobile Number</label><br>
		 <input type="text" name="mobileno" placeholder="Enter Your Mobile Number"><br><br>

		<label for="Address">Enter Your Address</label><br>
		<input type="text" name="address" placeHolder="Enter Your Address"><br><br>
	
		<label for="pincode">PinCode</label><br>
		<input type="text" name="pincode" placeholder="Enter Your Pincode"><br><br>
		
		<label for="status">Availible Status</label><br>
		<select class="form-control" name="status" id="status">
			<option value=""></option>
			<option value=""></option>
			<option value=""></option>
			<option value=""></option>
		</select><br><br>
		
		<label for="country">Country</label><br>
		<select class="form-control" name="country" id="country"><br><br>
			<option value=""></option>
			<option value=""></option>
			<option value=""></option>
			<option value=""></option>
		</select>
		<br><br>
		
		<label for="state">State</label><br>
			<select class="form-control" name="state" id="state">
			<option value=""></option>
		</select>
		<br><br>

		<label for="city">City</label><br>

		<select class="form-control" name="city"  id="city"><br><br>
			<option value=""></option>
		</select>
		<br><br>
		
		<label for="profilePic"> Upload Your ProfilePicture: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
		<input type="file" class="form-control" name="profilePic" required="" /> <br>
		
		<br><br><br>
		<input type="submit" value="REGISTER DETAILS"><br><br> 

		</form>
	</div>
	
</body>
</html>