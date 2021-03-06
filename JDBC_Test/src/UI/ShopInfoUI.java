package UI;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import Kernel.ShopInfoKernel;
import Kernel.GuestInfoKernel;
import Kernel.Constants.Item;
import Kernel.Constants.Order;
import Kernel.Constants;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import javax.swing.border.LineBorder;

public class ShopInfoUI {
	private static final String titleName="Shop Manage Panel";
	private int uid;
	private Item[] itemList;
	private final ShopInfoKernel kernel=new ShopInfoKernel();;
	private JFrame frmShopinfo;
	private JTextField txtf_name;
	private JTextField txtf_loc;
	private JButton btn_editinfo;
	private JButton btn_doneinfo;
	private JButton btn_cancelinfo;
	private JList<String>	list_item;
	private JTextField txtf_item_name;
	private JTextField txtf_item_value;
	private JTextArea textArea_item_des;
	private JButton btnEdit;
	private JButton btnNew;
	private JButton btnCancel;
	private JPanel panel_item_interact;

	private Item item_selected;
	
	private JList<String> list_order;
	
	private JTextField txtf_orderer;
	private JTextField txtf_ordertime;
	private JTextField txtf_itemrequest;
	private JTextField txtf_total;
	private JButton btnClose;
	private JButton btnDone;
	private JPanel panel_order_interact;
	private Order[] orderList;
	private Order order_selected;
	
	
	private JCheckBox chckbx_undone;
	private JCheckBox chckbx_done;
	int order_display_type=Constants.ORDER_DISPALY_TYPE_UNDONE;
	
	private final GuestInfoKernel gkernel=new GuestInfoKernel();
	
	
	/**
	 * Create the application.
	 */
	public ShopInfoUI(int uid) {
		this.uid=uid;
		itemList=new Item[1];
		initialize();
		update();
	}
	
	/**
	 * update all data with the db
	 */
	private void update(){
		kernel.GetInfo(uid); //refresh
		update_itemlist(true);
		update_orderlist(true);
	}
	
	private void update_itemlist(final boolean inforetrieved){
		Thread t=new Thread(new Runnable(){
			public void run(){
				
		String title=frmShopinfo.getTitle()+"--loading(item)";
		frmShopinfo.setTitle(title);		
		if(!inforetrieved){
			kernel.GetInfo(uid); //refresh
		}
		txtf_name.setText(kernel.getFullname());
		txtf_loc.setText(kernel.getLocation());
		itemList=kernel.getItemList();
		ArrayList<String> arr=new ArrayList<String>();
		if(itemList!=null){
			for(Item I : itemList){
				if(I.isAvailable() && I.getI_id()>=0 )
					arr.add(String.format("%s(%d)", I.getFullname(),I.getValue()));
			}
		}
		list_item.setListData(arr.toArray(new String[1]));
		frmShopinfo.setTitle(titleName);
		
			}
		});
		t.start();
	}
	
	private void update_orderlist(final boolean inforetrieved){
		Thread t=new Thread(new Runnable(){
			public void run(){
		String title=frmShopinfo.getTitle()+"--loading(order)";
		frmShopinfo.setTitle(title);
		if(!inforetrieved){
			kernel.GetInfo(uid); //refresh
		}
		orderList=kernel.getOrderList();
		ArrayList<String> arr=new ArrayList<String>();
		if(orderList!=null){
			for(Order O : orderList){
				if(O.getO_id()>=0){
					switch(order_display_type){
					default: case Constants.ORDER_DISPALY_TYPE_UNDONE:
						if(!O.isIsdone())
							orderlistInnerFunc(O,arr);
						break;
					case Constants.ORDER_DISPALY_TYPE_DONE:
						if(O.isIsdone()) 
							orderlistInnerFunc(O,arr);
						break;
					case Constants.ORDER_DISPALY_TYPE_ALL:
						orderlistInnerFunc(O,arr);
						break;
					case Constants.ORDER_DISPALY_TYPE_NONE:
						break;
					}
				}
			}
		}
		list_order.setListData(arr.toArray(new String[1]));
		frmShopinfo.setTitle(titleName);
			}
		});
		t.start();
	}
	
