package com.nacre.productexchange.service;

import java.io.OutputStream;
import java.sql.SQLException;

import com.nacre.productexchange.daoi.ReportDaoImpl;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.formbean.ProductRequestNotificationFormBean;
import com.nacre.productexchange.util.Download;

public class ReportServiceImpl implements ReportServiceI{

	@Override
	public ProductRequestNotificationFormBean viewExchangeDetails(int excProductId)throws SQLException, Exception {
		
		System.out.println("Service Started----");
		
		// TODO Auto-generated method stub
		ReportDaoImpl dao=null;
		ProductRequestNotificationFormBean excInfo=null;
		
		//create report object
		dao=new ReportDaoImpl();
		
		excInfo=dao.getExchangeDetails(excProductId);
		
		return excInfo;
	}

	@Override
	public boolean getExchangeDetails(int excProductId, OutputStream out) throws SQLException, Exception {
		
		// TODO Auto-generated method stub
		ReportDaoImpl dao=null;
		ProductRequestNotificationFormBean excInfo=null;
		ProductDto exe=null;
		ProductDto req=null;
				
		//create report object
		dao=new ReportDaoImpl();
		excInfo=dao.getExchangeDetails(excProductId);
				
		exe=excInfo.getExchangeProduct();
		req=excInfo.getRequestProduct();
				
		Download.download(excInfo, out);
				
		return false;
	}
}