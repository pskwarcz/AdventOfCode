package aoc.year2025;

import aoc.Utils;

import java.util.List;

public class Day07b {

    static void main() {
        long start = System.currentTimeMillis();
        new Day07b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input07");
        long result = process(lines);
        IO.println("\nresult: " + result);
    }

    long process(List<String> lines) {
        int x = lines.getFirst().indexOf('S');

        return getPossiblePaths(x, 1, lines);
    }

    private long getPossiblePaths(int x, int depth, List<String> lines) {
        if (depth == lines.size() - 1) {
            return 1;
        }

        if (lines.get(depth).charAt(x) == '^') {
            long left = getPossiblePaths(x - 1, depth + 1, lines);
            long right = getPossiblePaths(x + 1, depth + 1, lines);
            return left + right;
        } else {
            return getPossiblePaths(x, depth + 1, lines);
        }
    }


}
