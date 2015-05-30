package org.rui.swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * �������һ������Ҫ����ʹ�õĹ��ߣ� Ҫ��ʹ���������Ӧ�þͱ���λ��һ��JFrame�� ��̬��run�������Խ��Ӵ��ı�������Ϊ��ļ���
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
