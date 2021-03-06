package Kernel;

import java.sql.*;
import java.util.*;

import Kernel.Constants.Item;
import db.MySQL;
import Md5.Md5;

public class RegisterKernel extends MySQL{
	
	
	public RegisterKernel(){
		super();
	}
	
	public boolean accountName_Used(String name){
		boolean flag=false;
		String selectSQL = "SELECT uid FROM `users` "+
				"WHERE `acc_name`='"+name+"'";
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				flag=true;
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return flag;
	}
	
	
	public int createAccount(Account acc){
		int uid=-1;
		String insertdbSQL = "INSERT into `users`(`acc_name`, `type`, `pwd`) " + 
			      "VALUES ('"+acc.getAcc()+"','"+acc.getType()+"','"+Md5.md5(acc.getPwd())+"')";
		String selectSQL = "SELECT uid FROM `users` "+
				"WHERE `acc_name`='"+acc.getAcc()+"'";
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				uid=rs.getInt("uid");
			}
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return uid;
	}
	
	public void registerGuest(int uid,Guest guest){
		int gender=(guest.isGender())?0:1;
		String insertdbSQL = "INSERT into `guest`(`uid`, `fullname`, `studentid`, `dept`, `gender`) " + 
			      "VALUES ('"+uid+"','"+guest.getFullname()+"','"+guest.getStudentid()+"','"+guest.getDept()+"','"+gender+"')";
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public void registerShop(int uid,Shop shop){
		String insertdbSQL = "INSERT into `shop`(`uid`, `fullname`, `location`) " + 
			      "VALUES ('"+uid+"','"+shop.getFullname()+"','"+shop.getLocation()+"')";
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public static class Shop{
		private String fullname;
		private String location;

		public Shop(){
			this.fullname="";
			this.location="";
		}
		public Shop(String fullname,String location){
			this.fullname=fullname;
			this.location=location;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
	}
	
	public static class Guest{
		private String fullname;
		private String studentid;
		private String dept;
		private boolean gender;
		public Guest(){
			this.fullname="";
			this.studentid="";
			this.dept="";
			this.gender=Constants.MALE;
		}
		public Guest(String fullname,String studentid,String dept,boolean gender){
			this.fullname=fullname;
			this.studentid=studentid;
			this.dept=dept;
			this.gender=gender;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public String getStudentid() {
			return studentid;
		}
		public void setStudentid(String studentid) {
			this.studentid = studentid;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
		}
		public boolean isGender() {
			return gender;
		}
		public void setGender(boolean gender) {
			this.gender = gender;
		}
	}
	
	public static class Account{
		private String acc;
		private String pwd;
		private int type;
		
		
		public Account(){
			acc="";
			pwd="";
			type=Constants.GUEST;
		}
		public Account(String acc,String pwd,int type){
			this.acc=acc;
			this.pwd=pwd;
			this.type=type;
		}
		public String getAcc() {
			return acc;
		}
		public void setAcc(String acc) {
			this.acc = acc;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

}
