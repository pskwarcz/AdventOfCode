package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day10Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input10.txt");

		long res = new Day10().process(lines);
		assertEquals(8, res);
	}

	@Test
	public void part2() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input10.txt");

		long res = new Day10().process(lines);
		assertEquals(-1, res);
	}

}
