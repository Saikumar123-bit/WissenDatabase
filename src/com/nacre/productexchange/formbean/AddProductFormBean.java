package com.nacre.productexchange.formbean;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;

public class AddProductFormBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String product_Id;
	private String product_name;
	private String product_price;
	private String product_description;
	private Date purchased_Date;
	private int product_Avalible_Status;
	private InputStream product_Image;
	private String status_Id;
	private String owner_Id;
	
	public String getProduct_Id() {
		return product_Id;
	}
	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public Date getPurchased_Date() {
		return purchased_Date;
	}
	public void setPurchased_Date(Date prodDate) {
		this.purchased_Date = purchased_Date;
	}
	public int getProduct_Avalible_Status() {
		return product_Avalible_Status;
	}
	public void setProduct_Avalible_Status(int productAvailibilityStatusId) {
		this.product_Avalible_Status = productAvailibilityStatusId;
	}
	public InputStream getProduct_Image() {
		return product_Image;
	}
	public void setProduct_Image(InputStream product_Image) {
		this.product_Image = product_Image;
	}
	public String getStatus_Id() {
		return status_Id;
	}
	public void setStatus_Id(String status_Id) {
		this.status_Id = status_Id;
	}
	public String getOwner_Id() {
		return owner_Id;
	}
	public void setOwner_Id(String owner_Id) {
		this.owner_Id = owner_Id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "AddProductFormBeam [ProductId=" + product_Id + ", productName=" + product_name + ", productPrice="
				+ product_price + ", productDescription=" + product_description + ", purchageDate=" + purchased_Date
				+ ", productAvailableStatus=" + product_Avalible_Status + ", productImage=" + product_Image+ productId+",ProductId";
	}
}