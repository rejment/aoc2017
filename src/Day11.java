import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author daniel.rejment@tacton.com (2017-12-11)
 */
public class Day11 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day11.txt")).get(0);

        int max = 0, x = 0, y = 0, dist = 0;
        for (String s : line.split(",")) {
            switch (s) {
                case "n":
                    y++;
                    break;
                case "ne":
                    x++;
                    break;
                case "se":
                    x++; y--;
                    break;
                case "s":
                    y--;
                    break;
                case "sw":
                    x--;
                    break;
                case "nw":
                    x--; y++;
                    break;
            }
            dist = Math.max(Math.max( Math.abs(x), Math.abs(y)), Math.abs( (-x + -y)));
            max = Math.max(dist, max);
        }
        System.out.println(dist);
        System.out.println(max);
    }
}
