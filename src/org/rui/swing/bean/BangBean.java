package org.rui.swing.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.TooManyListenersException;

import javax.swing.JPanel;

/**
 * 一个复杂的bean
 * 
 * @author lenovo
 * 
 */
public class BangBean extends JPanel implements Serializable {
	private int xm, ym;
	private int cSize = 20;
	private String text = "Bang!";
	private int fontSize = 48;
	private Color tColor = Color.RED;
	private ActionListener actionListener;

	public BangBean() {
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
	}

	public int getcSize() {
		return cSize;
	}

	public void setcSize(int cSize) {
		this.cSize = cSize;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public Color gettColor() {
		return tColor;
	}

	public void settColor(Color tColor) {
		this.tColor = tColor;
	}

	// =========================
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawOval(xm - cSize / 2, ym - cSize / 2, cSize, cSize);
	}

	// 这是这是一个单播侦听器
	// 最简单形式的侦听器管理
	public void addActionListener(ActionListener l)
			throws TooManyListenersException {
		if (actionListener != null) {
			throw new TooManyListenersException();
		}
		actionListener = l;
	}

	public void removeActionListener(ActionListener l) {
		actionListener = null;
	}

	// ///
	class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			Graphics g = getGraphics();
			g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
			int width = g.getFontMetrics().stringWidth(text);
			g.drawString(text, (getSize().width - width) / 2,
					getSize().height / 2);
			g.dispose();
			// call the lisener 's method
			if (actionListener != null) {
				actionListener.actionPerformed(new ActionEvent(BangBean.this,
						ActionEvent.ACTION_PERFORMED, null));
			}
		}
	}

	// ////////////////
	class MML extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			xm = e.getX();
			ym = e.getY();
			repaint();
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

}
