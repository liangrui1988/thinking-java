package org.rui.io.fout;

import java.io.File;
import java.io.ObjectInputStream.GetField;

/**
 * Ŀ¼ʵ�ù���
 * 
 * @author lenovo
 * 
 */
public class Dtest {

	public static void main(String[] args) {
		System.out.println("---");
		System.out.println(new File("").getAbsolutePath());

		System.out.println(System.getProperty("user.dir"));

		System.out.println(new File(".").getAbsolutePath());
		System.out.println(Dtest.class.getPackage().getName());

	}
}