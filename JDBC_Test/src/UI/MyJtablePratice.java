
///http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#combobox
///setCellEditor�k
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

public class MyJtablePratice extends JPanel {
    private boolean DEBUG = false;///DEBUG��   ==true ==>�bCMD�W���

    private JTable table;
    private JComboBox<String> shoplist;
    private String shop_list_String [] = {"NONE","shop1","shop2","shop3","shop4","shop5","shop6"};
    
    private JComboBox<String> itemlist;
    
    
    public MyJtablePratice(){
        super(new GridLayout(1,0));
        	
        table = new JTable(new MyTableModel());///MyTableModel(�ۭq�qclass���A�A�бqline139�ݨ䤺�e)�A��class�~��AbstractTableModel(�ФW���d�䤺�e)
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);///��JTable���JScrollPane�W

        //Set up column sizes.
        initColumnSizes(table);///�۩w�q��ơA���e�qline85�}�l    �ت��O���s�]�w�Ҧ���l�����涵���

        
        
        shoplist = new JComboBox<String>();
        itemlist = new JComboBox<String>();
        
        
        setUpComboBoxList(shoplist, shop_list_String);
        setUpComboBoxList(itemlist, new String[]{"NONE","shop1_item_1","shop1_item_2"});

        shoplist.addItemListener(
        	new ItemListener(){
        		@Override
        		public void itemStateChanged(ItemEvent event){
        			if(event.getStateChange()==ItemEvent.SELECTED){
        		        if(shoplist.getSelectedIndex()==0){
        		            setUpComboBoxList(itemlist, new String[]{"NONE"});        		        	
        		        }else if(shoplist.getSelectedIndex()==1){
            		        setUpComboBoxList(itemlist, new String[]{"NONE","item1","item2"});        		        	
        		        }else if(shoplist.getSelectedIndex()==2){
            		        setUpComboBoxList(itemlist, new String[]{"NONE","item1","item2","item3"});        		        	
        		        }
        				
        			}
        		}
        	}
        );
        
        
        
        
        //Fiddle with the Sport column's cell editors / renderers.
        setUpColumnAsComboBox(table, table.getColumnModel().getColumn(1),shoplist);
        setUpColumnAsComboBox(table, table.getColumnModel().getColumn(2),itemlist);
  
        //Add the scroll pane to this panel.
        add(scrollPane);///��scrollPane�[��WTableRenderDemo�o��panel�W

        

    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {///�ت��O���s�]�w�Ҧ���l�����涵���

    	
    	MyTableModel model = (MyTableModel)table.getModel();///Jtable�� MyTableModel reference
        /*model.add_an_order();
        model.delete_an_order();*/

        ///�g�o�T�檺�ت��O�������ۤv�n�ק�order�ƮɡA�n�gcode����3��
        
        TableColumn column = null;///line 96 table.getColumnModel().getColumn(i)�� reference
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();///headerRenderer reference ���V���D�C���榡


        for (int i = 0; i < table.getColumnCount(); i++) {///�վ�C�檺���
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;///�P table �� ��r�ƥت��ӼƦ����� (�H��Φ��Ӥ��t�U�� �j�p�B���)

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
            				      getTableCellRendererComponent(table, longValues[i],
                                 false, false, 0, i);

            cellWidth = comp.getPreferredSize().width;///�����D�P�����ܼƥ�����?????

            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));///�]�w���            
        }
        
        
        
        
    }

    public void setUpComboBoxList(JComboBox<String> list,String []input){///��Jinput String�A�o��������Ucombo box ����ܪ�list
    	///if(list.getComponentCount()==0){
    	list.removeAllItems();
    	///}
    	for(String a : input){
    		list.addItem(a);
    	}
    	
    }
    
    public void setUpColumnAsComboBox(JTable table,TableColumn Column,JComboBox<String> comboBox) {///�N�䤤�@��榡�� combo box ��{  
        //Set up the editor for the sport cells.
    	
        Column.setCellEditor(new DefaultCellEditor(comboBox));///�t�Τ��]�p������cell���A��k  Cell��Editor!! (�t�@��k���ۤv�g�@��override�t�Ϊ� cell���A��k)

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = ///�t�ΥΨӳW�wcell���A���ܼ�
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");///�S�ݨ����ĪG�A���ӬO�Ψӥ[���ܦr�K
        Column.setCellRenderer(renderer);///�S�ݨ����ĪG�A���ӬO�Ψӥ[���ܦr�K
    }


    
    class MyTableModel extends AbstractTableModel {///�]�w table ��Ȥ��e �ȹ�ڤ��e!!!!
        private String[] columnNames = {"num","Shop","Item","quantum","money","selection"};
        private Object[][] data = {
        	{"1.", "shop1","item1"     , new Integer( 5), new Integer(5), new Boolean(false)},
        	//{"2.", "shop2","item2"        , new Integer( 3), new Integer(5), new Boolean(true)},
        	//{"3.", "shop3","item3"      , new Integer( 2), new Integer(5), new Boolean(false)},
        	//{"4.", "shop4","item4" , new Integer(20), new Integer(5), new Boolean(true)},
        	//{"5.", "shop5","item5"          , new Integer(10), new Integer(5), new Boolean(false)}
        };

        
        public final Object[] longValues = {"100", "Kathy",
                                            "None of the above",
                                            new Integer(20), new Integer( 5), Boolean.TRUE};///���F�]�e�צӳ̹w�����̱`�ۭ�


    	final Object[] defaulValue ={""+(getRowCount()+1),"NONE","None of the above",new Integer(20),new Integer( 5),new Boolean(true)};

        public void add_an_order(){
        	Object[][] tmp ;
        	tmp = new Object[getRowCount()+1][];

        	for(int i=0;i<getRowCount()+1;i++){
        		tmp[i] = new Object[getColumnCount()];
        	}
        	
        	for(int i=0;i<getRowCount();i++){
        		for(int j=0;j<getColumnCount();j++){
        			tmp[i][j] = new Object();
        			tmp[i][j] = data[i][j];
        		}
        	}
        	
        	for(int i=0;i<getColumnCount();i++){
        		tmp[getRowCount()][i] = new Object();
        		tmp[getRowCount()][i] = defaulValue[i];  
        	}
        	data = tmp;
        }
        
        public void delete_an_order(){
        	Object[][] tmp ;
        	tmp = new Object[getRowCount()-1][];

        	for(int i=0;i<getRowCount()-1;i++){
        		tmp[i] = new Object[getColumnCount()];
        	}
        	
        	for(int i=0;i<getRowCount()-1;i++){
        		for(int j=0;j<getColumnCount();j++){
        			tmp[i][j] = new Object();
        			tmp[i][j] = data[i][j];
        		}
        	}
        	data = tmp;
        	
        }
        
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            fireTableCellUpdated(row, col);

            
            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {///�򥻤W�N�O�s�X�@��JFrame��W�z��TableRenderDemo�K�W�h
        //Create and set up the window.
        JFrame frame = new JFrame("MyJtablePratice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MyJtablePratice newContentPane = new MyJtablePratice();
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
