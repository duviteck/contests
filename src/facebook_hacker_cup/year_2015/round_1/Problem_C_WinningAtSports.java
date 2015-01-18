package facebook_hacker_cup.year_2015.round_1;

import java.io.*;
import java.util.Arrays;

/**
 * Created by duviteck. 18 Jan 2015.
 *
 * In the game of Sports, the object is have more points than the other team after a certain amount of time has elapsed.
 * Scores are denoted by two hyphen-separated integers. For example, scores may include 3-2, 4-1, or 10-0.
 * The first number is how many points you've scored, and the second is the number of points scored by the opposing team.
 * You're very good at Sports, and consequently you always win.
 * However, you don't always achieve victory the same way every time.
 *
 * The two most extreme kinds of victory are called stress-free and stressful. In a stress-free victory,
 * you score the first point and from then on you always have more points than your opponent. In a stressful victory,
 * you never have more points than your opponent until after their score is equal to their final score.
 *
 * Given the final score of a game of Sports, how many ways could you arrange the order in which the points are scored
 * such that you secure a stress-free or stressful win?
 *
 * Input
 * Input begins with an integer T, the number of games you'll play. For each game, there is one line containing
 * the final score of the game in the format described above.
 *
 * Output
 * For the ith game, print a line containing "Case #i: " followed by two space-separated integers,
 * the number of ways you can achieve a stress-free or stressful win, respectively.
 * Since these numbers may be very large, output them modulo 1,000,000,007.
 *
 * Constraints
 * 1 ≤ T ≤ 100
 * Since you always win, the first number in any final score will always be larger than the second.
 * Both scores will be non-negative integers not exceeding 2000.
 */
public class Problem_C_WinningAtSports {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    private static final long BASE = 1000000007L;
    private static final long[][] stressFree = new long[2001][2001];
    private static final long[][] stressFull = new long[2001][2001];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_IN));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        fillStressFree();
        fillStressFull();

        int tests = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= tests; i++) {
            int[] input = parseInput(reader.readLine());

            long stressFreeAns = stressFree[input[0]][input[1]];
            long stressFullAns = stressFull[input[1]][input[1]];
            writer.format("Case #%d: %d %d\n", i, stressFreeAns, stressFullAns);
        }
        writer.flush();
    }

    private static void fillStressFree() {
        for (int i = 1; i <= 2000; i++) {
            stressFree[i][0] = 1;
            for (int j = 1; j < i; j++) {
                stressFree[i][j] = (stressFree[i - 1][j] + stressFree[i][j - 1]) % BASE;
            }
        }
    }

    private static void fillStressFull() {
        Arrays.fill(stressFull[0], 1);
        for (int i = 1; i <= 2000; i++) {
            for (int j = i; j <= 2000; j++) {
                stressFull[i][j] = (stressFull[i - 1][j] + stressFull[i][j - 1]) % BASE;
            }
        }
    }

    private static int[] parseInput(String s) {
        String[] tokens = s.split("-");
        return new int[] { Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])};
    }
}
