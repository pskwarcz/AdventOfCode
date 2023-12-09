package aoc.year2023;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day9 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day9().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {

		long sum = lines.stream().map(l -> this.extrapolate(l)).reduce(0L, Long::sum);

		return sum;
	}

	long extrapolate(String line) {

		List<Long> l = mapToLongList(line);
		System.out.println(l);

		List<List<Long>> ll = new ArrayList<>();
		ll.add(l);

		while (!allZeros(l)) {
			List<Long> next = nextLevel(l);
			ll.add(next);
			l = next;
		}

		long e = 0;
		for (int i = ll.size() - 2; i >= 0; i--) {
			l = ll.get(i);
			long last = ll.get(i).get(l.size() - 1);
			e += last;
			System.out.println("last:" + last + " e:" + e);
		}

		return e;
	}

	List<Long> nextLevel(List<Long> l) {
		List<Long> next = new ArrayList<>();
		Iterator<Long> it = l.iterator();
		long a = it.next();

		while (it.hasNext()) {
			long b = it.next();
			next.add(b - a);
			a = b;
		}
		System.out.println(next);
		return next;
	}

	private boolean allZeros(List<Long> l) {
		return !l.stream().filter(n -> !n.equals(Long.valueOf(0L))).findFirst().isPresent();
	}

	public List<Long> mapToLongList(String line) {
		String[] ss = line.split("\\s");
		List<Long> l = new ArrayList<>();
		for (String s : ss) {
			l.add(Long.parseLong(s));
		}
		return l;
	}

}
