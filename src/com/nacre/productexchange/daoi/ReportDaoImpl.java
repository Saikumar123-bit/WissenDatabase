package com.nacre.productexchange.daoi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nacre.productexchange.constants.SqlQuery;
import com.nacre.productexchange.dbutils.DbUtils;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.ProductRequestNotificationFormBean;

public class ReportDaoImpl implements ReportDaoI {
	
	Connection con=null;
	PreparedStatement preparedstatement =null;
	
	@Override
	public ProductRequestNotificationFormBean getExchangeDetails(int excProductId) throws SQLException, Exception {
		
		System.out.println("Now control download report dao ---");
		ProductDto exeproduct=null;
		ProductDto reqproduct=null;
		UserDetailsDto reqUser=null;
		UserDetailsDto excuser=null;
		ProductRequestNotificationFormBean pReqNoti=null;
		ResultSet rs=null;
		
		try{
			//getting databse connection
			con=DbUtils.getConnection();
			System.out.println("Database connected");
			preparedstatement=con.prepareStatement(SqlQuery.Get_P_Exchange_Details);
			preparedstatement.setInt(1, excProductId);
		
			//execute the query
			rs=preparedstatement.executeQuery();
		
		//getting results from databse
		while(rs.next()){
			
			//create object
			exeproduct=new ProductDto();
			reqproduct=new ProductDto();
			reqUser=new UserDetailsDto();
			excuser=new UserDetailsDto();
			pReqNoti=new ProductRequestNotificationFormBean();
			
			//setting values to formbean
			pReqNoti.setExcReqDate(rs.getDate(1));
			pReqNoti.setApproveReqDate(rs.getDate(2));
			System.out.println("Details"+rs.getDate(1)+"---"+rs.getDate(2)+"--"+rs.getInt(3)+"---"+rs.getString(4)+"---"+rs.getInt(5)+"---"+rs.getString(6)+"----");
			
			exeproduct.setProduct_id(rs.getInt(3));
			exeproduct.setProduct_name(rs.getString(4));
			exeproduct.setProduct_price(rs.getInt(5));
			exeproduct.setProduct_description(rs.getString(6));
			exeproduct.setProductImage(rs.getBlob(9));
			exeproduct.setPurchased_date(rs.getDate(7));
			pReqNoti.setExchangeProduct(exeproduct);
			
			reqproduct.setProduct_id(rs.getInt(11));
			reqproduct.setProduct_name(rs.getString(12));
			reqproduct.setProduct_price(rs.getInt(13));
			reqproduct.setProduct_description(rs.getString(14));
			reqproduct.setProductImage(rs.getBlob(17));
			reqproduct.setPurchased_date(rs.getDate(15));
			pReqNoti.setRequestProduct(reqproduct);
			
			excuser.setUserid(rs.getInt(19));
			excuser.setName(rs.getString(20));
			excuser.setEmail(rs.getString(21));
			excuser.setMobileno(rs.getString(23));			
			pReqNoti.setExchangeUser(excuser);
			
			exeproduct.setOwnerid(excuser);	
			pReqNoti.setExchangeProduct(exeproduct);
			
			reqUser.setUserid(rs.getInt(25));
			reqUser.setName(rs.getString(27));
			reqUser.setEmail(rs.getString(28));
			reqUser.setMobileno(rs.getString(30));
			pReqNoti.setRequesthangeUser(reqUser);
			
			reqproduct.setOwnerid(reqUser);
			pReqNoti.setRequestProduct(reqproduct);
			
			}
		}catch(SQLException e){
			System.out.println("sqllll-----"+e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("prblm in downlading----"+e.getMessage());
			e.printStackTrace();
		}
		return pReqNoti;
	}

}
