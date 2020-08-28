package com.nacre.productexchange.deligate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nacre.productexchange.dto.ApproveRejectBean;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.ProductRequestDto;
import com.nacre.productexchange.dto.StatusDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.ApproveRejectFormBean;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;
import com.nacre.productexchange.formbean.GetProductFormBean;
import com.nacre.productexchange.formbean.NotificationDetailsFormBean;
import com.nacre.productexchange.formbean.ProductRequestFormBean;
import com.nacre.productexchange.service.ProductExchangeServiceImpl;
import com.nacre.productexchange.util.ImageUtility;

public class ProductExchangeDeligates {
	
	public int requestExchange(ProductRequestFormBean prfb) throws SQLException, DbException {
		
		ProductExchangeServiceImpl pesi=null;

		//create object
		pesi=new ProductExchangeServiceImpl();
		
		return pesi.requestExchange(prfb);
	}	
	
	public boolean checkExchangeProduct(ProductDto product)throws SQLException,IOException,Exception {
		
		ProductExchangeServiceImpl pesi=null;

		//create object
		pesi=new ProductExchangeServiceImpl();

		
		return pesi.checkExchangeProduct(product);
	}
	
	//public List<GetProductFormBean> getExchangeProducts(Integer userId)throws Exception{
	public List<GetNotificationDetailsFormBean> getExchangeProducts(Integer userId)throws Exception{
		
		ProductExchangeServiceImpl pesi=null;
		List<GetProductFormBean> pb=null;
		List<ProductRequestDto> prd=null;
		List<ProductDto> lip=null;
		List<GetNotificationDetailsFormBean> lg=null;
		GetNotificationDetailsFormBean getbean=null;
		String reqProdEncodedImg=null,excProdEncodedImg=null;
		InputStream reqProdImage=null,excProdImage=null;
		
		//create object
		pesi=new ProductExchangeServiceImpl();
		
		prd=pesi.getExchangeProducts(userId);
		System.out.println("Now control reversed to ProductExchangeDeligates ");
		
		//create arraylist objet
		pb=new ArrayList<GetProductFormBean>();
		lg=new ArrayList<>();
		
		for(ProductRequestDto tblproduct:prd){
			System.out.println("Product request id---"+tblproduct.getProduct_request_id());
			System.out.println("In Exchange Deligate class");
			System.out.println("requested product details---"+tblproduct.getProduct_request_id()+"------"+tblproduct.getRequest_product_id().getProduct_id()+"----------"+tblproduct.getRequest_product_id().getProduct_name()+"-------------"+"----Image here----"+tblproduct.getRequest_product_id().getOwner_id().getUserid());
			
			System.out.println("Exchange Product id---"+tblproduct.getExchange_product_id().getProduct_id());
			
			System.out.println("------"+tblproduct.getExchange_product_id().getProduct_name()+"---------"+"Image here---");
			
			System.out.println("-----------"+tblproduct.getExchange_product_id().getOwner_id().getUserid());
			
			//create getnotificationdetailsfrombean class object
			getbean=new GetNotificationDetailsFormBean();
			
			getbean.setProdreqid(tblproduct.getProduct_request_id());
			
			//setting requset product details
			getbean.setReqProdId(tblproduct.getRequest_product_id().getProduct_id());
			getbean.setReqProdName(tblproduct.getRequest_product_id().getProduct_name());
			getbean.setReqProdPrice(tblproduct.getRequest_product_id().getProduct_price());
			getbean.setReqProdDesc(tblproduct.getRequest_product_id().getProduct_description());
			getbean.setReqProdPurchasedDate(tblproduct.getRequest_product_id().getPurchased_date());
			
			reqProdImage=tblproduct.getRequest_product_id().getProduct_image();
			if(reqProdImage!=null){
				reqProdEncodedImg=ImageUtility.encodeImage(reqProdImage);
				getbean.setReqProdImg(reqProdEncodedImg);
			}
			getbean.setReqProdOwnerId(tblproduct.getRequest_product_id().getOwner_id().getUserid());
			System.out.println("Current user Id---"+tblproduct.getRequest_product_id().getOwner_id().getUserid());
			
			//setting exchange product details
			getbean.setExcProdId(tblproduct.getExchange_product_id().getProduct_id());
			getbean.setExcProdName(tblproduct.getExchange_product_id().getProduct_name());
			getbean.setExcProdPrice(tblproduct.getExchange_product_id().getProduct_price());
			getbean.setExcProdDesc(tblproduct.getExchange_product_id().getProduct_description());
			getbean.setExcProdPurchasedDate(tblproduct.getExchange_product_id().getPurchased_date());
			
			//excProdImage=tblproduct.getExchange_product_id().getProduct_image();
			excProdImage=(InputStream) tblproduct.getExchange_product_id().getProduct_image();
			
			if(excProdImage!=null){
				excProdEncodedImg=ImageUtility.encodeImage(excProdImage);
				getbean.setExcProdImg(excProdEncodedImg);
			}
			getbean.setExcProdOwnerId(tblproduct.getExchange_product_id().getOwner_id().getUserid());
			System.out.println("Exchnage Login User Id--->"+tblproduct.getExchange_product_id().getOwner_id().getUserid());
			
			getbean.setDateOfReq(tblproduct.getDate_of_request());
			
			//add values to getbean
			lg.add(getbean);
		}
			
		return lg;
	}
	
