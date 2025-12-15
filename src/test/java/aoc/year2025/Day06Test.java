package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day06Test {

    private static final String TEST_INPUT = "/aoc/year2025/testInput06";
    private static final String INPUT = "/aoc/year2025/input06";

    @Test
    void testDataPart1() {
        List<String> lines = Utils.readFile(TEST_INPUT);

        long res = new Day06().process(lines);

        assertThat(res, is(4277556L));
    }

    @Test
    void testRealDataPart1() {
        List<String> lines = Utils.readFile(INPUT);

        long res = new Day06().process(lines);

        assertThat(res, is(6635273135233L));
    }

    @Test
    void testDataPart2() {
        List<String> lines = Utils.readFile(TEST_INPUT);

        long res = new Day06b().process(lines);

        assertThat(res, is(3263827L));
    }

    @Test
    void testRealDataPart2() {
        List<String> lines = Utils.readFile(INPUT);

        long res = new Day06b().process(lines);

        assertThat(res, not(is(6635273135233L))); // too low
        assertThat(res, is(12542543681221L));
    }

}

