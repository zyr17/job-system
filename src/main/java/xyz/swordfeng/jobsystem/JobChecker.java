package xyz.swordfeng.jobsystem;

public final class JobChecker {
    public static Job getCheckingJob(){
        int totalJobNum = Job.getNextId();
        for (int i = 0; i < totalJobNum; i ++ ){
            Job job = Job.get(i);
            if (job == null) continue;
            if (job.state == Job.CHECKING)
                return job;
        }
        return null;
    }
    public static boolean checkingJobPassed(boolean passed){
        int totalJobNum = Job.getNextId();
        for (int i = 0; i < totalJobNum; i ++ ){
            Job job = Job.get(i);
            if (job == null) continue;
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
