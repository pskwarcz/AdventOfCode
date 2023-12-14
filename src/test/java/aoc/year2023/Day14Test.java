package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day14Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input14.txt");
		long res = new Day14().process(lines);
		assertEquals(136, res);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input14.txt");
		long res = new Day14b().process(lines);
		assertEquals(64, res);
	}

}
