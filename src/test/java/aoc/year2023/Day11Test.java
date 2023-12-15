package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day11Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(2).process(lines);
		assertThat(res, is(374L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(10).process(lines);
		assertThat(res, is(1030L));
	}

	@Test
	public void part2b() {
		List<String> lines = Utils.readFile("/aoc/year2023/input11.txt");

		long res = new Day11(100).process(lines);
		assertThat(res, is(8410L));
	}

}
