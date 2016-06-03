package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Kernel.Constants.Item;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class RegisterUI {

	private JFrame frame;
	
	/*
	Item it1=new Item();
	Item it2=new Item();
	Item it3=new Item();
	private  Item[] items={it1,it2,it3};
	private  Item[] item2={it1,it2};
	*/
	
	private static final String[] usertypes={"","Guest","Shop"};
	private static final String ACCOUNT_CARD="0";
	private static final String GUEST_CARD="1";
	private static final String SHOP_CARD="2";
	private JTextField txtf_acc;
	private JPasswordField pwdf_1;
	private JPasswordField pwdf_re;
	private JPanel panel_card;
	private JPanel panel_accinfo;
	//private JPanel panel_guest;
	//private JPanel panel_shop;
	private JComboBox cbx_usertype;
	private CardLayout panel_card_layout;
	private JTextField txtf_fulllname;
	private JTextField txtf_studentid;
	private JTextField txtf_dept;
	
	private ButtonGroup radioGroup;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI window = new RegisterUI();
					window.frame.setVisible(true);
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
		
		//it1.setFullname("A");
		//it2.setFullname("B");
		//it3.setFullname("C");
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
		panel_accinfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_accinfo.setLayout(new BoxLayout(panel_accinfo, BoxLayout.Y_AXIS));
		
		JLabel lblAccountInfo = new JLabel("Account Info");
		panel_accinfo.add(lblAccountInfo);
		
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
		
		JLabel lblReassurePwd = new JLabel("Reassure Pwd");
		panel_repwd.add(lblReassurePwd);
		
		pwdf_re = new JPasswordField();
		panel_repwd.add(pwdf_re);
		pwdf_re.setColumns(10);
		
		JPanel panel_usertype = new JPanel();
		panel_accinfo.add(panel_usertype);
		
		JLabel lblUsertype = new JLabel("UserType");
		panel_usertype.add(lblUsertype);
		
		//cbx_usertype = new JComboBox<String>(usertypes);
		//cbx_usertype = new JComboBox<Item>();
		cbx_usertype = new JComboBox();
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
		
		panel_guest.setLayout(new BoxLayout(panel_guest, BoxLayout.Y_AXIS));
		
		JLabel lblRegisterGuest = new JLabel("Register Guest");
		lblRegisterGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_guest.add(lblRegisterGuest);
		
		JPanel panel_fullname = new JPanel();
		panel_guest.add(panel_fullname);
		
		JLabel lblFullname = new JLabel("FullName");
		panel_fullname.add(lblFullname);
		
		txtf_fulllname = new JTextField();
		panel_fullname.add(txtf_fulllname);
		txtf_fulllname.setColumns(10);
		
		
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
		
		JButton btnPrev = new JButton("Prev");
		panel_guest_sub.add(btnPrev);
		
		JButton btnSubmit = new JButton("Submit");
		panel_guest_sub.add(btnSubmit);
		
		JPanel panel_shop = new JPanel();
		panel_card.add(panel_shop, SHOP_CARD);
		panel_shop.setLayout(new BoxLayout(panel_shop, BoxLayout.Y_AXIS));
		
		JLabel lblRegisterShop = new JLabel("Register Shop");
		panel_shop.add(lblRegisterShop);
	}

}
