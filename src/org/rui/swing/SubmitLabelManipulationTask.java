package org.rui.swing;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * swing 有它自已的专用线程来接收UI事件并更新屏幕
 * 
 * @author lenovo
 * 
 */
public class SubmitLabelManipulationTask {

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Hello Swing");
		final JLabel label = new JLabel("A Label");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
		TimeUnit.SECONDS.sleep(1);
		// 专用线程
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setText("hey! this is different");

			}
		});
	}
}
