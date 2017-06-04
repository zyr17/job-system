package xyz.swordfeng.jobsystem;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.io.FileUtils;
import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;

import java.io.File;
import java.io.IOException;
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

    private org.iq80.leveldb.DB db;
    private Options options;
    private DB() {
        options = new Options().createIfMissing(true);
        try {
            db = factory.open(new File("data.db"), options);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void reset() {
        try {
            db.close();
            FileUtils.deleteDirectory(new File("data.db"));
            db = factory.open(new File("data.db"), options);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public <T> T get(@NotNull String key, Class<T> t) {
        String type = t.getName();
        byte[] k = (type + "/" + key).getBytes();
        byte[] v = db.get(k);
        if (v == null) return null;
        return SerializationUtils.deserialize(v);
    }

    public void put(@NotNull String key, @NotNull Serializable value) {
        String type = value.getClass().getName();
        byte[] k = (type + "/" + key).getBytes();
        byte[] v = SerializationUtils.serialize(value);
        db.put(k, v);
    }

    public <T> void delete(@NotNull String key, Class<T> t) {
        String type = t.getName();
        byte[] k = (type + "/" + key).getBytes();
        db.delete(k);
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

        protected static <T> int getNextId(Class<T> t) {
            DB db = DB.getInstance();
            String idName = t.getName() + "/Id";
            Integer i = db.get(idName, Integer.class);
            if (i == null) {
                i = 0;
            }
            return i;
        }

        protected static <T> T get(int id, Class<T> t) {
            DB db = DB.getInstance();
            return db.get(Integer.toString(id), t);
        }
    }
}
