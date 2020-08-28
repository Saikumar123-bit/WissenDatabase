package com.nacre.productexchange.dto;

public class UserCityDto 
{
	private int city_id;
	private String city_name;
	private UserStateDto state_id;
	
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public UserStateDto getState_id() {
		return state_id;
	}
	public void setState_id(UserStateDto state_id) {
		this.state_id = state_id;
	}
	
	@Override
	public String toString() {
		return "RegisterCity [cityId=" + city_id + ", CityName=" + city_name + ", stateId=" + state_id + "]";
	}
	
}
