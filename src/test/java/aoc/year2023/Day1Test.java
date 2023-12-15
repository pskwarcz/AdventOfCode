package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day1Test {

	Day1 sut = new Day1();

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1");

		int res = sut.process(lines);
		assertThat(142, is(res));
	}

}
