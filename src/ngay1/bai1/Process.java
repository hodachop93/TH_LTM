package ngay1.bai1;

import java.util.Scanner;

public class Process {
	public static String toUpperCase(String input){
		String s;
		s=input.toUpperCase();
		return s;
	}
	public static String toLowerCase(String input){
		return input.toLowerCase();
	}
	public static String toUpper_LowerCase(String input){
		String doi = "";
		for (int i = 0; i < input.length(); i++) {
			String tam = input.charAt(i) + "";
			if (input.charAt(i) >= 97 && input.charAt(i) <= 122) {
				doi += tam.toUpperCase();
			} else if (input.charAt(i) >= 65 && input.charAt(i) <= 97) {
				doi += tam.toLowerCase();
			} else {
				doi += tam;
			}
		}
		return doi;
	}
	public static int getLength(String input){
		String[] words = input.split(" ");
		int count = 0;
		for (int i = 0; i < words.length; i++){
			if (!words[i].equals(""))
				count++;
		}
		return count;
	}
}
