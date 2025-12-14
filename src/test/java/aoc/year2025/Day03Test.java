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

        long res = new Day03().process(lines);

        assertThat(res, is(357L));
    }

    @Test
    void testPart1() {
        List<String> lines = Utils.readFile("/aoc/year2025/input03");

        long res = new Day03().process(lines);

        assertThat(res, is(17343L));
    }

}

