package com.nacre.productexchange.dto;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class ProductDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int product_id;
	private String product_name;
	private float product_price;
	private String product_description;
	private Date purchased_date;
	private StatusDto product_availibility_status;
	private String productAvalibilityStatus; 
	private InputStream product_image;
	private Blob productImage;
	private UserDetailsDto owner_id;
	private String Rejected_Reason;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getProduct_price() {
		return product_price;
	}
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public Date getPurchased_date() {
		return purchased_date;
	}
	public void setPurchased_date(Date purchased_date) {
		this.purchased_date = purchased_date;
	}
	public StatusDto getProduct_availibility_status() {
		return product_availibility_status;
	}
	public void setProduct_availibility_status(StatusDto product_availibility_status) {
		this.product_availibility_status = product_availibility_status;
	}
	public String getProductAvalibilityStatus() {
		return productAvalibilityStatus;
	}
	public void setProductAvalibilityStatus(String productAvalibilityStatus) {
		this.productAvalibilityStatus = productAvalibilityStatus;
	}
	public InputStream getProduct_image() {
		return product_image;
	}
	public void setProduct_image(InputStream product_image) {
		this.product_image = product_image;
	}
	public Blob getProductImage() {
		return productImage;
	}
	public void setProductImage(Blob productImage) {
		this.productImage = productImage;
	}
	public UserDetailsDto getOwner_id() {
		return owner_id;
	}
	public void setOwnerid(UserDetailsDto owner_id) {
		this.owner_id = owner_id;
	}
	
	public String getRejected_Reason() {
		return Rejected_Reason;
	}
	public void setRejected_Reason(String rejected_Reason) {
		Rejected_Reason = rejected_Reason;
	}
	@Override
	public String toString() {
		return "ProductDTO [ProductId=" + product_id + ", ProductName=" + product_name + ", ProductPrice=" + product_price
				+ ", ProductDescription=" + product_description + ", PurchasedDate=" + purchased_date
				+ ", ProductAvailabilityStatus=" + product_availibility_status + ", ProductImage=" + product_image
				+ ", ownerId=" + owner_id + "]";
	}
	
}