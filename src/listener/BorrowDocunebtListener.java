package listener;

import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.Borrow;
import entry.Book;

public class BorrowDocunebtListener implements DocumentListener{

	@Override
	public void changedUpdate(DocumentEvent e) {
		insertUpdate(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		Book book=new Book();
		if(!"输入书名进行查询".equals(Borrow.txt1.getText())){
			book.setBookname(Borrow.txt1.getText());
		}
		String type="";
		for(int i=0;i<Borrow.jbarr.size();i++){
			if(Borrow.jbarr.get(i).getBackground()==Color.gray){
				type=Borrow.jbarr.get(i).getText();
			}
		}
		book.setBooktype(type);
		Borrow.selectTerm(book);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		insertUpdate(e);
	}
	
}
