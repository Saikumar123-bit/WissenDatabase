package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nacre.productexchange.deligate.ProductExchangeDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/CheckUserProductValid")
public class CheckUserProductValid extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		
		Integer productId=0,reqProductId=0,pid=0;
		UserDetailsDto ownerId=null;
		PrintWriter pw=null;
		HttpSession session=null;
		ProductDto dto=null;
		ProductExchangeDeligates deligate=null;
		String msg=null;
		
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		session=req.getSession();
		
		//check user product valid
		ownerId=(UserDetailsDto)req.getAttribute("userid");
		reqProductId=Integer.parseInt(req.getParameter("pid"));
		
		dto=new ProductDto();
		dto.setProduct_id(reqProductId);
		dto.setOwnerid(ownerId);
				
		//call deligates method
		deligate = new ProductExchangeDeligates();
		try{
			msg="Select another Product.. This is Your Product";
			req.setAttribute("msg", msg);
			deligate.checkExchangeProduct(dto);
			getServletContext().getRequestDispatcher("/home.jsp").forward(req, res);
		}catch(SQLException e){
			msg="Some Technical Problem occurred.Try Again";
			req.setAttribute("msg", msg);
			System.out.println("Problem here Check out-->"+e.getMessage());
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/home.jsp");
		}catch(Exception e){
			System.out.println("Internal problem occurred---"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}
