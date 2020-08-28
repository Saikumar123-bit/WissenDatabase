package com.nacre.productexchange.daoi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.nacre.productexchange.dto.ProductDto;
import com.nacre.productexchange.exception.DbException;
import com.nacre.productexchange.formbean.AddProductFormBean;
import com.nacre.productexchange.formbean.ResetFormBean;

public interface ProductManagementDao 
{
	
	public List<ProductDto> viewEligibleProducts()throws DbException;
	
	public List<ProductDto> viewUserProducts(int userId)throws SQLException,IOException,DbException;
	
	public int getStatus(int number)throws DbException,IOException;
	
	public List<ProductDto> viewProducts()throws SQLException,IOException;
	
	public Integer addProducts(AddProductFormBean apdf,Integer userId) throws DbException;
	
	public Integer updateProducts(AddProductFormBean apdf,Integer userId) throws DbException;
	
	public int removeUserProducts(Integer productId, Integer ownerid) throws SQLException, DbException;
	
}
