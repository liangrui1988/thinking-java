package org.rui.io;

import java.io.File;
import java.util.regex.Pattern;

public class DirectoryDemo {

	public static void main(String[] args) {

		String s = DirectoryDemo.class.getPackage().getName().replace(".", "/");
		String path = new File("").getAbsolutePath() + "\\src\\" + s;
		// System.out.println(path);
		// path=".";
		PPrint.pprint(Directory.TreeInfo.walk(path).dirs);
		// all files beginning with "t"
		for (File f : Directory.local(path, "D.*"))
			System.out.println(f);
		System.out.println("----------------");
		for (File f : Directory.local(path, "D.*\\.java"))
			System.out.println(f);
		System.out.println("================");
		// class files containing "z" or "Z"
		for (File f : Directory.TreeInfo.walk(path, ".*[Zz].*\\.class"))
			System.out.println(f);
	}
}
/**
 * [ D:\Users\liangrui\workspace\thinking\src\org\rui\io\fout
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\fout\f
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\inout ]
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\D.txt
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\Directory.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\DirectoryDemo.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\DirList.java
 * ----------------
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\Directory.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\DirectoryDemo.java
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\DirList.java
 * ================
 * D:\Users\liangrui\workspace\thinking\src\org\rui\io\zzz.class
 */
