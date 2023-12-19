package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day18Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input18.txt");
		long res = new Day18().process(lines);
		assertThat(res, is(62L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input18.txt");
		long res = new Day18b().process(lines);
		assertThat(res, is(952408144115L));
	}

}
