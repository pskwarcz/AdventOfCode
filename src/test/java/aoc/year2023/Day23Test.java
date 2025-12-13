package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import aoc.Utils;

@Ignore
public class Day23Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23t.txt");
		long res = new Day23().process(lines);
		assertThat(res, is(94L));
	}

	@Test
	public void part1real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23.txt");
		long res = new Day23().process(lines);
		assertThat(res, is(2310L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23t.txt");
		long res = new Day23b().process(lines);
		assertThat(res, is(154L));
	}

	@Test
	public void part2real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input23.txt");
		long res = new Day23b().process(lines);
		assertThat(res, is(6738L));
	}

}
