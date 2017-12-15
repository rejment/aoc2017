import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author daniel.rejment@tacton.com (2017-12-15)
 */
public class Day15 {
    public static void main(String[] args) throws IOException {
        int[] input = Files.readAllLines(Paths.get("src/day15.txt")).stream().mapToInt(l -> Integer.parseInt(l.split(" ")[4])).toArray();

        long a = input[0];
        long b = input[1];
        long mod = 2147483647;
        long fa = 16807;
        long fb = 48271;

        int part1 = 0;
        for (int i = 0; i < 40_000_000; i++) {
            a = (a * fa) % mod;
            b = (b * fb) % mod;
            if ((a & 0xffff) == (b & 0xffff)) part1++;
        }
        System.out.println(part1);

        a = input[0];
        b = input[1];
        int part2 = 0;
        for (int i = 0; i < 5_000_000; i++) {
            do a = (a * fa) % mod; while ((a % 4) != 0);
            do b = (b * fb) % mod; while ((b % 8) != 0);
            if ((a & 0xffff) == (b & 0xffff)) part2++;
        }
        System.out.println(part2);
    }
}
