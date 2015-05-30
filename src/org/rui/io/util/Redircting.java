package org.rui.io.util;

import java.io.*;

/**
 * 标准I/O重定向
 */
public class Redircting {
	public static void main(String[] args) throws IOException {
		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\util/";
		PrintStream console = System.out;
		// 输入
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				path + "Redircting.java"));
		// 输出
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream(path + "testf.out")));
		// 允许我们对标准输入输出 和错误IO流进行重定向
		System.setIn(in);
		// System.setOut(out);
		// System.setErr(out);

		// 打印得定向的输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;

		while ((s = br.readLine()) != null)
			System.out.println(s);
		out.close();// remember this!
		System.setOut(console);
		;
	}

}
/**
 * output: 同上源码
 */

