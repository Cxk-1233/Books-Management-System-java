package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.BorrowBook;

public class BorrowBookActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new BorrowBook(e.getActionCommand().toString());
	}

}
