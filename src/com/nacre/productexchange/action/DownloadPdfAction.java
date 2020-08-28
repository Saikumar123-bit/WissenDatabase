package com.nacre.productexchange.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nacre.productexchange.deligate.ReportsDeligate;

@WebServlet("/DownloadPdfAction")
public class DownloadPdfAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		System.out.println("Now control in DownloadPdf Action");
		ReportsDeligate rd=null;
		Integer ProdReqId=0;
		
		//create object
		rd=new ReportsDeligate();
		
		try{
			res.setContentType("application/pdf");
			res.setContentType("application/force-download");			
			res.setHeader("Content-Disposition","attachment; filename=mypdf.pdf");
			
			ProdReqId=(Integer.parseInt(req.getParameter("prodReqId")));
			System.out.println("Product req id---"+ProdReqId);
			
			//send control
			rd.getExchangeDeligates(ProdReqId, res);
			
		}catch(SQLException e){
			System.out.println("Problem in sql---"+e.getMessage());
			e.printStackTrace();
		}catch(Exception e1){
			System.out.println("Problem"+e1.getMessage());
			e1.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}

}
