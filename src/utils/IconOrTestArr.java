package utils;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import listener.HomeActionListener;

public class IconOrTestArr {
	//该构造方法用于绘制按钮
	//传入图片路径数组，按钮文字数组，容器，按钮，命名种子
	//只支持FlowLayount、GripLayout布局的JPanel容器
	public IconOrTestArr(String[] icon,String[] text,JPanel jp,JButton jb,String name){
		for(int i=0;i<icon.length;i++){
			jb = new JButton(text[i]);
			jb.setIcon(ImageSize.Size(icon[i], 50, 50));
			jb.setContentAreaFilled(false);
			jb.setBorder(null);
			jb.setHorizontalTextPosition(JButton.CENTER);
			jb.setVerticalTextPosition(JButton.BOTTOM);
			jb.setActionCommand(name+(i+1));
			jb.addActionListener(new HomeActionListener());
			if(Prestrain.color.equals("black")){
				jb.setBackground(Color.gray);
				jb.setForeground(Color.orange);
			}
			jp.add(jb);
		}
	}
	
}
