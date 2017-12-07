import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A little later: the solution I tried to make before I gave up and brute forced it.
 *
 * @author daniel.rejment@tacton.com (2017-12-07)
 */
public class Day07b {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> weights = new HashMap<>();
        Map<String, List<String>> tree = new HashMap<>();

        Files.readAllLines(Paths.get("src/day07.txt")).forEach(line -> {
            Matcher matcher = Pattern.compile("^(.+) \\((\\d+)\\)( -> (.*))?$").matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(1);
                weights.put(name, Integer.parseInt(matcher.group(2)));
                List<String> list = tree.computeIfAbsent(name, a -> new ArrayList<>());
                Optional.ofNullable(matcher.group(4)).ifPresent(l-> list.addAll(Arrays.asList(l.split(", "))));
            }
        });

        String root = tree.keySet().stream().filter(a -> tree.values().stream().noneMatch(l -> l.contains(a))).findFirst().orElse(null);
        int part2 = solve(root, weights, tree);

        System.out.println(root);
        System.out.println(part2);
    }

    private static int solve(String root, Map<String, Integer> weights, Map<String, List<String>> tree) {
        Map<Integer, List<String>> itemsBySize = tree.get(root).stream().collect(Collectors.groupingBy(a -> getWeight(a, weights, tree)));
        for (Map.Entry<Integer, List<String>> e : itemsBySize.entrySet()) {
            int totalWeight = e.getKey();
            List<String> list = e.getValue();
            if (list.size() == 1) {
                String child = list.get(0);
                if (isBalanced(child, weights, tree)) {
                    int targetWeight = itemsBySize.keySet().stream().filter(a -> a != totalWeight).findFirst().orElse(-1);
                    int childWeight = weights.get(child);
                    return childWeight + (targetWeight - totalWeight);
                } else {
                    return solve(child, weights, tree);
                }
            }
        }
        return -1;
    }

    private static boolean isBalanced(String root, Map<String, Integer> weights, Map<String, List<String>> nodes) {
        List<String> children = nodes.get(root);
        if (children.stream().anyMatch(a -> !isBalanced(a, weights, nodes))) return false;
        int[] ws = children.stream().mapToInt(a -> getWeight(a, weights, nodes)).toArray();
        return IntStream.of(ws).noneMatch(a -> a != ws[0]);
    }

    private static int getWeight(String root, Map<String, Integer> weights, Map<String, List<String>> nodes) {
        return weights.get(root) + nodes.get(root).stream().mapToInt(a -> getWeight(a, weights, nodes)).sum();
    }
}
