package UI;

import java.awt.EventQueue;
import javax.swing.*;
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
	
	
	private JFrame optionJframe;
	private JButton revisedata;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	
	/////
	
	JTextArea abc;
	
	
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
		frame.setBounds(100, 100, 868, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl_title = new JLabel("Welcome!");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbl_title, BorderLayout.NORTH);
		
		JPanel panel_for_testing = new JPanel();
		frame.getContentPane().add(panel_for_testing, BorderLayout.WEST);
		
		JTabbedPane JTab_for_testing = new JTabbedPane(JTabbedPane.TOP);
		panel_for_testing.add(JTab_for_testing);
		
		JPanel user_information = new JPanel();
		JTab_for_testing.addTab("user information", null, user_information, null);
		user_information.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		user_information.add(panel_4);
		
		JLabel label = new JLabel("Fullname");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(true);
		textField.setColumns(10);
		panel_4.add(textField);
		
		JPanel panel_5 = new JPanel();
		user_information.add(panel_5);
		
		JLabel label_1 = new JLabel("Gender");
		panel_5.add(label_1);
		
		JRadioButton radioButton = new JRadioButton("Male", true);
		panel_5.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Female", false);
		panel_5.add(radioButton_1);
		
		JPanel panel_6 = new JPanel();
		user_information.add(panel_6);
		
		JLabel label_2 = new JLabel("StudentID");
		panel_6.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEditable(true);
		textField_1.setColumns(10);
		panel_6.add(textField_1);
		
		JPanel panel_7 = new JPanel();
		user_information.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		JLabel label_3 = new JLabel("Department");
		panel_8.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setEditable(true);
		textField_2.setColumns(10);
		panel_8.add(textField_2);
		
		JButton button = new JButton("Enter");
		panel_8.add(button);
		
		JPanel check_the_order = new JPanel();
		JTab_for_testing.addTab("New tab", null, check_the_order, null);
		check_the_order.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("here is the order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		check_the_order.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel the_order = new JPanel();
		check_the_order.add(the_order);
		
		JTextArea textArea = new JTextArea();
		the_order.add(textArea);
		textArea.setText("sjlkafj;kla");
		

		abc =  textArea;/////don forget this variable
		JButton btnGetData = new JButton("get data");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp =kernel.getOrderSyntex();
				abc.setText(tmp);
			}
		});
		the_order.add(btnGetData);
		
		JPanel panel_2 = new JPanel();
		JTab_for_testing.addTab("New tab", null, panel_2, null);
		
		JLabel lblNewLabel_1 = new JLabel("random code sjakdlfjlsdjf;askjdf;j;alskjdf;lj");
		panel_2.add(lblNewLabel_1);
		
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
		
		
		
		
		
		
		
//////  1.set 兩層          2.更新資料          3.放訂單
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
		
		
		
		
		frame.setVisible(false);
		
		
		
		optionJframe = new JFrame();
		optionJframe.setBounds(100, 100, 450, 300);
		
		optionJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		optionJframe.getContentPane().add(panel1, BorderLayout.CENTER);
		panel1.setLayout(new GridLayout(3, 0, 0, 0));
		
		revisedata = new JButton("revise");
		
		
		panel1.add(revisedata);
		
		
		revisedata.addActionListener(///Anonymous inner class
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event){
						frame.setVisible(true);
					}
			}
		);
		//////  1.set 兩層          2.更新資料          3.放訂單     
		
		
		
		
	}
	/**
	 * Launch the application.
	 */
	public static void start(final int uid) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestInfoUI window = new GuestInfoUI(uid);
					//window.frame.setVisible(true);
					window.optionJframe.setVisible(true);///////////////////////revise
					
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
					
					window.optionJframe.setVisible(true);///////////////////////revise
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
