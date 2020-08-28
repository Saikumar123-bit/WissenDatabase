package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nacre.productexchange.deligate.AuthenticationDeligate;
import com.nacre.productexchange.dto.UserStateDto;
import com.nacre.productexchange.exception.DbException;

@WebServlet("/state")
public class GetStateAction extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is in GetStateAction class");
		String s = getAllStatesJson(req,res);
		res.setContentType("application/json");
		res.getWriter().append(s);
		
	}
	private String getAllStatesJson(HttpServletRequest req,HttpServletResponse res) 
	{
		System.out.println("Now conrol is in getAllStatesJson() method");
		
		AuthenticationDeligate authdelimpl=null;
		List<UserStateDto> regstate=null;
		
		//create gson class object
		Gson gson = new Gson();
		String data=" ";
		
		try{
			authdelimpl = new AuthenticationDeligate();
			regstate = authdelimpl.getStates(req.getParameter("cid"));
			data = "{\"success\":\"yes\","+"\"data\":"+gson.toJson(regstate)+"}";
		}
		catch(DbException | SQLException | IOException e)
		{
			data = "{\"success\":\"no\","+"\"data\":\"PLEASE TRY LATER\"}";
			System.out.println("Error in getting the data--->"+e.getMessage());
		}
		return data;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}
