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

	long process(List<String> lines) {

		Machine m = new Machine(lines);

		for (int i = 0; i < 1000; i++) {
			System.out.println(i);
			m.pressButton();
		}

		long lowSum = Module.lowCounter;
		long highSum = Module.highCounter;
		System.out.println("low sum=" + lowSum + " high sum=" + highSum);

		return lowSum * highSum;
	}

}
