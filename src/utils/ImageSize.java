package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageSize {
	private static ImageIcon icon;
	
	//已提供的图片大小转换的方法
	//使用此方法会导致图片边缘出现锯齿
	//不推荐使用
	//使用方法:ImageSize.Size("图片链接",转换后的宽,转换后的高);
	public static ImageIcon Size(String url,int w,int h ){
		icon=new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		return icon;
	}
}
