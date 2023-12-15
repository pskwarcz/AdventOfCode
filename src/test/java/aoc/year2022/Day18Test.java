package aoc.year2022;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day18Test {

	Day18 sut = new Day18();

	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input18t");
		assertThat(sut.start(lines), is(58));
	}

}
