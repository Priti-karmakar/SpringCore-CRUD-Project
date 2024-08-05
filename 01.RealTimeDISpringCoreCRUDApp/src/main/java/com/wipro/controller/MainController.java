package com.wipro.controller;

import com.wipro.dto.CustomerDTO;
import com.wipro.service.ICustomerMgmtService;
import com.wipro.vo.CustomerVO;

public final class MainController {
	private ICustomerMgmtService service;
	
	public MainController(ICustomerMgmtService service) {
		this.service=service;
	}
	
	public String processCustomer(CustomerVO vo)throws Exception{
		CustomerDTO dto=new CustomerDTO();
		dto.setCName(vo.getCName());
		dto.setCAddr(vo.getCAddr());
		dto.setPAmt(Integer.parseInt(vo.getPAmt()));
		dto.setRate(Float.parseFloat(vo.getRate()));
		dto.setTime(Float.parseFloat(vo.getTime()));
		String resultMsg=service.registerCustomer(dto);
		return resultMsg;
	}
	
	@SuppressWarnings("unused")
	public CustomerVO selectCustomer(int cNo)throws Exception{
		CustomerDTO dto=service.searchCustomer(cNo) ;
		CustomerVO vo=new CustomerVO();
		if(dto!=null){
			vo.setCNo(dto.getCNo().toString());
			vo.setCName(dto.getCName());
			vo.setCAddr(dto.getCAddr());
			vo.setPAmt(dto.getPAmt().toString());
			vo.setIntrAmt(dto.getIntrAmt().toString());
		}
		else
			vo=null;
		return vo;
	}
	
	public String updateCustomer(CustomerVO vo)throws Exception{
		CustomerDTO dto=new CustomerDTO();
		dto.setCNo(Integer.parseInt(vo.getCNo()));
		dto.setCAddr(vo.getCAddr());
		dto.setCName(vo.getCName());
		dto.setPAmt(Integer.parseInt(vo.getPAmt()));
		System.out.println(vo.getRate()+" "+vo.getTime());
		dto.setRate(Float.parseFloat(vo.getRate()));
		dto.setTime(Float.parseFloat(vo.getTime()));
		return service.updateCustomer(dto);
	}
	
	public String deleteCustomer(CustomerVO vo)throws Exception{
		CustomerDTO dto=new CustomerDTO();
		dto.setCNo(Integer.parseInt(vo.getCNo()));
		return service.removeCustomer(dto);
	}
}
