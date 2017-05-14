package org.hs.util;

public class AppStringUtils {
	public static void replaceAll(StringBuffer buffer, String search, String replace) {
		int index = buffer.indexOf(search);
		while (index != -1) {
			buffer.replace(index, index + search.length(), replace);
			index += replace.length();
			index = buffer.indexOf(search, index);
		}
	}
	public static void main(String[] args) {
		
	}
}
