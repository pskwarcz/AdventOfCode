package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day14 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day14().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input14.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		print(lines);

		moveRocks(lines);

		System.out.println();
		print(lines);
		long sum = calculateLoad(lines);
		System.out.println("load: " + sum);
		return sum;
	}

	void moveRocks(List<String> lines) {
		while (moveRocksN(lines)) {
			// System.out.println();
			// print(lines);
		}
	}

	private long calculateLoad(List<String> lines) {
		long sum = 0;

		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(0).length(); x++) {

				char c = lines.get(y).charAt(x);

				if (c == 'O') {
					sum += lines.size() - y;
				}

			}
		}

		return sum;
	}

	void print(List<String> lines) {
		for (String line : lines) {
			System.out.println(line);
		}

	}

	boolean moveRocksN(List<String> lines) {
		boolean moved = false;
		for (int y = 0; y < lines.size() - 1; y++) {
			for (int x = 0; x < lines.get(0).length(); x++) {
				char c = lines.get(y).charAt(x);
				char up = lines.get(y + 1).charAt(x);
				if (c == '.' && up == 'O') {
					moveRock(lines, y + 1, x, y, x);
					moved = true;
				}
			}
		}
		return moved;
	}

	void moveRock(List<String> lines, int fromY, int fromX, int y, int x) {
		StringBuilder sb = new StringBuilder(lines.get(y));
		sb.setCharAt(x, 'O');
		lines.set(y, sb.toString());

		sb = new StringBuilder(lines.get(fromY));
		sb.setCharAt(fromX, '.');
		lines.set(fromY, sb.toString());
	}

}
