package com.nacre.productexchange.action;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/GetExchangedProducts")
public class GetExchangedProducts extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		System.out.println("Now control came from getExchangeproduct.jsp page");
		ProductExchangeDeligates ped=null;
		HttpSession session=null;
		UserDetailsDto user=null;
		List<GetNotificationDetailsFormBean> userNotificationList=null;
		Integer userId=0,p_id=0;
		String g1=null,data=null;
		
		//create session objet
		session=req.getSession(false);
		
		if(session.getAttribute("userInfo")!=null){
			
			//create userdetailsdto objet
			user=new UserDetailsDto();
			user=(UserDetailsDto)session.getAttribute("userInfo");
			
			//set response content type application/json
			res.setContentType("application/json");
			ped=new ProductExchangeDeligates();
			
			//get userId from session 
			userId=(Integer) session.getAttribute("userid");
			
			//call exchangedeligates
			try {
				userNotificationList=ped.getExchangeProducts(userId);
				System.out.println("now Control reached to getExchangeProducts;;;;;;;;;;");
				for(GetNotificationDetailsFormBean pdt:userNotificationList){
					System.out.println("Details of Requset Product details-----");
					System.out.println("get produ requset id---"+pdt.getProdreqid());
					
					System.out.println(pdt.getReqProdId()+"-------"+pdt.getReqProdName()+"-----"+pdt.getReqProdOwnerId());
					System.out.println("Details of Exchnage Product Details----");
					System.out.println(pdt.getExcProdId()+"-----"+pdt.getExcProdName()+"----"+pdt.getExcProdOwnerId());
					session.setAttribute("P_R_Id", pdt.getProdReqId());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Problem getExchange products------"+e.getMessage());
				e.printStackTrace();
			}
			g1=new Gson().toJson(userNotificationList);
			 data ="{\"success\":\"yes\","+"\"data\":"+g1+"}";
			 //System.out.println("getexchange json data------"+g1);
			 
			 //sending to webpage
			 res.getWriter().println(g1);
		}
		
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
}
