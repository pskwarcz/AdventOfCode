package aoc.year2025;

import aoc.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class Day01Test {

    @Test
    void testPart1() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput01");

        int res = new Day01().process(lines);
        assertThat(res, is(3));
    }

    @Test
    void testPart2() {
        List<String> lines = Utils.readFile("/aoc/year2025/testInput01");

        int res = new Day01b().process(lines);
        assertThat(res, is(6));
    }

    @Test
    void testPart3() {
        List<String> lines = Utils.readFile("/aoc/year2025/input01");

        int res = new Day01b().process(lines);
        assertThat(res, not(is(2209)));
        assertThat(res, not(is(6364))); // too low
        assertThat(res, not(is(6536))); // too high
        assertThat(res, is(6475)); // too high

    }

    @ParameterizedTest
    @MethodSource("provideSpinArguments")
    void testCalculateZeros(int current, int spin, int expectedZeros) {
        assertThat(new Wheel(current).spin(spin).get(), is(expectedZeros));
    }

    private static Stream<Arguments> provideSpinArguments() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, 0),
                Arguments.of(0, 50, 0),
                Arguments.of(0, 99, 0),
                Arguments.of(0, 100, 1),
                Arguments.of(0, 101, 1),
                Arguments.of(0, 250, 2),

                Arguments.of(6, 10, 0),
                Arguments.of(50, 49, 0),
                Arguments.of(50, 50, 1),
                Arguments.of(50, 51, 1),
                Arguments.of(50, 149, 1),

                Arguments.of(99, -1, 0),
                Arguments.of(99, -98, 0),
                Arguments.of(99, -99, 1),
                Arguments.of(1, -1, 1),
                Arguments.of(1, -2, 1),
                Arguments.of(1, -100, 1),
                Arguments.of(1, -101, 2),
                Arguments.of(1, -102, 2),
                Arguments.of(1, -201, 3),
                Arguments.of(1, -301, 4),

                Arguments.of(0, -1, 0),
                Arguments.of(0, -2, 0),
                Arguments.of(0, -99, 0),
                Arguments.of(0, -100, 1),
                Arguments.of(0, -101, 1),
                Arguments.of(0, -102, 1),

                Arguments.of(0, -200, 2),
                Arguments.of(0, -250, 2),
                Arguments.of(0, -300, 3)
        );
    }


    @ParameterizedTest
    @MethodSource("provideTargetArguments")
    void testCalculateTargetPosition(int current, int spin, int expectedPos) {
        assertThat(new Wheel(current).spin(spin).current, is(expectedPos));
    }

    private static Stream<Arguments> provideTargetArguments() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(0, 1, 1),
                Arguments.of(0, 50, 50),
                Arguments.of(0, 99, 99),
                Arguments.of(0, 100, 0),
                Arguments.of(0, 101, 1),
                Arguments.of(0, 250, 50),

                Arguments.of(6, 10, 16),
                Arguments.of(50, 49, 99),
                Arguments.of(50, 50, 0),
                Arguments.of(50, 51, 1),
                Arguments.of(50, 149, 99),

                Arguments.of(99, -1, 98),
                Arguments.of(99, -98, 1),
                Arguments.of(99, -99, 0),
                Arguments.of(1, -1, 0),
                Arguments.of(1, -2, 99),
                Arguments.of(1, -100, 1),
                Arguments.of(1, -101, 0),
                Arguments.of(1, -102, 99),
                Arguments.of(1, -201, 0),
                Arguments.of(1, -301, 0),

                Arguments.of(0, -1, 99),
                Arguments.of(0, -2, 98),
                Arguments.of(0, -99, 1),
                Arguments.of(0, -100, 0),
                Arguments.of(0, -101, 99),
                Arguments.of(0, -102, 98),

                Arguments.of(0, -200, 0),
                Arguments.of(0, -250, 50),
                Arguments.of(0, -300, 0)
        );
    }

}

