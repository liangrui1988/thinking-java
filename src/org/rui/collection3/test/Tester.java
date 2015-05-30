package org.rui.collection3.test;

import java.util.List;
/**
 * ���� ���ܲ��Կ�� 
 * @author lenovo
 *
 * @param <C>
 */
public class Tester<C> {
	public static int fieldWidth = 8;
	public static TestParam[] defaultParams = TestParam.array(10, 5000, 100,
			5000, 1000, 5000, 500);

	// override this to modify pre-test initialization;
	protected C initialize(int size) {
		return container;
	}

	protected C container;
	private String headline = "";
	private List<Test<C>> tests;

	private static String stringField() {
		return "%" + fieldWidth + "s";
	}

	private static String numberField() {
		return "%" + fieldWidth + "d";
	}

	private static int sizeWidth = 5;
	private static String sizeField = "%" + sizeWidth + "s";
	private TestParam[] paramList = defaultParams;

	public Tester(C container, List<Test<C>> tester) {
		this.container = container;
		this.tests = tester;

	}

	public Tester(C container, List<Test<C>> tester, TestParam[] paramList) {
		this.container = container;
		this.tests = tester;
		this.paramList = paramList;

	}

	public void setHeadline(String newHeadline) {
		this.headline = newHeadline;
	}

	// generic methods for convenience:���ͷ���Ϊ�˷���:
	public static <C> void run(C cntnr, List<Test<C>> tests) {
		new Tester<C>(cntnr, tests).timedTest();
	}

	public static <C> void run(C cntnr, List<Test<C>> tests,
			TestParam[] paramList) {
		new Tester<C>(cntnr, tests, paramList).timedTest();
	}

	private void displayHeader() {
		// calculate width and pad with '-' �����Ⱥ͵�
		int width = fieldWidth * tests.size() + sizeWidth;
		int dashLength = width - headline.length() - 1;
		StringBuilder head = new StringBuilder(width);
		for (int i = 0; i < dashLength / 2; i++) {
			head.append("-");
		}
		head.append(" ");
		head.append(headline);
		head.append(" ");
		for (int i = 0; i < dashLength / 2; i++) {
			head.append("-");
		}
		System.out.println(head);
		// print column headers:
		for (Test test : tests)
			System.out.format(stringField(), test.name);
		System.out.println();
	}

	// run the tests for this container:������������в���:
	public void timedTest() {
		displayHeader(); // ��ӡͷ��Ϣ
		for (TestParam param : paramList) {
			System.out.format(sizeField, param.size);
			for (Test<C> test : tests) {
				C kontainer = initialize(param.size);
				long start = System.nanoTime();
				// call the overriden method:
				int reps = test.test(kontainer, param);
				long duration = System.nanoTime() - start;
				long timePerRep = duration / reps;// Nanoseconds
				System.out.format(numberField(), timePerRep);
			}
			System.out.println();
		}
	}

}
