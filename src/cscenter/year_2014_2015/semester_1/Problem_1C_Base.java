package cscenter.year_2014_2015.semester_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * "Collector"
 *
 * Created by duviteck. 23 Dec 2014.
 */
public class Problem_1C_Base {
    private static final int TOTAL_COUNT = 15000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        int[] existing = new int[TOTAL_COUNT];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < count; i++) {
            int val = Integer.parseInt(tokenizer.nextToken());
            existing[val - 1] = 1;
        }

        int existingCount = 0;
        for (int i : existing) {
            existingCount += i;
        }

        System.out.println(TOTAL_COUNT - existingCount);
    }
}
