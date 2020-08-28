package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nacre.productexchange.constants.StringConstants;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.ResetFormBean;

@WebServlet("/changepassword")
public class ChangePasswordServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n -------------------------");
		System.out.println("now control is in ChangePasswordServlet class");
		
		String email=null;
		String oldpwd=null;
		String newpwd=null;
		String renewpwd=null;
		HttpSession session=null;
		ResetFormBean rsb=null;
		AuthenticationDeligate deligates=null;
		int result=0;
		
		session = req.getSession(false);
		email= (String) session.getAttribute("Email");
		oldpwd=(String) session.getAttribute("Password");
		newpwd=req.getParameter("newpwd");
		renewpwd=req.getParameter("renewpwd");
		
		System.out.println(email+"-----"+oldpwd+"----"+newpwd+"-----"+renewpwd);
		
		if(newpwd=="" || renewpwd=="" || newpwd.equals(null) || renewpwd.equals(null)){
			
			req.setAttribute("msg", StringConstants.PASSWORD_VALIDATE_MSG);
			getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(req, res);
			
		}
		else if(!newpwd.equals(renewpwd)){
			req.setAttribute("msg", StringConstants.PASSWORD_FAILURE_MSG);
			getServletContext().getRequestDispatcher("/ChangePassord.jsp").forward(req, res);
		}
		else{
			
			rsb = new ResetFormBean();
			rsb.setEmail(email);
			rsb.setOldpassword(oldpwd);
			rsb.setNewpassword(newpwd);
			rsb.setRenewPassword(renewpwd);
			
			deligates = new AuthenticationDeligate();
			
			try {
				result=deligates.changePassword(rsb);						
			} catch (SQLException | DbException e) {
				req.setAttribute("msg",StringConstants.DB_PROBLEM);
				getServletContext().getRequestDispatcher("ChangePassword.jsp").forward(req, res);
			}
		}
		if (result!=0) {
			String msg="Password Updated Plz Login To Check";
			req.setAttribute("msg",msg);
			getServletContext().getRequestDispatcher("home.jsp").forward(req, res);	
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}	
}