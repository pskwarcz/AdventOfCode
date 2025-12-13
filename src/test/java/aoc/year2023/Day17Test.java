package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day17Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input17.txt");
		long res = new Day17().process(lines);
		assertThat(res, is(102L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input17.txt");
		long res = new Day17b().process(lines);
		assertThat(res, is(94L));
	}

	@Test
	public void part2example2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input17b.txt");
		long res = new Day17b().process(lines);
		assertThat(res, is(71L));
	}
}
