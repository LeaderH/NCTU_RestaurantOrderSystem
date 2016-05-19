package UI;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import Kernel.ShopInfoKernel;
import Kernel.ShopInfoKernel.Item;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class ShopInfoUI {
	private int uid;
	private Item[] itemList;
	private ShopInfoKernel kernel;
	private JFrame frame;
	private JTextField txtf_name;
	private JTextField txtf_loc;
	private JList<String>	list_item;
	private JTextField txtf_item_name;
	private JTextField txtf_item_value;
	private JTextArea textArea_item_des;
	private JButton btnEdit;
	private JButton btnNew;
	private JButton btnCancel;
	JPanel panel_item_interact;
	
	private JList<String> list_order;
	
	
	private Item item_selected;
	private JTextField txtf_orderer;
	private JTextField txtf_ordertime;
	private JTextField txtf_itemrequest;
	private JTextField txtf_total;
	
	/**
	 * Create the application.
	 */
	public ShopInfoUI(int uid) {
		this.uid=uid;
		itemList=new Item[1];
		kernel=new ShopInfoKernel();
		initialize();
		update();
	}
	
	private void update(){
		kernel.GetInfo(uid); //refresh
		txtf_name.setText(kernel.getFullname());
		txtf_loc.setText(kernel.getLocation());
		itemList=kernel.getItemList();
		ArrayList<String> arr=new ArrayList<String>();
		for(Item I : itemList){
			if(I.isAvailable())
				arr.add(String.format("%s(%d)", I.getFullname(),I.getValue()));
		}
		list_item.setListData(arr.toArray(new String[1]));
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
		Item item_edited=new Item(item_selected.getI_id(),item_selected.getS_id(),
				txtf_item_name.getText(),Integer.parseInt(txtf_item_value.getText()),textArea_item_des.getText(),
				true
				);
		//Item(int iid,int sid,String name,int v,String des,boolean available);
		kernel.updateItemInfo(item_edited);
		update();
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
		update();
	}
	private void btn_delete_action(){
		try{
			item_selected=itemList[list_item.getSelectedIndex()];
			int res=JOptionPane.showConfirmDialog(frame,"Are you sure to delete item "+item_selected.getFullname()+"?",
					"Confirm",JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION){
				Item item_edited=new Item(item_selected.getI_id(),item_selected.getS_id(),
						item_selected.getFullname(),item_selected.getValue(),item_selected.getDescription(),
						false
						);
				//Item(int iid,int sid,String name,int v,String des,boolean available);
				kernel.updateItemInfo(item_edited);
				update();
			}
		}catch(IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(frame, "Please select an item", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btn_cancel_action(){
		panel_item_interact.setVisible(false);
		btnNew.setVisible(false);
		btnEdit.setVisible(true);
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_title = new JLabel("Welcome");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbl_title, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		

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
		gbl_panel_detail.columnWidths = new int[] { frame.getWidth(), 0 };
		gbl_panel_detail.rowHeights = new int[] { 35, 35, 0 };
		gbl_panel_detail.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_detail.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_detail.setLayout(gbl_panel_detail);

		JPanel panel_name = new JPanel();
		GridBagConstraints gbc_panel_name = new GridBagConstraints();
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
		
		JLabel lblOrderlist = new JLabel("Order List");
		lblOrderlist.setHorizontalAlignment(SwingConstants.CENTER);
		panel_orderlist.add(lblOrderlist, BorderLayout.NORTH);
		
		
		list_order = new JList<String>();
		list_order.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(list_order);
		panel_orderlist.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_order_interact = new JPanel();
		panel_order.add(panel_order_interact);
		panel_order_interact.setLayout(new BorderLayout(0, 0));
		
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
		panel_orderer.add(txtf_orderer);
		txtf_orderer.setColumns(10);
		
		JPanel panel_order_time = new JPanel();
		panel_order_details.add(panel_order_time);
		
		JLabel lblOrdertime = new JLabel("OrderTime");
		panel_order_time.add(lblOrdertime);
		
		txtf_ordertime = new JTextField();
		panel_order_time.add(txtf_ordertime);
		txtf_ordertime.setColumns(10);
		
		JPanel panel_orderitem = new JPanel();
		panel_order_details.add(panel_orderitem);
		
		JLabel lblItemRequest = new JLabel("ItemRequest");
		panel_orderitem.add(lblItemRequest);
		
		txtf_itemrequest = new JTextField();
		panel_orderitem.add(txtf_itemrequest);
		txtf_itemrequest.setColumns(10);
		
		JPanel panel_value = new JPanel();
		panel_order_details.add(panel_value);
		
		JLabel lblTotal = new JLabel("Total");
		panel_value.add(lblTotal);
		
		txtf_total = new JTextField();
		panel_value.add(txtf_total);
		txtf_total.setColumns(10);
		
		JPanel panel_order_btns = new JPanel();
		panel_order_details.add(panel_order_btns);
		
		JButton btnDone = new JButton("Done");
		panel_order_btns.add(btnDone);
		
		JButton btnClose = new JButton("Close");
		panel_order_btns.add(btnClose);
	}
	
	/**
	 * Launch the application.
	 */
	public static void start(final int uid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopInfoUI window = new ShopInfoUI(uid);
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
