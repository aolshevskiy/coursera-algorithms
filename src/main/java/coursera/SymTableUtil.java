package coursera;

import static com.google.common.base.Preconditions.checkArgument;

public class SymTableUtil {
	public static char toUpperChar(int i) {
		checkArgument(i >= 0 && i < 26);
		return (char) (i + 'A');
	}
	public static int fromUpperChar(char c) {
		checkArgument('A' <= c && c <= 'Z');
		return c - 'A';
	}

	public static int fromUpperChar(String c) {
		checkArgument(c.length() == 1);
		return fromUpperChar(c.charAt(0));
	}
}
