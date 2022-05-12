package listener;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import daoimpl.AdminDaoImpl;
import entry.Admin;
import utils.ImageSize;
import utils.Prestrain;
import view.HomeFrame;

//主页的鼠标基本事件
public class HomeListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (HomeFrame.jl.getLocation().x == 0) {
			HomeFrame.jl.setIcon(ImageSize.Size("img/收回.png", 50, 50));
			HomeFrame.jl.setLocation(150, 0);
			HomeFrame.jp.setSize(200, 700);
			HomeFrame.jp.setPreferredSize(new Dimension(200, 700));
			for (int i = 0; i < 4; i++) {
				HomeFrame.jbarr[i].setText(HomeFrame.texarr[i]);
				HomeFrame.jbarr[i].setBounds(25,
						HomeFrame.jbarr[i].getLocation().y, 175, 50);
			}
			HomeFrame.jb5.setText("<html><center>" + Prestrain.user
					+ "</center>" + Prestrain.rank + "</html>");
			HomeFrame.jb5.setBounds(60, 450, 80, 130);
			HomeFrame.jl2.setBounds(50, 580, 105, 30);
			Prestrain.homestate = true;
		} else if (HomeFrame.jl.getLocation().x == 150) {
			little();
		}
		Admin admin = new Admin();
		admin.setHomestate(Prestrain.homestate);
		new AdminDaoImpl().update(admin);
		HomeFrame.jp.revalidate();
		HomeFrame.jp2.revalidate();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void little() {
		HomeFrame.jl.setIcon(ImageSize.Size("img/展开.png", 50, 50));
		HomeFrame.jl.setLocation(0, 0);
		HomeFrame.jp.setSize(50, 700);
		HomeFrame.jp.setPreferredSize(new Dimension(50, 700));
		for (int i = 0; i < 4; i++) {
			HomeFrame.jbarr[i].setText(null);
			HomeFrame.jbarr[i].setBounds(0, HomeFrame.jbarr[i].getLocation().y,
					50, 50);
		}
		HomeFrame.jb5.setText(null);
		HomeFrame.jb5.setBounds(0, 450, 50, 50);
		HomeFrame.jl2.setBounds(5, 500, 40, 120);
		Prestrain.homestate = false;
	}

}
