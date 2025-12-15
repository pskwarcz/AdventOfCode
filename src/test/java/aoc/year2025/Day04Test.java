package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day04Test {

    @Test
    void testDataPart1() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput04");

        long res = new Day04().process(lines);

        assertThat(res, is(13L));
    }

    @Test
    void testPart1Real() {
        List<String> lines = Utils.readFile("/aoc/year2025/input04");

        long res = new Day04().process(lines);

        assertThat(res, is(1523L));
    }

    @Test
    void testDataPart2() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput04");

        long res = new Day04b().process(lines);

        assertThat(res, is(43L));
    }

    @Test
    void testDataPart2Real() {
        List<String> lines = Utils.readFile("/aoc/year2025/input04");

        long res = new Day04b().process(lines);

        assertThat(res, is(9290L));
    }


}

