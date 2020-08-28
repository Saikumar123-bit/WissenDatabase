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

@WebServlet("/GetUserReqNotificationsAction")
public class GetUserReqNotificationAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		try {
			getUserReqNotificationsProcess(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getUserReqNotificationsProcess(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException, SQLException {
		
		System.out.println("\n-- now control in getuserReqNotificationAction 000000000000000000");
		
		HttpSession session=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;		
		UserDetailsDto user=null;
		String data=null;
		//String data="";
		ProductExchangeDeligates pedi=null;
		List<ProductRequestDto> userNotificationList=null;
		Gson gson=null;
		
		//genereal settings
		pw=res.getWriter();
		
		//set content type
		res.setContentType("application/json");
		
		//create session object
		session=req.getSession(false);
		
		user=new UserDetailsDto();
		
		//System.out.println("User information is--->"+session.getAttribute("userInfo"));
		//System.out.println("User id is--->"+session.getAttribute("userid"));
		
		if(session.getAttribute("userInfo")!=null){
		
			user=(UserDetailsDto)session.getAttribute("userInfo");
			
			System.out.println("Servlet User id--123---------->"+user.getUserid());
			//data=getUserReqNotificationJson(user);
			session.setAttribute("userNotificationList", data);
			
			//create Object ProductExchangeDeliagets
			pedi=new ProductExchangeDeligates();
			
			try{
				//System.out.println("Now control in getuserreqNotificationJson and sending control to dao through deligate, service layers");
				userNotificationList=pedi.getUserReqNotification(user);
				
				for(ProductRequestDto p:userNotificationList){
					System.out.println("Logined person user id--->"+p.getRequest_product_id().getOwner_id().getUserid());
					System.out.println("Exchanged person user id--->"+p.getExchange_product_id().getOwner_id().getUserid());
					session.setAttribute("excuser", p.getExchange_product_id().getOwner_id().getUserid());
				}
				
				//System.out.println("\n Now control reached to getUserRequestNotificationAction--");
			}catch(SQLException e1){
				System.out.println("--->"+e1.getMessage());
			}catch(Exception e2){
				System.out.println("-->"+e2.getMessage());
			}
			
			gson=new Gson();
			
			try{
				data = "{\"success\":\"yes\","+ "\"data\":"+gson.toJson(userNotificationList)+"}";
				System.out.println(" 7777777796574512 json data in GetUserrequestNotification-->"+data);
			}catch(Exception e){
				data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
				//System.out.println("json data in GetUserrequestNotification-->"+data);
			}

			
			//System.out.println("get UserRequestNotifcationActionJson-->\n"+data);
			
			pw.println(data);			
		}else {
			pw.println("<h2>You Must Login First </h2>");
			rd=req.getRequestDispatcher("/Login.jsp");
			rd.include(req, res);
		}
	}
	private String getUserReqNotificationJson(UserDetailsDto user)throws SQLException,IOException {
		
		String data="";
		ProductExchangeDeligates pedi=null;
		List<ProductRequestDto> userNotificationList=null;
		Gson gson=null;
		
		//create Object ProductExchangeDeliagets
		pedi=new ProductExchangeDeligates();
		
		try{
			//System.out.println("Now control in getuserreqNotificationJson and sending control to dao through deligate, service layers");
			userNotificationList=pedi.getUserReqNotification(user);
			
			//System.out.println("\n Now control reached to getUserRequestNotificationAction--");
		}catch(SQLException e1){
			System.out.println("--->"+e1.getMessage());
		}catch(Exception e2){
			System.out.println("-->"+e2.getMessage());
		}
		
		gson=new Gson();
		
		try{
			data = "{\"success\":\"yes\","+ "\"data\":"+gson.toJson(userNotificationList)+"}";
			//System.out.println(" 7777777796574512 json data in GetUserrequestNotification-->"+data);
		}catch(Exception e){
			data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
			//System.out.println("json data in GetUserrequestNotification-->"+data);
		}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}