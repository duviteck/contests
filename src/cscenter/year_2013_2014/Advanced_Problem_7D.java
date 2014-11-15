package cscenter.year_2013_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Advanced_Problem_7D {
    private static final int NOT_VISITED = 2000000;
    private static final int FORBIDDEN = -1;

    private static int w;
    private static int h;
    private static int[][] table;

    private static int[] pointsRow;
    private static int[] pointsCol;
    private static int pointsCnt;

    private static int[] wayRow;
    private static int[] wayCol;
    private static int wayIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String l = reader.readLine();
        String[] temp = l.split(" ");
        w = Integer.parseInt(temp[0]);
        h = Integer.parseInt(temp[1]);
        int c1 = Integer.parseInt(temp[2]) - 1;
        int r1 = Integer.parseInt(temp[3]) - 1;
        int c2 = Integer.parseInt(temp[4]) - 1;
        int r2 = Integer.parseInt(temp[5]) - 1;

        table = new int[h][w];
        pointsRow = new int[w * h];
        pointsCol = new int[w * h];
        pointsCnt = 0;

        for (int i = 0; i < h; i++) {
            char[] line = reader.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                table[i][j] = (line[j] == '.') ? NOT_VISITED : FORBIDDEN;
            }
        }

        addPoint(r1, c1, 1);

        int curPoint = 0;
        while (curPoint < pointsCnt) {
            processPoint(pointsRow[curPoint], pointsCol[curPoint]);
            curPoint++;
        }

        if (table[r2][c2] == NOT_VISITED) {
            System.out.println("NO");
            return;
        }

        int wayLength = table[r2][c2];
        wayRow = new int[wayLength];
        wayCol = new int[wayLength];
        wayIndex = wayLength - 1;
        buildBackWay(r2, c2);

        StringBuilder sb = new StringBuilder();
        String space = " ";
        String end = "\n";

        sb.append("YES");
        sb.append(end);
        for (int i = 0; i < wayLength; i++) {
            sb.append(wayCol[i]);
            sb.append(space);
            sb.append(wayRow[i]);
            sb.append(end);
        }
        System.out.println(sb.toString());
    }

    private static void addPoint(int row, int column, int newVal) {
        table[row][column] = newVal;

        pointsRow[pointsCnt] = row;
        pointsCol[pointsCnt] = column;
        pointsCnt++;
    }

    private static void processPoint(int r, int c) {
        int newVal = table[r][c] + 1;

        if (r > 0 && table[r - 1][c] > newVal) {
            addPoint(r - 1, c, newVal);
        }
        if (r < h - 1 && table[r + 1][c] > newVal) {
            addPoint(r + 1, c, newVal);
        }
        if (c > 0 && table[r][c - 1] > newVal) {
            addPoint(r, c - 1, newVal);
        }
        if (c < w - 1 && table[r][c + 1] > newVal) {
            addPoint(r, c + 1, newVal);
        }
    }

    private static void buildBackWay(int r, int c) {
        wayRow[wayIndex] = r + 1;
        wayCol[wayIndex] = c + 1;
        int prevVal = wayIndex;
        wayIndex--;

        if (prevVal == 0) {
            return;
        }

        if (r > 0 && table[r - 1][c] == prevVal) {
            buildBackWay(r - 1, c);
        } else if (r < h - 1 && table[r + 1][c] == prevVal) {
            buildBackWay(r + 1, c);
        } else if (c > 0 && table[r][c - 1] == prevVal) {
            buildBackWay(r, c - 1);
        } else {
            buildBackWay(r, c + 1);
        }
    }
}
