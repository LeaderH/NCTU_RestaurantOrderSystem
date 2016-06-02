
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

public class MyJtablePratice extends JPanel {
    private boolean DEBUG = false;///DEBUG用   ==true ==>在CMD上顯示

    private JTable table;
    private JComboBox<String> shoplist;
    private String shop_list_String [] = {"NONE","shop1","shop2","shop3","shop4","shop5","shop6"};
    
    private JComboBox<String> itemlist;
    
    
    public MyJtablePratice(){
        super(new GridLayout(1,0));
        	
        table = new JTable(new MyTableModel());///MyTableModel(自訂義class型態，請從line139看其內容)，其class繼承AbstractTableModel(請上網查其內容)
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);///把JTable放到JScrollPane上

        //Set up column sizes.
        initColumnSizes(table);///自定義函數，內容從line85開始    目的是重新設定所有格子的個欄項比例

        
        
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
        add(scrollPane);///把scrollPane加到上TableRenderDemo這個panel上

        

    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {///目的是重新設定所有格子的個欄項比例

    	
    	MyTableModel model = (MyTableModel)table.getModel();///Jtable的 MyTableModel reference
        /*model.add_an_order();
        model.delete_an_order();*/

        ///寫這三行的目的是讓提醒自己要修改order數時，要寫code為此3行
        
        TableColumn column = null;///line 96 table.getColumnModel().getColumn(i)的 reference
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();///headerRenderer reference 指向標題列的格式


        for (int i = 0; i < table.getColumnCount(); i++) {///調整每欄的比例
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;///與 table 欄 文字數目的個數成正比 (以後用此來分配各欄 大小、比例)

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
            				      getTableCellRendererComponent(table, longValues[i],
                                 false, false, 0, i);

            cellWidth = comp.getPreferredSize().width;///不知道與哪個變數正相關?????

            if (DEBUG) {
                System.out.println("Initializing width of column "
                                   + i + ". "
                                   + "headerWidth = " + headerWidth
                                   + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));///設定比例            
        }
        
        
        
        
    }

    public void setUpComboBoxList(JComboBox<String> list,String []input){///輸入input String，得到該欄位按下combo box 所顯示的list
    	///if(list.getComponentCount()==0){
    	list.removeAllItems();
    	///}
    	for(String a : input){
    		list.addItem(a);
    	}
    	
    }
    
    public void setUpColumnAsComboBox(JTable table,TableColumn Column,JComboBox<String> comboBox) {///將其中一欄格式用 combo box 表現  
        //Set up the editor for the sport cells.
    	
        Column.setCellEditor(new DefaultCellEditor(comboBox));///系統內設計的改變cell型態方法  Cell的Editor!! (另一方法為自己寫一個override系統的 cell型態方法)

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = ///系統用來規定cell型態的變數
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");///沒看到具體效果，應該是用來加提示字八
        Column.setCellRenderer(renderer);///沒看到具體效果，應該是用來加提示字八
    }


    
    class MyTableModel extends AbstractTableModel {///設定 table 初值內容 值實際內容!!!!
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
                                            new Integer(20), new Integer( 5), Boolean.TRUE};///為了設寬度而最預估的最常自原


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
    private static void createAndShowGUI() {///基本上就是製出一個JFrame把上述的TableRenderDemo貼上去
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
