package Kernel;

import java.sql.*;
import Md5.Md5;
import db.MySQL;

public class LoginKernel extends MySQL {
	
	private int uid;
	private int type;
	/**
	 * initialize variables
	 */
	private void varinit(){
		uid=-1;
		type=Constants.GUEST;
	}
	/**
	 * Constructor
	 */
	public LoginKernel(){
		super();
		varinit();
	}
	/**
	 * Login check
	 * @param name
	 * @param pwd
	 * @return login success
	 */
	public boolean Login(String name,String pwd) {
		boolean loginsuccess=false;
		String hashed_pwd=Md5.md5(pwd);
		String selectSQL = "SELECT uid,type FROM users "+
				"WHERE acc_name='"+name+"' "+
				"AND pwd='"+hashed_pwd+"'";
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				loginsuccess=true;
				uid=rs.getInt("uid");
				type=rs.getInt("type");
			}
			else{
				loginsuccess=false;
				varinit();
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return loginsuccess;
	}

	public int getUid() {
		return uid;
	}
	public int getType() {
		return type;
	}
	/**
	 * testing func
	 * @param args
	 */
	public static void main(String[] args) {
		LoginKernel test = new LoginKernel();
		test.Login("alice", "alice");
		test.Login("alice", "ccccc");
	    System.out.println("done");
	}
}
