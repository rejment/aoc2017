import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author daniel.rejment@tacton.com (2017-12-17)
 */
public class Day17 {
    public static void main(String[] args) throws IOException {
        int input = Integer.parseInt(Files.readAllLines(Paths.get("src/day17.txt")).get(0));

        List<Integer> list = new ArrayList<>(Collections.singletonList(0));
        int pos = 0;
        for (int i = 1; i <= 2017; i++) {
            pos = (pos + input) % i + 1;
            list.add(pos, i);
        }
        System.out.println(list.get(pos + 1));

        int part2 = -1;
        pos = 0;
        for (int i = 1; i < 50000000; i++) {
            pos = (pos + input) % i + 1;
            if (pos == 1) {
                part2 = i;
            }
        }
        System.out.println(part2);
    }
}
