package com.kevin.demo.test;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description:    springboot整合定时任务框架Quartz --- 配置类
 * @Author:         Kevin
 * @CreateDate:     2019/5/9 0:38
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/9 0:38
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
//@Configuration
public class TestQuartzConfig {

    /**
     * 创建jobDetail
     * @return
     */
    //@Bean
    public JobDetail getJobDetail(){
        //由job类生产jobDetail
        JobDetail jobDetail = newJob(TestQuartzJob.class) //产生Job实例，job信息存储在jobDetail中
                .usingJobData("key1","val1")//通过jobDataMap为job实例增加属性，然后再传递到调度器中去
                .usingJobData("key2","val2")
                .withIdentity("h1","t1")
                .requestRecovery(true)
                .storeDurably()
                .build();//由jobBuilder生成jobDetail
        return jobDetail;
    }

    /**
     * 获取cronTrigger和设置调度器
     * @return
     */
    //@Bean
    public CronTrigger getCronTrigger(){
       CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
               .cronSchedule(" 0/5 * * * * ? ");

       return TriggerBuilder.newTrigger().forJob(getJobDetail())
               .withIdentity("testQuartz")
               .withSchedule(cronScheduleBuilder)
               .build();
    }

}
