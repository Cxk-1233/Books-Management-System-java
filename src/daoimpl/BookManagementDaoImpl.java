package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DBUtil;
import dao.BookManagementDao;
import entry.Book;

public class BookManagementDaoImpl implements BookManagementDao {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static ArrayList<Book> bookarr;
	private Book books;

	// 添加图书
	@Override
	public boolean insert(Book book) {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("insert into libraryBookInfo values (?,?,?,?,?,?,?,?)");
			pst.setString(1, book.getBookid());
			pst.setString(2, book.getBookname());
			pst.setString(3, book.getPublishinghouse());
			pst.setInt(4, book.getQuantity());
			pst.setString(5, book.getBooktype());
			pst.setString(6, book.getName());
			pst.setDouble(7, book.getBookprice());
			pst.setString(8, book.getDate());
			if (pst.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

	// 查询全部图书
	@Override
	public ArrayList<Book> select() {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("select * from libraryBookInfo");
			rs = pst.executeQuery();
			bookarr=new ArrayList<Book>();
			while (rs.next()) {
				books = new Book(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8));
				bookarr.add(books);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return bookarr;
	}

	@Override
	public ArrayList<Book> selectTerm(Book book) {
		conn = DBUtil.getConn();
		String url="select * from libraryBookInfo where 1=1";
		if(!"".equals(book.getBookid())){
			url+=" and bookcode='"+book.getBookid()+"'";
		}
		if(!"".equals(book.getBookname())){
			url+=" and bookname like '"+book.getBookname()+"%'";
		}
		if(!"".equals(book.getPublishinghouse())){
			url+=" and bookpublish='"+book.getPublishinghouse()+"'";
		}
		if(book.getQuantity()>0){
			url+=" and bookaddnumber="+book.getQuantity();
		}
		if(!"".equals(book.getBooktype())){
			url+=" and booksort='"+book.getBooktype()+"'";
		}
		if(!"".equals(book.getName())){
			url+=" and bookauthor='"+book.getName()+"'";
		}
		if(book.getBookprice()>0){
			url+=" and bookprice="+book.getBookprice();
		}
		
		try {
			pst = conn.prepareStatement(url);
			rs = pst.executeQuery();
			bookarr=new ArrayList<Book>();
			while (rs.next()) {
				books = new Book(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8));
				bookarr.add(books);
			}
		} catch (SQLException e) {
			
		}
		DBUtil.getClose(conn, pst, rs);
		return bookarr;
	}

	@Override
	public boolean delete(String id) {
		conn=DBUtil.getConn();
		try {
			pst=conn.prepareStatement("delete from libraryBookInfo where bookcode='"+id+"'");
			if(pst.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

	@Override
	public boolean update(Book book) {
		conn=DBUtil.getConn();
		String url="update libraryBookInfo set bookcode='"+book.getBookid()+"'";
		
		if(!"".equals(book.getBookname())){
			url+=",bookname='"+book.getBookname()+"'";
		}
		if(!"".equals(book.getPublishinghouse())){
			url+=",bookpublish='"+book.getPublishinghouse()+"'";
		}
		if(book.getQuantity()>0){
			url+=",bookaddnumber="+book.getQuantity();
		}
		if(!"".equals(book.getBooktype())){
			url+=",booksort='"+book.getBooktype()+"'";
		}
		if(!"".equals(book.getName())){
			url+=",bookauthor='"+book.getName()+"'";
		}
		if(book.getBookprice()>0){
			url+=",bookprice="+book.getBookprice();
		}
		
		url+=" where bookcode='"+book.getBookid()+"'";
		
		try {
			pst=conn.prepareStatement(url);
			if(pst.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
		return false;
	}

}
