import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

public class Finnlays {

	public static void f(int i) {
		System.out.println("init");
		try {

			if (i == 1) {
				System.out.println("point1");
				return;
			}
			
			if (i == 2) {
				System.out.println("point2");
				return;
			}
			if (i == 3) {
				System.out.println("point3");
				return;
			}
			System.out.println("end ");
			return;
		} finally {
			System.out.println("performing cleanup");

		}
	}

	public static void main(String[] args) {
		/*for(int i=0;i<=4;i++){
			Finnlays.f(i);
		}*/
		
		Finnlays.f(1);
		
	}

}
