package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day05Test {

    private static final String TEST_INPUT = "/aoc/year2025/testInput05";
    private static final String INPUT = "/aoc/year2025/input05";

    @Test
    void testDataPart1() {
        List<String> lines = Utils.readFile(TEST_INPUT);

        long res = new Day05().process(lines);

        assertThat(res, is(3L));
    }

    @Test
    void testRealDataPart1() {
        List<String> lines = Utils.readFile(INPUT);

        long res = new Day05().process(lines);

        assertThat(res, is(701L));
    }

}

