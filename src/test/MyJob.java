package test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
    	System.out.println("스케줄러에 의해 실행된 코드.");
    }
}