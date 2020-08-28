package com.nacre.productexchange.constants;

public class SqlQuery 
{
	//Queries on Authentication
	public static final String SQL_Query="select email,password from user where name=?";
	public static final String Sql_Address_Table="insert into address(City_Id,Address,Pin_Code) values(?,?,?)";	
	//going to insert data into user table by reading addressid
	public static final String SQL_INSERT_QUERY="insert into user(A_Id,Name,Email,Password,Mobile_No) values(?,?,?,?,?)";	
	//getting the data from database for login application
	public static final String SQL_LOGIN_QUERY = "select U_Id,Name,Email,Password,Mobile_No,Ac_bal from user where Email=? and Password=?";	
	//update user details
	public static final String SQL_UPDATE_DETAILS="update user set Name=?, email=?, Mobile_No=? where u_id=?";
	//getting country details
	public static final String Get_Country_details="select C_Id,C_Name from country";	
	//getting state details based on country id
	public static final String Get_State_details="select s.S_Id,s.S_Name from state s JOIN country c on s.C_Id=c.C_Id where c.C_Id=?";	
	//getting city details based on stateid
	public static final String Get_City_details="select c.City_Id,c.City_Name from state s join city c on s.S_Id=c.S_Id where s.s_Id=?";
	//update password query
	public static final String SQL_UPDATE_CHANGE_PASSWORD_QUERY="update user set password=? where email=?";
	
	
	//----------------------Product Management Dao class Querys-------------------
	//Get Eligible products
	public static final String Get_Eligible_Products="select P_id,P_Name,P_Price,P_Description,Purchased_date,P_Avalibility_Status,P_Img,Owner_Id from product where P_Avalibility_Status=?";
	public static final int Availibile_status_id=1;
	//query to add products to product Table
	public static final String INSERT_ELIGIBLE_PRODUCTS=" insert into product(P_Name,P_Price,P_Description,Purchased_date,P_Avalibility_Status,P_Img,owner_Id) values(?,?,?,?,?,?,?)";
	//query to update products to product table
	public static final String UPDATE_PRODCUT_DETAILS=" update product set P_Name=?,P_Price=?,P_Description=?,P_Avalibility_Status=?,P_Img=? where P_Id=?";
	public static final String View_Product="select product.P_Id,product.P_Name,product.P_Price,product.P_Description,product.Purchased_date,product.P_Avalibility_Status,product.P_Img,product.Owner_Id,status.Status from product,status where status.Status_Id=? and product.P_Avalibility_Status=? and Owner_Id=?";
	public static final String viewProducts="select * from product where Owner_Id=? and P_Avalibility_Status=?";
	
	
	//--------------------ProductExchangeDaoimpl ----------------------------
	public static final String Check_ExchangeProduct="select *from product where Owner_id=? and P_Id=?";
	public static final String INSERT_PRODUCT_REQUEST="insert into product_request(Ex_P_Id,Req_P_Id,Date_Of_Request,Req_Status) values(?,?,?,?)";

	//
	public static final String SQL_Login="select P_Img from product where Owner_id=?";
		
	public static final int Product_Approved_Status_number=5;
	
	//getting status of user
	public static final String SQL_GET_STATUS="select status from status where Status_Id=?";
	
	//for getting all eligible prodcuts for exchange
	public static final String GET_ELIGIBLE_PRODUCTS="select *from product where P_Avalibility_Status=?";
			
	public static final String View_Exchanged_Products  = "SELECT PR.P_R_Id, PR.Req_P_Id, P1.P_Name,P1.P_Img,P1.P_Price,P1.P_Description,P1.Purchased_date,P1.Owner_Id, PR.Ex_P_Id, P2.P_Name,P2.P_Img,P2.P_Price,P2.P_Description,P2.Purchased_date,P2.Owner_Id, Date_Of_Request, Req_Status From  product_request PR Inner JOIN PRODUCT P1  ON P1.P_Id = PR.Ex_P_Id INNER JOIN Product p2 ON P2.P_Id = PR.Req_P_Id INNER JOIN  user U  ON U.U_Id = P2.Owner_Id INNER JOIN  user U1 ON U1.U_Id = P1.Owner_Id WHERE ( U1.U_Id =? OR  U.U_Id =?) AND PR.Req_Status = 5";
	
	public static final String Get_P_Exchange_Details="select r.Date_Of_Request,r.Date_Of_Approval,p.*,p1.*,u.*,u1.* from product p,product p1,user u,user u1,product_request r where r.Ex_P_Id=p.P_Id and r.Req_P_Id=p1.P_Id and p.Owner_Id=u.U_Id and p1.Owner_Id=u1.U_Id and r.P_R_Id=? and p1.P_Avalibility_Status=p.P_Avalibility_Status and r.Req_Status=5";
		
	
	//get eligible product based on status
	public static final String GET_ELIGIBLE_PRODUCT_BASED_ON_STATUS=" select P_Id,P_Name,P_Price,P_Description,Purchased_Date,P_Img,owner_Id from product where P_Avalibility_Status=?";
	
	//Get Product Request Notification Details
	public static final String GET_PROD_REQ_NOTIFICATION_DETAILS="select P_R_Id, p1.*, p2.*,Date_Of_Request,Date_Of_Approval, Req_Status FROM product_request pr inner join product p1 on p1.P_Id=pr.Req_P_Id inner join product p2 on p2.P_Id=pr.Ex_P_Id INNER JOIN user u  on U_Id=p1.Owner_Id where u.U_Id=? and pr.P_R_Id=? and pr.Req_Status=?";
	
