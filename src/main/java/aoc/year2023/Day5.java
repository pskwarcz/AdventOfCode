package aoc.year2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day5 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day5().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input5");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		Iterator<String> l = lines.iterator();
		String[] src = l.next().split(":")[1].trim().split(" ");
		long[] s = toNumbers(src);
		System.out.println(Arrays.toString(s));

		l.next(); // empty row

		while (l.hasNext()) {
			List<long[]> map = loadMap(l);

			for (long[] r : map) {
				System.out.println(Arrays.toString(r));
			}

			s = translate(s, map);
		}

		System.out.println(Arrays.toString(s));

		long min = findMin(s);

		return min;
	}

	public long findMin(long[] s) {
		long min = s[0];
		for (long c : s) {
			if (c < min) {
				min = c;
			}
		}
		return min;
	}

	private long[] translate(long[] s, List<long[]> map) {
		long[] d = new long[s.length];

		for (int i = 0; i < s.length; i++) {
			d[i] = map(s[i], map);
		}

		return d;
	}

	private long map(long d, List<long[]> map) {
		for (long[] m : map) {
			if (d >= m[1] && d < m[1] + m[2]) {
				return m[0] + d - m[1];
			}
		}
		return d;
	}

	private List<long[]> loadMap(Iterator<String> l) {
		System.out.println(l.next());

		List<long[]> m = new ArrayList<>();

		while (l.hasNext()) {
			String line = l.next();
			if (line.equals("")) {
				break;
			}
			m.add(toNumbers(line.split(" ")));
		}

		return m;
	}

	private long[] toNumbers(String[] src) {
		long[] n = new long[src.length];
		int i = 0;
		for (String s : src) {
			n[i] = Long.parseLong(s);
			i++;
		}
		return n;
	}

}
