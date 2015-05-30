package org.rui.swing.bean;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.rui.swing.SwingConsole;

/**
 * 使用introspector抽取BeanInfo
 * 
 * @author lenovo
 * 
 */
public class BeanDumper extends JFrame {
	private JTextField query = new JTextField(20);
	private JTextArea results = new JTextArea();

	public void print(String s) {
		results.append(s + " \n");
	}

	public void dump(Class<?> bean) {
		results.setText("");
		BeanInfo bi = null;

		try {
			bi = Introspector.getBeanInfo(bean, Object.class);
		} catch (IntrospectionException e) {
			System.out.println("Couldn 't introspect" + bean.getName());
			return;
		}
		// 获取 bean属性 方法
		for (PropertyDescriptor d : bi.getPropertyDescriptors()) {
			Class<?> p = d.getPropertyType();
			if (p == null)
				continue;
			System.out.println("Property type:\n" + p.getName());
			Method m = d.getReadMethod();
			if (m != null)
				System.out.println("read method:+\n" + m.getName());
			Method rm = d.getWriteMethod();
			if (rm != null)
				System.out.println("write method:+\n" + rm.getName());
			System.out.println("====================================");
		}
		System.out.println("public methods:");
		for (MethodDescriptor ms : bi.getMethodDescriptors()) {
			System.out.println("ms:" + ms.getMethod().toString());
		}
		System.out.println("====================================");
		System.out.println("envent support:");
		for (EventSetDescriptor e : bi.getEventSetDescriptors()) {
			System.out.println("listener type:\n"
					+ e.getListenerType().getName());
			for (Method lm : e.getListenerMethods())
				System.out.println("listener method:\n" + lm.getName());

			for (MethodDescriptor lmd : e.getListenerMethodDescriptors())
				System.out.println("listener methodDescriptor:\n"
						+ lmd.getName());
			//
			Method addListener = e.getAddListenerMethod();
			System.out.println("add listener method:\n" + addListener);
			Method removeListener = e.getRemoveListenerMethod();
			System.out.println("Remove Listener Method:\n" + removeListener);
			System.out.println("===========================================");
		}
	}

	// ------------------------------------------------
	class Dumper implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = query.getText();
			System.out.println("name=========>" + name);
			Class<?> c = null;
			try {
				c = Class.forName(name);
			} catch (ClassNotFoundException e1) {
				results.setText("couldn 't find " + name);
				e1.printStackTrace();
				return;
			}
			dump(c);
		}
	}

	public BeanDumper() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(new JLabel("qualifeied bean name:"));
		p.add(query);
		add(BorderLayout.NORTH, p);
		add(new JScrollPane(results));
		Dumper dmpr = new Dumper();
		query.addActionListener(dmpr);
		query.setText("org.rui.swing.bean.Frog");
		dmpr.actionPerformed(new ActionEvent(dmpr, 0, ""));
	}

	public static void main(String[] args) {
		// 工具类
		SwingConsole.run(new BeanDumper(), 600, 500);
	}
}

/**
 * outputt: name=========>org.rui.swing.bean.Frog Property type: java.awt.Color
 * read method:+ getColor write method:+ setColor
 * ==================================== Property type: boolean read method:+
 * isJmpr write method:+ setJmpr ==================================== Property
 * type: int read method:+ getJumps write method:+ setJumps
 * ==================================== Property type: org.rui.classts.Pet read
 * method:+ getPet write method:+ setPet ====================================
 * public methods: ms:public void org.rui.swing.bean.Frog.croak() ms:public void
 * org.rui.swing.bean.Frog.removeActionListener(java.awt.event.ActionListener)
 * ms:public void org.rui.swing.bean.Frog.setColor(java.awt.Color) ms:public int
 * org.rui.swing.bean.Frog.getJumps() ms:public void
 * org.rui.swing.bean.Frog.addKeyListener(java.awt.event.KeyListener) ms:public
 * void org.rui.swing.bean.Frog.setJmpr(boolean) ms:public void
 * org.rui.swing.bean.Frog.setPet(org.rui.classts.Pet) ms:public void
 * org.rui.swing.bean.Frog.addActionListener(java.awt.event.ActionListener)
 * ms:public java.awt.Color org.rui.swing.bean.Frog.getColor() ms:public boolean
 * org.rui.swing.bean.Frog.isJmpr() ms:public org.rui.classts.Pet
 * org.rui.swing.bean.Frog.getPet() ms:public void
 * org.rui.swing.bean.Frog.removeKeyListener(java.awt.event.KeyListener)
 * ms:public void org.rui.swing.bean.Frog.setJumps(int)
 * ==================================== envent support: listener type:
 * java.awt.event.ActionListener listener method: actionPerformed listener
 * methodDescriptor: actionPerformed add listener method: public void
 * org.rui.swing.bean.Frog.addActionListener(java.awt.event.ActionListener)
 * Remove Listener Method: public void
 * org.rui.swing.bean.Frog.removeActionListener(java.awt.event.ActionListener)
 * =========================================== listener type:
 * java.awt.event.KeyListener listener method: keyPressed listener method:
 * keyReleased listener method: keyTyped listener methodDescriptor: keyPressed
 * listener methodDescriptor: keyReleased listener methodDescriptor: keyTyped
 * add listener method: public void
 * org.rui.swing.bean.Frog.addKeyListener(java.awt.event.KeyListener) Remove
 * Listener Method: public void
 * org.rui.swing.bean.Frog.removeKeyListener(java.awt.event.KeyListener)
 * ===========================================
 */
