package hackerrank.algorithms.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by duviteck. 13 Jun 2014.
 */
public class SnakesAndLaddersTheQuickestWayUp {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());

        for (int i = 0; i < tests; i++) {
            Graph graph = new Graph();
            graph.readGraph(reader);
            graph.buildGraph();
            int ans = graph.getShortestWay();
            System.out.println(ans);
        }

        reader.close();
    }


    private static class Graph {
        private int[][] pipes;
        private int[] starts;
        private List<Integer>[] ways;

        public void readGraph(BufferedReader reader) throws IOException {
            int[] laddersAndSnakes = parseToken(reader.readLine());
            pipes = new int[laddersAndSnakes[0] + laddersAndSnakes[1]][];

            // parse ladders
            String[] tokens = reader.readLine().split(" ");
            for (int i = 0; i < laddersAndSnakes[0]; i++) {
                pipes[i] = parseToken(tokens[i]);
            }

            // parse snakes
            tokens = reader.readLine().split(" ");
            for (int i = 0; i < laddersAndSnakes[1]; i++) {
                pipes[i + laddersAndSnakes[0]] = parseToken(tokens[i]);
            }

            Arrays.sort(pipes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            starts = new int[pipes.length];
            for (int i = 0; i < starts.length; i++) {
                starts[i] = pipes[i][0];
            }
        }

        private int[] parseToken(String token) {
            String[] items = token.split(",");
            return new int[] {Integer.parseInt(items[0]), Integer.parseInt(items[1])};
        }

        public void buildGraph() {
            ways = new ArrayList[101];
            for (int i = 1; i < 101; i++) {
                ways[i] = new ArrayList<Integer>(6);
            }

            for (int i = 1; i < 100; i++) {
                List<Integer> list = ways[i];
                for (int j = 1; j <= 6; j++) {
                    int next = i + j;
                    if (next > 100) {
                        break;
                    }
                    int pipeIndex = Arrays.binarySearch(starts, next);
                    if (pipeIndex < 0) {
                        list.add(next);
                    } else {
                        list.add(pipes[pipeIndex][1]);
                    }
                }
            }
        }

        public int getShortestWay() {
            int[] shortestWay = new int[101];
            Arrays.fill(shortestWay, -1);
            shortestWay[1] = 0;

            int[] queue = new int[100];
            int queueSize = 1;
            queue[0] = 1;

            int curQueueIndex = 0;
            while (curQueueIndex < queueSize) {
                int curElem = queue[curQueueIndex];
                for (int next : ways[curElem]) {
                    if (shortestWay[next] == -1) {
                        shortestWay[next] = shortestWay[curElem] + 1;
                        queue[queueSize++] = next;

                        if (next == 100) {
                            return shortestWay[100];
                        }
                    }
                }

                curQueueIndex++;
            }

            return shortestWay[100];
        }
    }
}