package com.wipro.bo;

import lombok.Data;

@Data
public class CustomerBO {
	private String cName;
	private String cAddr;
	private Integer cNo;
	private Integer pAmt;
	private Float rate;
	private Float time;
	private Float intrAmt;
}
