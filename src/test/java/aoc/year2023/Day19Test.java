package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day19Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19.txt");
		long res = new Day19().process(lines);
		assertThat(res, is(19114L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19.txt");
		long res = new Day19b().process(lines);
		assertThat(res, is(167409079868000L));
	}

}