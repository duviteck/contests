package facebook_hacker_cup.year_2015.round_1;

import java.io.*;
import java.util.*;

/**
 * Created by duviteck. 18 Jan 2015.
 *
 * The fine people of Corpro Corp. are a festive bunch. Every holiday season, everybody buys a gift for their manager.
 * A cynic might say that the employees are just trying to bribe their way to a better performance review,
 * but if you asked them yourself, they'd say they just wanted to spread cheer.
 *
 * The fine people of Corpro Corp. are a frugal bunch. When they buy gifts, they cooperate to collectively buy
 * the least expensive gifts that they can. A cynic might say that the employees are cheap,
 * but if you asked them yourself, they'd say it's the thought that counts.
 *
 * There are N employees working at Corpro Corp., and each of them has a manager, except for the CEO who has no manager
 * (the CEO also buys a gift every year, but she donates it to charity). The employees each have
 * a unique employee ID which is an integer from 1 to N. As you might expect, the CEO has the ID 1.
 *
 * If there exists a set of two or more employees {p1, ..., pk} such that, for all i < k, pi is the manager of pi+1,
 * then we say that p1 is "responsible for" pk. There are never two employees who are responsible for each other.
 * That would be a silly hierarchy indeed.
 *
 * There are N kinds of gifts available for purchase, and the ith kind of gift costs i dollars.
 * That is, the prices of the different kinds of gifts are {$1, $2, $3, ... $N}.
 * There are N copies of each gift available for purchase.
 *
 * The only thing that stops all employees from purchasing gifts that cost $1 is the awkwardness of buying a gift
 * for their manager that's the same as the one their manager is giving away. No employee would ever do such a thing!
 *
 * For example, in a company with just 2 employees, at least $3 must be spent in total. If employee #1 (the CEO)
 * buys a $1 gift to donate to charity, then employee #2 cannot buy a $1 gift for employee #1 (their manager),
 * but they can buy a $2 gift instead. Note that it would be equally optimal for the CEO to buy a $2 gift,
 * while receiving a $1 gift from her subordinate.
 *
 * What's the minimum possible total expenditure across the whole company during the gift exchange?
 *
 * Input
 * Input begins with an integer T, the number of corporate hierarchies to consider. Each hierarchy is made up
 * of two lines. The first line contains the integer N. The second line contains N space-separated integers.
 * The ith integer is the employee ID of the manager of employee i, with the exception that the first integer
 * is always 0, denoting that the CEO has no manager.
 *
 * Output
 * For the ith hierarchy, print a line containing "Case #i: " followed by the smallest amount of money
 * the entire company would need to spend.
 *
 * Constraints
 * 1 ≤ T ≤ 100
 * 1 ≤ N ≤ 200,000
 */
public class Problem_D_CorporateGifting {

    private static final String FILE_IN = "in.txt";
    private static final String FILE_OUT = "out.txt";

    private static long ans;
    private static Deque<Node> traverseQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        MyReader reader = new MyReader(new FileInputStream(new File(FILE_IN)));
        PrintWriter writer = new PrintWriter(new File(FILE_OUT));

