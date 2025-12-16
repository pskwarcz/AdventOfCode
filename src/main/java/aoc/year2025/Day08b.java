package aoc.year2025;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Day08b extends Day08 {

    static void main() {
        new Day08b().start();
    }

    Day08b() {
        super(0);
    }

    int calculateResult() {
        // distances is ordered by key=distance, so we start with the shortest ones
        Box first = boxes.getFirst();

        Iterator<Map.Entry<Long, Set<Box>>> iterator = distances.entrySet().iterator();
        Map.Entry<Long, Set<Box>> es = null;
        while (first.cluster.size() != boxes.size()) {
            es = iterator.next();
            connectBoxes(es.getValue());
        }
        assert es != null;
        return es.getValue().stream().map(b -> b.x).reduce((x, y) -> x * y).orElseThrow();
    }


}
