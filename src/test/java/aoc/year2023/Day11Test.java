package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day11Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(2).process(lines);
		assertEquals(374, res);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(10).process(lines);
		assertEquals(1030, res);
	}

	@Test
	public void part2b() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(100).process(lines);
		assertEquals(8410, res);
	}

}
