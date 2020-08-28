package com.nacre.productexchange.formbean;

import java.io.Serializable;
import java.util.Date;

public class ApproveRejectFormBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodreqId;
	private String rejectedReason;
	private String reqProdId;
	private String excProdId;
	private String dateOfApproval;
	private int statusId;
	
	public String getProdreqId() {
		return prodreqId;
	}
	public void setProdreqId(String prodreqId) {
		this.prodreqId = prodreqId;
	}
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	public String getReqProdId() {
		return reqProdId;
	}
	public void setReqProdId(String reqProdId) {
		this.reqProdId = reqProdId;
	}
	public String getExcProdId() {
		return excProdId;
	}
	public void setExcProdId(String excProdId) {
		this.excProdId = excProdId;
	}
	public String getDateOfApproval() {
		return dateOfApproval;
	}
	public void setDateOfApproval(String dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
}
