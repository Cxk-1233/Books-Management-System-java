package entry;

public class Reader {
	private int readerid=0;// 读者id
	private String readername="";// 读者姓名
	private String readercardid="";// 读者身份证
	private String readerdate="";// 登记日期
	private String readertel="";// 读者电话
	private String readeraddress="";// 读者地址
	private String readerremark="";// 读者备注

	public int getReaderid() {
		return readerid;
	}

	public void setReaderid(int readerid) {
		this.readerid = readerid;
	}

	public String getReadername() {
		return readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public String getReaderremark() {
		return readerremark;
	}

	public void setReaderremark(String readerrremark) {
		this.readerremark = readerrremark;
	}

	public String getReadercardid() {
		return readercardid;
	}

	public void setReadercardid(String readercardid) {
		this.readercardid = readercardid;
	}

	public String getReaderdate() {
		return readerdate;
	}

	public void setReaderdate(String readerdate) {
		this.readerdate = readerdate;
	}

	public String getReadertel() {
		return readertel;
	}

	public void setReadertel(String readertel) {
		this.readertel = readertel;
	}

	public String getReaderaddress() {
		return readeraddress;
	}

	public void setReaderaddress(String readeraddress) {
		this.readeraddress = readeraddress;
	}

	@Override
	public String toString() {
		return "Reader [readerid=" + readerid + ", readername=" + readername
				+ ", readerremark=" + readerremark + ", readercardid="
				+ readercardid + ", readerdate=" + readerdate + ", readertel="
				+ readertel + ", readeraddress=" + readeraddress + "]";
	}

	public Reader(int readerid, String readername, String readercardid,
			String readerdate, String readertel, String readeraddress,
			String readerremark) {
		super();
		this.readerid = readerid;
		this.readername = readername;
		this.readercardid = readercardid;
		this.readerdate = readerdate;
		this.readertel = readertel;
		this.readeraddress = readeraddress;
		this.readerremark = readerremark;
	}

	public Reader() {
		super();
	}

}
