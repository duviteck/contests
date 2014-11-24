package codeforces.round279_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_B {

    // ugly solution

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        HashMap<Integer, Integer> forward = new HashMap<>(n);
        HashMap<Integer, Integer> backward = new HashMap<>(n);
        HashMap<Integer, Integer> count = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int[] pair = parseNums(reader);
            forward.put(pair[0], pair[1]);
            backward.put(pair[1], pair[0]);

            Integer c1 = count.get(pair[0]);
            count.put(pair[0], c1 == null ? 1 : 2);

            Integer c2 = count.get(pair[1]);
            count.put(pair[1], c2 == null ? 1 : 2);
        }

        int[] queue = new int[n];
        Integer cur = forward.get(0);
        int index = 1;
        while (index < n) {
            queue[index] = cur;
            index += 2;
            cur = forward.get(cur);
        }

        if (n % 2 == 0) {
            cur = backward.get(0);
            index = n - 2;
            while (index >= 0) {
                queue[index] = cur;
                index -= 2;
                cur = backward.get(cur);
            }
        } else {
            Integer start = null;
            for (Map.Entry<Integer, Integer> c : count.entrySet()) {
                if (c.getValue() == 1 && forward.keySet().contains(c.getKey())) {
                    start = c.getKey();
                    break;
                }
            }

            cur = start;
            index = 0;
            while (index < n) {
                queue[index] = cur;
                index += 2;
                cur = forward.get(cur);
            }
        }

        // print
        for (int i : queue) {
            System.out.print(i + " ");
        }
        System.out.println();

        reader.close();
    }

    private static int[] parseNums(BufferedReader reader) throws IOException {
        String[] tokens = reader.readLine().split(" ");
        return new int[] {Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
    }
}
