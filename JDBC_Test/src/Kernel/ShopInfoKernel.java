package Kernel;

import java.sql.*;
import java.util.*;

import db.MySQL;
import Kernel.Constants.Item;
import Kernel.Constants.Order;

public class ShopInfoKernel extends MySQL{
	private int sid;
	private String fullname;
	private String location;
	private Item[] itemList;
	private Order[] orderList;
	
	

	private void varinit(){
		sid=-1;
		fullname=null;
		location=null;
		itemList=new Item[1];
		orderList=new Order[1];
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
			if(con==null) reconnect();
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				success=true;
				sid=rs.getInt("s_id");
				fullname=rs.getString("fullname");
				location=rs.getString("location");
				FetchItemList();
				FetchOrderList();
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
			if(con==null) reconnect();
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
			if(con==null) reconnect();
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
			if(con==null) reconnect();
			stat = con.createStatement();
			stat.executeUpdate(insertdbSQL);
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public Item FetchItem(int iid){
		Item I=new Item();
		String selectSQL = "SELECT fullname,value,description,available FROM item "+
				"WHERE i_id='"+iid+"'";
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			if(rs.next()) {
				I=new Item(
						iid,
						sid,
						rs.getString("fullname"),
						rs.getInt("value"),
						rs.getString("description"),
						rs.getBoolean("available")
						);
			}
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
		return I;
	}
	
	public void FetchOrderList(){
		String selectSQL = "SELECT * FROM `order` "+
				"WHERE s_id='"+sid+"'";
				
		ArrayList<Order> L=new ArrayList<Order>();
		try {
			if(con==null) reconnect();
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);
			
			while(rs.next()) {
				Order I=new Order(
						rs.getInt("o_id"),
						rs.getInt("g_id"),
						sid,
						rs.getInt("i_id"),
						rs.getInt("quant"),
						rs.getBoolean("isdone"),
						rs.getTimestamp("timestmp")
						);
				//System.out.println(rs.getInt("i_id"));
				//Order(int oid,int gid,int sid,int iid,
				//		int quant,boolean isdone,Timestamp ts)
				L.add(I);
			}
			orderList=L.toArray(orderList);
		} catch (SQLException e) {
			System.out.println("SelectDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	public void updateOrderDone(int oid){
		String selectSQL = "UPDATE `order` "+
				"SET isdone='1'"+
				" WHERE o_id="+oid+"";
		try {
			if(con==null) reconnect();
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
		ShopInfoKernel test = new ShopInfoKernel();
		test.GetInfo(3);
		test.FetchOrderList();
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
	public Order[] getOrderList() {
		return orderList;
	}
}
