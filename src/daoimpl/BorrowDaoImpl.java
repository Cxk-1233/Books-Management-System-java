package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBUtil;
import entry.Borrow;
import dao.BorrowDao;

public class BorrowDaoImpl implements BorrowDao {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;

	@Override
	public boolean insert(Borrow borrow) {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("insert into Libraryborrow values (?,?,?,?)");
			pst.setInt(1, borrow.getReaderid());
			pst.setString(2, borrow.getBookcode());
			pst.setString(3, borrow.getBorrowdate());
			pst.setString(4, borrow.getReturndate());
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Borrow> select(Borrow borrow) {
		conn = DBUtil.getConn();
		String sql = "select * from Libraryborrow where 1=1";
		if (borrow.getReaderid() != 0) {
			sql += " and readerid=" + borrow.getReaderid();
		}
		if (!"".equals(borrow.getBookcode())) {
			sql += " and bookcode='" + borrow.getBookcode() + "'";
		}
		if (!"".equals(borrow.getBorrowdate())) {
			sql += " and borrowdate='" + borrow.getBorrowdate() + "'";
		}
		if (!"".equals(borrow.getReturndate())) {
			sql += " and returndate='" + borrow.getReturndate() + "'";
		}
		
		ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				borrowarr.add(new Borrow(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return borrowarr;
	}
	
	public boolean update(Borrow borrow){
		conn = DBUtil.getConn();
		String sql = "update Libraryborrow set readerid="+borrow.getReaderid();
		if (!"".equals(borrow.getBorrowdate())) {
			sql += ",borrowdate='" + borrow.getBorrowdate() + "'";
		}
		if (!"".equals(borrow.getReturndate())) {
			sql += ",returndate='" + borrow.getReturndate() + "'";
		}
		
		sql+=" where readerid="+borrow.getReaderid()+"and"+" bookcode='" + borrow.getBookcode() + "'";
		
		try {
			pst = conn.prepareStatement(sql);
			if(pst.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(String bookid,int readerid) {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("delete from libraryborrow where bookcode='"
							+ bookid+"' and readerid="+readerid);
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
