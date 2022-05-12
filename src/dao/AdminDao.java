package dao;

import entry.Admin;

public interface AdminDao {
	//登录
	public boolean login(String account,String password);
	//修改
	public boolean update(Admin admin);
}
