import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-21)
 */
public class Day21 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/day21.txt"));

        Map<String, String> lookup = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split(" => ");
            String[] key = split[0].split("/");
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
            flip(key);
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
            rotate(key);
            lookup.put(String.join("/", key), split[1]);
        }

        String[] board = ".#./..#/###".split("/");
        int d = 3;
        for (int r = 0; r < 18; r++) {
            if (d % 2 == 0) {
                String[] next = new String[3 * (d / 2)];
                Arrays.fill(next, "");
                for (int cy = 0; cy < d / 2; cy++) {
                    for (int cx = 0; cx < d / 2; cx++) {
                        String[] block = new String[2];
                        for (int y = 0; y < 2; y++) block[y] = board[cy * 2 + y].substring(cx * 2, cx * 2 + 2);
                        String[] expanded = lookup.get(String.join("/", block)).split("/");
                        for (int y = 0; y < 3; y++) next[cy * 3 + y] += expanded[y];
                    }
                }
                d = 3 * (d / 2);
                board = next;
            } else {
                String[] next = new String[4 * (d / 3)];
                Arrays.fill(next, "");
                for (int cy = 0; cy < d / 3; cy++) {
                    for (int cx = 0; cx < d / 3; cx++) {
                        String[] block = new String[3];
                        for (int y = 0; y < 3; y++) block[y] = board[cy * 3 + y].substring(cx * 3, cx * 3 + 3);
                        String[] expanded = lookup.get(String.join("/", block)).split("/");
                        for (int y = 0; y < 4; y++) next[cy * 4 + y] += expanded[y];
                    }
                }
                d = 4 * (d / 3);
                board = next;
            }
            long count = Stream.of(String.join("", board).split("")).filter(a -> a.equals("#")).count();
            System.out.println((r + 1) + ": " + count);
        }
    }

    private static void flip(String[] block) {
        String[] temp = Arrays.copyOf(block, block.length);
        for (int y = 0; y < block.length; y++) {
            block[y] = temp[block.length - 1 - y];
        }
    }

    private static void rotate(String[] block) {
        String[] temp = Arrays.copyOf(block, block.length);
        for (int y = 0; y < block.length; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < block.length; x++) {
                line.append(temp[x].charAt(block.length - y - 1));
            }
            block[y] = line.toString();
        }
    }
}
