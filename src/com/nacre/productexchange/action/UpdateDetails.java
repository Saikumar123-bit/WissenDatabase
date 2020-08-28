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

import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.formbean.LoginFormBean;

@WebServlet("/updateDetails")
public class UpdateDetails extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		
		System.out.println("now control is in updateDetails Servlet Class");
		String name=null,email=null,mobileno=null, msg=null;
		int userid=0;
		PrintWriter pw=null;
		AuthenticationDeligate aid=null;
		LoginFormBean lfb=null;
		int update=0;
		HttpSession  session=null;
		
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
		session=req.getSession(false);
		userid=(int)session.getAttribute("userid");
		
		//get details ffrom editDetails page
		name=req.getParameter("userName");
		email=req.getParameter("email");
		mobileno=req.getParameter("mobileNo");
		
		aid=new AuthenticationDeligate();
		lfb = new LoginFormBean();
		
		//set details to form bean
		lfb.setUserid(userid);
		lfb.setUsername(name);
		lfb.setEmail(email);
		lfb.setMobileNo(mobileno);
		
		try{
			update=aid.updateDetails(lfb);
			if(update!=0){
				msg="Details updated...Successfully";
				req.setAttribute("msg", msg);
				res.sendRedirect("UpdateDetails.jsp");
			}else {
				msg="Details not updated..!!!";
				req.setAttribute("msg", msg);
				res.sendRedirect("UpdateDetails.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			msg="Technical problem!!! please try again";
			System.out.println("Error at laoding class problem-->"+e.getMessage());
			req.setAttribute("msg", msg);
			res.sendRedirect("UpdateDetails.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg="Technical problem------ please try again";
			req.setAttribute("msg", msg);
			System.out.println("Sql error--->"+e.getMessage());
			res.sendRedirect("UpdateDetails.jsp");
		} catch(Exception e) {
			msg="Technical problems &&& please try again";
			req.setAttribute("msg", msg);
			System.out.println("Other than error---->"+e.getMessage());
			res.sendRedirect("UpdateDetails.jsp");
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
}