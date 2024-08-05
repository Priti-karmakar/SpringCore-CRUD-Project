package com.wipro.service;

import com.wipro.bo.CustomerBO;
import com.wipro.dao.ICustomerDAO;
import com.wipro.dto.CustomerDTO;

public class CustomerServiceImpl implements ICustomerMgmtService {

	private ICustomerDAO dao;
	
	public CustomerServiceImpl(ICustomerDAO dao) {
		this.dao = dao;
	}

	@Override
	public String registerCustomer(CustomerDTO dto) throws Exception {
		float intrAmt=(dto.getPAmt()*dto.getRate()*dto.getTime())/100.0f;
		CustomerBO bo=new CustomerBO();
		bo.setCName(dto.getCName());
		bo.setCAddr(dto.getCAddr());
		bo.setPAmt(dto.getPAmt());
		bo.setRate(dto.getRate());
		bo.setTime(dto.getTime());
		bo.setIntrAmt(intrAmt);
		
		int count=dao.insert(bo);
		if(count!=0)
			return "Customer registration success";
		else
			return "Customer registration failed";
	}
	
	@Override
	public CustomerDTO searchCustomer(int cNo)throws Exception{
		CustomerDTO dto=new CustomerDTO();
		CustomerBO bo=dao.search(cNo);
		if(bo!=null) {
			dto.setCNo(bo.getCNo());
			dto.setCName(bo.getCName());
			dto.setCAddr(bo.getCAddr());
			dto.setPAmt(bo.getPAmt());
			dto.setIntrAmt(bo.getIntrAmt());
		}
		else
			dto=null;
		return dto;
	}
	
	
	@Override
	public String updateCustomer(CustomerDTO dto) throws Exception{
		CustomerBO bo=new CustomerBO();
		float intrAmt=(dto.getPAmt()*dto.getRate()*dto.getTime())/100.0f;
		System.out.println(dto.getPAmt()+" "+dto.getRate()+" "+dto.getTime());
		bo.setCAddr(dto.getCAddr());
		bo.setCName(dto.getCName());
		bo.setPAmt(dto.getPAmt());
		bo.setCNo(dto.getCNo());
		bo.setIntrAmt(intrAmt);
		int count=dao.update(bo);
		return count==0?"Incorrect customer detail!!":"Customer address updated successfully :)";
	}
	
	
	@Override
	public String removeCustomer(CustomerDTO dto)throws Exception{
		int count=0;
		CustomerBO bo=new CustomerBO();
		bo.setCNo(dto.getCNo());
		count=dao.delete(bo);
		return count==0?"Incorrect customer detail!!":"Customer details deleted successfully :)";
	}

}
