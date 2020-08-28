package com.nacre.productexchange.dto;

public class AddressDto 
{
	private int address_id;
	private String address;
	private String pin_code;
	private UserCityDto city_id;
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPin_code() {
		return pin_code;
	}
	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}
	public UserCityDto getCity_id() {
		return city_id;
	}
	public void setCity_id(UserCityDto city_id) {
		this.city_id = city_id;
	}
	
	@Override
	public String toString() {
		return "RegisterAddress [addressId=" + address_id + ", address=" + address + ", pinCode=" + pin_code + ", cityId="
				+ city_id + "]";
	}
}