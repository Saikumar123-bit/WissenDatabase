package com.nacre.productexchange.daoi;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nacre.productexchange.dto.UserCityDto;
import com.nacre.productexchange.dto.UserCountryDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.dto.UserStateDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.ResetFormBean;
import com.nacre.productexchange.constants.SqlQuery;
import com.nacre.productexchange.dbutils.DbUtils;
import com.nacre.productexchange.dto.AddressDto;
import com.nacre.productexchange.formbean.RegisterFormBean;
import com.nacre.productexchange.formbean.LoginFormBean;

public class AuthenticationDaoImpl implements AuthenticationDaoI{
	
	@Override
	public boolean registerCheck(RegisterFormBean rfb) throws ClassNotFoundException, SQLException
	{
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs=null;
		int count = 0,count1=0;
		AddressDto regaddress=null;
		String address=null;
		
		try{
			//create databse connection
			con = DbUtils.getConnection();
			con.setAutoCommit(false);
		
			//creating object for RegisterAddress class to get the addressid
			regaddress= new AddressDto();
		
			//create preparedstatement object
			ps1 = con.prepareStatement(SqlQuery.Sql_Address_Table,Statement.RETURN_GENERATED_KEYS);
			ps = con.prepareStatement(SqlQuery.SQL_INSERT_QUERY);
			
			//setting the query params to the address table
			ps1.setInt(1, Integer.parseInt(rfb.getCity()));
		
			address = rfb.getAddress();
			ps1.setString(2, address);		
			ps1.setString(3, rfb.getPinCode());
		
			// execute the query
			count1 = ps1.executeUpdate();
		
			rs = ps1.getGeneratedKeys();
			
			if(rs!=null && rs.next()){
				//setting the value to the regiter address id
				regaddress.setAddress_id(rs.getInt(1));			
			}
		
			//set the values to preparedstatemnt object
			System.out.println(regaddress.getAddress_id()+"   "+rfb.getName()+"   "+rfb.getEmail()+"   "+rfb.getPassword()+"    "+rfb.getMobileno());
			ps.setInt(1, regaddress.getAddress_id());
			ps.setString(2,rfb.getName());
			ps.setString(3, rfb.getEmail());
			ps.setString(4, rfb.getPassword());
			ps.setString(5, rfb.getMobileno());
		
			//execute the user table
			count = ps.executeUpdate();

			if(count==1 || count1==1){
				flag=true;
				con.commit();
				System.out.println("Record Inserted");
			}else{
				con.rollback();
				System.out.println("Record Not Inserted");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}	
	
	@Override
	public UserDetailsDto loginCheck(LoginFormBean lfb) throws SQLException, ClassNotFoundException{
		
		List<UserDetailsDto> list=null;
		UserDetailsDto user=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
		//getting the connection
		con=DbUtils.getConnection();
		
		//create preparedStatement object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.SQL_LOGIN_QUERY);
					
		if(ps!=null)
			ps.setString(1, lfb.getEmail());
			ps.setString(2, lfb.getPassword());
		
		//execute the query	
		rs=ps.executeQuery();
		
		//creat arraylist obj for UserDetailsDto
		list = new ArrayList<UserDetailsDto>();		
		//create arraylist objetc
		user = new UserDetailsDto();
		
		if(rs!=null){
			if(rs.next()){
				System.out.println("Second one----"+rs.getString(1)+" --"+rs.getString(2)+"---"+rs.getString(3)+"---"+rs.getString(4)+"---"+rs.getString(5)+"---"+rs.getInt(6));
				user.setUserid(rs.getInt(1));
				user.setName(rs.getString(2));
				
				user.setEmail(rs.getString(3));
				lfb.setDbemail(rs.getString(3));
				
				user.setPassword(rs.getString(4));
				lfb.setDbpassword(rs.getString(4));
				
				user.setMobileno(rs.getString(5));
				user.setAc_Balanace(rs.getInt(6));
				System.out.println("-----------------");
				list.add(user);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public int updateDetails(LoginFormBean lfb) throws SQLException, IOException {
		
		System.out.println("Now control in updateDetails()");
		Connection con=null;
		PreparedStatement ps=null;
		int update=0;
		
		
		//create connection object
		con=DbUtils.getConnection();
		con.setAutoCommit(false);
		//create preparedStamenet object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.SQL_UPDATE_DETAILS);
		
		//send and execute sql query
		if(ps!=null)
			ps.setString(1, lfb.getUsername());
			ps.setString(2, lfb.getEmail());
			ps.setString(3, lfb.getMobileNo());
			ps.setInt(4, lfb.getUserid());
		
		//execute the query
		update=ps.executeUpdate();	
		
		if(update==1){
			con.commit();
		}else{
			con.rollback();
		}
		
		System.out.println("Query is executed-->"+update);
		
		return update;
	}
	
	@Override
	public List<UserCountryDto> getCountries() throws DbException, SQLException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<UserCountryDto> listregcountry=null;
		UserCountryDto regcountry=null;
		
		//getting the connection
		con = DbUtils.getConnection();
		
		//create preparedstatement object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.Get_Country_details);
		
		//send and execute sql query
		if(ps!=null)
			rs=ps.executeQuery();
		
		//create an arraylist object
		listregcountry = new ArrayList<UserCountryDto>();

		if(rs!=null){
			while(rs.next())
			{
				regcountry = new UserCountryDto();
				System.out.println(rs.getInt(1)+"<------------>"+rs.getString(2));
				regcountry.setCountry_Id(rs.getInt(1));
				regcountry.setCountry_Name(rs.getString(2));
				
				//adding to the collection
				listregcountry.add(regcountry);
				
			}//while
		}
		return listregcountry;
	}
	public List<UserStateDto> getStates(int cid)throws DbException, SQLException
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<UserStateDto> listregstate=null;
		UserStateDto regstate=null;
				
		//getting the connection
		con = DbUtils.getConnection();
		
		//create preparedstatement object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.Get_State_details);
		
		//setting query params to ps object
		ps.setInt(1, cid);
		
		//send and execute sql query
		if(ps!=null)
			rs=ps.executeQuery();
		
		//create an arraylist object
		listregstate = new ArrayList<UserStateDto>();

		if(rs!=null){
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"------"+rs.getString(2));
				regstate = new UserStateDto();
				
				regstate.setState_Id(rs.getInt(1));
				regstate.setState_name(rs.getString(2));
								
				//adding to the collection
				listregstate.add(regstate);
				
			}//while
		}
		return listregstate;
	}
	public List<UserCityDto> getCity(int stateid)throws DbException,SQLException
	{
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<UserCityDto> listregcity=null;
		UserCityDto regcity=null;
		
		//getting the connection
		con = DbUtils.getConnection();
		
		//create preparedstatement object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.Get_City_details);
		
		System.out.println("Statieid-------->"+stateid);

		//setting query params to ps object
		ps.setInt(1, stateid);
		
		//send and execute sql query
		if(ps!=null)
			rs=ps.executeQuery();
		
		//create an arraylist object
		listregcity = new ArrayList<UserCityDto>();
				
		if(rs!=null){
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"------"+rs.getString(2));
				regcity = new UserCityDto();
				
				regcity.setCity_id(rs.getInt(1));
				regcity.setCity_name(rs.getString(2));
				//adding to the collection
				listregcity.add(regcity);
				
			}//while
		}
		return listregcity;
	}
	@Override
	public int updatePassword(String email) throws SQLException, DbException {
		
		Connection con=null;
		PreparedStatement ps=null;
		UserDetailsDto userdto=null;
		int result=0;
		ResetFormBean reset=null;
		
		//ucreate reset object
		reset = new ResetFormBean();
		
		//getting the connection
		con=DbUtils.getConnection();
		con.setAutoCommit(false);
		
		//create preparedstatement object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.SQL_UPDATE_CHANGE_PASSWORD_QUERY);

		//setting query params to ps object
		if(ps!=null){	
			ps.setString(1, reset.getNewpassword());
			ps.setString(2, reset.getEmail());
		}	
		
		//send and execute the sql qurey
		result=ps.executeUpdate();
		if(result==1){
			con.commit();
		}else{
			con.rollback();
		}
		System.out.println("Updated query result is---->"+result);
		
		return result;
	}
	@Override
	public int changePassword(ResetFormBean reset) throws SQLException, DbException {
		
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		//getting the connection
		con=DbUtils.getConnection();
		con.setAutoCommit(false);
		//create preparedstateemnt object
		if(con!=null)
			ps=con.prepareStatement(SqlQuery.SQL_UPDATE_CHANGE_PASSWORD_QUERY);
		
		//setting query params to ps object
		if(ps!=null){
			ps.setString(1, reset.getNewpassword());
			ps.setString(2, reset.getEmail());
		}
		
		//send and exceute sql query
		result=ps.executeUpdate();
		if(result==1){
			con.commit();
		}else{
			con.rollback();
		}
		System.out.println("Query updated result is--->"+result+"--->Query updated result ");
		
		return result;
	}
}