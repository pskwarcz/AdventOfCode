package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day12Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long res = new Day12().process(lines);
		assertThat(res, is(21L));
	}

	@Test
	public void countSolutions() {
		Day12 d = new Day12();
		String line = "???.???????#??#??. 9";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertThat(res, is(3L));
	}

	@Test
	public void countSolutions2() {
		Day12 d = new Day12();
		String line = ".????...#..?????.. 1,2,1";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertThat(res, is(4L));
	}

	@Test
	public void countSolutions3() {
		Day12 d = new Day12();
		String line = "??#??.?##. 1,3";
		char[] pattern = d.readPattern(line);
		int[] info = d.readInfo(line);

		long res = new Day12().countSolutions(pattern, info, 0, 0);
		assertThat(res, is(1L));
	}

	@Test
	public void part2solutions2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		Day12b d = new Day12b();

		assertThat(d.countArrangements(lines.get(0)), is(1L));
		assertThat(d.countArrangements(lines.get(1)), is(16384L));
		assertThat(d.countArrangements(lines.get(2)), is(1L));
		assertThat(d.countArrangements(lines.get(3)), is(16L));
		assertThat(d.countArrangements(lines.get(4)), is(2500L));
		assertThat(d.countArrangements(lines.get(5)), is(506250L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input12.txt");

		long res = new Day12b().process(lines);
		assertThat(res, is(525152L));
	}

	@Test
	public void part2solutions2long() {
		Day12b d = new Day12b();
		assertThat(d.countArrangements("???#???????????# 5,1,1,2,1"), is(2189421L));
	}

}
