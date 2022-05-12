package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import utils.IconOrTestArr;
import utils.ImageSize;
import utils.Prestrain;
import utils.Scaling;
import utils.TimerStop;
import listener.HomeActionListener;
import listener.HomeListener;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame implements ActionListener {
	public static Container c;
	public static JPanel jp;
	public static JLabel jl;
	public static JLabel jl2;
	public static JPanel jp2;
	private static JButton jb1;
	private static JButton jb2;
	private static JButton jb3;
	private static JButton jb4;
	public static JButton jbarr[] = { jb1, jb2, jb3, jb4 };
	public static String texarr[] = { "图书管理", "借阅管理", "读者管理", "系统管理" };
	public static JButton jb5;
	public static JButton jb6;
	public static JButton jb7;
	public static JButton jb8;
	public static JButton jb9;
	public Timer timer = new Timer(1, this);
	public static int lock=Prestrain.lock;
	public static JPopupMenu jpm1;
	static JMenuItem menu3;
	static JMenuItem menu4;
	static JMenuItem menu5;
	static JMenuItem menu6;
	static JMenuItem menu7;
	static JMenuItem menu8;
	public static JMenuItem menuarr[]={menu3,menu4,menu5,menu6,menu7,menu8};

	// 返回此窗口
	public HomeFrame frame() {
		return this;
	}

	public HomeFrame() {
		this.setSize(800, 700);
		this.setTitle("图书管理系统");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/资料库.png"));

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		if(Prestrain.lock!=0){
			c.addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					//重新计算自动锁屏时间
					if(lock>0){
						lock=Prestrain.lock;
					}
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		// 上
		JMenuBar jmb = new JMenuBar();
		Font f=new Font("微软雅黑",Font.BOLD,15);
		jmb.setSize(this.getWidth(), 50);
		JMenu menu1=new JMenu("设置");
		menu1.setFont(f);
		JMenu menu2=new JMenu("主题");
		menu2.setFont(f);
		menuarr[0]=new JMenuItem("白");
		menuarr[0].setFont(f);
		menuarr[0].setActionCommand("menu1");
		menuarr[0].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[0]);
		menuarr[1]=new JMenuItem("黑");
		menuarr[1].setFont(f);
		menuarr[1].setActionCommand("menu2");
		menuarr[1].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[1]);
		menu1.add(menu2);
		
		menu2=new JMenu("自动锁定设置");
		menu2.setFont(f);
		menuarr[2]=new JMenuItem("无");
		menuarr[2].setFont(f);
		menuarr[2].setActionCommand("menu3");
		menuarr[2].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[2]);
		menuarr[3]=new JMenuItem("30秒");
		menuarr[3].setFont(f);
		menuarr[3].setActionCommand("menu4");
		menuarr[3].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[3]);
		menuarr[4]=new JMenuItem("1分钟");
		menuarr[4].setFont(f);
		menuarr[4].setActionCommand("menu5");
		menuarr[4].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[4]);
		menuarr[5]=new JMenuItem("3分钟");
		menuarr[5].setFont(f);
		menuarr[5].setActionCommand("menu6");
		menuarr[5].addActionListener(new HomeActionListener(this));
		menu2.add(menuarr[5]);
		menu1.add(menu2);
		
		jmb.add(menu1);
		c.add(jmb, BorderLayout.NORTH);
		// 右
		jp = new JPanel();
		jp.setLayout(null);
		jp.setPreferredSize(new Dimension(200, 700));
		jp.setBackground(Color.white);
		if (Prestrain.color.equals("black")) {
			jp.setBackground(Color.black);
		}
		jl = new JLabel();
		jl.setIcon(ImageSize.Size("img/收回.png", 50, 50));
		if (Prestrain.color.equals("black")) {
			jl.setOpaque(true);
			jl.setBackground(Color.GRAY);
		}
		jl.addMouseListener(new HomeListener());
		jl.setBounds(150, 0, 50, 50);
		jp.add(jl);

		ImageIcon iconarr[] = { ImageSize.Size("img/HomeImgs/1.png", 50, 50),
				ImageSize.Size("img/HomeImgs/2.png", 50, 50),
				ImageSize.Size("img/HomeImgs/3.png", 50, 50),
				ImageSize.Size("img/HomeImgs/4.png", 50, 50) };
		int jbhe = 100;
		for (int i = 0; i < 4; i++) {
			jbarr[i] = new JButton(texarr[i], iconarr[i]);
			jbarr[i].setBounds(25, jbhe, 175, 50);
			jbarr[i].setBackground(Color.white);
			jbarr[i].setFocusable(false);
			jbarr[i].setActionCommand("jb" + (i + 1));
			jbarr[i].addActionListener(new HomeActionListener());
			if (Prestrain.color.equals("black")) {
				jbarr[i].setBackground(Color.darkGray);
				jbarr[i].setForeground(Color.lightGray);
			}
			jbarr[i].setBorder(null);
			jbarr[i].setFont(new Font("微软雅黑",Font.BOLD,15));
			jp.add(jbarr[i]);
			jbhe += 70;
		}

		jb5 = new JButton("<html><center>" + Prestrain.user + "</center>" + Prestrain.rank
				+ "</html>", ImageSize.Size("img/未登录.png", 50, 50));
		jb5.setBounds(60, 450, 80, 130);
		jb5.setFocusable(false);
		if (Prestrain.color.equals("black")) {
			jb5.setBackground(Color.GRAY);
		} else {
			jb5.setContentAreaFilled(false);
		}
		jb5.setBorder(null);
		jb5.setHorizontalTextPosition(JButton.CENTER);
		jb5.setVerticalTextPosition(JButton.BOTTOM);
		jb5.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jb5.setActionCommand("jb5");
		jb5.addActionListener(new HomeActionListener());
		// 弹出菜单
		jpm1 = new JPopupMenu();
		jpm1.setPopupSize(100, 100);
		JMenuItem jm1;
		jm1 = new JMenuItem("切换账号");
		jm1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jm1.setActionCommand("jm1-1");
		jm1.addActionListener(new HomeActionListener(this));
		jpm1.add(jm1);
		jm1 = new JMenuItem("暂时离开");
		jm1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jm1.setActionCommand("jm1-2");
		jm1.addActionListener(new HomeActionListener(this));
		jpm1.add(jm1);
		jm1 = new JMenuItem("账号信息");
		jm1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		jm1.setActionCommand("jm1-3");
		jm1.addActionListener(new HomeActionListener(this));
		jpm1.add(jm1);

		jp.add(jb5);

		jl2 = new JLabel();
		jl2.setBounds(50, 580, 105, 30);
		jl2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		if (Prestrain.color.equals("black")) {
			jl2.setForeground(Color.LIGHT_GRAY);
		}
		jp.add(jl2);
		// 启动计时器
		new Prestrain();
		timer.start();

		// 主要内容
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 1, 10, 10));
		if (Prestrain.color.equals("black")) {
			jp2.setBackground(Color.black);
		}

		c.add(jp2, BorderLayout.CENTER);
		c.add(jp, BorderLayout.WEST);

		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);

		if (Prestrain.homestate) {

		} else {
			HomeListener.little();
		}
		
		//檢測權限等級
		if(!"管理员".equals(Prestrain.rank)){
			jbarr[0].setEnabled(false);
			jbarr[2].setEnabled(false);
			jbarr[3].setEnabled(false);
		}
		
		//绑定窗口事件监听器
		this.addWindowListener(new TimerStop(timer));
		
		//启动窗体缩放检测
		new Scaling();
	}

	// 图书管理
	public static void BookManage() {
		String ManageIconArr[] = { "img/Book Management Imgs/登记.png",
				"img/Book Management Imgs/遗失补办.png",
				"img/Book Management Imgs/修改.png",
				"img/Book Management Imgs/查询.png",
				"img/Book Management Imgs/统计.png" };
		String ManageTexArr[] = { "图书登记", "图书遗失", "图书修改", "图书查询", "图书统计" };
		new IconOrTestArr(ManageIconArr, ManageTexArr, jp2, jb6, "jb6");
	}

	// 借阅管理
	public static void LibraryManagement() {
		String BorrowIconArr[] = { "img/Library Management Imgs/借阅.png",
				"img/Library Management Imgs/续借.png",
				"img/Library Management Imgs/归还.png",
				"img/Library Management Imgs/逾期.png" };
		String BorrowTextArr[] = { "图书借阅", "图书续借", "图书归还", "超期图书" };
		new IconOrTestArr(BorrowIconArr, BorrowTextArr, jp2, jb7, "jb7");
	}

	// 读者管理
	public static void TheReaderManagement() {
		String BorrowIconArr[] = { "img/The reader management Imgs/登记.png",
				"img/The reader management Imgs/信息.png",
				"img/The reader management Imgs/删改.png" };
		String BorrowTextArr[] = { "读者登记", "读者信息", "读者删改" };
		new IconOrTestArr(BorrowIconArr, BorrowTextArr, jp2, jb7, "jb8");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Prestrain.homestate) {
			jl2.setText(Prestrain.timearr[0] + "时" + Prestrain.timearr[1] + "分"
					+ Prestrain.timearr[2] + "秒");
		} else {
			jl2.setText("<html>" + Prestrain.timearr[0] + "时<br>"
					+ Prestrain.timearr[1] + "分<br>" + Prestrain.timearr[2]
					+ "秒</html>");
		}
		if(Prestrain.lock>0){
			if(lock==0){
				new HomeLave();
			}
			if(lock>=0){
				lock--;
			}
		}
	}
}
