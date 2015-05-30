package org.rui.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 这可能是一个你想要自已使用的工具， 要想使用它，你的应用就必须位于一个JFrame中 静态的run方法可以将视窗的标题设置为类的简单名
 * 
 * @author lenovo
 * 
 */
public class SwingConsole {

	public static void run(final JFrame f, final int width, final int height) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				f.setTitle(f.getClass().getSimpleName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);

			}
		});
	}

}
