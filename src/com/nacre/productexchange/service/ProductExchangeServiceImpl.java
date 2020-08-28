package com.nacre.productexchange.service;

import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.daoi.ProductExchangeDaoImpl;
import com.nacre.productexchange.dto.ApproveRejectBean;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.ProductRequestDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;
import com.nacre.productexchange.formbean.NotificationDetailsFormBean;
import com.nacre.productexchange.formbean.ProductRequestFormBean;

public class ProductExchangeServiceImpl implements ProductExchangeServiceI {

	@Override
	public int requestExchange(ProductRequestFormBean productrequest) throws SQLException, DbException {

		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.requestExchange(productrequest);
	}
	
	@Override
	public boolean checkExchangeProduct(ProductDto product) throws SQLException, DbException {
		// TODO Auto-generated method stub
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi=new ProductExchangeDaoImpl();
		
		return pedi.checkExchangeProduct(product);
	}
	
	public List<ProductRequestDto> getExchangeProducts(Integer userId)throws Exception{
		
		// TODO Auto-generated method stub
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi=new ProductExchangeDaoImpl();
		
		return pedi.getExchangeProducts(userId);
	}

	
	@Override
	public List<ProductRequestDto> getRequestNotificationsDetails(Integer UserId, Integer productRequestId,Integer requestStatus) throws SQLException, Exception {
		
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.getRequestNotificationsDetails(UserId, productRequestId, requestStatus);
	}

	@Override
	public GetNotificationDetailsFormBean getRejNotificationSetails(NotificationDetailsFormBean notificationDetailsFormBean) throws SQLException, Exception {
		
		return null;
	}

	@Override
	public List<ProductRequestDto> getUserReqNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.getUserReqNotifications(user);
	}

	@Override
	public List<ProductRequestDto> getUserApprovedNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.getUserApprovedNotifications(user);
	}

	@Override
	public List<ProductRequestDto> getUserRejectedNotifications(UserDetailsDto user) throws SQLException, Exception {
		
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.getUserRejectedNotifications(user);
	}

	@Override
	public boolean reqApproved(ApproveRejectBean approveRejectBean) throws SQLException, Exception {
		
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.reqApproved(approveRejectBean);
	}

	@Override
	public boolean reqRejected(ApproveRejectBean approveRejectBean) throws SQLException, Exception {
			
		ProductExchangeDaoImpl pedi=null;
		
		//create object
		pedi = new ProductExchangeDaoImpl();
		
		return pedi.reqRejected(approveRejectBean);
	}

}
