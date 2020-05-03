package zemian.springquartz.data;

import org.quartz.*;

public class CreateSimpleJob {
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
        int repeatCount;
        long repeatInterval;

        public int getRepeatCount() {
            return repeatCount;
        }

        public void setRepeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
        }

        public long getRepeatInterval() {
            return repeatInterval;
        }

        public void setRepeatInterval(long repeatInterval) {
            this.repeatInterval = repeatInterval;
        }
    }

    public JobDetail toJobDetail() throws Exception {
        return CreateJobUtils.toJobDetail(getJobDetail());
    }

    public Trigger toTrigger() throws Exception {
        CreateSimpleJob.TriggerData triggerData = getTrigger();
        CreateJobUtils.autoSetTriggerNames(triggerData, getJobDetail());
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule().
                withIntervalInMilliseconds(triggerData.getRepeatInterval()).
                withRepeatCount(triggerData.getRepeatCount());
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(triggerData.getName(), triggerData.getGroup()).
                withSchedule(schedule).
                build();
        return trigger;
    }
}
