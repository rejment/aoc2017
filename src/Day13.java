import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

/**
 * @author daniel.rejment@tacton.com (2017-12-13)
 */
public class Day13 {
    public static void main(String[] args) throws IOException {
        int[] heights = new int[1000];
        Files.readAllLines(Paths.get("src/day13.txt")).stream().
                map(line -> line.split(": ")).
                forEach(split -> heights[Integer.parseInt(split[0])] = Integer.parseInt(split[1]));

        int part1 = IntStream.range(0, heights.length).map(i -> (i % (2 * (heights[i] - 1)) == 0) ? i * heights[i] : 0).sum();
        System.out.println(part1);

        int part2 = IntStream.range(1, Integer.MAX_VALUE).filter(wait ->
                IntStream.range(0, heights.length).filter(i -> heights[i] > 0).
                        noneMatch(i -> (i + wait) % (2 * (heights[i] - 1)) == 0)).
                findFirst().orElse(-1);
        System.out.println(part2);
    }
}
