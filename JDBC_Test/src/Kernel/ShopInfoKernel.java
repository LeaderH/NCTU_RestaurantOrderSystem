package Kernel;

import java.sql.*;
import java.util.*;

import db.MySQL;
import Kernel.Constants.Item;

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
	
	public ShopInfoKernel(){
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
		String selectSQL = "SELECT i_id,fullname,value,description,available FROM item "+
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
						rs.getString("description"),
						rs.getBoolean("available")
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
	
	public void updateItemInfo(Item I){
		int avail=I.isAvailable()?1:0;
		String selectSQL = "UPDATE item "+
				"SET fullname='"+I.getFullname()+"'"+
				", value="+I.getValue()+
				", description='"+I.getDescription()+"'"+
				", available='"+avail+"'"+
				" WHERE i_id="+I.getI_id()+"";
		try {
			stat = con.createStatement();
			stat.executeUpdate(selectSQL);
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public void insertItem(Item I){
		String insertdbSQL = "INSERT into item(s_id,fullname,value,description) " + 
			      "VALUES ('"+sid+"','"+I.getFullname()+"','"+I.getValue()+"','"+I.getDescription()+"')"; 
		try {
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
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
}
