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

public class ShopInfoUI {
	private int uid;
	private ShopInfoKernel kernel;
	private JFrame frame;
	private JTextField txtf_name;
	private JTextField txtf_loc;
	private JList<String>	list_item;
	//private JPanel panel_display;
	
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
		list_item.setBorder(new CompoundBorder(new CompoundBorder(), null));
		panel_item.add(new JScrollPane(list_item),BorderLayout.CENTER);
		
		JPanel panel_btns = new JPanel();
		panel_detail.add(panel_btns);
		

		JPanel panel_display = new JPanel();
		panel_main.add(panel_display);
		
		JButton btn_itemdetail = new JButton("Detail");
		btn_itemdetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					ShopInfoUI window = new ShopInfoUI(uid); //3 is just example
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
					ShopInfoUI window = new ShopInfoUI(3); //3 is just example
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
