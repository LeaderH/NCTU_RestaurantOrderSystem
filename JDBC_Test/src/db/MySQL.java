package db;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class MySQL {
	protected static Connection con = null; //Database objects 
	protected Statement stat = null; //����,�ǤJ��sql������r�� 
	protected ResultSet rs = null; //���G�� 
	protected PreparedStatement pst = null; 
	//����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
	//���Q��?�Ӱ��Х� 
	/**
	 * Constructor
	 */
	public MySQL() {
		reconnect();
	}
	public MySQL(Connection c) {
		con = c;
	}
	
	public Connection reconnect(){
		con=SingletonConnect.getInstance();
		return con;
	}
	
 	public void createTable(String createdbSQL) {
		try {
			stat = con.createStatement();
			stat.executeUpdate(createdbSQL);
		} catch (SQLException e) {
			System.out.println("CreateDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public void dropTable(String dropdbSQL) {
		try {
			stat = con.createStatement();
			stat.executeUpdate(dropdbSQL);
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	//����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
		//�_�h�b����Timeout��,�i��|��Connection poor�����p 
	protected void Close(){ 
		try { 
			if(rs!=null) { 
				rs.close(); 
				rs = null; 
			} 
			if(stat!=null) { 
				stat.close(); 
				stat = null; 
			} 
			if(pst!=null) { 
				pst.close(); 
				pst = null; 
			} 
		} 
		catch(SQLException e) { 
			System.out.println("Close Exception :" + e.toString()); 
		} 
	} 
	
	@Override
	protected void finalize(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con=null;
		}
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
