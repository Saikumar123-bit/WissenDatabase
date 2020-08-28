package com.nacre.productexchange.formbean;

import java.io.Serializable;

public class ExchangeProductBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String requestedProdId;
	private String exchangingProdId;
	
	public String getRequestedProdId() {
		return requestedProdId;
	}
	public void setRequestedProdId(String requestedProdId) {
		this.requestedProdId = requestedProdId;
	}
	public String getExchangingProdId() {
		return exchangingProdId;
	}
	public void setExchangingProdId(String exchangingProdId) {
		this.exchangingProdId = exchangingProdId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
