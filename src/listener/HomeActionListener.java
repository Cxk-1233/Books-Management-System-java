package listener;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import daoimpl.AdminDaoImpl;
import entry.Admin;
import utils.Prestrain;
import view.AccountInformation;
import view.BookAmend;
import view.BookCheckIn;
import view.BookLose;
import view.BookQuery;
import view.BookStatistics;
import view.Borrow;
import view.BorrowBook;
import view.BorrowBookRenew;
import view.HomeFrame;
import view.HomeLave;
import view.LAFrame;
import view.LoginFrame;
import view.OverdueBook;
import view.ReaderDeleteAndUpdate;
import view.ReaderManage;
import view.ReaderMessage;
import view.RegisterFrame;
import view.ReturnBook;
import view.SystemManagement;

//主页的按钮点击事件
public class HomeActionListener implements ActionListener {
	private HomeFrame frame;

	public HomeActionListener(HomeFrame frame) {
		this.frame = frame;
	}

	public HomeActionListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//关闭计时器
		try {
			ReturnBook.timer.stop();
			Borrow.timer.stop();
			BookCheckIn.time.stop();
			BorrowBook.timer.stop();
			RegisterFrame.timer.stop();
		} catch (Exception e2) {
			
		}
		
		HomeFrame.jp2.setLayout(new GridLayout(1, 8, 10, 10));
		if (e.getActionCommand().equals("jb1")) {
			HomeFrame.jp2.removeAll();
			HomeFrame.BookManage();
			HomeFrame.jp2.repaint();
			HomeFrame.jp2.revalidate();
			for (int i = 0; i < 4; i++) {
				HomeFrame.jbarr[i].setBackground(null);
				if(Prestrain.color.equals("black")){
					HomeFrame.jbarr[i].setBackground(Color.darkGray);
				}
			}
			HomeFrame.jbarr[0].setBackground(new Color(240, 240, 240));
			if(Prestrain.color.equals("black")){
				HomeFrame.jbarr[0].setBackground(Color.gray);
			}
		} else if (e.getActionCommand().equals("jb2")) {
			HomeFrame.jp2.removeAll();
			HomeFrame.LibraryManagement();
			HomeFrame.jp2.repaint();
			HomeFrame.jp2.revalidate();
			for (int i = 0; i < 4; i++) {
				HomeFrame.jbarr[i].setBackground(null);
				if(Prestrain.color.equals("black")){
					HomeFrame.jbarr[i].setBackground(Color.darkGray);
				}
			}
			HomeFrame.jbarr[1].setBackground(new Color(240, 240, 240));
			if(Prestrain.color.equals("black")){
				HomeFrame.jbarr[1].setBackground(Color.gray);
			}
		} else if (e.getActionCommand().equals("jb3")) {
			HomeFrame.jp2.removeAll();
			HomeFrame.TheReaderManagement();
			HomeFrame.jp2.repaint();
			HomeFrame.jp2.revalidate();
			for (int i = 0; i < 4; i++) {
				HomeFrame.jbarr[i].setBackground(null);
				if(Prestrain.color.equals("black")){
					HomeFrame.jbarr[i].setBackground(Color.darkGray);
				}
			}
			HomeFrame.jbarr[2].setBackground(new Color(240, 240, 240));
			if(Prestrain.color.equals("black")){
				HomeFrame.jbarr[2].setBackground(Color.gray);
			}
		} else if (e.getActionCommand().equals("jb4")) {
			HomeFrame.jp2.removeAll();
			new SystemManagement();
			HomeFrame.jp2.repaint();
			HomeFrame.jp2.revalidate();
			for (int i = 0; i < 4; i++) {
				HomeFrame.jbarr[i].setBackground(null);
				if(Prestrain.color.equals("black")){
					HomeFrame.jbarr[i].setBackground(Color.darkGray);
				}
			}
			HomeFrame.jbarr[3].setBackground(new Color(240, 240, 240));
			if(Prestrain.color.equals("black")){
				HomeFrame.jbarr[3].setBackground(Color.gray);
			}
		} else if (e.getActionCommand().equals("jb5")) {
			HomeFrame.jpm1.show(HomeFrame.jb5, 0, 0);
		} else if (e.getActionCommand().equals("jb61")) {
			new BookCheckIn();
		} else if (e.getActionCommand().equals("jb62")) {
			new BookLose();
		} else if (e.getActionCommand().equals("jb63")) {
			new BookAmend();
		} else if (e.getActionCommand().equals("jb64")) {
			new BookQuery();
		} else if (e.getActionCommand().equals("jb65")) {
			new BookStatistics();
		} else if (e.getActionCommand().equals("jb71")) {
			new Borrow();
		} else if (e.getActionCommand().equals("jb72")) {
			new BorrowBookRenew();
		} else if (e.getActionCommand().equals("jb73")) {
			new ReturnBook();
		} else if (e.getActionCommand().equals("jb74")) {
			new OverdueBook();
		} else if (e.getActionCommand().equals("jb81")) {
			new ReaderManage();
		} else if (e.getActionCommand().equals("jb82")) {
			new ReaderMessage();
		} else if (e.getActionCommand().equals("jb83")) {
			new ReaderDeleteAndUpdate();
		}

		// 登录用户
		if (e.getActionCommand().equals("jm1-1")) {
			Prestrain.user=null;
			Prestrain.password=null;
			Prestrain.lock=0;
			Prestrain.reader=0;
			new LoginFrame();
			frame.setVisible(false);
		} else if (e.getActionCommand().equals("jm1-2")) {
			new HomeLave();
		} else if (e.getActionCommand().equals("jm1-3")) {
			new AccountInformation();
		} else if (e.getActionCommand().equals("menu1")) {
			Admin admin = new Admin();
			admin.setColor("white");
			new AdminDaoImpl().update(admin);
			frame.getContentPane().repaint();
			frame.setVisible(false);
			new LAFrame();
		} else if (e.getActionCommand().equals("menu2")) {
			Admin admin = new Admin();
			admin.setColor("black");
			new AdminDaoImpl().update(admin);
			frame.setVisible(false);
			new LAFrame();
		} else if (e.getActionCommand().equals("menu3")) {
			Admin admin = new Admin();
			admin.setLock(0);
			new AdminDaoImpl().update(admin);
			frame.setVisible(false);
			new LAFrame();
		} else if (e.getActionCommand().equals("menu4")) {
			Admin admin = new Admin();
			admin.setLock(3000);
			new AdminDaoImpl().update(admin);
			frame.setVisible(false);
			new LAFrame();
		} else if (e.getActionCommand().equals("menu5")) {
			Admin admin = new Admin();
			admin.setLock(6000);
			new AdminDaoImpl().update(admin);
			frame.setVisible(false);
			new LAFrame();
		} else if (e.getActionCommand().equals("menu6")) {
			Admin admin = new Admin();
			admin.setLock(18000);
			new AdminDaoImpl().update(admin);
			frame.setVisible(false);
			new LAFrame();
		}
	}

}
