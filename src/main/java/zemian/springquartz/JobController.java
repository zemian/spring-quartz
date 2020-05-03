package zemian.springquartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zemian.springquartz.data.CreateCronJob;
import zemian.springquartz.data.CreateSimpleJob;
import zemian.springquartz.data.JobDetailData;

import java.util.Date;
import java.util.Set;

@CrossOrigin
@RestController
public class JobController {
    private static Logger LOG = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private Scheduler scheduler;

    @GetMapping("/jobs")
    public Set<JobKey> listJobs() throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyJobGroup());
    }

    @PostMapping("/jobs")
    public JobKey createJobWithSimpleTrigger(@RequestBody CreateSimpleJob createJob) throws Exception {
        JobDetailData jobDetailData = createJob.getJobDetail();
        CreateSimpleJob.TriggerData triggerData = createJob.getTrigger();
        if (triggerData.getName() == null) {
            triggerData.setName(jobDetailData.getName());
        }
        if (triggerData.getGroup() == null) {
            triggerData.setGroup(jobDetailData.getGroup());
        }

        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobDetailData.getJobClassName());
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(jobClass).
                        withIdentity(jobDetailData.getName(), jobDetailData.getGroup()).
                        build();
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(triggerData.getName(), triggerData.getGroup()).
                withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().
                                withIntervalInMilliseconds(triggerData.getRepeatInterval()).
                                withRepeatCount(triggerData.getRepeatCount())
                ).forJob(jobDetail).
                build();
        Date nextFireDate = scheduler.scheduleJob(jobDetail, trigger);
        LOG.info("Job scheduled: jobDetail={}, trigger={}, nextFireDate={}", jobDetail, trigger, nextFireDate);
        return jobDetail.getKey();
    }

    @PostMapping("/jobs/cron")
    public JobKey createJobWithCronTrigger(@RequestBody CreateCronJob createJob) throws Exception {
        JobDetailData jobDetailData = createJob.getJobDetail();
        CreateCronJob.TriggerData triggerData = createJob.getTrigger();
        if (triggerData.getName() == null) {
            triggerData.setName(jobDetailData.getName());
        }
        if (triggerData.getGroup() == null) {
            triggerData.setGroup(jobDetailData.getGroup());
        }

        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobDetailData.getJobClassName());
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(jobClass).
                        withIdentity(jobDetailData.getName(), jobDetailData.getGroup()).
                        build();
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity(triggerData.getName(), triggerData.getGroup()).
                withSchedule(
                        CronScheduleBuilder.
                                cronSchedule(triggerData.getCron())
                ).forJob(jobDetail).
                build();
        Date nextFireDate = scheduler.scheduleJob(jobDetail, trigger);
        LOG.info("Job scheduled: jobDetail={}, trigger={}, nextFireDate={}", jobDetail, trigger, nextFireDate);
        return jobDetail.getKey();
    }
}
