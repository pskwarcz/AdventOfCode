package aoc.year2022;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import aoc.Utils;

public class Day25 {

	enum Snafu {
		M_TWO('=', -2, 0), M_ONE('-', -1, 1), ZERO('0', 0, 2), ONE('1', 1, 3), TWO('2', 2, 4);

		char c;
		int value;
		int order;

		Snafu(char c, int value, int order) {
			this.c = c;
			this.value = value;
			this.order = order;
		}

		public String toString() {
			return String.valueOf(c);
		}

		static Snafu getByChar(char c) {
			for (Snafu o : Snafu.values()) {
				if (o.c == c) {
					return o;
				}
			}
			return null;
		}
	}

	List<List<Snafu>> numbers = new ArrayList<>();

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2022/input25");

		long result = new Day25().start(lines);

		System.out.println("RESULT:" + result);
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Time[ms]: " + duration);
	}

	public long start(List<String> lines) {
		init(lines);

		long sum = 0;
		for (List<Snafu> n : numbers) {
			long d = toDecimal(n);
			sum += d;
		}

		System.out.println("Result: " + toString(toSnafu(sum)));

		return sum;
	}

	private String toString(List<Snafu> five) {
		StringBuilder sb = new StringBuilder("");
		for (int i = five.size() - 1; i >= 0; i--) {
			sb.append(five.get(i).c);
		}
		return sb.toString();
	}

	private long toDecimal(List<Snafu> n) {
		long v = 0;
		long e = 1;

		for (Snafu f : n) {
			long c = f.value * e;
			// System.out.println("e: " + e + " f:" + f.value + " c:" + c);
			v += c;
			e = e * 5;
		}
		return v;
	}

	private List<Snafu> toSnafu(long v) {
		List<Snafu> r = new ArrayList<>();

		long rest = v;
		do {
			int n = BigDecimal.valueOf(rest + 2).remainder(BigDecimal.valueOf(5)).intValue();
			Snafu number = Snafu.values()[(5 + n) % 5];
			rest = BigDecimal.valueOf(rest - number.value).divide(BigDecimal.valueOf(5), 50, RoundingMode.FLOOR)
					.longValue();

			r.add(number);

		} while (rest != 0);

		return r;
	}

	private void init(List<String> lines) {

		for (String line : lines) {

			List<Snafu> number = new ArrayList<Snafu>();
			for (int i = 0; i < line.length(); i++) {
				number.add(Snafu.getByChar(line.charAt(line.length() - 1 - i)));
			}
			numbers.add(number);
		}
	}

}
