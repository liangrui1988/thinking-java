package test;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		int c = rand.nextInt(8000);
		System.out.println(c);
	}

}
