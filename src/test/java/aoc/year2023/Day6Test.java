package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day6Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input6.txt");

		long res = new Day6().process(lines);
		assertThat(res, is(288L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input6.txt");

		long res = new Day6b().process(lines);
		assertThat(res, is(71503L));
	}

}
