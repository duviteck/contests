package codeforces.spbsu_train_8_geometry_base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_R_PointInSegment {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("point3.in"));
        PrintWriter writer = new PrintWriter(new File("point3.out"));

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();

        double ans = (x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3);
        if (ans == 0 && x1 >= Math.min(x2, x3) && x1 <= Math.max(x2, x3) &&
                y1 >= Math.min(y2, y3) && y1 <= Math.max(y2, y3)) {
            writer.println("YES");
        } else {
            writer.println("NO");
        }

        writer.close();
    }
}
