package test;

public class Tests extends SimpleCalc {
	
	public static void main(String[] args) {
		Tests t=new Tests();
		t.caculate(2);
		System.out.println(t.value);
	}
	
	
	public void caculate(){
		value-=3;
	}
	
	public void caculate(int multiplier){
		caculate();
		super.caculate();
		value*=multiplier;
	}

}

class SimpleCalc{
	public int value;
	public void caculate(){
		value+=7;
	}
	
}
