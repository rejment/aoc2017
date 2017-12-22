import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel.rejment@tacton.com (2017-12-22)
 */
public class Day22 {
    public static void main(String[] args) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/day22.txt"));
        Map<Point, Integer> grid1 = new HashMap<>();
        Map<Point, Integer> grid2 = new HashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            String line = strings.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid1.put(Point.of(j, i), line.charAt(j) == '#' ? 1 : 0);
                grid2.put(Point.of(j, i), line.charAt(j) == '#' ? 2 : 0);
            }
        }

        Point current = Point.of(12, 12);
        Point direction = Point.of(0, -1);
        int part1 = 0;
        for (int i = 0; i < 10000; i++) {
            int val = grid1.getOrDefault(current, 0);
            direction = direction.rotate90(val == 1 ? -1 : 1);
            val = 1 - val;
            grid1.put(current, val);
            current = current.add(direction);
            if (val == 1) part1++;
        }
        System.out.println(part1);

        current = Point.of(12, 12);
        direction = Point.of(0, -1);
        int part2 = 0;
        for (int i = 0; i < 10000000; i++) {
            int inf = grid2.getOrDefault(current, 0);
            if (inf == 0) {
                grid2.put(current, 1);
                direction = direction.rotate90(1);
            } else if (inf == 1) {
                grid2.put(current, 2);
                part2++;
            } else if (inf == 2) {
                grid2.put(current, 3);
                direction = direction.rotate90(-1);
            } else if (inf == 3) {
                grid2.put(current, 0);
                direction = Point.of(-direction.x, -direction.y);
            }
            current = current.add(direction);
        }
        System.out.println(part2);
    }

    private static class Point {
        int x;
        int y;

        static Point of(int x, int y) {
            Point pt = new Point();
            pt.x = x;
            pt.y = y;
            return pt;
        }

        Point rotate90(int direction) {
            return Point.of(direction * y, (-direction) * x);
        }

        Point add(Point pt) {
            return Point.of(x + pt.x, y + pt.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
