package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day4Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long res = new Day4().process(lines);
		assertThat(res, is(13L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input4");

		long res = new Day4b().process(lines);
		assertThat(res, is(30L));
	}

}
