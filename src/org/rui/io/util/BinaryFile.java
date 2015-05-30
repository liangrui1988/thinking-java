package org.rui.io.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取二进制文件
 * 
 * @author lenovo
 * 
 */
public class BinaryFile {

	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				bFile));
		try {
			// 产生恰当数组尺寸
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		} finally {
			bis.close();
		}
	}

	public static byte[] read(String bF) throws IOException {
		// 重载
		return read(new File(bF).getAbsoluteFile());// getAbsoluteFile
													// 返回此抽象路径名的绝对路径名形式。
	}

}
