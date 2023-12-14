package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

public class Day13b extends Day13 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day13b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long reflectionNumber(List<String> pattern) {
//		for (String l : pattern) { 
//			System.out.println("\t" + l); 
//		} 
		long firstV = verticalReflection(pattern, 0);
		long firstH = horizontalReflection(pattern, 0);
		// System.out.println("\tFirst num: " + firstV + "/" + firstH);
		int yMax = pattern.size();
		int xMax = pattern.get(0).length();

		for (int y = 0; y < yMax; y++) {
			for (int x = 0; x < xMax; x++) {
				List<String> copy = getModified(pattern, x, y);

				long v = verticalReflection(copy, firstV);
				if (v > 0) {
					// System.out.println("different number found (" + x + "," + y + ") v:" + v);
					return v;
				}

				long h = 100 * horizontalReflection(copy, firstH);
				if (h > 0) {
					// System.out.println("different number found (" + x + "," + y + ") h:" + h);
					return h;
				}

			}
		}
		throw new IllegalStateException("new pattern not found");
	}

	private List<String> getModified(List<String> pattern, int x, int y) {
		List<String> changed = new ArrayList<String>(pattern);
		String line = changed.get(y);
		char c = line.charAt(x);
		c = c == '.' ? '#' : '.';
		StringBuilder sb = new StringBuilder(line);
		sb.setCharAt(x, c);
		changed.set(y, sb.toString());
		return changed;
	}

	private long verticalReflection(List<String> pattern, long exclude) {
		for (int x = 1; x < pattern.get(0).length(); x++) {
			if (x == exclude) {
				continue;
			}
			if (isVerticalReflection(pattern, x)) {
				// System.out.println("v=" + x);
				return x;
			}
		}
		return 0;
	}

	int horizontalReflection(List<String> pattern, long exclude) {
		for (int y = 1; y < pattern.size(); y++) {
			if (y == exclude) {
				continue;
			}
			if (isHorizontalReflection(pattern, y)) {
				// System.out.println("h=" + y);
				return y;
			}
		}
		return 0;
	}

}
