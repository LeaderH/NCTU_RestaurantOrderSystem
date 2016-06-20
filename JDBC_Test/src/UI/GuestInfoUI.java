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

	JComboBox<ShopInfoKernel> shop_comboBox_1;
	JComboBox<Item> item_comboBox_1;
	private JTextField money_1;
	private JTextField amount_1;
	JCheckBox add_1;
	
	//<String> shop_comboBox_1;

	JComboBox<ShopInfoKernel> shop_comboBox_2;
	JComboBox<Item> item_comboBox_2 ;
	private JTextField money_2;
	private JTextField amount_2;
	JCheckBox add_2;
	
	ArrayList<ShopInfoKernel> shoplist;
	JTextArea txtrTotalMoney;/// information text area. It can show item description , total money you might spend and some testing code
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

		///the_order.add(text_area_scroll, BorderLayout.WEST);
		
		
		abc =  textArea;/////don forget this variable
		JButton btnGetData = new JButton("get data");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp =kernel.getOrderSyntex();
				abc.setText(tmp);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						int []selected_int_array = table.getSelectedRows();
						for(int i : selected_int_array){
							if(((String)table.getValueAt(i, 2)).equals("Trade has not done yet!!!")){
								//table.setValueAt(String.valueOf(i), i, 2);
								kernel.delete_order_name_by_oid(Integer.parseInt((String) table.getValueAt(i, 0)));
							}
							
						}
						//table.getSelectedRow()
						//table.getSelectedRows()
					}
				}
		);
		
		JPanel the_panel_for_get_data_and_canecel_button = new JPanel();
		the_panel_for_get_data_and_canecel_button.setLayout(new GridLayout(2, 1, 0, 0));
		the_panel_for_get_data_and_canecel_button.add(btnGetData);
		the_panel_for_get_data_and_canecel_button.add(btnCancel);
		the_order.add(the_panel_for_get_data_and_canecel_button, BorderLayout.EAST);
		


		
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
		
		JLabel lblNewLabel_1 = new JLabel(" shop name               | item name           | money per item     | quant of item  | seleted?");
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
		
		txtrTotalMoney = new JTextArea();
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
						txtrTotalMoney.setText("<the result of sending data>\n\n");
						/*txtrTotalMoney.setText(
								"uid = "+ uid+"         "
								+"\ngid = " + kernel.getGid()
								+"\nsid = " + shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getS_id()
								+"\niid = " + shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_1.getSelectedIndex()].getItemList()[item_comboBox_1.getSelectedIndex()].getI_id()
								+"\nquant = " + amount_1.getText()
						);*/
						//txtrTotalMoney.setText(	kernel.find_shop_name_by_sid(1) );
						if(add_1.isSelected()){
							kernel.insertOrder(g_id, s_id, i_id, quant);
							txtrTotalMoney.setText(txtrTotalMoney.getText()+"order1 has been sent!!!\n");
						}
						
						
						g_id = kernel.getGid();
						s_id = shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_2.getSelectedIndex()].getItemList()[item_comboBox_2.getSelectedIndex()].getS_id();
						i_id = shoplist.toArray(new ShopInfoKernel[shoplist.size()])[shop_comboBox_2.getSelectedIndex()].getItemList()[item_comboBox_2.getSelectedIndex()].getI_id();
						quant= Integer.parseInt(amount_2.getText());
						
						if(add_2.isSelected()){
							kernel.insertOrder(g_id, s_id, i_id, quant);
							txtrTotalMoney.setText(txtrTotalMoney.getText()+"order2 has been sent!!!\n");
						}
						txtrTotalMoney.setText(txtrTotalMoney.getText()+"\n\n\n");

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
						txtrTotalMoney.setText("   <calculate result>      \r\n\n");
						if(add_1.isSelected()){
							txtrTotalMoney.setText(txtrTotalMoney.getText()+		
							money_1.getText()+" * "+amount_1.getText()+" = "+(Integer.parseInt(money_1.getText())*Integer.parseInt(amount_1.getText()))+'\n'
									
							);
						}else{
							txtrTotalMoney.setText(txtrTotalMoney.getText()+"0\n");
						}

						if(add_2.isSelected()){
							txtrTotalMoney.setText(txtrTotalMoney.getText()+		
							money_2.getText()+" * "+amount_2.getText()+" = "+(Integer.parseInt(money_2.getText())*Integer.parseInt(amount_2.getText()))+'\n'
							);
						}else{
							txtrTotalMoney.setText(txtrTotalMoney.getText()+"0\n");
						}
						
						
						int total_money_spend = 0;
						if(add_1.isSelected()){
							total_money_spend += Integer.parseInt(money_1.getText())*Integer.parseInt(amount_1.getText());
						}
						if(add_2.isSelected()){
							total_money_spend += Integer.parseInt(money_2.getText())*Integer.parseInt(amount_2.getText());
						}

						txtrTotalMoney.setText(txtrTotalMoney.getText()+
							"\n   <total money you spend>\n"+	total_money_spend +"\n\n\n"
						);
						
					}
				}
		);
		
		
		JPanel order_table = new JPanel();
		input_area.add(order_table, BorderLayout.CENTER);
		order_table.setLayout(new GridLayout(5, 1, 0, 0));
		
		
		
		

		shoplist=new ArrayList<ShopInfoKernel>();
		kernel.input_all_shop_name_into_combobox(shoplist);;

		
		JPanel panel_1 = new JPanel();
		order_table.add(panel_1);
		
			
		shop_comboBox_1 = new JComboBox <ShopInfoKernel> (shoplist.toArray(new ShopInfoKernel[shoplist.size()]));//5改成shop數      ex   shoplist length
		panel_1.add(shop_comboBox_1);
		
		shop_comboBox_1.addItemListener(
				new ItemListener(){
					@Override
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){

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
		

		
		item_comboBox_1 = new JComboBox <Item> (shoplist.get(0).getItemList());
		//setUpComboBoxList(item_comboBox_1,new String []{"None"});		
		panel_1.add(item_comboBox_1);
		
		txtrTotalMoney.setText("   <item desciption>\n\n"+shoplist.get(0).getItemList()[0].getDescription()+"\n\n\n");
		
		
		item_comboBox_1.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
					    	//kernel.input_all_item_of_the_shop_name_into_combobox(shop_comboBox_1,item_comboBox_1,shop_comboBox_1.getSelectedIndex());
							Item item_pointer=(Item)event.getItem();
							money_1.setText(String.valueOf(item_pointer.getValue()));
							
							txtrTotalMoney.setText("   <item desciption>\n\n");
							
							txtrTotalMoney.setText(txtrTotalMoney.getText()+item_pointer.getDescription()+"\n\n\n");
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
		
		
		JLabel sign_of_money_1 = new JLabel("$ per item   ");
		panel_1.add(sign_of_money_1);
		
		
		amount_1 = new JTextField();
		amount_1.setHorizontalAlignment(SwingConstants.RIGHT);
		amount_1.setText("1");
		panel_1.add(amount_1);
		amount_1.setColumns(3);

		JLabel sign_of_items_1 = new JLabel("items   ");
		panel_1.add(sign_of_items_1);
		
		add_1 = new JCheckBox("add");
		add_1.setSelected(true);
		panel_1.add(add_1);
		
		
		
		
		
		JPanel panel_2 = new JPanel();
		order_table.add(panel_2);
		

		shop_comboBox_2 = new JComboBox <ShopInfoKernel> (shoplist.toArray(new ShopInfoKernel[shoplist.size()]));//5改成shop數      ex   shoplist length
		panel_2.add(shop_comboBox_2);
		
		shop_comboBox_2.addItemListener(
				new ItemListener(){
					@Override
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){

							ShopInfoKernel sk=(ShopInfoKernel)event.getItem();
							
							Item[] items=sk.getItemList();
							
//							item_comboBox_1 = new JComboBox <Item> (sk.getItemList());
							item_comboBox_2.removeAllItems();
							for(Item i : items){
								System.out.println(i.getFullname()+String.valueOf(i.getValue()));
								item_comboBox_2.addItem(i);
								
							}
							
						}
					}
					
				}
		);		
		

		
		item_comboBox_2 = new JComboBox <Item> (shoplist.get(0).getItemList());
		//setUpComboBoxList(item_comboBox_1,new String []{"None"});		
		panel_2.add(item_comboBox_2);
		
		txtrTotalMoney.setText("   <item desciption>\n\n"+shoplist.get(0).getItemList()[0].getDescription()+"\n\n\n");
		
		
		item_comboBox_2.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
					    	//kernel.input_all_item_of_the_shop_name_into_combobox(shop_comboBox_1,item_comboBox_1,shop_comboBox_1.getSelectedIndex());
							Item item_pointer=(Item)event.getItem();
							money_2.setText(String.valueOf(item_pointer.getValue()));
							
							txtrTotalMoney.setText("   <item desciption>\n\n");
							
							txtrTotalMoney.setText(txtrTotalMoney.getText()+item_pointer.getDescription()+"\n\n\n");
						}
					}
				}
		);

		
		money_2 = new JTextField();
		money_2.setHorizontalAlignment(SwingConstants.RIGHT);
		money_2.setText(String.valueOf(shoplist.get(0).getItemList()[0].getValue()));
		money_2.setEditable(false);
		panel_2.add(money_2);
		money_2.setColumns(3);
		
		
		JLabel sign_of_money_2 = new JLabel("$ per item   ");
		panel_2.add(sign_of_money_2);
		
		
		amount_2 = new JTextField();
		amount_2.setHorizontalAlignment(SwingConstants.RIGHT);
		amount_2.setText("1");
		panel_2.add(amount_2);
		amount_2.setColumns(3);

		JLabel sign_of_items_2 = new JLabel("items   ");
		panel_2.add(sign_of_items_2);
		
		add_2 = new JCheckBox("add");
		add_2.setSelected(true);
		panel_2.add(add_2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		////panel for user information!!!
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
