import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * @author daniel.rejment@tacton.com (2017-12-09)
 */
public class Day09 {
    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Paths.get("src/day09.txt")).get(0);
        int[] counts = {0, 0};
        parse(new StringCharacterIterator(line), 0, counts);
        System.out.println(counts[0]);
        System.out.println(counts[1]);
    }

    private static void parse(CharacterIterator ci, int outer, int[] counts) {
        counts[0] += outer + 1;
        while (ci.current() != '}') {
            switch (ci.next()) {
                case '{':
                    parse(ci, outer + 1, counts);
                    break;
                case '<':
                    while (ci.next() != '>') {
                        if (ci.current() == '!') {
                            ci.next();
                        } else {
                            counts[1]++;
                        }
                    }
                    ci.next();
                    break;
            }
        }
        ci.next();
    }
}
