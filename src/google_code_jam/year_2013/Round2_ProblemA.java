package google_code_jam.year_2013;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Dudin Victor (duviteck@gmail.com)
 * SPbSU, Faculty of Mathematics and Mechanics
 * 2013 year
 */
public class Round2_ProblemA {
    private static final String INPUT_FILENAME = "input.in";
    private static final String OUTPUT_FILENAME = "output.txt";
    private static final long MODULO = 1000002013;

    private static long n;
    private static long initCost;
    private static long realCost;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(INPUT_FILENAME));
        PrintWriter writer = new PrintWriter(OUTPUT_FILENAME);

        int numOfTests = s.nextInt();

        for (int i = 1; i <= numOfTests; i++) {
            n = s.nextInt();
            int m = s.nextInt();
            Task[] tasks = new Task[m * 2];
            initCost = 0;

            for (int j = 0; j < m; j++) {
                int begin = s.nextInt();
                int end = s.nextInt();
                int count = s.nextInt();
                tasks[j * 2] = new Task(begin, count, 0);
                tasks[j * 2 + 1] = new Task(end, count, 1);
                initCost += calcCost(begin, end, count);
            }
            Arrays.sort(tasks, new TaskComparator());
            processTasks(tasks);

            long ans = calcAns();
            writer.println("Case #" + i + ": " + ans);
        }

        s.close();
        writer.close();
    }

    private static long calcCost(int s1, int s2, int count) {
        int way = Math.abs(s1 - s2);
        long cost = (2 * n - way + 1) * way / 2;
        return (cost % MODULO * count) % MODULO;
    }

    private static void processTasks(Task[] tasks) {
        realCost = 0;

        Stack<Task> stack = new Stack<Task>();
        for (Task t : tasks) {
            if (t.type == 0) {
                stack.push(t);
            } else {
                while (t.count > 0) {
                    Task top = stack.peek();
                    int processCount = Math.min(top.count, t.count);
                    realCost += calcCost(t.stage, top.stage, processCount);
                    t.count -= processCount;
                    top.count -= processCount;
                    if (top.count == 0) {
                        stack.pop();
                    }
                }
            }
        }
    }

    private static long calcAns() {
        long ans = (initCost - realCost) % MODULO;
        if (ans < 0) {
            ans += MODULO;
        }
        return ans;
    }


    private static class Task {
        int stage;
        int count;
        int type;   // 0 - enter, 1 - leave

        public Task(int stage, int count, int type) {
            this.stage = stage;
            this.count = count;
            this.type = type;
        }
    }


    private static class TaskComparator implements Comparator<Task> {

        @Override
        public int compare(Task t1, Task t2) {
            if (t1.stage != t2.stage) {
                return (t1.stage < t2.stage) ? -1 : 1;
            } else if (t1.type != t2.type) {
                return (t1.type < t2.type) ? -1 : 1;
            } else {
                return 0;
            }
        }
    }
}
