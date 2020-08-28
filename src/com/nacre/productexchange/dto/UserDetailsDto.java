package com.nacre.productexchange.dto;

import java.io.Serializable;


public class UserDetailsDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;
	private String name;
	private String email;
	private String password;
	private String mobileno;
	private AddressDto address_Id;
	private int Ac_Balanace;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public AddressDto getAddress_Id() {
		return address_Id;
	}
	public void setAddress_Id(AddressDto address_Id) {
		this.address_Id = address_Id;
	}
	public int getAc_Balanace() {
		return Ac_Balanace;
	}
	public void setAc_Balanace(int ac_Balanace) {
		Ac_Balanace = ac_Balanace;
	}
	@Override	
	public String toString() {
		return "UserDetailsDTO [userId="+userid+",name=" + name + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileno + ", addressId=" + address_Id + "]";
	}	
}
