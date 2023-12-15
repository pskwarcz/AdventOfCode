package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day7Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input7.txt");

		long res = new Day7().process(lines);
		assertThat(res, is(6440L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input7.txt");

		long res = new Day7b().process(lines);
		assertThat(res, is(5905L));
	}

}