	//disable user products
	public static final String DISABLE_USER_PRODUCTS="update product set P_Avalibility_Status=? where P_Id=? and Owner_Id=?";
	
	public static final String Check_Exchabnge_Product="select *from product where P_Id=? and Owner_Id=?";
	
	public static final String REJECT_REQUEST1="update product_request set Date_Of_Approval=?,Req_Status=4 where P_R_Id=?";
	
	public static final String APPROVE_REQUEST="update product_request set Date_Of_Approval=?,Req_Status=4 where P_R_Id=?";
	
	public static final String GET_USER_REJECTED_NOTIFICATIONS = "SELECT PR.P_R_Id, PR.Ex_P_Id, P1.P_Name, PR.Req_P_Id, P2.P_Name, Req_Status FROM USER U INNER JOIN PRODUCT P1 ON U.U_Id = P1.Owner_Id INNER JOIN Product_request PR ON P1.P_Id = PR.Ex_P_Id INNER JOIN PRODUCT P2 ON P2.P_Id = PR.Req_P_Id WHERE U.U_Id = ? AND PR.Req_Status = 4";
	
	public static final String GET_REQ_PROD_REJ_NOTI_DETAILS="SELECT P_R_Id, P1.*, P2.*, Date_Of_Approval, Req_Status, Rejected_Reason FROM USER U INNER JOIN PRODUCT P1 ON U.U_Id = P1.Owner_Id INNER JOIN PRODUCT_REQUEST PR ON P1.P_Id = PR.Ex_P_Id INNER JOIN PRODUCT P2 ON P2.P_Id = PR.P_R_Id WHERE U.U_Id = ? AND PR.P_R_Id = ? AND PR.Req_Status = ?";
	
	public static final String GET_USER_REQ_NOTIFICATIONS  = "SELECT PR.P_R_Id, PR.Req_P_Id, P1.P_Name,P1.Owner_Id, PR.Ex_P_Id, P2.P_Name,P2.Owner_Id, Date_Of_Request, Req_Status From  product_request PR Inner JOIN PRODUCT P1  ON P1.P_Id = PR.Req_P_Id INNER JOIN Product p2 ON P2.P_Id = PR.Ex_P_Id INNER JOIN  user U  ON U.U_Id = P2.Owner_Id  INNER JOIN  user U1 ON U1.U_Id = P1.Owner_Id WHERE U1.U_Id =? AND PR.Req_Status = 6";
	
	public static final String GET_USER_APPROVED_NOTIFICATIONS="SELECT PR.P_R_Id, PR.Req_P_Id, P1.P_Name,P1.P_Price, PR.Req_P_Id, P2.P_Name,P2.P_Price, Date_Of_Approval, Req_Status FROM USER U INNER JOIN PRODUCT P1 ON U.U_Id = P1.Owner_Id INNER JOIN PRODUCT_REQUEST PR ON P1.P_Id = PR.Ex_P_Id INNER JOIN PRODUCT P2 ON P2.P_Id = PR.Req_P_Id  WHERE U.U_Id = ? AND PR.Req_Status = 5";
	
	public static final String REJECT_REQUEST="update product_request set Date_Of_Approval=?,Req_Status=4 where Ex_P_Id=? and P_R_Id=?";
	
	public static final String REQ_APPROVED="UPDATE PRODUCT_REQUEST SET DATE_OF_APPROVAL = NOW(), Req_Status  = 5, Rejected_Reason = ? WHERE P_R_Id = ? AND Req_Status = 6";
	
	public static final String REQ_REJECTED=" UPDATE PRODUCT_REQUEST SET Date_Of_Approval =NOW() , Req_Status = 4 , Rejected_Reason = ? WHERE Ex_P_Id = ? AND P_R_Id = ?  AND Req_Status = 6";
	
	public static final String UPDATE_PROD_REQ_STATUS_TO_APPROVED="UPDATE PRODUCT SET P_Avalibility_Status = 5 WHERE P_Id IN ( ?,?)";
	
	public static final String UPDATE_PROD_REQ_STATUS_TO_REJECTED="UPDATE PRODUCT_REQUEST SET Req_Status = 4, Rejected_Reason = ? WHERE P_R_Id= ? AND Req_Status = 6";
	
	public static final String UPDATE_EXC_PROD_STATUS_TO_ELIGIBLE="UPDATE PRODUCT P, PRODUCT_REQUEST PR SET P.P_Avalibility_Status = 1 WHERE P.P_Id = PR.Ex_P_Id AND PR.Req_Status = 4";
	
	public static final String PRODUCT_REQUEST_DETAILS="select r.P_R_Id,p.*,p1.*,u1.* from product p, product p1,user u,user u1 product_request where p.Owner_Id=u.U_Id and r.Ex_P_Id=p.P_Id and u.U_Id=? and r.Req_Status=6 and p.P_Avalibility_Status=1 and p1.P_Id=r.Req_P_Id and p1.P_Avalibility_Status=1 and p1.Owner_Id=u1.U_Id";
		
	//to get prodcut based on userid and status
	public static final String View_Products="select *from product where Owner_Id=? and P_Avalibility_Status=?";
	
	public static final String SEND_REQUEST_PRODUCTS="select *from product_requset where Ex_P_Id=?";
	
}