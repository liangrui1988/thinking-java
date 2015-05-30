public class Parce17 {
	public Contents contents(){
		return new Contents(){
			private int i=11;

			@Override
			public int value() {
				// TODO Auto-generated method stub
				return i;
			}
			
		};
	}
	public static void main(String[] args) {

		Parce17 pa=new Parce17();
		Contents s=pa.contents();
		System.out.println(s.value());
	}

}
