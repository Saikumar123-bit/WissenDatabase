package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/removeproduct")
public class RemovePropductServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("\n \n \t <---> Now control is in DisableProductServlet class <--->");
		Integer productId=0;
		String productI=null;
		Integer ownerid=0;
		HttpSession session=null;
		int result=0;
		String msg=null;
		ProductManagementDeligates pmdi=null;
		
		//read data from the page
		productId=Integer.parseInt(req.getParameter("prodId"));
		//productI = req.getParameter("prodId");
		
		session=req.getSession(false);
		
		//productId=(Integer)session.getAttribute("productId"); //--> not needed beacuse the value coming from last image bcz we are using session
		ownerid= (Integer)session.getAttribute("userid");
		//ownerid=Integer.parseInt(userid);
		
		System.out.println("ProductId--->"+productId+"<----->"+"OwnerId--->"+ownerid);
		
		pmdi = new ProductManagementDeligates();
		
		try {
			result=pmdi.removeUserProducts(productId, ownerid);			
		} catch (SQLException | DbException e) 
		{
			msg = "Technical problem!!! Please try again";
			req.setAttribute("msg",msg);
			res.sendRedirect("ViewProduct.jsp");
			System.out.println("Exception at--->"+e.getMessage());
		} 
		System.out.println("Finally reached to RemoteProductServlet And The Result is--->"+result);
		
		if(result==0){
			msg = "Product Not Removed ";
			req.setAttribute("msg",msg);
			res.sendRedirect("ViewProduct.jsp");
			//getServletContext().getRequestDispatcher("ViewProduct.jsp");
		}
		else{
			msg = "Product Removed Successfully";
			req.setAttribute("msg",msg);
			System.out.println(msg);
			//getServletContext().getRequestDispatcher("ViewProduct.jsp");
			res.sendRedirect("ViewProduct.jsp");
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}