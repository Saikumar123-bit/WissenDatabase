package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.GetProductFormBean;

@WebServlet("/product")
public class GetProductBasedOnUserIdAction extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("<<<<<Now Control is in GetProductBasedOnUserIdAction class");
		PrintWriter pw=null;
		List<GetProductFormBean> arraylist=null;
		UserDetailsDto userdto=null;
		ProductDto productdto=null;
		RequestDispatcher rd=null;
		ServletContext sc1=null;
		ProductManagementDeligates productdeligate=null;
		HttpSession httpsession=null;
		String email=null;int userid=0;
		//Gson g=null;
		String data=null;
		res.setContentType("application/json");
		
		pw = res.getWriter();
		httpsession=req.getSession();
		email=(String) httpsession.getAttribute("user");
		userid=(int)httpsession.getAttribute("userid");
		
		
		System.out.println("Email--->"+email+"        "+"userid---->"+userid);
		
		//create userdetailsdto object
		userdto = new UserDetailsDto();
		userdto.setEmail(email);
		userdto.setUserid(userid);
		
		//create productdto and setting the values
		productdto = new ProductDto();
		productdto.setOwnerid(userdto);
		
		productdeligate = new ProductManagementDeligates();
		sc1=getServletContext();
		
		try
		{
			arraylist = productdeligate.viewUserProductsDeligates(userid);
			
			req.setAttribute("myproductlist", arraylist);
			System.out.println("arralist data is converting into Gson Data");
			
			//create Gson object
			// g = new Gson();
			//data = g.toJson(arraylist);
			//System.out.println(data);
			
			String g=null;
			g= new Gson().toJson(arraylist);
			
			/*data = g.toJson();*/
			res.getWriter().write(g);
			//System.out.println("Json data---->"+g);
			
		}
		catch(Exception e){
			System.out.println("Error is--->"+e.getMessage());
			/*rd = req.getRequestDispatcher("/error.jsp");
			rd.forward(req, res);*/
		}
	}	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}