package aoc.year2023;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import aoc.Utils;

public class Day13 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day13().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input13.txt");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		List<List<String>> patterns = readPatterns(lines);

		return patterns.stream().mapToLong(this::reflectionNumber).sum();
	}

	long reflectionNumber(List<String> pattern) {
		for (String s : pattern) {
			System.out.println(s);
		}
		long n = 0;
		n += verticalReflection(pattern);
		n += 100 * horizontalReflection(pattern);
		return n;
	}

	private long verticalReflection(List<String> pattern) {
		for (int x = 1; x < pattern.get(0).length(); x++) {
			if (isVerticalReflection(pattern, x)) {
				System.out.println("v=" + x);
				return x;
			}
		}
		return 0;
	}

	boolean isVerticalReflection(List<String> pattern, int x) {
		for (String line : pattern) {
			if (!isVerticalReflection(line, x)) {
				return false;
			}
		}
		return true;
	}

	boolean isVerticalReflection(String line, int x) {
		for (int i = 1; x - i >= 0 && x - 1 + i < line.length(); i++) {
			if (line.charAt(x - i) != line.charAt(x - 1 + i)) {
				return false;
			}
		}
		return true;
	}

	int horizontalReflection(List<String> pattern) {
		for (int y = 1; y < pattern.size(); y++) {
			if (isHorizontalReflection(pattern, y)) {
				System.out.println("h=" + y);
				return y;
			}
		}
		return 0;
	}

	// y=4
	boolean isHorizontalReflection(List<String> pattern, int y) {
		for (int i = 1; y - i >= 0 && y - 1 + i < pattern.size(); i++) {
			if (!pattern.get(y - i).equals(pattern.get(y - 1 + i))) {
				return false;
			}
		}
		return true;
	}

	List<List<String>> readPatterns(List<String> lines) {
		List<List<String>> patterns = new ArrayList<>();

		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			patterns.add(readPattern(it));
		}
		return patterns;
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
