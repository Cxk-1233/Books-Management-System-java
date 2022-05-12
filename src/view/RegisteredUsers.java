package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import utils.Prestrain;
import daoimpl.AdminDaoImpl;
import daoimpl.ReaderDaoImpl;
import entry.Admin;
import entry.Reader;
@SuppressWarnings("serial")
public class RegisteredUsers extends JFrame{
	public RegisteredUsers(){
		this.setSize(380,500);
		Container c=this.getContentPane();
		c.setLayout(null);
		Font f=new Font("幼圆",Font.PLAIN,25);

		ImageIcon icon1=new ImageIcon("img/未登录.png");
		JLabel jl1=new JLabel();
		jl1.setText("读者姓名:");
		jl1.setIcon(icon1);
		jl1.setFont(f);
		jl1.setBounds(0,0,200,50);
		c.add(jl1);
		JTextField txt1=new JTextField(18);
		txt1.setBounds(170,10,180,30);
		c.add(txt1);
		
		
		ImageIcon icon2 =new ImageIcon("img/Book Management Imgs/挂失.png");
		JLabel jl2=new JLabel("读者身份证:");
		jl2.setIcon(icon2);
		jl2.setFont(f);
		jl2.setBounds(0,60,200,50);
		c.add(jl2);
		JTextField txt2=new JTextField(18);
		txt2.setBounds(200,70,150,30);
		c.add(txt2);
		
		ImageIcon icon3 =new ImageIcon("img/HomeImgs/2.png");
		JLabel jl3=new JLabel("登记日期:");
		jl3.setIcon(icon3);
		jl3.setFont(f);
		jl3.setBounds(0,120,200,50);
		c.add(jl3);
		JTextField txt3=new JTextField(15);
		txt3.setBounds(170,130,180,30);
		c.add(txt3);
		
		ImageIcon icon4 =new ImageIcon("img/HomeImgs/3.png");
		JLabel jl4=new JLabel("读者邮箱:");
		jl4.setIcon(icon4);
		jl4.setFont(f);
		jl4.setBounds(0,180,200,50);
		c.add(jl4);
		JTextField txt4=new JTextField(18);
		txt4.setBounds(170,190,180,30);
		c.add(txt4);
		
		ImageIcon icon5 =new ImageIcon("img/搜索.png");
		JLabel jl5=new JLabel("读者地址:");
		jl5.setIcon(icon5);
		jl5.setFont(f);
		jl5.setBounds(0,240,200,50);
		c.add(jl5);
		JTextField txt5=new JTextField(18);
		txt5.setBounds(170,250,180,30);
		c.add(txt5);
		
		ImageIcon icon6 =new ImageIcon("img/修改.png");
		JLabel jl6=new JLabel("读者备注:");
		jl6.setIcon(icon6);
		jl6.setFont(f);
		jl6.setBounds(0,300,200,50);
		c.add(jl6);
		JTextField txt6=new JTextField(18);
		txt6.setBounds(170,310,180,30);
		c.add(txt6);
		

		ImageIcon ico=new ImageIcon("img/登录.png");
		JButton btn=new JButton();
		btn.setIcon(ico);
		btn.setText("注册");
		btn.setFont(f);
		btn.setBorder(null);
		btn.setBackground(Color.white);
		btn.setBounds(100,360,150,70);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Reader registext=new Reader(0,txt1.getText(),txt2.getText(),txt3.getText(),txt4.getText(),txt5.getText(),txt6.getText());
					if(new ReaderDaoImpl().insert(registext)){
						RegisteredUsers.this.setVisible(false);
						ArrayList<Reader> readerarr=new ArrayList<Reader>();
						registext.setReaderdate("");
						readerarr=new ReaderDaoImpl().selectterm(registext);
						Admin admin=new Admin(readerarr.get(0).getReaderid());
						System.out.println(admin.getReader());
						if(new AdminDaoImpl().update(admin)){
							JOptionPane.showMessageDialog(null, "绑定成功");
							Prestrain.Prestrain2();
						}
					}
			}
		});
		c.add(btn);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		txt3.setText(Prestrain.timearr[3] + "-" + Prestrain.timearr[4]
				+ "-" + Prestrain.timearr[5] + " " + Prestrain.timearr[0] + ":"
				+ Prestrain.timearr[1] + ":" + Prestrain.timearr[2] + "");
	}
}
