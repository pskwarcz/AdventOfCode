package aoc.year2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Utils;

public class Day3b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day3b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input3");

		long result = process(lines);

		System.out.println("result: " + result);
	}

	long process(List<String> lines) {

		char[][] engine = load(lines);
		Utils.print(engine);

		long sum = countNumbers(engine);

		return sum;
	}

	private long countNumbers(char[][] engine) {
		long sum = 0;

		Map<Point, List<Integer>> stars = new HashMap<>();

		int y = 0;
		for (char[] row : engine) {
			int x = 0;
			int n = 0;
			Point partPos = null;

			for (char c : row) {

				if (Character.isDigit(c)) {
					int d = Character.getNumericValue(c);

					n = 10 * n + d;
					Point symb = gotSymbol(engine, y, x);
					if (symb != null) {
						partPos = symb;
					}

				}

				if (!Character.isDigit(c) || x == row.length - 1) {
					if (n > 0) {
						if (partPos != null) {

							if (engine[partPos.y][partPos.x] == '*') {
								// System.out.println("star at " + partPos);
								if (!stars.containsKey(partPos)) {
									stars.put(partPos, new ArrayList<>());

								}
								stars.get(partPos).add(n);

							}
						}
					}

					n = 0;
					partPos = null;
				}

				x++;

			}
			y++;
		}

		sum = countGears(stars);

		return sum;

	}

	private long countGears(Map<Point, List<Integer>> stars) {
		long sum = 0;
		for (List<Integer> numbers : stars.values()) {
			if (numbers.size() == 2) {
				sum += numbers.get(0) * numbers.get(1);
			}
		}
		return sum;
	}

	private Point gotSymbol(char[][] engine, int py, int px) {
		for (int y = py - 1; y <= py + 1; y++) {
			for (int x = px - 1; x <= px + 1; x++) {

				boolean s = isSymbol(engine, x, y);
				if (s) {
					return new Point(x, y);
				}
			}
		}
		return null;
	}

	private boolean isSymbol(char[][] engine, int x, int y) {
		if (x < 0 || x >= engine[0].length || y < 0 || y >= engine.length) {
			return false;
		}
		char c = engine[y][x];

		return !Character.isDigit(c) && c != '.';
	}

	private char[][] load(List<String> lines) {
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

}
