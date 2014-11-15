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
public class Problem_B_AngleBetweenVectors {

    private static final String filename = "angle2";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename + ".in"));
        PrintWriter writer = new PrintWriter(new File(filename + ".out"));

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        writer.println(calcAngleBetweenVectors(x1, y1, x2, y2));

        writer.close();
    }

    private static double calcAngleBetweenVectors(double x1, double y1, double x2, double y2) {
        double scalarMult = x1 * x2 + y1 * y2;
        double norm1 = Math.sqrt(x1 * x1 + y1 * y1);
        double norm2 = Math.sqrt(x2 * x2 + y2 * y2);
        double angleCos = scalarMult / (norm1 * norm2);
        return Math.acos(angleCos);
    }
}
