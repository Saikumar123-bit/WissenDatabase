package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.nacre.productexchange.deligate.ProductExchangeDeligates;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;

@WebServlet("/GetApprovedNotificationDetailsAction")
public class GetApprovedNotificationDetailsAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		System.out.println("Now control came from NotificationApproveReply.jsp status id--->5");
		HttpSession session=null;
		PrintWriter pw=null;
		UserDetailsDto user=null;
		Integer userId=0,productId=0,reqStatus=0;
		ProductExchangeDeligates ped=null;
		List<GetNotificationDetailsFormBean> gnfb=null;
		
		//create session object
		session=req.getSession(false);
		pw=res.getWriter();
		
		if(session.getAttribute("userInfo")!=null){
			
			user=new UserDetailsDto();
			user=(UserDetailsDto)session.getAttribute("userInfo");
			
			//get userId
			userId=(Integer)session.getAttribute("userid");
			
			System.out.println("Product Request Id---"+req.getParameter("prodReqId"));
			System.out.println("Reqest STatus---"+req.getParameter("reqStatus"));
			
			productId=Integer.parseInt(req.getParameter("prodReqId"));
			reqStatus=Integer.parseInt(req.getParameter("reqStatus"));
			
			System.out.println(productId+"------"+reqStatus);
			res.setContentType("application/json");
			
			//create ped object
			ped=new ProductExchangeDeligates();
			
			try{
				 //gnfb=ped.getRequestNotificationDetails(userId, productId, reqStatus);
				 
				 for(GetNotificationDetailsFormBean g:gnfb){
					 
					 System.out.println(g.getReqProdId()+"---"+g.getReqProdName()+"----"+g.getExcProdName()+"----"+g.getDateOfApproval()+"<-------Details of form bean");
					 
				 } 
			 }catch(Exception e){
				 System.out.println("Problem here is-->"+e.getMessage());
				 e.printStackTrace();
			 }
		}		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
	

}
