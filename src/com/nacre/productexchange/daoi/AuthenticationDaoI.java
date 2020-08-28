package com.nacre.productexchange.daoi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.dto.UserCityDto;
import com.nacre.productexchange.dto.UserCountryDto;
import com.nacre.productexchange.dto.UserStateDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.ResetFormBean;
import com.nacre.productexchange.formbean.RegisterFormBean;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.LoginFormBean;

public interface AuthenticationDaoI {
	
	public boolean registerCheck(RegisterFormBean rfb) throws ClassNotFoundException, SQLException;
	
	public UserDetailsDto loginCheck(LoginFormBean lfb) throws SQLException, ClassNotFoundException;
	
	public int updateDetails(LoginFormBean lfb) throws SQLException, IOException;
	
	public List<UserCountryDto> getCountries() throws DbException,SQLException;
	
	public List<UserStateDto> getStates(int cid) throws DbException,SQLException;
	
	public List<UserCityDto> getCity(int stateid) throws DbException,SQLException;
	
	public int updatePassword(String email) throws SQLException, DbException;
	
	public int changePassword(ResetFormBean reset) throws SQLException, DbException;

}
