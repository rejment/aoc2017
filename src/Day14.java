import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-14)
 */
public class Day14 {
    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Paths.get("src/day14.txt")).get(0);

        int[][] grid = new int[128][128];

        int part1 = 0;
        for (int i = 0; i < 128; i++) {
            String knot = hash(input + "-" + i);
            for (int j = 0; j < 128; j++) {
                if (knot.charAt(j) == '1') {
                    grid[i][j] = 1;
                    part1++;
                }
            }
        }
        System.out.println(part1);

        int part2 = 0;
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                if (grid[i][j] == 1) {
                    fill(grid, i, j);
                    part2++;
                }
            }
        }
        System.out.println(part2);
    }

    private static void fill(int[][] grid, int i, int j) {
        if (i >= 0 && i < 128 && j >= 0 && j < 128 && grid[i][j] == 1) {
            grid[i][j] = 0;
            fill(grid, i - 1, j);
            fill(grid, i + 1, j);
            fill(grid, i, j - 1);
            fill(grid, i, j + 1);
        }
    }

    private static String hash(String line) {
        int[] vars = {0, 0};
        int[] lengths = IntStream.concat(Stream.of(line.split("")).mapToInt(a -> a.charAt(0)), IntStream.of(17, 31, 73, 47, 23)).toArray();
        int[] list = IntStream.range(0, 256).toArray();
        IntStream.range(0, 64).forEach(repeat -> IntStream.of(lengths).forEach(len -> {
            IntStream.range(0, len / 2).forEach(x -> swap(list, (vars[0] + x) % list.length, (vars[0] + (len - x) - 1) % list.length));
            vars[0] = (vars[0] + len + vars[1]++) % list.length;
        }));
        return IntStream.range(0, 16).mapToObj(i -> Integer.toBinaryString(0x100 + IntStream.of(list).skip(i * 16).limit(16).reduce((a, b) -> a ^ b).orElse(0)).substring(1)).collect(Collectors.joining(""));
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
