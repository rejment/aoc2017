import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-10)
 */
public class Day10 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day10.txt")).get(0);

        int[] lens = Stream.of(line.split(",")).mapToInt(Integer::parseInt).toArray();
        int[] list = IntStream.range(0, 256).toArray();
        hash(list, lens, 1);
        int part1 = list[0] * list[1];
        System.out.println(part1);

        int[] lens2 = IntStream.concat(Stream.of(line.split("")).mapToInt(a -> a.charAt(0)), IntStream.of(17, 31, 73, 47, 23)).toArray();
        int[] list2 = IntStream.range(0, 256).toArray();
        hash(list2, lens2, 64);
        String part2 = IntStream.range(0, 16).mapToObj(i -> Integer.toHexString(0x100 + IntStream.of(list2).skip(i * 16).limit(16).reduce((a, b) -> a ^ b).orElse(0)).substring(1)).collect(Collectors.joining(""));
        System.out.println(part2);
    }

    private static void hash(int[] list, int[] lens, int count) {
        int pos = 0;
        int skip = 0;
        for (int i = 0; i < count; i++) {
            for (int len : lens) {
                for (int i1 = 0; i1 < (len / 2); i1++) {
                    int temp = list[(pos + i1) % list.length];
                    list[(pos + i1) % list.length] = list[(pos + (len - i1) - 1) % list.length];
                    list[(pos + (len - i1) - 1) % list.length] = temp;
                }
                pos = (pos + len + skip) % list.length;
                skip++;
            }
        }
    }
}
