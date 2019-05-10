package com.kevin.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Description:    任务器
 * @Author:         Kevin
 * @CreateDate:     2019/5/9 23:58
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/9 23:58
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
@Slf4j
public class ScheduleQuartzJob extends QuartzJobBean {

    public ScheduleQuartzJob(){

    }
    /**
     * 具体任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取任务组名称
        String group = jobExecutionContext.getJobDetail().getJobDataMap().get("group").toString();
        //获取任务名
        String name = jobExecutionContext.getJobDetail().getJobDataMap().get("name").toString();
        //具体业务逻辑 ....
        log.info("任务组："+group+"的任务："+name+"执行了定时任务：...."+"执行时间："+new Date());
    }
}
