package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import daoimpl.AdminDaoImpl;
import view.LAFrame;
import view.LoginFrame;

public class AdminLoginListner implements ActionListener{
	private LoginFrame frame;
	
	public AdminLoginListner(LoginFrame frame){
		this.frame=frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String password="";
		for(int i=0;i<frame.textField2.getPassword().length;i++){
			password+=frame.textField2.getPassword()[i];
		}
		if(new AdminDaoImpl().login(frame.textField1.getText(), password)){
			frame.setVisible(false);
			new LAFrame();
		}else{
			JOptionPane.showMessageDialog(frame, "账号或密码错误!");
		}
	}
	
}
