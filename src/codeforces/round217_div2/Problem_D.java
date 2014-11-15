package codeforces.round217_div2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_D {

    private static char[][] table;
    private static int n;
    private static int m;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        s.nextLine();

        List<Point> points = new ArrayList<Point>();
        table = new char[n][];
        for (int i = 0; i < n; i++) {
            char[] line = s.nextLine().toCharArray();
            table[i] = line;

            for (int j = 0; j < m; j++) {
                if (line[j] == 'w') {
                    points.add(new Point(i, j));
                }
            }
        }

        if (points.size() == 1) {
            printTable();
            return;
        }

        boolean ans = processTable(points);
        if (!ans) {
            System.out.println(-1);
            return;
        }

        printTable();
    }

    private static void printTable() {
        for (char[] line : table) {
            System.out.println(new String(line));
        }
    }

    private static boolean processTable(List<Point> points) {
        int l = 100000;
        int r = -1;
        int up = 100000;
        int down = -1;

        for (Point p : points) {
            if (p.x < up) {
                up = p.x;
            }
            if (p.x > down) {
                down = p.x;
            }
            if (p.y < l) {
                l = p.y;
            }
            if (p.y > r) {
                r = p.y;
            }
        }

        int height = down - up + 1;
        int width = r - l + 1;
        int size = Math.max(height, width);

        for (int x : new int[] {0, up, down - size + 1}) {
            for (int y : new int[] {0, l, r - size + 1}) {
                Point start = new Point(x, y);
                if (checkPoint(start, size) && canDrawOnPoints(points, start, size)) {
                    drawFigure(start, size);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkPoint(Point start, int size) {
        return start.x >= 0 && start.y >= 0 && (start.x + size <= n) && (start.y + size <= m);
    }

    private static boolean canDrawOnPoints(List<Point> points, Point start, int size) {
        for (Point p : points) {
            if (    (p.x == start.x && p.y < start.y + size) ||
                    (p.x == start.x + size - 1 && p.y < start.y + size) ||
                    (p.y == start.y && p.x < start.x + size) ||
                    (p.y == start.y + size - 1 && p.x < start.x + size) ) {
            }
            else {
                return false;
            }
        }

        return true;
    }

    private static void drawFigure(Point start, int size) {
        char[] line = table[start.x];
        for (int i = start.y; i < start.y + size; i++) {
            if (line[i] == '.') {
                line[i] = '+';
            }
        }

        line = table[start.x + size - 1];
        for (int i = start.y; i < start.y + size; i++) {
            if (line[i] == '.') {
                line[i] = '+';
            }
        }

        for (int i = start.x; i < start.x + size; i++) {
            if (table[i][start.y] == '.') {
                table[i][start.y] = '+';
            }
            if (table[i][start.y + size - 1] == '.') {
                table[i][start.y + size - 1] = '+';
            }
        }
    }
}
