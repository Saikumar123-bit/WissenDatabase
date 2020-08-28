
package com.nacre.productexchange.daoi;

import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.dto.ApproveRejectBean;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.ProductRequestDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;
import com.nacre.productexchange.formbean.NotificationDetailsFormBean;
import com.nacre.productexchange.formbean.ProductRequestFormBean;

public interface ProductExchangeDaoI {
	

	public int requestExchange(ProductRequestFormBean productrequest)throws SQLException,DbException;
	
	public boolean checkExchangeProduct(ProductDto product)throws SQLException,DbException;
    
	public List<ProductRequestDto> getExchangeProducts(Integer userId)throws Exception;
	
    public List<ProductRequestDto> getRequestNotificationsDetails(Integer UserId,Integer productRequestId,Integer requestStatus)throws SQLException,Exception;

    public GetNotificationDetailsFormBean getRejNotificationSetails(NotificationDetailsFormBean notificationDetailsFormBean) throws SQLException,Exception;
    
    public List<ProductRequestDto> getUserReqNotifications(UserDetailsDto user) throws SQLException,Exception;
    
    public List<ProductRequestDto> getUserApprovedNotifications(UserDetailsDto user) throws SQLException,Exception;
    
    public List<ProductRequestDto> getUserRejectedNotifications(UserDetailsDto user) throws SQLException,Exception;
    
    public boolean reqApproved(ApproveRejectBean approveRejectBean) throws SQLException,Exception;
    
    public boolean reqRejected(ApproveRejectBean approveRejectBean) throws SQLException,Exception;

}
