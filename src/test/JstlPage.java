package test;

public class JstlPage {

	public static void main(String[] args) {
		int showNum = 10;
		int startR = 1;// 起始
		// 当前页
		int pags = 99; // 总页数
		// System.out.println(pags % showNum);// 当前页 % 显示条数=是否翻 页
		// i==当前页
		for (int i = 0; i < pags; i++) {
			if (i % showNum == 0) {
				System.out.println("起始页" + i + ":翻页");
				System.out.println("\t显示的页码:");
				for (int j = i + 1; j < (i + 1 + showNum); j++) {
					System.out.print(" " + j);
				}
				System.out.println();
			}
		}
	}

}

/** output:
 * 起始页0:翻页
	显示的页码:
 1 2 3 4 5 6 7 8 9 10
起始页10:翻页
	显示的页码:
 11 12 13 14 15 16 17 18 19 20
起始页20:翻页
	显示的页码:
 21 22 23 24 25 26 27 28 29 30
起始页30:翻页
	显示的页码:
 31 32 33 34 35 36 37 38 39 40
起始页40:翻页
	显示的页码:
 41 42 43 44 45 46 47 48 49 50
起始页50:翻页
	显示的页码:
 51 52 53 54 55 56 57 58 59 60
起始页60:翻页
	显示的页码:
 61 62 63 64 65 66 67 68 69 70
起始页70:翻页
	显示的页码:
 71 72 73 74 75 76 77 78 79 80
起始页80:翻页
	显示的页码:
 81 82 83 84 85 86 87 88 89 90
起始页90:翻页
	显示的页码:
 91 92 93 94 95 96 97 98 99 100

 */
