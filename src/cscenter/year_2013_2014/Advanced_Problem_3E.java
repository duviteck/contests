package cscenter.year_2013_2014;

import java.util.Scanner;

public class Advanced_Problem_3E {

    private static char[] line;
    private static int lineLen;
    private static int[] lens;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        line = s.nextLine().toCharArray();
        s.close();

        lineLen = line.length;
        lens = new int[lineLen];

        lens[lineLen - 1] = -1;
        for (int i = lineLen - 2; i >= 0; i--) {
            calcSeqLen(i);
        }

        int bestStart = -1;
        int bestLen = 0;

        for (int i = 0; i <= lineLen - bestLen; i++) {
            int curLen = 0;
            int start = i;
            while (start < lineLen && lens[start] > 0) {
                curLen += lens[start];
                start += lens[start];
            }

            if (curLen > bestLen) {
                bestLen = curLen;
                bestStart = i;
            }
        }

        if (bestStart == -1) {
            System.out.println();
        } else {
            System.out.println(new String(line, bestStart, bestLen));
        }
    }

    private static void calcSeqLen(int start) {
        char c = line[start];
        if (c == ')' || c == ']' || c == '}') {
            lens[start] = -1;
            return;
        }

        if (isCompliant(c, line[start + 1])) {
            lens[start] = 2;
            return;
        }

        int newStart = start + 1;
        while (lens[newStart] > 0) {
            int i = newStart + lens[newStart];
            if (i < lineLen && lens[i] > 0) {
                newStart = i;
            } else {
                if (i < lineLen && isCompliant(c, line[i])) {
                    lens[start] = i - start + 1;
                } else {
                    lens[start] = -1;
                }
                return;
            }
        }
    }

    private static boolean isCompliant(char a, char b) {
        return ((a == '(' && b == ')') ||
                (a == '[' && b == ']') ||
                (a == '{' && b == '}'));
    }
}
