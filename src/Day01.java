import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author daniel.rejment@tacton.com (2017-12-01)
 */
public class Day01 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day01.txt")).get(0);

        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < line.length(); i++) {

            char c1 = line.charAt(i);
            char c2 = line.charAt((i + 1) % line.length());
            char c3 = line.charAt((i + (line.length() / 2)) % line.length());

            if (c1 == c2) {
                sum += (c1 - '0');
            }
            if (c1 == c3) {
                sum2 += (c1 - '0');
            }
        }

        System.out.println(sum);
        System.out.println(sum2);
    }
}
