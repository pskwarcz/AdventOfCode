package aoc.year2023;

import java.util.HashMap;
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

		String pattern = s.toString();
		int[] info = Utils.toIntegers(inf.toString().split(","));

		// System.out.println(String.valueOf(pattern) + " " + Arrays.toString(info));
		long sum = count(pattern, info);

		long end = System.currentTimeMillis();
		long duration = end - start;

		System.out.println(line + " sum=" + sum + " duration=" + duration);
		return sum;
	}

	long count(String pattern, int[] info) {
		long n = 0;
		Cache cache = new Cache();
		n = countSolutions(pattern, info, 0, 0, cache);

		return n;
	}

	class Cache {

		HashMap<Point, Long> c = new HashMap<>();

		public long get(int idx, int startAt) {
			return c.getOrDefault(new Point(idx, startAt), -1L);
		}

		public void put(int idx, int startAt, long cnt) {
			c.put(new Point(idx, startAt), cnt);
		}

	}

	public long countSolutions(String pattern, int[] info, int idx, int startAt, Cache cache) {
		long c = cache.get(idx, startAt);
		if (c >= 0) {
			return c;
		}

		int i = info[idx];

		long cnt = 0;

		for (int x = startAt; x <= pattern.length() - i; x++) {

			if (canMatch(i, pattern, x)) {
				if (idx == info.length - 1) {
					// last info

					if (!anyRemaining(pattern, x + i)) {
						cnt++;

						// System.out.println("solution: " + String.valueOf(pattern));
					}

				} else {
					cnt += countSolutions(pattern, info, idx + 1, x + i + 1, cache);
				}

			}

			if (pattern.charAt(x) == '#') {
				break;
			}
		}
		cache.put(idx, startAt, cnt);
		return cnt;
	}

	private boolean anyRemaining(String pattern, int startAt) {
		for (int x = startAt; x < pattern.length(); x++) {
			if (pattern.charAt(x) == '#') {
				return true;
			}
		}
		return false;
	}

	// can match group of i damaged springs starting from x position
	// should fit i # followed by .
	private boolean canMatch(int i, String pattern, int startAt) {
		for (int x = startAt; x < startAt + i; x++) {
			char c = pattern.charAt(x);
			if (c != '#' && c != '?') {
				return false;
			}
		}
		if (startAt + i == pattern.length()) {
			return true;
		}
		char c = pattern.charAt(i + startAt);
		return c == '.' || c == '?';
	}

}
