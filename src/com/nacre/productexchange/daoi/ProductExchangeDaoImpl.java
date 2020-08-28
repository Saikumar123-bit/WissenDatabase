package com.nacre.productexchange.daoi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nacre.productexchange.constants.IntegerContants;
import com.nacre.productexchange.constants.SqlQuery;
import com.nacre.productexchange.dbutils.DbUtils;
import com.nacre.productexchange.dto.ApproveRejectBean;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.ProductRequestDto;
import com.nacre.productexchange.dto.StatusDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;
import com.nacre.productexchange.formbean.NotificationDetailsFormBean;
import com.nacre.productexchange.formbean.ProductRequestFormBean;

public class ProductExchangeDaoImpl implements ProductExchangeDaoI{

	Connection con=null;
	PreparedStatement ps=null;
	
	@Override
	public int requestExchange(ProductRequestFormBean productrequest) throws SQLException, DbException {

		System.out.println("Control in requestExchane() on dao");
		
		int result=0;
		Date d=null;
		long millis=0;
		
		//create database connection
		con=DbUtils.getConnection();
		
		//prepare query
		con.setAutoCommit(false);
		ps=con.prepareStatement(SqlQuery.INSERT_PRODUCT_REQUEST);
		
		//set values to query string
		ps.setInt(1, productrequest.getExchangeProductId());
		ps.setInt(2, productrequest.getRequestProductId());
		
		millis=System.currentTimeMillis();
		d=new Date(millis);
		ps.setDate(3, d);
		ps.setInt(4, IntegerContants.REQUEST_STATUS_ID_PRODUCT);
		
		result=ps.executeUpdate();
		
		if(result==1){
			con.commit();
		}else{
			con.rollback();
		}
		
		System.out.println("Prepared query is--->@@@@@@@@@@@@@@@@@@@@@"+result);
		return result;
	}
	
