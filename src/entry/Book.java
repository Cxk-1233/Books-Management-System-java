package entry;

public class Book {
	private String bookid="";//图书编号
	private String bookname="";//图书名字
	private String Publishinghouse="";//出版社
	private int quantity=0;//加入数量
	private String booktype="";//类别
	private String name="";//作者
	private double bookprice=0;//价格
	private String date;//入馆时间

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPublishinghouse() {
		return Publishinghouse;
	}

	public void setPublishinghouse(String publishinghouse) {
		Publishinghouse = publishinghouse;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", bookname=" + bookname
				+ ", Publishinghouse=" + Publishinghouse + ", quantity="
				+ quantity + ", booktype=" + booktype + ", name=" + name
				+ ", bookprice=" + bookprice + ", date=" + date + "]";
	}

	public Book(String bookid, String bookname, String publishinghouse,
			int quantity, String booktype, String name, double bookprice,
			String date) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		Publishinghouse = publishinghouse;
		this.quantity = quantity;
		this.booktype = booktype;
		this.name = name;
		this.bookprice = bookprice;
		this.date = date;
	}

	public Book(String bookid, String bookname, String publishinghouse,
			int quantity, String booktype, String name, double bookprice) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		Publishinghouse = publishinghouse;
		this.quantity = quantity;
		this.booktype = booktype;
		this.name = name;
		this.bookprice = bookprice;
	}

	public Book() {
		super();
	}

}
