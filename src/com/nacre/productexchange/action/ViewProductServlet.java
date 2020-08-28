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

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.formbean.GetProductFormBean;

@WebServlet("/view")
public class ViewProductServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n ------------------------------- ");
		System.out.println("Now Control is in ViewProductServlet Class");
		
		//UserDetailsDto user = null;
		String email=null;
		String s=null;
		HttpSession session=null;
			
		//create object
		//user = new UserDetailsDto();
		session=req.getSession(false);
		
		//getting user information
		//user=(UserDetailsDto) session.getAttribute("Email");
		
		email=(String) session.getAttribute("Email");
		System.out.println("Email is ---->"+email);
		
		//set response contentin json format
		
		s = getAllProductsJson(req,res);
		
	}
	private static String getAllProductsJson(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String data = null;
		HttpSession httpsession = null;
		Integer userId = 0;
		String g=null;
		ProductManagementDeligates deligate=null;
		List<GetProductFormBean> userprodlist=null;
		//List<ProductDto> userprodlist=null; //created my line
		
		//get session attribute
		httpsession = req.getSession(false);
		userId = (Integer)httpsession.getAttribute("userid");		
		
		System.out.println("userId-->"+userId);
		
		//g = new Gson();
		
		try{
			 deligate = new ProductManagementDeligates();
			 userprodlist = deligate.viewUserProductsDeligates(userId);
			 System.out.println("Now control is reached to ViewProductServlet");	 
			 httpsession.setAttribute("UserProductList", userprodlist);
			//System.out.println(pdt.getProduct_id()+"--"+pdt.getProduct_name()+"--"+pdt.getOwner_id()+"--"+userId);
			 
			for(GetProductFormBean gpfb:userprodlist) {
				System.out.println(gpfb.getProductId()+"----"+gpfb.getProductname()+"---"+gpfb.getProductprice()+"----"+gpfb.getProductdescription()+"---"+gpfb.getPurchasedDate()+"----"+gpfb.getProductAvalibleStatus());
				
				httpsession.setAttribute("productId", gpfb.getProductId());
				httpsession.setAttribute("productName", gpfb.getProductname());
				httpsession.setAttribute("productPrice", gpfb.getProductprice());
				httpsession.setAttribute("productDescription", gpfb.getProductdescription());
				httpsession.setAttribute("productAvailibleStatus", gpfb.getProductAvalibleStatus());
				httpsession.setAttribute("purchasedDate", gpfb.getPurchasedDate());
				httpsession.setAttribute("productImage", gpfb.getProductImage());
			}
			
			 httpsession.setAttribute("UserId", userId);
			 			
			//set response content type
			res.setContentType("application/json");
			//req.setAttribute("", userprodlist);
			
			g=new Gson().toJson(userprodlist);
			//System.out.println("Converted json data-->"+g);
			data = "{\"success\":\"yes\","+"\"data\":"+g;
			res.getWriter().write(g);			
			
			//res.getWriter().print(g.toJson(userprodlist));
			System.out.println("33333333333333333333333333");
		}catch(Exception e){
			 data = "{\"success\":\"no\","+"\"data\":\"Please Try Later\"}";
			 System.out.println("--------------------------"+e.getMessage());
		}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}