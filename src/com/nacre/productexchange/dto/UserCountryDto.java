package com.nacre.productexchange.dto;

public class UserCountryDto 
{
	private int country_Id;
	private String country_Name;
	
	public int getCountry_Id() {
		return country_Id;
	}
	public void setCountry_Id(int country_Id) {
		this.country_Id = country_Id;
	}
	public String getCountry_Name() {
		return country_Name;
	}
	public void setCountry_Name(String country_Name) {
		this.country_Name = country_Name;
	}
	@Override
	public String toString() {
		return "RegisterCountry [countryId=" + country_Id + ", countryName=" + country_Name + "]";
	}

}
