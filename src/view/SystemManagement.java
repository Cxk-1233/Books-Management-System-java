package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utils.Prestrain;
import utils.SendEamil;
import daoimpl.BorrowDaoImpl;
import daoimpl.ReaderDaoImpl;
import entry.Borrow;
import entry.Reader;

public class SystemManagement {
	private int lendbooks;
	private int overdue;
	private int overdue2;

	public SystemManagement() {
		ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();
		borrowarr = new BorrowDaoImpl().select(new Borrow());
		for (int i = 0; i < borrowarr.size(); i++) {
			lendbooks++;
		}
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (int i = 0; i < borrowarr.size(); i++) {
				Date date1 = ft.parse(borrowarr.get(i).getBorrowdate());
				Date date2 = ft.parse(borrowarr.get(i).getReturndate());
				if (date2.compareTo(date1) < 0) {
					overdue++;
				}
				if (date2.compareTo(date1) == 0) {
					overdue2++;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HomeFrame.jp2.removeAll();

		JPanel jp1 = new JPanel();
		jp1.setLayout(null);

		JLabel jl;
		// ���ͼ��
		JPanel jp2 = new JPanel();
		Border b1 = new LineBorder(Color.black, 5);
		jp2.setBorder(new TitledBorder(b1, "���ͼ��", 2, 2, new Font("΢���ź�",
				Font.BOLD, 20)));
		jp2.setBounds(10, 10, 150, 150);
		jl = new JLabel(lendbooks + "");
		jl.setFont(new Font("΢���ź�", Font.BOLD, 70));
		jp2.add(jl);
		jp1.add(jp2);

		// ��������
		JPanel jp3 = new JPanel();
		jp3.setBorder(new TitledBorder(b1, "��������", 2, 2, new Font("΢���ź�",
				Font.BOLD, 20)));
		jp3.setBounds(215, 10, 150, 150);
		jl = new JLabel(overdue2 + "");
		jl.setFont(new Font("΢���ź�", Font.BOLD, 70));
		jp3.add(jl);
		jp1.add(jp3);

		// ����
		JPanel jp4 = new JPanel();
		jp4.setBorder(new TitledBorder(b1, "����ͼ��", 2, 2, new Font("΢���ź�",
				Font.BOLD, 20)));
		jp4.setBounds(420, 10, 150, 150);
		jl = new JLabel(overdue + "");
		jl.setFont(new Font("΢���ź�", Font.BOLD, 70));
		jp4.add(jl);
		jp1.add(jp4);

		// ��ݹ���
		JButton jb = new JButton("������������");
		jb.setFont(new Font("΢���ź�", Font.BOLD, 18));
		jb.setBounds(15, 200, 200, 50);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();
				borrowarr = new BorrowDaoImpl().select(new Borrow());
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				int fs = 0;// �ɹ����ʹ���
				try {
					for (int i = 0; i < borrowarr.size(); i++) {
						Date date1 = ft.parse(borrowarr.get(i).getBorrowdate());
						Date date2 = ft.parse(borrowarr.get(i).getReturndate());
						// ��ѯ��������ͼ��
						if (date2.compareTo(date1) == 0) {
							Reader reader = new Reader();
							reader.setReaderid(borrowarr.get(i).getReaderid());
							new SendEamil(new ReaderDaoImpl()
									.selectterm(reader).get(0).getReadertel(),
									"ͼ�鼴����������",
									"����ͼ�鼴�����ڣ����ڳ�ŵ���ڽ�ͼ��黹������ͼ�飬�������𣡸�л����ʹ��");
							fs++;
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {

				} catch (GeneralSecurityException e1) {

				}
				JOptionPane.showMessageDialog(null, "�ɹ�����" + fs + "���ʼ�");
			}
		});
		jp1.add(jb);
		jb = new JButton("����ͼ������");
		jb.setFont(new Font("΢���ź�", Font.BOLD, 18));
		jb.setBounds(225, 200, 200, 50);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Borrow> borrowarr = new ArrayList<Borrow>();
				borrowarr = new BorrowDaoImpl().select(new Borrow());
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
				int fs = 0;// �ɹ����ʹ���
				try {
					for (int i = 0; i < borrowarr.size(); i++) {
						Date date1 = ft.parse(borrowarr.get(i).getBorrowdate());
						Date date2 = ft.parse(borrowarr.get(i).getReturndate());
						// ��ѯ��������ͼ��
						if (date2.compareTo(date1) < 0) {
							Reader reader = new Reader();
							reader.setReaderid(borrowarr.get(i).getReaderid());
							new SendEamil(new ReaderDaoImpl()
									.selectterm(reader).get(0).getReadertel(),
									"ͼ����������",
									"����ͼ���Ѿ����ڣ�����ϵ����Ա���й黹���Ա���������ĲƲ���ʧ������ͼ�飬�������𣡸�л����ʹ��");
							fs++;
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {

				} catch (GeneralSecurityException e1) {

				}
				JOptionPane.showMessageDialog(null, "�ɹ�����" + fs + "���ʼ�");
			}
		});
		jp1.add(jb);

		JPanel jp5 = new JPanel();
		jp5.setLayout(new BorderLayout());
		jp5.setBorder(new TitledBorder(new LineBorder(Color.black, 2),
				"����ͼ������", 1, 2, new Font("΢���ź�", Font.BOLD, 20)));
		jp5.setBounds(10, 300, 550, 300);
		Borrow borrow = new Borrow();
		borrowarr = new BorrowDaoImpl().select(borrow);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		Object[][] tableDate = new Object[overdue][4];
		int i2 = 0;
		for (int i = 0; i < borrowarr.size(); i++) {
			try {
				Date date1 = ft.parse(borrowarr.get(i).getBorrowdate());
				Date date2 = ft.parse(borrowarr.get(i).getReturndate());
				if (date2.compareTo(date1) < 0) {
					tableDate[i2][0] = borrowarr.get(i).getReaderid();
					tableDate[i2][1] = borrowarr.get(i).getBookcode();
					tableDate[i2][2] = borrowarr.get(i).getBorrowdate();
					tableDate[i2][3] = borrowarr.get(i).getReturndate();
					i2++;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String[] name = { "����id", "ͼ����", "����ʱ��", "�黹ʱ��" };
		JTable table = new JTable(tableDate, name);
		if (Prestrain.color == "black") {
			table.setBackground(Color.black);
			table.setForeground(Color.gray);
		}
		// �����˵�
		JPopupMenu jpm1 = new JPopupMenu();
		jpm1.setPopupSize(50, 30);
		JMenuItem jm1;
		jm1 = new JMenuItem("ɾ��");
		jm1.setFont(new Font("΢���ź�", Font.BOLD, 18));
		jm1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int readerid = Integer.valueOf((table.getValueAt(
						table.getSelectedRow(), 0)).toString());
				String bookid = table.getValueAt(table.getSelectedRow(), 1)
						.toString();
				if (new BorrowDaoImpl().delete(bookid, readerid)) {
					JOptionPane.showMessageDialog(null, "�����ɹ�");
					new SystemManagement();
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ��");
				}
			}
		});
		jpm1.add(jm1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jpm1.show(jp5, e.getX(), e.getY());
			}
		});
		JScrollPane jsp = new JScrollPane(table);
		jp5.add(jsp);
		jp1.add(jp5);

		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
	}
}
