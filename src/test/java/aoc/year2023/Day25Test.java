package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;
import aoc.year2023.day25.Graph;

public class Day25Test {

	@Test
	public void cut3andCountTest() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25t.txt");
		Graph g = new Graph(lines);
		long res = g.cut3andCount();
		assertThat(res, is(54L));
	}

	@Test
	public void part1() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25t.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(54L));
	}

	@Test
	public void part1real() {
		List<String> lines = Utils.readFile("/aoc/year2023/input25.txt");
		long res = new Day25().process(lines);
		assertThat(res, is(592171L));
	}

}
