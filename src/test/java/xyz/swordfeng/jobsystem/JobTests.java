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
        } catch (User.UserExist userExist) {
            userExist.printStackTrace();
        } catch (User.MoneyBelowZero moneyBelowZero) {
            moneyBelowZero.printStackTrace();
        }
    }
    @Test
    public void createJob() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        //TODO I don't know whether we can use Arrays.equals to compare two String arrays
        assertTrue("Create a job", username.equals(job.username) && job.getName().equals(name) &&
                    job.getAddress().equals(address) && job.getRequiredNumOfPeople() == 10 &&
                    Arrays.equals(job.getSkills(), skills) && job.getEducation().equals(education));
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullName() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = null;
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongName() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "averyveryveryverylongname";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullAddress() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = null;
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongAddress() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "1111234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithZeroPerson() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, 0, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNegativePerson() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "大学";
        Job job = new Job(user, name, address, -1, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithZeroSkills() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[0];
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullSkills() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = null;
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithNullSkill() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = null;
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test
    public void createJobWithZeroSkill() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithOneEmptySkill() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithTooLongSkill() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "averyveryveryverylongskill";
        String education = "大学";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
    @Test(expected = ValidationError.class)
    public void createJobWithIncorrectEducation() throws ValidationError {
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[1];
        skills[0] = "Java";
        String education = "幼儿园";
        Job job = new Job(user, name, address, 10, skills, education);
        fail("should throw ValidationError exception");
    }
}
