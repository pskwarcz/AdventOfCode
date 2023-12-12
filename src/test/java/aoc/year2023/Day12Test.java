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
	public void part2solutions2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		Day12b d = new Day12b();

		assertEquals(1, d.countArrangements(lines.get(0)));
		assertEquals(16384, d.countArrangements(lines.get(1)));
		assertEquals(1, d.countArrangements(lines.get(2)));
		assertEquals(16, d.countArrangements(lines.get(3)));
		assertEquals(2500, d.countArrangements(lines.get(4)));
		assertEquals(506250, d.countArrangements(lines.get(5)));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long res = new Day12b().process(lines);
		assertEquals(525152, res);
	}

}
