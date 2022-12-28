package aoc.year2021;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import aoc.Utils;

public class Day3 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<String> lines = Utils.readFile("/aoc/year2021/input3");

		long result = new Day3().lifeSupportRating(lines);
		System.out.println("\nRESULT: " + result);

		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private int powerConsumption(List<String> lines) {

		int[] cnt = new int[lines.get(0).length()];

		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (c == '1') {
					cnt[i]++;
				}
			}
		}

		StringBuilder gammaSb = new StringBuilder();
		StringBuilder epsilonSb = new StringBuilder();
		int half = lines.size() / 2;
		for (int c : cnt) {
			if (c == half) {
				System.err.println("equal number of 0 and 1");
			}

			if (c > half) {
				gammaSb.append(1);
				epsilonSb.append(0);
			} else {
				gammaSb.append(0);
				epsilonSb.append(1);
			}
		}
		String gamma = gammaSb.toString();
		String epsilon = epsilonSb.toString();

		System.out.println("gamma:   " + gamma);
		System.out.println("epsilon: " + epsilon);

		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	private int lifeSupportRating(List<String> lines) {
		String oxygen = filter(lines, true);
		String co2 = filter(lines, false);

		System.out.println("\noxygen: " + oxygen);
		System.out.println("co2:    " + co2);

		return Integer.parseInt(oxygen, 2) * Integer.parseInt(co2, 2);
	}

	private String filter(List<String> lines, boolean greater) {
		List<String> l = new ArrayList<>();
		l.addAll(lines);

		int i = 0;
		while (l.size() > 1) {

			int ones = countOnes(l, i);
			int zeros = l.size() - ones;

			if (greater && ones >= zeros || !greater && ones < zeros) {
				filter(l, i, '1');
			} else {
				filter(l, i, '0');
			}

			i++;
		}
		return l.get(0);
	}

	private void filter(List<String> l, int idx, char c) {
		Iterator<String> it = l.iterator();
		while (it.hasNext()) {
			String value = it.next();
			if (value.charAt(idx) != c) {
				it.remove();
			}
		}
	}

	private int countOnes(List<String> lines, int idx) {
		int i = 0;
		for (String line : lines) {
			if (line.charAt(idx) == '1') {
				i++;
			}
		}
		return i;
	}

}
