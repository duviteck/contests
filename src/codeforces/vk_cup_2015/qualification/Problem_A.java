package codeforces.vk_cup_2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * http://codeforces.ru/contest/523/problem/A
 *
 * Created by duviteck on 14/03/15.
 */
public class Problem_A {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] tokens = reader.readLine().split(" ");
        int w = Integer.parseInt(tokens[0]);
        int h = Integer.parseInt(tokens[1]);

        char[][] table = new char[h][];
        for (int i = 0; i < h; i++) {
            table[i] = reader.readLine().toCharArray();
        }

        char[][] mutateRotate = rotate(table);
//        printTable(writer, mutateRotate);

//        System.out.println("############################");

        char[][] mutateMirror = mirror(mutateRotate);
//        printTable(writer, mutateMirror);

//        System.out.println("############################");

        char[][] mutateScale = scale(mutateMirror);
        printTable(writer, mutateScale);
    }

    private static void printTable(PrintWriter writer, char[][] table) {
        for (char[] line : table) {
            writer.println(line);
        }
        writer.flush();
    }

    private static char[][] rotate(char[][] table) {
        int h = table.length;
        int w = table[0].length;
        char[][] res = new char[w][h];

        for (int i = 0; i < h; i++) {
            int col = h - i - 1;
            for (int j = 0; j < w; j++) {
                res[j][col] = table[i][j];
            }
        }

        return res;
    }

    private static char[][] mirror(char[][] table) {
        int h = table.length;
        int w = table[0].length;
        char[][] res = new char[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                res[i][w - j - 1] = table[i][j];
            }
        }

        return res;
    }

    private static char[][] scale(char[][] table) {
        int h = table.length;
        int w = table[0].length;
        char[][] res = new char[2 * h][];

        for (int i = 0; i < h; i++) {
            char[] target = new char[2 * w];

            for (int j = 0; j < w; j++) {
                target[2 * j] = target[2 * j + 1] = table[i][j];
            }

            int resLine = i * 2;
            res[resLine] = target;
            res[resLine + 1] = target;
        }


        return res;
    }
}
