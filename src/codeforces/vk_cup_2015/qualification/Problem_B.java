package codeforces.vk_cup_2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * http://codeforces.ru/contest/523/problem/B
 *
 * Created by duviteck on 14/03/15.
 */
public class Problem_B {

    private static long[] realSums;
    private static double[] approxMeans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        // read input
        String[] tokens = reader.readLine().split(" ");
        int n = parseInt(tokens[0]);
        int T = parseInt(tokens[1]);
        double c = parseDouble(tokens[2]);

        long[] a = new long[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = parseLong(tokenizer.nextToken());
        }

        // process input
        realSums = new long[n];
        realSums[0] = a[0];
        for (int i = 1; i < n; i++) {
            realSums[i] = realSums[i - 1] + a[i];
        }

        approxMeans = new double[n];
        approxMeans[0] = (a[0] / (double)T) / c;
        for (int i = 1; i < n; i++) {
            approxMeans[i] = (approxMeans[i - 1] + a[i] / (double)T) / c;
        }

        // process queries
        int m = parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < m; i++) {
            int p = parseInt(tokenizer.nextToken());
            double real = getReal(p, T);
            double approx = getApprox(p);
            double dif = Math.abs(approx - real) / real;
            writer.format("%.6f %.6f %.6f\n", real, approx, dif);
        }
        writer.flush();
    }

    private static double getApprox(int p) {
        return approxMeans[p - 1];
    }

    private static double getReal(int p, int t) {
        if (p > t) {
            return (realSums[p - 1] - realSums[p - 1 - t]) / (double)t;
        } else {
            return realSums[p - 1] / (double)p;
        }
    }
}
