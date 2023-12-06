package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day6Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input6.txt");

		long res = new Day6().process(lines);
		assertEquals(res, 288);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input6.txt");

		long res = new Day6().process(lines);
		assertEquals(46, res);
	}

}
