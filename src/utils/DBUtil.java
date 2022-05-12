package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;
	
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true","root","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void getClose(Connection conn,PreparedStatement pst,ResultSet rs){
		try {
			conn.close();
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
