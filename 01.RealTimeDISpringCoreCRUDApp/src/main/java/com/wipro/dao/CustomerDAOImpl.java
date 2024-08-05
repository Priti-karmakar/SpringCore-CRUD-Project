package com.wipro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wipro.bo.CustomerBO;

public final class CustomerDAOImpl implements ICustomerDAO {
	private DataSource ds;
	
	public CustomerDAOImpl(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public int insert(CustomerBO bo) throws Exception {
		int count=0;
		try(Connection con=ds.getConnection();
			Statement st=con.createStatement();){
			if(st!=null) {
				count=st.executeUpdate("insert into realTimeDI_Customer values(CUST_NO_SEQ.nextval,'"+bo.getCName()+"','"+bo.getCAddr()+"',"+bo.getPAmt()+","+bo.getIntrAmt()+")");
			}
		}catch(SQLException se) {
			se.printStackTrace();
			throw se;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}
	
	@Override
	public CustomerBO search(int cNo)throws Exception{
		CustomerBO bo=null;
		try(Connection con=ds.getConnection();
			Statement st=con.createStatement();){
			ResultSet rs=null;
			if(st!=null) 
				rs=st.executeQuery("select * from realTimeDI_Customer where cNo="+cNo);
			if(rs!=null) {
				boolean b=rs.next();
				if(b==true) {
					bo=new CustomerBO();
					bo.setCNo(rs.getInt(1));
					bo.setCName(rs.getString(2));
					bo.setCAddr(rs.getString(3));
					bo.setPAmt(rs.getInt(4));
					bo.setIntrAmt(rs.getFloat(5));
				}
				else {
					bo=null;
				}	
			}
		}catch(SQLException se) {
			se.printStackTrace();
			throw se;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bo;
	}
	
	@Override
	public int update (CustomerBO bo) throws Exception{
		int count=0;
		try(Connection con=ds.getConnection();
				Statement st=con.createStatement();){
				if(st!=null) {
					count=st.executeUpdate("update realtimedi_customer set cname='"+bo.getCName()+"',caddr='"+bo.getCAddr()+"',pamt="+bo.getPAmt()+",intrAmt="+bo.getIntrAmt()+" where cno="+bo.getCNo());
				}
			}catch(SQLException se) {
				se.printStackTrace();
				throw se;
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		return count;
	}
	
	@Override
	public int delete(CustomerBO bo)throws Exception{
		int count=0;
		try(Connection con=ds.getConnection();
			Statement st=con.createStatement();){
			if(st!=null) {
				count=st.executeUpdate(" delete from realtimedi_customer where cno="+bo.getCNo());
			}
		}catch(SQLException se) {
			se.printStackTrace();
			throw se;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

}
