package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day14Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input14.txt");
		long res = new Day14().process(lines);
		assertThat(res, is(136L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input14.txt");
		long res = new Day14b().process(lines);
		assertThat(res, is(64L));
	}

}
