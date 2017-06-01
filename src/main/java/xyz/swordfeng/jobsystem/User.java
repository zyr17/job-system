package xyz.swordfeng.jobsystem;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;

/**
 * Created by swordfeng on 17-5-29.
 */
public final class User implements Serializable {

    public static class AuthFailure extends Exception {
        public AuthFailure() {
            super("User authentication failed");
        }
    }
    public static class UserExist extends Exception {
        public UserExist() {
            super("User has already existed");
        }
    }
    public static @NotNull User login(@NotNull String username, @NotNull String password) throws AuthFailure {
        User user = DB.getInstance().get(username, User.class);
        if (user == null || !password.equals(user.password)) {
            throw new AuthFailure();
        }
        return user;
    }
    public static @NotNull User register(@NotNull String username, @NotNull String password) throws UserExist {
        User user = new User(username, password);
        user.password = password;
        synchronized (User.class) {
            if (DB.getInstance().get(username, User.class) == null) {
                DB.getInstance().put(username, user);
            } else {
                throw new UserExist();
            }
        }
        return user;
    }

    private User(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public final String username;
    private String password;
}
