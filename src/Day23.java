import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel.rejment@tacton.com (2017-12-23)
 */
public class Day23 {
    public static void main(String[] args) throws IOException {
        List<String> program = Files.readAllLines(Paths.get("src/day23.txt"));

        Map<String, Long> regs = new HashMap<>();
        int ip = 0;
        int part1 = 0;
        while (ip >= 0 && ip < program.size()) {
            String[] cmd = program.get(ip).split(" ");
            switch (cmd[0]) {
                case "set": {
                    regs.put(cmd[1], val(regs, cmd[2]));
                    break;
                }
                case "sub": {
                    regs.put(cmd[1], val(regs, cmd[1]) - val(regs, cmd[2]));
                    break;
                }
                case "mul": {
                    regs.put(cmd[1], val(regs, cmd[1]) * val(regs, cmd[2]));
                    part1++;
                    break;
                }
                case "jnz": {
                    if (val(regs, cmd[1]) != 0) {
                        ip += val(regs, cmd[2]);
                        ip--;
                    }
                    break;
                }
            }
            ip++;
        }
        System.out.println(part1);

        int b = 57 * 100 + 100000;
        int c = b + 17000;
        int h = 0;
        for (; b != c; b += 17) {
            int f = 1;
            for (int d = 2; d < b; d++) {
                if (b % d == 0) f = 0;
                /*for (int e = 2; e < b; e++) {
                    if (d * e == b) f = 0;
                }*/
                if (f == 0) break;
            }
            if (f == 0) h++;
        }
        System.out.println(h);
    }


    private static Long val(Map<String, Long> registers, String register) {
        return register.matches("-?\\d+") ? Long.valueOf(Long.parseLong(register)) : registers.getOrDefault(register, 0L);
    }

}
