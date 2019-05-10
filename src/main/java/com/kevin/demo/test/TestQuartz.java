package com.kevin.demo.test;

import org.quartz.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;

import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;

/**
 * @Description:    定时任务
 * @Author:         Kevin
 * @CreateDate:     2019/5/7 20:59
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/7 20:59
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
//@Configuration
public class TestQuartz {

    public static void test(Date startTime,Date endTime) throws SchedulerException {
        //由job类生产jobDetail
        JobDetail jobDetail = newJob(TestJob.class) //产生Job实例，job信息存储在jobDetail中
                .usingJobData("key1","val1")//通过jobDataMap为job实例增加属性，然后再传递到调度器中去
                .usingJobData("key2","val2")
                .withIdentity("helloTask","task1")
                .build();//由jobBuilder生成jobDetail

        //SimpleTrigger
        SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1","group1")//触发器标识
                .startAt(startTime)//开始触发时间
                .forJob("helloTask","task1")//绑定的任务组
                .withSchedule(simpleSchedule()//调度设定
                    .withRepeatCount(10)//重复次数
                    .withIntervalInMilliseconds(10))//执行频率
                .endAt(endTime)//结束时间
                .build();

        //CronTrigger
        CronTrigger cronTrigger = (CronTrigger) newTrigger()
                .withIdentity("trigger2","group2")//触发器标识
                .startAt(startTime)//开始触发时间
                .forJob("helloTask1","task2")//绑定的任务组
                .withSchedule(cronSchedule("0 42 10 * * ?")//cron表达式 --- 每天10点42分执行
                        .withMisfireHandlingInstructionFireAndProceed())//错过触发 -- 一般是智能模式
                .endAt(endTime)//结束时间
                .build();

        //获取调度工厂
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        //从工厂中获取调度器
        Scheduler sched = schedFact.getScheduler();
        //开始调度
        sched.start();
        //挂起
        //sched.standby();
        //关闭任务调度
        //1.等待当前任务执行完毕在关闭任务调度
        //sched.shutdown(true);
        //2.直接关闭任务调度
        //sched.shutdown(false);
        //传入调度任务和任务触发器
        sched.scheduleJob(jobDetail,simpleTrigger);

        //全局任务监听配置
        sched.getListenerManager().addJobListener(new TestJobListener(), EverythingMatcher.allJobs());
        //局部任务监听配置
        sched.getListenerManager().addJobListener(new TestJobListener(), KeyMatcher.keyEquals(new JobKey("helloTask","task1")));
    }

}
