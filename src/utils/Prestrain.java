package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Timer;

import daoimpl.AdminDaoImpl;
import daoimpl.BookManagementDaoImpl;
import daoimpl.BorrowDaoImpl;
import daoimpl.ReaderDaoImpl;
import daoimpl.RegisterDaoimpl;
import entry.Admin;
import listener.HomeActionListener;

//这个类用于预加载
public class Prestrain implements ActionListener {
	public static String user;// 账号
	public static String password;// 密码
	public static String rank;// 账号等级
	public static String color = "white";// 当前主题颜色
	public static String usertel;//当前登录账号的邮箱或电话
	public static int lock;// 自动锁屏时间
	public static int reader;//所绑定的读者id
	private static String timeHH;// 时间 小时
	private static String timemm;// 时间 分
	private static String timess;// 时间 秒
	private static String timeyyyy;// 时间 年
	private static String timeMM;// 时间 月
	private static String timedd;// 时间 日
	public static String timearr[] = { timeHH, timemm, timess, timeyyyy,
			timeMM, timedd };// 时间
	public static boolean homestate = true;// 主窗口菜单栏状态
	public static ArrayList<Admin> adminarr = new ArrayList<Admin>();// 登录账号信息

	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;

	public Prestrain() {
		Timer time = new Timer(100, this);
		time.start();
		// HomeActionListener预加载
		new HomeActionListener(null);
	}

	// 获取账号信息
	public static void Prestrain2() {
		conn = DBUtil.getConn();
		try {
			pst = conn.prepareStatement("select * from libraryUser where username=? and userpassword=?");
			pst.setString(1, user);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin(rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getBoolean(10));
				if (rs.getString(7)==null) {
					Prestrain.rank = "普通用户";
				} else {
					Prestrain.rank = rs.getString(7);
				}
				if (rs.getString(9) != null) {
					Prestrain.color = rs.getString(9);
				}
				Prestrain.homestate = rs.getBoolean(10);
				usertel=rs.getString(4);
				lock = rs.getInt(11);
				reader = rs.getInt(12);
				adminarr.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.getClose(conn, pst, rs);
	}

	// 准备数据写入器
	public static void Prestrain3() {
		new BookManagementDaoImpl();
		new ReaderDaoImpl();
		new AdminDaoImpl();
		new BorrowDaoImpl();
		new ReaderDaoImpl();
		new RegisterDaoimpl();
	}

	// 准备数据读取器
	public static void Prestrain4() {
		new BookManagementDaoImpl().select();
		new ReaderDaoImpl().select();
		new AdminDaoImpl();
		new BorrowDaoImpl();
		new ReaderDaoImpl();
		new RegisterDaoimpl();
	}

	// 每一秒刷新一次时钟
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timearr[0] = new SimpleDateFormat("HH").format(new Date());
		timearr[1] = new SimpleDateFormat("mm").format(new Date());
		timearr[2] = new SimpleDateFormat("ss").format(new Date());
		timearr[3] = new SimpleDateFormat("yyyy").format(new Date());
		timearr[4] = new SimpleDateFormat("MM").format(new Date());
		timearr[5] = new SimpleDateFormat("dd").format(new Date());
	}
}