        int tests = reader.nextInt();
        for (int i = 1; i <= tests; i++) {
            int n = reader.nextInt();
            int[] ar = new int[n];
            for (int j = 0; j < n; j++) {
                ar[j] = reader.nextInt();
            }

            calcAns(ar);
            writer.format("Case #%d: %d\n", i, ans);
        }
        writer.flush();
    }

    private static void calcAns(int[] input) {
        ans = 0;

        initQueue(input);

        while (traverseQueue.size() > 0) {
            Node node = traverseQueue.pollFirst();
            processNode(node);
        }
    }

    private static void initQueue(int[] input) {
        traverseQueue.clear();

        int n = input.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }

        for (int i = 1; i < n; i++) {
            nodes[i].addParent(nodes[input[i] - 1]);
        }

        for (Node node : nodes) {
            if (node.waitForProcess == 0) {
                traverseQueue.addLast(node);
            }
        }
    }

    private static void processNode(Node node) {
        if (node.children.isEmpty()) {
            node.optimalValue = 1;
            node.changeCost = 1;
        } else {
            long nonExist = findNonExist(node.children.keySet());
            List<Pair> changeCosts = calcChangeCosts(node.children);

            // define optimal
            Triplet bestChange = findBestChange(changeCosts);   // TODO: can de slowed, but become more readable
            long bestChangeCost = bestChange.value + bestChange.changeCost;
            if (nonExist < bestChangeCost) {
                node.optimalValue = nonExist;
            } else if (nonExist > bestChangeCost) {
                ans += bestChange.changeCost;
                node.optimalValue = bestChange.value;
                if (bestChange.isMultiple) {
                    node.changeCost = 0;
                }
            } else {
                node.optimalValue = nonExist;
                node.changeCost = 0;
            }

            // define changeCost
            defineChangeCost(node, changeCosts);
        }

        ans += node.optimalValue;
        node.registerChildForParent();
    }

    private static long findNonExist(Set<Long> existSet) {
        long size = existSet.size();
        for (long i = 1; i <= size; i++) {
            if (!existSet.contains(i)) {
                return i;
            }
        }
        return size + 1;
    }

    private static List<Pair> calcChangeCosts(Map<Long, List<Node>> children) {
        List<Pair> res = new ArrayList<>(children.size());
        for (Map.Entry<Long, List<Node>> entry : children.entrySet()) {
            long changeCost = 0;
            for (Node node : entry.getValue()) {
                changeCost += node.changeCost;
            }
            res.add(new Pair(entry.getKey(), changeCost));
        }
        return res;
    }

    private static Triplet findBestChange(List<Pair> changeCosts) {
        Triplet res = new Triplet(0, 0, false);
        long bestCost = Long.MAX_VALUE;
        for (Pair pair : changeCosts) {
            long cost = pair.value + pair.changeCost;
            if (res.value == 0 || bestCost > cost) {
                res.value = pair.value;
                res.changeCost = pair.changeCost;
                res.isMultiple = false;
                bestCost = cost;
            } else if (bestCost == cost) {
                res.isMultiple = true;
            }
        }
        return res;
    }

    private static void defineChangeCost(Node node, List<Pair> changeCosts) {
        if (node.changeCost > -1) {
            return;
        }

        HashSet<Long> temp = new HashSet<>(node.children.keySet());
        temp.add(node.optimalValue);
        long bestChangeCost = findNonExist(temp) - node.optimalValue;   // TODO: should be > 0

        for (Pair pair : changeCosts) {
            long newChangeCost = pair.value - node.optimalValue + pair.changeCost;
            if (newChangeCost < bestChangeCost) {
                bestChangeCost = newChangeCost;
            }
        }
        node.changeCost = bestChangeCost;
    }


    private static class Node {
        long optimalValue;
        long changeCost = -1;
        Map<Long, List<Node>> children = new HashMap<>();
        Node parent;
        int waitForProcess;

        public void registerChildForParent() {
            if (parent != null) {
                Map<Long, List<Node>> parentChildren = parent.children;
                List<Node> valueChildren = parentChildren.get(optimalValue);
                if (valueChildren == null) {
                    valueChildren = new ArrayList<>();
                    valueChildren.add(this);
                    parentChildren.put(optimalValue, valueChildren);
                } else {
                    valueChildren.add(this);
                }

                parent.waitForProcess--;
                if (parent.waitForProcess == 0) {
                    traverseQueue.addLast(parent);
                }
            }
        }

        public void addParent(Node parent) {
            this.parent = parent;
            parent.waitForProcess++;
        }
    }


    private static class Pair {
        long value;
        long changeCost;

        public Pair(long value, long changeCost) {
            this.value = value;
            this.changeCost = changeCost;
        }
    }


    private static class Triplet {
        long value;
        long changeCost;
        boolean isMultiple;

        public Triplet(long value, long changeCost, boolean isMultiple) {
            this.value = value;
            this.changeCost = changeCost;
            this.isMultiple = isMultiple;
        }
    }


    private static class MyReader {
        BufferedInputStream in;

        final int bufSize = 1 << 16;
        final byte b[] = new byte[bufSize];

        MyReader(InputStream in) {
            this.in = new BufferedInputStream(in, bufSize);
        }

        int nextInt() throws IOException {
            int c;
            while ((c = nextChar()) <= 32);

            int x = 0, sign = 1;
            if (c == '-') {
                sign = -1;
                c = nextChar();
            }
            while (c >= '0') {
                x = x * 10 + (c - '0');
                c = nextChar();
            }
            return x * sign;
        }

        StringBuilder _buf = new StringBuilder();

        String nextWord() throws IOException {
            int c;
            _buf.setLength(0);
            while ((c = nextChar()) <= 32)
                ;
            while (c > 32) {
                _buf.append((char) c);
                c = nextChar();
            }
            return _buf.toString();
        }

        int bn = bufSize, k = bufSize;

        int nextChar() throws IOException {
            if (bn == k) {
                k = in.read(b, 0, bufSize);
                bn = 0;
            }
            return bn >= k ? -1 : b[bn++];
        }

        int nextNotSpace() throws IOException {
            int ch;
            while ((ch = nextChar()) <= 32 && ch != -1)
                ;
            return ch;
        }
    }
}
