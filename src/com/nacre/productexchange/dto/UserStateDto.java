package com.nacre.productexchange.dto;

public class UserStateDto
{
	private int state_Id;
	private String state_name;
	private UserCountryDto country_id;
	
	public int getState_Id() {
		return state_Id;
	}
	public void setState_Id(int state_Id) {
		this.state_Id = state_Id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public UserCountryDto getCountry_id() {
		return country_id;
	}
	public void setCountry_id(UserCountryDto country_id) {
		this.country_id = country_id;
	}
	
	@Override
	public String toString() {
		return "UserStateDTO [stateId=" + state_Id + ", stateName=" + state_name + ", countryId=" + country_id + "]";
	}
	
}
