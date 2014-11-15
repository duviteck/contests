package cscenter.year_2013_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Advanced_Problem_6F {

    private static int n;
    private static int[] x;
    private static int[] y;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        x = new int[n];
        y = new int[n];
        int[] plus = new int[n];
        int[] minus = new int[n];

        for (int i = 0; i < n; i++) {
            String[] temp = reader.readLine().split(" ");
            x[i] = Integer.parseInt(temp[0]);
            y[i] = Integer.parseInt(temp[1]);
            plus[i] = x[i] + y[i];
            minus[i] = x[i] - y[i];
        }

        double midPlus = getMiddle(plus);
        double midMinus = getMiddle(minus);

        int[] plusCases = getCases(midPlus);
        int[] minusCases = getCases(midMinus);

        int[] ans = chooseBestAns(plusCases, minusCases);

        System.out.println(ans[0] + " " + ans[1]);
    }

    private static double getMiddle(int[] a) {
        int max = a[0];
        int min = a[0];

        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        return ((double)min + (double)max) / 2;
    }

    private static int[] getCases(double a) {
        double rest = a - Math.floor(a);
        if (rest != 0.5d) {
            return new int[] { (int)Math.round(a)};
        } else {
            return new int[] { (int)Math.floor(a), (int)Math.ceil(a) };
        }
    }

    private static int[] chooseBestAns(int[] plusCases, int[] minusCases) {
        int bestX = 0;
        int bestY = 0;
        int bestTime = Integer.MAX_VALUE;

        for (int plus : plusCases) {
            for (int minus : minusCases) {
                int[] xy = getXY(plus, minus);
                int time = calcTime(xy[0], xy[1]);
                if (time < bestTime) {
                    bestTime = time;
                    bestX = xy[0];
                    bestY = xy[1];
                }
            }
        }

        return new int[] {bestX, bestY};
    }

    private static int[] getXY(int plus, int minus) {
        int x = (plus + minus) / 2;
        int y = plus - x;
        return new int[] {x, y};
    }

    private static int calcTime(int testX, int testY) {
        int maxTime = 0;

        for (int i = 0; i < n; i++) {
            int time = Math.abs(testX - x[i]) + Math.abs(testY - y[i]);
            if (time > maxTime) {
                maxTime = time;
            }
        }

        return maxTime;
    }
}
