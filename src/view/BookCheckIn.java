package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import daoimpl.BookManagementDaoImpl;
import entry.Book;
import utils.Prestrain;

public class BookCheckIn implements ActionListener {
	private static JPanel jp1;
	private static JPanel jp2;
	private static JPanel jp4;
	private static JPanel jp5;
	private JTextPane text1;
	public static JTextField text2;
	public static JTextField text3;
	public static JTextField text4;
	public static JTextField text5;
	public static JTextField text6;
	public static JTextField text7;
	public static JTextField text8;
	public static JTextField text9;
	public static JTextField textarr[] = { text2, text3, text4, text5, text6,
			text7, text8, text9 };
	public static Timer time;

	public BookCheckIn() {
		HomeFrame.jp2.removeAll();

		jp1 = new JPanel();
		HomeFrame.jp2.setLayout(new BorderLayout());
		HomeFrame.jp2.add(jp1);

		jp1.setLayout(new BorderLayout());

		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(jp1.getWidth(), 300));
		jp2.setLayout(new BorderLayout());
		jp1.add(jp2);

		JLabel jl = new JLabel("新书登记管理");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl.setBounds(0, 0, 120, 30);
		jp2.add(jl, BorderLayout.NORTH);

		// 上半部分
		jp4 = new JPanel();
		jp4.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 5),
				"温馨提示"));
		if (Prestrain.color.equals("black")) {
			jp4.setBackground(Color.black);
		}
		jp4.setLayout(new BorderLayout());
		jp4.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jp2.add(jp4, BorderLayout.CENTER);

		jp5 = new JPanel();
		jp5.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 5),
				"请输入完整图书信息"));
		if (Prestrain.color.equals("black")) {
			jp5.setBackground(Color.black);
		}
		jp5.setPreferredSize(new Dimension(jp2.getWidth(), 500));
		jp5.setLayout(null);
		jp5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jp2.add(jp5, BorderLayout.SOUTH);

		text1 = new JTextPane();
		text1.setText("尊敬的用户！希望您能将图书信息填写完整便于您的管理,在您使用系统时，如需帮助，请联系:\nzhibinhu666@gmail.com");
		text1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		text1.setOpaque(false);
		jp4.add(text1);
		// -----------请输入完整的图书信息--------------
		Font f = new Font("微软雅黑", Font.BOLD, 15);
		String text[] = { "图书编码:", "图书名称:", "出版社:", "加入数量:", "图书类别:", "图书作者:",
				"图书价钱:", "入馆日期:" };
		int he = 20;
		int i2 = 4;
		for (int i = 0; i < 4; i++) {
			jl = new JLabel(text[i]);
			jl.setFont(f);
			jl.setBounds(20, he, 65, 30);
			textarr[i] = new JTextField();
			textarr[i].setBounds(90, he, 130, 30);
			textarr[i].setFont(new Font("微软雅黑", Font.BOLD, 15));
			if (Prestrain.color.equals("black")) {
				textarr[i].setForeground(Color.white);
				textarr[i].setBackground(Color.gray);
				textarr[i].setBorder(null);
			}
			jp5.add(jl);
			jp5.add(textarr[i]);

			jl = new JLabel(text[i2]);
			jl.setFont(f);
			jl.setBounds(270, he, 65, 30);
			textarr[i2] = new JTextField();
			textarr[i2].setBounds(340, he, 130, 30);
			textarr[i2].setFont(new Font("微软雅黑", Font.BOLD, 15));
			if (Prestrain.color.equals("black")) {
				textarr[i2].setForeground(Color.white);
				textarr[i2].setBackground(Color.gray);
				textarr[i2].setBorder(null);
			}
			jp5.add(jl);
			jp5.add(textarr[i2]);

			i2++;
			he += 40;
		}
		textarr[7].setEditable(false);
		textarr[7].setFont(new Font("微软雅黑", Font.BOLD, 12));

		Font f1 = new Font("微软雅黑", Font.BOLD, 20);
		JButton jb;
		jb = new JButton("清空");
		jb.setBounds(90, 200, 130, 50);
		jb.setIcon(new ImageIcon("img/清空.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
		}
		jb.setFont(f1);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					BookCheckIn.textarr[i].setText(null);
				}
			}
		});
		jp5.add(jb);

		jb = new JButton("添加");
		jb.setBounds(340, 200, 130, 50);
		jb.setIcon(new ImageIcon("img/添加.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
		}
		jb.setFont(f1);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) {
					if (textarr[i].getText().equals("")) {
						text1.setText("请填写完整的图书信息");
						return;
					}
				}
				Book book = new Book(textarr[0].getText(),
						textarr[1].getText(), textarr[2].getText(), Integer
								.valueOf(textarr[3].getText()), textarr[4]
								.getText(), textarr[5].getText(), Double
								.valueOf(textarr[6].getText()), textarr[7]
								.getText());
				if (new BookManagementDaoImpl().insert(book)) {
					text1.setText("添加成功");
				}
			}
		});
		jp5.add(jb);

		// ---------------------------------------

		// 下半部分
		jb = new JButton();
		jb.setIcon(new ImageIcon("img/Book Management Imgs/表格.png"));
		jb.setBounds(90, 270, 50, 50);
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookCheckInTable();
			}
		});
		jp5.add(jb);

		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();

		// 启动计时器
		time = new Timer(1, this);
		time.start();
	}

	public static void BookCheckInTable() {
		jp5.removeAll();

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setSize(jp5.getWidth(), jp5.getHeight());

		JButton jb = new JButton();
		jb.setIcon(new ImageIcon("img/Book Management Imgs/表格.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BookCheckIn();
			}
		});
		jp.add(jb, BorderLayout.NORTH);

		ArrayList<Book> bookarr = new ArrayList<Book>();
		bookarr = new BookManagementDaoImpl().select();
		Object[][] tableDate = new Object[bookarr.size()][8];
		for (int i = 0; i < bookarr.size(); i++) {
			tableDate[i][0] = bookarr.get(i).getBookid();
			tableDate[i][1] = bookarr.get(i).getBookname();
			tableDate[i][2] = bookarr.get(i).getPublishinghouse();
			tableDate[i][3] = bookarr.get(i).getQuantity();
			tableDate[i][4] = bookarr.get(i).getBooktype();
			tableDate[i][5] = bookarr.get(i).getName();
			tableDate[i][6] = bookarr.get(i).getBookprice();
			tableDate[i][7] = bookarr.get(i).getDate();
		}
		String[] name = { "图书编码", "图书名称", "出版社", "加入数量", "图书类别", "图书作者",
				"图书价钱", "入馆日期" };
		JTable table = new JTable(tableDate, name);
		if (Prestrain.color == "black") {
			table.setBackground(Color.black);
			table.setForeground(Color.gray);
		}
		jp.add(new JScrollPane(table));

		jp5.add(jp);

		jp5.repaint();
		jp5.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textarr[7].setText(Prestrain.timearr[3] + "-" + Prestrain.timearr[4]
				+ "-" + Prestrain.timearr[5] + " " + Prestrain.timearr[0] + ":"
				+ Prestrain.timearr[1] + ":" + Prestrain.timearr[2] + "");
	}
}
