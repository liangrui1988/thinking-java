package test;

class A{int i=10;}
interface B{int i=1;}

public class Whil/* extends A implements B*/ {
	
	
	/*public void p(){
		System.out.println(i);
	}*/
	public static void main(String[] args) {
		int i = 1, j = 10;
		

		do {
			//System.out.println(i);
			if (j < i) 
				continue;
			j--;
		}
		while (++i < 6);
		System.out.println(i+"\t"+j);
	}
	

}
