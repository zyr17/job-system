package xyz.swordfeng.jobsystem;

import org.rocksdb.RocksDBException;

public class TestMain {
    public static void main(String[] args) throws User.AuthFailure, User.UserExist {
        User u = User.register("swordfeng", "123456");
        User p = User.login("swordfeng", "123456");
        try {
            User r = User.login("swordfeng", "22");
        } catch (User.AuthFailure e) {
            System.out.println("expected");
        }
    }
}
