package com.nacre.productexchange.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nacre.productexchange.constants.IntegerContants;
import com.nacre.productexchange.deligate.ProductManagementDeligates;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.formbean.AddProductFormBean;

@MultipartConfig
@WebServlet("/add")
public class AddProductServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AddProductServlet(){
		super();
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("\n <------------------------------->");
		System.out.println("Now control is in AddProductServlet class");
		System.out.println("Clicked--->"+req.getParameter("s1")+"<-----"+"Button");
		
		String paramvalue=null;
		String productName=null,productPrice=null,productDesc=null,purchaseDate=null,productPhotoPath=null;
		ProductDto product=null;
		HttpSession session=null;
		Integer userid=0;
		AddProductFormBean p=null;
		ProductManagementDeligates cfd=null;
		InputStream inputstream=null;
		Part part=null;
		int addproductstatus=0;
		String message=null;
		Date sqldate = null;
		PrintWriter pw=null;
		
		paramvalue=req.getParameter("s1");
		
		if(paramvalue.equals("UPLOAD PRODUCTS")){
			try{
				productName=req.getParameter("productName");
				productPrice=req.getParameter("productPrice");
				productDesc=req.getParameter("productDescription");
				purchaseDate=req.getParameter("purchaseDate");
				
				//getting productImage
				part = req.getPart("productImage");
				
				System.out.println(productName+"---"+productPrice+"---"+productDesc+"---"+purchaseDate+"---");
				
				//create productdto object
				product = new ProductDto();
				session = req.getSession();
				
				userid=(Integer) session.getAttribute("userid");
				System.out.println("user id is--->"+userid);
				
				System.out.println("Part of the image is---->"+part);
				
				if(part!=null){
					inputstream = part.getInputStream();
					System.out.println(inputstream);	
				}
				
				System.out.println("Input Stream---->"+inputstream);
				
				p = new AddProductFormBean();
				p.setProduct_name(productName);
				p.setProduct_price(productPrice);
				p.setProduct_description(productDesc);
				
				//Convert the given date to sql date
				sqldate = Date.valueOf(purchaseDate);
				
				p.setPurchased_Date(sqldate);
				p.setProduct_Avalible_Status(IntegerContants.product_availibility_status_id);
				p.setProduct_Image(inputstream);
								
				//creating object 
				cfd = new ProductManagementDeligates();
				addproductstatus = cfd.addProduct(p, userid);
				
				if(addproductstatus==1)
				{
					message = "Product Added Succesfully.... ";
					System.out.println(message);
					session.setAttribute("message", message);
					req.getRequestDispatcher("/home.jsp").forward(req, res); 
				}
				else{
					 message = "Product Not Added!!!!! ......Please Try Again";
					 System.out.println(message);
					 session.setAttribute("message",message);
					 req.getRequestDispatcher("/addProduct.jsp").forward(req, res);	 
				}
			}
			catch(Exception e){
				System.out.println("Error is------------->"+e.getMessage());
			}
		}	
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}