	@Override
	public List<ProductRequestDto> getExchangeProducts(Integer userId)throws SQLException, Exception{
		System.out.println("Now control dao on getexchnageProducts");
		
		ResultSet rs=null;
		List<ProductRequestDto> list=null;
		ProductRequestDto dto=null;
		ProductDto reqProdDetails=null;
		ProductDto excProdDetails=null;
		PreparedStatement ps=null;
		StatusDto reqProdAvailStatus=null;
		StatusDto excProdAvailStatus=null;
		StatusDto reqStatus=null;
		UserDetailsDto reqProdOwnerId=null;
		UserDetailsDto excProdOwnerId=null;
		
		//create productRequest arraylist object
		list=new ArrayList();
		
		try{
			
			//create databse connection object
			con=DbUtils.getConnection();
			
			//create preparedStatement object
			ps=con.prepareStatement(SqlQuery.View_Exchanged_Products);
			
			//set values to query param
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			System.out.println("View_Exchanged_Products Query is--->"+ps);
			
			//execute the query
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				System.out.println("Requested exchnaeg details");
				System.out.println(rs.getInt(1)+"----"+rs.getInt(2)+"----"+rs.getString(3)+"------"+"image here"+"--------"+rs.getInt(5)+"---"+rs.getString(6)+"----"+rs.getDate(7)+"----"+rs.getInt(8));
				
				//create productrequest object
				dto=new ProductRequestDto();
				
				dto.setProduct_request_id(rs.getInt(1));
				
				//Adding Requested product Details
				reqProdDetails = new ProductDto();
				
				//set db values to productdto class
				reqProdDetails.setProduct_id(rs.getInt(2));				
				reqProdDetails.setProduct_name(rs.getString(3));
				reqProdDetails.setProduct_image( rs.getBinaryStream(4));
				reqProdDetails.setProduct_price(rs.getInt(5));
				reqProdDetails.setProduct_description(rs.getString(6));
				reqProdDetails.setPurchased_date(rs.getDate(7));
				
				//create userdetials dto class object
				reqProdOwnerId=new UserDetailsDto();
				reqProdOwnerId.setUserid(rs.getInt(8));
				System.out.println("Current Login Usr id----"+rs.getInt(8));
				
				reqProdDetails.setOwnerid(reqProdOwnerId);
				dto.setRequest_product_id(reqProdDetails);
				
				
				//adding excproduct details
				excProdDetails=new ProductDto();				
				System.out.println("exchange details user id----"+rs.getInt(9)+"---"+rs.getString(10)+"----"+"image here"+"---"+rs.getInt(12)+"---"+rs.getString(13)+"---"+rs.getDate(14)+"---"+rs.getInt(15));
				System.out.println(rs.getString(16)+"---"+rs.getInt(17));
				
				excProdDetails.setProduct_id(rs.getInt(9));
				excProdDetails.setProduct_name(rs.getString(10));
				excProdDetails.setProduct_image( rs.getBinaryStream(11));
				excProdDetails.setProduct_price(rs.getInt(12));
				excProdDetails.setProduct_description(rs.getString(13));
				excProdDetails.setPurchased_date(rs.getDate(14));
				
				excProdOwnerId=new UserDetailsDto();
				excProdOwnerId.setUserid(rs.getInt(15));
				System.out.println("Exchnage User id---"+rs.getInt(15));
				excProdDetails.setOwnerid(excProdOwnerId);
				
				dto.setDate_of_request(rs.getString(16));
				reqStatus=new StatusDto();
				reqStatus.setStatus_id(rs.getInt(17));
				
				excProdDetails.setProduct_availibility_status(reqStatus);
				
				dto.setExchange_product_id(excProdDetails);
				
				//add all values to ProductRequestdto
				list.add(dto);
				
				System.out.println("From here control returning ");
			}			
			
		}catch(SQLException sql){
			System.out.println("Error with sql Problem--->"+sql.getMessage());
			throw new DbException("db problem");
		}
		return list;
	}
	
	@Override
	public boolean checkExchangeProduct(ProductDto product) throws SQLException, DbException {
		
		ResultSet rs=null;
		
		//create database connection object
		con=DbUtils.getConnection();
		
		//prepare prepapredstatement query
		ps=con.prepareStatement(SqlQuery.Check_Exchabnge_Product);
		ps.setInt(1, product.getOwner_id().getUserid());
		ps.setInt(2, product.getProduct_id());
		
		System.out.println("Value in dao is--->"+product.getOwner_id().getUserid()+"--------"+product.getProduct_id());
		
		//execute the query
		rs=ps.executeQuery();
		
		if(rs.next()){
			return true;
		}
		return false;
	}

	@Override
	public List<ProductRequestDto> getRequestNotificationsDetails(Integer UserId, Integer productRequestId,Integer requestStatus) throws SQLException, Exception {
		
		System.out.println("Now control in getrequestNotificationDetails on dao");
		
		List<ProductRequestDto> list=null;
		ProductRequestDto dto=null;
		ProductDto reqProdDetails=null;
		ProductDto excProdDetails=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StatusDto reqProdAvailStatus=null;
		StatusDto excProdAvailStatus=null;
		StatusDto reqStatus=null;
		UserDetailsDto reqProdOwnerId=null;
		UserDetailsDto excProdOwnerId=null;
		
		
		list=new ArrayList();
		
		//create databse connection object
		con=DbUtils.getConnection();
		System.out.println("Connection is established");
		System.out.println(UserId+"------"+productRequestId+"------------"+requestStatus);
		
		try{
			ps=con.prepareStatement(SqlQuery.GET_PROD_REQ_NOTIFICATION_DETAILS);
			
			//set param values to Prepare
			ps.setInt(1, UserId);
			ps.setInt(2, productRequestId);
			ps.setInt(3, requestStatus);
			
			rs=ps.executeQuery();
			System.out.println("hahhahahahhhhhhhhhhhhhhhhh------------query req noti"+ps);
			
			System.out.println("Query got executed---");
			
			if(rs.next()){
				//create productrequestdto object
				dto=new ProductRequestDto();
				
				System.out.println("Getting details from databse");
				System.out.println("Requested product Details on dao--->\n");
				System.out.println(rs.getInt(1)+"--"+rs.getInt(2)+"--"+rs.getString(3)+"--"+rs.getInt(4)+"--"+rs.getString(5)+"--"+rs.getDate(6)+"--"+rs.getInt(7)+"--"+rs.getInt(9));
				System.out.println("Requested Image is-->"+rs.getBinaryStream(8));
				
				dto.setProduct_request_id(rs.getInt(1));
				
				//Adding Requested product Details
				reqProdDetails = new ProductDto();
				
				//set param values to ProductDto class
				reqProdDetails.setProduct_id(rs.getInt(2));
				reqProdDetails.setProduct_name(rs.getString(3));
				reqProdDetails.setProduct_price(rs.getInt(4));
				reqProdDetails.setProduct_description(rs.getString(5));
				reqProdDetails.setPurchased_date(rs.getDate(6));
				
				//create statusDto object
				reqProdAvailStatus=new StatusDto();
				reqProdAvailStatus.setStatus_id(rs.getInt(7));
				System.out.println("7the valjjjjjjjjjj---------"+rs.getInt(7));
				reqProdDetails.setProduct_availibility_status(reqProdAvailStatus);
				reqProdDetails.setProduct_image( rs.getBinaryStream(8));
				
				//create userdetails dto class object
				reqProdOwnerId = new UserDetailsDto();
				reqProdOwnerId.setUserid(rs.getInt(9)); //problem here may arise
				
				reqProdDetails.setOwnerid(reqProdOwnerId);
				
				dto.setRequest_product_id(reqProdDetails);

				//Adding Exchanging Product details
				excProdDetails=new ProductDto();
				
				System.out.println("Exchange Product Details on dao --\n");
				System.out.println(rs.getInt(10)+"----"+rs.getString(11)+"----"+rs.getInt(12)+"----"+rs.getString(13)+"-----"+rs.getDate(14)+"----"+rs.getInt(15)+"==="+rs.getInt(17)+"---"+rs.getString(18)+"---"+"----"+rs.getDate(19)+"-----"+rs.getInt(20)+"---");
				System.out.println("The 17th value is--->"+rs.getInt(17));
				System.out.println("Request STatus--->"+rs.getInt(20));
				System.out.println("exchange imgae------->on dao "+rs.getBinaryStream(16));
				
				excProdDetails.setProduct_id(rs.getInt(10));
				excProdDetails.setProduct_name(rs.getString(11));
				excProdDetails.setProduct_price(rs.getInt(12));
				excProdDetails.setProduct_description(rs.getString(13));
				excProdDetails.setPurchased_date(rs.getDate(14));
				
				//create exchange Statusdto object
				excProdAvailStatus = new StatusDto();
				excProdAvailStatus.setStatus_id(rs.getInt(15));
				System.out.println("Prod savl status--"+rs.getInt(15));
				excProdDetails.setProduct_availibility_status(excProdAvailStatus);
				excProdDetails.setProduct_image(rs.getBinaryStream(16));
				
				//create excProdOwnerid exchaneg object
				excProdOwnerId=new UserDetailsDto();
				excProdOwnerId.setUserid(rs.getInt(17));
				
				excProdDetails.setOwnerid(excProdOwnerId);
				
				//add to productdto class object
				dto.setDate_of_request(rs.getString(18));
				//dto.setDateOfApproval(rs.getString(19));
				//dto.setDate_of_approval(rs.getString(19)); //changed here getLong to getString
				dto.setDate_of_approval(rs.getDate(19));
				reqStatus=new StatusDto();
				
				reqStatus.setStatus_id(rs.getInt(20));
				
				dto.setProduct_request_status(reqStatus);
				
				dto.setExchange_product_id(excProdDetails);

				//adding above all values to dto class object
				list.add(dto);
				
				System.out.println("COntrol moving to GetNotificationDetailsAction");
				
			}
			
		}catch(SQLException e){
			System.out.println("problem in GetRequeNotiDetails on SQLException ---->"+e.getMessage());
		}
		
		return list;
	}

	@Override
	public GetNotificationDetailsFormBean getRejNotificationSetails(NotificationDetailsFormBean notificationDetailsFormBean) throws SQLException, Exception {
		
		ResultSet rs=null;
		ProductDto excProdDetails=null;
		ProductDto reqProdDetails=null;
		StatusDto excProdAvailStatus=null;
		StatusDto reqProdAvailStatus=null;
		UserDetailsDto excProdOwnerId=null;
		UserDetailsDto reqProdOwnerId=null;
		StatusDto reqStatus=null;
		
		
		//create databse connection object
		con=DbUtils.getConnection();
		GetNotificationDetailsFormBean gndfb=null;
		
		try{
			con.setAutoCommit(false);
			
			//create preparedstatemnt query object
			ps=con.prepareStatement(SqlQuery.GET_REQ_PROD_REJ_NOTI_DETAILS);
			ps.setInt(1, notificationDetailsFormBean.getUserId().getUserid());
			ps.setInt(2, notificationDetailsFormBean.getProdReqId());
			ps.setInt(3, notificationDetailsFormBean.getReqStatus());
			
			//execute the query
			rs=ps.executeQuery();
			
			if(rs.next()){
				
				//create object
				gndfb=new GetNotificationDetailsFormBean();
				
				gndfb.setProdReqId(rs.getString(1));
				
				//Adding requested product Details
				excProdDetails=new ProductDto();
				excProdDetails.setProduct_id(rs.getInt(2));
				excProdDetails.setProduct_name(rs.getString(3));
				excProdDetails.setProduct_price(rs.getInt(4));
				excProdDetails.setProduct_description(rs.getString(5));
				excProdDetails.setPurchased_date(rs.getDate(6));
				
				excProdAvailStatus=new StatusDto();
				excProdAvailStatus.setStatus_id(rs.getInt(7));
				excProdDetails.setProduct_availibility_status(excProdAvailStatus);
				
				excProdDetails.setProduct_image(rs.getBinaryStream(8));
				
				excProdOwnerId=new UserDetailsDto();
				excProdOwnerId.setUserid(rs.getInt(9));
				excProdDetails.setOwnerid(excProdOwnerId);
				
				//gndfb.setExcProdId(excProdDetails);
				
				//Adding Exchanging product Details
				reqProdDetails=new ProductDto();
				
				reqProdDetails.setProduct_id(rs.getInt(10));
				reqProdDetails.setProduct_name(rs.getString(11));
				reqProdDetails.setProduct_price(rs.getInt(12));
				reqProdDetails.setProduct_description(rs.getString(13));
				reqProdDetails.setPurchased_date(rs.getDate(14));
				
				reqProdAvailStatus=new StatusDto();
				reqProdAvailStatus.setStatus_id(rs.getInt(15));
				reqProdDetails.setProduct_availibility_status(reqProdAvailStatus);
				
				reqProdDetails.setProduct_image(rs.getBinaryStream(16));
				
				reqProdOwnerId=new UserDetailsDto();
				reqProdOwnerId.setUserid(rs.getInt(17));
				reqProdDetails.setOwnerid(reqProdOwnerId);
				
				//gndfb.setReqProdId(reqProdDetails);
				//gndfb.setDateOfApproval(rs.getString(18));
				//gndfb.setDateOfApproval(rs.getString(18)); //changed here getLong to get String
				gndfb.setDateOfApproval(rs.getDate(18)); //getString to getDate
				
				reqStatus=new StatusDto();
				reqStatus.setStatus_id(rs.getInt(19));
				gndfb.setReqStatus(reqStatus.toString());
				
				gndfb.setRejectedReason(rs.getString(20));
				
			}
			con.commit();
		}catch(SQLException e){
			System.out.println("hahahah problem here is--->"+e.getMessage());
			e.printStackTrace();
		}
		
		return gndfb;
	}

	@Override
	public List<ProductRequestDto> getUserReqNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		List<ProductRequestDto> userNotificationList=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ProductRequestDto userNotification=null;
		ProductDto reqProdDetails=null;
		ProductDto excProdDetails=null;
		StatusDto reqStatus=null;
		UserDetailsDto requser=null;
		UserDetailsDto excuser=null;
		
		//create object
		con=DbUtils.getConnection();
		userNotificationList=new ArrayList<ProductRequestDto>();
		System.out.println("Now control in GetUserRequestNotifications dao()");
		try{
			con.setAutoCommit(false);
			ps=con.prepareStatement(SqlQuery.GET_USER_REQ_NOTIFICATIONS);
			ps.setInt(1, user.getUserid());
			System.out.println("req query prepared---"+ps);
			
			//execute query
			rs=ps.executeQuery();
			
			System.out.println("After Execution of Query----checking req details");
			while(rs.next()){
				
				userNotification=new ProductRequestDto();
				userNotification.setProduct_request_id(rs.getInt(1));
				
				reqProdDetails = new ProductDto();
				reqProdDetails.setProduct_id(rs.getInt(2));
				reqProdDetails.setProduct_name(rs.getString(3));
				
				requser=new UserDetailsDto();
				requser.setUserid(rs.getInt(4));
				reqProdDetails.setOwnerid(requser);
				
				userNotification.setRequest_product_id(reqProdDetails);
				
				excProdDetails = new ProductDto();
				excProdDetails.setProduct_id(rs.getInt(5));
				excProdDetails.setProduct_name(rs.getString(6));
				
				excuser=new UserDetailsDto();
				excuser.setUserid(rs.getInt(7));
				excProdDetails.setOwnerid(excuser);
				
				userNotification.setExchange_product_id(excProdDetails);
				
				userNotification.setDate_of_request(rs.getString(8));
				
				reqStatus = new StatusDto();
				reqStatus.setStatus_id(rs.getInt(9));
				
				userNotification.setProduct_request_status(reqStatus);
				userNotificationList.add(userNotification);
				
				//System.out.println(rs.getInt(1)+"--->"+rs.getInt(2)+"---"+rs.getString(3)+"--"+rs.getInt(4)+"--"+rs.getString(5)+"--"+rs.getString(6)+"---"+rs.getInt(7));
				
				System.out.println("Current user id--->"+rs.getInt(4));
				System.out.println("Exchanging person user id--->"+rs.getInt(7));
				
			}
			con.commit();
		}catch(SQLException e){
			System.out.println("Checkout the probem here --->"+e.getMessage());
		}
		
		return userNotificationList;
	}

	@Override
	public List<ProductRequestDto> getUserApprovedNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		System.out.println("Now control in getUserApprovedNotifications() on dao");
		List<ProductRequestDto> userNotificationList=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ProductRequestDto userNotification=null;
		ProductDto reqProdDetails=null;
		ProductDto excProdDetails=null;
		StatusDto reqStatus=null;
		
		//create obj and connect to databse
		con=DbUtils.getConnection();
		
		userNotificationList = new ArrayList<ProductRequestDto>();
		
		try{
			System.out.println("User id---->"+user.getUserid());
			con.setAutoCommit(false);
			ps=con.prepareStatement(SqlQuery.GET_USER_APPROVED_NOTIFICATIONS);
			ps.setInt(1, user.getUserid());
			
			System.out.println("Approved Query prepared---->"+ps);
			
			//execute the query
			rs=ps.executeQuery();
	
			System.out.println("After execution of query--->");
			while(rs.next()){
				
				userNotification = new ProductRequestDto();
				userNotification.setProduct_request_id(rs.getInt(1));
				
				//System.out.println(rs.getInt(1)+"--"+rs.getInt(2)+"--"+rs.getString(3)+"--"+rs.getInt(4)+"--"+rs.getString(5)+"----"+rs.getString(6)+"--"+rs.getInt(7));
				System.out.println(rs.getInt(1)+"---"+rs.getInt(2)+"---"+rs.getString(3)+"----"+rs.getInt(4)+"---"+rs.getInt(5)+"----"+rs.getString(6)+"---"+rs.getInt(7)+"---"+rs.getString(8)+"---"+rs.getInt(9));
				
				reqProdDetails = new ProductDto();
				reqProdDetails.setProduct_id(rs.getInt(2));
				reqProdDetails.setProduct_name(rs.getString(3));
				reqProdDetails.setProduct_price(rs.getInt(4));
				userNotification.setRequest_product_id(reqProdDetails);
				
				excProdDetails = new ProductDto();
				excProdDetails.setProduct_id(rs.getInt(5));
				excProdDetails.setProduct_name(rs.getString(6));
				excProdDetails.setProduct_price(rs.getInt(7));
				userNotification.setExchange_product_id(excProdDetails);
				
				userNotification.setDateOfApproval(rs.getString(8));
				
				reqStatus = new StatusDto();
				reqStatus.setStatus_id(rs.getInt(9));
				userNotification.setProduct_request_status(reqStatus);
				
				userNotificationList.add(userNotification);
			}
			con.commit();
		}catch(SQLException e){
			System.out.println("Problem in sql exeption-->"+e.getMessage());
			e.printStackTrace();
		}
		
		return userNotificationList;
	}

	@Override
	public List<ProductRequestDto> getUserRejectedNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		List<ProductRequestDto> userNotificationList=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ProductRequestDto userNotification=null;
		ProductDto excProdDetails=null;
		ProductDto reqProdDetails=null;
		StatusDto reqStatus=null;
		
		//create object
		con=DbUtils.getConnection();
		userNotificationList=new ArrayList<ProductRequestDto>();
		
		try{
			con.setAutoCommit(false);
			ps=con.prepareStatement(SqlQuery.GET_USER_REJECTED_NOTIFICATIONS);
			ps.setInt(1, user.getUserid());
			System.out.println("Rejected Notification query----"+ps);
			//excute query
			rs=ps.executeQuery();
			System.out.println("After execution of query--->");
			
			userNotification=new ProductRequestDto();
			excProdDetails=new ProductDto();
			reqProdDetails = new ProductDto();
			reqStatus=new StatusDto();
			
			while(rs.next()){
				
				//
				userNotification.setProduct_request_id(rs.getInt(1));
				
				//
				excProdDetails.setProduct_id(rs.getInt(2));
				excProdDetails.setProduct_name(rs.getString(3));
				
				//
				userNotification.setExchange_product_id(excProdDetails);
				
				//
				reqProdDetails.setProduct_id(rs.getInt(4));
				reqProdDetails.setProduct_name(rs.getString(5));
				userNotification.setRequest_product_id(reqProdDetails);
				
				reqStatus.setStatus_id(rs.getInt(6));
				userNotification.setProduct_request_status(reqStatus);
				
				userNotificationList.add(userNotification);
				
				System.out.println(rs.getInt(1)+"--"+rs.getInt(2)+"---"+rs.getString(3)+"---"+rs.getInt(4)+"---"+rs.getString(5)+"----"+rs.getInt(6));
				
			}
			con.commit();
		}catch(SQLException e){
			System.out.println("e-->"+e.getMessage());
		}
		
		return userNotificationList;
	}

	@Override
	public boolean reqApproved(ApproveRejectBean approveRejectBean) throws SQLException, Exception {
		
		PreparedStatement ps1=null,ps2=null,ps3=null,ps4=null;
		//long millis=0;
		String d=null; //changed return type date to string
		int count1=0,count2=0,count3=0,count4=0;
		
		//create databse connection
		con=DbUtils.getConnection();
		
		try{
			con.setAutoCommit(false);
			ps1=con.prepareStatement(SqlQuery.REQ_APPROVED);
			d=approveRejectBean.getProdReqId().getDateOfApproval();
			//millis=approveRejectBean.getProdReqId().getDateOfApproval();
			//System.out.println("Time in milli seconds----"+millis);
			System.out.println("Time in milli seconds---"+d);
			//d=new Date(millis);
			
			//System.out.println(d+"-----"+approveRejectBean.getProdReqId().getProduct_request_status().getStatus_id()+"----"+approveRejectBean.getProdReqId().getProduct_rejected_reason()+"----"+approveRejectBean.getProdReqId().getProduct_request_id());
			System.out.println("-----"+approveRejectBean.getProdReqId().getStatusId()+"----"+approveRejectBean.getProdReqId().getProduct_rejected_reason()+"----"+approveRejectBean.getProdReqId().getProduct_request_id());
			
			//set values to preparedStatement object
			//ps1.setString(1, d);
			//ps1.setInt(2, approveRejectBean.getProdReqId().getProduct_request_status().getStatus_id());
			//ps1.setInt(1, approveRejectBean.getProdReqId().getStatusId());
			//ps1.setInt(1, IntegerContants.REQUEST_STATUS_ID_APPROVE);
			ps1.setString(1, approveRejectBean.getProdReqId().getProduct_rejected_reason());
			ps1.setInt(2, approveRejectBean.getProdReqId().getProduct_request_id());
			System.out.println("here query---***********  6666666    *************"+approveRejectBean.getProdReqId().getProduct_request_id());
			
			//execute the query
			count1=ps1.executeUpdate();
			System.out.println("First Query---->"+count1   );
			System.out.println("First query executed result is----"+count1);
			
			
				ps2=con.prepareStatement(SqlQuery.UPDATE_PROD_REQ_STATUS_TO_APPROVED);
				
				System.out.println(approveRejectBean.getReqProdId().getProduct_id()+"---------"+approveRejectBean.getExcProdId().getProduct_id());
				
				ps2.setInt(1, approveRejectBean.getReqProdId().getProduct_id());
				ps2.setInt(2, approveRejectBean.getExcProdId().getProduct_id());
				
				//ps2.setInt(2, approveRejectBean.getExcProdId().getProduct_id());
				
				//execute the query
				count2=ps2.executeUpdate();
				System.out.println("Second Query---->"+ps2);
				System.out.println("second time query executed result is-----"+count2);
				
				
					
					/*if(count3>0){
						ps4=con.prepareStatement(SqlQuery.UPDATE_EXC_PROD_STATUS_TO_ELIGIBLE);
						
						//EXECUTE THE QUERY
						count4=ps4.executeUpdate();
						System.out.println("Fourth Query -->"+ps4);
						System.out.println("Query Exceuted and the Result is---->"+count4);*/
						
						//if(count4>0){
							
							ps3=con.prepareStatement(SqlQuery.UPDATE_PROD_REQ_STATUS_TO_REJECTED);
							ps3.setString(1, "Exchanged With Other Request");
							ps3.setInt(2, approveRejectBean.getProdReqId().getProduct_request_id());
							//ps3.setInt(3, approveRejectBean.getProdReqId().getProduct_request_id());
							
							//exeute the query
							count3=ps3.executeUpdate();
							System.out.println("Third Query---->"+ps3);
							System.out.println("Third time query executed resuult is----"+count3);	
							
							
						//}else{ con.rollback();}
					/*//}else{
						con.rollback();
						return true;*/
					
				//}else { con.rollback(); }
	      if(count1>0 && count2>0)	
	      {
	    	  con.commit();
			return true;	
			}else {
				con.rollback();
			  return false;
			}
		}catch(SQLException e){
			System.out.println("Problem with--->"+e.getMessage());
			e.printStackTrace();
			con.rollback();
			return false;
			
		}
		
		
	}

	@Override
	public boolean reqRejected(ApproveRejectBean approveRejectBean) throws SQLException, Exception {
		
		PreparedStatement ps1=null,ps2=null;
		long millis=0;
		//Date d=null;
		String d=null; //changed return type date to string
		int count1=0,count2=0;
		
		//create databse connection 
		con=DbUtils.getConnection();
		try{
			con.setAutoCommit(false);
			ps1=con.prepareStatement(SqlQuery.REQ_REJECTED);
			
			//millis=approveRejectBean.getProdReqId().getDate_of_approval();
			//d=approveRejectBean.getProdReqId().getDateOfApproval();
			//millis=approveRejectBean.getProdReqId().getDateOfApproval();
			//System.out.println("Time in milli seconds----"+millis);
			//System.out.println("Time in milli seconds---"+d);
			//d=new Date(millis);
			
			
			//d=new Date(millis);
			//set values query param
			//ps1.setString(1, d);
			
			ps1.setString(1, approveRejectBean.getProdReqId().getProduct_rejected_reason());
			ps1.setInt(2, approveRejectBean.getExcProdId().getProduct_id());
			ps1.setInt(3, approveRejectBean.getProdReqId().getProduct_request_id());
			
			System.out.println("Rejected Query prepared ----"+ps1);
			
			//execute the query
			count1=ps1.executeUpdate();
			System.out.println("First query executed result---"+count1);
			if(count1==1){
				ps2=con.prepareStatement(SqlQuery.UPDATE_EXC_PROD_STATUS_TO_ELIGIBLE);
				System.out.println("Second Rejected Query prepared ---->"+ps2);
				count2=ps2.executeUpdate();
				System.out.println("Second query executed result---"+count2);
				
				if(count2>0){
					con.commit();
					return true;
				}else{con.rollback(); }			
			} else { con.rollback(); }
		}catch(SQLException e){
			System.out.println("Problem in req-rejected---"+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
