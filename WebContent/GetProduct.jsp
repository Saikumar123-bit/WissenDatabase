<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<form action="product" method="post">
		<h1 style="color: blue;">Your Availible Products</h1>
</form>

<h2 style="color:red">${msg}</h2>	
	
<h3 style="color: red">${message }</h3>	

<script type="text/javascript">
$("document").ready(function() {		
		$.get( "<%=application.getContextPath()%>/product", function(data) {
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
			
				$.each( data, function(k, v) {
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
							+ "</td></tr><tr><td>Product Name : </td><td>"+ pname
							+ "</td></tr><tr><td>Price :  </td><td>"+ price
							+ "</td></tr><tr><td>Description : </td><td>"+ pdesc
							+ "</td></tr><tr><td>Purchaced Date : </td><td>"+ pdate
							+ "</td></tr><tr><td>Exchange Status : </td><td>"+ pqua
							+ "</td></tr><tr><td>Owner Id : </td><td>"+ ownerId
							+ "</td></tr><tr><td><button onclick=exchange(this); style='width:auto;background-color: green; font-size: 22px;'><a href='ExchangeProduct.jsp'>Exchange</a></button></td></tr></table></center></div><br><br>";
												
							if (j == 1) {
								$("#tab1").append(table);
								j++;
							} else if (j == 2) {
								$("#tab2").append(table);
								j++;
							}
						});
		});
});
</script>

</html>