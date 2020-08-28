package com.nacre.productexchange.formbean;

public class RegisterFormBean 
{
	private static final long serialVersionUID = 1L;
	private int userid;
	private String name;
	private String email;
	private String password;
	private String mobileno;
	private String address;
	private String pinCode;
	private String city;
	private String state;
	private String country;
	
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
		
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String toString() {
		return "UserDetailsDTO [userId="+userid+",name=" + name + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileno + ", addressId=" + address + "]";
	}


}
