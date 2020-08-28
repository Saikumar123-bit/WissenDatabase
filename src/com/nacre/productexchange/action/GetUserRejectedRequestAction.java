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

@WebServlet("/GetUserRejectedNotificationAction")
public class GetUserRejectedRequestAction extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		
		System.out.println("\n---Now control in getUserRejectedNotificationAction");
		getUserRejectedNotificationProcess(req,res);
		
	}
	private void getUserRejectedNotificationProcess(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		
		HttpSession session=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		UserDetailsDto user=null;
		String data=null;
		
		//setresponsecontent type
		res.setContentType("application/json");
		
		//create printWriter objetc
		pw=res.getWriter();
		
		//create session object
		session=req.getSession(false);
		
		if(session.getAttribute("userInfo")!=null){
			user=new UserDetailsDto();
			user=(UserDetailsDto)session.getAttribute("userInfo");
			
			System.out.println("Servlet User id-->"+user.getUserid());
			try {
				data=getUserRejectedNotificationJson(user);
			} catch (SQLException e) {
				System.out.println("Problem getuserrejectedrequestAction-->"+e.getMessage());
			}
			System.out.println("get User Rejected NotifcationJson-->\n"+data);
			pw.println(data);			
		}else {
			System.out.println("contril in else block of getuserrejectedrequestAction");
			rd=req.getRequestDispatcher("/Login.jsp");
			rd.include(req, res);
			pw.println("<h2>You Must Login First </h2>");
		}
	}
	private String getUserRejectedNotificationJson(UserDetailsDto user)throws SQLException,IOException {
		
		String data="";
		ProductExchangeDeligates pedi=null;
		List<ProductRequestDto> userNotificationList=null;
		Gson gson=null;
		
		//create Object ProductExchangeDeliagets
		pedi=new ProductExchangeDeligates();
		
		try{
			userNotificationList=pedi.getUserRejectedNotifications(user);
			System.out.println("\n Now control reached to getUserRejectedRequestAction--");
		}catch(SQLException e1){
			System.out.println("--->"+e1.getMessage());
		}catch(Exception e2){
			System.out.println("-->"+e2.getMessage());
		}	
		gson=new Gson();
		try{
			data = "{\"success\":\"yes\","+ "\"data\":"+gson.toJson(userNotificationList)+"}";
			System.out.println("Json data in Get User rejected RequestAction--->"+data);
		}catch(Exception e){
			data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
			System.out.println("Json data in Get User rejected RequestAction--->"+data);
		}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}