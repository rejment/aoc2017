import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author daniel.rejment@tacton.com (2017-12-07)
 */
public class Day07 {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> weights = new HashMap<>();
        Map<String, List<String>> tree = new HashMap<>();

        Files.readAllLines(Paths.get("src/day07.txt")).forEach(line -> {
            String[] split = line.split(" -> ");
            String weight = split[0].substring(split[0].indexOf("(") + 1, split[0].length() - 1);
            String name = split[0].substring(0, split[0].indexOf(" "));
            weights.put(name, Integer.parseInt(weight));
            List<String> list = tree.computeIfAbsent(name, a -> new ArrayList<>());
            if (split.length == 2) {
                list.addAll(Arrays.asList(split[1].split(", ")));
            }
        });

        List<String> roots = new ArrayList<>(tree.keySet());
        tree.values().forEach(roots::removeAll);
        String root = roots.get(0);
        System.out.println(root);

        for (String child : tree.keySet()) {
            for (int theBestForce = -10; theBestForce < 10; theBestForce++) {
                int candidate = weights.get(child) + theBestForce;
                if (candidate > 0) {
                    Map<String, Integer> modifiedWeights = new HashMap<>(weights);
                    modifiedWeights.put(child, candidate);
                    if (isBalanced(root, modifiedWeights, tree)) {
                        System.out.println(candidate);
                        break;
                    }
                }
            }
        }
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
