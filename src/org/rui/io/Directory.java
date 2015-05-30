package org.rui.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Ŀ¼ʵ�ù���
 * 
 * @author lenovo
 * 
 */
public class Directory {
	public static File[] local(File dir, final String regex) {
		return dir.listFiles(new FilenameFilter() {
			private Pattern pa = Pattern.compile(regex);

			public boolean accept(File dir, String name) {
				return pa.matcher(name).matches();
			}
		});
	}

	// overloaded����
	public static File[] local(String path, final String regex) {
		return local(new File(path), regex);
	}

	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();

		public Iterator<File> iterator() {
			return files.iterator();
		}

		void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}

		public String toString() {
			return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles:"
					+ PPrint.pformat(files);
		}

		public static TreeInfo walk(String start, String regex) {
			return recurseDirs(new File(start), regex);
		}

		public static TreeInfo walk(File f, String regex) {
			return recurseDirs(f, regex);
		}

		public static TreeInfo walk(File f) {
			return recurseDirs(f, ".*");
		}

		public static TreeInfo walk(String start) {
			return recurseDirs(new File(start), ".*");
		}

		// �ݹ�
		static TreeInfo recurseDirs(File f, String reg) {
			TreeInfo result = new TreeInfo();
			for (File item : f.listFiles()) {
				if (item.isDirectory()) {
					result.dirs.add(item);// ���Ŀ¼����
					result.addAll(recurseDirs(item, reg));// �ݹ�ط��� ѭ����Ŀ¼
				} else {
					if (item.getName().matches(reg)) {
						result.files.add(item);// ����ļ�
					}
				}
			}
			return result;
		}

		// ////////////////////////mian
		public static void main(String[] args) {
			String[] argss = new String[1];
			// C:\\Users\\lenovo\\Pictures\\screen
			argss[0] = ".";
			if (argss.length == 0)
				System.out.println(walk("."));
			else
				for (String arg : argss) {
					System.out.println(walk(arg));
				}
		}

	}

}
/**
 * output:
 * 
 * //result.addAll(recurseDirs(item,reg));//�����ǲ��ݹ��������ļ�̫�� �������
 * 
 * dirs: [.\.settings, .\bin, .\src]
 * 
 * files:[.\.classpath, .\.project]
 * ---------------------------------------------------------------------
 * ----------��ʽ��������--------------
 * --------------------------------------------------------------------- dirs: [
 * .\.settings .\bin .\src ]
 * 
 * files:[ .\.classpath .\.project ]
 */
