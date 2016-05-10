package UI;

import java.awt.EventQueue;

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
	private ShopInfoKernel kernel;
	private JFrame frame;
	private JTextField txtf_name;
	private JTextField txtf_loc;
	private JList<String>	list_item;
	private JPanel panel_display;
	private JTextField txtf_item_name;
	private JTextField txtf_item_value;
	
	private final CardLayout display=new CardLayout(0, 0);
	private final String DEFAULT_CARD="deafult";
	private final String ITEM_DETAIL_CARD="item_selected";
	/**
	 * Create the application.
	 */
	public ShopInfoUI(int uid) {
		this.uid=uid;
		kernel=new ShopInfoKernel();
		initialize();
		update();
	}
	
	private void update(){
		kernel.GetInfo(uid); //refresh
		txtf_name.setText(kernel.getFullname());
		txtf_loc.setText(kernel.getLocation());
		Item[] List=kernel.getItemList();
		ArrayList<String> arr=new ArrayList<String>();
		for(Item I : List){
			if(I.isAvailable())
				arr.add(String.format("%s(%d)", I.getFullname(),I.getValue()));
		}
		list_item.setListData(arr.toArray(new String[1]));
		
	}
	private void btn_detail_action(){
		//System.out.println("fff");
		display.show(panel_display, ITEM_DETAIL_CARD);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
 	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbl_title = new JLabel("Welcome");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbl_title, BorderLayout.NORTH);
		
		JPanel panel_main = new JPanel();
		frame.getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_info = new JPanel();
		panel_info.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_main.add(panel_info);
		panel_info.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_info.add(lblInfo, BorderLayout.NORTH);
		
		JPanel panel_detail = new JPanel();
		panel_info.add(panel_detail, BorderLayout.CENTER);
		panel_detail.setLayout(new BoxLayout(panel_detail, BoxLayout.Y_AXIS));
		
		JPanel panel_name = new JPanel();
		panel_detail.add(panel_name);
		
		JLabel lblName = new JLabel("Name");
		panel_name.add(lblName);
		
		txtf_name = new JTextField();
		txtf_name.setEditable(false);
		panel_name.add(txtf_name);
		txtf_name.setColumns(10);
		
		JPanel panel_location = new JPanel();
		panel_detail.add(panel_location);
		
		JLabel lblLocation = new JLabel("Location");
		panel_location.add(lblLocation);
		
		txtf_loc = new JTextField();
		txtf_loc.setEditable(false);
		panel_location.add(txtf_loc);
		txtf_loc.setColumns(10);
		
		JPanel panel_item = new JPanel();
		panel_detail.add(panel_item);
		panel_item.setLayout(new BorderLayout(0, 0));
		
		
		list_item=new JList<String>();
		panel_item.add(new JScrollPane(list_item),BorderLayout.CENTER);
		
		JPanel panel_btns = new JPanel();
		panel_detail.add(panel_btns);
		
		
		panel_display = new JPanel();
		panel_main.add(panel_display);
		panel_display.setLayout(display);
		
		display.show(panel_display, DEFAULT_CARD);
		
		JPanel panel_card_default = new JPanel();
		panel_card_default.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_display.add(panel_card_default, "default");
		panel_card_default.setLayout(new BorderLayout(0, 0));
		
		JLabel lbl_SelAct = new JLabel("Please Select an action");
		lbl_SelAct.setHorizontalAlignment(SwingConstants.CENTER);
		panel_card_default.add(lbl_SelAct, BorderLayout.CENTER);
		
		JPanel panel_card1 = new JPanel();
		panel_card1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_display.add(panel_card1, "item_selected");
		panel_card1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblItemDetail = new JLabel("Item Detail");
		lblItemDetail.setFont(new Font("Calibri", Font.BOLD, 20));
		lblItemDetail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_card1.add(lblItemDetail, BorderLayout.NORTH);
		
		JPanel panel_itemdetail = new JPanel();
		panel_card1.add(panel_itemdetail, BorderLayout.CENTER);
		panel_itemdetail.setLayout(new BoxLayout(panel_itemdetail, BoxLayout.Y_AXIS));
		
		JPanel panel_item_name = new JPanel();
		panel_itemdetail.add(panel_item_name);
		
		JLabel lblItemname = new JLabel("ItemName");
		panel_item_name.add(lblItemname);
		
		txtf_item_name = new JTextField();
		panel_item_name.add(txtf_item_name);
		txtf_item_name.setColumns(10);
		
		JPanel panel_item_v = new JPanel();
		panel_itemdetail.add(panel_item_v);
		
		JLabel lblItemValue = new JLabel("Value");
		panel_item_v.add(lblItemValue);
		
		txtf_item_value = new JTextField();
		panel_item_v.add(txtf_item_value);
		txtf_item_value.setColumns(10);
		
		JPanel panel_des = new JPanel();
		panel_itemdetail.add(panel_des);
		panel_des.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		panel_des.add(lblDescription, BorderLayout.NORTH);
		
		JTextArea textArea_item_des = new JTextArea();
		panel_des.add(textArea_item_des, BorderLayout.CENTER);
		
		JPanel panel_item_btns = new JPanel();
		panel_card1.add(panel_item_btns, BorderLayout.SOUTH);
		
		JButton btnEdit = new JButton("Edit");
		panel_item_btns.add(btnEdit);
		
		JButton btn_itemdetail = new JButton("Detail");
		btn_itemdetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_detail_action();
			}
		});
		panel_btns.add(btn_itemdetail);
		
		JButton btn_itemadd = new JButton("Add");
		panel_btns.add(btn_itemadd);
		
		
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
