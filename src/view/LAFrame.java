package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import utils.Prestrain;
import utils.SendEamil;

@SuppressWarnings("serial")
public class LAFrame extends JFrame implements ActionListener {
	private Container c;
	private JLabel jl;
	private JLabel jl2;
	private int i=0;
	private int i2=0;
	private Timer timer=new Timer(1, this);
	private JPanel jp;
	
	public LAFrame(){
//		super(f,true);
		this.setSize(600,140);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		
		c=this.getContentPane();
		c.setLayout(null);
		jl=new JLabel("欢迎使用图书管理系统");
		jl.setFont(new Font("华文行楷", Font.PLAIN, 30));
		jl.setBounds(150,10,300,30);
		c.add(jl);
		jp=new JPanel();
		jp.setLayout(null);
		jp.setBounds(20,90,550,35);
		jp.setBorder(new LineBorder(Color.black, 1));
		c.add(jp);
		jl2=new JLabel("正在载入");
		jl2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		jl2.setBounds(225,45,150,30);
		c.add(jl2);
		
		timer.start();
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(i==10){
			jl2.setText("正在准备基本信息");
			new Prestrain();
			jl2.setText("正在载入");
		}else if(i==30){
			jl2.setText("正在获取账号信息");
			Prestrain.Prestrain2();
			jl2.setText("正在载入");
		}else if(i==50){
			jl2.setText("正在准备数据写入器");
			Prestrain.Prestrain3();
			jl2.setText("正在载入");
		}else if(i==80){
			jl2.setText("正在准备数据读取器");
			Prestrain.Prestrain4();
			jl2.setText("正在载入");
		}else if(i==90){
			jl2.setText("正在准备邮件发送");
			new SendEamil();
			jl2.setText("正在载入");
		}
		jl=new JLabel("/");
		jl.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		jl.setBounds(i,0,150,30);
		jp.add(jl);
		jl2.setText(jl2.getText()+".");
		if(i2==3){
			i2=-1;
			jl2.setText("正在载入");
		}
		i+=10;
		i2++;
		this.getContentPane().repaint();
		if(i==550){
			jl2.setText("加载成功");
			timer.stop();
			new HomeFrame().setVisible(true);
			this.dispose();
		}
	}
}
