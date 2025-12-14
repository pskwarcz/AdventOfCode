package aoc.year2025;

import aoc.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03b {

    void main() {
        long start = System.currentTimeMillis();
        new Day03b().start();
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

    static final int N = 12;

    private long maxOutput(String s) {
        char[] bank = s.toCharArray();

        int[] resultIdx = new int[N];

        for (int i = 0; i < N; i++) {
            resultIdx[i] = findFirstBiggest(i == 0 ? 0 : resultIdx[i - 1] + 1, s.length() - N + i + 1, bank);
        }
        String value = Arrays.stream(resultIdx).mapToObj(i -> bank[i]).map(String::valueOf).collect(Collectors.joining());

        return Long.parseLong(value);
    }

    private int findFirstBiggest(int fromIdx, int toIdx, char[] bank) {
        int x = fromIdx;
        for (int i = fromIdx; i < toIdx; i++) {
            if (bank[i] > bank[x]) {
                x = i;
            }
        }
        return x;
    }


}
