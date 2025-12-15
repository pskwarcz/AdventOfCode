package aoc.year2025;

import aoc.Utils;

import java.util.List;

public class Day04 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day04().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input04");
        long result = process(lines);
        System.out.println("\nresult: " + result);
    }

    static final char ROLL = '@';

    long process(List<String> lines) {
        int count = 0;
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (ROLL == line.charAt(col) && isRollAccessible(row, col, lines)) {
                    count++;
                }
            }
        }
        return count;
    }

    record Position(int row, int col) {
    }

    boolean isRollAccessible(int row, int col, List<String> lines) {
        List<Position> positionsToCheck = List.of(
                new Position(row - 1, col - 1),
                new Position(row - 1, col),
                new Position(row - 1, col + 1),
                new Position(row, col - 1),
                new Position(row, col + 1),
                new Position(row + 1, col - 1),
                new Position(row + 1, col),
                new Position(row + 1, col + 1));

        return positionsToCheck.stream().filter(p -> isEmptySpace(p, lines)).limit(5).count() == 5;
    }

    boolean isEmptySpace(Position p, List<String> lines) {
        if (p.row < 0 || p.row >= lines.size()) {
            return true;
        }
        String row = lines.get(p.row);
        if (p.col < 0 || p.col >= row.length()) {
            return true;
        }
        return ROLL != row.charAt(p.col);
    }


}
