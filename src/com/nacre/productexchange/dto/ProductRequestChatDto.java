package com.nacre.productexchange.dto;

import java.io.Serializable;

public class ProductRequestChatDto implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String product_request_chat_id;
	private String chat_msg;
	private UserDetailsDto sender_id;
	private UserDetailsDto receiver_id;
	private ProductRequestDto product_request_id;
	private String product_request_chat_date;
	private String product_request_chat_time;
	
	public String getProduct_request_chat_id() {
		return product_request_chat_id;
	}
	public void setProduct_request_chat_id(String product_request_chat_id) {
		this.product_request_chat_id = product_request_chat_id;
	}
	public String getChat_msg() {
		return chat_msg;
	}
	public void setChat_msg(String chat_msg) {
		this.chat_msg = chat_msg;
	}
	public UserDetailsDto getSender_id() {
		return sender_id;
	}
	public void setSender_id(UserDetailsDto sender_id) {
		this.sender_id = sender_id;
	}
	public UserDetailsDto getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(UserDetailsDto receiver_id) {
		this.receiver_id = receiver_id;
	}
	public ProductRequestDto getProduct_request_id() {
		return product_request_id;
	}
	public void setProduct_request_id(ProductRequestDto product_request_id) {
		this.product_request_id = product_request_id;
	}
	public String getProduct_request_chat_date() {
		return product_request_chat_date;
	}
	public void setProduct_request_chat_date(String product_request_chat_date) {
		this.product_request_chat_date = product_request_chat_date;
	}
	public String getProduct_request_chat_time() {
		return product_request_chat_time;
	}
	public void setProduct_request_chat_time(String product_request_chat_time) {
		this.product_request_chat_time = product_request_chat_time;
	}
	
	
}