package aoc.year2023;

import java.util.List;

import aoc.Utils;

public class Day12b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day12b().start();
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
		sum = lines.parallelStream().mapToLong(this::countArrangements).sum();
		return sum;
	}

	protected long countArrangements(String line) {
		long start = System.currentTimeMillis();

		String p = line.split("\\s")[0];
		StringBuilder s = new StringBuilder(p);

		String i = line.split("\\s")[1];
		StringBuilder inf = new StringBuilder(i);

		for (int n = 1; n < 5; n++) {
			s.append('?').append(p);
			inf.append(',').append(i);
		}

		char[] pattern = s.toString().toCharArray();
		int[] info = Utils.toIntegers(inf.toString().split(","));

		// System.out.println(String.valueOf(pattern) + " " + Arrays.toString(info));
		long sum = count(pattern, info);

		long end = System.currentTimeMillis();
		long duration = end - start;

		System.out.println(line + " sum=" + sum + " duration=" + duration);
		return sum;
	}

	long count(char[] pattern, int[] info) {
		long n = 0;
		n = countSolutions(pattern, info, 0, 0);

		return n;
	}

	public long countSolutions(char[] pattern, int[] info, int idx, int startAt) {
		int i = info[idx];

		long cnt = 0;

		for (int x = startAt; x <= pattern.length - i; x++) {

			if (canMatch(i, pattern, x)) {
				if (idx == info.length - 1) {
					// last info

					if (!anyRemaining(pattern, x + i)) {
						cnt++;

						// System.out.println("solution: " + String.valueOf(pattern));
					}

				} else {
					cnt += countSolutions(pattern, info, idx + 1, x + i + 1);
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
			pattern[x] = 'X';
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
