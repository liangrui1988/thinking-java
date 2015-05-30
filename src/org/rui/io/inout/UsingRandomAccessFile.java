package org.rui.io.inout;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * 读写随机访问文件
 * 
 * 使用RandomAccessFile时，你必须知道文件排版，这样才能正确地操作它 拥有读取基本类型和utf-8字符串的各种具体方法，下面示例
 * 
 * 你可能会考虑 使用 内存映射文件 来贷替randomaccessfile
 * 
 * @author lenovo
 * 
 */
public class UsingRandomAccessFile {
	static String file = "rtest.dat";

	static void display() throws Exception {
		RandomAccessFile rf = new RandomAccessFile(file, "r");
		for (int i = 0; i < 7; i++)
			System.out.println("value " + i + " : " + rf.readDouble());
		System.out.println(rf.readUTF());
		rf.close();
	}

	public static void main(String[] args) throws Exception {
		RandomAccessFile rf = new RandomAccessFile(file, "rw");
		// 写
		for (int i = 0; i < 7; i++)
			rf.writeDouble(i * 1.414);
		rf.writeUTF(" the end of the file");
		rf.close();

		// 打印
		display();
		// 写 打开并修改
		rf = new RandomAccessFile(file, "rw");
		rf.seek(5 * 8);
		rf.writeDouble(47.0001);
		rf.close();
		// 打印
		display();
	}
}
/**
 * output: value 0 : 0.0 value 1 : 1.414 value 2 : 2.828 value 3 : 4.242 value 4
 * : 5.656 value 5 : 7.069999999999999 value 6 : 8.484 the end of the file value
 * 0 : 0.0 value 1 : 1.414 value 2 : 2.828 value 3 : 4.242 value 4 : 5.656 value
 * 5 : 47.0001 value 6 : 8.484 the end of the file
 */
