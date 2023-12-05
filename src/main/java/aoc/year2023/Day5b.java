package aoc.year2023;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day5b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day5b().start();
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

		List<Long> s = toNumbers(src);
		System.out.println(s);

		l.next(); // empty row

		while (l.hasNext()) {
			List<List<Long>> map = loadMap(l);

			for (List<Long> r : map) {
				System.out.println(r);
			}

			s = translate(s, map);
			System.out.println("results: " + s + "\n");
		}

		System.out.println(s);

		long min = s.get(0);
		for (int i = 0; i < s.size(); i += 2) {
			if (min > s.get(i)) {
				min = s.get(i);
			}
		}

		return min;
	}

	private List<Long> translate(List<Long> s, List<List<Long>> map) {
		List<Long> result = new ArrayList<>();

		for (int i = 0; i < s.size(); i += 2) {
			long value = s.get(i);
			long range = s.get(i + 1);
			List<Long> r = map(value, range, map);
			result.addAll(r);
		}

		return result;
	}

	private List<Long> map(long d, long range, List<List<Long>> map) {
		Iterator<List<Long>> it = map.iterator();
		return map(d, range, it);
	}

	private List<Long> map(long d, long range, Iterator<List<Long>> it) {
		List<Long> result = new ArrayList<>();

		while (it.hasNext()) {
			List<Long> m = it.next();

			long mFrom = m.get(1);
			long mRange = m.get(2);
			long mMax = mFrom + mRange - 1;
			long mTo = m.get(0);

			// no range specified, direct map
			if (d < mFrom) {
				result.add(d);
				if (d + range - 1 < mFrom) {
					result.add(range);
					return result;
				} else {
					long r = mFrom - d;
					result.add(r);
					range = range - r;
					d = d + r;
				}
			}

			// found matching range
			if (d >= mFrom && d <= mMax) {
				result.add(mTo + d - mFrom);
				if (d + range - 1 <= mMax) {
					result.add(range);
					return result;
				} else {
					long r = mFrom + mRange - d;
					result.add(r);

					range = range - r;
					d = d + r;
				}
			}

			// this is last range

			if (!it.hasNext() && d > mMax) {
				result.add(d);
				result.add(range);
				return result;
			} else {
				// continue to next range
			}

		}
		throw new IllegalStateException("not possible");
	}

	private List<List<Long>> loadMap(Iterator<String> l) {
		System.out.println(l.next());

		List<List<Long>> m = new ArrayList<>();

		while (l.hasNext()) {
			String line = l.next();
			if (line.equals("")) {
				break;
			}
			m.add(toNumbers(line.split(" ")));
		}

		m.sort((a, b) -> a.get(1).compareTo(b.get(1)));
		return m;
	}

	private List<Long> toNumbers(String[] src) {
		List<Long> n = new ArrayList<>();
		for (String s : src) {
			n.add(Long.parseLong(s));
		}
		return n;
	}

}
