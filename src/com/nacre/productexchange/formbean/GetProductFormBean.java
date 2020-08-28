package com.nacre.productexchange.formbean;

import com.nacre.productexchange.dto.StatusDto;
import com.nacre.productexchange.dto.UserDetailsDto;

public class GetProductFormBean 
{
	private static final long serialVersionUID = 1L;
	private int productId;
	private String productname;
	private float productprice;
	private String productdescription;
	private String purchasedDate;
	private int productAvalibleStatus;
	private String productImage;
	private StatusDto statusId;
	private UserDetailsDto ownerId;
	private String Rejected_reason;
	private int ownerid;
	
		public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public float getProductprice() {
		return productprice;
	}

	public void setProductprice(float productprice) {
		this.productprice = productprice;
	}

	public String getProductdescription() {
		return productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public int getProductAvalibleStatus() {
		return productAvalibleStatus;
	}

	public void setProductAvalibleStatus(int productAvalibleStatus) {
		this.productAvalibleStatus = productAvalibleStatus;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public StatusDto getStatusId() {
		return statusId;
	}

	public void setStatusId(StatusDto statusId) {
		this.statusId = statusId;
	}

	public UserDetailsDto getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UserDetailsDto ownerId) {
		this.ownerId = ownerId;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public String getRejected_reason() {
		return Rejected_reason;
	}

	public void setRejected_reason(String rejected_reason) {
		Rejected_reason = rejected_reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		@Override
	public String toString() {
		return "AddProductFormBeam [ProductId=" + productId + ", productName=" + productname + ", productPrice="
				+ productprice + ", productDescription=" + productdescription + ", purchageDate=" + purchasedDate
				+ ", productAvailableStatus=" + productAvalibleStatus + ", productImage=" + productImage+", ownerId";

	}
}
