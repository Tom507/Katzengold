package base2;

import java.util.Random;

public class Utils {
	static Random random = new Random();
	static String vokal = "aeuioäüö";
	static String konsonanten = "bcdfghjklmnpqrstvwxyz";

	public static String getRandomName() {
		String name;
		name = "" + randomElement(konsonanten);
		name = name.toUpperCase();
		for (int i = 1; i < 6; i++) {
			if (i == 1 || i == 4) {
				name += randomElement(vokal);
			} else {
				name += randomElement(konsonanten);
			}
		}
		return name;
	}

	static char randomElement(String s) {
		int n = (int) (Math.random() * s.length());
		return s.charAt(n);
	}

}
