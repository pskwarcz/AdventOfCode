package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day2Test {

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		int res = new Day2().process(lines);
		assertThat(8, is(res));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		long res = new Day2b().process(lines);
		assertThat(res, is(2286L));
	}

}
