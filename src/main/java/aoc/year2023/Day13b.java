package aoc.year2023;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import aoc.Utils;

public class Day13b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day13b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input13.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		List<List<String>> patterns = readPatterns(lines);

		return patterns.stream().mapToLong(this::differentReflNumber).sum();
	}

	public List<List<String>> readPatterns(List<String> lines) {
		List<List<String>> patterns = new ArrayList<>();

		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			patterns.add(readPattern(it));
		}
		return patterns;
	}

	private long differentReflNumber(List<String> pattern) {
		for (String l : pattern) {
			System.out.println("\t" + l);
		}
		long firstV = verticalReflection(pattern, 0);
		long firstH = horizontalReflection(pattern, 0);
		System.out.println("\tFirst num: " + firstV + "/" + firstH);
		int yMax = pattern.size();
		int xMax = pattern.get(0).length();

		for (int y = 0; y < yMax; y++) {
			for (int x = 0; x < xMax; x++) {
				List<String> copy = new ArrayList<String>(pattern);
				change(copy, x, y);
				System.out.println("changed: (" + x + "," + y + ")");
				long v = verticalReflection(copy, firstV);
				if (v > 0) {
					System.out.println("different number found (" + x + "," + y + ") v:" + v);
					return v;
				}

				long h = 100 * horizontalReflection(copy, firstH);
				if (h > 0) {
					System.out.println("different number found (" + x + "," + y + ") h:" + h);
					return h;
				}

			}
		}
		throw new IllegalStateException("new pattern not found");
	}

	private void change(List<String> pattern, int x, int y) {
		String line = pattern.get(y);
		char c = line.charAt(x);
		c = c == '.' ? '#' : '.';
		StringBuilder sb = new StringBuilder(line);
		sb.setCharAt(x, c);
		pattern.set(y, sb.toString());
	}

	private long verticalReflection(List<String> pattern, long excludeV) {
		for (int x = 1; x < pattern.get(0).length(); x++) {
			if (x == excludeV) {
				continue;
			}
			if (isVerticalReflection(pattern, x)) {
				System.out.println("v=" + x);
				return x;
			}
		}
		return 0;
	}

	private boolean isVerticalReflection(List<String> pattern, int x) {
		for (String line : pattern) {
			if (!isVerticalReflection(line, x)) {
				return false;
			}
		}
		return true;
	}

	private boolean isVerticalReflection(String line, int x) {
		for (int i = 1; x - i >= 0 && x - 1 + i < line.length(); i++) {
			if (line.charAt(x - i) != line.charAt(x - 1 + i)) {
				return false;
			}
		}
		return true;
	}

	private int horizontalReflection(List<String> pattern, long exclude) {
		for (int y = 1; y < pattern.size(); y++) {
			if (y == exclude) {
				continue;
			}
			if (isHorizontalReflection(pattern, y)) {
				System.out.println("h=" + y);
				return y;
			}
		}
		return 0;
	}

	// y=4
	private boolean isHorizontalReflection(List<String> pattern, int y) {
		for (int i = 1; y - i >= 0 && y - 1 + i < pattern.size(); i++) {
			if (!pattern.get(y - i).equals(pattern.get(y - 1 + i))) {
				return false;
			}
		}
		return true;
	}

	private List<String> readPattern(Iterator<String> it) {
		List<String> pattern = new ArrayList<>();
		while (it.hasNext()) {
			String line = it.next();
			if (StringUtils.isEmpty(line)) {
				break;
			}
			pattern.add(line);
		}
		return pattern;
	}

}
