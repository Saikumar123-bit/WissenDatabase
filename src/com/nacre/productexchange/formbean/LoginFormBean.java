package com.nacre.productexchange.formbean;

import java.io.Serializable;

public class LoginFormBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userid;
	private String username;
	private String email;
	private String password;
	private String dbemail;
	private String dbpassword;
	private String dbMobileno;
	private String mobileNo;
	private int Ac_balance;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDbemail() {
		return dbemail;
	}

	public void setDbemail(String dbemail) {
		this.dbemail = dbemail;
	}

	public String getDbpassword() {
		return dbpassword;
	}
	
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public String getDbMobileno() {
		return dbMobileno;
	}

	public void setDbMobileno(String dbMobileno) {
		this.dbMobileno = dbMobileno;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAc_balance() {
		return Ac_balance;
	}

	public void setAc_balance(int ac_balance) {
		Ac_balance = ac_balance;
	}
}