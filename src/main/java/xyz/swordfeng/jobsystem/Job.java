package xyz.swordfeng.jobsystem;

/**
 * Created by swordfeng on 17-5-29.
 */
public final class Job extends DB.PersistentData {
    public final String username;
    public String name;
    public String address;
    public int requiredNumOfPeople;
    public String skills;
    public String education;

    public Job(User user) {
        super();
        this.username = user.username;
    }

    public static Job get(int id) {
        return get(id, Job.class);
    }
}
