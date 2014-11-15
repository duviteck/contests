package codeforces.round226_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Problem_C {

    private static final List<Integer> prime = new ArrayList<Integer>(100000);
    private static final int[] count = new int[10000001];
    private static final long[] f = new long[10000001];
    private static StringBuilder stringBuilder;

    public static void main(String[] args) throws IOException {
        initPrime();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            count[val]++;
        }

        fillF();

        int m = Integer.parseInt(reader.readLine());
        stringBuilder = new StringBuilder(m * 10);
        for (int i = 0; i < m; i++) {
            String[] temp = reader.readLine().split(" ");
            int l = Integer.parseInt(temp[0]);
            int r = Integer.parseInt(temp[1]);
            l = Math.min(l, 10000000);
            r = Math.min(r, 10000000);
            processQuery(l, r);
        }

        System.out.println(stringBuilder.toString());
    }

    private static void initPrime() {
        byte[] arr = new byte[10000001];
        for (int i = 2; i <= 10000000; i++) {
            if (arr[i] == 0) {
                prime.add(i);
                for (int j = i * 2; j <= 10000000; j += i) {
                    arr[j] = 1;
                }
            }
        }
    }

    private static void fillF() {
        for (int p : prime) {
            for (int i = p; i <= 10000000; i += p) {
                f[p] += count[i];
            }
        }

        for (int i = 1; i <= 10000000; i++) {
            f[i] += f[i - 1];
        }
    }

    private static void processQuery(int l, int r) {
        long ans = f[r] - f[l - 1];

        stringBuilder.append(ans);
        stringBuilder.append("\n");
    }
}
