package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import daoimpl.ReaderDaoImpl;
import entry.Reader;
import utils.Prestrain;
import utils.Scaling;
import utils.SendEamil;

public class AccountInformation {
	public static JPanel jp3;
	public static JPanel jp4;

	public AccountInformation() {
		HomeFrame.jp2.removeAll();

		JPanel jp1 = new JPanel();
		jp1.setLayout(null);

		Font f = new Font("微软雅黑", Font.BOLD, 20);

		// 我的账号--------------
		JPanel jp2 = new JPanel();
		jp2.setLayout(null);
		Border border = new LineBorder(Color.black, 2);
		jp2.setBorder(new TitledBorder(border, "我的账号:", 1, 2, f));
		jp2.setBounds(10, 10, 300, 200);
		JLabel jl = new JLabel();
		// 大头照
		jl.setIcon(new ImageIcon("img/账号.png"));
		jl.setBounds(20, 30, 100, 100);
		jp2.add(jl);
		// 邮箱/电话
		jl = new JLabel(Prestrain.usertel);
		jl.setFont(new Font("微软雅黑", Font.BOLD, 15));
		jl.setBounds(20, 130, 110, 30);
		jp2.add(jl);
		// 账号
		jl = new JLabel("账号:");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl.setBounds(140, 30, 50, 50);
		jp2.add(jl);
		jl = new JLabel(Prestrain.user);
		jl.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jl.setBounds(190, 30, 100, 50);
		jl.setToolTipText("点击修改");
		jp2.add(jl);
		// 密码
		jl = new JLabel("密码:");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl.setBounds(140, 80, 50, 50);
		jp2.add(jl);
		jl = new JLabel(Prestrain.password);
		jl.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jl.setBounds(190, 80, 100, 50);
		jl.setToolTipText("点击修改");
		jp2.add(jl);

		jp1.add(jp2);
		// --------------------

		// 扩展信息--------------
		jp3 = new JPanel();
		jp3.setLayout(null);
		jp3.setBorder(new TitledBorder(border, "扩展信息:", 1, 2, f));
		jp3.setBounds(340, 10, 200, 200);
		Reader reader = new Reader();
		reader.setReaderid(Prestrain.reader);
		jl = new JLabel("<html>当前绑定的读者信息为:id：" + Prestrain.reader + " 姓名："
				+ new ReaderDaoImpl().selectterm(reader).get(0).getReadername()+"</html>");
		jl.setFont(new Font("微软雅黑",Font.BOLD,18));
		jl.setBounds(10,20,jp3.getWidth(),50);
		jp3.add(jl);
		jp1.add(jp3);
		// --------------------

		// 资料修改--------------
		/*jl = new JLabel("账号:");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl.setBounds(15, 220, 50, 50);
		jp1.add(jl);
		JTextField jb1 = new JTextField();
		jb1.setBounds(65, 230, 200, 30);
		jb1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jp1.add(jb1);

		jl = new JLabel("密码:");
		jl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jl.setBounds(15, 260, 50, 50);
		jp1.add(jl);
		JTextField jb2 = new JTextField();
		jb2.setBounds(65, 270, 200, 30);
		jb2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		jp1.add(jb2);*/
		// --------------------

		// 问题反馈--------------
		jp4 = new JPanel();
		jp4.setLayout(new BorderLayout());
		jp4.setBorder(new TitledBorder(border, "问题反馈:", 1, 2, f));
		jp4.setBounds(10, 210, 530, 200);
		JTextArea txt=new JTextArea();
		txt.setFont(new Font("微软雅黑",Font.BOLD,18));
		txt.setBackground(new Color(240, 240, 240));
		jp4.add(new JScrollPane(txt),BorderLayout.CENTER);
		JButton jb=new JButton("发送");
		jb.setFont(new Font("微软雅黑",Font.BOLD,18));
		jb.setBackground(Color.white);
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SendEamil("zhibinhu666@gmail.com","问题反馈",txt.getText());
				} catch (MessagingException e) {
					JOptionPane.showMessageDialog(null, "发送失败，未连接网络");
					return;
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
					return;
				}
				JOptionPane.showMessageDialog(null, "成功，感谢您的反馈!");
				txt.setText(null);
			}
		});
		jp4.add(jb,BorderLayout.SOUTH);
		jp1.add(jp4);
		// --------------------
		
		HomeFrame.jp2.add(jp1);
		HomeFrame.jp2.repaint();
		HomeFrame.jp2.revalidate();

		// 通知
		Scaling.frame = "AccountInformation";
	}
}
