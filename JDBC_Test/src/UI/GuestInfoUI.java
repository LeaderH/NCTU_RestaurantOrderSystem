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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl_title = new JLabel("Welcome!");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbl_title, BorderLayout.NORTH);
		
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
		
		JPanel pan_dept = new JPanel();
		pan_info.add(pan_dept);
		
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
