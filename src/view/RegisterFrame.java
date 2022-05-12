package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.swing.*;

import utils.SendEamil;
import utils.TimerStop;
import dao.RegisterDao;
import daoimpl.RegisterDaoimpl;
@SuppressWarnings("serial")
public class RegisterFrame extends JFrame implements ActionListener{
	private static JButton btn1;
	public static Timer timer;
	private int txt=0;
	private int yzm;
	
	public RegisterFrame(LoginFrame lf){
		this.setSize(380,500);
		Container c=this.getContentPane();
    	c.setLayout(null);
		this.setTitle("注册");
		Font f=new Font("幼圆",Font.PLAIN,25);
		
		ImageIcon icon1=new ImageIcon("img/未登录.png");
		JLabel jl1=new JLabel();
		jl1.setText("账号:");
		jl1.setIcon(icon1);
		jl1.setFont(f);
		jl1.setBounds(0,0,120,50);
		c.add(jl1);
		JTextField txt1=new JTextField(18);
		txt1.setBounds(125,10,230,30);
		c.add(txt1);
		
		ImageIcon icon2 =new ImageIcon("img/密码登录.png");
		JLabel jl2=new JLabel();
		jl2.setText("密码:");
		jl2.setIcon(icon2);
		jl2.setFont(f);
		jl2.setBounds(0,60,120,50);
		c.add(jl2);
		JTextField txt2=new JTextField(18);
		txt2.setBounds(125,70,230,30);
		c.add(txt2);
		
		ImageIcon icon3 =new ImageIcon("img/HomeImgs/2.png");
		JLabel jl3=new JLabel("邮箱:");
		jl3.setIcon(icon3);
		jl3.setFont(f);
		jl3.setBounds(0,120,120,50);
		c.add(jl3);
		JTextField txt3=new JTextField(18);
		txt3.setBounds(125,130,230,30);
		c.add(txt3);
		JLabel jl5=new JLabel("验证码:");
		jl5.setFont(f);
		jl5.setBounds(25,180,160,50);
		c.add(jl5);
		JTextField txt5=new JTextField(15);
		txt5.setBounds(125,190,150,30);
		c.add(txt5);
		btn1=new JButton("发送");
		btn1.setBounds(280,190,70,30);
		btn1.setBackground(Color.white);
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txt3.getText().equals("")){
					try {
						yzm=SecurityCode();
						new SendEamil(txt3.getText(),"欢迎注册，您的6位验证码为:"+yzm,"如有疑问，请联系管理员(作者：zhibinhu666@gmail.com,版权所有)");
						btn1.setText("120");
						txt=119;
						timer.start();
						btn1.setEnabled(false);
					} catch (MessagingException e1) {
						JOptionPane.showMessageDialog(null, "验证码发送失败,请检查后重试");
					} catch (GeneralSecurityException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "邮箱不能为空");
				}
			}
		});
		c.add(btn1);
		
		ImageIcon icon4 =new ImageIcon("img/清空.png");
		JLabel jl4=new JLabel("地址:");
		jl4.setIcon(icon4);
		jl4.setFont(f);
		jl4.setBounds(0,240,120,50);
		c.add(jl4);
		JTextField txt4=new JTextField(18);
		txt4.setBounds(125,250,230,30);
		c.add(txt4);
		
		ImageIcon ico=new ImageIcon("img/登录.png");
		JButton btn=new JButton();
		btn.setIcon(ico);
		btn.setText("注册");
		btn.setFont(f);
		btn.setBorder(null);
		btn.setBackground(Color.white);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("") || txt4.getText().equals("") || txt5.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请将内容填写完整");
				}else{
					if(Integer.valueOf(txt5.getText())==yzm && yzm!=0){
						RegisterDao registext=new RegisterDao(txt1.getText(),txt2.getText(),txt3.getText(),txt4.getText());
						if(new RegisterDaoimpl().insert(registext)){
							RegisterFrame.this.setVisible(false);                       
						}
					}else{
						JOptionPane.showMessageDialog(null, "验证码不正确");
						txt5.setText(null);
					}
				}
			}
		});
		btn.setBounds(110,310,150,70);
		c.add(btn);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//计时器
		timer=new Timer(1000,this);
		
		//绑定窗口事件监听器
		this.addWindowListener(new TimerStop(timer));
		//预加载
		new SendEamil();
	}
	
	//生成6位验证码
	public int SecurityCode(){
		Random ran=new Random();
		int yzm=ran.nextInt(899999)+100000;
		return yzm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		btn1.setText(txt+"");
		if(txt==0){
			timer.stop();
			btn1.setEnabled(true);
			btn1.setText("发送");
			yzm=0;
		}
		txt--;
	}

}
