package cscenter.year_2014_2015.semester_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Square matrix multiplication
 *
 * Created by duviteck. 24 Dec 2014.
 */
public class Problem_1G_Advanced {

    private static int n;
    private static long mod;

    private static long OVERFLOW_MOD_ADD;

    private static long[][] a;
    private static long[][] b;
    private static long[][] c;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long[] temp = tokenizeLine(reader.readLine(), 2);

        n = (int)temp[0];
        mod = temp[1];
        OVERFLOW_MOD_ADD = Long.MAX_VALUE % mod + 1 - Long.MIN_VALUE;

        a = new long[n][];
        b = new long[n][];
        c = new long[n][n];

        readMatrix(a, reader);
        readMatrix(b, reader);

        matrixMultiplication();
        printMatrix(c);
    }

    private static long[] tokenizeLine(String line, int len) {
        long[] res = new long[len];
        StringTokenizer tokenizer = new StringTokenizer(line, " ");
        for (int i = 0; i < len; i++) {
            res[i] = Long.parseLong(tokenizer.nextToken());
        }
        return res;
    }

    private static void readMatrix(long[][] matrix, BufferedReader reader) throws IOException {
        for (int i = 0; i < n; i++) {
            matrix[i] = tokenizeLine(reader.readLine(), n);
        }
    }

    private static void matrixMultiplication() {
        for (int i = 0; i < n; i++) {
            long[] targetLine = c[i];
            for (int k = 0; k < n; k++) {
                // modulate values
                if (k > 0 && k % 17 == 0) {
                    modulate(targetLine);
                }

                long m1 = a[i][k];
                long[] m2Line = b[k];
                for (int j = 0; j < n; j++) {
                    targetLine[j] += m1 * m2Line[j];
                }
            }
            modulate(targetLine);
        }
    }

    private static void modulate(long[] line) {
        for (int i = 0; i < n; i++) {
            if (line[i] < 0) {
                line[i] = (line[i] + OVERFLOW_MOD_ADD) % mod;
            } else {
                line[i] = line[i] % mod;
            }
        }
    }

    private static void printMatrix(long[][] m) {
        StringBuilder sb = new StringBuilder(700 * 700 * 10);
        for (long[] line : m) {
            for (long num : line) {
                sb.append(num);
                sb.append(' ');
            }
            sb.setCharAt(sb.length() - 1, '\n');
        }
        System.out.println(sb.toString());
    }
}
