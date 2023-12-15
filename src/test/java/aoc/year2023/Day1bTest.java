package aoc.year2023;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day1bTest {

	Day1b sut = new Day1b();

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1b");

		int res = sut.process(lines);
		assertThat(281, is(res));
	}

}
