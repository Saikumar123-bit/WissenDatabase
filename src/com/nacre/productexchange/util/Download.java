package com.nacre.productexchange.util;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.formbean.ProductRequestNotificationFormBean;

public class Download {
	public static boolean download(ProductRequestNotificationFormBean excInfo,OutputStream out)throws IOException{
		
		Document document=null;
		Paragraph p=null;
		Paragraph p1=null;
		ProductDto excproduct=null;
		ProductDto reqproduct=null;
		Blob image=null;
		Blob image2=null;
		
		//create a document object
		document=new Document();
		
		try{
			PdfWriter.getInstance(document, out);
			
			document.open();
			document.add(new Paragraph("Complete Products Exchanged Information"));
			
			//create paragraph object
			p=new Paragraph();
			p1=new Paragraph();
			
			p.setAlignment(Element.ALIGN_CENTER);
			p1.setAlignment(Element.ALIGN_CENTER);
			
			//create productdto object
			excproduct =excInfo.getExchangeProduct();
			reqproduct=excInfo.getRequestProduct();
			System.out.println(excproduct.getProduct_name()+"---product name in download page");
			
			image=excproduct.getProductImage();
			image2=reqproduct.getProductImage();
			InputStream is=null;
			
			try{
				is=image.getBinaryStream();
				
				//Convert image into Base64 format
				byte[] bytes= image.getBytes(1, (int) image.length());
				
				Image image1=Image.getInstance(bytes);
				image1.setAlignment(Image.MIDDLE);
				image1.scaleAbsolute(180,180);
				
				p.add("Exchange product Details----"+"\n");
				
				p.add(image1);

				//exchange produc details
				p.add("ProductName----------->"+excproduct.getProduct_name()+"\n");
				p.add("Product Price--------->"+String.valueOf(excproduct.getProduct_price())+"\n");
				p.add("Product Description--->"+excproduct.getProduct_description()+"\n");
				p.add("User Name------------->"+excproduct.getOwner_id().getName()+"\n");
				p.add("Mobile Number--------->"+excproduct.getOwner_id().getMobileno()+"\n");
				
				p1.add("<--------------------------------------------------------------------->\n\n\n\n");
				p1.add("Request Product Details----"+"\n");
				
				byte[] bytes1=( image2.getBytes(1, (int) image2.length()));
				Image image3=Image.getInstance(bytes1);
				
				image3.setAlignment(Image.MIDDLE);
				image3.scaleAbsolute(180,180);
				
				p1.add(image3);
			
				
				//requested Product details
				p1.add("ProductName----------->"+reqproduct.getProduct_name()+"\n");
				p1.add("Product Price--------->"+String.valueOf(reqproduct.getProduct_price())+"\n");
				p1.add("Product Description--->"+reqproduct.getProduct_description()+"\n");
				p1.add("User Name------------->"+reqproduct.getOwner_id().getName()+"\n");
				p1.add("Mobile Number--------->"+reqproduct.getOwner_id().getMobileno()+"\n");
				
			}catch(Exception e){
				System.out.println("Problem in pdf file---"+e.getMessage());
				e.printStackTrace();
			}			
			
			//adding document info to file
			document.add(p);
			document.add(p1);
			
			//close the document file
			document.close();
			
		}catch(DocumentException e){
			System.out.println("Downloading document problem");
			e.printStackTrace();
		}
		
		return true;
		
	}
}
