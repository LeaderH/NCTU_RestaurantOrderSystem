
///http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#combobox
///setCellEditor法
package UI;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;

public class FormUItesting extends JPanel {
	//private JTextField money;
	//private JTextField quantum;
	
	per_data_panel orders[];
	JPanel main_panel;
	MyJtablePratice selection_board;
	public FormUItesting(){
		setLayout(new BorderLayout(0, 0));

		
		selection_board = new MyJtablePratice();
		//.add(table, BorderLayout.CENTER);
		
		add(selection_board, BorderLayout.NORTH);
		
		
		
		
		
		
		per_data_panel orders_1 = new per_data_panel(1,new String[]{"NONE","Shop1","Shop2","Shop3"});

		add(orders_1, BorderLayout.SOUTH);
		
			

    }
	
	class per_data_panel extends JPanel{
		int num=0;
		JLabel order_num;
		JLabel space;
		JComboBox <String> shop_comboBox;
		JComboBox <String> item_comboBox;
		private JTextField money;
		private JTextField quantum;
		JLabel the_signs_and_space;
		JCheckBox add_it_or_not;
		
		String shop_stirng_list[] = {"NONE","Shop1"};
		String item_stirng_list[] = {"NONE","item1"};
		
		per_data_panel(){
			order_num = new JLabel("  "+num+".");
			
			shop_stirng_list = new String[2];
			shop_stirng_list[0] = new String("NONE");
			shop_stirng_list[1] = new String("Shop1");
			

			item_stirng_list = new String[2];
			item_stirng_list[0] = new String("NONE");
			item_stirng_list[1] = new String("item1");

			
			add(order_num);
			
			shop_comboBox = new JComboBox();
			setUpComboBoxList(shop_comboBox,new String []{"NONE","Shop1","Shop2","Shop2"});
			add(shop_comboBox);
			
			
			item_comboBox = new JComboBox();
			setUpComboBoxList(item_comboBox,new String []{"NONE","item1","item2","item3"});
			add(item_comboBox);
			
			
			money = new JTextField();
			money.setHorizontalAlignment(SwingConstants.RIGHT);
			money.setText("10");
			money.setEditable(false);
			add(money);
			money.setColumns(5);
			
			the_signs_and_space = new JLabel("$  per item          ");
			add(the_signs_and_space);
			
			quantum = new JTextField();
			quantum.setHorizontalAlignment(SwingConstants.RIGHT);
			quantum.setEnabled(true);
			quantum.setEditable(true);
			quantum.setText("");
			add(quantum);
			quantum.setColumns(5);
			
			space = new JLabel("items        ");
			add(space);
			
			JCheckBox add_it_or_not = new JCheckBox("add");
			add_it_or_not.setSelected(true);
			add(add_it_or_not);
		}
		
		per_data_panel(int i,String shoplist[]){
			num = i;
			order_num = new JLabel("  "+num+".");
			add(order_num);
			
			shop_comboBox = new JComboBox<String>();
			setUpComboBoxList(shop_comboBox,shoplist);
			add(shop_comboBox);
			
			
			item_comboBox = new JComboBox <String> ();
			setUpComboBoxList(item_comboBox,new String []{"NONE","item1","item2","item3"});
			add(item_comboBox);
			
			
			money = new JTextField();
			money.setHorizontalAlignment(SwingConstants.RIGHT);
			money.setText("10");
			money.setEditable(false);
			add(money);
			money.setColumns(5);
			
			the_signs_and_space = new JLabel("$  per item          ");
			add(the_signs_and_space);
			
			quantum = new JTextField();
			quantum.setHorizontalAlignment(SwingConstants.RIGHT);
			quantum.setEnabled(true);
			quantum.setEditable(true);
			quantum.setText("");
			add(quantum);
			quantum.setColumns(5);
			
			space = new JLabel("items        ");
			add(space);
			
			JCheckBox add_it_or_not = new JCheckBox("add");
			add_it_or_not.setSelected(true);
			add(add_it_or_not);
			
			
			
		}
		
		public void setUpComboBoxList(JComboBox <String> list,String []input){///輸入input String，得到該欄位按下combo box 所顯示的list
	    	///if(list.getComponentCount()==0){
	    	list.removeAllItems();
	    	///}
	    	for(String a : input){
	    		list.addItem(a);
	    	}
		}
	
	
	}
	
	
	
	
	
    private static void createAndShowGUI() {///基本上就是製出一個JFrame把上述的TableRenderDemo貼上去

    	JFrame frame = new JFrame("FormUItesting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        FormUItesting newContentPane = new FormUItesting();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setSize(750,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
