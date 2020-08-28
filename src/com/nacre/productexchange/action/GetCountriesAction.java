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
import com.nacre.productexchange.dto.UserCountryDto;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/country")
public class GetCountriesAction extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("Now Control is in GetCountriesAction class");
		String s=null;
		
		s = getAllCountriesJson();
		res.setContentType("application/json");
		res.getWriter().append(s);
		
	}
	private String getAllCountriesJson()
	{
		AuthenticationDeligate authdelimpl = new AuthenticationDeligate();
		List<UserCountryDto> regcountry=null;
		
		System.out.println("Now the countrol is in getAllCountriesJson()");
		
		//create Gson object
		Gson gson = new Gson();
		String data="";
		
		try{
			regcountry=authdelimpl.getCountries();
			data = "{\"success\":\"yes\","+"\"data\":"+gson.toJson(regcountry)+"}";		
		}catch(DbException | SQLException e)
		{
			data="{\"success\":\"no\","+"\"data\":\"PLEASE TRY LATER\"}";
			System.out.println("getting error in data--->"+e.getMessage());
		}
		return data;
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}
