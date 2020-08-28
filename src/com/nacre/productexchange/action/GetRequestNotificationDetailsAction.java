package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.ProductExchangeDeligates;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.GetNotificationDetailsFormBean;

@WebServlet("/GetRequestNotificationDetailsAction")
public class GetRequestNotificationDetailsAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		
		GetNotificationDetailsProcess(req,res);
		
	}
	private void GetNotificationDetailsProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		
		UserDetailsDto user=null;
		HttpSession session=null;
		RequestDispatcher rd=null;
		Integer userId=0;
		Integer productId=0;
		Integer requestStatus=0;
		String data=null;
		PrintWriter pw=null;
		List<GetNotificationDetailsFormBean> gnfb=null;
		ProductExchangeDeligates ped=null;
		String g1=null;
		JSONObject json=null;
		 	
		
		System.out.println("\n Now control in GetNotificationDetailsAction class");
		
		session=req.getSession(false);
		pw=res.getWriter();
		
		if(session.getAttribute("userInfo")!=null){
			
			user = new UserDetailsDto();
			
			user=(UserDetailsDto)session.getAttribute("userInfo");
			userId=(Integer)session.getAttribute("userid");
			
			System.out.println("Servlet User Id in getnotificationdetailaction-->"+user.getUserid());
			
			productId=Integer.parseInt(req.getParameter("prodReqId"));
			requestStatus=Integer.parseInt(req.getParameter("reqStatus"));
			System.out.println(productId+"-----"+requestStatus);
			
			//data=getNotificationDetailsJson(userId,productId,requestStatus);
			//String g = new Gson().toJson(data);
			
			res.setContentType("application/json");
			
			 //create object
			 ped = new ProductExchangeDeligates();
			 json=new JSONObject();
			 
			 try{
				 gnfb=ped.getRequestNotificationDetails(userId, productId, requestStatus);
				 for(GetNotificationDetailsFormBean g:gnfb){
					 
					 System.out.println(g.getReqProdId()+"---"+g.getReqProdName()+"----"+g.getExcProdName()+"----"+g.getDateOfApproval()+"<-------Details of form bean");
					 System.out.println("Product Requst Id--->"+g.getProdreqid());
					 System.out.println("RRequested product id--------kkkkkkkkkkkkkkkkkkkkkk-----"+g.getReqProdId());
					 System.out.println("Exchange product Request Id--->"+g.getExcProdId());
					 System.out.println("Shanker's film 2.0---- req status------"+g.getProductRqstSts());
					 
					 json.put("prodReqId", g.getProdreqid());
					 
					 json.put("reqProdId", g.getReqProdId());
					 json.put("reqProdName", g.getReqProdName());
					 json.put("reqProdPrice", g.getReqProdPrice());
					 json.put("reqProdDesc", g.getReqProdDesc());
					 json.put("reqProdPurchasedDate", g.getReqProdPurchasedDate());
					 json.put("reqProdImg", g.getReqProdImg());
					 json.put("reqProdAvalStatus", g.getReqProdAvalStatus());
					 
					 json.put("excProdId", g.getExcProdId());
					 json.put("excProdName", g.getExcProdName());
					 json.put("excProdPrice", g.getExcProdPrice());
					 json.put("excProdDesc", g.getExcProdDesc());
					 json.put("excProdPurchasedDate", g.getExcProdPurchasedDate());
					 json.put("excProdImg", g.getExcProdImg());
					 json.put("excProdAvalStatus", g.getExcProdAvalStatus());
					 
					 json.put("dateOfApproval", g.getDateOfApproval()); // my one
					 json.put("statusId", g.getProductRqstSts());
					 
   					 res.getWriter().print(json);
   					 
				 } 
			 }catch(Exception e){
				 System.out.println("Problem here is-->"+e.getMessage());
				 e.printStackTrace();
			 }

			 g1=new Gson().toJson(gnfb);
			 data ="{\"success\":\"yes\","+"\"data\":"+g1+"}";
			 
			 //  data=g1;
			/* try{
				// data= "{\"success\":\"yes\","+ "\"data\":"+g1+"}";
				   data ="{\"success\":\"yes\","+"\"data\":"+g1;
				 // data=g.toJson(gnfb);
				 
			 }catch(Exception e){
					data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
				}*/
			 
			System.out.println("sai kumar chary");
			
			//System.out.println("gngngngngnngngng json data in GetNotification Details Action--->"+data);
			//set response content type
			//res.setContentType("application/json");
			
			//System.out.println("Now json data--->"+data);
			
			//res.getWriter().write(data);
			//res.getWriter().print(data);
			//res.getWriter().println(data);
			//res.getWriter().print(g1);
			//pw.println(data);
			
		}else{
			
			rd=req.getRequestDispatcher("/Login.jsp");
			rd.include(req, res);
			
		}
	}
	private String getNotificationDetailsJson(Integer userId, Integer productId, Integer requestStatus) {
		
		 String data=null;
		 ProductExchangeDeligates ped=null;
		 List<GetNotificationDetailsFormBean> gnfb=null;
		 Gson g1=null;
		 JSONObject json=null;
		 
		 //create object
		 ped = new ProductExchangeDeligates();
		 
		 try{
			 gnfb=ped.getRequestNotificationDetails(userId, productId, requestStatus);
			 
			 for(GetNotificationDetailsFormBean g:gnfb){
				 System.out.println(g.getProdReqId()+"---"+g.getReqProdName()+"<-------Details of form bean");
			 }
			 
		 }catch(Exception e){
			 System.out.println("Problem here is-->"+e.getMessage());
			 e.printStackTrace();
		 }
		 
		 g1=new Gson();
		 json=new JSONObject();
		
		 try{
			 data= "{\"success\":\"yes\","+ "\"data\":"+g1.toJson(gnfb)+"}";
			 // data=g.toJson(gnfb);
			 
		 }catch(Exception e){
				data = "{\"success\":\"no\","+ "\"data\":\"PLEASE TRY LATER\"}";
			}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}