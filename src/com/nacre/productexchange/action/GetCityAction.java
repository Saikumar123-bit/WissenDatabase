package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.dto.UserCityDto;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/city")
public class GetCityAction extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is in getCityAction class ");
		String s = getAllCity(req,res);
		res.setContentType("application/json");
		res.getWriter().append(s);
		
	}
	private String getAllCity(HttpServletRequest req, HttpServletResponse res)
	{
		System.out.println("Now control is in getCityAction class on getAllCity() ");
		AuthenticationDeligate authdelimpl=null;
		List<UserCityDto> regcity=null;
		
		//creating gson class object
		Gson gson = new Gson();
		String data=" ";
		try{
			
			authdelimpl = new AuthenticationDeligate();
			regcity = authdelimpl.getCity(req.getParameter("state"));
			data = "{\"success\":\"yes\","+"\"data\":"+gson.toJson(regcity)+"}";
			
		}
		catch(DbException | IOException | NumberFormatException | SQLException e)
		
		{
			e.printStackTrace();
			data = "{\"success\":\"no\","+"\"data\":\"Please Try later\"}";
			System.out.println("Error with--->"+e.getMessage());
		} 				
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}