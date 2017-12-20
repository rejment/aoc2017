import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author daniel.rejment@tacton.com (2017-12-20)
 */
public class Day20 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/day20.txt"));

        int[] c = {0};
        List<String[]> particles = lines.stream().map(line -> (line.substring(3) + Integer.toString(c[0]++)).split(",|>, v=<|>, a=<|>")).collect(Collectors.toList());
        String part1 = particles.stream().sorted(Comparator.comparing((String[] p) -> str(p, 6)).
                thenComparing(p -> str(p, 3)).thenComparing(particle -> str(particle, 0))).findFirst().orElse(new String[9])[9];
        System.out.println(part1);

        for (int i = 0; i < 1000; i++) {
            for (String[] particle : particles) {
                particle[3] = Long.toString(Long.parseLong(particle[3]) + Long.parseLong(particle[6]));
                particle[4] = Long.toString(Long.parseLong(particle[4]) + Long.parseLong(particle[7]));
                particle[5] = Long.toString(Long.parseLong(particle[5]) + Long.parseLong(particle[8]));
                particle[0] = Long.toString(Long.parseLong(particle[0]) + Long.parseLong(particle[3]));
                particle[1] = Long.toString(Long.parseLong(particle[1]) + Long.parseLong(particle[4]));
                particle[2] = Long.toString(Long.parseLong(particle[2]) + Long.parseLong(particle[5]));
            }
            particles = particles.stream().collect(Collectors.groupingBy(p -> str(p, 0))).values().stream().filter(a -> a.size() == 1).flatMap(Collection::stream).collect(Collectors.toList());
        }
        System.out.println(particles.size());
    }

    private static String str(String[] array, int start) {
        return Stream.of(array).skip(start).limit(3).collect(Collectors.joining(","));
    }
}
