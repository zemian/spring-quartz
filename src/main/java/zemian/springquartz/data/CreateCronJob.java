package zemian.springquartz.data;

import org.quartz.*;

public class CreateCronJob {
    JobDetailData jobDetail;
    TriggerData trigger;

    public JobDetailData getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(JobDetailData jobDetail) {
        this.jobDetail = jobDetail;
    }

    public TriggerData getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerData trigger) {
        this.trigger = trigger;
    }

    public static class TriggerData extends KeyData {
        String cron;

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }
    }

    public JobDetail toJobDetail() throws Exception {
        return CreateJobUtils.toJobDetail(getJobDetail());
    }

    public Trigger toTrigger() throws Exception {
        CreateCronJob.TriggerData triggerData = getTrigger();
        CreateJobUtils.autoSetTriggerNames(triggerData, getJobDetail());
        CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule(triggerData.getCron());
        CreateJobUtils.autoSetTriggerNames(triggerData, getJobDetail());
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(triggerData.getName(), triggerData.getGroup()).
                withSchedule(schedule).
                build();
        return trigger;
    }
}
