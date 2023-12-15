package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day10Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input10.txt");

		long res = new Day10().process(lines);
		assertThat(res, is(8L));
	}

	@Test
	public void part2() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input10b.txt");

		long res = new Day10b('F').process(lines);
		assertThat(res, is(4L));
	}

	@Test
	public void part2c() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input10c.txt");

		long res = new Day10b('7').process(lines);
		assertThat(res, is(10L));
	}
}
