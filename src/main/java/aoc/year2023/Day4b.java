package aoc.year2023;

import java.util.Arrays;
import java.util.List;

import aoc.Utils;

public class Day4b {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day4b().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long result = process(lines);

		System.out.println("\nresult: " + result);
	}

	long process(List<String> lines) {
		long sum = 0;

		int[] cnt = new int[lines.size()];
		for (int i = 0; i < cnt.length; i++) {
			cnt[i] = 1;
		}
		System.out.println(Arrays.toString(cnt));
		int idx = 0;
		for (String line : lines) {
			System.out.println("\n Card: " + (idx + 1));
			System.out.println("card numbers:" + cnt[idx]);
			int pts = (int) cardPoint(line);
			for (int i = idx + 1; i < idx + 1 + pts && i < cnt.length; i++) {
				cnt[i] += cnt[idx];
			}
			System.out.println(Arrays.toString(cnt));
			idx++;
		}

		for (int a : cnt) {
			sum += a;
		}

		return sum;
	}

	private long cardPoint(String line) {
		String cards = line.split(":")[1];
		String winning = cards.split("\\|")[0];
		String numers = cards.split("\\|")[1];

		List<String> w = Arrays.asList(winning.trim().split("\\s+"));
		List<String> n = Arrays.asList(numers.trim().split("\\s+"));

		long c = n.stream().filter(w::contains).count();
		System.out.println("matching:" + c);
		return c;
	}

}
