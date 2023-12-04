package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day4Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long res = new Day4().process(lines);
		assertEquals(res, 13);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long res = new Day4().process(lines);
		assertEquals(res, 467835);
	}

}
