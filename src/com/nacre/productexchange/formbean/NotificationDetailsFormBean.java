package com.nacre.productexchange.formbean;

import java.io.Serializable;

import com.nacre.productexchange.dto.UserDetailsDto;

public class NotificationDetailsFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int prodReqId;
	private int reqStatus;
	private UserDetailsDto userId;
	
	public int getProdReqId() {
		return prodReqId;
	}
	public void setProdReqId(int prodReqId) {
		this.prodReqId = prodReqId;
	}
	public int getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(int reqStatus) {
		this.reqStatus = reqStatus;
	}
	public UserDetailsDto getUserId() {
		return userId;
	}
	public void setUserId(UserDetailsDto userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
