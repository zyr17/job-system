package xyz.swordfeng.jobsystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PersistentQueue<T> {
    private final String name;

    private PersistentQueue(String name) {
        this.name = name;
    }

    public void add(T v) {
        synchronized (PersistentQueue.class) {
            DB db = DB.getInstance();
            LinkedList<T> q = db.get("Persistent/" + name, LinkedList.class);
            if (q == null) q = new LinkedList<>();
            q.add(v);
            db.put("Persistent/" + name, q);
        }
    }
    public T poll() {
        DB db = DB.getInstance();
        LinkedList<T> q = db.get("Persistent/" + name, LinkedList.class);
        if (q == null) q = new LinkedList<>();
        T result = q.poll();
        db.put("Persistent/" + name, q);
        return result;
    }
    public List<T> getList() {
        DB db = DB.getInstance();
        LinkedList<T> q = db.get("Persistent/" + name, LinkedList.class);
        if (q == null) q = new LinkedList<>();
        return q;
    }

    public static <T> PersistentQueue<T> get(String name) {
        return new PersistentQueue<T>(name);
    }
}
