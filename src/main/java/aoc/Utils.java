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

	public static void printMatrix(char[][] t) {
		for (char[] row : t) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static char[][] toMatrix(List<String> lines) {
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] e = new char[ySize][xSize];
		int y = 0;
		for (String line : lines) {
			e[y] = line.toCharArray();
			y++;
		}
		return e;
	}

	public static long[] toNumbers(String[] src) {
		long[] n = new long[src.length];
		int i = 0;
		for (String s : src) {
			n[i] = Long.parseLong(s);
			i++;
		}
		return n;
	}

	public static int[] toIntegers(String[] src) {
		int[] n = new int[src.length];
		int i = 0;
		for (String s : src) {
			n[i] = Integer.parseInt(s);
			i++;
		}
		return n;
	}

}
