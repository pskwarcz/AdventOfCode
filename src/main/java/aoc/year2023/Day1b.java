package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day1b {

	enum digits {
		one, two, three, four, five, six, seven, eight, nine;

		public int getNumber() {
			return this.ordinal() + 1;
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day1b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1b");

		int result = process(lines);

		System.out.println("result: " + result);
	}

	int process(List<String> lines) {
		int sum = 0;
		for (String line : lines) {
			sum += getNumber(line);
		}
		return sum;
	}

	private int getNumber(String line) {
		char[] a = line.toCharArray();

		Integer first = null;
		Integer last = null;

		int idx = -1;
		for (char c : a) {
			idx++;

			Integer i = null;
			if (Character.isDigit(c)) {
				i = Integer.valueOf(String.valueOf(c));
			}

			if (i == null) {
				i = getWordNumber(line, idx);

			}

			if (i != null) {
				if (first == null) {
					first = i;
				}
				last = i;
			}

		}

		return first * 10 + last;
	}

	private Integer getWordNumber(String line, int idx) {
		for (digits d : digits.values()) {
			if (line.startsWith(d.name(), idx)) {
				return d.getNumber();
			}
		}
		return null;
	}

}
