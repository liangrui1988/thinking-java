package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * 下面演示了怎样一次一个字节地的读取文件
 * 
 * @author lenovo
 * 
 */
public class TestEOF {

	public static void main(String[] args) throws Exception {

		String path = "D:\\Users\\liangrui\\workspace\\thinking\\src\\org\\rui\\io\\inout/";
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(path + "TestEOF.java")));
		// 注意 会随着所读的媒介类型的不同而有所不同，字面的意思就是 在没有阻塞的情况下所能读取的字节数
		// 对于文件 这意味着整个文件，但是对于不同类型的流，可能就不是这样的，因此要谨慎使用
		while (in.available() != 0)
			System.out.print((char) in.readByte());

	}
}
