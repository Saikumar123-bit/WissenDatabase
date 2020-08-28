package com.nacre.productexchange.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.GetProductFormBean;

@WebServlet("/geteligible")
public class GetEligibleProductsAction extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
	
		List<GetProductFormBean> arrayList=null;
		ProductManagementDeligates product = null;
		UserDetailsDto userdto=null;
		ProductDto productdto=null;
		String name=null,email=null;;
		
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is GetEligibleProductsAction class ");
		
		product = new ProductManagementDeligates();
		userdto = new UserDetailsDto();
		productdto = new ProductDto();
		
		//set the value
		userdto.setEmail(email);
		productdto.setOwnerid(userdto);
		
		res.setContentType("application/json");
		try
		{	
			arrayList = product.viewEligibleProductsDeligates();
			String g=null;
			
			//seeting values to the session attribute
			req.setAttribute("user", name);
			
			//keep listdto int request object
			req.setAttribute("RegisterProduct", arrayList);
			
			g= new Gson().toJson(arrayList);
			/*data = g.toJson();*/
			res.getWriter().write(g);
			//System.out.println("Json data"+g);	
		}
		catch(DbException e)
		{
			System.out.println("Error in--->"+e.getMessage());
			res.getWriter().write("[]");
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}