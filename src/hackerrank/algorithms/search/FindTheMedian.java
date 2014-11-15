package hackerrank.algorithms.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by duviteck. 13 Jun 2014.
 */
public class FindTheMedian {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] values = new int[20001];
        String[] tokens = reader.readLine().split(" ");
        reader.close();

        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(tokens[i]);
            values[val + 10000]++;
        }

        int med = getMedian(values, n);
        System.out.println(med);
    }

    private static int getMedian(int[] values, int total) {
        int targetCount = total / 2 + 1;
        int curCount = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                curCount += values[i];
                if (curCount >= targetCount) {
                    return i - 10000;
                }
            }
        }

        return -100500;
    }
}
