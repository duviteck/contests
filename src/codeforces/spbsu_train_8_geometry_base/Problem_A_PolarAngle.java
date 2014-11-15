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
public class Problem_A_PolarAngle {

    private static final String filename = "angle1";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename + ".in"));
        PrintWriter writer = new PrintWriter(new File(filename + ".out"));

        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        writer.println(calcPolarAngle(x, y));

        writer.close();
    }

    private static double calcPolarAngle(double x, double y) {
        double angle = Math.atan2(y, x);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }
}
