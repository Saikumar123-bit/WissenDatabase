package com.nacre.productexchange.dto;

import java.util.Date;

public class ProductRequestDto 
{
	private Integer product_request_id;
	private ProductDto exchange_product_id;
	private ProductDto request_product_id;
	private String date_of_request;
	private Date date_of_approval; //changed long to string
	private String DateOfApproval;
	private StatusDto product_request_status;
	private int statusId; //my own
	
	public String getDateOfApproval() {
		return DateOfApproval;
	}
	public void setDateOfApproval(String dateOfApproval) {
		DateOfApproval = dateOfApproval;
	}
	private String product_rejected_reason;
	
	public Integer getProduct_request_id() {
		return product_request_id;
	}
	public void setProduct_request_id(Integer product_request_id) {
		this.product_request_id = product_request_id;
	}
	public ProductDto getExchange_product_id() {
		return exchange_product_id;
	}
	public void setExchange_product_id(ProductDto exchange_product_id) {
		this.exchange_product_id = exchange_product_id;
	}
	public ProductDto getRequest_product_id() {
		return request_product_id;
	}
	public void setRequest_product_id(ProductDto request_product_id) {
		this.request_product_id = request_product_id;
	}
	public String getDate_of_request() {
		return date_of_request;
	}
	public void setDate_of_request(String date_of_request) {
		this.date_of_request = date_of_request;
	}
	public Date getDate_of_approval() {
		return date_of_approval;
	}
	public void setDate_of_approval(Date date_of_approval) {
		this.date_of_approval = date_of_approval;
	}
	public StatusDto getProduct_request_status() {
		return product_request_status;
	}
	public void setProduct_request_status(StatusDto product_request_status) {
		this.product_request_status = product_request_status;
	}
	public String getProduct_rejected_reason() {
		return product_rejected_reason;
	}
	public void setProduct_rejected_reason(String product_rejected_reason) {
		this.product_rejected_reason = product_rejected_reason;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
}
