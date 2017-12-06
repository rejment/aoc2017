import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-06)
 */
public class Day06 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day06.txt")).get(0);
        int[] slots = Stream.of(line.split("\\s")).mapToInt(Integer::parseInt).toArray();

        List<String> seen = new ArrayList<>();
        seen.add(Arrays.toString(slots));

        int count = 0;
        int distance;
        while (true) {

            int max = -1;
            int maxIndex = 0;
            for (int i = 0; i < slots.length; i++) {
                if (slots[i] > max) {
                    max = slots[i];
                    maxIndex = i;
                }
            }

            slots[maxIndex] = 0;
            for (int i = 0; i < max; i++) {
                slots[(maxIndex + 1 + i) % slots.length]++;
            }
            count++;

            String key = Arrays.toString(slots);
            if (seen.contains(key)) {
                distance = count - seen.indexOf(key);
                break;
            }
            seen.add(key);
        }

        System.out.println(count);
        System.out.println(distance);
    }
}
