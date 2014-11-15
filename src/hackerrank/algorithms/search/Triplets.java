package hackerrank.algorithms.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2014 year
 */
public class Triplets {

    private static Element[] nums;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine().trim());
        nums = new Element[n];

        Map<Integer, Element> map = new HashMap<Integer, Element>(n);

        StringTokenizer st = new StringTokenizer(reader.readLine().trim());
        for (int j = 0; j < n; j++) {
            int val = Integer.parseInt(st.nextToken());
            Element elem = map.get(val);
            if (elem == null) {
                elem = new Element(val, j, n - j - 1);
                map.put(val, elem);
            }
            nums[j] = elem;
        }

        long ans = calcAns();
        System.out.println(ans);
    }

    private static long calcAns() {
        mergeSort(nums, 0, n - 1, 0);
        mergeSort(nums, 0, n - 1, 1);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            Element elem = nums[i];
            //ans += elem.elemsBefore * elem.elemsAfter;
        }

        return ans;
    }

    private static Element[] mergeSort(Element[] arr, int start, int end, int mergeFlag) {
        if (start == end) {
            return new Element[] {arr[start]};
        }

        int middle = (start + end) / 2;
        Element[] part1 = mergeSort(arr, start, middle, mergeFlag);
        Element[] part2 = mergeSort(arr, middle + 1, end, mergeFlag);

        if (mergeFlag == 0) {
            return mergeBefore(part1, part2);
        } else {
            return mergeAfter(part1, part2);
        }

    }

    private static Element[] mergeBefore(Element[] p1, Element[] p2) {
        int i1 = 0;
        int i2 = 0;
        int ires = 0;
        int l1 = p1.length;
        int l2 = p2.length;
        int lres = l1 + l2;
        Element[] res = new Element[lres];

        while (ires < lres) {
            if (i1 == l1) {
                System.arraycopy(p2, i2, res, ires, l2 - i2);
                break;
            } else if (i2 == l2) {
                System.arraycopy(p1, i1, res, ires, l1 - i1);
                break;
            } else if (p1[i1].val < p2[i2].val) {
                res[ires++] = p1[i1++];
            } else {
                //p2[i2].elemsBefore -= l1 - i1;
                res[ires++] = p2[i2++];
            }
        }

        return res;
    }

    private static Element[] mergeAfter(Element[] p1, Element[] p2) {
        int i1 = 0;
        int i2 = 0;
        int ires = 0;
        int l1 = p1.length;
        int l2 = p2.length;
        int lres = l1 + l2;
        Element[] res = new Element[lres];

        int lessCount = 0;

        while (ires < lres) {
            if (i1 == l1) {
                System.arraycopy(p2, i2, res, ires, l2 - i2);
                break;
            } else if (i2 == l2) {
                System.arraycopy(p1, i1, res, ires, l1 - i1);
                for (int i = i1 + 1; i < l1; i++) {
                    //p1[i].elemsAfter -= lessCount;
                }
                break;
            } else if (p1[i1].val < p2[i2].val) {
                res[ires++] = p1[i1++];
                if (i1 < l1) {
                    //p1[i1].elemsAfter -= lessCount;
                }
            } else {
                res[ires++] = p2[i2++];
                //p1[i1].elemsAfter--;
                lessCount++;
            }
        }

        return res;
    }


    private static class Element {
        int val;
        HashSet<Integer> elemsBefore;
        HashSet<Integer> elemsAfter;

        public Element(int value, int elemsBefore, int elemsAfter) {
            this.val = value;
            this.elemsBefore = new HashSet<Integer>(elemsBefore);
            this.elemsAfter = new HashSet<Integer>(elemsAfter);
        }

        public static void addRelation(Element before, Element after) {
            before.elemsAfter.add(after.val);
            after.elemsBefore.add(before.val);
        }
    }
}
