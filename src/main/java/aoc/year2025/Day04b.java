package aoc.year2025;

import java.util.List;

public class Day04b extends Day04 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day04b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    long process(List<String> lines) {
        int count = 0;
        int linesCount = lines.size();
        int colsCount = lines.getFirst().length();

        boolean removed;
        do {
            removed = false;
            for (int row = 0; row < linesCount; row++) {
                for (int col = 0; col < colsCount; col++) {
                    if (ROLL == lines.get(row).charAt(col) && isRollAccessible(row, col, lines)) {
                        StringBuilder sb = new StringBuilder(lines.get(row));
                        sb.setCharAt(col, 'x');
                        lines.set(row, sb.toString());
                        removed = true;
                        count++;
                    }
                }
            }
        } while (removed);
        return count;
    }


}
