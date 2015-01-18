package facebook_hacker_cup.year_2015.qualification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duviteck. 10 Jan 2015.
 *
 * Alex's New Year's resolution for 2015 is to eat healthier foods. He's done some research and has found out
 * that calories come from three main sources, called macronutrients: protein, carbohydrates, and fat.
 * Alex wants to get the right balance of protein, carbohydrates, and fat to have a balanced diet.
 * For each available food, Alex can only choose to eat it or not eat it. He can't eat a certain food more than once,
 * and he can't eat a fractional amount of a food.
 *
 * Input
 * Input begins with an integer T, the number of test cases.
 * For each test case, the first line consists of three space-separated integers: GP, GC, and GF, which represent
 * the amount of protein, carbohydrates, and fat that Alex wants to eat. The next line has the number of foods
 * for that test case, an integer N. The next N lines each consist of three space-separated integers: P, C, and F,
 * which represent the amount of protein, carbohydrates, and fat in that food, respectively.
 *
 * Output
 * For each test case i, print a line containing "Case #i: " followed by either "yes" if it is possible for Alex
 * to eat the exact amount of each macronutrient, or "no" if it is not possible.
 *
 * Constraints
 * 1 ≤ T ≤ 20
 * 1 ≤ N ≤ 20
 * 10 ≤ GP, GC, GF ≤ 1000
 * 10 ≤ P, C, F ≤ 1000
 */
public class Problem_B_NewYearResolution {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_IN));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        int tests = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= tests; i++) {
            List<Triplet> input = readInput(reader);
            Triplet target = input.remove(input.size() - 1);
            boolean ans = calcAns(target, input);

            writer.format("Case #%d: %s\n", i, ans ? "yes" : "no");
        }
        writer.flush();
    }

    private static List<Triplet> readInput(BufferedReader reader) throws IOException {
        Triplet target = readTriplet(reader);

        int n = Integer.parseInt(reader.readLine());

        List<Triplet> res = new ArrayList<>(n + 1);
        for (int i = 0; i < n; i++) {
            res.add(readTriplet(reader));
        }
        res.add(target);

        return res;
    }

    private static Triplet readTriplet(BufferedReader reader) throws IOException {
        String[] tokens = reader.readLine().split(" ");
        return new Triplet(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }

    private static boolean calcAns(Triplet target, List<Triplet> items) {
        return traverseAllCases(target, items, 0);
    }

    private static boolean traverseAllCases(Triplet target, List<Triplet> items, int index) {
        if (target.a == 0 && target.b == 0 && target.c == 0) {
            return true;
        } else if (index == items.size()) {
            return false;
        }

        // try with cur usage
        Triplet cur = items.get(index);
        if (cur.a <= target.a && cur.b <= target.b && cur.c <= target.c) {
            target.remove(cur);
            boolean canCover = traverseAllCases(target, items, index + 1);
            if (canCover) {
                return true;
            }
            target.add(cur);
        }

        // try without cur usage
        return traverseAllCases(target, items, index + 1);
    }


    private static class Triplet {
        int a;
        int b;
        int c;

        public Triplet(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public void remove(Triplet t) {
            a -= t.a;
            b -= t.b;
            c -= t.c;
        }

        public void add(Triplet t) {
            a += t.a;
            b += t.b;
            c += t.c;
        }
    }
}
