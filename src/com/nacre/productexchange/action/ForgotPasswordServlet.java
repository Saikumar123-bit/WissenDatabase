package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;
import java.net.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import com.nacre.productexchange.constants.StringConstants;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is in forgotpasswordservlet class ");
		int result=0,i=0;
		AuthenticationDeligate Deligates=null;
		String email=null,msg=null;
		
		email=req.getParameter("email");
		
		System.out.println(email+"--->Email ");
		
		//cretae object for Authdeligate class object
		Deligates=new AuthenticationDeligate();
		try {
			i=Deligates.checkInternetConnection();
			System.out.println("Noew control reached to servlet class");
			if(i==0){
				try {
					result=Deligates.updatePassword(email);
				} catch (DbException | SQLException e) {
					req.setAttribute("msg",StringConstants.DB_PROBLEM);
					getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(req, res);	
				}
				if (result!=0) {
					msg="Password Updated Plz Login To Check";
					req.setAttribute("msg",msg);
					getServletContext().getRequestDispatcher("/Login.jsp").forward(req, res);	
				}
				else{
					if (result==0) {
						msg="Please Enter Correct Email Address";
						req.setAttribute("msg",msg);
						getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(req, res);	
					}
				}
			}else{
				msg="Please Check Your Internet Connection......";
				req.setAttribute("msg", msg);
				getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(req, res);
			}
		}catch(Exception e1){
			System.out.println("Problem with-->"+e1.getMessage());
			msg=e1.getMessage();
			req.setAttribute("msg", msg);
			getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}
