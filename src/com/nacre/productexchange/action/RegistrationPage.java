package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nacre.productexchange.constants.StringConstants;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.dto.AddressDto;
import com.nacre.productexchange.formbean.RegisterFormBean;

@WebServlet("/registration")
public class RegistrationPage extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is in RegistrationPage class");
		boolean flag=false;
		String rname=null,remail=null,rpassword=null,rmobileno=null,rpincode=null,rcity=null,rstate=null,rcountry=null;
		String message=null;
		String raddress=null;
		AddressDto raddress1=null;
		RegisterFormBean rfb=null;
		
		res.setContentType("text/html");
				
		rname=req.getParameter("name");
		remail=req.getParameter("email");
		rpassword=req.getParameter("password");
		rmobileno=req.getParameter("mobileno");
		raddress=req.getParameter("address");
		rpincode=req.getParameter("pincode");
		rcity=req.getParameter("city");
		rstate=req.getParameter("state");
		rcountry=req.getParameter("country");
		System.out.println("Name-->"+rname+" "+"Email--->"+remail+" "+"Password--->"+rpassword+" "+"MobileNo--->"+rmobileno+" "+"Address--->"+raddress+" "+"PinCode-->"+rpincode+" "+"Country--->"+rcountry+" "+"State--->"+rstate+" "+""+rcity);
		
		rfb = new RegisterFormBean();
		
		rfb.setName(rname);
		rfb.setEmail(remail);
		rfb.setPassword(rpassword);
		rfb.setMobileno(rmobileno);
		
		raddress1 = new AddressDto();
		//System.out.println(raddress);
		//raddress1.setAddress(raddress);
		rfb.setAddress(raddress);
		
		raddress1.setPin_code(rpincode);
		rfb.setPinCode(rpincode);
		rfb.setCity(rcity);
		rfb.setState(rstate);
		rfb.setCountry(rcountry);
		
		//rfb.setAddress_id(radrs);
		AuthenticationDeligate rd = new AuthenticationDeligate();
		try {
			flag=rd.register(rfb);
			
			if(flag)
			{
				message = "Registration Successful Please login to Access Your Account";
				req.setAttribute("message", message);
				req.getRequestDispatcher("/Login.jsp").forward(req, res);
			}
			else{
				System.out.println("Y fail mail means flag is ---->"+flag);
				req.setAttribute("message", StringConstants.REGISTER_FAILURE_MSG);
				req.getRequestDispatcher("/Register.jsp").forward(req, res);
			}
		} 
		catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message="Technical Problem!!! please try Again";
			req.setAttribute("message", message);
			System.out.println("Error in loading jdbc driver class--->"+e.getMessage());
			res.sendRedirect("/Register.jsp");
		}
		catch(SQLException e){
			System.out.println("\n \n Error with Input Values---->"+e.getMessage());
			message="Technical Problem----Please try Again";
			req.setAttribute("message", message);
			res.sendRedirect("/Register.jsp");			
		}
		catch(Exception e){
			message="Technical Problem &&&& please try again";
			req.setAttribute("message", message);
			System.out.println("Errror is---->"+e.getMessage());
			res.sendRedirect("/Register.jsp");
		}
	}	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}