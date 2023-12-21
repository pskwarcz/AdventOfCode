package aoc.year2023;

import java.util.List;

import aoc.Utils;
import aoc.year2023.day20.Machine;
import aoc.year2023.day20.Module;

public class Day20 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Day20().start();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("\nTime[ms]: " + duration);
	}

	private void start() {
		List<String> lines = Utils.readFile("/aoc/year2023/input20.txt");
		long result = process(lines);
		System.out.println("\nresult: " + result);
	}

	static int i;

	long process(List<String> lines) {

		Machine m = new Machine(lines);

		long lowSum = 0;
		long highSum = 0;
		for (i = 0; i < 1000; i++) {
			// System.out.println();
			System.out.println(i);
			m.pressButton();
			// System.out.println(
			// i + " low sum=" + (Module.lowCounter - lowSum) + " high sum=" +
			// (Module.highCounter - highSum));
			lowSum = Module.lowCounter;
			highSum = Module.highCounter;
		}

		System.out.println("\nlow sum=" + lowSum + " high sum=" + highSum);

		return lowSum * highSum;
	}

}
