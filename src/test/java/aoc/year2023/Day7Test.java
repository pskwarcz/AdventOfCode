package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day7Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input7.txt");

		long res = new Day7().process(lines);
		assertEquals(6440, res);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input7.txt");

		long res = new Day7b().process(lines);
		assertEquals(5905, res);
	}

}
