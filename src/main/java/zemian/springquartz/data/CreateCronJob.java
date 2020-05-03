package zemian.springquartz.data;

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
}
