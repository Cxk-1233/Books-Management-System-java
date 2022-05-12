package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DBUtil;
import dao.RegisterDao;

public class RegisterDaoimpl extends RegisterDao{
	private static Connection conn;
	private static PreparedStatement pst;

	 public boolean insert(RegisterDao s){
		 conn =DBUtil.getConn();
		 String sql="insert into  LibraryUser (username,userpassword,usertel,useraddress)  values(?,?,?,?)";
		 try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, s.getUsername());
			pst.setString(2, s.getUserpassword());
			pst.setString(3, s.getUsertel());
			pst.setString(4, s.getUseraddress());
			int result= pst.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		 
		return false;
		 
	 }

}
