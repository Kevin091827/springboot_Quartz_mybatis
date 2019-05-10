package com.kevin.demo.service;

import com.kevin.demo.util.QuartzListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.stereotype.Service;

import static org.quartz.JobBuilder.newJob;

/**
 * @Description:    任务调度
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 0:54
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 0:54
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Service
public class QuartzServiceImpl implements QuartzService{

    //StdSchedulerFactory工厂
    private static StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();

    /**
     * 获取一个调度器
     * 通过 具体执行的任务器的.class类型，任务组名和任务名，还有cron表达式动态获取该任务的调度器
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     */
    @Override
    public Scheduler startJob(String group,String name,Class clazz,String cron) {

        //封装信息
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("group",group);
        jobDataMap.put("Name",name);

        //创建jobDetail
        JobDetail jobDetail = newJob(clazz)
                .usingJobData(jobDataMap)
                .build();

        //创建cronScheduleBuilder
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

        //创建trigger
        CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(cronScheduleBuilder)
                .build();

        //创建scheduler
        Scheduler scheduler = null;

        //配置scheduler，绑定任务和触发器
        try {
            scheduler = schedulerFactory.getScheduler();
            //绑定任务和触发器
            scheduler.scheduleJob(jobDetail,cronTrigger);
            //配置全局任务监听器
            scheduler.getListenerManager().addJobListener(new QuartzListener(),EverythingMatcher.allJobs());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return scheduler;
    }
}
