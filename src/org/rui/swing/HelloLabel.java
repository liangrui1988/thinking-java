package org.rui.swing;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * JFream ����� һ��JLabel��ʹ�����ø���Ȥ
 * 
 * @author lenovo
 * 
 */
public class HelloLabel {

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("hello swing");
		JLabel label = new JLabel("A label");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
		TimeUnit.SECONDS.sleep(1);
		label.setText("Hey! this is Different!");

	}

}
