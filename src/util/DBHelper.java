package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	private static final String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";
	private static String username="root";
	private static String password="123456";
	private static Connection conn=null;
	static{
		try{
			Class.forName(driver);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception{
		if(conn==null){
			conn=DriverManager.getConnection(url,username, password);
			return conn;
		}
		return conn;
		
	}
	public static void main(String[] args) {
		try {
			Connection conn=DBHelper.getConnection();
			if(conn!=null){
				System.out.println("数据库连接正常");	
			}
			else{
				System.out.println("数据库连接失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
