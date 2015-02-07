package leetcode.medium;

import java.util.*;

/**
 * Created by duviteck. 07 Feb 2015.
 */
public class CloneGraph {

    private Deque<UndirectedGraphNode> queue;
    private Map<Integer, UndirectedGraphNode> cloneMap;

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {
        if (root == null) {
            return null;
        }

        queue = new LinkedList<>();
        cloneMap = new HashMap<>();

        UndirectedGraphNode rootClone = cloneNode(root);
        while (queue.size() > 0) {
            UndirectedGraphNode node = queue.poll();
            cloneNodeNeighbors(node);
        }

        return rootClone;
    }

    private UndirectedGraphNode cloneNode(UndirectedGraphNode node) {
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        cloneMap.put(node.label, clone);
        queue.push(node);

        return clone;
    }

    private void cloneNodeNeighbors(UndirectedGraphNode node) {
        UndirectedGraphNode clone = cloneMap.get(node.label);

        for (UndirectedGraphNode neigh : node.neighbors) {
            UndirectedGraphNode neighClone = cloneMap.get(neigh.label);
            if (neighClone == null) {
                neighClone = cloneNode(neigh);
            }
            clone.neighbors.add(neighClone);
        }
    }


    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}
