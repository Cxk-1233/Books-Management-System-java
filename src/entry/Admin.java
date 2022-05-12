package entry;

import utils.Prestrain;

public class Admin {
	private String username="";//账号
	private String userpassword="";//密码
	private String usertel="";//电话/邮箱
	private String useraddress="";//地址
	private String userdate="";//注册时间
	private String userpurview="";//权限
	private String usertruce="";//状态
	private String color="";// 当前主题颜色
	private int lock=-1;//自动锁屏时间
	private boolean homestate=Prestrain.homestate;// 主窗口菜单栏状态
	private int reader=0;//绑定的读者
	
	public int getReader() {
		return reader;
	}

	public void setReader(int reader) {
		this.reader = reader;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUserdate() {
		return userdate;
	}

	public void setUserdate(String userdate) {
		this.userdate = userdate;
	}

	public String getUserpurview() {
		return userpurview;
	}

	public void setUserpurview(String userpurview) {
		this.userpurview = userpurview;
	}

	public String getUsertruce() {
		return usertruce;
	}

	public void setUsertruce(String usertruce) {
		this.usertruce = usertruce;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean getHomestate() {
		return homestate;
	}

	public void setHomestate(boolean homestate) {
		this.homestate = homestate;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public Admin(String username, String userpassword, String usertel,
			String useraddress, String userdate, String userpurview,
			String usertruce) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.usertel = usertel;
		this.useraddress = useraddress;
		this.userdate = userdate;
		this.userpurview = userpurview;
		this.usertruce = usertruce;
	}

	public Admin(String username, String userpassword, String usertel,
			String useraddress, String userdate, String userpurview,
			String usertruce, String color, boolean homestate) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.usertel = usertel;
		this.useraddress = useraddress;
		this.userdate = userdate;
		this.userpurview = userpurview;
		this.usertruce = usertruce;
		this.color = color;
		this.homestate = homestate;
	}

	public Admin(int reader) {
		super();
		this.reader = reader;
	}

	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", userpassword=" + userpassword
				+ ", usertel=" + usertel + ", useraddress=" + useraddress
				+ ", userdate=" + userdate + ", userpurview=" + userpurview
				+ ", usertruce=" + usertruce + ", color=" + color + ", lock="
				+ lock + ", homestate=" + homestate + "]";
	}

	
}
