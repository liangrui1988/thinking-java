package org.rui.io.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 将System.out 转换成PrintWriter System.out是一个printStream
 * 而printStream是一个outputstream PrintWriter 有一个可以接受outputstream作为参数的构造器
 */
public class ChangeSystemOut {
	public static void main(String[] args) throws IOException {
		// true 自动清空功能
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello, wrold");
	}
}
/**
 * output: Hello, wrold
 */
