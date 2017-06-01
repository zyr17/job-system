package xyz.swordfeng.jobsystem;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.Serializable;

/**
 * Created by swordfeng on 17-5-29.
 */
public class DB {
    private static DB _instance = null;
    public static synchronized DB getInstance() {
        if (_instance == null) {
            _instance = new DB();
        }
        return _instance;
    }

    private RocksDB db;
    private DB() {
        RocksDB.loadLibrary();
        Options options = new Options().setCreateIfMissing(true);
        try {
            db = RocksDB.open(options, "data.db");
        } catch (RocksDBException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public <T> T get(@NotNull String key, Class<T> t) {
        try {
            String type = t.getName();
            byte[] k = (type + "/" + key).getBytes();
            byte[] v = db.get(k);
            if (v == null) return null;
            return SerializationUtils.deserialize(v);
        } catch (RocksDBException e) {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    public void put(@NotNull String key, @NotNull Serializable value) {
        String type = value.getClass().getName();
        byte[] k = (type + "/" + key).getBytes();
        byte[] v = SerializationUtils.serialize(value);
        try {
            db.put(k, v);
        } catch (RocksDBException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static class PersistentData implements Serializable {
        public final int id;
        public PersistentData() {
            DB db = DB.getInstance();
            String idName = this.getClass().getName() + "/Id";
            synchronized (Job.class) {
                Integer i = db.get(idName, Integer.class);
                if (i == null) {
                    i = 0;
                }
                id = i++;
                db.put(idName, i);
            }
        }
        public void save() {
            DB db = DB.getInstance();
            db.put(Integer.toString(id), this);
        }

        protected static <T> T get(int id, Class<T> t) {
            DB db = DB.getInstance();
            return db.get(Integer.toString(id), t);
        }
    }
}
