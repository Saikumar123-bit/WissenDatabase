package com.nacre.productexchange.formbean;

import java.io.Serializable;
import java.sql.Date;

public class GetNotificationDetailsFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String prodReqId;
	private int prodreqid; //my own
	
	private int reqProdId;
	private String reqProdName;
	private float reqProdPrice;
	private String reqProdDesc;
	private Date reqProdPurchasedDate;
	private int reqProdAvalStatus;
	private String reqProdImg;
	private int reqProdOwnerId;
	
	private int excProdId;
	private String excProdName;
	private float excProdPrice;
	private String excProdDesc;
	private Date excProdPurchasedDate;
	private int excProdAvalStatus;
	private String excProdImg;
	private int excProdOwnerId;
	
	private String dateOfReq;
	private Date dateOfApproval; //changed String to long
	private String reqStatus;
	private String rejectedReason;
	private int productRqstSts;
	
	public String getProdReqId() {
		return prodReqId;
	}
	public void setProdReqId(String prodReqId) {
		this.prodReqId = prodReqId;
	}
	public int getProdreqid() {
		return prodreqid;
	}
	public void setProdreqid(int prodreqid) {
		this.prodreqid = prodreqid;
	}
	public int getReqProdId() {
		return reqProdId;
	}
	public void setReqProdId(int reqProdId) {
		this.reqProdId = reqProdId;
	}
	public String getReqProdName() {
		return reqProdName;
	}
	public void setReqProdName(String reqProdName) {
		this.reqProdName = reqProdName;
	}
	public float getReqProdPrice() {
		return reqProdPrice;
	}
	public void setReqProdPrice(float reqProdPrice) {
		this.reqProdPrice = reqProdPrice;
	}
	public String getReqProdDesc() {
		return reqProdDesc;
	}
	public void setReqProdDesc(String reqProdDesc) {
		this.reqProdDesc = reqProdDesc;
	}
	public java.util.Date getReqProdPurchasedDate() {
		return reqProdPurchasedDate;
	}
	public void setReqProdPurchasedDate(java.util.Date date) {
		this.reqProdPurchasedDate = (Date) date;
	}
	public int getReqProdAvalStatus() {
		return reqProdAvalStatus;
	}
	public void setReqProdAvalStatus(int reqProdAvalStatus) {
		this.reqProdAvalStatus = reqProdAvalStatus;
	}
	public String getReqProdImg() {
		return reqProdImg;
	}
	public void setReqProdImg(String reqProdImg) {
		this.reqProdImg = reqProdImg;
	}
	public int getReqProdOwnerId() {
		return reqProdOwnerId;
	}
	public void setReqProdOwnerId(int reqProdOwnerId) {
		this.reqProdOwnerId = reqProdOwnerId;
	}
	public int getExcProdId() {
		return excProdId;
	}
	public void setExcProdId(int excProdId) {
		this.excProdId = excProdId;
	}
	public String getExcProdName() {
		return excProdName;
	}
	public void setExcProdName(String excProdName) {
		this.excProdName = excProdName;
	}
	public float getExcProdPrice() {
		return excProdPrice;
	}
	public void setExcProdPrice(float excProdPrice) {
		this.excProdPrice = excProdPrice;
	}
	public String getExcProdDesc() {
		return excProdDesc;
	}
	public void setExcProdDesc(String excProdDesc) {
		this.excProdDesc = excProdDesc;
	}
	public java.util.Date getExcProdPurchasedDate() {
		return excProdPurchasedDate;
	}
	public void setExcProdPurchasedDate(java.util.Date date) {
		this.excProdPurchasedDate = (Date) date;
	}
	public int getExcProdAvalStatus() {
		return excProdAvalStatus;
	}
	public void setExcProdAvalStatus(int excProdAvalStatus) {
		this.excProdAvalStatus = excProdAvalStatus;
	}
	public String getExcProdImg() {
		return excProdImg;
	}
	public void setExcProdImg(String excProdImg) {
		this.excProdImg = excProdImg;
	}
	public int getExcProdOwnerId() {
		return excProdOwnerId;
	}
	public void setExcProdOwnerId(int excProdOwnerId) {
		this.excProdOwnerId = excProdOwnerId;
	}
	public String getDateOfReq() {
		return dateOfReq;
	}
	public void setDateOfReq(String dateOfReq) {
		this.dateOfReq = dateOfReq;
	}
	public Date getDateOfApproval() {
		return dateOfApproval;
	}
	public void setDateOfApproval(Date dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}
	
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}
	public int getProductRqstSts() {
		return productRqstSts;
	}
	public void setProductRqstSts(int productRqstSts) {
		this.productRqstSts = productRqstSts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
