package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day9Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");

		long res = new Day9().process(lines);
		assertThat(res, is(114L));
	}

	@Test
	public void part2() throws InterruptedException {
		List<String> lines = Utils.readFile("/aoc/year2023/input9.txt");

		long res = new Day9b().process(lines);
		assertThat(res, is(2L));
	}

}
