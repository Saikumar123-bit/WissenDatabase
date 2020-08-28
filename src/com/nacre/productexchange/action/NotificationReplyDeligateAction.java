package com.nacre.productexchange.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NotificationReplyDeligateAction")
public class NotificationReplyDeligateAction extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		notificationReplyDeligateProcess(req,res);
	}
	private void notificationReplyDeligateProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("---\n Now Control in NotificationReplyDeligateAction class----");
		HttpSession session=null;
		RequestDispatcher rd=null;
		String nextpage="";
		String reqStatusId=null;
		
		//create session object
		session=req.getSession(false);
		
		if(session.getAttribute("userInfo")!=null){
			
			reqStatusId=req.getParameter("reqStatus");
			System.out.println("reqStatusId-->"+reqStatusId);
			
			if(reqStatusId.equals("6")){
				nextpage="NotificationRequestReply.jsp";
			}else if(reqStatusId.equals("5")){
				nextpage="NotificationApproveReply.jsp";
			}else if(reqStatusId.equals("4")){
				nextpage="NotificationRejectReply.jsp";
			}
			rd=req.getRequestDispatcher(nextpage);
			rd.forward(req, res);
		}
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}