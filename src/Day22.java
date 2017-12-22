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
        Map<Integer, Integer> grid1 = new HashMap<>();
        Map<Integer, Integer> grid2 = new HashMap<>();
        for (int y = 0; y < strings.size(); y++) {
            String line = strings.get(y);
            for (int x = 0; x < line.length(); x++) {
                int key = (y + 10000) * 10000 + x;
                grid1.put(key, line.charAt(x) == '#' ? 1 : 0);
                grid2.put(key, line.charAt(x) == '#' ? 2 : 0);
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int x = 12, y = 12;
        int d = 0;

        int part1 = 0;
        for (int i = 0; i < 10000; i++) {
            int key = (y + 10000) * 10000 + x;
            int val = grid1.getOrDefault(key, 0);
            d = (d + (val == 1 ? 1 : 3)) % 4;
            grid1.put(key, 1 - val);
            x += dx[d];
            y += dy[d];
            if (val == 0) part1++;
        }
        System.out.println(part1);

        x = y = 12;
        d = 0;
        int part2 = 0;
        for (int i = 0; i < 10000000; i++) {
            int key = (y + 10000) * 10000 + x;
            int val = grid2.getOrDefault(key, 0);
            if (val == 0) {
                d = (d + 3) % 4;
            } else if (val == 1) {
                part2++;
            } else if (val == 2) {
                d = (d + 1) % 4;
            } else if (val == 3) {
                d = (d + 2) % 4;
            }
            grid2.put(key, (val + 1) % 4);
            x += dx[d];
            y += dy[d];
        }
        System.out.println(part2);
    }
}
