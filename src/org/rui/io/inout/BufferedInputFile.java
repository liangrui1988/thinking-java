package org.rui.io.inout;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 缓冲输入文件
 * 
 * @author lenovo
 * 
 */
public class BufferedInputFile {
	public static String read(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null)
			sb.append(s + "\n");
		br.close();
		// getClass();

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		// String
		// path=BufferedInputFile.class.getResource(".").getFile().toString();
		// System.out.println(read(path+"BufferedInputFile.java"));
		String path = "D:/Users/liangrui/workspace/thinking/src/org/rui/io/inout/";
		System.out.println(read(path + "BufferedInputFile.java"));
	}

}

/**
 * output: 同 上
 */