	/**
	 * Inner function to display items in the order list
	 * @param O
	 * @param arr
	 * @return
	 */
	ArrayList<String> orderlistInnerFunc(Order O,ArrayList<String> arr){
		String time=(new java.text.SimpleDateFormat("MM-dd HH:mm").format(O.getTimestamp()));
		gkernel.GetInfo(O.getG_id());
		String guestname=gkernel.getFullname();
		String itemname=kernel.FetchItem(O.getI_id()).getFullname();
		String done=(O.isIsdone())?"done":"undone";
		String s1=String.format("%20s * %-2d",itemname,O.getQuant());
		arr.add(String.format("%30s %20s | %-20s %s",s1,guestname,time,done));
		return arr;
	}
	
	private void btn_detail_action(){
		try{
			panel_item_interact.setVisible(true);
			item_selected=itemList[list_item.getSelectedIndex()];
			txtf_item_name.setText(item_selected.getFullname());
			txtf_item_value.setText(Integer.toString(item_selected.getValue()));
			textArea_item_des.setText(item_selected.getDescription());
		}catch(IndexOutOfBoundsException e){
			panel_item_interact.setVisible(false);
		}
	}
	private void btn_edit_action(){
		final Item item_edited=new Item(item_selected.getI_id(),item_selected.getS_id(),
				txtf_item_name.getText(),Integer.parseInt(txtf_item_value.getText()),textArea_item_des.getText(),
				true
				);
		//Item(int iid,int sid,String name,int v,String des,boolean available);
		kernel.updateItemInfo(item_edited);
		update_itemlist(false);
	}
	private void btn_add_action(){
		panel_item_interact.setVisible(true);
		btnNew.setVisible(true);
		btnEdit.setVisible(false);
		txtf_item_name.setText(null);
		txtf_item_value.setText(null);
		textArea_item_des.setText(null);
	}
	private void btn_new_action(){
		btnNew.setVisible(false);
		btnEdit.setVisible(true);
		Item item_new=new Item(-1,-1,
				txtf_item_name.getText(),Integer.parseInt(txtf_item_value.getText()),textArea_item_des.getText(),
				true
				);
		//Item(int iid,int sid,String name,int v,String des,boolean available);
		kernel.insertItem(item_new);
		update_itemlist(false);
	}
	private void btn_delete_action(){
		try{
			item_selected=itemList[list_item.getSelectedIndex()];
			int res=JOptionPane.showConfirmDialog(frmShopinfo,"Are you sure to delete item "+item_selected.getFullname()+"?",
					"Confirm",JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION){
				Item item_edited=new Item(item_selected.getI_id(),item_selected.getS_id(),
						item_selected.getFullname(),item_selected.getValue(),item_selected.getDescription(),
						false
						);
				//Item(int iid,int sid,String name,int v,String des,boolean available);
				kernel.updateItemInfo(item_edited);
				update_itemlist(false);
			}
		}catch(IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(frmShopinfo, "Please select an item", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void btn_cancel_action(){
		panel_item_interact.setVisible(false);
		btnNew.setVisible(false);
		btnEdit.setVisible(true);
	}
	
	private void btn_orderdetail_action(){
		try{
			panel_order_interact.setVisible(true);
			
			order_selected=orderList[list_order.getSelectedIndex()];
			
			String d=(new java.text.SimpleDateFormat("MM-dd HH:mm").format(order_selected.getTimestamp()));
			gkernel.GetInfo(order_selected.getG_id());
			String guestname=gkernel.getFullname();
			String itemname=kernel.FetchItem(order_selected.getI_id()).getFullname();
			int quant=order_selected.getQuant();
			int value=kernel.FetchItem(order_selected.getI_id()).getValue()*quant;
			
			txtf_orderer.setText(guestname);
			txtf_ordertime.setText(d);
			txtf_itemrequest.setText(String.format("%8s * %-2d", itemname,quant));
			txtf_total.setText(String.valueOf(value));
			
			if(order_selected.isIsdone())
				btnDone.setVisible(false);
			else
				btnDone.setVisible(true);
				
		}catch(IndexOutOfBoundsException e){
			panel_order_interact.setVisible(false);
		}
	}
	private void btn_orderclose_action(){
		panel_order_interact.setVisible(false);
	}
	private void btn_orderdone_action(){
		kernel.updateOrderDone(order_selected.getO_id());
		btnDone.setVisible(false);
		update_orderlist(false);
	}
	
	private void checkbox_action(){
		if(chckbx_undone.isSelected()){
			if(chckbx_done.isSelected()){
				order_display_type=Constants.ORDER_DISPALY_TYPE_ALL;
			}
			else{
				order_display_type=Constants.ORDER_DISPALY_TYPE_UNDONE;
			}
		}
		else if(chckbx_done.isSelected()){
			order_display_type=Constants.ORDER_DISPALY_TYPE_DONE;
		}
		else{
			order_display_type=Constants.ORDER_DISPALY_TYPE_NONE;
		}
		update_orderlist(false);
	}

	private void btn_logout_action(){
		frmShopinfo.dispatchEvent(new WindowEvent(frmShopinfo, WindowEvent.WINDOW_CLOSING));
		LoginUI.main(new String[0]);
	}
	
	private void btn_info_edit_action(){
		txtf_name.setEditable(true);
		txtf_loc.setEditable(true);
		btn_doneinfo.setVisible(true);
		btn_cancelinfo.setVisible(true);
		btn_editinfo.setVisible(false);
	}
	private void btn_info_done_action(){
		kernel.updateInfo(txtf_name.getText(), txtf_loc.getText());
		txtf_name.setEditable(false);
		txtf_loc.setEditable(false);
		btn_doneinfo.setVisible(false);
		btn_cancelinfo.setVisible(false);
		btn_editinfo.setVisible(true);
		kernel.GetInfo(uid); //refresh
	}
	private void btn_info_cancel_action(){
		txtf_name.setText(kernel.getFullname());
		txtf_name.setEditable(false);
		txtf_loc.setText(kernel.getLocation());
		txtf_loc.setEditable(false);
		btn_doneinfo.setVisible(false);
		btn_cancelinfo.setVisible(false);
		btn_editinfo.setVisible(true);
	}
	
	
	private void initialize() {
		frmShopinfo = new JFrame();
		frmShopinfo.setTitle(titleName);
		frmShopinfo.setBounds(100, 100, 600, 400);
		frmShopinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopinfo.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_title = new JPanel();
		frmShopinfo.getContentPane().add(panel_title, BorderLayout.NORTH);
		panel_title.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_title = new JLabel("Welcome");
		panel_title.add(lbl_title);
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_refreshbtn = new JPanel();
		panel_title.add(panel_refreshbtn, BorderLayout.EAST);
		
		JButton btn_refresh = new JButton(new ImageIcon(
						(new ImageIcon(
								ShopInfoUI.class.getResource("/image/refresh-icon.png")
								).getImage()
						).getScaledInstance(45, 40, java.awt.Image.SCALE_SMOOTH)
					)
				);
		btn_refresh.setToolTipText("Refresh");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		panel_refreshbtn.add(btn_refresh);
		btn_refresh.setPreferredSize(new Dimension(30, 30));
		
		JButton btn_Logout = new JButton(new ImageIcon(
				(new ImageIcon(
						ShopInfoUI.class.getResource("/image/logout-icon.png")
						).getImage()
				).getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)
			)
		);
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_logout_action();
			}
		});
		btn_Logout.setToolTipText("Logout");
		panel_refreshbtn.add(btn_Logout);
		btn_Logout.setPreferredSize(new Dimension(30, 30));
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmShopinfo.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		

