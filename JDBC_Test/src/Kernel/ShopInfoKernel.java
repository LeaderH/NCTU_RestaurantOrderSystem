package Kernel;

import java.sql.*;
import java.util.*;

import db.MySQL;

public class ShopInfoKernel extends MySQL{
	private int sid;
	private String fullname;
	private String location;
	private Item[] itemList;
	
	private void varinit(){
		sid=-1;
		fullname=null;
		location=null;
		itemList=new Item[1];
	}
	
	ShopInfoKernel(){
		super();
		varinit();
	}
	
	public boolean GetInfo(int uid) {
		boolean success=false;
		String selectSQL = "SELECT s_id,fullname,location FROM shop "+
				"WHERE uid='"+uid+"'";
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				success=true;
				sid=rs.getInt("s_id");
				fullname=rs.getString("fullname");
				location=rs.getString("location");
				FetchItemList();
			}
			else{
				varinit();
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return success;
	}
	
	private void FetchItemList(){
		String selectSQL = "SELECT i_id,fullname,value,description FROM item "+
				"WHERE s_id='"+sid+"'";
		ArrayList<Item> L=new ArrayList<Item>();
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			while(rs.next()) {
				Item I=new Item(
						rs.getInt("i_id"),
						sid,
						rs.getString("fullname"),
						rs.getInt("value"),
						rs.getString("description")
						);
				L.add(I);
			}
			itemList=L.toArray(itemList);
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
		ShopInfoKernel test = new ShopInfoKernel();
		test.GetInfo(3);
		Item[] itemlist=test.getItemList();
		for(Item I:itemlist){
			System.out.println(I.getFullname());
		}
	}
	
	public int getSid() {
		return sid;
	}
	public String getFullname() {
		return fullname;
	}
	public String getLocation() {
		return location;
	}
	public Item[] getItemList() {
		return itemList;
	}
	
	public class Item{
		private int i_id;
		private int s_id;
		private String fullname;
		private int value;
		private String description;
		
		private void varinit(){
			i_id=-1;
			s_id=-1;
			value=0;
			fullname=null;
			description=null;
		}
		Item(int iid,int sid,String name,int v,String des){
			i_id=iid;
			s_id=sid;
			value=v;
			fullname=name;
			description=des;
		}
		Item(){
			varinit();
		}
		public int getI_id() {
			return i_id;
		}
		public void setI_id(int i_id) {
			this.i_id = i_id;
		}
		public int getS_id() {
			return s_id;
		}
		public void setS_id(int s_id) {
			this.s_id = s_id;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
}
