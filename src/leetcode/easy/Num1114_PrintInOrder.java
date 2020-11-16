package leetcode.easy;

public class Num1114_PrintInOrder {
    private boolean firstDone = false;
    private boolean secondDone = false;

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public Num1114_PrintInOrder() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        synchronized (lock1) {
            firstDone = true;
            lock1.notify();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1) {
            if (!firstDone) {
                lock1.wait();
            }

            printSecond.run();
            synchronized (lock2) {
                secondDone = true;
                lock2.notify();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
            if (!secondDone) {
                lock2.wait();
            }

            printThird.run();
        }
    }
}
