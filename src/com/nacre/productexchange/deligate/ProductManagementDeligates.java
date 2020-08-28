package com.nacre.productexchange.deligate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.dto.StatusDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;
import com.nacre.productexchange.formbean.GetProductFormBean;
import com.nacre.productexchange.service.ProductManagementServiceImpl;
import com.nacre.productexchange.util.ImageUtility;

public class ProductManagementDeligates
{

	public List<GetProductFormBean> viewEligibleProductsDeligates() throws DbException, IOException
	{
		System.out.println("Now comtrol is in ProductsManagementDeligatesImpl class ");
		
		List<GetProductFormBean> pb = null;
		List<ProductDto> p=null;
		GetProductFormBean getbean=null;
		ProductManagementServiceImpl productmanagementserviceimpl=null;
		
		//create service class object
		productmanagementserviceimpl = new ProductManagementServiceImpl();
		
		//call servvice class object
		p = productmanagementserviceimpl.viewEligibleProductsService();
				
		//arraylist object
		pb = new ArrayList<>();

		//here written the query converting images based on the query....
		
		for(ProductDto tblproduct:p){
			
			String s = ImageUtility.BinaryToString(tblproduct);
			
			//create getproductformbean class object
			getbean = new GetProductFormBean();
			
			getbean.setProductId(tblproduct.getProduct_id());
			getbean.setProductname(tblproduct.getProduct_name());
			getbean.setProductprice(tblproduct.getProduct_price());
			getbean.setProductdescription(tblproduct.getProduct_description());
			getbean.setPurchasedDate(tblproduct.getPurchased_date().toString());
			getbean.setProductAvalibleStatus(tblproduct.getProduct_availibility_status().getStatus_id());
			getbean.setProductImage(s.toString());
			getbean.setOwnerId(tblproduct.getOwner_id());
			
			//add to arraList
			pb.add(getbean);
		}		
		return pb;
	}
	public List<GetProductFormBean> viewUserProductsDeligates(int userid)throws DbException,SQLException,IOException{
			
		System.out.println("\n\t\n\t<---->viewuserproductsDEligates on viewuserproductsdeligate");
		
		ProductManagementServiceImpl productmanagementservice=null;
		List<GetProductFormBean> pb = null;
		List<ProductDto> p=null;
		GetProductFormBean getbean=null;
		
		//create service class object
		productmanagementservice = new ProductManagementServiceImpl();
		
		//call service class
		p = productmanagementservice.viewUserProductsService(userid);
		
		System.out.println("now control is in deligate");
		
		//arraylist object
		pb = new ArrayList<GetProductFormBean>();
		
		for(ProductDto tblproduct:p){
			String s = ImageUtility.BinaryToString(tblproduct);
			
		//create getproductformbean class object
		getbean = new GetProductFormBean();
		
		System.out.println(tblproduct.getProduct_id()+"----"+tblproduct.getProduct_name()+"---"+tblproduct.getProduct_price()+"---"+tblproduct.getPurchased_date().toString()+"-------------"+tblproduct.getProduct_description());
			
		getbean.setProductId(tblproduct.getProduct_id());
		getbean.setProductname(tblproduct.getProduct_name());
		getbean.setProductprice(tblproduct.getProduct_price());
		getbean.setPurchasedDate(tblproduct.getPurchased_date().toString());
		getbean.setProductdescription(tblproduct.getProduct_description());
		//productmanagementservice.getStatus(tblproduct.getProduct_availibility_status().getStatus_id());			
		getbean.setProductAvalibleStatus(tblproduct.getProduct_availibility_status().getStatus_id());
		getbean.setProductImage(s.toString());
		getbean.setOwnerid(tblproduct.getOwner_id().getUserid());
		
		//add to arraylist
		pb.add(getbean);
		}
		return pb;
	}
	public Integer addProduct(AddProductFormBean apfb,Integer userId) throws SQLException,DbException
	{
		ProductManagementServiceImpl productmanagementserviceimpl=null;
				
		//create obj of ProductManagementServiceImpl
		productmanagementserviceimpl = new ProductManagementServiceImpl();
		
		return productmanagementserviceimpl.addProduct(apfb, userId);
	}
	public Integer updateProduct(AddProductFormBean apfb,Integer userId)throws SQLException,DbException{
		
		ProductManagementServiceImpl productmanagementserviceimpl=null;
				
		//create obj of ProductManagementServiceImpl
		productmanagementserviceimpl = new ProductManagementServiceImpl();
		
		System.out.println("Sql date is--->"+apfb.getPurchased_Date());
		
		return productmanagementserviceimpl.updateProduct(apfb, userId);	
	}
	public List<ProductDto> viewProduct() throws SQLException, IOException 
	{
		ProductManagementServiceImpl pmsi=null;
		
		//create obj for Productserviceimpl class obj
		pmsi = new ProductManagementServiceImpl();
				
		return pmsi.viewProducts();
		
	}
	public int removeUserProducts(Integer productId, Integer ownerid)throws SQLException, DbException 
	{		
		ProductManagementServiceImpl pmsi=null;
		
		pmsi= new ProductManagementServiceImpl();
		
		return pmsi.removeUserProducts(productId, ownerid);
	}
}