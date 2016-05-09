package UI;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import Kernel.GuestInfoKernel;
import Kernel.Constants;
public class GuestInfoUI {
	
	private GuestInfoKernel kernel;
	private JFrame frame;
	private JLabel lbl_title;
	private JTextField txtf_Fullname;
	private JTextField txtf_Studentid;
	private JTextField txtf_dept;
	private ButtonGroup radioGroup;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	/**
	 * Create the application.
	 */
	public GuestInfoUI(int uid) {
		kernel=new GuestInfoKernel();
		initialize();
		kernel.GetInfo(uid);
		update();
	}
	private void update(){
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
		pan_studentid.add(txtf_Studentid);
		txtf_Studentid.setColumns(10);
		
		JPanel pan_dept = new JPanel();
		pan_info.add(pan_dept);
		
		JLabel lbl_dept = new JLabel("Department");
		pan_dept.add(lbl_dept);
		
		txtf_dept = new JTextField();
		pan_dept.add(txtf_dept);
		txtf_dept.setColumns(10);
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
