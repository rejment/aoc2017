import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-04)
 */
public class Day04 {
    public static void main(String[] args) throws IOException {
        List<String> rows = Files.readAllLines(Paths.get("src/day04.txt"));
        int sum = 0;
        int sum2 = 0;
        for (String row : rows) {
            String[] split = row.split("\\s");
            Object[] objects = Stream.of(split).distinct().toArray();
            if (split.length == objects.length) {
                sum++;
            }

            String[] sortedWords = Stream.of(split).map(a -> {
                char[] chars = a.toCharArray();
                Arrays.sort(chars);
                return new String(chars);
            }).toArray(String[]::new);
            Object[] objects2 = Stream.of(sortedWords).distinct().toArray();
            if (sortedWords.length == objects2.length) {
                sum2++;
            }
        }
        System.out.println(sum);
        System.out.println(sum2);
    }
}
