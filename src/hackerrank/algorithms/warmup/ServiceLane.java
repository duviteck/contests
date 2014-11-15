package hackerrank.algorithms.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class ServiceLane {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int[] temp = readPair(reader.readLine());
        int n = temp[0];
        int tests = temp[1];

        a = readArray(reader.readLine(), n);

        for (int i = 0; i < tests; i++) {
            int[] query = readPair(reader.readLine());
            int ans = calcAns(query[0], query[1]);
            writer.println(ans);
        }

        reader.close();
        writer.close();
    }

    private static int calcAns(int start, int end) {
        int ans = 3;
        int decreaseCnt = 0;
        for (int i = start; i <= end; i++) {
            if (a[i] < ans) {
                ans = a[i];
                decreaseCnt++;
                if (decreaseCnt == 2) {
                    return 1;
                }
            }
        }
        return ans;
    }

    private static int[] readPair(String line) {
        String[] tokens = line.split(" ");
        return new int[] {Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
    }

    private static int[] readArray(String line, int n) {
        String[] tokens = line.split(" ");
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = Integer.parseInt(tokens[i]);
        }
        return ans;
    }
}
