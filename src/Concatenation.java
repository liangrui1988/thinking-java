import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

public class Concatenation {
	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		/*
		 * String mango="mango"; String s="abc"+"mango"+"def"+47;
		 * System.out.println(s);
		 */

		String str = "avvbb";
		// System.out.println(str.contains("c"));x=8;

		int x = 8;
		double y = 66.99;
		System.out.format("Row 1: [%d %f]\n", x, y);

		Formatter f = new Formatter();

		System.out.println(f.format("%-15s %5s %20s\n", "ppite", "fff", "==="));
		System.out.println(f.format("%-15.5s %5d %20f\n", "uuite", x, y));

	}

}
