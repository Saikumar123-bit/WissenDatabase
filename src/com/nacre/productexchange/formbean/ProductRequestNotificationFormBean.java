package com.nacre.productexchange.formbean;

import java.sql.Date;

import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.UserDetailsDto;

public class ProductRequestNotificationFormBean 
{
	private int productRequestId;
	private Date excReqDate;
	private Date approveReqDate;
	private int reqcount;
	private ProductDto exchangeProduct;
	private UserDetailsDto exchangeUser;
	private ProductDto requestProduct;
	private UserDetailsDto requesthangeUser;
	
	public int getProductRequestId() {
		return productRequestId;
	}
	public void setProductRequestId(int productRequestId) {
		this.productRequestId = productRequestId;
	}
	public Date getExcReqDate() {
		return excReqDate;
	}
	public void setExcReqDate(Date excReqDate) {
		this.excReqDate = excReqDate;
	}
	public Date getApproveReqDate() {
		return approveReqDate;
	}
	public void setApproveReqDate(Date approveReqDate) {
		this.approveReqDate = approveReqDate;
	}
	public int getReqCount() {
		return reqcount;
	}
	public void setReqCount(int count) {
		this.reqcount = reqcount;
	}
	public ProductDto getExchangeProduct() {
		return exchangeProduct;
	}
	public void setExchangeProduct(ProductDto exchangeProduct) {
		this.exchangeProduct = exchangeProduct;
	}
	public UserDetailsDto getExchangeUser() {
		return exchangeUser;
	}
	public void setExchangeUser(UserDetailsDto exchangeUser) {
		this.exchangeUser = exchangeUser;
	}
	public ProductDto getRequestProduct() {
		return requestProduct;
	}
	public void setRequestProduct(ProductDto requestProduct) {
		this.requestProduct = requestProduct;
	}
	public UserDetailsDto getRequesthangeUser() {
		return requesthangeUser;
	}
	public void setRequesthangeUser(UserDetailsDto requesthangeUser) {
		this.requesthangeUser = requesthangeUser;
	}
	
}
