package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;
import aoc.year2023.day24.Hailstone;

public class Day25Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25t.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(54L));
	}

	@Test
	public void part1real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(20847L));
	}

	@Test
	public void part2() {
		Hailstone.min = 7L;
		Hailstone.max = 27L;
		List<String> lines = Utils.readFile("/aoc/year2023/input25t.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(47L));
	}

	@Test
	public void part2real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(6738L));
	}

}
