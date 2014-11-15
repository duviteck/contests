package hackerrank.contests.expansion_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by duviteck. 21 Jun 2014.
 */
public class FindTheMinimaOfAnd {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int tests = Integer.parseInt(reader.readLine());

        for (int i = 0; i < tests; i++) {
            int n = Integer.parseInt(reader.readLine());
            long andOfArray = calcAns(reader.readLine(), n);
            writer.println(andOfArray);
        }

        reader.close();
        writer.close();
    }

    private static long calcAns(String dataLine, int n) {
        String[] tokens = dataLine.split(" ");
        long ans = -1;
        for (int i = 0; i < n; i++) {
            long newNum = Long.parseLong(tokens[i]);
            ans &= newNum;
        }
        return ans;
    }
}
