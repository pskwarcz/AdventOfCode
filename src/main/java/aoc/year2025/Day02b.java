package aoc.year2025;

import aoc.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day02b {

    void main() {
        long start = System.currentTimeMillis();
        new Day02b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    private void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input02");

        long result = process(lines);

        System.out.println("\nresult: " + result);
    }

    long process(List<String> lines) {
        return lines.stream()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .flatMapToLong(this::getInvalidIDs)
                .sum();
    }

    private LongStream getInvalidIDs(String p) {

        String[] range = p.split("-");
        long start = Long.parseLong(range[0]);
        long end = Long.parseLong(range[1]);

        LongStream.Builder b = LongStream.builder();

        for (long l = start; l <= end; l++) {
            if (isInvalidId(l)) {
                b.add(l);
            }
        }

        return b.build();
    }

    private boolean isInvalidId(long l) {
        String s = Long.toString(l);

        for (int i = 1; i <= s.length() / 2; i++) {
            if (isInvalidId(s, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean isInvalidId(String s, int sequenceLength) {
        if (s.length() % sequenceLength != 0) {
            return false;
        }

        String left = s.substring(0, sequenceLength);

        for (int n = sequenceLength, end = 2 * sequenceLength; end <= s.length(); n += sequenceLength, end += sequenceLength) {
            if (!left.equals(s.substring(n, end))) {
                return false;
            }
        }
        // IO.println("left: " + left + " right: " + right);
        return true;
    }


}
