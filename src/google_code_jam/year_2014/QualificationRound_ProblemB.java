package google_code_jam.year_2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by duviteck on 12/04/14.
 */
public class QualificationRound_ProblemB {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("b.in"));
        PrintWriter writer = new PrintWriter(new File("b.out"));

        int testCount = s.nextInt();
        s.nextLine();

        for (int testNum = 1; testNum <= testCount; testNum++) {
            double c = s.nextDouble();
            double f = s.nextDouble();
            double x = s.nextDouble();
            double ans = calcAns(c, f, x);
            writer.format("Case #%d: %.7f\n", testNum, ans);
        }

        writer.close();
        s.close();
    }

    private static double calcAns(double c, double f, double x) {
        if (c >= x) {
            return x / 2d;
        }

        final double const1 = f * x;
        final double const2 = c * f;

        double ans = 0;
        double g = 2.0d;
        while (const1 > (const2 + c * g)) {
            ans += c / g;
            g += f;
        }

        ans += x / g;
        return ans;
    }
}
