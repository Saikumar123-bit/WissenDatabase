package com.nacre.productexchange.action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductRejectAction")
public class ProductRejectAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		System.out.println("-34-3-4-3---Now control in ProductRejectAction-----");
		res.getWriter().append("Served at:-").append(req.getContextPath());
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}