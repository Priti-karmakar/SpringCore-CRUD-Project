package com.wipro.service;

import com.wipro.dto.CustomerDTO;

public interface ICustomerMgmtService {
	public String registerCustomer(CustomerDTO dto)throws Exception;
	public CustomerDTO searchCustomer(int cNo)throws Exception;
	public String updateCustomer(CustomerDTO dto)throws Exception;
	public String removeCustomer(CustomerDTO dto)throws Exception;
}
