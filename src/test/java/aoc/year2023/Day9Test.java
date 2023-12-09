package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day9Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");

		long res = new Day9().process(lines);
		assertEquals(114, res);
	}

	@Test
	public void part2() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");

		long res = new Day9().process(lines);
		assertEquals(6, res);
	}

}