	//tab info
		JPanel panel_info = new JPanel();
		tabbedPane.addTab("Info", null, panel_info, null);
		panel_info.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_info.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblInfo, BorderLayout.NORTH);

		JPanel panel_detail = new JPanel();
		panel_info.add(panel_detail, BorderLayout.CENTER);
		GridBagLayout gbl_panel_detail = new GridBagLayout();
		gbl_panel_detail.columnWidths = new int[] { frmShopinfo.getWidth(), 0 };
		gbl_panel_detail.rowHeights = new int[] { 35, 35, 0, 0 };
		gbl_panel_detail.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_detail.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_detail.setLayout(gbl_panel_detail);

		JPanel panel_name = new JPanel();
		GridBagConstraints gbc_panel_name = new GridBagConstraints();
		gbc_panel_name.insets = new Insets(0, 0, 5, 0);
		gbc_panel_name.fill = GridBagConstraints.BOTH;
		gbc_panel_name.gridx = 0;
		gbc_panel_name.gridy = 0;
		panel_detail.add(panel_name, gbc_panel_name);

		JLabel lblName = new JLabel("Name");
		panel_name.add(lblName);

		txtf_name = new JTextField();
		txtf_name.setEditable(false);
		panel_name.add(txtf_name);
		txtf_name.setColumns(10);

		JPanel panel_location = new JPanel();
		GridBagConstraints gbc_panel_location = new GridBagConstraints();
		gbc_panel_location.insets = new Insets(0, 0, 5, 0);
		gbc_panel_location.fill = GridBagConstraints.BOTH;
		gbc_panel_location.gridx = 0;
		gbc_panel_location.gridy = 1;
		panel_detail.add(panel_location, gbc_panel_location);

		JLabel lblLocation = new JLabel("Location");
		panel_location.add(lblLocation);

		txtf_loc = new JTextField();
		txtf_loc.setEditable(false);
		panel_location.add(txtf_loc);
		txtf_loc.setColumns(10);
		
		JPanel panel_infoedit = new JPanel();
		panel_info.add(panel_infoedit, BorderLayout.SOUTH);
		
		btn_editinfo = new JButton("Edit");
		btn_editinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_info_edit_action();
			}
		});
		panel_infoedit.add(btn_editinfo);
		
		btn_doneinfo = new JButton("Done");
		btn_doneinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_info_done_action();
			}
		});
		btn_doneinfo.setVisible(false);
		panel_infoedit.add(btn_doneinfo);
		
		btn_cancelinfo = new JButton("Cancel");
		btn_cancelinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_info_cancel_action();
			}
		});
		btn_cancelinfo.setVisible(false);
		panel_infoedit.add(btn_cancelinfo);
	//end tab info
		
	//tab item
		JPanel panel_item = new JPanel();
		tabbedPane.addTab("Item", null, panel_item, null);
		panel_item.setLayout(new BoxLayout(panel_item, BoxLayout.X_AXIS));
		
		JPanel panel_itemlist = new JPanel();
		panel_item.add(panel_itemlist);
		panel_itemlist.setLayout(new BorderLayout(0, 0));
		
		list_item=new JList<String>();
		list_item.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(list_item);
		panel_itemlist.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_btns = new JPanel();
		panel_itemlist.add(panel_btns, BorderLayout.SOUTH);
		
		JButton btn_itemdetail = new JButton("Detail");
		btn_itemdetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_detail_action();
			}
		});
		panel_btns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_btns.add(btn_itemdetail);
		
		JButton btn_itemadd = new JButton("Add");
		btn_itemadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_add_action();
			}
		});
		panel_btns.add(btn_itemadd);
		
		JButton btn_itemdelete = new JButton("Delete");
		btn_itemdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_delete_action();
			}
		});
		panel_btns.add(btn_itemdelete);
		
		JLabel lblItemlist = new JLabel("ItemList");
		lblItemlist.setHorizontalAlignment(SwingConstants.CENTER);
		panel_itemlist.add(lblItemlist, BorderLayout.NORTH);
		
		panel_item_interact = new JPanel();
		panel_item_interact.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_item.add(panel_item_interact);
		panel_item_interact.setLayout(new BorderLayout(0, 0));
		panel_item_interact.setVisible(false);
		
		JLabel lblItemDetail = new JLabel("Item Detail");
		panel_item_interact.add(lblItemDetail, BorderLayout.NORTH);
		lblItemDetail.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemDetail.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_item_details = new JPanel();
		panel_item_interact.add(panel_item_details, BorderLayout.CENTER);
		panel_item_details.setLayout(new BoxLayout(panel_item_details, BoxLayout.Y_AXIS));
		
		JPanel panel_item_name = new JPanel();
		panel_item_details.add(panel_item_name);
		
		JLabel lblItemname = new JLabel("ItemName");
		panel_item_name.add(lblItemname);
		
		txtf_item_name = new JTextField();
		panel_item_name.add(txtf_item_name);
		txtf_item_name.setColumns(10);
		
		JPanel panel_item_v = new JPanel();
		panel_item_details.add(panel_item_v);
		
		JLabel lblItemValue = new JLabel("Value");
		panel_item_v.add(lblItemValue);
		
		txtf_item_value = new JTextField();
		panel_item_v.add(txtf_item_value);
		txtf_item_value.setColumns(10);
		
		JPanel panel_des = new JPanel();
		panel_item_details.add(panel_des);
		panel_des.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		panel_des.add(lblDescription, BorderLayout.NORTH);
		
		textArea_item_des = new JTextArea();
		panel_des.add(textArea_item_des, BorderLayout.CENTER);
		
		JPanel panel_item_btns = new JPanel();
		panel_item_details.add(panel_item_btns, BorderLayout.SOUTH);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_edit_action();
			}
		});
		panel_item_btns.add(btnEdit);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_new_action();
			}
		});
		panel_item_btns.add(btnNew);
		btnNew.setVisible(false);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_cancel_action();
			}
		});
		panel_item_btns.add(btnCancel);
	//end tab item
	//tab order
		JPanel panel_order = new JPanel();
		tabbedPane.addTab("Order", null, panel_order, null);
		panel_order.setLayout(new BoxLayout(panel_order, BoxLayout.X_AXIS));
		
		JPanel panel_orderlist = new JPanel();
		panel_order.add(panel_orderlist);
		panel_orderlist.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_orderlistbar = new JPanel();
		panel_orderlist.add(panel_orderlistbar, BorderLayout.NORTH);
		panel_orderlistbar.setLayout(new BoxLayout(panel_orderlistbar, BoxLayout.X_AXIS));
		
		JLabel lblOrderlist = new JLabel("Order List");
		panel_orderlistbar.add(lblOrderlist);
		lblOrderlist.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_order_chkbox = new JPanel();
		panel_orderlistbar.add(panel_order_chkbox);
		
		chckbx_undone = new JCheckBox("Undone");
		chckbx_undone.setSelected(true);
		panel_order_chkbox.add(chckbx_undone);
		
		chckbx_done = new JCheckBox("Done");
		panel_order_chkbox.add(chckbx_done);
		
		chckbx_undone.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				checkbox_action();
			}
		});
		
		chckbx_done.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				checkbox_action();
			}
		});
		
		list_order = new JList<String>();
		list_order.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(list_order);
		panel_orderlist.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_orderbtns = new JPanel();
		panel_orderlist.add(panel_orderbtns, BorderLayout.SOUTH);
		
		JButton btn_orderdetail = new JButton("Detail");
		btn_orderdetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_orderdetail_action();
			}
		});
		panel_orderbtns.add(btn_orderdetail);
		
		panel_order_interact = new JPanel();
		panel_order_interact.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_order.add(panel_order_interact);
		panel_order_interact.setLayout(new BorderLayout(0, 0));
		panel_order_interact.setVisible(false);
		
		JLabel lblOrderDetail = new JLabel("Order Detail");
		lblOrderDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderDetail.setFont(new Font("Calibri", Font.BOLD, 20));
		panel_order_interact.add(lblOrderDetail, BorderLayout.NORTH);
		
		JPanel panel_order_details = new JPanel();
		panel_order_interact.add(panel_order_details, BorderLayout.CENTER);
		panel_order_details.setLayout(new BoxLayout(panel_order_details, BoxLayout.Y_AXIS));
		
		JPanel panel_orderer = new JPanel();
		panel_order_details.add(panel_orderer);
		
		JLabel lblOrderer = new JLabel("Orderer");
		panel_orderer.add(lblOrderer);
		
		txtf_orderer = new JTextField();
		txtf_orderer.setEditable(false);
		panel_orderer.add(txtf_orderer);
		txtf_orderer.setColumns(10);
		
		JPanel panel_order_time = new JPanel();
		panel_order_details.add(panel_order_time);
		
		JLabel lblOrdertime = new JLabel("OrderTime");
		panel_order_time.add(lblOrdertime);
		
		txtf_ordertime = new JTextField();
		txtf_ordertime.setEditable(false);
		panel_order_time.add(txtf_ordertime);
		txtf_ordertime.setColumns(10);
		
		JPanel panel_orderitem = new JPanel();
		panel_order_details.add(panel_orderitem);
		
		JLabel lblItemRequest = new JLabel("ItemRequest");
		panel_orderitem.add(lblItemRequest);
		
		txtf_itemrequest = new JTextField();
		txtf_itemrequest.setEditable(false);
		panel_orderitem.add(txtf_itemrequest);
		txtf_itemrequest.setColumns(10);
		
		JPanel panel_value = new JPanel();
		panel_order_details.add(panel_value);
		
		JLabel lblTotal = new JLabel("Total");
		panel_value.add(lblTotal);
		
		txtf_total = new JTextField();
		txtf_total.setEditable(false);
		panel_value.add(txtf_total);
		txtf_total.setColumns(10);
		
		JPanel panel_order_btns = new JPanel();
		panel_order_details.add(panel_order_btns);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_orderdone_action();
			}
		});
		panel_order_btns.add(btnDone);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_orderclose_action();
			}
		});
		panel_order_btns.add(btnClose);
		
		JPanel panel = new JPanel();
		panel_order_interact.add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Launch the application.
	 */
	public static void start(final int uid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopInfoUI window = new ShopInfoUI(uid);
					window.frmShopinfo.setVisible(true);
					window.frmShopinfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopInfoUI window = new ShopInfoUI(3); //3 is just an example
					window.frmShopinfo.setVisible(true);
					window.frmShopinfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
