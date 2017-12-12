import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author daniel.rejment@tacton.com (2017-12-12)
 */
public class Day12 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/day12.txt"));

        Map<String, List<String>> map = new HashMap<>();
        for (String line : lines) {
            String key = line.split(" <-> ")[0];
            List<String> list = Arrays.asList(line.split(" <-> ")[1].split(", "));
            map.put(key, list);
        }

        Set<String> set = new TreeSet<>();
        gather("0", map, set);
        System.out.println(set.size());

        int groups = 1;
        set.forEach(map::remove);
        for (; !map.isEmpty(); groups++) {
            set.clear();
            gather(map.keySet().iterator().next(), map, set);
            set.forEach(map::remove);
        }
        System.out.println(groups);
    }

    private static void gather(String root, Map<String, List<String>> map, Set<String> seen) {
        seen.add(root);
        map.get(root).forEach(c -> {
            if (!seen.contains(c)) {
                gather(c, map, seen);
            }
        });
    }
}
