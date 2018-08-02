package com.common.quartz.test;




import com.common.quartz.job.HelloJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by jackiechan on 18-7-28/下午2:29
 */
public class TestMain {
    public static void main(String[] args) throws Exception {

    }
    @Test
    public void test1() throws Exception {

        //创建Scheduler，执行计划
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();

        //定义一个触发条件
        //withIntervalInSeconds重复频率为每秒
        //withIdentity("t1","laowang")定义组名，任务名
        //startNow()一旦加入scheduler立即执行
        // withSchedule(SimpleScheduleBuilder.simpleSchedule()使用simple触发
        // withIntervalInSeconds(1)每隔一秒执行一次
        //repeatForever重复无限次数，一直执行withRepeatCount(10)执行10次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("t1","laowang").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
        //Trigger trigger = TriggerBuilder.newTrigger().withIdentity("t1","laowang").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10)).build();
        //定义jobDetail为helloJob类，这是真正的逻辑所在
        //usingJobData定义属性
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1","laowang").usingJobData("name","abc").build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        //程序结束后，任务也就结束了，那么所谓的所有设置也就都来不及执行就结束了，所以加个睡眠阻塞一下这个主线程
        Thread.sleep(10000);
    }

    @Test
    public void test2()  throws Exception{
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();
        CronTrigger cronTrigger =TriggerBuilder.newTrigger().withIdentity("c1","group1").usingJobData("name","tingjie").withSchedule(CronScheduleBuilder.cronSchedule("0/1 32-33 * * * ? *")).build();

//        MutableTrigger trigger = CronScheduleBuilder.cronSchedule("0/1 20-21 * * * ? *") // 每天10:00-12:00，每隔2分钟执行一次
//                .build();
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1","laowang").usingJobData("name","abc").build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
        Thread.sleep(1000000);
    }
}
