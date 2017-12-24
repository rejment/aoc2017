import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-24)
 */
public class Day24 {
    public static void main(String[] args) throws IOException {
        List<int[]> input = Files.readAllLines(Paths.get("src/day24.txt")).stream().map(line ->
                Stream.of(line.split("/")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());

        int search = solve(input, 0, 0);
        System.out.println(search);
        int[] search2 = solve2(input, 0, 0, 0);
        System.out.println(search2[0]);
    }

    private static int solve(List<int[]> input, int port, int s) {
        int max = s;
        for (int[] comp : input) {
            if (comp[0] == port || comp[1] == port) {
                max = Math.max(max, solve(remove(input, comp), comp[0] == port ? comp[1] : comp[0], s + comp[0] + comp[1]));
            }
        }
        return max;
    }

    private static int[] solve2(List<int[]> input, int port, int s, int l) {
        int [] max = {s, l};
        for (int[] comp : input) {
            if (comp[0] == port || comp[1] == port) {
                int[] r = solve2(remove(input, comp), comp[0] == port ? comp[1] : comp[0], s + comp[0] + comp[1], l + 1);
                if (r[1] > max[1] && r[0] > max[0]) {
                    max = r;
                }
            }
        }
        return max;
    }

    private static List<int[]> remove(List<int[]> input, int[] comp) {
        ArrayList<int[]> ints = new ArrayList<>(input);
        ints.remove(comp);
        return ints;
    }
}
