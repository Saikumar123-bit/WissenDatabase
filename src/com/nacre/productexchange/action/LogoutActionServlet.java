package com.nacre.productexchange.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.s;

@WebServlet("/logout")
public class LogoutActionServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <-------------------------------------->");
		System.out.println("Now Control is in LogoutActionServlet class");
		HttpSession session = null;
		
		//create session object
		session=req.getSession();
		
		if(session!=null){
				//System.out.println("User Name is----->"+session.getAttribute("user"));
				
			//	session.removeAttribute("user");
				session.invalidate();
					System.out.println("User is ----->"+session.getAttribute("user"));
					
		}
		System.out.println("<--- Now control is moving to Home Page --->");
		res.sendRedirect("home.jsp");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}
