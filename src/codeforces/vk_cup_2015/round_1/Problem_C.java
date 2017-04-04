package codeforces.vk_cup_2015.round_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

/**
 * Created by duviteck on 21/03/15.
 */
public class Problem_C {

    private static int n;
    private static int k;
    private static int[] val;
    private static Set<Integer> valSet;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" ");
        n = parseInt(tokens[0]);
        k = parseInt(tokens[1]);

        val = new int[n];
        valSet = new HashSet<>(n);
        tokens = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int v = parseInt(tokens[i]);
            val[i] = v;
            valSet.add(v);
        }

        int q = parseInt(reader.readLine());
        for (int i = 0; i < q; i++) {
            int query = parseInt(reader.readLine());
            int ans = processQuery(query);
            System.out.println(ans);
        }
    }

    private static int processQuery(int query) {
        if (k == 1) {
            if (valSet.contains(query)) {
                return 1;
            } else {
                return -1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int v = val[i];
            if (v > query) {
                continue;
            }
            if (v * k < query) {
                break;
            }

            int vMaxMult = query / v;
            if (query == vMaxMult * v) {
                return vMaxMult;
            }

            for (int vMult = vMaxMult; vMult > 0; vMult--) {
                int queryRest = query - v * vMult;
                int kRest = k - vMaxMult;

                for (int j = i - 1; j >= 0; j--) {
                    int w = val[j];
                    if (w > queryRest) {
                        continue;
                    }
                    if (w * kRest < queryRest) {
                        break;
                    }

                    int wMult = queryRest / w;
                    if (queryRest == wMult * w) {
                        return vMult + wMult;
                    }
                }
            }
        }

        return -1;
    }
}
