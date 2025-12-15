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
        int rows = lines.size();
        int cols = lines.getFirst().length();

        int count = 0;
        boolean anyChange;

        do {
            anyChange = false;

            for (int row = 0; row < rows; row++) {
                char[] chars = lines.get(row).toCharArray();
                boolean changed = false;

                for (int col = 0; col < cols; col++) {
                    if (ROLL != chars[col]) continue;
                    if (!isRollAccessible(row, col, lines)) continue;

                    chars[col] = 'x';
                    count++;
                    changed = true;
                }

                if (changed) {
                    lines.set(row, new String(chars));
                    anyChange = true;
                }
            }
        } while (anyChange);
        return count;
    }


}
