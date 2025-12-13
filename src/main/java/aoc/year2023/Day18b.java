package aoc.year2023;

import java.util.ArrayList;
import java.util.List;

public class Day18b extends Day18 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day18b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	@Override
	long process(List<String> lines) {
		lines = decode(lines);
		return super.process(lines);
	}

	private List<String> decode(List<String> lines) {
		List<String> result = new ArrayList<>();
		for (String line : lines) {
			String decoded = decode(line);
			result.add(decoded);
			System.out.println(line + " -> " + decoded);
		}
		return result;
	}

	private String decode(String line) {
		String s = line.split("\\(")[1];
		String hex = s.substring(1, 6);
		char d = s.charAt(6);
		StringBuilder b = new StringBuilder();
		b.append(decodeDirection(d));
		b.append(" ");
		b.append(Long.parseLong(hex, 16));
		return b.toString();
	}

	private Object decodeDirection(char d) {
		switch (d) {
		case '0':
			return 'R';
		case '1':
			return 'D';
		case '2':
			return 'L';
		case '3':
			return 'U';
		}
		throw new IllegalArgumentException("Unknown direction: " + d);
	}

}
