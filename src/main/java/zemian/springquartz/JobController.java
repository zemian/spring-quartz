package zemian.springquartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zemian.springquartz.data.CreateCronJob;
import zemian.springquartz.data.CreateSimpleJob;
import zemian.springquartz.data.ListData;

import java.util.*;

@CrossOrigin
@RestController
public class JobController {
    private static Logger LOG = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private Scheduler scheduler;

    @GetMapping("/jobs")
    public ListData listJobs() throws SchedulerException {
        Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        ListData<JobKey> result = new ListData<>();
        result.setItems(new ArrayList(keys));
        result.getItems().sort(Comparator.naturalOrder());
        return result;
    }

    @PostMapping("/jobs")
    public JobKey createJobWithSimpleTrigger(@RequestBody CreateSimpleJob createJob) throws Exception {
        JobDetail jobDetail = createJob.toJobDetail();
        Trigger trigger = createJob.toTrigger();
        Date nextFireDate = scheduler.scheduleJob(jobDetail, trigger);
        LOG.info("Simple Job scheduled: jobDetail={}, trigger={}, nextFireDate={}", jobDetail, trigger, nextFireDate);
        return jobDetail.getKey();
    }

    @PostMapping("/jobs/cron")
    public JobKey createJobWithCronTrigger(@RequestBody CreateCronJob createJob) throws Exception {
        JobDetail jobDetail = createJob.toJobDetail();
        Trigger trigger = createJob.toTrigger();
        Date nextFireDate = scheduler.scheduleJob(jobDetail, trigger);
        LOG.info("Cron Job scheduled: jobDetail={}, trigger={}, nextFireDate={}", jobDetail, trigger, nextFireDate);
        return jobDetail.getKey();
    }
}
