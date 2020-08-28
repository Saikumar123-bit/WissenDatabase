package com.nacre.productexchange.exception;

public class NoProductAvailableException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoProductAvailableException(String msg){
		super(msg);
	}
}
