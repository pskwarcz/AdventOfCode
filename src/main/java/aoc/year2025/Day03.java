package aoc.year2025;

import aoc.Utils;

import java.util.List;

public class Day03 {

    void main() {
        long start = System.currentTimeMillis();
        new Day03().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input03");

        long result = process(lines);

        System.out.println("\nresult: " + result);
    }

    long process(List<String> lines) {
        return lines.stream().mapToLong(this::maxOutput).sum();
    }

    private long maxOutput(String s) {
        char[] bank = s.toCharArray();

        char a = 0;
        char b = 0;

        for (int i = 0; i < bank.length - 1; i++) {
            char left = bank[i];
            for (int j = i + 1; j < bank.length; j++) {
                char right = bank[j];
                if (left > a) {
                    a = left;
                    b = right;
                } else if (left == a) {
                    if (right > b) {
                        b = right;
                    }
                }
            }
        }

        return Integer.parseInt(a + "" + b);
    }


}
