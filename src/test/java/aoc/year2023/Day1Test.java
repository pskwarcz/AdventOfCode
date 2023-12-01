package aoc.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import aoc.Utils;

public class Day1Test {

	Day1 sut = new Day1();

	@Test
	public void test() {
		List<String> lines = Utils.readFile("/aoc/year2023/input1");

		int res = sut.process(lines);
		assertEquals(res, 142);
	}

}
