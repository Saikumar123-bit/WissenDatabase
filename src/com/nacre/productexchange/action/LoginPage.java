package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.dto.UserDetailsDto;
import com.nacre.productexchange.formbean.LoginFormBean;

@WebServlet("/login")
public class LoginPage extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		UserDetailsDto user=null;
		String lemail=null,password=null;
		String status=null,msg=null;
		LoginFormBean lfb=null;
		AuthenticationDeligate ld=null;
		HttpSession session=null;
		
		//set responsecontenttpe
		res.setContentType("text/html");
		
		status = req.getParameter("login");
		System.out.println("Clicked--->"+status+"--->Button");
		
		lemail=req.getParameter("email");
		password=req.getParameter("password");
		System.out.println("<--->"+lemail+"<--->"+password);
		
		lfb = new LoginFormBean();
		lfb.setEmail(lemail);
		lfb.setPassword(password);
		
		ld = new AuthenticationDeligate();
		msg = "UserName or password is incorrect!!! Please try again";
		try {
			user=ld.login(lfb);
			session = req.getSession(true);
			if(user!=null){
				session.setAttribute("userInfo", user);
				session.setAttribute("userid", user.getUserid());
				session.setAttribute("user", user.getName());
				session.setAttribute("Email", user.getEmail());
				session.setAttribute("Password", user.getPassword());
				session.setAttribute("Mobile_No", user.getMobileno());
				session.setAttribute("Ac_Balance", user.getAc_Balanace());
				
				//control sending to login suucessful page
				res.sendRedirect("about.jsp");
			}else{
				session.setAttribute("msg", msg);
				res.sendRedirect("Login.jsp");
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			msg="Technical problem!!! please try again";
			req.setAttribute("msg", msg);
			res.sendRedirect("Login.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			msg="Technical problem------>please try again";
			req.setAttribute("msg", msg);
			res.sendRedirect("Login.jsp");
		}
		catch(Exception e)
		{
			msg="Technical problems &&& please try again";
			req.setAttribute("msg", msg);
			res.sendRedirect("Login.jsp");
		}
	}	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}