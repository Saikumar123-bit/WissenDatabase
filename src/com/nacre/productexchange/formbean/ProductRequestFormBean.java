package com.nacre.productexchange.formbean;

import java.io.Serializable;
import java.util.Date;

import com.nacre.productexchange.dto.StatusDto;

public class ProductRequestFormBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productReqId;
	private int exchangeProductId;
	private int requestProductId;
	private Date requestDate;
	private Date dateOfApproval;
	private StatusDto statusId;
	
	public int getProductReqId() {
		return productReqId;
	}
	public void setProductReqId(int productReqId) {
		this.productReqId = productReqId;
	}
	
	public int getExchangeProductId() {
		return exchangeProductId;
	}
	public void setExchangeProductId(int exchangeProductId) {
		this.exchangeProductId = exchangeProductId;
	}
	public int getRequestProductId() {
		return requestProductId;
	}
	public void setRequestProductId(int requestProductId) {
		this.requestProductId = requestProductId;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getDateOfApproval() {
		return dateOfApproval;
	}
	public void setDateOfApproval(Date dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}
	public StatusDto getStatusId() {
		return statusId;
	}
	public void setStatusId(StatusDto statusId) {
		this.statusId = statusId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
