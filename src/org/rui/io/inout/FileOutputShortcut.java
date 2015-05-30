package org.rui.io.inout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * �ı��ļ�����Ŀ�b��ʽ
 * 
 * @author lenovo
 * 
 */
public class FileOutputShortcut {
	public static void main(String[] args) throws Exception {
		String file = "D:/BasicFileOutput.out";
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";

		// ���ļ�
		BufferedReader in = new BufferedReader(new StringReader(
				BufferedInputFile.read(path + "BasicFileOutput.java")));

		// д java se5��printWriter����� ��һ������������
		PrintWriter pw = new PrintWriter(file);

		// //
		int linecount = 1;
		String s;
		while ((s = in.readLine()) != null)
			pw.println(linecount++ + s);
		pw.close();

		// ����д����ļ�
		System.out.println(BufferedInputFile.read(file));

	}
}