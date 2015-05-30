package org.rui.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Ŀ¼�б��� ���� ɨ��Ŀ¼�µ��ļ�
 * 
 * @author lenovo
 * 
 */
// Args:"C:/Users/lenovo/Pictures/screen/*\.jpg"
public class DirList3 {
	public static void main(String[] args) {
		final String[] argss = new String[1];
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
			list = path.list(new FilenameFilter() {// �ļ�������
						private Pattern pattern = Pattern.compile(argss[0]);

						@Override
						public boolean accept(File dir, String name) {
							return pattern.matcher(name).matches();
						}
					});
			// ��ʾ�ļ���������ļ���������ƥ��
			// list = path.list(new DirFilter(argss[0]));

		}
		// �����ӡ
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String dirItem : list) {
			System.out.println(dirItem);
		}
	}

}