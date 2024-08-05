package com.wipro.test;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wipro.controller.MainController;
import com.wipro.vo.CustomerVO;

public class RealTimeDI {

	public static void main(String[] args) {
		String cNo;
		String cName;
		String cAddr;
		String pAmt;
		String rate;
		String time;
		
		//read input from enduser using Scanner
		Scanner sc=new Scanner(System.in);
		
		//create IOC container
		ApplicationContext factory=new ClassPathXmlApplicationContext("config.xml");
		
		//get controller class obj
		MainController controller=factory.getBean("controller",MainController.class);
		
		//create customerVO class obj having those info
		CustomerVO vo=new CustomerVO();
		
		while(true) {
			System.out.println("\n*******************************");
			System.out.println("\tCRUD Operations");
			System.out.println("*******************************\n");
			System.out.println("1.Add Customer");
			System.out.println("2.Search Customer");
			System.out.println("3.Update Customer");
			System.out.println("4.Delete Customer");
			System.out.println("5.Exit");
			System.out.println("Enter your option:");
			int option=sc.nextInt();
			switch(option) {
				case 1:
					System.out.println("\n*****Customer Add Module*****\n");
					System.out.println("Enter The cust Name: ");
					cName=sc.next();
					System.out.println("Enter the cust addr: ");
					cAddr=sc.next();
					System.out.println("Enter the principal amt: ");
					pAmt=sc.next();
					System.out.println("Enter the rate: ");
					rate=sc.next();
					System.out.println("Enter the time: ");
					time=sc.next();
		
					vo.setCName(cName);
					vo.setCAddr(cAddr);
					vo.setPAmt(pAmt);
					vo.setRate(rate);
					vo.setTime(time);
					
					//invoke business methods
					try {
						String msg=controller.processCustomer(vo);
						System.out.println("Result is: "+msg);
					}catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.println("\n*****Customer Search Module*****\n");
					System.out.println("Enter the Customer number:");
					cNo=sc.next();
					try {
						vo=controller.selectCustomer(Integer.parseInt(cNo));
						if(vo==null)
							System.out.println("No records found");
						else{
							System.out.println("Status: Customer existed");
							System.out.println("Customer number: "+vo.getCNo());
							System.out.println("Customer name: "+vo.getCName());
							System.out.println("Customer address: "+vo.getCAddr());
							System.out.println("Principal Amount: "+vo.getPAmt());
							System.out.println("Interest Amount: "+vo.getIntrAmt());
							System.out.println();
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("\n*****Customer Update Module*****\n");
					System.out.println("Enter the Customer number:");
					cNo=sc.next();
					try {
						vo=controller.selectCustomer(Integer.parseInt(cNo));
						if(vo==null)
							System.out.println("No records found");
						else {
							CustomerVO vo1=new CustomerVO();
							vo1.setCNo(cNo);
							System.out.println("Customer Name[Old:"+vo.getCName()+"]  New: ");
							String val1=sc.next();
							if(val1==null||val1.equals(""))
								vo1.setCName(vo.getCName());
							else 
								vo1.setCName(val1);
							System.out.println("Customer Address[Old:"+vo.getCAddr()+"]  New: ");
							String val2=sc.next();
							if(val2==null||val2.equals(""))
								vo1.setCAddr(vo.getCAddr());
							else 
								vo1.setCAddr(val2);
							System.out.println("Principal Amount[Old:"+vo.getPAmt()+"]  New: ");
							String val3=sc.next();
							if(val3==null||val3.equals(""))
								vo1.setPAmt(vo.getPAmt());
							else 
								vo1.setPAmt(val3);
							String uRate="2.4";
							String uTime="3.0";
							vo1.setRate(uRate);
							vo1.setTime(uTime);
//							System.out.println("Rate [Old:"+vo.getRate()+"]  New: ");
//							String val4=sc.next();
//							if(val4==null||val4.equals(""))
//								vo1.setRate(vo.getRate());
//							else 
//								vo1.setRate(val4);
//							System.out.println("Time [Old:"+vo.getTime()+"]  New: ");
//							String val5=sc.next();
//							if(val5==null||val5.equals(""))
//								vo1.setTime(vo.getTime());
//							else 
//								vo1.setTime(val5);
							String msg=controller.updateCustomer(vo1);
							System.out.println(msg);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 4: 
					System.out.println("\n*****Customer Delete Module*****\n");
					System.out.println("Enter the Customer number:");
					cNo=sc.next();
					try {
						vo.setCNo(cNo);
						String msg=controller.deleteCustomer(vo);
						System.out.println(msg);
					}catch(Exception e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.println("\n*****Thank you*****");
					System.exit(0);
					break;
				default: 
					System.out.println("\nWrong choice!!");
			}
		}	
	}
}
