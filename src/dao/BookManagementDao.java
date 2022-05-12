package dao;

import java.util.ArrayList;

import entry.Book;

public interface BookManagementDao{
		//添加图书
		public boolean insert(Book book);
		//查询全部图书
		public ArrayList<Book> select();
		//按条件查询图书
		public ArrayList<Book> selectTerm(Book book);
		//按编号删除图书
		public boolean delete(String id);
		//按编号修改图书
		public boolean update(Book book);
}
