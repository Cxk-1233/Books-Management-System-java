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

import daoimpl.ReaderDaoImpl;
import entry.Reader;
import utils.Prestrain;

public class ReaderManage implements ActionListener {
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

	public ReaderManage() {
		HomeFrame.jp2.removeAll();

		jp1 = new JPanel();
		HomeFrame.jp2.setLayout(new BorderLayout());
		HomeFrame.jp2.add(jp1);

		jp1.setLayout(new BorderLayout());

		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(jp1.getWidth(), 300));
		jp2.setLayout(new BorderLayout());
		jp1.add(jp2);

		JLabel jl = new JLabel("读者登记管理");
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
				"请输入完整读者信息"));
		if (Prestrain.color.equals("black")) {
			jp5.setBackground(Color.black);
		}
		jp5.setPreferredSize(new Dimension(jp2.getWidth(), 500));
		jp5.setLayout(null);
		jp5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jp2.add(jp5, BorderLayout.SOUTH);

		text1 = new JTextPane();
		text1.setText("尊敬的用户！希望您能将读者信息填写完整便于您的管理,在您使用系统时，如需帮助，请联系:\nzhibinhu666@gmail.com");
		text1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		text1.setOpaque(false);
		jp4.add(text1);
		// -----------请输入完整的图书信息--------------
		Font f = new Font("微软雅黑", Font.BOLD, 15);
		String text[] = { "读者姓名:", "身份证:", "登记日期:", "读者电话:", "读者地址:", "读者备注:" };
		int he = 20;
		int i2 = 3;
		for (int i = 0; i < 3; i++) {
			jl = new JLabel(text[i]);
			jl.setFont(f);
			jl.setBounds(20, he, 65, 30);
			textarr[i] = new JTextField();
			textarr[i].setBounds(90, he, 130, 30);
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
		textarr[2].setEditable(false);

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
				for (int i = 0; i < 6; i++) {
					ReaderManage.textarr[i].setText(null);
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
				for (int i = 0; i < 5; i++) {
					if (textarr[i].getText().equals("")) {
						text1.setText("请填写完整的读者信息");
						return;
					}
				}
				Reader reader = new Reader(0, textarr[0].getText(), textarr[1]
						.getText(), textarr[2].getText(), textarr[3].getText(),
						textarr[4].getText(), textarr[5].getText());
				 if (new ReaderDaoImpl().insert(reader)) {
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
		Timer time = new Timer(1, this);
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
				new ReaderManage();
			}
		});
		jp.add(jb, BorderLayout.NORTH);

		ArrayList<Reader> readerarr = new ArrayList<Reader>();
		readerarr = new ReaderDaoImpl().select();
		Object[][] tableDate = new Object[readerarr.size()][7];
		for (int i = 0; i < readerarr.size(); i++) {
			tableDate[i][0] = readerarr.get(i).getReaderid();
			tableDate[i][1] = readerarr.get(i).getReadername();
			tableDate[i][2] = readerarr.get(i).getReaderremark();
			tableDate[i][3] = readerarr.get(i).getReadercardid();
			tableDate[i][4] = readerarr.get(i).getReaderdate();
			tableDate[i][5] = readerarr.get(i).getReadertel();
			tableDate[i][6] = readerarr.get(i).getReaderaddress();
		}
		String[] name = { "读者id", "读者姓名", "读者备注", "读者身份证", "登记日期", "读者电话",
				"读者地址" };
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
		textarr[2].setText(Prestrain.timearr[3] + "-" + Prestrain.timearr[4]
				+ "-" + Prestrain.timearr[5] + " " + Prestrain.timearr[0] + ":"
				+ Prestrain.timearr[1] + ":" + Prestrain.timearr[2] + "");
	}
}
