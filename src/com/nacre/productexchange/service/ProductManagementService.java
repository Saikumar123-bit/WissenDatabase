package com.nacre.productexchange.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;
import com.nacre.productexchange.formbean.ResetFormBean;

public interface ProductManagementService 
{
	
	public List<ProductDto> viewEligibleProductsService() throws DbException;
	
	public Integer addProduct(AddProductFormBean apfb,Integer userId)throws SQLException,DbException;
	
	public Integer updateProduct(AddProductFormBean apfb,Integer userId)throws SQLException,DbException;
	
	public List<ProductDto> viewProducts()throws SQLException,IOException;
	
	public List<ProductDto> viewUserProductsService(int userId)throws DbException,SQLException, IOException;
	
	public int getStatus(int number)throws DbException,IOException;
	
	public int removeUserProducts(Integer productId, Integer ownerid)throws SQLException, DbException;
	
}
