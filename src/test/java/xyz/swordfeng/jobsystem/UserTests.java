package xyz.swordfeng.jobsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserTests {
    @Before
    public void initialize() {
        DB.getInstance().reset();
        try {
            User.register("user1", "password", 0);
        } catch (User.UserExist userExist) {
            userExist.printStackTrace();
        } catch (User.MoneyBelowZero moneyBelowZero) {
            moneyBelowZero.printStackTrace();
        }
    }
    @Test
    public void createUser() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 0);
        assertTrue("Create a user", "user2".equals(user.username));
    }
    @Test(expected = User.UserExist.class)
    public void createExistingUser() throws User.UserExist, User.MoneyBelowZero {
        User.register("user1", "xyz", 0);
        fail("should throw UserExist exception");
    }
    @Test(expected = User.MoneyBelowZero.class)
    public void negativeMoney() throws User.UserExist, User.MoneyBelowZero {
        User.register("user2", "password2", -1);
        fail("should throw MoneyBelowZero exception");
    }

    @Test
    public void login() throws User.AuthFailure {
        User user = User.login("user1", "password");
        assertTrue("login", "user1".equals(user.username));
    }
    @Test(expected = User.AuthFailure.class)
    public void loginWrongPassword() throws User.AuthFailure {
        User user = User.login("user1", "xyz");
        fail("should throw AuthFailure exception");
    }
    @Test(expected = User.AuthFailure.class)
    public void loginNonexistUser() throws User.AuthFailure {
        User user = User.login("user2", "password2");
        fail("should throw AuthFailure exception");
    }

    @Test
    public void registerWithMoney0() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 0);
        assertTrue("Create a vip0 user", user.vipLevel == 0);
    }
    @Test
    public void registerWithMoney99() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 99);
        assertTrue("Create a vip0 user", user.vipLevel == 0);
    }
    @Test
    public void registerWithMoney100() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 100);
        assertTrue("Create a vip0 user", user.vipLevel == 1);
    }
    @Test
    public void registerWithMoney9999() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 9999);
        assertTrue("Create a vip0 user", user.vipLevel == 1);
    }
    @Test
    public void registerWithMoney10000() throws User.UserExist, User.MoneyBelowZero {
        User user = User.register("user2", "password2", 10000);
        assertTrue("Create a vip0 user", user.vipLevel == 2);
    }
}
