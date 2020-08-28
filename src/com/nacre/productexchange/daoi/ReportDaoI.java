package com.nacre.productexchange.daoi;

import java.sql.SQLException;

import com.nacre.productexchange.formbean.ProductRequestNotificationFormBean;

public interface ReportDaoI {
	public ProductRequestNotificationFormBean getExchangeDetails(int excProductId)throws SQLException,Exception;
}
