package aoc.year2025;

import java.util.List;

public class Day06b extends Day06 {

    static void main() {
        long start = System.currentTimeMillis();
        new Day06b().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        IO.println("\nTime[ms]: " + duration);
    }

    @Override
    long process(List<String> lines) {
        boolean[] eol = new boolean[lines.size() - 1]; // if all true, program reached end of all lines
        int col = 0;
        long totalSum = 0;

        while (!isEndOfAllLines(eol)) {
            boolean isMultiplication = lines.getLast().charAt(col) == '*';
            long result = isMultiplication ? 1 : 0;

            String value;
            do {
                value = parseColumnValue(lines, col, eol);
                if (!value.isBlank()) {
                    long n = Long.parseLong(value);
                    if (isMultiplication) {
                        result *= n;
                    } else {
                        result += n;
                    }
                }

                col++;
            } while (!value.isBlank());

            totalSum += result;
        }

        return totalSum;

    }

    private String parseColumnValue(List<String> lines, int col, boolean[] eol) {
        StringBuilder b = new StringBuilder();
        for (int row = 0; row < lines.size() - 1; row++) {
            String line = lines.get(row);
            if (col >= line.length()) {
                eol[row] = true;
            } else {
                b.append(line.charAt(col));
            }
        }
        return b.toString().trim();
    }

    private boolean isEndOfAllLines(boolean[] eol) {
        for (boolean b : eol) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}
