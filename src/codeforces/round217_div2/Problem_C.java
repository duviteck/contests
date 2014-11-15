package codeforces.round217_div2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Problem_C {

    private static int[][] ans;
    private static List[] colorUsers;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        colorUsers = new List[m + 1];
        ans = new int[n][2];

        for (int i = 0; i <= m; i++) {
            colorUsers[i] = new ArrayList<Integer>();
        }

        int maxCountInOneColor = 0;
        for (int i = 0; i < n; i++) {
            int color = s.nextInt();
            colorUsers[color].add(i);
            if (colorUsers[color].size() > maxCountInOneColor) {
                maxCountInOneColor = colorUsers[color].size();
            }
        }

        List<Integer> restUsers = Collections.emptyList();
        int restColor = -1;

        for (int i = maxCountInOneColor; i > 0; i--) {
            List<Integer> colors = getListsWithSize(colorUsers, i);
            if (colors.isEmpty()) {
                System.out.println("WARNING");
            }

            if (colors.size() == 1) {
                int maxColor = colors.get(0);
                int minColor = getNextMinColor(colorUsers, maxColor);
                if (maxColor == minColor) {
                    restUsers = (List<Integer>)colorUsers[maxColor];
                    restColor = maxColor;
                    break;
                }

                List<Integer> exchangeList = new ArrayList<Integer>(2);
                exchangeList.add(maxColor);
                exchangeList.add(minColor);
                exchange(exchangeList);

            }
            else {
                exchange(colors);
            }
        }

        for (Integer i : restUsers) {
            ans[i][0] = restColor;
            ans[i][1] = restColor;
        }

        System.out.println(n - restUsers.size());
        for (int i = 0; i < n; i++) {
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
    }

    private static List<Integer> getListsWithSize(List[] lists, int size) {
        List<Integer> ans = new ArrayList<Integer>();
        int count = lists.length;

        for (int i = 1; i < count; i++) {
            if (lists[i].size() == size) {
                ans.add(i);
            }
        }

        return ans;
    }

    private static int getNextMaxColor(List[] lists, int forbiddenIndex) {
        int maxCount = 0;
        int bestIndex = 0;

        int count = lists.length;
        for (int i = 1; i < count; i++) {
            if (i == forbiddenIndex) {
                continue;
            }

            List l = lists[i];
            if (l.size() > maxCount) {
                maxCount = l.size();
                bestIndex = i;
            }
        }

        return bestIndex == 0 ? forbiddenIndex : bestIndex;
    }

    private static int getNextMinColor(List[] lists, int forbiddenIndex) {
        int minCount = 5001;
        int bestIndex = 0;

        int count = lists.length;
        for (int i = 1; i < count; i++) {
            if (i == forbiddenIndex) {
                continue;
            }

            List l = lists[i];
            if (l.size() > 0 && l.size() < minCount) {
                minCount = l.size();
                bestIndex = i;
            }
        }

        return bestIndex == 0 ? forbiddenIndex : bestIndex;
    }

    private static void exchange(List<Integer> colors) {
        int count = colors.size();

        List<Integer> users = new ArrayList<Integer>(count);
        for (Integer color : colors) {
            users.add((Integer)colorUsers[color].remove(colorUsers[color].size() - 1));
        }

        for (int i = 0; i < count; i++) {
            int user = users.get(i);
            ans[user][0] = colors.get(i);
            ans[user][1] = colors.get((i + 1) % count);
        }
    }
}
