import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daniel.rejment@tacton.com (2017-12-03)
 */
public class Day03 {
    public static void main(String[] args) throws IOException {
        int input = Integer.parseInt(Files.readAllLines(Paths.get("src/day03.txt")).get(0));
        int[] coordinates = spiral(input);
        int distance = Math.abs(coordinates[0]) + Math.abs(coordinates[1]);
        System.out.println(distance);

        Map<String, Integer> m = new HashMap<>();
        m.put("0 0", 1);
        for (int i = 2; ; i++) {
            coordinates = spiral(i);
            int sum = 0;
            sum += m.getOrDefault((coordinates[0] - 1) + " " + (coordinates[1] - 1), 0);
            sum += m.getOrDefault((coordinates[0] - 1) + " " + (coordinates[1]), 0);
            sum += m.getOrDefault((coordinates[0] - 1) + " " + (coordinates[1] + 1), 0);
            sum += m.getOrDefault((coordinates[0]) + " " + (coordinates[1] - 1), 0);
            sum += m.getOrDefault((coordinates[0]) + " " + (coordinates[1] + 1), 0);
            sum += m.getOrDefault((coordinates[0] + 1) + " " + (coordinates[1] - 1), 0);
            sum += m.getOrDefault((coordinates[0] + 1) + " " + (coordinates[1]), 0);
            sum += m.getOrDefault(coordinates[0] + 1 + " " + (coordinates[1] + 1), 0);
            if (sum > input) {
                System.out.println(sum);
                break;
            }
            m.put((coordinates[0]) + " " + (coordinates[1]), sum);
        }
    }

    private static int[] spiral(int n) {
        int k = (int) Math.ceil((Math.sqrt(n) - 1) / 2);
        int t = 2 * k + 1;
        int m = t * t;
        t -= 1;
        if (n >= m - t) {
            return new int[]{k - (m - n), -k};
        }
        m -= t;
        if (n >= m - t) {
            return new int[]{-k, -k + (m - n)};
        }
        m -= t;
        if (n >= m - t) {
            return new int[]{-k + (m - n), k};
        }
        return new int[]{k, k - (m - n - t)};
    }
}
