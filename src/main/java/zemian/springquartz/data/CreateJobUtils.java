package zemian.springquartz.data;

import org.quartz.*;

public class CreateJobUtils {

    public static JobDetail toJobDetail(JobDetailData jobDetailData) throws Exception {
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobDetailData.getJobClassName());
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(jobClass).
                        withIdentity(jobDetailData.getName(), jobDetailData.getGroup()).
                        build();
        return jobDetail;
    }

    public static void autoSetTriggerNames(KeyData triggerData, JobDetailData jobDetailData) throws Exception {
        if (triggerData.getName() == null) {
            triggerData.setName(jobDetailData.getName());
        }
        if (triggerData.getGroup() == null) {
            triggerData.setGroup(jobDetailData.getGroup());
        }
    }
}
