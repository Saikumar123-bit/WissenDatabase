package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nacre.productexchange.deligate.ProductExchangeDeligates;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.ProductRequestFormBean;

@WebServlet("/ProductRequestAction")
public class ProductRequestAction extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		
		HttpSession session=null;
		String reqProductId=null,exchangeProductId=null,msg=null;
		ProductRequestFormBean prfb=null;
		ProductExchangeDeligates ped=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		int result=0,reqPrice=0,excPrice=0;
		
		System.out.println("\n\n\t------------Now control in ProductRequestAction");
		
		//create session object
		session=req.getSession(false);
		
		//get values from ExchangeProduct.jsp
		reqProductId=req.getParameter("rpid");
		exchangeProductId=req.getParameter("eId");
		reqPrice=Integer.parseInt(req.getParameter("reqprice"));
		excPrice=Integer.parseInt(req.getParameter("excprice"));
		
		System.out.println("reqproductId--->"+reqProductId+"\t ExchangeproductId--->"+exchangeProductId);
		System.out.println("requested price---"+reqPrice+"------\t Exchange price-----"+excPrice);
		
		if(reqProductId.equals(exchangeProductId)){
			msg="You Cannot Exchange Your own Product.. Select Another Product";
			req.setAttribute("msg", msg);
			getServletContext().getRequestDispatcher("/homeLogin.jsp").forward(req, res);
		}else if(reqPrice>=excPrice){
			msg="Exchange Not Allowed!!!!!   because Your Product price is less than Requested Product.....Inorder to Exchange Send Your requsted balance with product";
			req.setAttribute("msg", msg);
			getServletContext().getRequestDispatcher("/SendingMoney.jsp").forward(req, res);
		}else {
			
			//create productrequestformean
			prfb=new ProductRequestFormBean();
			prfb.setExchangeProductId(Integer.parseInt(exchangeProductId));
			prfb.setRequestProductId(Integer.parseInt(reqProductId));
			
			ped = new ProductExchangeDeligates();
			
			//get printwriter object
			pw=res.getWriter();
			
			//set responsecontent type
			res.setContentType("text/html");
			
			try{
				result=ped.requestExchange(prfb);
				System.out.println("Control reached to ProductRequestAction class");
				if(result!=0){
					msg="Your Requst has been sent to the user ";
					req.setAttribute("msg", msg);
					rd=req.getRequestDispatcher("/homeLogin.jsp");
					rd.include(req, res);
				}else {
					msg="Please try After Some Time";
					req.setAttribute("msg", msg);
					rd=req.getRequestDispatcher("/homeLogin.jsp");
					rd.include(req, res);
				}
			}catch(SQLException e){
				msg="Please try again...Sorry for Inconvienece";
				req.setAttribute("msg", msg);
				System.out.println(msg+"--->"+e.getMessage());
				rd=req.getRequestDispatcher("/homeLogin.jsp");
				rd.forward(req, res);
				e.printStackTrace();
			}catch(DbException e){
				msg="Please try Again Some Technical issue Occur";
				req.setAttribute("msg", msg);
				System.out.println(msg+"---->"+e.getMessage());
				req.getRequestDispatcher("/homeLogin.jsp").forward(req, res);
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}