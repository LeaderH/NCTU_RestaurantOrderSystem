package db;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

public class MySQL {
	private final String db_name="jdbc:mysql://stevie.heliohost.org/leader_testdb1";
	private final String db_user="leader_normal";
	private final String db_pwd="normal";
	protected static Connection con = null; //Database objects 
	protected Statement stat = null; //執行,傳入之sql為完整字串 
	protected ResultSet rs = null; //結果集 
	protected PreparedStatement pst = null; 
	//執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	//先利用?來做標示 
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
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		      //註冊driver 
		    con = DriverManager.getConnection(db_name,db_user,db_pwd); 
		      //取得connection
		} catch (ClassNotFoundException e) {
			System.out.println("DriverClassNotFound :" + e.toString());
		} // 有可能會產生sqlexception
		catch (SQLException x) {
			System.out.println("Exception :" + x.toString());
		}
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
	//完整使用完資料庫後,記得要關閉所有Object 
		//否則在等待Timeout時,可能會有Connection poor的狀況 
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
			if(con!=null){
				con.close();
				con=null;
			}
		} 
		catch(SQLException e) { 
			System.out.println("Close Exception :" + e.toString()); 
		} 
	} 
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
}
