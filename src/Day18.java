import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author daniel.rejment@tacton.com (2017-12-18)
 */
public class Day18 {
    public static void main(String[] args) throws IOException {
        List<String> program = Files.readAllLines(Paths.get("src/day18.txt"));

        List<Long> q = new ArrayList<>();
        long[] w = {-1, 0};
        run(program, q, q, new HashMap<>(), w);
        System.out.println(w[0]);

        List<Long> q1 = new ArrayList<>();
        List<Long> q2 = new ArrayList<>();

        Map<String, Long> r1 = new HashMap<>();
        Map<String, Long> r2 = new HashMap<>(Collections.singletonMap("p", 1L));

        w[1] = 0;
        do {
            run(program, q1, q2, r1, new long[2]);
            run(program, q2, q1, r2, w);
        } while (!q1.isEmpty() || !q2.isEmpty());
        System.out.println(w[1]);
    }


    private static void run(List<String> program, List<Long> write, List<Long> read, Map<String, Long> regs, long[] counters) {
        long ip = val(regs, "ip");
        while (ip >= 0 && ip < program.size()) {
            String[] cmd = program.get((int) ip).split(" ");
            switch (cmd[0]) {
                case "snd": {
                    write.add(val(regs, cmd[1]));
                    counters[1]++;
                    break;
                }
                case "set": {
                    regs.put(cmd[1], val(regs, cmd[2]));
                    break;
                }
                case "add": {
                    regs.put(cmd[1], val(regs, cmd[1]) + val(regs, cmd[2]));
                    break;
                }
                case "mul": {
                    regs.put(cmd[1], val(regs, cmd[1]) * val(regs, cmd[2]));
                    break;
                }
                case "mod": {
                    regs.put(cmd[1], val(regs, cmd[1]) % val(regs, cmd[2]));
                    break;
                }
                case "rcv": {
                    if (read.isEmpty()) return;
                    if (counters[0] == -1) {
                        counters[0] = read.get(read.size() - 1);
                        return;
                    }
                    regs.put(cmd[1], read.remove(0));
                    break;
                }
                case "jgz": {
                    if (val(regs, cmd[1]) > 0) {
                        ip += val(regs, cmd[2]);
                        regs.put("ip", ip);
                        continue;
                    }
                    break;
                }
            }
            ip++;
            regs.put("ip", ip);
        }
    }

    private static Long val(Map<String, Long> registers, String register) {
        return register.matches("-?\\d+") ? Long.valueOf(Long.parseLong(register)) : registers.getOrDefault(register, 0L);
    }
}
