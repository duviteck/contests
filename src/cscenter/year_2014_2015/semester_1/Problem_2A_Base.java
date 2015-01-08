package cscenter.year_2014_2015.semester_1;

import java.util.*;

/**
 * Enter to "LCSH" (Summer Computer School, perhaps)
 *
 * Created by duviteck. 28 Dec 2014.
 */
public class Problem_2A_Base {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();

        Pair[] elems = new Pair[m];
        for (int i = 0; i < m; i++) {
            Pair newElem = new Pair(s.nextInt(), i + 1);
            elems[i] = newElem;
        }

        List<Integer> res = calcAns(elems, n);

        printAns(res);
    }

    private static List<Integer> calcAns(Pair[] elems, int target) {
        Arrays.sort(elems, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (o1.val > o2.val) ? -1 : (o1.val < o2.val) ? 1 : 0;
            }
        });

        List<Integer> res = new ArrayList<>();
        for (Pair elem : elems) {
            res.add(elem.order);
            target -= elem.val;
            if (target <= 0) {
                break;
            }
        }

        if (target > 0) {
            return null;
        } else {
            return res;
        }
    }

    private static void printAns(List<Integer> ans) {
        if (ans == null) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder(ans.size() * 8 + 5);

            sb.append(ans.size()).append("\n");

            for (int order : ans) {
                sb.append(order).append(" ");
            }
            sb.setLength(sb.length() - 1);

            System.out.println(sb.toString());
        }
    }


    private static class Pair {
        int val;
        int order;

        public Pair(int val, int order) {
            this.val = val;
            this.order = order;
        }
    }
}
