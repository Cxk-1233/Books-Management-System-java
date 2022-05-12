package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBUtil;
import dao.ReaderDao;
import entry.Reader;

public class ReaderDaoImpl implements ReaderDao {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;

	@Override
	public ArrayList<Reader> select() {
		conn = DBUtil.getConn();
		ArrayList<Reader> readerarr = new ArrayList<Reader>();
		try {
			pst = conn.prepareStatement("select * from libraryreader");
			rs = pst.executeQuery();
			while (rs.next()) {
				Reader reader = new Reader(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				readerarr.add(reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return readerarr;
	}

	@Override
	public boolean insert(Reader reader) {
		conn = DBUtil.getConn();
		try {
			pst = conn
					.prepareStatement("insert into libraryreader values (?,?,?,?,?,?)");
			pst.setString(1, reader.getReadername());
			pst.setString(2, reader.getReadercardid());
			pst.setString(3, reader.getReaderdate());
			pst.setString(4, reader.getReadertel());
			pst.setString(5, reader.getReaderaddress());
			pst.setString(6, reader.getReaderremark());
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

	@Override
	public ArrayList<Reader> selectterm(Reader reader) {
		conn = DBUtil.getConn();
		String url = "select * from libraryreader where 1=1";
		
		if (reader.getReaderid()!=0) {
			url += " and readerid=" + reader.getReaderid();
		}
		if (!"".equals(reader.getReadername())) {
			url += " and readername='" + reader.getReadername() + "'";
		}
		if (!"".equals(reader.getReadercardid())) {
			url += " and readercardid='" + reader.getReadercardid() + "'";
		}
		if (!"".equals(reader.getReaderdate())) {
			url += " and readerdate like '" + reader.getReaderdate() + "'";
		}
		if (!"".equals(reader.getReadertel())) {
			url += " and readertel='" + reader.getReadertel() + "'";
		}
		if (!"".equals(reader.getReaderaddress())) {
			url += " and readeraddress='" + reader.getReaderaddress() + "'";
		}
		if (!"".equals(reader.getReaderremark())) {
			url += " and readerremark='" + reader.getReaderremark() + "'";
		}
		
		ArrayList<Reader> readerarr = new ArrayList<Reader>();
		try {
			pst = conn.prepareStatement(url);
			rs = pst.executeQuery();
			while (rs.next()) {
				readerarr.add(new Reader(Integer.valueOf(rs.getString(1)), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return readerarr;
	}

	@Override
	public boolean delete(int id) {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("delete from libraryreader where readerid="
							+ id);
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

	@Override
	public boolean update(Reader reader) {
		conn = DBUtil.getConn();
			try {
			String sql="update libraryreader set readername=?,readercardid=?,readerdate=?,readertel=?,readeraddress=?,readerremark=? where readerid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, reader.getReadername());
			pst.setString(2, reader.getReadercardid());
			pst.setString(3, reader.getReaderdate());
			pst.setString(4, reader.getReadertel());
			pst.setString(5, reader.getReaderaddress());
			pst.setString(6, reader.getReaderremark());
			pst.setInt(7, reader.getReaderid());
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
