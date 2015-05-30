package org.rui.stringTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
	// Args: abcabcabcdefabc "abc+" "(abc)+" "(abc){2,}"
	public static void main(String[] args) {
		System.out.println(args.length);
		for (String s : args) {
			System.out.println("s:" + s);
		}
		if (args.length < 2) {
			System.out.println("usage:---");
			System.exit(0);
		}
		// System.out.println(args[0]);
		System.out.println();
		for (String arg : args) {
			System.out.print("arg:\"" + arg + "\"");
			Pattern p = Pattern.compile(arg);
			Matcher m = p.matcher(args[0]);
			while (m.find()) {
				System.out.print("   group:" + m.group()
						+ "at positions  statr:" + m.start() + "  end:"
						+ (m.end() - 1));
			}
			System.out.println();
		}

	}

}
