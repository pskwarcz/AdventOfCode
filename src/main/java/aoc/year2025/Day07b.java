package aoc.year2025;

import aoc.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07b {

    static void main() {
        long start = System.currentTimeMillis();
        new Day07b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    Map<Pos, Long> cache = new HashMap<>();

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input07");
        long result = process(lines);
        IO.println("\nresult: " + result);
    }

    long process(List<String> lines) {
        int x = lines.getFirst().indexOf('S');
        Pos start = new Pos(x, 1);
        return getPossiblePaths(start, lines);
    }

    private long getPossiblePaths(Pos p, List<String> lines) {
        if (p.depth == lines.size() - 1) {
            return 1;
        }

        if (cache.containsKey(p)) {
            return cache.get(p);
        }
        long v = calculate(p, lines);
        cache.put(p, v);
        return v;

    }

    private long calculate(Pos p, List<String> lines) {
        if (lines.get(p.depth).charAt(p.x) == '^') {
            long left = getPossiblePaths(new Pos(p.x - 1, p.depth + 1), lines);
            long right = getPossiblePaths(new Pos(p.x + 1, p.depth + 1), lines);
            return left + right;
        } else {
            return getPossiblePaths(new Pos(p.x, p.depth + 1), lines);
        }
    }

    record Pos(int x, int depth) {
    }

}