	public List<GetNotificationDetailsFormBean> getRequestNotificationDetails(Integer userId,Integer productRequestId,Integer requestStatus)throws Exception {
		
		System.out.println("Now Control in productExchangeDeligates class on GetRequestNotificationDetailsActions() emthod");
		
		ProductExchangeServiceImpl pesi=null;
		List<GetNotificationDetailsFormBean> gnfb=null;
		List<ProductRequestDto> p=null;
		List<GetNotificationDetailsFormBean> pb=null;
		GetNotificationDetailsFormBean bean=null;
		InputStream reqProdIs=null,reqProdImage=null;
		String reqProdEncodedImg=null,reqProdEnImg=null;
		StatusDto st=null;
		
		//create object
		pesi = new ProductExchangeServiceImpl();
		st=new StatusDto();
		
		if(requestStatus==6){
			 p=pesi.getRequestNotificationsDetails(userId, productRequestId, requestStatus);
			 System.out.println("---->sai 888888  Control reached productExchangeDeligates class GetrequestNotificationDetails ");
			 pb=new ArrayList<>();
			 
			for(ProductRequestDto tblproduct:p){
				bean=new GetNotificationDetailsFormBean();
				
				//bean.setProdReqId(tblproduct.getRequest_product_id().toString());
				System.out.println("Request Product details--->"+tblproduct.getRequest_product_id().getProduct_id()+"-------"+tblproduct.getRequest_product_id().getProduct_price()+"----"+tblproduct.getRequest_product_id().getProduct_description()+"----"+tblproduct.getRequest_product_id().getPurchased_date());
				System.out.println("Proruct name---"+tblproduct.getRequest_product_id().getProduct_name());	
				System.out.println("----"+tblproduct.getRequest_product_id().getProduct_availibility_status().getStatus_id());
				
				//bean.setProdReqId(tblproduct.getProduct_request_id());
				bean.setProdreqid(tblproduct.getProduct_request_id());
				System.out.println("Product id ahahahahahahah--->"+tblproduct.getProduct_request_id());
				
				bean.setReqProdId(tblproduct.getRequest_product_id().getProduct_id());
				bean.setReqProdName(tblproduct.getRequest_product_id().getProduct_name());
				bean.setReqProdPrice(tblproduct.getRequest_product_id().getProduct_price());
				bean.setReqProdDesc(tblproduct.getRequest_product_id().getProduct_description());
				bean.setReqProdPurchasedDate(tblproduct.getRequest_product_id().getPurchased_date());
				
				bean.setReqProdAvalStatus(tblproduct.getRequest_product_id().getProduct_availibility_status().getStatus_id());
				
				reqProdIs=(tblproduct.getRequest_product_id().getProduct_image());
				
				if(reqProdIs!=null){
					reqProdEncodedImg=ImageUtility.encodeImage(reqProdIs);
					bean.setReqProdImg(reqProdEncodedImg);
					//System.out.println("image in--->exchange deligae on request---"+reqProdEncodeImg);
				}
				bean.setReqProdOwnerId(tblproduct.getRequest_product_id().getOwner_id().getUserid());
				
				System.out.println("Userid-->"+tblproduct.getRequest_product_id().getOwner_id().getUserid());
				
				System.out.println("Exchange product id details--->");
				System.out.println(tblproduct.getExchange_product_id().getProduct_id()+"----"+tblproduct.getExchange_product_id().getProduct_name()+"----"+tblproduct.getExchange_product_id().getProduct_price()+"-----"+tblproduct.getExchange_product_id().getProduct_description()+"---"+tblproduct.getExchange_product_id().getPurchased_date());
				System.out.println("-ttttttttttttttttt---"+tblproduct.getExchange_product_id().getProduct_availibility_status().getStatus_id()); // not working
				
				bean.setExcProdId(tblproduct.getExchange_product_id().getProduct_id());
				bean.setExcProdName(tblproduct.getExchange_product_id().getProduct_name());
				bean.setExcProdPrice(tblproduct.getExchange_product_id().getProduct_price());
				bean.setExcProdDesc(tblproduct.getExchange_product_id().getProduct_description());
				//bean.setExcProdPurchasedDate(tblproduct.getExchange_product_id().getPurchased_date());
				bean.setExcProdPurchasedDate(tblproduct.getExchange_product_id().getPurchased_date());
				
				bean.setExcProdAvalStatus(tblproduct.getExchange_product_id().getProduct_availibility_status().getStatus_id());
				
				System.out.println("Status id---->in deligate exchange on getrequestnotificationdetails---->"+tblproduct.getProduct_request_status().getStatus_id());
				//System.out.println("Previous version of status id----"+tblproduct.getExchange_product_id().getProduct_availibility_status().getStatus_id());
				bean.setProductRqstSts(tblproduct.getProduct_request_status().getStatus_id());
				
				reqProdImage=(tblproduct.getExchange_product_id().getProduct_image());
				
				if(reqProdImage!=null){
					reqProdEnImg=ImageUtility.encodeImage(reqProdImage);
					bean.setExcProdImg(reqProdEnImg);
					//System.out.println("image in--->exchange deligae on exchange---"+reqProdEnImg);
				}
				
				bean.setReqProdOwnerId(tblproduct.getRequest_product_id().getOwner_id().getUserid());
				bean.setDateOfReq(tblproduct.getDate_of_request());
				
				bean.setDateOfApproval((Date) tblproduct.getDate_of_approval());;
				
				System.out.println("Date of approval---->"+tblproduct.getDate_of_approval());
				pb.add(bean);
				
			}
		}
		/*else if(requestStatus==4){
			bean=new GetNotificationDetailsFormBean();
			bean=pesi.getRejNotificationSetails(ProductRequestDto dto);
		}*/
		return pb;
	}
	public GetNotificationDetailsFormBean getRejNotificationDetails(NotificationDetailsFormBean notificationdetailsformbean)throws SQLException,Exception {
		
		ProductExchangeServiceImpl pesi=null;
		
		//create object
		pesi = new ProductExchangeServiceImpl();
		
		return pesi.getRejNotificationSetails(notificationdetailsformbean);
	}
	public List<ProductRequestDto> getUserReqNotification(UserDetailsDto user)throws SQLException,Exception {
		
		ProductExchangeServiceImpl pesi=null;
		
		//create object
		pesi = new ProductExchangeServiceImpl();
		
		return pesi.getUserReqNotifications(user);
	}
	public List<ProductRequestDto> getUserApprovedNotification(UserDetailsDto user)throws SQLException,Exception {
		
		ProductExchangeServiceImpl pesi=null;
		
		//create object
		pesi = new ProductExchangeServiceImpl();
		
		return pesi.getUserApprovedNotifications(user);
	}
	public List<ProductRequestDto> getUserRejectedNotifications(UserDetailsDto user)throws SQLException,Exception {
		
		ProductExchangeServiceImpl pesi=null;
		
		//create object
		pesi=new ProductExchangeServiceImpl();
		
		return pesi.getUserRejectedNotifications(user);
	}
	public boolean reqApproved(ApproveRejectFormBean approverejectformbean)throws Exception {
		
		ProductRequestDto prd=null;
		ApproveRejectBean approverejectbean=null; 
		ProductDto reqProdId=null;
		ProductDto excProdId=null;
		ProductExchangeServiceImpl pesi=null;
		
		prd = new ProductRequestDto();
		
		//add values to dto class
		prd.setProduct_request_id(Integer.parseInt(approverejectformbean.getProdreqId()));
		prd.setProduct_rejected_reason(approverejectformbean.getRejectedReason());
		//prd.setDateOfApproval(approverejectformbean.getDateOfApproval());
		prd.setStatusId(approverejectformbean.getStatusId());
		
		approverejectbean = new ApproveRejectBean();
		//set values to bean class
		approverejectbean.setProdReqId(prd);
		
		reqProdId=new ProductDto();
		//Request product id
		reqProdId.setProduct_id(Integer.parseInt(approverejectformbean.getReqProdId()));
		approverejectbean.setReqProdId(reqProdId);
		
		excProdId=new ProductDto();
		//Exchnage Product Id
		excProdId.setProduct_id(Integer.parseInt(approverejectformbean.getExcProdId()));
		approverejectbean.setExcProdId(excProdId);
		
		//create exchangeservice class object
		pesi=new ProductExchangeServiceImpl();
		
		return pesi.reqApproved(approverejectbean);
	}
	public boolean reqRejected(ApproveRejectFormBean approverejectformbean)throws SQLException,IOException,Exception {
		
		ProductRequestDto prdt=null;
		ApproveRejectBean arb=null;
		ProductDto reqProdId=null;
		ProductDto excProdId=null;
		ProductExchangeServiceImpl service=null;
		
		//create object
		prdt=new ProductRequestDto();
		prdt.setProduct_request_id(Integer.parseInt(approverejectformbean.getProdreqId()));
		prdt.setProduct_rejected_reason(approverejectformbean.getRejectedReason());
		
		arb=new ApproveRejectBean();
		arb.setProdReqId(prdt);
		
		reqProdId=new ProductDto();
		reqProdId.setProduct_id(Integer.parseInt(approverejectformbean.getReqProdId()));
		arb.setReqProdId(reqProdId);
		
		excProdId=new ProductDto();
		excProdId.setProduct_id(Integer.parseInt(approverejectformbean.getExcProdId()));
		arb.setExcProdId(excProdId);
		
		service = new ProductExchangeServiceImpl();
		
		return service.reqRejected(arb);
	}	
}
