package leetcode.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by duviteck. 05 Dec 2014.
 */
public class LRUCache {
    int capacity;
    HashMap<Integer, Pair> map;
    TreeSet<Pair> set;
    long curTime = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        set = new TreeSet(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.time < p2.time ? -1 : p1.time > p2.time ? 1 : 0;
            }
        });
    }

    public int get(int key) {
        Pair curPair = map.get(key);
        if (curPair == null) {
            return -1;
        } else {
            // update time of existing
            set.remove(new Pair(key, curPair.time));
            set.add(new Pair(key, curTime));
            map.put(key, new Pair(curPair.val, curTime));
            curTime++;
            return curPair.val;
        }
    }

    public void set(int key, int value) {
        Pair curPair = map.get(key);
        if (curPair == null) {
            // add new element
            if (set.size() == capacity) {
                // need to remove latest
                Pair firstPair = set.first();
                set.remove(set.first());
                map.remove(firstPair.val);
            }
        } else {
            // just update time of existing
            set.remove(new Pair(key, curPair.time));
        }
        map.put(key, new Pair(value, curTime));
        set.add(new Pair(key, curTime));
        curTime++;
    }


    private static class Pair {
        int val;
        long time;

        public Pair(int val, long time) {
            this.val = val;
            this.time = time;
        }
    }
}
