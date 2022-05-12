package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sunking.swing.JDatePicker;

import daoimpl.ReaderDaoImpl;
import entry.Reader;
import utils.Prestrain;

public class ReaderDeleteAndUpdate{
	private static JPanel jp1;
	private static JPanel jp2;
	private static JPanel jp4;
	private static JPanel jp5;
	private static JTextPane text1;
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
	private static ArrayList<Reader> readerarr=new ArrayList<Reader>();
	public static JPopupMenu jpm1;
	private static String readerid;
	private static String readers[]=new String[6];
	private static JButton jb2;

	public ReaderDeleteAndUpdate() {
		HomeFrame.jp2.removeAll();

		jp1 = new JPanel();
		HomeFrame.jp2.setLayout(new BorderLayout());
		HomeFrame.jp2.add(jp1);

		jp1.setLayout(new BorderLayout());

		jp2 = new JPanel();
		jp2.setPreferredSize(new Dimension(jp1.getWidth(), 300));
		jp2.setLayout(new BorderLayout());
		jp1.add(jp2);

		JLabel jl = new JLabel("读者删改");
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
				"请输入读者信息"));
		if (Prestrain.color.equals("black")) {
			jp5.setBackground(Color.black);
		}
		jp5.setPreferredSize(new Dimension(jp2.getWidth(), 500));
		jp5.setLayout(null);
		jp5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jp2.add(jp5, BorderLayout.SOUTH);

		text1 = new JTextPane();
		text1.setText("使用方法，先查询到需要修改的读者，再进行修改,在您使用系统时，如需帮助，请联系:\nzhibinhu666@gmail.com");
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
		textarr[2].setFont(new Font("微软雅黑", Font.BOLD, 12));
		JDatePicker jdp=new JDatePicker();
		jdp.setBounds(220,100,20,20);
		jdp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
		

		Font f1 = new Font("微软雅黑", Font.BOLD, 20);
		JButton jb  = new JButton("清空");
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
					ReaderDeleteAndUpdate.textarr[i].setText(null);
				}
			}
		});
		jp5.add(jb);

		jb2 = new JButton("查询");
		jb2.setBounds(340, 200, 130, 50);
		jb2.setIcon(new ImageIcon("img/搜索.png"));
		jb2.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
		}
		jb2.setFont(f1);
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jb2.getText().equals("查询")){
					Reader reader = new Reader(0, textarr[0].getText(), textarr[1]
							.getText(), textarr[2].getText()+"%", textarr[3].getText(),
							textarr[4].getText(), textarr[5].getText());
					readerarr=new ReaderDaoImpl().selectterm(reader);
					text1.setText("查询到"+readerarr.size()+"条读者信息");
				}else{
					Reader reader = new Reader(Integer.valueOf(readerid), textarr[0].getText(), textarr[1]
							.getText(), textarr[2].getText(), textarr[3].getText(),
							textarr[4].getText(), textarr[5].getText());
					System.out.println(reader.toString());
					if(new ReaderDaoImpl().update(reader)){
						text1.setText("修改成功");
					}else{
						text1.setText("修改失败");
					}
				}
			}
		});
		jp5.add(jb2);

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
		jb.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookCheckInTable();
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
		jb.setIcon(new ImageIcon("img/Book Management Imgs/表格.png"));
		jb.setBackground(Color.white);
		if (Prestrain.color == "black") {
			jb.setBackground(Color.gray);
			jb.setForeground(Color.white);
		}
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderDeleteAndUpdate();
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
		String[] name = { "读者id", "读者姓名", "读者备注", "读者身份证", "登记日期", "读者电话",
				"读者地址" };
		JTable table = new JTable(tableDate, name);
		if (Prestrain.color == "black") {
			table.setBackground(Color.black);
			table.setForeground(Color.gray);
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jpm1.show(table,e.getX(),e.getY());
				readerid=(table.getValueAt(table.getSelectedRow(), 0)).toString();
				String readername=(String) table.getValueAt(table.getSelectedRow(), 1);
				text1.setText("选中了读者编号:【"+readerid+"】,读者姓名:【"+readername+"】");
				for(int i=0;i<6;i++){
					readers[i]=(table.getValueAt(table.getSelectedRow(), i+1)).toString();
				}
			}
		});
		jp.add(new JScrollPane(table));
		
		// 弹出菜单
		jpm1 = new JPopupMenu();
		jpm1.setPopupSize(50, 50);
		JMenuItem jm1;
		jm1 = new JMenuItem("删除");
		jm1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jm1.setActionCommand("jm1-1");
		jm1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!"".equals(readerid)){
					if(new ReaderDaoImpl().delete(Integer.valueOf(readerid))){
						text1.setText("删除成功");
					}else{
						text1.setText("删除失败");
					}
				}else{
					text1.setText("还未选择任何读者");
				}
			}
		});
		jpm1.add(jm1);
		jm1 = new JMenuItem("修改");
		jm1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jm1.setActionCommand("jm1-2");
		jm1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderDeleteAndUpdate();
				textarr[0].setText(readers[0]);
				textarr[1].setText(readers[2]);
				textarr[2].setText(readers[3]);
				textarr[3].setText(readers[4]);
				textarr[4].setText(readers[5]);
				textarr[5].setText(readers[1]);
				jb2.setText("修改");
				jb2.setIcon(new ImageIcon("img/修改.png"));
				jb2.repaint();
				jb2.revalidate();
			}
		});
		jpm1.add(jm1);
				
		jp5.add(jp);

		jp5.repaint();
		jp5.revalidate();
	}
}
