package com.nacre.productexchange.service;

import java.io.OutputStream;
import java.sql.SQLException;

import com.nacre.productexchange.formbean.ProductRequestNotificationFormBean;

public interface ReportServiceI {
	
	public ProductRequestNotificationFormBean viewExchangeDetails(int excProductId) throws SQLException, Exception;
	boolean getExchangeDetails(int excProductId, OutputStream out) throws SQLException, Exception;

}
