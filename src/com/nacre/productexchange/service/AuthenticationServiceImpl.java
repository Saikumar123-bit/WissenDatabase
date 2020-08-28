package com.nacre.productexchange.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.daoi.AuthenticationDaoImpl;
import com.nacre.productexchange.dto.UserCityDto;
import com.nacre.productexchange.dto.UserCountryDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.dto.UserStateDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.LoginFormBean;
import com.nacre.productexchange.formbean.RegisterFormBean;
import com.nacre.productexchange.formbean.ResetFormBean;
import com.nacre.productexchange.util.MailUtil;
import com.nacre.productexchange.util.PasswordGeneratorUtility;


public class AuthenticationServiceImpl implements AuthenticationServiceI {
	
	@Override
	public boolean registerLogic(RegisterFormBean rfb) throws ClassNotFoundException, SQLException 
	{
		boolean flag=false;
				
		AuthenticationDaoImpl rdi = new AuthenticationDaoImpl();		
		flag = rdi.registerCheck(rfb);
				
		return flag;
	}
	
	@Override
	public UserDetailsDto loginCheck(LoginFormBean lfb) throws SQLException, ClassNotFoundException
	{
		UserDetailsDto userdto=null;
		UserDetailsDto userdto1=null;
		
		AuthenticationDaoImpl ldi = new AuthenticationDaoImpl();
		userdto=ldi.loginCheck(lfb);
		
		if(lfb.getEmail().equals(lfb.getDbemail()) && lfb.getPassword().equals(lfb.getDbpassword()))
		{
			System.out.println("Login Successful");
			return userdto;
		}else{
			System.out.println("Login failure");
			return userdto1;
		}	
	}

	@Override
	public int updateDetails(LoginFormBean lfb) throws SQLException, IOException {
		
		AuthenticationDaoImpl adi=null;
		
		//create object authserviceimpl class
		adi = new AuthenticationDaoImpl();
				
		
		return adi.updateDetails(lfb);
	}
	@Override
	public List<UserCountryDto> getCountries() throws DbException, SQLException 
	{
		AuthenticationDaoImpl authdao=null;
		
		//create object authserviceimpl class
		authdao = new AuthenticationDaoImpl();
		
		return authdao.getCountries();
		
	}
	
	@Override
	public List<UserStateDto> getStates(int cityid) throws DbException, SQLException 
	{
		AuthenticationDaoImpl authdao=null;
		
		//create object authserviceimpl class
		authdao = new AuthenticationDaoImpl();
		
		return authdao.getStates(cityid);
		
	}
	
	@Override
	public List<UserCityDto> getCity(int stateid) throws DbException, SQLException 
	{
		AuthenticationDaoImpl authdao=null;
		
		//create object authserviceimpl class
		authdao = new AuthenticationDaoImpl();
		
		return authdao.getCity(stateid);
	}
	
	@Override
	public int updatePassword(String email) throws SQLException, DbException 
	{
		AuthenticationDaoImpl authdao=null;
		ResetFormBean reset=null;
		String ps=null;
		int result=0;
		
		//create object authserviceimpl class
		authdao = new AuthenticationDaoImpl();
		reset = new ResetFormBean();
		
		//generate Random Password
		ps=PasswordGeneratorUtility.generateRandomPaassword();
		
		System.out.println("Generated Randome Password-->"+ps);
		
		reset.setEmail(email);
		reset.setNewpassword(ps);
		
		//control is going authdao and updating random password in database
		result = authdao.updatePassword(email);
		
		if(result>0){
			boolean b=MailUtil.sendEmail("saikumarchary33@gmail.com", "Reset password", "Your Updated password is---->"+ps);
			if(b){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}		
	@Override
	public int changePassword(ResetFormBean reset) throws SQLException, DbException {
		
		AuthenticationDaoImpl authdao=null;
		
		//create object authserviceimpl class
		authdao = new AuthenticationDaoImpl();
		
		return authdao.changePassword(reset);
	}

}
