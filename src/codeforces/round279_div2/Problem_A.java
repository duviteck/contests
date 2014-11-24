package codeforces.round279_div2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by duviteck. 23 Nov 2014.
 */
public class Problem_A {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<Integer> l1 = new ArrayList<>(5000);
        List<Integer> l2 = new ArrayList<>(5000);
        List<Integer> l3 = new ArrayList<>(5000);

        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            int num = s.nextInt();
            switch (num) {
                case 1:
                    l1.add(i + 1);
                    break;
                case 2:
                    l2.add(i + 1);
                    break;
                case 3:
                    l3.add(i + 1);
                    break;
            }
        }

        int size = Math.min(l1.size(), Math.min(l2.size(), l3.size()));
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            System.out.println(l1.get(i) + " " + l2.get(i) + " " + l3.get(i));
        }

        s.close();
    }
}
