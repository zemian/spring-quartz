package zemian.springquartz.data;

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
}
