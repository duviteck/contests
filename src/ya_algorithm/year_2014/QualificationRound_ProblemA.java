package ya_algorithm.year_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class QualificationRound_ProblemA {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] size = parseLine(reader);
        int[][] table = new int[size[0] + 2][size[1] + 2];
        Arrays.fill(table[0], 0);
        Arrays.fill(table[size[0] + 1], 0);
        for (int i = 1; i <= size[0]; i++) {
            int[] line = table[i];
            line[0] = 0;
            line[line.length - 1] = 0;
            int[] nums = parseLine(reader);
            System.arraycopy(nums, 0, line, 1, nums.length);
        }

        int[] ans = findBestCell(table);
        System.out.println(ans[0] + " " + ans[1]);

        reader.close();
    }

    private static int[] parseLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] nums = line.split(" ");
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = Integer.parseInt(nums[i]);
        }
        return res;
    }

    private static int[] findBestCell(int[][] table) {
        int n = table.length;
        int m = table[0].length;
        int[] res = new int[2];
        double bestVal = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                double val = calcVal(table, i, j);
                if (val > bestVal) {
                    bestVal = val;
                    res[0] = i + 1;
                    res[1] = j + 1;
                }
            }
        }

        return res;
    }

    private static double calcVal(int[][] table, int n, int m) {
        double res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res += (double)table[n + i][m + j] / 16;
            }
        }

        res += (double)table[n + 1][m + 1] * 7 / 16;
        return res;
    }
}
