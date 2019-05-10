package com.kevin.demo.test;


import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@DisallowConcurrentExecution    //相同定义的jobDetail不能并发执行
@PersistJobDataAfterExecution   //jobDataMap数据保存
@Slf4j
public class TestQuartzJob extends QuartzJobBean {

    public TestQuartzJob(){

    }
    /**
     * 具体任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("springboot整合定时任务Quartz:"+new Date());
    }
}
