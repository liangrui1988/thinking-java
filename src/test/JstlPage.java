package test;

public class JstlPage {

	public static void main(String[] args) {
		int showNum = 10;
		int startR = 1;// ��ʼ
		// ��ǰҳ
		int pags = 99; // ��ҳ��
		// System.out.println(pags % showNum);// ��ǰҳ % ��ʾ����=�Ƿ� ҳ
		// i==��ǰҳ
		for (int i = 0; i < pags; i++) {
			if (i % showNum == 0) {
				System.out.println("��ʼҳ" + i + ":��ҳ");
				System.out.println("\t��ʾ��ҳ��:");
				for (int j = i + 1; j < (i + 1 + showNum); j++) {
					System.out.print(" " + j);
				}
				System.out.println();
			}
		}
	}

}

/** output:
 * ��ʼҳ0:��ҳ
	��ʾ��ҳ��:
 1 2 3 4 5 6 7 8 9 10
��ʼҳ10:��ҳ
	��ʾ��ҳ��:
 11 12 13 14 15 16 17 18 19 20
��ʼҳ20:��ҳ
	��ʾ��ҳ��:
 21 22 23 24 25 26 27 28 29 30
��ʼҳ30:��ҳ
	��ʾ��ҳ��:
 31 32 33 34 35 36 37 38 39 40
��ʼҳ40:��ҳ
	��ʾ��ҳ��:
 41 42 43 44 45 46 47 48 49 50
��ʼҳ50:��ҳ
	��ʾ��ҳ��:
 51 52 53 54 55 56 57 58 59 60
��ʼҳ60:��ҳ
	��ʾ��ҳ��:
 61 62 63 64 65 66 67 68 69 70
��ʼҳ70:��ҳ
	��ʾ��ҳ��:
 71 72 73 74 75 76 77 78 79 80
��ʼҳ80:��ҳ
	��ʾ��ҳ��:
 81 82 83 84 85 86 87 88 89 90
��ʼҳ90:��ҳ
	��ʾ��ҳ��:
 91 92 93 94 95 96 97 98 99 100

 */
