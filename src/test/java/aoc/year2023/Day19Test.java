package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day19Test {

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19t.txt");
		long res = new Day19().process(lines);
		assertThat(res, is(19114L));
	}

	@Test
	public void part1real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19.txt");
		long res = new Day19().process(lines);
		assertThat(res, is(406849L));
	}

	@Test
	public void part2() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19t.txt");
		long res = new Day19b().process(lines);
		assertThat(res, is(167409079868000L));
	}

	@Test
	public void part2real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input19.txt");
		long res = new Day19b().process(lines);
		assertThat(res, not(187456592788932L));
		assertThat(res, not(75743050454221L));
		assertThat(res, not(138672802425232L));
		assertThat(res, not(138112728734123L));

		assertThat(res, is(-1L));
	}

}
