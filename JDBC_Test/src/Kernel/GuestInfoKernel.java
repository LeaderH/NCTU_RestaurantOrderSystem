package Kernel;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import Kernel.Constants.Item;
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
	public void insertOrder(int g_id,int s_id,int i_id,int quant){
		String insertdbSQL = "INSERT into `order`(`g_id`,`s_id`,`i_id`,`quant`) " + 
			      "VALUES ('"+g_id+"','"+s_id+"','"+i_id+"','"+quant+"')"; 
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	public void input_all_shop_name_into_combobox(ArrayList<ShopInfoKernel> shoplist){
				
		String selectSQL = "SELECT uid FROM shop ";
		
		
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);

			while(rs.next()) {
				ShopInfoKernel shop1=new ShopInfoKernel();
				shop1.GetInfo(rs.getInt("uid"));
				shoplist.add(shop1);
				
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
		/*for(int i=0;i<tabledata.getRowCount();i++){
			tabledata.removeRow(0);			
		}*/
		tabledata.addRow(new Object[]{A,B,C,D,E,F});
	}
	public DefaultTableModel gettabledata(){
		return tabledata;
	}
	public String find_item_name_by_iid(int iid){
		String selectSQL = "SELECT fullname FROM `item` "+
				"WHERE i_id='"+iid+"'";
		String information_string = new String("") ;
		
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			while(rs.next()) {
			
				information_string = rs.getString("fullname");// rs.getString("fullname");
			
			}
		}catch (SQLException e) {
				System.out.println("SelectDB Exception :" + e.toString());
			} finally {
				Close();
			}
		
		return information_string;
		
	}
	
	public String find_shop_name_by_sid(int sid){
		String selectSQL = "SELECT fullname FROM `shop` "+
				"WHERE s_id='"+sid+"'";
		String information_string = new String("") ;
		
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			while(rs.next()) {
			
				information_string = rs.getString("fullname");// rs.getString("fullname");
			
			}
		}catch (SQLException e) {
				System.out.println("SelectDB Exception :" + e.toString());
			} finally {
				Close();
			}
		
		return information_string;
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
		
		for(int i=0;i<tabledata.getRowCount();i++){
			tabledata.removeRow(0);			
		}
		
		try {
			if(con==null) reconnect();//////////////////////important change!!!
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			int counter = 1;
			
			while(rs.next()) {
//				addtabledata( ""+rs.getInt("o_id"),""+rs.getInt("s_id"),""+rs.getInt("isdone"),""+rs.getString("timestmp"),""+rs.getString("i_id"),""+rs.getString("quant"));
				addtabledata( ""+counter,""+rs.getInt("s_id"),""+rs.getInt("isdone"),""+rs.getString("timestmp"),""+rs.getString("i_id"),""+rs.getString("quant"));

				///find_shop_name_by_sid(tmp_s_id);  似乎不能出現在這，會有NULL POINTER EXCEPTION
				
				
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
				
				counter++;
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
		
		for(int i=0;i<tabledata.getRowCount();i++){
			int tmp_s_id = Integer.parseInt((String)tabledata.getValueAt(i,1));
			int tmp_i_id = Integer.parseInt((String)tabledata.getValueAt(i,4));
			String tmp = new String("");
			tmp = find_shop_name_by_sid(tmp_s_id);
			tabledata.setValueAt(tmp, i, 1);
			tmp = find_item_name_by_iid(tmp_i_id);
			tabledata.setValueAt(tmp, i, 4);
			
			if(Integer.parseInt((String)tabledata.getValueAt(i,2))==0){
				tabledata.setValueAt("Trade has not done yet!!!",i,2);				
			}else{
				tabledata.setValueAt("Trade done!!!",i,2);
			}
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
