package com.nacre.productexchange.dto;

import java.io.Serializable;

public class ApproveRejectBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductRequestDto prodReqId;
	private ProductDto reqProdId;
	private ProductDto excProdId;
	
	public ProductRequestDto getProdReqId() {
		return prodReqId;
	}
	public void setProdReqId(ProductRequestDto prodReqId) {
		this.prodReqId = prodReqId;
	}
	public ProductDto getReqProdId() {
		return reqProdId;
	}
	public void setReqProdId(ProductDto reqProdId) {
		this.reqProdId = reqProdId;
	}
	public ProductDto getExcProdId() {
		return excProdId;
	}
	public void setExcProdId(ProductDto excProdId) {
		this.excProdId = excProdId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
