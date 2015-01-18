package facebook_hacker_cup.year_2015.qualification;

import java.io.*;
import java.util.Arrays;

/**
 * Created by duviteck. 10 Jan 2015.
 *
 * Every business can make use of a good accountant and, if they're not big on following the law, sometimes a bad one.
 * Bad accountants try to make more money for their employers by fudging numbers without getting caught.
 *
 * Sometimes a bad accountant wants to make a number larger, and sometimes smaller. It is widely known
 * that tax auditors will fail to notice two digits being swapped in any given number, but any discrepancy
 * more egregious will certainly be caught. It's also painfully obvious when a number has fewer digits than it ought to,
 * so a bad accountant will never swap the first digit of a number with a 0.
 *
 * Given a number, how small or large can it be made without being found out?
 *
 * Input
 * Input begins with an integer T, the number of numbers that need tweaking.
 * Each of the next T lines contains a integer N.
 *
 * Output
 * For the ith number, print a line containing "Case #i: " followed by the smallest and largest numbers
 * that can be made from the original number N, using at most a single swap and following the rules above.
 *
 * Constraints
 * 1 ≤ T ≤ 100
 * 0 ≤ N ≤ 999999999
 * N will never begin with a leading 0 unless N = 0
 */
public class Problem_A_CookingTheBooks {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_IN));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        int tests = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= tests; i++) {
            char[] input = reader.readLine().toCharArray();
            char[] min = calcMinPossible(input);
            char[] max = calcMaxPossible(input);

            writer.format("Case #%d: %s %s\n", i, String.valueOf(min), String.valueOf(max));
        }
        writer.flush();
    }

    private static char[] calcMinPossible(char[] input) {
        char[] res = Arrays.copyOf(input, input.length);
        for (int i = 0; i < res.length - 1; i++) {
            int minIndex = getMinIndex(res, i + 1);
            if (minIndex == -1) {
                return res;
            }

            if (res[i] > res[minIndex]) {
                swap(res, i, minIndex);
                return res;
            }
        }
        return res;
    }

    private static int getMinIndex(char[] ar, int startPos) {
        final char minComparedValue = (startPos == 1) ? '1' : '0';

        int minIndex = -1;
        for (int i = startPos; i < ar.length; i++) {
            if (ar[i] < minComparedValue) {
                continue;
            }

            if (minIndex == -1 || ar[i] <= ar[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static char[] calcMaxPossible(char[] input) {
        char[] res = Arrays.copyOf(input, input.length);
        for (int i = 0; i < res.length - 1; i++) {
            int maxIndex = getMaxIndex(res, i + 1);
            if (res[i] < res[maxIndex]) {
                swap(res, i, maxIndex);
                return res;
            }
        }
        return res;
    }

    private static int getMaxIndex(char[] ar, int startPos) {
        int maxIndex = startPos;
        for (int i = startPos + 1; i < ar.length; i++) {
            if (ar[i] >= ar[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static void swap(char[] ar, int p1, int p2) {
        char temp = ar[p1];
        ar[p1] = ar[p2];
        ar[p2] = temp;
    }
}
