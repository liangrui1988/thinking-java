package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 基本的文件输出
 * 
 * @author lenovo
 * 
 */
public class BasicFileOutput {

	public static void main(String[] args) throws Exception {
		String file = "D:/BasicFileOutput.out";
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";

		// 读文件
		BufferedReader in = new BufferedReader(new StringReader(
				BufferedInputFile.read(path + "BasicFileOutput.java")));

		// 写
		PrintWriter pw = new PrintWriter(new BufferedWriter(
				new FileWriter(file)));

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
