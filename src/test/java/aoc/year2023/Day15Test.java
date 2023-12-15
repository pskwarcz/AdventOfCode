package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day15Test {

	@Test
	public void hashTest() {
		assertThat(new Day15().hash("HASH"), is(52));
	}

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input15.txt");
		long res = new Day15().process(lines);
		assertThat(res, is(1320L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input15.txt");
		long res = new Day15b().process(lines);
		assertThat(res, is(145L));
	}

}
