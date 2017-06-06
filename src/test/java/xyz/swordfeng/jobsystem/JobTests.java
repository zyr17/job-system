package xyz.swordfeng.jobsystem;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JobTests {
    @Before
    public void initialize() {
        DB.getInstance().reset();
        try {
            User.register("user1", "password", 0);
        } catch (User.UserExist | User.MoneyBelowZero e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createJob() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
        assertTrue("Create a job", username.equals(job.username) && job.getName().equals(name) &&
                    job.getAddress().equals(address) && job.getRequiredNumOfPeople() == 10 &&
                    Arrays.equals(job.getSkills(), skills) && job.getEducation().equals(education));
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullName() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = null;
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongName() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "averyveryveryverylongname";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullAddress() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = null;
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongAddress() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "1111234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithZeroPerson() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, 0, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNegativePerson() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job = new Job(user, name, address, -1, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithZeroSkills() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[0];
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullSkills() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = null;
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullSkill() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = null;
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test
    public void createJobWithZeroSkill() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithOneEmptySkill() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongSkill() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "averyveryveryverylongskill";
        String education = "College";
        Job job = new Job(user, name, address, 10, skills, education);
    }
    @Test(expected = ValidationError.class)
    public void createJobWithIncorrectEducation() throws ValidationError, User.AuthFailure {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "Java";
        String education = "kindergarten";
        Job job = new Job(user, name, address, 10, skills, education);
    }
}
