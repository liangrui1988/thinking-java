package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 存储和恢复数据 如果我们使用了dataoutputstream写入数据，java保证我们可以使用datainputstream准确地读取数据，
 * 无论读和写数据平台多么不同 utf-8是一种多字节格式，其编码长度根据实际使用的字符集会有所变化，如果我们的只是ASCII只占7位
 * 浪费空间和带宽，所以utf-8将ASCII字符编码成单一字节的形式，而非ASCII字符则编码成两到三个字节的形式
 * 
 * @author lenovo
 * 
 */
public class StoringAndRecoveringData {
	public static void main(String[] args) throws Exception {
		String path = "d:/data.txt";
		// 输出
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream(path)));
		out.writeDouble(5.55);
		out.writeUTF("hello world");
		out.writeDouble(6.66);
		out.writeUTF("你好  世界");
		out.close();
		// 读取
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(path)));

		//
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());

	}
}
/**
 * output: 5.55 hello world 6.66 你好 世界
 */
