package com.common.quartz.job;


import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by jackiechan on 18-7-28/下午2:26
 * 工作类的具体实现
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //创建工作详情
        JobDetail jobDetail = jobExecutionContext.getJobDetail();

        //获取工作名称
        //组名
        System.out.println(jobDetail.getJobDataMap().get("job1"));
        //工作名
        System.out.println(jobDetail.getJobDataMap().get("name"));
        System.out.println("定时任务执行了");
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
    }
}
