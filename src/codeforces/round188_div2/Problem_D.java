package codeforces.round188_div2;

import java.util.Scanner;

public class Problem_D {

    private static final int SIDE = 90;
    private static final int SIZE = 2 * SIDE + 1;
    private static int[][] table;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        table = new int[SIZE][SIZE];
        table[SIDE][SIDE] = n;
        emulateProcess();

        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            System.out.println(getAns(x, y));
        }
    }

    private static int getAns(int x, int y) {
        if (Math.abs(x) > SIDE || Math.abs(y) > SIDE) {
            return 0;
        }
        return table[x + SIDE][y + SIDE];
    }

    private static void emulateProcess() {
        boolean update = true;
        while (update) {
            update = false;
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (table[x][y] >= 4) {
                        int add = table[x][y] / 4;
                        table[x + 1][y] += add;
                        table[x - 1][y] += add;
                        table[x][y + 1] += add;
                        table[x][y - 1] += add;
                        table[x][y] -= add * 4;
                        update = true;
                    }
                }
            }
        }
    }
}
