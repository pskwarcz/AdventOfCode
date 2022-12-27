package aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.year2022.Day19;

public class Day19Test {

	Day19 sut = new Day19();

	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input19t");
		assertEquals(33, sut.start(lines));
	}

}
