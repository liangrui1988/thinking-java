package org.rui.swing.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.rui.swing.SwingConsole;
import org.rui.swing.bean.BangBean.ML;
import org.rui.swing.bean.BangBean.MML;

/**
 * java Bean ”ÎÕ¨≤Ω
 * 
 * @author lenovo
 * 
 */
public class BangBean2 extends JPanel implements Serializable {

	private int xm, ym;
	private int cSize = 20;
	private String text = "Bang2";
	private int fontSize = 48;
	private Color tColor = Color.BLACK;

	private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

	public BangBean2() {
		addMouseListener(new ML());
		addMouseMotionListener(new MM());
	}

	public synchronized int getCircleSize() {
		return cSize;
	}

	public synchronized void setCircleSize(int cSize) {
		this.cSize = cSize;
	}

	public synchronized String getText() {
		return text;
	}

	public synchronized void setText(String text) {
		this.text = text;
	}

	public synchronized int getFontSize() {
		return fontSize;
	}

	public synchronized void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public synchronized Color gettColor() {
		return tColor;
	}

	public synchronized void settColor(Color tColor) {
		this.tColor = tColor;
	}

	// /---------------------------------
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawOval(xm - cSize / 2, ym - cSize / 2, cSize, cSize);
	}

	public synchronized void addActionListener(ActionListener l) {
		actionListeners.add(l);
	}

	public synchronized void removeActionListener(ActionListener l) {
		actionListeners.remove(l);
	}

	public void notifyListener() {
		ActionEvent a = new ActionEvent(BangBean2.this,
				ActionEvent.ACTION_PERFORMED, null);
		ArrayList<ActionListener> lv = null;
		// make a shallow copy of the list in case
		// someone adds a listener while we're
		// calling listeners
		synchronized (this) {
			lv = new ArrayList<ActionListener>(actionListeners);
		}
		// call all the listener methods:
		for (ActionListener al : lv)
			al.actionPerformed(a);
	}

	class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Graphics g = getGraphics();
			g.setColor(tColor);
			g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
			int width = g.getFontMetrics().stringWidth(text);
			g.drawString(text, (getSize().width - width) / 2,
					getSize().height / 2);
			g.dispose();
			notifyListener();
		}
	}

	class MM extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();
			repaint();
		}
	}

	public static void main(String[] args) {
		BangBean2 bb2 = new BangBean2();

		bb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ActionEvent action:" + e);
			}

		});

		bb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("BangBean2 action:");
			}

		});

		bb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("More  action:");
			}

		});

		JFrame frame = new JFrame();
		frame.add(bb2);
		SwingConsole.run(frame, 300, 300);

	}

}
