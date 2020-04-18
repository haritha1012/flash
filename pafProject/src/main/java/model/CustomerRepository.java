package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

Connection con = null;
	
	Statement statement;
	
	PreparedStatement preStatement;
	
	public CustomerRepository(){
		
		String dbURL = "jdbc:mysql://localhost:3306/helthcare" ;
		String dbDriver = "com.mysql.jdbc.Driver" ;
		String dbUsername = "root" ;
		String dbPassword = "employee@321";
		
		
		try {
			if(con == null || con.isClosed()) {
				
				Class.forName(dbDriver) ;
				
				con = DriverManager.getConnection(dbURL,dbUsername , dbPassword);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
public List<Customer> getCustomer() {
		
		List<Customer> customer = new ArrayList<>();
		
		String sql = "SELECT * FROM user";
		
	try {
		Statement state = con.createStatement();
		ResultSet results = state.executeQuery(sql);
		
		while (results.next()) {
			Customer cus = new Customer();
			
			cus.setUserid(results.getString(1));
			cus.setFname(results.getString(2));
			cus.setLname(results.getString(3));
			cus.setAddress(results.getString(4));
			cus.setDoB(results.getDate(5));
			cus.setPhone(results.getString(6));
			cus.setEmail(results.getString(7));
			
			customer.add(cus);
			
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return customer;

}

public void create(Customer cus1) {
	
	String sql = "INSERT INTO user(userid,fname,lname,address,DoB,phone,email)VALUES (?,?,?,?,?,?,?) ";
	
	try {
		PreparedStatement state = con.prepareStatement(sql);
		
		state.setString(1, cus1.getUserid());
		state.setString(2, cus1.getFname());
		state.setString(3,  cus1.getLname());
		state.setString(4,  cus1.getAddress());
		state.setDate(5,  cus1.getDoB());
		state.setString(6,  cus1.getPhone());
		state.setString(7,  cus1.getEmail());
		
		state.executeUpdate();
		
	} catch (Exception e) {
		System.out.println(e);
	}
}

public Customer getCustomer(String userid) {
	String sql = "SELECT * FROM user WHERE userid="+userid;
	Customer cus = new Customer();
	
	try {
		Statement state = con.createStatement();
		ResultSet results = state.executeQuery(sql);
		
		if (results.next()) {
			cus.setUserid(results.getString(1));
			cus.setFname(results.getString(2));
			cus.setLname(results.getString(3));
			cus.setAddress(results.getString(4));
			cus.setDoB(results.getDate(5));
			cus.setPhone(results.getString(6));
			cus.setEmail(results.getString(7));
			
		}
		
	} catch (Exception e) {
		System.out.println(e);
	}
	return cus;
}


public void delete(String userid) {
	String sql = "DELETE FROM user WHERE userid=?";
	
	try {
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, userid);
		
		pstate.executeUpdate();
		
	} catch (Exception e) {
		System.out.println(e);
	}
}


public void update(Customer cus) {
	
	String sql = "UPDATE user SET Fname=?,Lname=?,Address=?,DoB=?,Phone=?,Email=? WHERE userid=?";
	
	try {
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(2, cus.getFname());
		pstate.setString(3, cus.getLname());
		pstate.setString(4, cus.getAddress());
		pstate.setDate(5, cus.getDoB());
		pstate.setString(6, cus.getPhone());
		pstate.setString(7, cus.getEmail());
		
		pstate.executeUpdate();
	
	} catch (Exception e) {
		System.out.println(e);
	}
}
	

}
