package aoc.year2025;

import aoc.Utils;

import java.util.List;

public class Day01 {

    void main() {
        long start = System.currentTimeMillis();
        new Day01().start();
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("\nTime[ms]: " + duration);
    }

    private void start() {
        List<String> lines = Utils.readFile("/aoc/year2025/input01");

        int result = process(lines);

        System.out.println("\nresult: " + result);
    }

    static final int INITIAL = 50;
    static final int MAX = 99;
    int current = INITIAL;
    int countZero = 0;

    int process(List<String> lines) {
        for (String l : lines) {
            Instruction i = readIinstruction(l);
            apply(i);
            if (current == 0) {
                countZero++;
            }
        }
        return countZero;
    }

    private void apply(Instruction i) {
        switch (i.direction) {
            case 'L' -> current -= i.rotation;
            case 'R' -> current += i.rotation;
        }

        current %= MAX + 1;
        if (current < 0) {
            current += MAX + 1;
        }
        //IO.println(i + " to " + current);
    }

    private Instruction readIinstruction(String l) {
        char direction = l.charAt(0);
        int rotation = Integer.parseInt(l.substring(1));
        return new Instruction(direction, rotation);
    }

    public record Instruction(char direction, int rotation) {
    }

}
