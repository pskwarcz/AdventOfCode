package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;
import aoc.year2023.day24.Hailstone;

public class Day24Test {

	@Test
	public void part1() {
		Hailstone.min = 7L;
		Hailstone.max = 27L;
		List<String> lines = Utils.readFile("/aoc/year2023/input24t.txt");
		long res = new Day24().process(lines);
		assertThat(res, is(2L));
	}

	@Test
	public void part1real() {
		Hailstone.min = 200000000000000L;
		Hailstone.max = 400000000000000L;
		List<String> lines = Utils.readFile("/aoc/year2023/input24.txt");
		long res = new Day24().process(lines);
		assertThat(res, is(20847L));
	}

	@Test
	public void part2() {
		Hailstone.min = 7L;
		Hailstone.max = 27L;
		List<String> lines = Utils.readFile("/aoc/year2023/input24t.txt");
		long res = new Day24().process(lines);
		assertThat(res, is(154L));
	}

	@Test
	public void part2real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input24.txt");
		long res = new Day24().process(lines);
		assertThat(res, is(6738L));
	}

}
