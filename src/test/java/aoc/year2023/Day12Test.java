package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day12Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long res = new Day12().process(lines);
		assertEquals(21, res);
	}

	@Test
	public void countSolutions() {
		Day12 d = new Day12();
		String line = "???.???????#??#??. 9";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertEquals(3, res);
	}

	@Test
	public void countSolutions2() {
		Day12 d = new Day12();
		String line = ".????...#..?????.. 1,2,1";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertEquals(4, res);
	}

	@Test
	public void countSolutions3() {
		Day12 d = new Day12();
		String line = "??#??.?##. 1,3";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertEquals(1, res);
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long res = new Day12().process(lines);
		assertEquals(21, res);
	}

}
