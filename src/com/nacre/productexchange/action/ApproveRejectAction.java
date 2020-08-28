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
import com.nacre.productexchange.formbean.ApproveRejectFormBean;

@WebServlet("/ApproveRejectAction")
public class ApproveRejectAction extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		System.out.println("\n--Now control in ApproveRejectAction servlet class");
		ApproveRejectprocess(req,res);
	}
	private void ApproveRejectprocess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=null;
		String nextPage=null;
		ApproveRejectFormBean arfb=null;
		int statusid=0;
		String btnValue=null,prodReqId=null,rejectedReason=null,reqProdId=null,excProdId=null;//dateOfApproval=null;
		boolean flag=false;
		ProductExchangeDeligates pedi=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		
		//create session object
		session=req.getSession(false);
		
		if(session.getAttribute("userInfo")!=null){
			
			btnValue=req.getParameter("reqReplyBtn");
			prodReqId=req.getParameter("prodReqId");
			rejectedReason=req.getParameter("rejectedReason");
			reqProdId=req.getParameter("reqProdId");
			excProdId=req.getParameter("excProdId");
			//dateOfApproval=req.getParameter("dateOfApproval");
			statusid=Integer.parseInt(req.getParameter("statusId"));
			
			System.out.println(btnValue+"---"+prodReqId+"---"+rejectedReason+"---"+reqProdId+"----"+excProdId);
			System.out.println("--status id---"+statusid);
			
			arfb = new ApproveRejectFormBean();
			arfb.setProdreqId(prodReqId);
			arfb.setReqProdId(reqProdId);
			arfb.setExcProdId(excProdId);
			//arfb.setDateOfApproval(dateOfApproval);
			arfb.setStatusId(statusid);
			
			if(btnValue.equals("Approve")){
				arfb.setRejectedReason("NA");
				pedi = new ProductExchangeDeligates();
				try{
					flag=pedi.reqApproved(arfb);	
				}catch(SQLException | IOException e){
					System.out.println("Problem in sql and IOexception reqApproved-->"+e.getMessage());
				}catch(Exception e){
					System.out.println("Tecnical problem reqqpproved--> "+e.getMessage());
					e.printStackTrace();
				}pw=res.getWriter();
				if(flag){
					pw.println("<p style='color:green'>Request is sent to the User</p>");
					nextPage="homeLogin.jsp";
				}else{
					pw.println("<p style='color:green'>Internal Problem !!! Please try again</p>");
					nextPage="homeLogin.jsp";
				}
			}else{
				arfb.setRejectedReason(rejectedReason);
				pedi = new ProductExchangeDeligates();
				flag=false;
				try{
					flag=pedi.reqRejected(arfb);
				}catch(SQLException | IOException e){
					System.out.println("Problem in sql and IOexception reqrejected-->"+e.getMessage());
					e.printStackTrace();
				}catch(Exception e){
					System.out.println(" reqrejetd Tecnical problem-->"+e.getMessage());
					e.printStackTrace();
				}pw=res.getWriter();
				if(flag){
					pw.println("<p style='color:red'>Requested Product is Rejected</p>");
					nextPage="homeLogin.jsp";
				}else{
					pw.println("<p style='color:red'>Internal Problem.... </p>");
					nextPage="homeLogin.jsp";
				}
			}
		}else{
			nextPage="Login.jsp";
		}
		rd=req.getRequestDispatcher(nextPage);
		rd.forward(req, res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}