package com.nacre.productexchange.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.nacre.productexchange.daoi.ProductManagementDaoImpl;
import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;

public class ProductManagementServiceImpl implements ProductManagementService
{

	@Override
	public List<ProductDto> viewEligibleProductsService() throws DbException 
	{
		List<ProductDto> eligibleproducts=null;
		ProductManagementDaoImpl productmanagementdaoimpl=null;
		
		productmanagementdaoimpl = new ProductManagementDaoImpl();
		eligibleproducts = productmanagementdaoimpl.viewEligibleProducts();
		
		return eligibleproducts;
		
	}

	public List<ProductDto> viewUserProductsService(int userId)throws DbException,SQLException, IOException
	{
		ProductManagementDaoImpl productmanagementdaoimpl=null;
		List<ProductDto> eligibleproducts=null;
		
		productmanagementdaoimpl = new ProductManagementDaoImpl();
		eligibleproducts = productmanagementdaoimpl.viewUserProducts(userId);
		
		return eligibleproducts;
		
	}

	@Override
	public Integer addProduct(AddProductFormBean apfb, Integer userId) throws SQLException, DbException 
	{
		ProductManagementDaoImpl productmanagementdaoimpl=null;
		
		productmanagementdaoimpl = new ProductManagementDaoImpl();
		
		return productmanagementdaoimpl.addProducts(apfb,userId);
	}
	
	@Override
	public Integer updateProduct(AddProductFormBean apfb, Integer userId) throws SQLException, DbException {
		
		ProductManagementDaoImpl productmanagementdaoimpl=null;
		
		productmanagementdaoimpl = new ProductManagementDaoImpl();
		
		return productmanagementdaoimpl.updateProducts(apfb,userId);		
	}

	@Override
	public List<ProductDto> viewProducts() throws SQLException, IOException 
	{
		ProductManagementDaoImpl productmanagementdaoimpl=null;
		
		productmanagementdaoimpl = new ProductManagementDaoImpl();
		
		return productmanagementdaoimpl.viewProducts();

	}

	@Override
	public int removeUserProducts(Integer productId, Integer ownerid) throws SQLException, DbException {
		
		ProductManagementDaoImpl pdai=null;		
		
		pdai = new ProductManagementDaoImpl();
		
		return pdai.removeUserProducts(productId, ownerid);
	}

	@Override
	public int getStatus(int number) throws DbException, IOException
	{
		ProductManagementDaoImpl pmsi=null;
		
		pmsi = new ProductManagementDaoImpl();
		
		return pmsi.getStatus(number);
	}
	
}
