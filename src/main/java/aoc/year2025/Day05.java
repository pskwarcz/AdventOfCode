package aoc.year2025;

import aoc.Utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Day05 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day05().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input05");
        long result = process(lines);
        System.out.println("\nresult: " + result);
    }

    long process(List<String> lines) {
        Iterator<String> it = lines.iterator();
        List<Range> ranges = new LinkedList<>();
        long count = 0;

        String line = it.next();
        while (!line.isBlank()) {
            ranges.add(new Range(line));
            line = it.next();
        }

        while (it.hasNext()) {
            long value = Long.parseLong(it.next());
            boolean isFresh = ranges.stream().anyMatch(r -> r.contains(value));
            if (isFresh) {
                count++;
            }
        }

        return count;
    }

    record Range(long start, long end) {
        public Range {
            if (start > end) {
                throw new IllegalArgumentException("Invalid range:" + start + ", " + end);
            }
        }

        public Range(String line) {
            String[] l = line.split("-");
            this(Long.parseLong(l[0]), Long.parseLong(l[1]));
        }

        boolean contains(long value) {
            return value >= start && value <= end;
        }
    }

}
