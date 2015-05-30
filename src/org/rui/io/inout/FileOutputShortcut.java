package org.rui.io.inout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 文本文件输出的快揵方式
 * 
 * @author lenovo
 * 
 */
public class FileOutputShortcut {
	public static void main(String[] args) throws Exception {
		String file = "D:/BasicFileOutput.out";
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";

		// 读文件
		BufferedReader in = new BufferedReader(new StringReader(
				BufferedInputFile.read(path + "BasicFileOutput.java")));

		// 写 java se5在printWriter中添加 了一个辅助构造器
		PrintWriter pw = new PrintWriter(file);

		// //
		int linecount = 1;
		String s;
		while ((s = in.readLine()) != null)
			pw.println(linecount++ + s);
		pw.close();

		// 读已写入的文件
		System.out.println(BufferedInputFile.read(file));

	}
}