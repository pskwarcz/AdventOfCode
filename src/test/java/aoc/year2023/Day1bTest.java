package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day1bTest {

	Day1b sut = new Day1b();

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1b");

		int res = sut.process(lines);
		assertEquals(res, 281);
	}

}
