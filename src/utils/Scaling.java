package utils;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.AccountInformation;
import view.Borrow;
import view.HomeFrame;
import view.HomeLave;
import view.OverdueBook;

public class Scaling implements ActionListener {
	public static String frame = "";

	public Scaling() {
		Timer timer = new Timer(1, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Borrow
		if (frame.equals("Borrow")) {
			if (Prestrain.homestate) {
				Borrow.jp3.setLayout(new GridLayout(
						Borrow.bookarr.size() / 2 + 1, 2, 20, 20));
			} else {
				Borrow.jp3.setLayout(new GridLayout(
						Borrow.bookarr.size() / 3 + 1, 2, 20, 20));
			}
		}

		// AccountInformation
		if (frame.equals("AccountInformation")) {
			if (Prestrain.homestate) {
				AccountInformation.jp3.setSize(200, 200);
				AccountInformation.jp4.setSize(530, 200);
			} else {
				AccountInformation.jp3.setSize(350, 200);
				AccountInformation.jp4.setSize(680, 200);
			}
		}

		// HomeLave
		if (frame.equals("HomeLave")) {
			if (Prestrain.homestate) {
				if (HomeLave.jp2.getLocation().x == 250) {
					HomeLave.jp2.setLocation(200, 200);
				}
			} else {
				if (HomeLave.jp2.getLocation().x == 200) {
					HomeLave.jp2.setLocation(250, 200);
				}
			}
		}

		// OverdueBook
		if (frame.equals("OverdueBook")) {
			OverdueBook.jp2.setBounds(10, 50, HomeFrame.jp2.getWidth() - 20,
					HomeFrame.jp2.getHeight() - 70);
			if(Prestrain.homestate){
				OverdueBook.jsp.setSize(540,200);
				OverdueBook.jl2.setSize(520,50);
			}else{
				OverdueBook.jsp.setSize(690,200);
				OverdueBook.jl2.setSize(670,50);
			}
		}
	}
}
