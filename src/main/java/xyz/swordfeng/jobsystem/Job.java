package xyz.swordfeng.jobsystem;

/**
 * Created by swordfeng on 17-5-29.
 */
public final class Job extends DB.PersistentData {

    public static final String[] VALID_EDUCATION = new String[]{
            "小学", "初中", "高中", "大学"
    };

    public final String username;
    private String name;
    private String address;
    private int requiredNumOfPeople;
    private String[] skills;
    private String education;

    public static final int CHECKING = 0;
    public static final int PASS = 1;
    public static final int FAIL = 2;

    public int state;

    public Job(User user, String name, String address, int requiredNumOfPeople, String[] skills, String education) throws ValidationError {
        super();
        this.username = user.username;
        this.state = CHECKING;
        setName(name);
        setAddress(address);
        setRequiredNumOfPeople(requiredNumOfPeople);
        setSkills(skills);
        setEducation(education);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationError {
        if (name == null) {
            throw new ValidationError("Null name");
        }
        if (name.length() >= 20) {
            throw new ValidationError("Job name too long");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws ValidationError {
        if (address == null) {
            throw new ValidationError("Null address");
        }
        if (name.length() >= 200) {
            throw new ValidationError("Address too long");
        }
        this.address = address;
    }

    public int getRequiredNumOfPeople() {
        return requiredNumOfPeople;
    }

    public void setRequiredNumOfPeople(int requiredNumOfPeople) throws ValidationError {
        if (requiredNumOfPeople <= 0) {
            throw new ValidationError("Zero or negate number of people");
        }
        this.requiredNumOfPeople = requiredNumOfPeople;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) throws ValidationError {
        if (skills == null) {
            throw new ValidationError("Null skills");
        }
        if (skills.length == 0) {
            throw new ValidationError("Zero length skills");
        }
        for (String skill: skills) {
            if (skill == null) {
                throw new ValidationError("Null skill");
            } else if (skill.length() == 0 && skills.length > 1) {
                throw new ValidationError("Contain empty skill with non-empty skills");
            } else if (skill.length() > 20) {
                throw new ValidationError("Skill name too long");
            }
        }
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) throws ValidationError {
        boolean valid = false;
        for (String vedu: VALID_EDUCATION) {
            if (vedu.equals(education)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new ValidationError("Invalid education");
        }
        this.education = education;
    }

    public static Job get(int id) {
        return get(id, Job.class);
    }
    @Override
    public void save() {
        super.save();
    }
}
