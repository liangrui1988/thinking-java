package org.rui.inner01;

public class Parcel15 {

	public interface Destination {
		String readLable();
	}

	public Destination destination(String s) {
		// meothd inner class
		class PDestination implements Destination {
			String lable;

			PDestination(String s) {
				lable = s;
			}

			public String readLable() {
				return lable;
			}
		}

		return new PDestination(s);
	}

	public static void main(String[] args) {
		Parcel15 p = new Parcel15();
		Destination des = p.destination("tasmania");
		System.out.println(des.readLable());
	}

}
