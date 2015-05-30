package test;

class Aa{
	{
		i=10;
		System.out.println("{}"+i);
	}
	
	static int i=init();
	static int init(){
		System.out.println("init:"+5);
		return 5;
	}
	public void printinfo(){
		System.out.println("i=:"+i);
	}
}//end class aa

class Bb extends Aa{
	private int i=7;
	public Bb(){
		
		i=15;
		System.out.println("Bb"+i);
	}
	
}

public class At {
	public static void main(String[] args) {
		System.out.println("statr");
		Bb b=new Bb();
		b.printinfo();
	}
	

}
