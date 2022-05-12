package dao;

import java.util.ArrayList;

import entry.Reader;

public interface ReaderDao {
	//查询全部读者
	public ArrayList<Reader> select();
	//添加读者
	public boolean insert(Reader reader);
	//条件查询
	public ArrayList<Reader> selectterm(Reader reader);
	//删除读者
	public boolean delete(int id);
	//修改读者
	public boolean update(Reader reader);
}
