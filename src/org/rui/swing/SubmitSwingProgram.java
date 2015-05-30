package org.rui.swing;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SubmitSwingProgram extends JFrame {
	JLabel label;

	public SubmitSwingProgram() {
		super("hello swing");
		label = new JLabel("A Label");
		add(label);
		setSize(300, 100);
		setVisible(true);
	}

	//
	static SubmitSwingProgram ssp;

	public static void main(String[] args) throws InterruptedException {
		// 实例对象
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ssp = new SubmitSwingProgram();
			}
		});

		TimeUnit.SECONDS.sleep(1);
		// 设置改变文字
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ssp.label.setText("hey! this is different!");
			}
		});

	}

}
