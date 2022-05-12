   package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import com.sunking.swing.JDatePicker;

import utils.Prestrain;
import daoimpl.BookManagementDaoImpl;
import daoimpl.BorrowDaoImpl;
import entry.Book;

public class BorrowBook implements ActionListener {
	private JTextField txt1;
	private JTextField txt2;
	private ArrayList<Book> bookarr=new ArrayList<Book>();
	public static Timer timer;
	
	public BorrowBook(String bookname){
		Book book=new Book();
		book.setBookname(bookname);
		bookarr = new BookManagementDaoImpl().selectTerm(book);
		
		HomeFrame.jp2.removeAll();
		
		JPanel jp1=new JPanel();
		jp1.setLayout(null);
		JLabel jl=new JLabel("图书信息");
		jl.setFont(new Font("微软雅黑",Font.BOLD,25));
		jl.setBounds(5, 5, 200, 30);
		jp1.add(jl);
		
		//书img
		JPanel jp2=new JPanel();
		jp2.setLayout(new GridBagLayout());
		jp2.setBorder(new LineBorder(Color.black, 2));
		JLabel jl1=new JLabel();
		jl1.setIcon(new ImageIcon("img/HomeImgs/1.png"));
		jp2.add(jl1);
		jp2.setBounds(5, 35, 150, 200);
		jp1.add(jp2);
		//详细信息
		JPanel jp3=new JPanel();
		jp3.setLayout(null);
		Font f=new Font("微软雅黑",Font.BOLD,20);
		jl=new JLabel("编码:"+bookarr.get(0).getBookid());
		jl.setFont(f);
		jl.setBounds(0,35,200,30);
		jp3.add(jl);
		jl=new JLabel("名称:"+bookarr.get(0).getBookname());
		jl.setFont(f);
		jl.setBounds(0,65,200,30);
		jp3.add(jl);
		jl=new JLabel("作者:"+bookarr.get(0).getName());
		jl.setFont(f);
		jl.setBounds(0,95,200,30);
		jp3.add(jl);
		jl=new JLabel("类别:"+bookarr.get(0).getBooktype());
		jl.setFont(f);
		jl.setBounds(200,95,200,30);
		jp3.add(jl);
		jl=new JLabel("价格:"+bookarr.get(0).getBookprice());
		jl.setFont(f);
		jl.setBounds(0,125,200,30);
		jp3.add(jl);
		jl=new JLabel("加入时间:"+bookarr.get(0).getDate());
		jl.setFont(f);
		jl.setBounds(200,125,300,30);
		jp3.add(jl);
		jl=new JLabel("出版社:"+bookarr.get(0).getPublishinghouse());
		jl.setFont(f);
		jl.setBounds(0,155,300,30);
		jp3.add(jl);
		jl=new JLabel("借书日期:");
		jl.setFont(f);
		jl.setBounds(0,200,100,30);
		jp3.add(jl);
		txt1=new JTextField();
		txt1.setBounds(100,200,200,30);
		jp3.add(txt1);
		jl=new JLabel("归还日期:");
		jl.setFont(f);
		jl.setBounds(0,230,100,30);
		jp3.add(jl);
		txt2=new JTextField();
		txt2.setBounds(100,230,180,30);
		jp3.add(txt2);

		JDatePicker jdp=new JDatePicker();
		jdp.setBounds(280,230,20,20);
		jdp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = jdp.getSelectedDate();
					txt2.setText(sdf.format(date));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		jp3.add(jdp);
		
		
		jp3.setBounds(170,0,405,300);
		jp1.add(jp3);
		
		//按钮
		JButton jb1=new JButton("返回");
		jb1.setBounds(50,400,200,100);
		jb1.setFont(f);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Borrow();
			}
		});
		jp1.add(jb1);
		
		JButton jb2=new JButton("借阅");
		jb2.setBounds(300,400,200,100);
		jb2.setFont(f);
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Prestrain.reader==0){
					int i=JOptionPane.showConfirmDialog(null, "是否绑定读者账号","还未绑定读者信息", JOptionPane.YES_OPTION);
					if(i==0){
						new RegisteredUsers();
					}else if(i==1){
						JOptionPane.showMessageDialog(null, "没有用于借阅的读者信息","警告",JOptionPane.CANCEL_OPTION);
					}
				}else{
					if(!"".equals(txt2.getText())){
						entry.Borrow borrow=new entry.Borrow();
						borrow.setBookcode(bookarr.get(0).getBookid());
						borrow.setReaderid(Prestrain.reader);
						if(new BorrowDaoImpl().select(borrow).size()==0){
							entry.Borrow borrow1=new entry.Borrow(Prestrain.reader,bookarr.get(0).getBookid(),txt1.getText(),txt2.getText());
							if(new BorrowDaoImpl().insert(borrow1)){
								JOptionPane.showMessageDialog(null, "借阅成功,尽情享受书本的魅力吧！","成功",JOptionPane.CANCEL_OPTION);
							}else{
								JOptionPane.showMessageDialog(null, "借阅失败,请检查后重试","失败",JOptionPane.CANCEL_OPTION);
							}
						}else{
							JOptionPane.showMessageDialog(null, "您已经借过这本书了");
						}
					}
				}
			}
		});
		jp1.add(jb2);
		
		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
		
		timer=new Timer(100,this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		txt1.setText(Prestrain.timearr[3] + "-" + Prestrain.timearr[4]
				+ "-" + Prestrain.timearr[5] + " " + Prestrain.timearr[0] + ":"
				+ Prestrain.timearr[1] + ":" + Prestrain.timearr[2] + "");
	}
}
