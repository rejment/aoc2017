import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * @author daniel.rejment@tacton.com (2017-12-08)
 */
public class Day08 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/day08.txt"));

        Map<String, BiPredicate<Integer, Integer>> functions = new HashMap<>();
        functions.put("!=", (a,b) -> !Objects.equals(a, b));
        functions.put("==", Objects::equals);
        functions.put(">", (a,b) -> a > b);
        functions.put("<", (a,b) -> a < b);
        functions.put(">=", (a,b) -> a >= b);
        functions.put("<=", (a,b) -> a <= b);

        Map<String, Integer> regs = new HashMap<>();
        int max = 0;

        for (String s : lines) {
            String[] split = s.split(" ");
            if (functions.get(split[5]).test(regs.getOrDefault(split[4], 0), Integer.parseInt(split[6]))) {
                int reg = regs.getOrDefault(split[0], 0);
                int val = Integer.parseInt(split[2]);
                regs.put(split[0], split[1].equals("dec") ? reg - val : reg + val);
            }
            max = Math.max(max, regs.values().stream().mapToInt(a -> a).max().orElse(0));
        }
        System.out.println(regs.values().stream().mapToInt(a -> a).max().orElse(0));
        System.out.println(max);
    }
}
