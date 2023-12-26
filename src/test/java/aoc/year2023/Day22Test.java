package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day22Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input22t.txt");
		long res = new Day22().process(lines);
		assertThat(res, is(5L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input22t.txt");
		long res = new Day22().process(lines);
		assertThat(res, is(167409079868000L));
	}

}
