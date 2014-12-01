package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duviteck. 02 Dec 2014.
 */
class MinStack {
    private List<ValObject> stack = new ArrayList<>();
    private int minVal = Integer.MAX_VALUE;

    public void push(int x) {
        ValObject newElem;
        if (minVal > x) {
            newElem = new Pair(x, minVal);
            minVal = x;
        } else {
            newElem = new ValObject(x);
        }
        stack.add(newElem);
    }

    public void pop() {
        if (stack.size() > 0) {
            Object top = stack.remove(stack.size() - 1);
            if (top instanceof Pair) {
                minVal = ((Pair) top).min;
            }
        }
    }

    public int top() {
        if (stack.size() > 0) {
            return stack.get(stack.size() - 1).val;
        }
        return 0;
    }

    public int getMin() {
        return minVal;
    }


    private class ValObject {
        public int val;

        public ValObject(int val) {
            this.val = val;
        }
    }


    private class Pair extends ValObject {
        public int min;

        public Pair(int val, int min) {
            super(val);
            this.min = min;
        }
    }
}