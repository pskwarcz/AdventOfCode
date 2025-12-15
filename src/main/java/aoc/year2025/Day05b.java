package aoc.year2025;

import aoc.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Day05b {

    static void main() {
        long start = System.currentTimeMillis();
        new Day05b().start();
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
        List<Range> ranges = new ArrayList<>();

        String line = it.next();
        while (!line.isBlank()) {
            ranges.add(new Range(line));
            line = it.next();
        }

        List<Range> merged = new LinkedList<>();
        for (Range r : ranges) {

            Iterator<Range> mit = merged.iterator();
            while (mit.hasNext()) {
                Range m = mit.next();
                if (r.overlaps(m)) {
                    r = m.mergeWith(r);

                    mit.remove();
                }
            }
            merged.add(r);
            IO.println(merged);
        }

        return merged.stream().mapToLong(Range::size).sum();
    }

    record Range(long start, long end) {
        public Range {
            if (start > end) {
                throw new IllegalArgumentException("Invalid range:" + start + ", " + end);
            }
        }

        @Override
        public String toString() {
            return "(" + start + " - " + end + ')';
        }

        public Range(String line) {
            String[] l = line.split("-");
            this(Long.parseLong(l[0]), Long.parseLong(l[1]));
        }

        boolean contains(long value) {
            return value >= start && value <= end;
        }

        public long size() {
            return end - start + 1;
        }

        public Range mergeWith(Range r) {
            Range merged = new Range(Math.min(r.start, this.start), Math.max(r.end, this.end));
            IO.println("\t Merging: " + this.toString() + " + " + r + " = " + merged);
            return merged;
        }

        public boolean overlaps(Range m) {
            return this.start() <= m.end() && m.start() <= this.end();
        }
    }

}
