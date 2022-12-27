package aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.year2022.Day17;

public class Day17Test {

	Day17 sut = new Day17();

	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17t");
		Day17.n = 2022;

		long heuight = sut.start(lines);
		assertEquals(3068L, heuight);
	}

	@Test
	public void testPartOneRealData() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17");
		Day17.n = 2022;

		long heuight = sut.start(lines);
		assertEquals(3219L, heuight);
	}

	@Test
	public void testPartTwo() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17t");
		Day17.n = 1000000000000L;

		long heuight = sut.start(lines);
		assertEquals(1514285714288L, heuight);
	}

	@Test
	public void testPartTwoRealData() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17");
		Day17.n = 1000000000000L;

		long heuight = sut.start(lines);
		assertEquals(1582758620701L, heuight);
	}

}
