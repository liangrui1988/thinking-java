package org.rui.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 目录实用工具
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

	// overloaded重载
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

		// 递归
		static TreeInfo recurseDirs(File f, String reg) {
			TreeInfo result = new TreeInfo();
			for (File item : f.listFiles()) {
				if (item.isDirectory()) {
					result.dirs.add(item);// 添加目录集合
					result.addAll(recurseDirs(item, reg));// 递归回方法 循环子目录
				} else {
					if (item.getName().matches(reg)) {
						result.files.add(item);// 添加文件
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
 * //result.addAll(recurseDirs(item,reg));//以下是不递归的输出，文件太多 不好理解
 * 
 * dirs: [.\.settings, .\bin, .\src]
 * 
 * files:[.\.classpath, .\.project]
 * ---------------------------------------------------------------------
 * ----------格式化后的输出--------------
 * --------------------------------------------------------------------- dirs: [
 * .\.settings .\bin .\src ]
 * 
 * files:[ .\.classpath .\.project ]
 */
