package org.rui.io.inout;

import java.io.*;

/**
 * 从内存输入
 * 
 * @author lenovo
 * 
 */
public class MemoryInput {
	public static void main(String[] args) throws Exception {
		String path = "D:/Users/liangrui/workspace/thinking/src/org/rui/io/inout/";
		// BufferedInputFile.read(path+"MemoryInput.java")
		StringReader in = new StringReader(BufferedInputFile.read(path
				+ "MemoryInput.java"));
		int c = 0;
		while ((c = in.read()) != -1)
			System.out.print((char) c);
	}

}
/**
 * output: 同 上
 */
