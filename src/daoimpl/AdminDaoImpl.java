package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import dao.AdminDao;
import entry.Admin;
import utils.DBUtil;
import utils.Prestrain;

public class AdminDaoImpl implements AdminDao {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;
//	private ArrayList<Admin> adminarr;

	@Override
	public boolean login(String account, String password) {
		conn = DBUtil.getConn();
		try {
			pst = conn
					.prepareStatement("select * from libraryUser where username=? and userpassword=?");
			pst.setString(1, account);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				Prestrain.user = account;
				Prestrain.password = password;
				return true;
			}
			while (rs.next()) {
				new Admin(rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8));
				Prestrain.user = rs.getString(2);
				Prestrain.password = rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

	@Override
	public boolean update(Admin admin) {
		conn = DBUtil.getConn();
		if(admin.getUsername().equals("")){
			admin.setUsername(Prestrain.user);
		}
		String sql = "update LibraryUser set username='"+admin.getUsername()+"'";

		if (!"".equals(admin.getUserpassword())) {
			sql+=",userpassword='"+admin.getUserpassword()+"'";
		}
		if (!"".equals(admin.getUsertel())) {
			sql+=",usertel='"+admin.getUsertel()+"'";
		}
		if (!"".equals(admin.getUseraddress())) {
			sql+=",useraddress='"+admin.getUseraddress()+"'";
		}
		if (!"".equals(admin.getUserdate())) {
			sql+=",userdate='"+admin.getUserdate()+"'";
		}
		if (!"".equals(admin.getUserpurview())) {
			sql+=",userpurview='"+admin.getUserpurview()+"'";
		}
		if (!"".equals(admin.getUsertruce())) {
			sql+=",usertruce='"+admin.getUsertruce()+"'";
		}
		if (!"".equals(admin.getColor())) {
			sql+=",color='"+admin.getColor()+"'";
		}
		if (!"".equals(admin.getHomestate())) {
			sql+=",homestate='"+admin.getHomestate()+"'";
		}
		if (admin.getLock()!=-1) {
			sql+=",lock="+admin.getLock()+"";
		}
		if (admin.getReader()!=0) {
			sql+=",reader="+admin.getReader()+"";
		}
		
		sql+=" where username='"+Prestrain.user+"'";
		
		try {
			pst = conn.prepareStatement(sql);
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}
}
