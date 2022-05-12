package listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Borrow;
import entry.Book;

public class BorrowTypeActionListner implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean bookcolor=false;
		for(int i=0;i<Borrow.jbarr.size();i++){
			if(Borrow.jbarr.get(i).getBackground()==Color.gray){
				if(Borrow.jbarr.get(i).getText().equals(e.getActionCommand())){
					bookcolor=true;
				}
			}
		}
		for(int i=0;i<Borrow.jbarr.size();i++){
			Borrow.jbarr.get(i).setBackground(Color.white);
		}
		if(!bookcolor){
			Book book=new Book();
			if(!"输入书名进行查询".equals(Borrow.txt1.getText())){
				book.setBookname(Borrow.txt1.getText());
			}
			book.setBooktype(e.getActionCommand());
			Borrow.selectTerm(book);
			for(int i=0;i<Borrow.jbarr.size();i++){
				if(Borrow.jbarr.get(i).getText().equals(e.getActionCommand())){
					Borrow.jbarr.get(i).setBackground(Color.gray);
				}
			}
		}else{
			Borrow.select();
		}
	}

}
