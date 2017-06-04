package xyz.swordfeng.jobsystem;

public class TestMain {
    public static void main(String[] args) throws User.AuthFailure, User.UserExist, User.MoneyBelowZero {
        DB.getInstance().reset();
        User u = User.register("swordfeng", "123456", Integer.MIN_VALUE);
        User p = User.login("swordfeng", "123456");
        try {
            User r = User.login("swordfeng", "22");
        } catch (User.AuthFailure e) {
            System.out.println("expected");
        }
    }
}
