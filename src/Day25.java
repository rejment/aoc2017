import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel.rejment@tacton.com (2017-12-25)
 */
public class Day25 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/day25.txt"));
        int[] write = lines.stream().filter(l -> l.contains("Write")).mapToInt(l -> l.charAt(22) - '0').toArray();
        int[] move = lines.stream().filter(l -> l.contains("Move")).mapToInt(l -> l.charAt(27) == 'r' ? 1 : -1).toArray();
        int[] next = lines.stream().filter(l -> l.contains("Cont")).mapToInt(l -> l.charAt(26) - 'A').toArray();

        Map<Integer, Integer> tape = new HashMap<>();
        int cursor = 0;
        int state = lines.get(0).charAt(15) - 'A';
        int reps = Integer.parseInt(lines.get(1).split(" ")[5]);
        for (int i = 0; i < reps; i++) {
            int val = tape.getOrDefault(cursor, 0);
            tape.put(cursor, write[state * 2 + val]);
            cursor = cursor + move[state * 2 + val];
            state = next[state * 2 + val];
        }
        System.out.println(tape.values().stream().mapToInt(a -> a).sum());
    }
}
