package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sunking.swing.JDatePicker;

import daoimpl.BorrowDaoImpl;
import daoimpl.ReaderDaoImpl;
import entry.Borrow;
import entry.Reader;
import utils.Prestrain;

public class ReaderMessage {
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
	private static ArrayList<Reader> readerarr = new ArrayList<Reader>();
	private static ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();

	public ReaderMessage() {
		HomeFrame.jp2.removeAll();

		jp1 = new JPanel();
		HomeFrame.jp2.setLayout(new BorderLayout());
		HomeFrame.jp2.add(jp1);

		jp1.setLayout(new BorderLayout());

		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(jp1.getWidth(), 300));
		jp2.setLayout(new BorderLayout());
		jp1.add(jp2);

		JLabel jl = new JLabel("������Ϣ����");
		jl.setFont(new Font("΢���ź�", Font.BOLD, 20));
		jl.setBounds(0, 0, 120, 30);
		jp2.add(jl, BorderLayout.NORTH);

		// �ϰ벿��
		jp4 = new JPanel();
		jp4.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 5),
				"��ܰ��ʾ"));
		if (Prestrain.color.equals("black")) {
			jp4.setBackground(Color.black);
		}
		jp4.setLayout(new BorderLayout());
		jp4.setFont(new Font("΢���ź�", Font.BOLD, 15));
		jp2.add(jp4, BorderLayout.CENTER);

		jp5 = new JPanel();
		jp5.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 5),
				"�����������Ϣ"));
		if (Prestrain.color.equals("black")) {
			jp5.setBackground(Color.black);
		}
		jp5.setPreferredSize(new Dimension(jp2.getWidth(), 500));
		jp5.setLayout(null);
		jp5.setFont(new Font("΢���ź�", Font.BOLD, 15));
		jp2.add(jp5, BorderLayout.SOUTH);

		text1 = new JTextPane();
		text1.setText("�𾴵��û���ϣ�����ܽ���֪�Ķ�����Ϣ��д�������ڲ�ѯ,����ʹ��ϵͳʱ���������������ϵ:\nzhibinhu666@gmail.com");
		text1.setFont(new Font("΢���ź�", Font.BOLD, 15));
		text1.setOpaque(false);
		jp4.add(text1);
		// -----------������������ͼ����Ϣ--------------
		Font f = new Font("΢���ź�", Font.BOLD, 15);
		String text[] = { "��������:", "���֤:", "�Ǽ�����:", "���ߵ绰:", "���ߵ�ַ:", "���߱�ע:" };
		int he = 20;
		int i2 = 3;
		for (int i = 0; i < 3; i++) {
			jl = new JLabel(text[i]);
			jl.setFont(f);
			jl.setBounds(20, he, 65, 30);
			textarr[i] = new JTextField("");
			textarr[i].setBounds(90, he, 130, 30);
			textarr[i].setFont(new Font("΢���ź�", Font.BOLD, 15));
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
			textarr[i2].setFont(new Font("΢���ź�", Font.BOLD, 15));
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
		textarr[2].setFont(new Font("΢���ź�", Font.BOLD, 12));
		JDatePicker jdp = new JDatePicker();
		jdp.setBounds(220, 100, 20, 20);
		jdp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = jdp.getSelectedDate();
					textarr[2].setText(sdf.format(date));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		jp5.add(jdp);

		Font f1 = new Font("΢���ź�", Font.BOLD, 20);
		JButton jb;
		jb = new JButton("���");
		jb.setBounds(90, 200, 130, 50);
		jb.setIcon(new ImageIcon("img/���.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
		}
		jb.setFont(f1);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 6; i++) {
					ReaderMessage.textarr[i].setText(null);
				}
			}
		});
		jp5.add(jb);

		jb = new JButton("��ѯ");
		jb.setBounds(340, 200, 130, 50);
		jb.setIcon(new ImageIcon("img/����.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
		}
		jb.setFont(f1);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Reader reader = new Reader(0, textarr[0].getText(), textarr[1]
						.getText(), textarr[2].getText() + "%",
						textarr[3].getText(), textarr[4].getText(), textarr[5]
								.getText());
				readerarr = new ReaderDaoImpl().selectterm(reader);
				text1.setText("��ѯ��" + readerarr.size() + "��������Ϣ�Ͷ�Ӧ�Ľ��ļ�¼");
				
				Borrow borrow=new Borrow();
				borrowarr.clear();
				for(int i=0;i<readerarr.size();i++){
					borrow.setReaderid(readerarr.get(i).getReaderid());
					borrowarr.addAll(new BorrowDaoImpl().select(borrow));
				}
			}
		});
		jp5.add(jb);

		// ---------------------------------------

		// �°벿��
		jb = new JButton("������Ϣ��");
		jb.setIcon(new ImageIcon("img/Book Management Imgs/���.png"));
		jb.setBounds(90, 270, 170, 50);
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.setFont(new Font("΢���ź�", Font.BOLD, 15));
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookCheckInTable();
			}
		});
		jp5.add(jb);

		jb = new JButton("���ļ�¼");
		jb.setIcon(new ImageIcon("img/Book Management Imgs/���.png"));
		jb.setBounds(340, 270, 170, 50);
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.setFont(new Font("΢���ź�", Font.BOLD, 15));
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookBorrow();
			}
		});
		jp5.add(jb);

		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
	}

	public static void BookCheckInTable() {
		jp5.removeAll();

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setSize(jp5.getWidth(), jp5.getHeight());

		JButton jb = new JButton();
		jb.setIcon(new ImageIcon("img/Book Management Imgs/���.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderMessage();
			}
		});
		jp.add(jb, BorderLayout.NORTH);

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
		String[] name = { "����id", "��������", "���߱�ע", "�������֤", "�Ǽ�����", "���ߵ绰",
				"���ߵ�ַ" };
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

	public static void BookBorrow() {
		jp5.removeAll();

		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setSize(jp5.getWidth(), jp5.getHeight());

		JButton jb = new JButton();
		jb.setIcon(new ImageIcon("img/Book Management Imgs/���.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderMessage();
			}
		});
		jp.add(jb, BorderLayout.NORTH);

		Object[][] tableDate = new Object[borrowarr.size()][4];
		for (int i = 0; i < borrowarr.size(); i++) {
			tableDate[i][0] = borrowarr.get(i).getReaderid();
			tableDate[i][1] = borrowarr.get(i).getBookcode();
			tableDate[i][2] = borrowarr.get(i).getBorrowdate();
			tableDate[i][3] = borrowarr.get(i).getReturndate();
		}
		String[] name = { "����id", "ͼ����", "����ʱ��", "�黹ʱ��" };
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
}
