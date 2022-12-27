package aoc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import aoc.year2022.Day18;

public class Day18Test {

	Day18 sut = new Day18();

	@Test
	public void testPartOne() {
		List<String> lines = Utils.readFile("/aoc/year2022/input18t");
		assertEquals(58, sut.start(lines));
	}

}
