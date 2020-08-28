package com.nacre.productexchange.daoi;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nacre.productexchange.constants.IntegerContants;
import com.nacre.productexchange.constants.SqlQuery;
import com.nacre.productexchange.dbutils.DbUtils;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.StatusDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;

public class ProductManagementDaoImpl implements ProductManagementDao
{

	@Override
	public List<ProductDto> viewEligibleProducts() throws DbException
	{
		System.out.println("Now control is in productsmanagementDaoImpl class on viewEligibleProducts()");
		
		//boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<ProductDto> eligibleproducts=null;
		UserDetailsDto user=null;
		ProductDto product=null;
		StatusDto status=null;
				
		try
		{
			// getting connection with mysql databse
			con = DbUtils.getConnection();
						
			//creating PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(SqlQuery.Get_Eligible_Products);
			
			if(ps!=null)
				ps.setInt(1, SqlQuery.Availibile_status_id);
			
			if(ps!=null)
				rs=ps.executeQuery();
						
			//creating an arraylist 
			eligibleproducts = new ArrayList<ProductDto>();
						
			if(rs!=null){
				while(rs.next())
				{
					// create registerproduct object
					product = new ProductDto();
					
					//setting the data to it
					product.setProduct_id(rs.getInt(1));
					product.setProduct_name(rs.getString(2));
					product.setProduct_price(rs.getFloat(3));
					product.setProduct_description(rs.getString(4));
					product.setPurchased_date(rs.getDate(5));
					
					//create obj for RegisterStatusDto
					status = new StatusDto();
					status.setStatus_id(rs.getInt(6));
					
					product.setProduct_availibility_status(status);
					product.setProduct_image( rs.getBinaryStream(7));
					
					//create RegisterUser class obj
					user = new UserDetailsDto();
					user.setUserid(rs.getInt(8));
					//registeruser.setUser_id(rs.getInt(8));
					
					product.setOwnerid(user);
					//adding all products to collection
					eligibleproducts.add(product);
					
				}//while
			}	//if
		}
		catch(SQLException sql)
		{
			System.out.println("Error with sql Problem--->"+sql.getMessage());
			throw new DbException("db problem");
		}
		return eligibleproducts;
	}//viewEligibleProducts() method

	@Override
	public Integer addProducts(AddProductFormBean apfb,Integer userId) throws DbException 
	{
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		
		try{
			//getting the connection
			con = DbUtils.getConnection();
			con.setAutoCommit(false);
			
			//create preparedStatement object
			ps = con.prepareStatement(SqlQuery.INSERT_ELIGIBLE_PRODUCTS);
			
			System.out.println(apfb.getProduct_name()+"& "+apfb.getProduct_price()+"&& "+apfb.getProduct_description()+"&&& "+apfb.getPurchased_Date()+" &&&&-->"+apfb.getProduct_Avalible_Status()+" &&&&& "+userId);
			
			ps.setString(1, apfb.getProduct_name());
			ps.setString(2, apfb.getProduct_price());
			ps.setString(3, apfb.getProduct_description());
			ps.setDate(4, apfb.getPurchased_Date());
			ps.setInt(5, apfb.getProduct_Avalible_Status());
			ps.setBlob(6, apfb.getProduct_Image());
			ps.setInt(7, userId);
			
			//execute the query
			count = ps.executeUpdate();
			if(count==1){
				con.commit();
			}else{
				con.rollback();
			}
			System.out.println("Addproducts() Query Executed result is---->"+count);			
		}
		catch(Exception e)
		{
			System.out.println("Error with--->"+e.getMessage());
		}
		return count;
	}//addProducts()
	
	@Override
	public Integer updateProducts(AddProductFormBean apfb, Integer userId) throws DbException {
		
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		
		try{
			System.out.println("Sql date is--->"+apfb.getPurchased_Date());
			//getting the connection
			con = DbUtils.getConnection();
			
			con.setAutoCommit(false);
			
			//create preparedStatement object
			ps = con.prepareStatement(SqlQuery.UPDATE_PRODCUT_DETAILS);
			
			System.out.println(apfb.getProduct_name()+"& "+apfb.getProduct_price()+"&& "+apfb.getProduct_description()+"&&& "+apfb.getPurchased_Date()+" &&&&-->"+apfb.getProduct_Avalible_Status()+" &&&&&---->"+userId);
			
			//setting the values to preparedstatement object
			//ps.setInt(1, SqlQuery.Availibile_status_id);
			ps.setString(1, apfb.getProduct_name());
			ps.setString(2, apfb.getProduct_price());
			ps.setString(3, apfb.getProduct_description());
			//ps.setDate(4, apfb.getPurchased_Date());
			ps.setInt(4, apfb.getProduct_Avalible_Status());
			ps.setBlob(5, apfb.getProduct_Image());
			ps.setInt(6, apfb.getProductId());
			
			//execute the query
			count = ps.executeUpdate();
						
			if(count==1){
				con.commit();
			}else{
				con.rollback();
			}
			System.out.println("updateProducts() Query Executed result is---->"+count);
			
		}
		catch(Exception e)
		{
			System.out.println("Error with--->"+e.getMessage());
		}
		return count;
	}

