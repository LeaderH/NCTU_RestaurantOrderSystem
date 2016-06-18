package db;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class SingletonConnect{
	private static final String db_name="jdbc:mysql://stevie.heliohost.org/leader_projectdb";
	private static final String db_user="leader_normal";
	private static final String db_pwd="normal";
	private static Connection con=null;
	private static void Connect(){
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
	}
	 public static Connection getInstance() {
	      if(con == null) {
	    	  Connect();
	      }
	      return con;
	   }
}