package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.ProductExchangeDeligates;
import com.nacre.productexchange.dto.ProductRequestDto;
import com.nacre.productexchange.dto.UserDetailsDto;

@WebServlet("/GetUserApprovedNotificationsActions")
public class GetUserApprovedNotificationAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		//System.out.println("\n---Now control in GetApprovedNotificationAction---2nd one");
		getUserApprovedNotificationProcess(req,res);
	}
	private void getUserApprovedNotificationProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		UserDetailsDto user=null;
		String data=null;
		
		// get response writer object
		pw=res.getWriter();
		
		//create session object
		session=req.getSession(false);
		
		if(session.getAttribute("userInfo")!=null){
			user=new UserDetailsDto();
			user=(UserDetailsDto)session.getAttribute("userInfo");
			//System.out.println("control transferring-----");
			
			//set response content type application/json
			res.setContentType("application/json");
			req.setCharacterEncoding("utf8"); 
			
			data=getUserApprovedNotificationJson(user);			
			
			pw.println(data);
		}else{
			rd=req.getRequestDispatcher("/Login.jsp");
			rd.include(req, res);
			pw.println("<h2>You Must Login first </h2");
		}
	}
	private String getUserApprovedNotificationJson(UserDetailsDto user) {
		
		String data="";
		ProductExchangeDeligates notificationDeligates=null;
		List<ProductRequestDto> userNotificationList=null;
		Gson gson=null;
		
		//create object
		notificationDeligates = new ProductExchangeDeligates();
		
		try{
			userNotificationList=notificationDeligates.getUserApprovedNotification(user);
			//System.out.println("\n Now control reached to GetApproved NotificationAction() on servlet class");
			for(ProductRequestDto pdt:userNotificationList){
				System.out.println("product name---"+pdt.getRequest_product_id().getProduct_name());
			}
		}catch(SQLException e){
			System.out.println("-=-=-=->"+e.getMessage());
		}catch(Exception e){
			System.out.println("======>"+e.getMessage());
		}
		
		gson=new Gson();
		
		try{
			data = "{\"success\":\"yes\","+ "\"data\":"+gson.toJson(userNotificationList)+"}";
			//System.out.println("Gson data in GetApprovedNotificationAction-->"+data);
		}catch(Exception e){
			data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
			//System.out.println("Gson data in GetApprovedNotificationAction-->"+data);
		}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}