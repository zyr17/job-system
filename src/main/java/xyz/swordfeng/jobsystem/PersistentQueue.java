package xyz.swordfeng.jobsystem;

import java.util.LinkedList;
import java.util.Queue;

abstract public class PersistentQueue<T> extends DB.PersistentData {
    private Queue<T> queue = new LinkedList();
    public void push(T v) {
        queue.add(v);
    }
    public T pop() {
        return queue.remove();
    }
}
