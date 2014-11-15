package codeforces.round172_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_D {

    private static int[] stack;
    private static int stackTop = -1;
    private static int bestXOR = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        stack = new int[n];
        for (int i = 0; i < n; i++) {
            int num = s.nextInt();
            stackPush(num);
        }

        while (stackTop > 0) {
            int xor = stack[stackTop] ^ stack[stackTop - 1];
            if (xor > bestXOR) {
                bestXOR = xor;
            }
            stackTop--;
        }

        System.out.println(bestXOR);
    }

    private static void stackPush(int num) {
        if (stackTop == -1) {
            stack[++stackTop] = num;
            return;
        }

        while (stackTop >= 0 && stack[stackTop] < num) {
            int xor = stack[stackTop] ^ num;
            if (xor > bestXOR) {
                bestXOR = xor;
            }
            stackTop--;
        }

        stack[++stackTop] = num;
        if (stackTop > 0) {
            int xor = stack[stackTop] ^ stack[stackTop - 1];
            if (xor > bestXOR) {
                bestXOR = xor;
            }
        }
    }
}
