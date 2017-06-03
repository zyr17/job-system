package xyz.swordfeng.jobsystem;

public class JobChecker {
    public static Job getCheckingJob(){
        DB db = DB.getInstance();
        int totalJobNum = db.get(Job.class.getName() + "/Id", Integer.class);
        for (int i = 0; i < totalJobNum; i ++ ){
            Job job = Job.get(i);
            if (job.state == Job.CHECKING)
                return job;
        }
        return null;
    }
    public static boolean checkingJobPassed(boolean passed){
        DB db = DB.getInstance();
        int totalJobNum = db.get(Job.class.getName() + "/Id", Integer.class);
        for (int i = 0; i < totalJobNum; i ++ ){
            Job job = Job.get(i);
            if (job.state == Job.CHECKING){
                if (passed)
                    job.state = Job.PASS;
                else
                    job.state = Job.FAIL;
                job.save();
                return true;
            }
        }
        return false;
    }
}
