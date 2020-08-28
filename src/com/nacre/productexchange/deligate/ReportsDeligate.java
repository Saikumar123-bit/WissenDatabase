package com.nacre.productexchange.deligate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.nacre.productexchange.service.ReportServiceImpl;

public class ReportsDeligate {
	
	public boolean getExchangeDeligates(int excProductId,HttpServletResponse res)throws SQLException,Exception{
		
		ReportServiceImpl service=null;
		
		//create ReportService class object
		service=new ReportServiceImpl();
		
		return service.getExchangeDetails(excProductId, res.getOutputStream());		
	}
	/*public ProductRequestNotificationFormBean viewExchangeDeligates(int excProductId)throws SQLException, Exception{
		
		ReportServiceImpl service=null;
		
		// create ReportService class object
		service = new ReportServiceImpl();
		
		return service.viewExchangeDetails(excProductId);
	}*/
	
}
