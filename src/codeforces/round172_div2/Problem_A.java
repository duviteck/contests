package codeforces.round172_div2;

import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] line = s.nextLine().toCharArray();
        String prefix = new String(new char[] {line[0]});
        prefix = prefix.toUpperCase();
        line[0] = prefix.charAt(0);
        System.out.println(new String(line));
    }
}
