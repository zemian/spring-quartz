package zemian.springquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(JobController.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Executing with jobExecutionContext={}", jobExecutionContext);
    }
}
