package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day2Test {

	Day2 sut = new Day2();

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input2");

		int res = sut.process(lines);
		assertEquals(res, 8);
	}

}
