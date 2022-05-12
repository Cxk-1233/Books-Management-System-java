package dao;

import java.util.ArrayList;

import entry.Borrow;

public interface BorrowDao {
	public boolean insert(Borrow borrow);//借阅
	public ArrayList<Borrow> select(Borrow borrow);//查询
}
