package UI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Kernel.GuestInfoKernel;
import Kernel.ShopInfoKernel;
import Kernel.Constants;
import Kernel.Constants.Item;



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
	
	private ShopInfoKernel skernel;
	
	
/////my revise
	private JButton enter_in_frame;
	
	
	
	
	/////
	
	JTextArea abc;
	private JTable table;
	private DefaultTableModel data;
	private JTable what_you_want_to_order;
	private JTextField money_1;
	private JTextField amount_1;
	
	JComboBox<ShopInfoKernel> shop_comboBox_1;
	//<String> shop_comboBox_1;
	JComboBox<Item> item_comboBox_1;
	ArrayList<ShopInfoKernel> shoplist;
	
	/////
	
	
	/**
	 * Create the application.
	 */
	public GuestInfoUI(int uid) {
		this.uid=uid;
		kernel=new GuestInfoKernel();
		
		skernel=new ShopInfoKernel();
		
		initialize();
		update();
	}
	private void update(){
		//new DefaultComboBoxModel(shoplist.toArray(new ShopInfoKernel[1]));
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
		new_order.add(new_order_center_board, BorderLayout.EAST);
		new_order_center_board.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel new_order_controlboard = new JPanel();//////////////////////////////////////////////////////JPanel

		
		
		
		
		
		
		
		
		
		new_order_controlboard.setLayout(new BorderLayout(0, 0));
		/////----------------------------------------------------------------------------------------------------------------------------------------------------
		
		//what_you_want_to_order = new JTable(new MyTableModel());
		JScrollPane what_you_want_to_order_Scroll = new JScrollPane(what_you_want_to_order);/////////////////////////////JTable
		//initColumnSizes(what_you_want_to_order);///自定義函數，內容從line85開始    目的是重新設定所有格子的個欄項比例

        //Fiddle with the Sport column's cell editors/renderers.
        //setUpSportColumn(what_you_want_to_order, what_you_want_to_order.getColumnModel().getColumn(2));///自定義函數，內容從line120開始  目的是將其中一欄格式用 combo box 表現

		
		
		
		new_order_controlboard.add(what_you_want_to_order_Scroll, BorderLayout.CENTER);
		//// new order table

		
		
		
		JPanel new_order_confirm_board = new JPanel();
		new_order_center_board.add(new_order_confirm_board);
		new_order_confirm_board.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel total_money = new JPanel();
		new_order_confirm_board.add(total_money);
		
		final JTextArea txtrTotalMoney = new JTextArea();
		txtrTotalMoney.setText("total money\r\n?????-->????$\r\n?????-->????$      \r\n?????-->????$\r\n?????-->????$\r\n\r\ntotal -->????$\r\n");
		total_money.add(txtrTotalMoney);
		
		JPanel confirm = new JPanel();
		new_order_confirm_board.add(confirm);
		
		Button confirm_order = new Button("confirm");
		confirm.add(confirm_order);
		confirm_order.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int g_id = kernel.getGid();
						int s_id = shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getS_id();
						int i_id = shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getI_id();
						int quant= Integer.parseInt(amount_1.getText());
						
						txtrTotalMoney.setText(
								"uid = "+ uid+"         "
								+"\ngid = " + kernel.getGid()
								+"\nsid = " + shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getS_id()
								+"\niid = " + shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getI_id()
								+"\nquant = " + amount_1.getText()
						);
						kernel.insertOrder(g_id, s_id, i_id, quant);
					}
				}
		);
		
		//Button update_order_list = new Button("update");
		//confirm.add(update_order_list);
		
		JPanel input_area = new JPanel();
		new_order.add(input_area, BorderLayout.CENTER);
		input_area.setLayout(new BorderLayout(0, 0));
		
		JPanel control_button = new JPanel();
		input_area.add(control_button, BorderLayout.SOUTH);
		
		JButton calculate = new JButton("calculate");
		control_button.add(calculate);
		calculate.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						txtrTotalMoney.setText("total money      \r\n"+
							money_1.getText()+" * "+amount_1.getText()+" = "+(Integer.parseInt(money_1.getText())*Integer.parseInt(amount_1.getText()))+'\n'+
						
							"total\n"+(Integer.parseInt(money_1.getText())*Integer.parseInt(amount_1.getText()))
						);
					
					}
				}
		);
		
		
		JPanel order_table = new JPanel();
		input_area.add(order_table, BorderLayout.CENTER);
		order_table.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		order_table.add(panel_1);
		
		
		shoplist=new ArrayList<ShopInfoKernel>();
		kernel.input_all_shop_name_into_combobox(shoplist);;
		/*
		ShopInfoKernel shop1=new ShopInfoKernel();
		shop1.GetInfo(3);
		shoplist.add(shop1);
		
		ShopInfoKernel shop2=new ShopInfoKernel();
		shop2.GetInfo(4);
		shoplist.add(shop2);*/
		
		
		shop_comboBox_1 = new JComboBox <ShopInfoKernel> (shoplist.toArray(new ShopInfoKernel[shoplist.size()]));//5改成shop數      ex   shoplist length
		panel_1.add(shop_comboBox_1);
		
		shop_comboBox_1.addItemListener(
				new ItemListener(){
					@Override
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
					    	//kernel.input_all_item_of_the_shop_name_into_combobox(shop_comboBox_1,item_comboBox_1,shop_comboBox_1.getSelectedIndex());
							ShopInfoKernel sk=(ShopInfoKernel)event.getItem();
							
							Item[] items=sk.getItemList();
							
//							item_comboBox_1 = new JComboBox <Item> (sk.getItemList());
							item_comboBox_1.removeAllItems();

							for(Item i : items){
								System.out.println(i.getFullname()+String.valueOf(i.getValue()));
								item_comboBox_1.addItem(i);
								
							}
							
						}
					}
					
				}
		);		
		
		//shoplist=new ArrayList<ShopInfoKernel>();
		//shoplist.add(shop1);
		//shoplist.add(shop2);
		
		
		//DefaultComboBoxModel model=new DefaultComboBoxModel(shoplist.toArray(new ShopInfoKernel[1]));
		//shop_comboBox_1.setModel(new DefaultComboBoxModel(shoplist.toArray(new ShopInfoKernel[5])));
		//shop_comboBox_1 = new JComboBox <String>();
		//setUpComboBoxList(shop_comboBox_1,new String []{"None"});
		/*
    	kernel.input_all_shop_name_into_combobox(skernel,shop_comboBox_1);
		panel_1.add(shop_comboBox_1);
		*/

		
		item_comboBox_1 = new JComboBox <Item> (shoplist.get(0).getItemList());
		//setUpComboBoxList(item_comboBox_1,new String []{"None"});		
		panel_1.add(item_comboBox_1);
		
		item_comboBox_1.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
					    	//kernel.input_all_item_of_the_shop_name_into_combobox(shop_comboBox_1,item_comboBox_1,shop_comboBox_1.getSelectedIndex());
							Item item_pointer=(Item)event.getItem();
							money_1.setText(String.valueOf(item_pointer.getValue()));
							
						}
					}
				}
		);

		
		money_1 = new JTextField();
		money_1.setHorizontalAlignment(SwingConstants.RIGHT);
		money_1.setText(String.valueOf(shoplist.get(0).getItemList()[0].getValue()));
		money_1.setEditable(false);
		panel_1.add(money_1);
		money_1.setColumns(3);
		
		
		JLabel sign_of_money = new JLabel("$ per item   ");
		panel_1.add(sign_of_money);
		
		
		amount_1 = new JTextField();
		amount_1.setHorizontalAlignment(SwingConstants.RIGHT);
		amount_1.setText("0");
		panel_1.add(amount_1);
		amount_1.setColumns(3);

		JLabel sign_of_items = new JLabel("items   ");
		panel_1.add(sign_of_items);
		
		JCheckBox add_1 = new JCheckBox("add");
		panel_1.add(add_1);
		
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
	public void setUpComboBoxList(JComboBox<String> list,String []input){///輸入input String，得到該欄位按下combo box 所顯示的list
    	///if(list.getComponentCount()==0){
    	list.removeAllItems();
    	///}
    	for(String a : input){
    		list.addItem(a);
    	}
    	
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
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
