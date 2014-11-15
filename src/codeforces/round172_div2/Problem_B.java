package codeforces.round172_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_B {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double x = s.nextDouble();
        double y = s.nextDouble();
        int n = s.nextInt();
        double target = x / y;

        int bestA = -1;
        int bestB = -1;
        float minError = (float)(2 * target);
        for (int fract = 1; fract <= n; fract++) {
            double candidate = x * fract / y;
            // check candidate 1
            int cand1 = (int)candidate;
            float curError = (float)Math.abs(target - (double)cand1 / (double)fract);
            if (curError < minError) {
                bestA = cand1;
                bestB = fract;
                minError = curError;
            }
            // cgeck candidate 2
            int cand2 = (int)Math.ceil(candidate);
            curError = (float)Math.abs(target - (double)cand2 / (double)fract);
            if (curError < minError) {
                bestA = cand2;
                bestB = fract;
                minError = curError;
            }
        }

        System.out.println(bestA + "/" + bestB);
    }
}
