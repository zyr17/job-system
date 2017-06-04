package xyz.swordfeng.jobsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JobCheckerTests {
    @Before
    public void initialize() throws User.AuthFailure, ValidationError {
        DB.getInstance().reset();
        try {
            User.register("user1", "password", 0);
        } catch (User.UserExist | User.MoneyBelowZero e) {
            e.printStackTrace();
        }
        String username = "user1";
        User user = User.login(username, "password");
        String name = "Programmer";
        String address = "China";
        String[] skills = new String[2];
        skills[0] = "Java";
        skills[1] = "Steam";
        String education = "College";
        Job job1 = new Job(user, name, address, 11, skills, education);
        job1.save();
        Job job2 = new Job(user, name + "2", address + "2", 12, skills, education);
        job2.save();
        Job job3 = new Job(user, name + "3", address + "3", 13, skills, education);
        job3.save();
        JobChecker.checkingJobPassed(true);
    }
    @Test
    public void getFirstCheckingJob() {
        Job job = JobChecker.getCheckingJob();
        assertTrue("Get first checking job", job.id == 1);
    }
    @Test
    public void setCheckingJobPassed() {
        Job job = JobChecker.getCheckingJob();
        JobChecker.checkingJobPassed(true);
        job = Job.get(job.id);
        assertTrue("Set first checking job passed", job.state == Job.PASS);
    }
    @Test
    public void setCheckingJobFailed() {
        Job job = JobChecker.getCheckingJob();
        JobChecker.checkingJobPassed(false);
        job = Job.get(job.id);
        assertTrue("Set first checking job failed", job.state == Job.FAIL);
    }
    @Test
    public void getSecondCheckingJob() {
        JobChecker.checkingJobPassed(true);
        Job job = JobChecker.getCheckingJob();
        assertTrue("Get second checking job", job.id == 2);
    }
    @Test
    public void setSecondCheckingJob() {
        JobChecker.checkingJobPassed(true);
        Job job = JobChecker.getCheckingJob();
        JobChecker.checkingJobPassed(true);
        job = Job.get(job.id);
        assertTrue("Get second checking job", job.state == Job.PASS);
    }
    @Test
    public void getNonexistCheckingJob() {
        JobChecker.checkingJobPassed(true);
        JobChecker.checkingJobPassed(true);
        Job job = JobChecker.getCheckingJob();
        assertTrue("Get non-exist checking job", job == null);
    }
    @Test
    public void setNonexistCheckingJob() {
        JobChecker.checkingJobPassed(true);
        JobChecker.checkingJobPassed(true);
        boolean result = JobChecker.checkingJobPassed(true);
        assertTrue("Get non-exist checking job", result == false);
    }
}
