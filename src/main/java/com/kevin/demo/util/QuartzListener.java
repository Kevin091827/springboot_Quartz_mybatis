package com.kevin.demo.util;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Description:    全局任务监听器
 * @Author:         Kevin
 * @CreateDate:     2019/5/11 1:04
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/11 1:04
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class QuartzListener implements JobListener {


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
