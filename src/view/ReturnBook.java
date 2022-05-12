package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import utils.Prestrain;
import daoimpl.BookManagementDaoImpl;
import daoimpl.BorrowDaoImpl;
import entry.Book;
import entry.Borrow;

public class ReturnBook implements ActionListener {
	private static JTextField txt1;
	private static JTextField txt2;
	private static ArrayList<Book> bookarr;

	private static JPanel jp1 = new JPanel();
	private static JPanel jp3;
	private static JLabel jl;
	private static JLabel jl1;
	private static JLabel jl2;
	private static JLabel jl3;
	private static JLabel jl4;
	private static JLabel jl5;
	private static JLabel jl6;
	private static JLabel jl7;
	public static Timer timer;
	private static String bookid;

	public ReturnBook() {
		if(Prestrain.reader==0){
			int i=JOptionPane.showConfirmDialog(null, "是否绑定读者账号","还未绑定读者信息", JOptionPane.YES_OPTION);
			if(i==0){
				new RegisteredUsers();
			}else if(i==1){
				JOptionPane.showMessageDialog(null, "没有用于借阅的读者信息","警告",JOptionPane.CANCEL_OPTION);
			}
			return;
		}
		
		bookarr = new ArrayList<Book>();
		Book book = new Book();
		bookarr.add(book);

		HomeFrame.jp2.removeAll();

		jp1.setLayout(new BorderLayout());

		JPanel jp1_1 = new JPanel();
		jp1_1.setLayout(null);

		jl = new JLabel("图书归还");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 25));
		jl.setBounds(5, 5, 200, 30);
		jp1_1.add(jl);

		// 书img
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridBagLayout());
		jp2.setBorder(new LineBorder(Color.black, 2));
		JLabel jlabel = new JLabel();
		jlabel.setIcon(new ImageIcon("img/HomeImgs/1.png"));
		jp2.add(jlabel);
		jp2.setBounds(5, 35, 150, 200);
		jp1_1.add(jp2);
		// 详细信息
		jp3 = new JPanel();
		jp3.setLayout(null);

		Font f = new Font("微软雅黑", Font.BOLD, 20);
		jl1 = new JLabel("编码:");
		jl1.setFont(f);
		jl1.setBounds(0, 35, 200, 30);
		jp3.add(jl1);
		jl2 = new JLabel("名称:");
		jl2.setFont(f);
		jl2.setBounds(0, 65, 200, 30);
		jp3.add(jl2);
		jl3 = new JLabel("作者:");
		jl3.setFont(f);
		jl3.setBounds(0, 95, 200, 30);
		jp3.add(jl3);
		jl4 = new JLabel("类别:");
		jl4.setFont(f);
		jl4.setBounds(200, 95, 200, 30);
		jp3.add(jl4);
		jl5 = new JLabel("价格:");
		jl5.setFont(f);
		jl5.setBounds(0, 125, 200, 30);
		jp3.add(jl5);
		jl6 = new JLabel("加入时间:");
		jl6.setFont(f);
		jl6.setBounds(200, 125, 300, 30);
		jp3.add(jl6);
		jl7 = new JLabel("出版社:");
		jl7.setFont(f);
		jl7.setBounds(0, 155, 300, 30);
		jp3.add(jl7);
		jl = new JLabel("归还日期:");
		jl.setFont(f);
		jl.setBounds(0, 200, 100, 30);
		jp3.add(jl);
		txt1 = new JTextField();
		txt1.setBounds(100, 200, 200, 30);
		txt1.setEditable(false);
		jp3.add(txt1);
		jl = new JLabel("实际归还日期:");
		jl.setFont(f);
		jl.setBounds(0, 230, 130, 30);
		jp3.add(jl);
		txt2 = new JTextField();
		txt2.setBounds(130, 230, 170, 30);
		txt2.setEditable(false);
		jp3.add(txt2);
		JButton jb = new JButton("确定");
		jb.setFont(f);
		jb.setBounds(300, 200, 100, 60);
		jb.setBackground(Color.white);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(new BorrowDaoImpl().delete(bookid,Prestrain.reader)){
					JOptionPane.showMessageDialog(null, "归还成功，爱护图书，人人有责！非常感谢您的使用");
					new ReturnBook();
				}else{
					JOptionPane.showMessageDialog(null, "归还失败,如有疑问，请联系管理员");
				}
			}
		});
		jp3.add(jb);
		jp3.setBounds(170, 0, 405, 300);

		jp1_1.add(jp3);

		BookBorrow();
		jp1.add(jp1_1, BorderLayout.CENTER);
		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
		
		//启动计时器
		timer = new Timer(1, this);
		timer.start();
	}

	public static void BookBorrow() {
		jp1.removeAll();
		ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();
		Borrow borrow = new Borrow();
		borrow.setReaderid(Prestrain.reader);
		borrowarr = new BorrowDaoImpl().select(borrow);

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());

		Object[][] tableDate = new Object[borrowarr.size()][4];
		for (int i = 0; i < borrowarr.size(); i++) {
			try {
				SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
				Date date1=ft.parse(borrowarr.get(i).getBorrowdate());
				Date date2=ft.parse(borrowarr.get(i).getReturndate());
				if(date2.compareTo(date1)>=0){
					tableDate[i][0] = borrowarr.get(i).getReaderid();
					tableDate[i][1] = borrowarr.get(i).getBookcode();
					tableDate[i][2] = borrowarr.get(i).getBorrowdate();
					tableDate[i][3] = borrowarr.get(i).getReturndate();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String[] name = { "读者id", "图书编号", "借阅时间", "归还时间" };
		JTable table = new JTable(tableDate, name);
		if (Prestrain.color == "black") {
			table.setBackground(Color.black);
			table.setForeground(Color.gray);
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() != -1) {
						Book book = new Book();
						book.setBookid((String) table.getValueAt(
								table.getSelectedRow(), 1));
						bookarr = new BookManagementDaoImpl().selectTerm(book);

						bookid=(table.getValueAt(table.getSelectedRow(), 1).toString());
						jl1.setText("编码:" + bookarr.get(0).getBookid());
						jl2.setText("名称:" + bookarr.get(0).getBookname());
						jl3.setText("作者:" + bookarr.get(0).getName());
						jl4.setText("类别:" + bookarr.get(0).getBooktype());
						jl5.setText("价格:" + bookarr.get(0).getBookprice());
						jl6.setText("加入时间:" + bookarr.get(0).getDate());
						jl7.setText("出版社:" + bookarr.get(0).getPublishinghouse());
						txt1.setText((String) table.getValueAt(table.getSelectedRow(), 3));
						
						jp3.repaint();
						jp3.revalidate();
					}
				} catch (Exception e2) {
					
				}
			}
		});
		jp.add(new JScrollPane(table));
		jp.setPreferredSize(new Dimension(jp1.getWidth(), 350));
		jp1.repaint();
		jp1.revalidate();

		jp1.add(jp, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		txt2.setText(Prestrain.timearr[3] + "-" + Prestrain.timearr[4] + "-"
				+ Prestrain.timearr[5] + " " + Prestrain.timearr[0] + ":"
				+ Prestrain.timearr[1] + ":" + Prestrain.timearr[2] + "");
	}
}
