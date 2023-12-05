package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day5Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input5");

		long res = new Day5().process(lines);
		assertEquals(res, 35);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input5");

		long res = new Day5b().process(lines);
		assertEquals(46, res);
	}

}
