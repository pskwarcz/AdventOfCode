package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day3Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input3");

		long res = new Day3().process(lines);
		assertThat(res, is(4361L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input3");

		long res = new Day3b().process(lines);
		assertThat(res, is(467835L));
	}

}
