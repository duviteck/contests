package codeforces.vk_cup_2015.round_1;

import java.util.*;

/**
 * Created by duviteck on 21/03/15.
 */
public class Problem_A {

    private static Map<Integer, List<Integer>> con = new HashMap<>(100);
    private static Set<Integer> friendsOfFriends = new HashSet<>(100);

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int m = s.nextInt();
        float k = s.nextInt() * 0.01f;

        for (int i = 0; i < m; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            List<Integer> conA = con.get(a);
            if (conA == null) {
                conA = new ArrayList<>(m);
                con.put(a, conA);
            }
            conA.add(b);

            List<Integer> conB = con.get(b);
            if (conB == null) {
                conB = new ArrayList<>(m);
                con.put(b, conB);
            }
            conB.add(a);
        }

        for (List<Integer> list : con.values()) {
            Collections.sort(list);
        }

        List<Integer> nodes = new ArrayList<>(con.keySet());
        Collections.sort(nodes);

        for (Integer start : nodes) {
            List<Integer> myFriends = con.get(start);
            Set<Integer> possible = new HashSet<>(nodes);
            possible.remove(start);
            possible.removeAll(myFriends);

            List<Integer> possibleFriends = new ArrayList<>(possible.size());
            for (Integer friend : possible) {
                int common = getCommonFriendsCount(myFriends, con.get(friend));
                if (common >= myFriends.size() * k) {
                    possibleFriends.add(friend);
                }
            }

            Collections.sort(possibleFriends);
            printRes(start, possibleFriends);
        }



//        if (k > 0) {
//            for (Integer start : nodes) {
//                friendsOfFriends.clear();
//                List<Integer> myFriends = con.get(start);
//
//                for (Integer friend : myFriends) {
//                    friendsOfFriends.addAll(con.get(friend));
//                }
//                friendsOfFriends.remove(start);
//                friendsOfFriends.removeAll(myFriends);
//
//                List<Integer> possibleFriends = new ArrayList<>(friendsOfFriends.size());
//                for (Integer friend : friendsOfFriends) {
//                    int common = getCommonFriendsCount(myFriends, con.get(friend));
//                    if (common >= myFriends.size() * k) {
//                        possibleFriends.add(friend);
//                    }
//                }
//
//                printRes(start, possibleFriends);
//            }
//        } else {
//            for (Integer start : nodes) {
//                Set<Integer> possible = new HashSet<>(nodes);
//                possible.remove(start);
//                possible.removeAll(con.get(start));
//
//                List<Integer> possibleFriends = new ArrayList<>(possible);
//                Collections.sort(possibleFriends);
//                printRes(start, possibleFriends);
//            }
//        }
    }

    private static void printRes(Integer start, List<Integer> possibleFriends) {
        StringBuilder sb = new StringBuilder();
        sb.append(start).append(": ").append(possibleFriends.size());
        for (Integer i : possibleFriends) {
            sb.append(" ").append(i);
        }
        System.out.println(sb.toString());
    }

    private static int getCommonFriendsCount(List<Integer> myFriends, List<Integer> friends) {
        int i1 = 0;
        int i2 = 0;
        int l1 = myFriends.size();
        int l2 = friends.size();
        int res = 0;

        while (i1 < l1 && i2 < l2) {
            int c1 = myFriends.get(i1);
            int c2 = friends.get(i2);
            if (c1 == c2) {
                res++;
                i1++;
                i2++;
            } else if (c1 < c2) {
                i1++;
            } else {
                i2++;
            }
        }

        return res;
    }
}
