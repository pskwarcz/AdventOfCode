package aoc.year2023;

import java.util.Arrays;
import java.util.List;

import aoc.Utils;

public class Day12 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day12().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		long sum = 0;
		for (String line : lines) {
			sum += countArrangements(line);
		}
		return sum;
	}

	private long countArrangements(String line) {
		char[] pattern = readPattern(line);
		int[] info = readInfo(line);

		System.out.println(String.valueOf(pattern) + " " + Arrays.toString(info));

		return count(pattern, info);
	}

	public int[] readInfo(String line) {
		return Utils.toIntegers(line.split("\\s")[1].split(","));
	}

	public char[] readPattern(String line) {
		return line.split("\\s")[0].toCharArray();
	}

	private long count(char[] pattern, int[] info) {
		int n = 0;

		n = countSolutions(pattern, info, 0, 0);

		System.out.println("count " + n);
		System.out.println();
		return n;
	}

	public int countSolutions(char[] pattern, int[] info, int idx, int startAt) {
		int i = info[idx];

		int cnt = 0;

		for (int x = startAt; x <= pattern.length - i; x++) {

			if (canMatch(i, pattern, x)) {
				if (idx == info.length - 1) {
					// last info

					if (!anyRemaining(pattern, x + i)) {
						cnt++;

						char[] solution = Arrays.copyOf(pattern, pattern.length);
						update(solution, i, x);
						System.out.println("solution: " + String.valueOf(solution));
					}

				} else {
					char[] solution = Arrays.copyOf(pattern, pattern.length);
					update(solution, i, x);
					cnt += countSolutions(solution, info, idx + 1, x + i + 1);
				}

			}

			if (pattern[x] == '#') {
				break;
			}
		}
		return cnt;
	}

	private boolean anyRemaining(char[] pattern, int startAt) {
		for (int x = startAt; x < pattern.length; x++) {
			if (pattern[x] == '#') {
				return true;
			}
		}
		return false;
	}

	private void update(char[] pattern, int i, int startAt) {
		for (int x = startAt; x < startAt + i; x++) {
			pattern[x] = '#';
		}
		if (i + startAt == pattern.length) {
			return;
		}

	}

	// can match group of i damaged springs starting from x position
	// should fit i # followed by .
	private boolean canMatch(int i, char[] pattern, int startAt) {
		for (int x = startAt; x < startAt + i; x++) {
			char c = pattern[x];
			if (c != '#' && c != '?') {
				return false;
			}
		}
		if (startAt + i == pattern.length) {
			return true;
		}
		char c = pattern[i + startAt];
		return c == '.' || c == '?';
	}

}
