package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import daoimpl.AdminDaoImpl;
import utils.ImageSize;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import listener.AdminLoginListner;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	public JTextField textField1=new JTextField();
	public JPasswordField textField2=new JPasswordField();

	public LoginFrame() {
		setSize(600,486);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/资料库.png"));
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 594, 453);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton jb1 = new JButton("\u767B\u5F55");
		jb1.setBackground(Color.WHITE);
		jb1.setFocusPainted(false);
		jb1.addActionListener(new AdminLoginListner(this));
		
		JButton jb2 = new JButton("\u6CE8\u518C");
		jb2.setFocusPainted(false);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegisterFrame(null);
			}
		});
		jb2.setIcon(ImageSize.Size("img/注册登录.png", 30, 30));
		jb2.setBounds(490, 0, 100, 40);
		jb2.setContentAreaFilled(false);
		jb2.setBorder(null);
		panel.add(jb2);
		jb1.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		jb1.setIcon(ImageSize.Size("img/登录.png", 50, 50));
		jb1.setBounds(220, 310, 160, 70);
		panel.add(jb1);
		
		textField2.setBounds(209, 248, 236, 31);
		textField2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		textField2.setEchoChar('*');
		panel.add(textField2);
		textField2.setColumns(10);
		textField2.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					String password="";
					for(int i=0;i<textField2.getPassword().length;i++){
						password+=textField2.getPassword()[i];
					}
					if(new AdminDaoImpl().login(textField1.getText(), password)){
						setVisible(false);
						new LAFrame();
					}else{
						JOptionPane.showMessageDialog(null, "账号或密码错误！");
						textField1.requestFocus();
					}
				}
			}
		});
		
		textField1.setBounds(209, 169, 236, 31);
		textField1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		panel.add(textField1);
		textField1.setColumns(10);
		textField1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					textField2.requestFocus();
				}
			}
		});
		
		JLabel label4 = new JLabel("");
		label4.setIcon(ImageSize.Size("img/密码登录.png", 50, 50));
		label4.setBounds(145, 229, 50, 50);
		panel.add(label4);
		
		JLabel label3 = new JLabel("");
		label3.setIcon(ImageSize.Size("img/未登录.png", 50, 50));
		label3.setBounds(145, 150, 50, 50);
		panel.add(label3);
		
		JLabel label2 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		label2.setBounds(145, 50, 300, 60);
		panel.add(label2);
		label2.setFont(new Font("华文行楷", Font.PLAIN, 30));
		
		JLabel label1 = new JLabel("");
		label1.setBounds(0, 0, 594, 453);
		panel.add(label1);
		label1.setIcon(new ImageIcon("img\\\u4E66.png"));
		
		setVisible(true);
		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);
		
		//锟斤拷媒锟斤拷锟�
		textField1.requestFocus();
		
		//预锟斤拷锟斤拷
		new AdminDaoImpl().login(null, null);
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
