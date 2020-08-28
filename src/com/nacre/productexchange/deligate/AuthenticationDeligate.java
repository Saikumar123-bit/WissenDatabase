package com.nacre.productexchange.deligate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.dto.UserCityDto;
import com.nacre.productexchange.dto.UserCountryDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.dto.UserStateDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.LoginFormBean;
import com.nacre.productexchange.formbean.RegisterFormBean;
import com.nacre.productexchange.formbean.ResetFormBean;
import com.nacre.productexchange.service.AuthenticationServiceImpl;



public class AuthenticationDeligate {
	
		public int checkInternetConnection(){
			
		@SuppressWarnings("resource")
		Socket socket = new Socket();
		InetSocketAddress addr = new InetSocketAddress("www.google.com",80);
		String msg=null;
		try{
			socket.connect(addr,3000);
		}catch(Exception e){
			System.out.println(msg);
			return 1;
		}
		return 0;
	}
		
	public boolean register(RegisterFormBean rfb) throws ClassNotFoundException, SQLException
	{
		boolean flag=false;		
		AuthenticationServiceImpl rsi =null;
		
		//create service class object
		rsi=new AuthenticationServiceImpl();
		
		//control sending to service
		flag=rsi.registerLogic(rfb);
				
		return flag;
	}
	
	public int updateDetails(LoginFormBean lfb)throws ClassNotFoundException,IOException, SQLException
	{
		AuthenticationServiceImpl asi=null;
		
		//create service class object
		asi = new AuthenticationServiceImpl();
		
		return asi.updateDetails(lfb);
	}
	
	public UserDetailsDto login(LoginFormBean lfb) throws ClassNotFoundException, SQLException
	{
		UserDetailsDto user=null;
		
		AuthenticationServiceImpl lsi = new AuthenticationServiceImpl();
		user = lsi.loginCheck(lfb);
				
		return user;
	}
	
	public List<UserCountryDto> getCountries() throws DbException, SQLException 
	{		
		AuthenticationServiceImpl authserviceimpl = null;
		
		authserviceimpl = new AuthenticationServiceImpl();
		
		return authserviceimpl.getCountries();
		
	}
	public List<UserStateDto> getStates(String cid)throws DbException,IOException, SQLException
	{
				
		AuthenticationServiceImpl authserviceimpl = null;
				
		authserviceimpl = new AuthenticationServiceImpl();
		
		return authserviceimpl.getStates(Integer.parseInt(cid));
	
	}
	public List<UserCityDto> getCity(String stateid)throws DbException,IOException, NumberFormatException, SQLException
	{
				
		AuthenticationServiceImpl authserviceimpl = null;
		
		authserviceimpl = new AuthenticationServiceImpl();	
		
		return authserviceimpl.getCity(Integer.parseInt(stateid));
	}
	
	public int updatePassword(String email) throws SQLException, DbException 
	{
		
		AuthenticationServiceImpl authserviceimpl = null;
		
		authserviceimpl = new AuthenticationServiceImpl();	
		
		return authserviceimpl.updatePassword(email);
	}
	
	public int changePassword(ResetFormBean reset)throws SQLException,DbException
	{
		AuthenticationServiceImpl authserviceimpl = null;
		
		authserviceimpl = new AuthenticationServiceImpl();	
		
		return authserviceimpl.changePassword(reset);	
	}

}