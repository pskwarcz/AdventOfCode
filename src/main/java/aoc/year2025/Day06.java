package aoc.year2025;

import aoc.Utils;

import java.util.List;
import java.util.stream.IntStream;

public class Day06 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day06().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input06");
        long result = process(lines);
        IO.println("\nresult: " + result);
    }

    String DELIMITER = "\\s+";

    long process(List<String> lines) {

        List<String[]> homework = lines.stream().map(row -> row.trim().split(DELIMITER)).toList();

        return IntStream.range(0, homework.getFirst().length).mapToLong(i -> operate(homework, i)).sum();

    }

    long operate(List<String[]> homework, int i) {
        boolean isMultiplication = homework.getLast()[i].equals("*");

        long result = isMultiplication ? 1 : 0;

        for (int row = 0; row < homework.size() - 1; row++) {
            long n = Long.parseLong(homework.get(row)[i]);
            if (isMultiplication) {
                result *= n;
            } else {
                result += n;
            }
        }
        return result;
    }

}
