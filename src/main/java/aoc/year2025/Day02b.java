package aoc.year2025;

import java.util.stream.IntStream;

public class Day02b extends Day02 {

    @Override
    void main() {
        long start = System.currentTimeMillis();
        new Day02b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    @Override
    boolean isInvalidId(long l) {
        String s = Long.toString(l);

        return IntStream.rangeClosed(1, s.length() / 2).anyMatch(i -> isInvalidId(s, i));
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
        return true;
    }


}
