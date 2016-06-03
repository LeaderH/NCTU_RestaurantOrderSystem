package Kernel;

import java.sql.*;
import java.util.*;

import db.MySQL;

public class RegisterKernel extends MySQL{
	
	
	public RegisterKernel(){
		super();
	}
	
	public void createAccount(Account acc){
		
	}
	
	public void registerGuest(Guest guest){
		
	}
	
	public void registerShop(Shop shop){
		
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
