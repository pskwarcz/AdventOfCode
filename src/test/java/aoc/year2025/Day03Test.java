package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day03Test {

    @Test
    void testDataPart1() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput03");

        long res = new Day03(2).process(lines);

        assertThat(res, is(357L));
    }

    @Test
    void testPart1Real() {
        List<String> lines = Utils.readFile("/aoc/year2025/input03");

        long res = new Day03(2).process(lines);

        assertThat(res, is(17343L));
    }

    @Test
    void testDataPart2() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput03");

        long res = new Day03(12).process(lines);

        assertThat(res, is(3121910778619L));
    }

    @Test
    void testDataPart2Real() {
        List<String> lines = Utils.readFile("/aoc/year2025/input03");

        long res = new Day03(12).process(lines);

        assertThat(res, is(172664333119298L));
    }


}

