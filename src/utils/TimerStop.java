package utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Timer;

public class TimerStop implements WindowListener {
	private Timer timer;
	
	public TimerStop(Timer timer) {
		this.timer=timer;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		try{
			timer.stop();
		}catch(Exception e1){
			
		}
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

}
