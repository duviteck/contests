package codeforces.round279_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_E {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        char[][] lines = new char[n][];
        for (int i = 0; i < n; i++) {
            lines[i] = reader.readLine().toCharArray();
        }

        char[] lastVal = new char[0];
        for (int i = 0; i < n; i++) {
            char[] restoredLine = restoreLine(lines[i], lastVal);
            if (restoredLine == null) {
                System.out.println("NO");
                return;
            }
            lastVal = restoredLine;
        }

        System.out.println("YES");
        for (char[] line : lines) {
            System.out.println(line);
        }
    }

    private static char[] restoreLine(char[] line, char[] lastVal) {
        if (line.length < lastVal.length) {
            return null;
        }

        if (line.length > lastVal.length) {
            fillWithZeros(line, 0);
            return line;
        }

        // equal length
        return restoreLine(line, lastVal, 0);
    }

    private static void fillWithZeros(char[] line, int startPos) {
        for (int i = startPos; i < line.length; i++) {
            if (line[i] == '?') {
                if (i == 0) {
                    line[i] = '1';
                } else {
                    line[i] = '0';
                }
            }
        }
    }

    private static char[] restoreLine(char[] line, char[] lastVal, int startPos) {
        if (startPos >= line.length) {
            return null;
        }

        if (line[startPos] != '?') {
            if (line[startPos] < lastVal[startPos]) {
                return null;
            } else if (line[startPos] > lastVal[startPos]) {
                fillWithZeros(line, startPos + 1);
                return line;
            } else {
                return restoreLine(line, lastVal, startPos + 1);
            }
        }

        // line[startPos] = '?'
        char[] restoredNext = restoreLine(line, lastVal, startPos + 1);
        if (restoredNext != null) {
            line[startPos] = lastVal[startPos];
            return line;
        } else {
            if (lastVal[startPos] == '9') {
                return null;
            } else {
                line[startPos] = (char)((int)lastVal[startPos] + 1);
                fillWithZeros(line, startPos + 1);
                return line;
            }
        }
    }
}
