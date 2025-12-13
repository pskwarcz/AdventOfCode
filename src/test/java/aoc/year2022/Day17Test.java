package aoc.year2022;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day17Test {

	Day17 sut = new Day17();

	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17t");
		Day17.n = 2022;

		long heuight = sut.start(lines);
		assertThat(heuight, is(3068L));
	}

	@Test
	public void testPartOneRealData() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17");
		Day17.n = 2022;

		long heuight = sut.start(lines);
		assertThat(heuight, is(3219L));
	}

	@Test
	public void testPartTwo() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17t");
		Day17.n = 1000000000000L;

		long heuight = sut.start(lines);
		assertThat(heuight, is(1514285714288L));
	}

	@Test
	public void testPartTwoRealData() {
		List<String> lines = Utils.readFile("/aoc/year2022/input17");
		Day17.n = 1000000000000L;

		long heuight = sut.start(lines);
		assertThat(heuight, is(1582758620701L));
	}

}
