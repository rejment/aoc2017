import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author daniel.rejment@tacton.com (2017-12-05)
 */
public class Day05 {
    public static void main(String[] args) throws IOException {
        int[] ints = Files.readAllLines(Paths.get("src/day05.txt")).stream().mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int pos=0; pos >= 0 && pos < ints.length; count++) {
            pos += ints[pos]++;
        }
        System.out.println(count);

        ints = Files.readAllLines(Paths.get("src/day05.txt")).stream().mapToInt(Integer::parseInt).toArray();
        count = 0;
        for (int pos=0; pos >= 0 && pos < ints.length; count++) {
            int jump = ints[pos]++;
            if (jump >= 3) {
                ints[pos] -= 2;
            }
            pos += jump;
        }
        System.out.println(count);
    }
}