	public List<ProductDto> viewUserProducts(int userId)throws SQLException,IOException, DbException 
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ProductDto> eligibleproducts=null;
		UserDetailsDto user=null;
		ProductDto product=null;
		StatusDto status=null;
		
		System.out.println("Now control is in productmanagementdaoimpl class on viewuserproducts()");
		
		try{
			//getting connection with database
			con = DbUtils.getConnection();
			
			//create preparedstatement object
			//ps = con.prepareStatement(SqlQuery.VIEW_PRODUCTS);
			ps=con.prepareStatement(SqlQuery.View_Product);
			ps.setInt(1, IntegerContants.product_availibility_status_id);
			ps.setInt(2, IntegerContants.product_availibility_status_id);
			ps.setInt(3, userId);
			
			//executing and getting resultset
			rs = ps.executeQuery();
			System.out.println("Query is executed");
			
			//creating an arraylist 
			eligibleproducts = new ArrayList<ProductDto>();
			int count=0;
			if(rs!=null){
				while(rs.next())
				{
					count=count+1;
					// create registerproduct object
					product = new ProductDto();
								
					//setting the data to it
					System.out.println("Setting details to product dto");
					product.setProduct_id(rs.getInt(1));
					product.setProduct_name(rs.getString(2));
					product.setProduct_price(rs.getFloat(3));
					product.setProduct_description(rs.getString(4));
					product.setPurchased_date(rs.getDate(5));
					
					//create obj for RegisterStatusDto
					status = new StatusDto();
					
					status.setStatus_id(rs.getInt(6));
					//registeruser.setUser_id(rs.getInt(8));
					
					product.setProductAvalibilityStatus(rs.getString(9));
					product.setProduct_availibility_status(status);
					product.setProduct_image( rs.getBinaryStream(7));
					
					//create RegisterUser class obj
					user = new UserDetailsDto();
					user.setUserid(rs.getInt(8));
					product.setOwnerid(user);
					
					//adding all products to collection
					eligibleproducts.add(product);
					
				}//while
			}	//if
		}
		catch(SQLException sql)
		{
			System.out.println("Error with sql Problem--->"+sql.getMessage());
			throw new DbException("db problem");
		}
		return eligibleproducts;
	}

	@Override
	public List<ProductDto> viewProducts() throws SQLException, IOException 
	{
		List<ProductDto> listproductdto=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//getting the connection with mysql databse software
		con = DbUtils.getConnection();
		
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.viewProducts);
		
		//set the values to the ps param query
		//ps.setInt(1, );
		
		listproductdto = new ArrayList<ProductDto>(); 
		
		return listproductdto;
		
	}

	@Override
	public int removeUserProducts(Integer productId, Integer ownerid) throws SQLException, DbException {
		
		System.out.println("Now control is in ProductManagementDaoImp,l class on on removeUserProducts()");
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		System.out.println(productId+"------"+ownerid);
		try{
			//getting the connection
			con=DbUtils.getConnection();
			con.setAutoCommit(false);
			
			System.out.println("Connection is established");
			//create preparedStatemnt obj
			if(con!=null)
				ps=con.prepareStatement(SqlQuery.DISABLE_USER_PRODUCTS);
			
			if(ps!=null)
				ps.setInt(1, IntegerContants.DISABLE_STATUS_ID_PRODUCT);
				ps.setInt(2, productId);
				ps.setInt(3, ownerid);
				
			//execute the query
				result=ps.executeUpdate();
				if(result==1){
					con.commit();
				}else{
					con.rollback();
				}
				System.out.println("Query updated Result-->"+result);
		
		}catch(SQLException e){
			System.out.println("Exception here--->"+e.getMessage());
			throw new DbException("Db Problem");
		}		
	return result;
	}

	@Override
	public int getStatus(int number) throws DbException, IOException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String status=null;
		StatusDto std=null;
		
		System.out.println("Status_Id is--->"+number);
		try{
		con=DbUtils.getConnection();
				
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.SQL_GET_STATUS);
		
		if(ps!=null)
			ps.setInt(1, number);
		
		//execute The Query
		if(ps!=null)
			rs=ps.executeQuery();
				
		//create Object for StatusDto
		std=new StatusDto();
		
		if(rs!=null){
			if(rs.next()){
			System.out.println("Getstatus() Status is--->"+rs.getString(1));
			std.setStatus(rs.getString(1));
			}
		}
		}
		catch (SQLException e) {
			System.out.println("Exception in status--->"+e.getMessage());
			e.printStackTrace();
			}
		return 0;
	}

}//class