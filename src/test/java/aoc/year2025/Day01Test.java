package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day01Test {

    Day01 sut = new Day01();

    @Test
    void test() {
        List<String> lines = Utils.readFile("/aoc/year2025/input01");

        int res = sut.process(lines);
        assertThat(res, is(3));
    }

}
