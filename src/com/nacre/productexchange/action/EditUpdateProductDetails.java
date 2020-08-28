package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.*;

import com.nacre.productexchange.constants.IntegerContants;
import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;

@WebServlet("/UpdateProduct")
@MultipartConfig
public class EditUpdateProductDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EditUpdateProductDetails() {
		super();
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		System.out.println("\n \t ----Now control is in EditUpdateProductdetails----");
		
		String status=null, prodName=null,prodPrice=null,prodDesc=null,prodDate=null;
		String msg=null;
		Integer productId=0;
		ProductDto product=null;
		HttpSession session=null;
		PrintWriter pw=null;
		Integer userId=0;
		InputStream is=null;Part part=null;
		AddProductFormBean apfb=null;
		ProductManagementDeligates pmdi=null;
		int addproductstatus=0;
		//Date sqldate=null;
		
		//get printwriter object
		pw=res.getWriter();
		
		//set responsecontent type
		res.setContentType("text/html");
		
		productId=Integer.parseInt(req.getParameter("productId"));
		prodName=req.getParameter("productName");
		prodPrice=req.getParameter("producPrice");
		prodDesc=req.getParameter("productDescription");
		prodDate=req.getParameter("purchageDate");
		
		System.out.println("############"+productId+"<---->"+prodName+"<-->"+prodPrice+"<-->"+prodDesc+"<-->"+prodDate+"<-->"+userId+"<-->");
		
		//create productDto object
		product = new ProductDto();
		session=req.getSession(false);
				
		//get userid from session
		userId=(Integer)session.getAttribute("userid");   	
		
		//get image
		part=req.getPart("productImage");
				
		if(part!=null){
			is=part.getInputStream();
		}				
				
		//create addproductformbean class object
		apfb=new AddProductFormBean();
		
		apfb.setProductId(productId);
		apfb.setProduct_name(prodName);
		apfb.setProduct_price(prodPrice);
		apfb.setProduct_description(prodDesc);
				
		//convert the given date to sql date
		//apfb.setPurchased_Date(Date.valueOf(prodDate));

		System.out.println("Product Availible Status--->"+IntegerContants.product_availibility_status_id);
		apfb.setProduct_Avalible_Status(IntegerContants.product_availibility_status_id);
		
		apfb.setProduct_Image(is);		
		System.out.println("############"+prodName+"<-->"+prodPrice+"<-->"+prodDesc+"<-->"+Date.valueOf(prodDate)+"<-->"+userId+"<-->");
		
		//create productmanagementdeliagtes object
		pmdi=new ProductManagementDeligates();
		
		try {
		addproductstatus=pmdi.updateProduct(apfb, userId);
		
		}catch(SQLException | DbException e1){
			msg="Internal Problem Please try Again";
			req.setAttribute("msg", msg);
			System.out.println("Problem is----->"+e1.getMessage());
			req.getRequestDispatcher("/ViewProduct.jsp").forward(req, res);;
		}catch(Exception e){
			msg="Product Not updated plz Try again &&&&"+e.getMessage();
			req.setAttribute("msg", msg);
			System.out.println(msg);
			req.getRequestDispatcher("/ViewProduct.jsp").forward(req, res);
		}
		if(addproductstatus==1){
			msg="Product Updated Successfully..Click vew to check Products";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/ViewProduct.jsp").forward(req, res);
		}else {
			msg="Product Not Updated..Check the file details and try again";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/ViewProduct.jsp").forward(req, res);
		}
	}			
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		doGet(req,res);
	}
}