package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utils.Prestrain;
import utils.Scaling;

public class HomeLave {
	public static JPanel jp2=new JPanel();
	private JPasswordField text;
	
	public HomeLave(){
		HomeFrame.BookManage();
		
		HomeFrame.jp2.removeAll();
		
		JPanel jp1=new JPanel();
		jp1.setLayout(null);
		Border border=new LineBorder(Color.black,2);
		if(Prestrain.color.equals("black")){
			border=new LineBorder(Color.gray,2);
		}
		jp2.setBorder(new TitledBorder(border,"已锁定:",1,2,new Font("微软雅黑",Font.BOLD,20)));
		jp2.setLayout(null);
		jp2.setBounds(200,200,200,200);
		JLabel jl;
		
		jl=new JLabel("请输入密码解锁");
		jl.setFont(new Font("微软雅黑",Font.BOLD,20));
		jl.setBounds(30,30,150,30);
		if(Prestrain.color.equals("black")){
			jl.setForeground(Color.orange);
		}
		jp2.add(jl);
		
		text=new JPasswordField();
		text.setFont(new Font("微软雅黑",Font.BOLD,20));
		text.setBounds(25,80,150,30);
		if(Prestrain.color.equals("black")){
			text.setForeground(Color.orange);
			text.setBackground(Color.gray);
		}
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					js();
				}
			}
		});
		jp2.add(text);
		
		JButton jb=new JButton("解锁");
		jb.setBounds(50,130,100,50);
		jb.setFont(new Font("微软雅黑",Font.BOLD,20));
		if(Prestrain.color.equals("black")){
			jb.setForeground(Color.orange);
			jb.setBackground(Color.gray);
		}
		jb.setFocusable(false);
		jb.setFocusPainted(false);
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				js();
			}
		});
		jp2.add(jb);
		
		for(int i=0;i<4;i++){
			HomeFrame.jbarr[i].setEnabled(false);
		}
		HomeFrame.jb5.setEnabled(false);
		HomeFrame.jp2.removeAll();
		for(int i=0;i<6;i++){
			HomeFrame.menuarr[i].setEnabled(false);
		}
		
		jp1.add(jp2);
		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();
		
		//获得焦点
		text.requestFocus();
		
		if(Prestrain.color.equals("black")){
			jp1.setBackground(Color.black);
			jp2.setBackground(Color.black);
		}
		
		Scaling.frame="HomeLave";
	}
	
	public void js(){
		String password = "";
		for(int i=0;i<text.getPassword().length;i++){
			password+=text.getPassword()[i];
		}
		if(password.equals(Prestrain.password)){
			for(int i=0;i<4;i++){
				HomeFrame.jbarr[i].setEnabled(true);
			}
			HomeFrame.jb5.setEnabled(true);
			for(int i=0;i<6;i++){
				HomeFrame.menuarr[i].setEnabled(true);
			}
			HomeFrame.lock=Prestrain.lock;
			HomeFrame.jp2.removeAll();
			HomeFrame.jp2.repaint();
			HomeFrame.jp2.revalidate();
			
			//檢測權限等級
			if(!"管理员".equals(Prestrain.rank)){
				HomeFrame.jbarr[0].setEnabled(false);
				HomeFrame.jbarr[2].setEnabled(false);
				HomeFrame.jbarr[3].setEnabled(false);
			}
		}else{
			JOptionPane.showMessageDialog(null, "密码错误，请输入您的登录密码");
		}
	}
}
