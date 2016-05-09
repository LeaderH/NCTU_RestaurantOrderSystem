package Kernel;

import java.sql.*;

import Md5.Md5;
import db.MySQL;

public class GuestInfoKernel extends MySQL{
	
	private int gid;
	private String fullname;
	private String studentid;
	private String dept;
	private boolean gender;
	/**
	 * initialize variables
	 */
	private void varinit(){
		gid=-1;
		fullname=null;
		studentid=null;
		dept=null;
		gender=Constants.MALE;
	}
	/**
	 * Constructor
	 */
	public GuestInfoKernel(){
		super();
		varinit();
	}
	/**
	 * get guest's info by uid
	 * @param uid
	 * @return success request
	 */
	public boolean GetInfo(int uid) {
		boolean success=true;
		String selectSQL = "SELECT g_id,fullname,studentid,dept,gender FROM guest "+
				"WHERE uid='"+uid+"'";;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				success=true;
				gid=rs.getInt("g_id");
				fullname=rs.getString("fullname");
				studentid=String.format("%07d", rs.getInt("studentid"));
				dept=rs.getString("dept");
				gender=rs.getBoolean("gender");
			}
			else{
				success=false;
				varinit();
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return success;
	}
	
	public int getGid() {
		return gid;
	}
	public String getFullname() {
		return fullname;
	}
	public String getStudentid() {
		return studentid;
	}
	public String getDept() {
		return dept;
	}
	public boolean getGender() {
		return gender;
	}
	/**
	 * testing func
	 * @param args
	 */
	public static void main(String[] args) {
		GuestInfoKernel test = new GuestInfoKernel();
		test.GetInfo(1);
		System.out.println(test.getFullname()+" "+test.studentid);
		test.GetInfo(2);
		System.out.println(test.getFullname()+" "+test.studentid);
	    System.out.println("done");
	}
}
