package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day8Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input8.txt");

		long res = new Day8().process(lines);
		assertThat(res, is(6L));
	}

	@Test
	public void part2() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input8b.txt");

		long res = new Day8b().process(lines);
		assertThat(res, is(6L));
	}

}
