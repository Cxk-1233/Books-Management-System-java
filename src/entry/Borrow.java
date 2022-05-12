package entry;

public class Borrow {
	private int readerid = 0;// 读者id
	private String bookcode="";// 图书编号
	private String borrowdate="";// 借阅时间
	private String returndate="";// 归还时间

	public int getReaderid() {
		return readerid;
	}

	public void setReaderid(int readerid) {
		this.readerid = readerid;
	}

	public String getBookcode() {
		return bookcode;
	}

	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}

	public String getBorrowdate() {
		return borrowdate;
	}

	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public Borrow(int readerid, String bookcode, String borrowdate,
			String returndate) {
		super();
		this.readerid = readerid;
		this.bookcode = bookcode;
		this.borrowdate = borrowdate;
		this.returndate = returndate;
	}

	public Borrow() {
		super();
	}

}
