
package UI;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Kernel.GuestInfoKernel;

import java.awt.*;
import java.awt.event.*;



public class New_orderUI_table {
	private JFrame frame;
	String [] request_list = {"asdfasdf","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv","zxcvzxcvzxcv"};
	private JTextField input;
	JList order_list;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public New_orderUI_table() {
		initialize();
	}
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("kdlfglkjdlkfjgklsdj;lg;lksdjfklgjkl;sdfjlkgjdlskfgklj;sldfg");
		lblNewLabel.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 33));
		panel_3.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel_3.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel new_order_controll_table = new JPanel();
		panel.add(new_order_controll_table);
		new_order_controll_table.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		new_order_controll_table.add(panel_1);
		
		
		
		
		
		
		order_list = new JList(request_list);
		order_list.setVisibleRowCount(10);
		order_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		///panel_1.add(order_list);
		
		panel_1.add(new JScrollPane(order_list));
		
		
		
		JPanel new_or_delete_order = new JPanel();
		new_order_controll_table.add(new_or_delete_order, BorderLayout.SOUTH);
		new_or_delete_order.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton delete_an_order = new JButton("-");
		new_or_delete_order.add(delete_an_order);
		
		JButton add_an_order = new JButton("+");
		new_or_delete_order.add(add_an_order);
		
		JButton calculate_money = new JButton("$$$");
		new_or_delete_order.add(calculate_money);
		
		input = new JTextField();
		new_or_delete_order.add(input);
		input.setColumns(10);
		
		JPanel new_order_confirm_table = new JPanel();
		panel.add(new_order_confirm_table);
		
		
		
		
		
		
		frame.setVisible(true);
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		New_orderUI_table abc = new New_orderUI_table();
	
	}

}