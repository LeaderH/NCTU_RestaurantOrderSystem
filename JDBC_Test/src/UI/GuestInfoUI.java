package UI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import Kernel.GuestInfoKernel;
import Kernel.Constants;



public class GuestInfoUI {
	private int uid;
	private GuestInfoKernel kernel;
	private JFrame frame;
	private JLabel lbl_title;
	private JTextField txtf_Fullname;
	private JTextField txtf_Studentid;
	private JTextField txtf_dept;
	private ButtonGroup radioGroup;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	
	
	
	
/////my revise
	private JButton enter_in_frame;
	
	
	
	
	/////
	
	JTextArea abc;
	private JTable table;
	private DefaultTableModel data;
	private JTable what_you_want_to_order;
	
	/////
	
	
	/**
	 * Create the application.
	 */
	public GuestInfoUI(int uid) {
		this.uid=uid;
		kernel=new GuestInfoKernel();
		
		initialize();
		update();
	}
	private void update(){
		kernel.GetInfo(uid); //refresh
		txtf_Fullname.setText(kernel.getFullname());
		txtf_Studentid.setText(kernel.getStudentid());
		txtf_dept.setText(kernel.getDept());
		if(kernel.getGender()==Constants.FEMALE){
			rdbtnFemale.setSelected(true);
			rdbtnMale.setSelected(false);
		}
		else{
			rdbtnFemale.setSelected(false);
			rdbtnMale.setSelected(true);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl_title = new JLabel("Welcome!");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbl_title, BorderLayout.NORTH);
		
		JPanel panel_for_testing = new JPanel();
		frame.getContentPane().add(panel_for_testing, BorderLayout.WEST);
		
		JTabbedPane JTab_for_testing = new JTabbedPane(JTabbedPane.TOP);
		panel_for_testing.add(JTab_for_testing);
		
		JPanel check_the_order = new JPanel();
		JTab_for_testing.addTab("history order", null, check_the_order, null);
		check_the_order.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("here is the order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		check_the_order.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel the_order = new JPanel();
		check_the_order.add(the_order);
		the_order.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setText("sjl;kla\n\n\n\n\n\n\n\n\n\nasdfa");
		JScrollPane text_area_scroll = new JScrollPane(textArea);

		the_order.add(text_area_scroll, BorderLayout.WEST);
		
		
		abc =  textArea;/////don forget this variable
		JButton btnGetData = new JButton("get data");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp =kernel.getOrderSyntex();
				abc.setText(tmp);
			}
		});
		
		the_order.add(btnGetData, BorderLayout.EAST);
		
		
		///table setting
		
		///data = new DefaultTableModel();
		
		///data.addColumn("o_id ");  data.addColumn("s_id  ");  data.addColumn("isdone ");  data.addColumn("timestmp ");  data.addColumn("i_id  ");  data.addColumn("quant ");
		//data.addRow(new Object[]{"A","B","C","D","E","F"});
		
		table = new JTable(kernel.gettabledata());
		//			 TableColumn
		//the_order.add(table, BorderLayout.CENTER);
		
		
		JScrollPane table_scroll = new JScrollPane(table);
		
		the_order.add(table_scroll,BorderLayout.CENTER);
		//table_scroll.setVisible(false);
		///table setting
		
		
		
		JPanel new_order = new JPanel();
		JTab_for_testing.addTab("I want to order new thing", null, new_order, null);
		new_order.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("random code sjakdlfjlsdjf;askjdf;j;alskjdf;lj");
		new_order.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel new_order_center_board = new JPanel();
		new_order.add(new_order_center_board, BorderLayout.CENTER);
		new_order_center_board.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel new_order_controlboard = new JPanel();
		new_order_center_board.add(new_order_controlboard);
		
		
		
		//// new order table
		DefaultTableModel what_you_want_to_order_DefaultTableModel = new DefaultTableModel();
		what_you_want_to_order_DefaultTableModel.addColumn("Shop Name");
		what_you_want_to_order_DefaultTableModel.addColumn("Item Name");
		what_you_want_to_order_DefaultTableModel.addColumn("quantum");
		
		//what_you_want_to_order_DefaultTableModel.
		
		String Shop_name[] = {"shop1","shop2","shop3"};
		JComboBox Shops =new JComboBox<String>(Shop_name);
		Shops.setMaximumRowCount(Shop_name.length);
		
		
		what_you_want_to_order_DefaultTableModel.addRow(new Object[]{new String[]{"A","B"},"2",true});
		what_you_want_to_order = new JTable(what_you_want_to_order_DefaultTableModel);
		JScrollPane what_you_want_to_order_Scroll = new JScrollPane(what_you_want_to_order);
		
		
		
		
		new_order_controlboard.add(what_you_want_to_order_Scroll);
		//// new order table

		
		
		
		JPanel new_order_confirm_board = new JPanel();
		new_order_center_board.add(new_order_confirm_board);
		new_order_confirm_board.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel total_money = new JPanel();
		new_order_confirm_board.add(total_money);
		
		JTextArea txtrTotalMoney = new JTextArea();
		txtrTotalMoney.setText("total money\r\n?????-->????$\r\n?????-->????$      \r\n?????-->????$\r\n?????-->????$\r\n\r\ntotal -->????$\r\n");
		total_money.add(txtrTotalMoney);
		
		JPanel confirm = new JPanel();
		new_order_confirm_board.add(confirm);
		
		Button confirm_order = new Button("confirm");
		confirm.add(confirm_order);
		
		Button update_order_list = new Button("update");
		confirm.add(update_order_list);
		
		JPanel pan_info = new JPanel();
		frame.getContentPane().add(pan_info, BorderLayout.CENTER);
		pan_info.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel pan_name = new JPanel();
		pan_info.add(pan_name);
		
		JLabel lbl_Fullname = new JLabel("Fullname");
		lbl_Fullname.setHorizontalAlignment(SwingConstants.CENTER);
		pan_name.add(lbl_Fullname);
		
		txtf_Fullname = new JTextField();
		txtf_Fullname.setEditable(true);
		pan_name.add(txtf_Fullname);
		txtf_Fullname.setColumns(10);
		
		JPanel pan_gender = new JPanel();
		pan_info.add(pan_gender);
		
		JLabel lblGender = new JLabel("Gender");
		pan_gender.add(lblGender);
		
		
		rdbtnMale = new JRadioButton("Male",true);
		pan_gender.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("Female",false);
		pan_gender.add(rdbtnFemale);
		radioGroup=new ButtonGroup();
		radioGroup.add(rdbtnMale);
		radioGroup.add(rdbtnFemale);
		
		JPanel pan_studentid = new JPanel();
		pan_info.add(pan_studentid);
		
		JLabel lbl_Studentid = new JLabel("StudentID");
		pan_studentid.add(lbl_Studentid);
		
		txtf_Studentid = new JTextField();
		txtf_Studentid.setEditable(true);
		pan_studentid.add(txtf_Studentid);
		txtf_Studentid.setColumns(10);
		
		JPanel panel = new JPanel();
		pan_info.add(panel);
		
		JPanel pan_dept = new JPanel();
		panel.add(pan_dept);
		
		JLabel lbl_dept = new JLabel("Department");
		pan_dept.add(lbl_dept);
		
		txtf_dept = new JTextField();
		txtf_dept.setEditable(true);
		pan_dept.add(txtf_dept);
		txtf_dept.setColumns(10);
		
		
		
		
		
		
		
///////////////////////////////////
		enter_in_frame = new JButton("Enter");
		pan_dept.add(enter_in_frame);

		enter_in_frame.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event){
						
						kernel.setFullname(txtf_Fullname.getText());
						
						kernel.setStudentid(txtf_Studentid.getText());
						
						kernel.setDept(txtf_dept.getText());
						
						if(rdbtnMale.getSelectedObjects() == null){
							kernel.setGender(true);
						}else{
							kernel.setGender(false);
						}
						
						/*
						public void setGender(boolean gender) {
							if(gender){
								this.gender = Constants.FEMALE;
							}else{
								this.gender = Constants.MALE;
							}
						}*/
						kernel.send_data();
						////////this.GuestInfoUI(uid);
					}
				}
		);
		
		
		
		
		
		
	}
	/**
	 * Launch the application.
	 */
	public static void start(final int uid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestInfoUI window = new GuestInfoUI(uid);
					window.frame.setVisible(true);
					
					
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestInfoUI window = new GuestInfoUI(1);
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
