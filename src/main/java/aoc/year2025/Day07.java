package aoc.year2025;

import aoc.Utils;

import java.util.*;

public class Day07 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day07().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input07");
        long result = process(lines);
        IO.println("\nresult: " + result);
    }

    Queue<Beam> in = new LinkedList<>();
    Set<Beam> out = new HashSet<>();

    long process(List<String> lines) {
        Beam start = null;

        long splits = 0;

        for (String line : lines) {
            if (start == null) {
                start = new Beam(lines.getFirst().indexOf('S'));
                in.offer(start);
            }

            while (in.peek() != null) {
                Beam b = in.poll();
                if (line.charAt(b.x) == '^') {
                    out.add(new Beam(b.x - 1));
                    out.add(new Beam(b.x + 1));
                    splits++;
                } else {
                    out.add(b);
                }
            }
            in.addAll(out);
            out.clear();
        }

        return splits;
    }


    record Beam(int x) {
    }

}
