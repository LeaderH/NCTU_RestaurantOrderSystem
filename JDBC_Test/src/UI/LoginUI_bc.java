package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Kernel.LoginKernel;


public class LoginUI_bc extends JApplet implements ActionListener{
	private JLabel name_label;
	private JTextField name_field;
	private JLabel pwd_label;
	private JTextField pwd_field;
	private JButton submit_btn;
	private JLabel status_label;
	private JButton retry_btn;
	private JButton logout_btn;
	//¹B¥Îsql
	private LoginKernel database;   
	//gdfdgf
	
	public void init(){
		database=new LoginKernel();
		
		
		Container container=getContentPane();
		setSize(200, 200);
		container.setLayout(new FlowLayout());
		name_label=new JLabel("Name:");
		container.add(name_label);
		name_field=new JTextField(15);
		container.add(name_field);
		
		pwd_label=new JLabel("Password:");
		container.add(pwd_label);
		pwd_field=new JTextField(15);
		container.add(pwd_field);
		submit_btn=new JButton("Submit");
		submit_btn.addActionListener(this);
		container.add(submit_btn);
		
		status_label=new JLabel("");
		status_label.setVisible(false);
		container.add(status_label);
		
		retry_btn=new JButton("Retry");
		retry_btn.addActionListener(this);
		retry_btn.setVisible(false);
		container.add(retry_btn);
		
		logout_btn=new JButton("Logout");
		logout_btn.addActionListener(this);
		logout_btn.setVisible(false);
		container.add(logout_btn);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		showStatus("paint() called");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit_btn){
			LoginProcess();
		}
		if(e.getSource()==retry_btn){
			submit_btn.setVisible(true);
			retry_btn.setVisible(false);
			LoginProcess();
		}
		if(e.getSource()==logout_btn){
			submit_btn.setVisible(true);
			logout_btn.setVisible(false);
			status_label.setVisible(false);
			name_field.setText("");
			pwd_field.setText("");
		}
	}
	
	private void LoginProcess(){
		String name=name_field.getText();
		String pwd=pwd_field.getText();
		
		showStatus("submitted as: ("+name+", "+pwd+")");
		
		submit_btn.setVisible(false);
		status_label.setVisible(true);
		
		boolean success=database.Login(name,pwd);
		if(success){
			status_label.setText("Welcome "+name);
			logout_btn.setVisible(true);
		}else{
			status_label.setText("Login failed");
			retry_btn.setVisible(true);
		}
	}
}
