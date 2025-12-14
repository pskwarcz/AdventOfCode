package aoc.year2025;

import aoc.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Day02 {

    void main() {
        long start = System.currentTimeMillis();
        new Day02().start();
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
        IO.println(start + " ----- " + end);


        LongStream.Builder b = LongStream.builder();

        for (long l = start; l <= end; l++) {
            if (isInvalidId(l)) {
                IO.println(l + " is INVALID ID!");
                b.add(l);
            }
        }

        return b.build();
    }

    private boolean isInvalidId(long l) {
        String s = Long.toString(l);
        if (s.length() % 2 != 0) {
            return false;
        }

        String left = s.substring(0, s.length() / 2);
        String right = s.substring(s.length() / 2);

        // IO.println("left: " + left + " right: " + right);

        return left.equals(right);
    }


}
