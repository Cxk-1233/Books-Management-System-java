package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import daoimpl.BookManagementDaoImpl;
import entry.Book;

public class BookStatistics {
	private static JPanel jp2_1;// 筛选
	public static JPanel jp3;// 书架
	private static int books;//数量
	public static JTextField txt1;
	public static ArrayList<JButton> jbarr = new ArrayList<JButton>();
	public static ArrayList<Book> bookarr = new ArrayList<Book>();
	public static Timer timer;

	public BookStatistics() {
		HomeFrame.jp2.removeAll();

		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout(10, 10));

		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout(10, 10));
		txt1 = new JTextField("输入书名进行查询");
		txt1.setPreferredSize(new Dimension(jp1.getWidth(), 30));
		txt1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		txt1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Book book=new Book();
				if(!"输入书名进行查询".equals(txt1.getText())){
					book.setBookname(txt1.getText());
				}
				String type="";
				for(int i=0;i<jbarr.size();i++){
					if(jbarr.get(i).getBackground()==Color.gray){
						type=jbarr.get(i).getText();
					}
				}
				book.setBooktype(type);
				selectTerm(book);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				insertUpdate(e);
			}
		});
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
		jp3.setLayout(null);
		select();

		// jp1.add
		jp1.add(jp2, BorderLayout.NORTH);
		jp1.add(jp3, BorderLayout.CENTER);

		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
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
				jbarr.get(jbindex).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean bookcolor=false;
						for(int i=0;i<jbarr.size();i++){
							if(jbarr.get(i).getBackground()==Color.gray){
								if(jbarr.get(i).getText().equals(e.getActionCommand())){
									bookcolor=true;
								}
							}
						}
						for(int i=0;i<jbarr.size();i++){
							jbarr.get(i).setBackground(Color.white);
						}
						if(!bookcolor){
							Book book=new Book();
							if(!"输入书名进行查询".equals(txt1.getText())){
								book.setBookname(Borrow.txt1.getText());
							}
							book.setBooktype(e.getActionCommand());
							selectTerm(book);
							for(int i=0;i<jbarr.size();i++){
								if(jbarr.get(i).getText().equals(e.getActionCommand())){
									jbarr.get(i).setBackground(Color.gray);
								}
							}
						}else{
							select();
						}
					}
				});
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
			book.setBookname(txt1.getText());
			String type="";
			for(int i=0;i<jbarr.size();i++){
				if(jbarr.get(i).getBackground()==Color.gray){
					type=jbarr.get(i).getText();
				}
			}
			book.setBooktype(type);
			Borrow.selectTerm(book);
			return;
		}
		jp3.removeAll();
		bookarr = new BookManagementDaoImpl().select();
		books=0;
		for (int i = 0; i < bookarr.size(); i++) {
			books++;
		}
		JLabel jl=new JLabel("查询到"+books+"本符合条件的图书");
		jl.setFont(new Font("微软雅黑",Font.BOLD,20));
		jl.setBounds(20,20,500,30);
		jp3.add(jl);
		jp3.repaint();
		jp3.revalidate();
	}

	// 条件查询书架
	public static void selectTerm(Book book) {
		jp3.removeAll();
		bookarr = new BookManagementDaoImpl().selectTerm(book);
		books=0;
		for (int i = 0; i < bookarr.size(); i++) {
			books++;
		}
		JLabel jl=new JLabel("查询到"+books+"本符合条件的图书");
		jl.setFont(new Font("微软雅黑",Font.BOLD,20));
		jl.setBounds(20,20,500,30);
		jp3.add(jl);
		jp3.repaint();
		jp3.revalidate();
	}
}
