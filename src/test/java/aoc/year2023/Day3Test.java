package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day3Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input3");

		long res = new Day3().process(lines);
		assertEquals(res, 4361);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		long res = new Day2b().process(lines);
		assertEquals(res, 2286);
	}

}
