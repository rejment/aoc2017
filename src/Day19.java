import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author daniel.rejment@tacton.com (2017-12-19)
 */
public class Day19 {
    public static void main(String[] args) throws IOException {
        List<String> grid = Files.readAllLines(Paths.get("src/day19.txt"));

        Point p = new Point(27, 0);
        Point dir = new Point(0, 1);

        StringBuilder result = new StringBuilder();
        int count = 0;
        while (true) {
            int c = getChar(grid, p);
            if (c == ' ') {
                break;
            } else if (Character.isAlphabetic(c)) {
                result.append((char) c);
            } else if (c == '+') {
                if (getChar(grid, p.add(dir.rotate90(1))) != ' ') {
                    dir = dir.rotate90(1);
                } else {
                    dir = dir.rotate90(-1);
                }
            }
            p = p.add(dir);
            count++;
        }
        System.out.println(result);
        System.out.println(count);

    }

    private static int getChar(List<String> grid, Point point) {
        if (point.y < 0 || point.y > grid.size() - 1) return ' ';
        String line = grid.get(point.y);
        return point.x >= 0 && point.x < line.length() ? line.charAt(point.x) : ' ';
    }

    private static class Point {
        public final int x;
        public final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point rotate90(int direction) {
            return new Point(direction * y, (-direction) * x);
        }

        public Point add(Point pt) {
            return new Point(x + pt.x, y + pt.y);
        }
    }
}
