package org.rui.swing.bean;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import org.rui.classts.Pet;

/**
 * ¼òµ¥µÄbean
 * 
 * @author lenovo
 * 
 */
public class Frog {
	private int jumps;
	private Color color;
	private Pet pet;
	private boolean jmpr;

	public int getJumps() {
		return jumps;
	}

	public void setJumps(int jumps) {
		this.jumps = jumps;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public boolean isJmpr() {
		return jmpr;
	}

	public void setJmpr(boolean jmpr) {
		this.jmpr = jmpr;
	}

	public void addActionListener(ActionListener l) {
		// .....
	}

	public void removeActionListener(ActionListener l) {
		// .....
	}

	public void addKeyListener(KeyListener l) {
		// .....
	}

	public void removeKeyListener(KeyListener l) {
		// .....
	}

	// an ordinary public method
	public void croak() {
		System.out.println("Ribbet!");
	}
}
