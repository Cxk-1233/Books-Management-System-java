package view;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import daoimpl.BorrowDaoImpl;
import daoimpl.ReaderDaoImpl;
import entry.Reader;
import utils.Prestrain;
import utils.Scaling;

public class OverdueBook {
	public static JPanel jp2;
	public static JScrollPane jsp;
	public static JLabel jl2;
	private int overdue=0;//逾期数量

	public OverdueBook() {
		if(Prestrain.reader==0){
			int i=JOptionPane.showConfirmDialog(null, "是否绑定读者账号","还未绑定读者信息", JOptionPane.YES_OPTION);
			if(i==0){
				new RegisteredUsers();
			}else if(i==1){
				JOptionPane.showMessageDialog(null, "没有用于借阅的读者信息","警告",JOptionPane.CANCEL_OPTION);
			}
			return;
		}
		HomeFrame.jp2.removeAll();

		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		Font f = new Font("微软雅黑", Font.BOLD, 20);
		JLabel jl;
		jl = new JLabel("逾期图书");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 25));
		jl.setBounds(0, 0, 100, 30);
		jp1.add(jl);

		jp2 = new JPanel();
		jp2.setLayout(null);
		Border b1 = new LineBorder(Color.black, 3);
		jp2.setBorder(new TitledBorder(b1, "个人信用报告", 2, 2, f));
		jp2.setBounds(10, 50, HomeFrame.jp2.getWidth() - 20,
				HomeFrame.jp2.getHeight() - 70);
		Reader reader = new Reader();
		reader.setReaderid(Prestrain.reader);
		jl2 = new JLabel("当前绑定的读者信息为   id：" + Prestrain.reader + " 姓名："
				+ new ReaderDaoImpl().selectterm(reader).get(0).getReadername());
		jl2.setFont(f);
		jl2.setBounds(20,20,520,50);
		jp2.add(jl2);
		
		ArrayList<entry.Borrow> borrowarr=new ArrayList<entry.Borrow>();
		entry.Borrow borrow=new entry.Borrow();
		borrow.setReaderid(Prestrain.reader);
		borrowarr=new BorrowDaoImpl().select(borrow);
		SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
		try {
			for(int i=0;i<borrowarr.size();i++){
				Date date1=ft.parse(borrowarr.get(i).getBorrowdate());
				Date date2=ft.parse(borrowarr.get(i).getReturndate());
				if(date2.compareTo(date1)<0){
					overdue++;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Object[][] tableDate = new Object[borrowarr.size()][4];
		for (int i = 0; i < borrowarr.size(); i++) {
			try {
				Date date1=ft.parse(borrowarr.get(i).getBorrowdate());
				Date date2=ft.parse(borrowarr.get(i).getReturndate());
				if(date2.compareTo(date1)<0){
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
		if (Prestrain.color.equals("black")) {
			table.setBackground(Color.black);
			table.setForeground(Color.gray);
		}
		jsp=new JScrollPane(table);
		jsp.setBounds(10,345,540,200);
		jp2.add(jsp);
		
		JLabel jl3=new JLabel("您的逾期图书数为【"+overdue+"】");
		if(overdue==0){
			jl3.setText(jl3.getText()+",表现优秀");
			JLabel icon=new JLabel(new ImageIcon("img/优秀.png"));
			icon.setBounds(300,100,200,200);
			jp2.add(icon);
		}else if(overdue>0){
			jl3.setText(jl3.getText()+",请联系管理员进行操作");
			JLabel icon=new JLabel(new ImageIcon("img/逾期.png"));
			icon.setBounds(300,100,300,200);
			jp2.add(icon);
		}
		jl3.setFont(f);
		jl3.setBounds(20,100,500,30);
		jp2.add(jl3);
		
		jp1.add(jp2);
		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();

		// 通知
		Scaling.frame = "OverdueBook";
	}
}
