package com.wipro.dao;

import com.wipro.bo.CustomerBO;

public interface ICustomerDAO {
	public int insert(CustomerBO bo)throws Exception;
	public CustomerBO search(int cNo)throws Exception;
	public int delete(CustomerBO bo)throws Exception;
	public int update(CustomerBO bo)throws Exception;
}
