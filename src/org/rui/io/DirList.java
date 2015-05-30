package org.rui.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Ŀ¼�б��� ����
 * 
 * @author lenovo
 * 
 */
// Args:"C:/Users/lenovo/Pictures/screen/*\.jpg"
public class DirList {
	public static void main(String[] args) {
		String[] argss = new String[1];
		// path=".";
		argss[0] = ".*.rec.*.java";// "D.*\\.java";

		String s = DirectoryDemo.class.getPackage().getName().replace(".", "/");
		String dirPath = new File("").getAbsolutePath() + "\\src\\" + s;

		File path = new File(dirPath);
		String[] list = null;
		if (argss.length == 0) {
			list = path.list();
		} else {
			System.out.println("all file list==>"
					+ Arrays.toString(path.list()));
			// ��ʾ�ļ���������ļ���������ƥ��
			list = path.list(new DirFilter(argss[0]));

		}
		// �����ӡ
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list)
			System.out.println(dirItem);
	}

}

/**
 * output: all file list==>[D.txt, Directory.java, DirectoryDemo.java,
 * DirList.java, fout, inout, Main.java, PPrint.java, zzz.class] Directory.java
 * DirectoryDemo.java DirList.java
 * 
 */

/**
 * ����һ������ģʽ������
 * 
 * @author lenovo
 * 
 */
class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();

	}

}