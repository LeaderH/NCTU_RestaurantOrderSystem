package Kernel;

import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import db.MySQL;

public class GuestInfoKernel extends MySQL{
	
	private int gid;
	private String fullname;
	private String studentid;
	private String dept;
	private boolean gender;
	
	

	private DefaultTableModel tabledata;
	
	int shop_uid_array[];

	/**
	 * initialize variables
	 */
	private void varinit(){
		///////
		tabledata = new DefaultTableModel();
		tabledata.addColumn("o_id ");  tabledata.addColumn("s_id  ");  tabledata.addColumn("isdone ");  tabledata.addColumn("timestmp ");  tabledata.addColumn("i_id  ");  tabledata.addColumn("quant ");
		//////table
		
		
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
	
	public void input_all_shop_name_into_combobox(ShopInfoKernel skernel,JComboBox <String> list){
		shop_uid_array=new int[100];
		int counter = 1;
		
		
		String selectSQL = "SELECT fullname,uid FROM shop ";
		list.addItem("NONE");
		shop_uid_array[0]=-1;
		
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			
			list.removeAll();
			while(rs.next()) {
				shop_uid_array[counter] = rs.getInt("uid");
				list.addItem(rs.getString("fullname"));
				counter++;
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		
	}
	
	public void input_all_item_of_the_shop_name_into_combobox(JComboBox <String>shop_list,JComboBox <String>item_list,int selectIndex){
		String selectSQL = "SELECT fullname FROM shop ";
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			while(rs.next()) {
				item_list.removeAll();
				item_list.addItem(rs.getString("fullname"));
				
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public boolean GetInfo(int uid) {
		boolean success=true;
		String selectSQL = "SELECT g_id,fullname,studentid,dept,gender FROM guest "+
				"WHERE uid='"+uid+"'";;
		try {
			if(con==null) reconnect();//////////////////////important change!!!
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
	
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setGender(boolean gender) {
		if(gender){
			this.gender = Constants.FEMALE;
		}else{
			this.gender = Constants.MALE;
		}
	}
	
	public void addtabledata(String A,String B,String C,String D,String E,String F){
		for(int i=0;i<tabledata.getRowCount();i++){
			tabledata.removeRow(0);			
		}
		tabledata.addRow(new Object[]{A,B,C,D,E,F});
	}
	public DefaultTableModel gettabledata(){
		return tabledata;
	}
	
	public String getOrderSyntex(){
		
		
/*		String selectSQL = "SELECT g_id FROM order "+
//				,g_id,sid,isdone,timestmp
				"WHERE 'o_id'='"+1+"'";
				*/	
		String selectSQL = "SELECT * FROM `order` "+
				"WHERE g_id='"+gid+"'";
		/*
		String selectSQL = "SELECT o_id,g_id,fullname,studentid,dept,gender FROM guest "+
				"WHERE uid='"+uid+"'";
		*/
		String information_string = new String("") ;
				
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			while(rs.next()) {
				addtabledata( ""+rs.getInt("o_id"),""+rs.getInt("s_id"),""+rs.getInt("isdone"),""+rs.getString("timestmp"),""+rs.getString("i_id"),""+rs.getString("quant"));
				
				
				information_string += " o_id = ";
				information_string = information_string + rs.getInt("o_id");
				information_string += "\n";


				information_string += " sid = ";
				information_string = information_string + rs.getInt("s_id");
				information_string += "\n";
				
				information_string += " isdone = ";
				information_string = information_string + rs.getInt("isdone");
				information_string += "\n";
				
				information_string += " timestmp = ";
				information_string = information_string + rs.getString("timestmp");
				information_string += "\n";
		
				
				information_string += " i_id = ";
				information_string = information_string + rs.getString("i_id");
				information_string += "\n";
				
				information_string += " quant = ";
				information_string = information_string + rs.getString("quant");
				information_string += "\n";
				/*	
				gid=rs.getInt("g_id");
				fullname=rs.getString("fullname");
				studentid=String.format("%07d", rs.getInt("studentid"));
				dept=rs.getString("dept");
				gender=rs.getBoolean("gender");
				*/
				
			}/*
			else{
				
				information_string = "there is error!!!!!!!";
				
				varinit();
			}*/
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return information_string;
	}
	////////////////////////////////////////////////////my change!!!!
	public void send_data(){
		String selectSQL = "UPDATE guest "+
				"SET fullname='"+this.getFullname()+"'"+
				", studentid="+this.getStudentid()+
				", dept='"+this.getDept()+"'"+
				", gender='"+((this.getGender() == Constants.FEMALE)?1:0)+"'"+
				" WHERE g_id="+this.getGid()+"";
		try {
			stat = con.createStatement();
			stat.executeUpdate(selectSQL);
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	
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
