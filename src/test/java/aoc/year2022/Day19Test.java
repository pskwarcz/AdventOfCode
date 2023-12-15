package aoc.year2022;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import aoc.Utils;

public class Day19Test {

	Day19 sut = new Day19();

	@Ignore
	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input19t");
		assertThat(sut.start(lines), is(33));
	}

}
