package codeforces.vk_cup_2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * http://codeforces.ru/contest/523/problem/D
 *
 * Created by duviteck on 14/03/15.
 */
public class Problem_D {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        int[] temp = parsePair(reader);
        int n = temp[0];
        int k = temp[1];
        PriorityQueue<Long> serverQueue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            serverQueue.add(0L);
        }

        for (int i = 0; i < n; i++) {
            temp = parsePair(reader);
            long s = temp[0];
            long m = temp[1];
            long nearestServer = serverQueue.poll();
            long ans = Math.max(s, nearestServer) + m;
            serverQueue.add(ans);

            writer.println(ans);
        }
        writer.flush();
    }

    private static int[] parsePair(BufferedReader reader) throws IOException {
        int[] ans = new int[2];
        String[] tokens = reader.readLine().split(" ");
        ans[0] = Integer.parseInt(tokens[0]);
        ans[1] = Integer.parseInt(tokens[1]);
        return ans;
    }
}
