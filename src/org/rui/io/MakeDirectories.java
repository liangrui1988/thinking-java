package org.rui.io;

import java.io.File;

/**
 * 目录检查级创建
 * 
 * @author lenovo
 * 
 */
public class MakeDirectories {

	// 提示信息
	private static void usage() {
		System.err.println("Usage:MakeDirectories path1...\n"
				+ "Creates each path\n"
				+ "usage:makedirectories -d path1....\n"
				+ "deletes each path\n"
				+ "usage make directories -r path1 path2\n"
				+ "Renames from path1 to path2");
		System.exit(0);
	}

	// 输出文件信息
	private static void fileDate(File f) {
		System.out.println("absolute path:" + f.getAbsolutePath()
				+ "\ncal read:" + f.canRead() + "\ncan Write:" + f.canWrite()
				+ "\n name:" + f.getName() + "\nparent:" + f.getParent()
				+ "\nlength:" + f.length() + "\npath:" + f.getPath()
				+ "\nlastmodiffied:" + f.lastModified());

		if (f.isFile())
			System.out.println("it is a file");
		else if (f.isDirectory())
			System.out.println("it s a directory");

	}

	// ///////////////////main test///////////////////////////////////////
	public static void main(String[] args) {
		String argss[] = new String[1];
		argss[0] = "MakeDirectoriesTest";
		if (argss.length < 1)
			usage();
		if (argss[0].equals("-r")) {
			if (argss.length != 3) {
				usage();
			}

			File old = new File(argss[1]), rname = new File(argss[2]);
			old.renameTo(rname);
			fileDate(old);
			fileDate(rname);
			return; // exit main
		}

		int count = 0;
		boolean del = false;
		if (argss[0].equals("-d")) {
			count++;
			del = true;
		}
		count--;
		while (++count < argss.length) {
			File f = new File(argss[count]);

			if (f.exists())// 如果存在
			{
				System.out.println(f + " exists");
				if (del)// 删除文件目录
				{
					System.out.println("deleting..." + f);
					f.delete();
				}
			} else {// 创建文件目录
				if (!del) {
					f.mkdirs();
					System.out.println("created:" + f);
				}
			}

			fileDate(f);

		}
	}
}
/**
 * output:(80% match) created:MakeDirectoriesTest absolute
 * path:D:\Users\liangrui\workspace\thinking\MakeDirectoriesTest cal read:true
 * can Write:true name:MakeDirectoriesTest parent:null length:0
 * path:MakeDirectoriesTest lastmodiffied:1403013856223 it s a directory
 */
