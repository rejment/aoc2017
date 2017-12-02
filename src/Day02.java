import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author daniel.rejment@tacton.com (2017-12-02)
 */
public class Day02 {
    public static void main(String[] args) throws IOException {
        List<String> rows = Files.readAllLines(Paths.get("src/day02.txt"));

        int sum1 = 0;
        int sum2 = 0;
        for (String string : rows) {
            int[] ints = Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).toArray();

            sum1 += IntStream.of(ints).max().orElse(0);
            sum1 -= IntStream.of(ints).min().orElse(0);

            for (int a : ints) {
                for (int b : ints) {
                    if (a != b && (a % b == 0)) {
                        sum2 += (a / b);
                    }
                }
            }
        }

        System.out.println(sum1);
        System.out.println(sum2);
    }
}
