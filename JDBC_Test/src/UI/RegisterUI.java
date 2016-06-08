package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Kernel.Constants;
import Kernel.RegisterKernel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class RegisterUI {

	private JFrame frame;
	private final RegisterKernel kernel=new RegisterKernel();
	
	private static final String[] usertypes={"","Guest","Shop"};
	private static final String ACCOUNT_CARD="0";
	private static final String GUEST_CARD="1";
	private static final String SHOP_CARD="2";
	private static final String ERROR_ACCOUNT_NAME_BLANK= "Please don't leave account name blank";
	private static final String ERROR_PASSWORD_UNMATCH= "Password unmatched";
	private static final String ERROR_INVALID_STUDENTID= "Invalid StudentID (7 digits)";
	private static final String ERROR_ACCOUNTNAME_USED= "AccountName Used";
	private static final String regex_studentid = "^\\d{7}$";
	private JTextField txtf_acc;
	private JPasswordField pwdf_1;
	private JPasswordField pwdf_re;
	private JPanel panel_card;
	private JPanel panel_accinfo;
	//private JComboBox cbx_usertype;
	private JComboBox<String> cbx_usertype;
	private CardLayout panel_card_layout;
	private JTextField txtf_guest_fullname;
	private JTextField txtf_studentid;
	private JTextField txtf_dept;
	
	private ButtonGroup radioGroup;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JPanel panel_shop_fullname;
	private JTextField txtf_shop_fullname;
	private JTextField txtf_location;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI window = new RegisterUI();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterUI() {
		initialize();
		//DefaultComboBoxModel model = new DefaultComboBoxModel( items );
		//cbx_usertype.setModel(model);
	}

	private void btn_acc_next_action(){
		String selection=(String)cbx_usertype.getSelectedItem();
		if(selection.equals(usertypes[1])){ //Guest
			panel_card_layout.show(panel_card,GUEST_CARD);
		}else if(selection.equals(usertypes[2])){ //Shop
			panel_card_layout.show(panel_card,SHOP_CARD);
		}
		else{
			panel_card_layout.show(panel_card,ACCOUNT_CARD);
		}
	}
	
	private void btn_guest_prev_action(){
		panel_card_layout.show(panel_card,ACCOUNT_CARD);
	}
	
	private void btn_guest_submit_action(){
		String fullname=txtf_guest_fullname.getText();
		String studentid=txtf_studentid.getText();
		String dept=txtf_dept.getText();
		boolean gender=(rdbtnMale.isSelected())?Constants.MALE:Constants.FEMALE;
		
		if(!studentid.matches(regex_studentid)){
			JOptionPane.showMessageDialog(frame, ERROR_INVALID_STUDENTID, "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int uid=create_account(Constants.GUEST);
		if(uid<0){ //<0 == create failed
			return;
		}
		//create account
		kernel.registerGuest(uid,new RegisterKernel.Guest(fullname, studentid, dept, gender));
		JOptionPane.showMessageDialog(frame, "Welcome, Please login again","Welcome",JOptionPane.INFORMATION_MESSAGE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		LoginUI.main(null);
	}
	
	private void btn_shop_prev_action(){
		panel_card_layout.show(panel_card,ACCOUNT_CARD);
	}
	
	private void btn_shop_submit_action(){
		String fullname=txtf_shop_fullname.getText();
		String location=txtf_location.getText();
		int uid=create_account(Constants.SHOP);
		if(uid<0){ //<0 == create failed
			return;
		}
		//create account
		kernel.registerShop(uid,new RegisterKernel.Shop(fullname, location));
		JOptionPane.showMessageDialog(frame, "Welcome, Please login again","Welcome",JOptionPane.INFORMATION_MESSAGE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		LoginUI.main(null);
	}
	
	private int create_account(int type){
		int uid=-1;
		String acc=txtf_acc.getText();
		String pwd=new String(pwdf_1.getPassword());
		if(acc.equals("")){
			JOptionPane.showMessageDialog(frame, ERROR_ACCOUNT_NAME_BLANK, "Error",JOptionPane.ERROR_MESSAGE);
			return uid;
		}
		if(!pwd.equals(new String(pwdf_re.getPassword()))){
			JOptionPane.showMessageDialog(frame, ERROR_PASSWORD_UNMATCH, "Error",JOptionPane.ERROR_MESSAGE);
			return uid;
		}
		if(kernel.accountName_Used(acc)){
			JOptionPane.showMessageDialog(frame, ERROR_ACCOUNTNAME_USED, "Error",JOptionPane.ERROR_MESSAGE);
			return uid;
		}
		uid=kernel.createAccount(new RegisterKernel.Account(acc,pwd,type));
		return uid;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Calibri", Font.BOLD, 24));
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblRegister, BorderLayout.NORTH);
		
		panel_card = new JPanel();
		panel_card_layout= new CardLayout();
		frame.getContentPane().add(panel_card, BorderLayout.CENTER);
		panel_card.setLayout(panel_card_layout);
		
		panel_accinfo = new JPanel();
		panel_card.add(panel_accinfo, ACCOUNT_CARD);
		panel_accinfo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),"Account Info"));
		panel_accinfo.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_acc = new JPanel();
		panel_accinfo.add(panel_acc);
		
		JLabel lblAccountName = new JLabel("Account Name");
		panel_acc.add(lblAccountName);
		
		txtf_acc = new JTextField();
		panel_acc.add(txtf_acc);
		txtf_acc.setColumns(10);
		
		JPanel panel_pwd = new JPanel();
		panel_accinfo.add(panel_pwd);
		
		JLabel lblPassword = new JLabel("Password");
		panel_pwd.add(lblPassword);
		
		pwdf_1 = new JPasswordField();
		panel_pwd.add(pwdf_1);
		pwdf_1.setColumns(10);
		
		JPanel panel_repwd = new JPanel();
		panel_accinfo.add(panel_repwd);
		
		JLabel lblReassurePwd = new JLabel("ReEnter Pwd");
		panel_repwd.add(lblReassurePwd);
		
		pwdf_re = new JPasswordField();
		panel_repwd.add(pwdf_re);
		pwdf_re.setColumns(10);
		
		JPanel panel_usertype = new JPanel();
		panel_accinfo.add(panel_usertype);
		
		JLabel lblUsertype = new JLabel("UserType");
		panel_usertype.add(lblUsertype);
		
		cbx_usertype = new JComboBox<String>(usertypes);
		//cbx_usertype = new JComboBox<Item>();
		//cbx_usertype = new JComboBox();
		cbx_usertype.setMaximumRowCount(3);
		
		
		panel_usertype.add(cbx_usertype);
		
		JPanel panel_accbtns = new JPanel();
		panel_accinfo.add(panel_accbtns);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_acc_next_action();
			}
		});
		panel_accbtns.add(btnNext);
		
		JPanel panel_guest = new JPanel();
		panel_card.add(panel_guest, GUEST_CARD);
		panel_guest.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),"Register Guest"));
		panel_guest.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_guest_fullname = new JPanel();
		panel_guest.add(panel_guest_fullname);
		
		JLabel lblFullname = new JLabel("FullName");
		panel_guest_fullname.add(lblFullname);
		
		txtf_guest_fullname = new JTextField();
		panel_guest_fullname.add(txtf_guest_fullname);
		txtf_guest_fullname.setColumns(10);
		
		
		JPanel panel_gender = new JPanel();
		panel_guest.add(panel_gender);
		
		JLabel lblGender = new JLabel("Gender");
		panel_gender.add(lblGender);
		
		
		rdbtnMale = new JRadioButton("Male",true);
		panel_gender.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("Female",false);
		panel_gender.add(rdbtnFemale);
		radioGroup=new ButtonGroup();
		radioGroup.add(rdbtnMale);
		radioGroup.add(rdbtnFemale);
		
		
		
		JPanel panel_studentid = new JPanel();
		panel_guest.add(panel_studentid);
		
		JLabel lblStudentId = new JLabel("Student ID");
		panel_studentid.add(lblStudentId);
		
		txtf_studentid = new JTextField();
		panel_studentid.add(txtf_studentid);
		txtf_studentid.setColumns(10);
		
		JPanel panel_dept = new JPanel();
		panel_guest.add(panel_dept);
		
		JLabel lblDepartment = new JLabel("Department");
		panel_dept.add(lblDepartment);
		
		txtf_dept = new JTextField();
		panel_dept.add(txtf_dept);
		txtf_dept.setColumns(10);
		
		JPanel panel_guest_sub = new JPanel();
		panel_guest.add(panel_guest_sub);
		
		JButton btn_guest_Prev = new JButton("Prev");
		btn_guest_Prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_guest_prev_action();
			}
		});
		panel_guest_sub.add(btn_guest_Prev);
		
		JButton btn_guest_Submit = new JButton("Submit");
		btn_guest_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_guest_submit_action();
			}
		});
		panel_guest_sub.add(btn_guest_Submit);
		
		JPanel panel_shop = new JPanel();
		panel_card.add(panel_shop, SHOP_CARD);
		panel_shop.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),"Register Shop"));
		panel_shop.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_shop_fullname = new JPanel();
		panel_shop.add(panel_shop_fullname);
		
		JLabel lblFullname_1 = new JLabel("Fullname");
		panel_shop_fullname.add(lblFullname_1);
		
		txtf_shop_fullname = new JTextField();
		panel_shop_fullname.add(txtf_shop_fullname);
		txtf_shop_fullname.setColumns(10);
		
		JPanel panel_location = new JPanel();
		panel_shop.add(panel_location);
		
		JLabel lblLocation = new JLabel("Location");
		panel_location.add(lblLocation);
		
		txtf_location = new JTextField();
		panel_location.add(txtf_location);
		txtf_location.setColumns(10);
		
		JPanel panel_dummy1 = new JPanel();
		panel_shop.add(panel_dummy1);
		
		JPanel panel_dummy2 = new JPanel();
		panel_shop.add(panel_dummy2);
		
		JPanel panel_shop_sub = new JPanel();
		panel_shop.add(panel_shop_sub);
		
		JButton btn_shop_Prev = new JButton("Prev");
		btn_shop_Prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_shop_prev_action();
			}
		});
		panel_shop_sub.add(btn_shop_Prev);
		
		JButton btn_shop_Submit = new JButton("Submit");
		btn_shop_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_shop_submit_action();
			}
		});
		panel_shop_sub.add(btn_shop_Submit);
	}

}
