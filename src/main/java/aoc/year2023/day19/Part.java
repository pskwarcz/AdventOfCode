package aoc.year2023.day19;

import java.util.HashMap;
import java.util.Map;

public class Part {
	Map<Character, Integer> values = new HashMap<>();

	public Part(String line) {
		String[] p = line.replaceAll("\\{|\\}", "").split(",");
		for (String s : p) {
			Character c = s.charAt(0);
			Integer v = Integer.valueOf(s.substring(2));
			values.put(c, v);
		}
		System.out.println(values);
	}

	// e.g.: condition = "x>2662"
	public boolean isTrue(String condition) {
		Character c = condition.charAt(0);
		int v = Integer.parseInt(condition.substring(2));
		switch (condition.charAt(1)) {
		case '<':
			return values.get(c).intValue() < v;
		case '>':
			return values.get(c).intValue() > v;
		default:
			throw new IllegalArgumentException("Unknown condition: " + condition);
		}
	}

	public int getRating() {
		return values.values().stream().mapToInt(i -> i).sum();
	}

}
