package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

	public static List<String> readFile(String filename) {
		InputStream is = Utils.class.getResourceAsStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br.lines().collect(Collectors.toList());
	}

	public static void print(char[][] t) {
		for (char[] row : t) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
