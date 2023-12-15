package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day15Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input15.txt");
		long res = new Day15().process(lines);
		assertEquals(1320, res);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input15.txt");
		long res = new Day15().process(lines);
		assertEquals(64, res);
	}

	@Test
	public void hashTest() {
		assertEquals(52, new Day15().hash("HASH"));
	}

}
