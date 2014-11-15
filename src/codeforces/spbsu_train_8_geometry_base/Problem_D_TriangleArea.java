package codeforces.spbsu_train_8_geometry_base;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_D_TriangleArea {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();

        double ans = (x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3);
        System.out.println((Math.abs(ans) / 2));
    }

}
