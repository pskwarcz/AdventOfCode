package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;
import aoc.year2023.day16.Day16;

public class Day16Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input16.txt");
		long res = new Day16().process(lines);
		assertThat(res, is(46L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input16.txt");
		long res = new Day16().process(lines);
		assertThat(res, is(0L));
	}

}
