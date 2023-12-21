package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day20Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input20.txt");
		long res = new Day20().process(lines);
		assertThat(res, is(32000000L));
	}

	@Test
	public void part1b() {
		List<String> lines = Utils.readFile("/aoc/year2023/input20b.txt");
		long res = new Day20().process(lines);
		assertThat(res, is(11687500L));
	}

	@Test
	public void part1c() {
		List<String> lines = Utils.readFile("/aoc/year2023/input20c.txt");
		long res = new Day20().process(lines);
		assertThat(res, is(11687500L));
	}

}
