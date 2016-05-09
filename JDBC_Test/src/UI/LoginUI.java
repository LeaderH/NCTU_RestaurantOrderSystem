package UI;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*; //JFrame
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Kernel.LoginKernel;

public class LoginUI {
	private LoginKernel kernel;
	private JFrame frame;
	//private JTextField txtf_pwd;
	private JPasswordField txtf_pwd;
	private JTextField txtf_acc;
	private JButton btn_submit;
	private JLabel lbl_title;
	//private JLabel lbl_login;
	/**
	 * Create the application.
	 */
	public LoginUI() {
		kernel= new LoginKernel();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pan_title = new JPanel();
		
		lbl_title = new JLabel("Login Interface");
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		/*
		lbl_login = new JLabel("");
		lbl_login.setFont(new Font("Calibri", Font.BOLD, 24));
		lbl_login.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login.setVisible(false);*/
		pan_title.add(lbl_title);
		//pan_title.add(lbl_login);
		frame.getContentPane().add(pan_title, BorderLayout.NORTH);
		
		JPanel pan_acc = new JPanel();
		panel.add(pan_acc);
		
		JLabel lbl_acc = new JLabel("Account");
		pan_acc.add(lbl_acc);
		
		txtf_acc = new JTextField();
		pan_acc.add(txtf_acc);
		txtf_acc.setColumns(10);
		
		JPanel pan_pwd = new JPanel();
		panel.add(pan_pwd);
		
		JLabel lbl_pwd = new JLabel("Password");
		pan_pwd.add(lbl_pwd);
		
		txtf_pwd = new JPasswordField();
		pan_pwd.add(txtf_pwd);
		txtf_pwd.setColumns(10);
		
		JPanel pan_sub = new JPanel();
		frame.getContentPane().add(pan_sub, BorderLayout.SOUTH);
		
		btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginProcess();
			}
		});
		pan_sub.add(btn_submit);
	}
	
	private void LoginProcess(){
		String name=txtf_acc.getText();
		char[] pwdarr=txtf_pwd.getPassword();
		String pwd=new String(pwdarr);
		/*
		btn_submit.setVisible(false);
		lbl_title.setVisible(false);
		lbl_login.setVisible(true);
		*/
		boolean success=kernel.Login(name,pwd);
		if(success){
			JOptionPane.showMessageDialog(frame, "Welcome "+name ,"Welcome",JOptionPane.INFORMATION_MESSAGE);
			GuestInfoUI.start(kernel.getUid());
		}else{
			JOptionPane.showMessageDialog(frame, "Login Falied", "Warning", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
