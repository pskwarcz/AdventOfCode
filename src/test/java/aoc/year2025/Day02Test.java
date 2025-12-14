package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day02Test {

    @Test
    void testPart1() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput02");

        long res = new Day02().process(lines);

        assertThat(res, is(1227775554L));
    }

    @Test
    void testPart2() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput02");

        long res = new Day02b().process(lines);

        assertThat(res, is(4174379265L));
    }

}

