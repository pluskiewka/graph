package main.util;

public class Random {
	private static final java.util.Random rand = new java.util.Random(
			new java.util.Date().getTime());
	private static final char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static char nextChar() {
		return chars[nextInt(chars.length)];
	}

	public static int nextInt(int modulo) {
		return nextInt(modulo, 1);
	}

	public static int nextInt(int modulo, int multi) {
		return rand.nextInt(modulo) * multi;
	}
}
