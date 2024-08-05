package com.wipro.dto;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CustomerDTO implements java.io.Serializable{
	private Integer cNo;
	private Integer pAmt;
	private Float rate;
	private Float time;
	private String cName;
	private String cAddr;
	private Float intrAmt;
}
