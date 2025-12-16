package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day08Test {

    private static final String TEST_INPUT = "/aoc/year2025/testInput08";
    private static final String INPUT = "/aoc/year2025/input08";

    @Test
    void testDataPart1() {
        List<String> lines = Utils.readFile(TEST_INPUT);

        long res = new Day08(10).process(lines);

        assertThat(res, is(40L));
    }

    @Test
    void testRealDataPart1() {
        List<String> lines = Utils.readFile(INPUT);

        long res = new Day08(1000).process(lines);

        assertThat(res, is(131580L));
    }

    @Test
    void testDataPart2() {
        List<String> lines = Utils.readFile(TEST_INPUT);

        long res = new Day08b().process(lines);

        assertThat(res, is(25272L));
    }

    @Test
    void testRealDataPart2() {
        List<String> lines = Utils.readFile(INPUT);

        long res = new Day08b().process(lines);

        assertThat(res, is(6844224L));
    }

}

