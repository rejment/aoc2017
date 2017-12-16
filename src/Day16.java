import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-16)
 */
public class Day16 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day16.txt")).get(0);
        Map<String, String> cache = new HashMap<>();
        List<String> seen = new ArrayList<>();

        String state = "abcdefghijklmnop";

        for (int x = 0; x < 1000000000; x++) {
            seen.add(state);
            if (cache.containsKey(state)) {
                state = seen.get(1000000000 % x);
                break;
            }
            state = cache.computeIfAbsent(state, c -> {
                int[] chars = Stream.of(c.split("")).mapToInt(a -> a.charAt(0)).toArray();
                for (String instr : line.split(",")) {
                    if (instr.startsWith("x")) {
                        int[] split = Stream.of(instr.substring(1).split("/")).mapToInt(Integer::parseInt).toArray();
                        int tmp = chars[split[0]];
                        chars[split[0]] = chars[split[1]];
                        chars[split[1]] = tmp;
                    } else if (instr.startsWith("s")) {
                        for (int i = 0; i < Integer.parseInt(instr.substring(1)); i++) {
                            int tmp = chars[15];
                            System.arraycopy(chars, 0, chars, 1, 15);
                            chars[0] = tmp;
                        }
                    } else if (instr.startsWith("p")) {
                        int c1 = instr.charAt(1);
                        int c2 = instr.charAt(3);
                        for (int i = 0; i < 16; i++) {
                            if (chars[i] == c1) chars[i] = c2;
                            else if (chars[i] == c2) chars[i] = c1;
                        }
                    }
                }
                return IntStream.of(chars).mapToObj(i -> "" + (char) i).collect(Collectors.joining(""));
            });
        }
        System.out.println(seen.get(1));
        System.out.println(state);
    }
}
