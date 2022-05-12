package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import utils.Prestrain;
import utils.Scaling;
import listener.BorrowBookActionListener;
import listener.BorrowTypeActionListner;
import listener.BorrowDocunebtListener;
import daoimpl.BookManagementDaoImpl;
import entry.Book;

public class Borrow {
	private static JPanel jp2_1;// 筛选
	public static JPanel jp3;// 书架
	public static JTextField txt1;
	public static ArrayList<JButton> jbarr = new ArrayList<JButton>();
	public static ArrayList<Book> bookarr = new ArrayList<Book>();
	public static Timer timer;

	public Borrow() {
		HomeFrame.jp2.removeAll();

		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout(10, 10));

		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout(10, 10));
		txt1 = new JTextField("输入书名进行查询");
		txt1.setPreferredSize(new Dimension(jp1.getWidth(), 30));
		txt1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		txt1.getDocument().addDocumentListener(new BorrowDocunebtListener());
		txt1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				if(txt1.getText().equals("输入书名进行查询")){
					txt1.setText(null);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		jp2.add(txt1, BorderLayout.NORTH);
		// 筛选-
		jp2_1 = new JPanel();
		term();

		jp2.add(jp2_1, BorderLayout.CENTER);
		jp2.setPreferredSize(new Dimension(jp1.getWidth(), 100));
		// -
		// 书架
		jp3 = new JPanel();
		select();

		JScrollPane jsp = new JScrollPane(jp3);
		jsp.getVerticalScrollBar().setUnitIncrement(10);

		// jp1.add
		jp1.add(jp2, BorderLayout.NORTH);
		jp1.add(jsp, BorderLayout.CENTER);

		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();

		// 通知
		Scaling.frame = "Borrow";
	}

	// 筛选栏
	public void term() {
		ArrayList<Book> bookarr = new ArrayList<Book>();
		bookarr = new BookManagementDaoImpl().select();
		int jbindex = 0;
		JButton typejb;
		for (int i = 0; i < bookarr.size(); i++) {
			boolean the = true;
			for (int i2 = 0; i2 < i; i2++) {
				if (bookarr.get(i2).getBooktype()
						.equals(bookarr.get(i).getBooktype())) {
					the = false;
				}
			}
			if (the) {
				typejb = new JButton(bookarr.get(i).getBooktype());
				jbarr.add(jbindex, typejb);
				jbarr.get(jbindex).setFont(new Font("微软雅黑", Font.BOLD, 15));
				jbarr.get(jbindex).setBorder(null);
				jbarr.get(jbindex).setBackground(Color.white);
				jbarr.get(jbindex).setActionCommand(
						jbarr.get(jbindex).getText());
				jbarr.get(jbindex).addActionListener(
						new BorrowTypeActionListner());
				jbarr.get(jbindex).setFocusPainted(false);
				jp2_1.setLayout(new GridLayout(jbindex / 6 + 1, 6, 5, 5));
				jp2_1.add(jbarr.get(jbindex));
				jbindex++;
			}
		}
	}

	// 查询全部书架
	public static void select() {
		if(!txt1.getText().equals("输入书名进行查询") && !txt1.getText().equals("")){
			Book book=new Book();
			book.setBookname(Borrow.txt1.getText());
			String type="";
			for(int i=0;i<Borrow.jbarr.size();i++){
				if(Borrow.jbarr.get(i).getBackground()==Color.gray){
					type=Borrow.jbarr.get(i).getText();
				}
			}
			book.setBooktype(type);
			Borrow.selectTerm(book);
			return;
		}
		jp3.removeAll();
		bookarr = new BookManagementDaoImpl().select();
		if (Prestrain.homestate) {
			jp3.setLayout(new GridLayout(bookarr.size() / 2 + 1, 2, 20, 20));
		} else {
			jp3.setLayout(new GridLayout(bookarr.size() / 3 + 1, 2, 20, 20));
		}
		JButton jb;
		for (int i = 0; i < bookarr.size(); i++) {
			jb = new JButton(bookarr.get(i).getBookname());
			jb.setIcon(new ImageIcon("img/HomeImgs/1.png"));
			jb.setFont(new Font("微软雅黑", Font.BOLD, 15));
			jb.setFocusPainted(false);
			jb.setHorizontalTextPosition(JButton.CENTER);
			jb.setVerticalTextPosition(JButton.BOTTOM);
			jb.setActionCommand(jb.getText());
			jb.addActionListener(new BorrowBookActionListener());
			jb.setBackground(Color.white);
			jp3.add(jb);
		}
		jp3.repaint();
		jp3.revalidate();
	}

	// 条件查询书架
	public static void selectTerm(Book book) {
		jp3.removeAll();
		bookarr = new BookManagementDaoImpl().selectTerm(book);
		if (Prestrain.homestate) {
			jp3.setLayout(new GridLayout(bookarr.size() / 2 + 1, 2, 20, 20));
		} else {
			jp3.setLayout(new GridLayout(bookarr.size() / 3 + 1, 2, 20, 20));
		}
		JButton jb;
		for (int i = 0; i < bookarr.size(); i++) {
			jb = new JButton(bookarr.get(i).getBookname());
			jb.setIcon(new ImageIcon("img/HomeImgs/1.png"));
			jb.setFont(new Font("微软雅黑", Font.BOLD, 15));
			jb.setActionCommand(jb.getText());
			jb.addActionListener(new BorrowBookActionListener());
			jb.setFocusPainted(false);
			jb.setHorizontalTextPosition(JButton.CENTER);
			jb.setVerticalTextPosition(JButton.BOTTOM);
			jb.setBackground(Color.white);
			jp3.add(jb);
		}
		jp3.repaint();
		jp3.revalidate();
	}
}
