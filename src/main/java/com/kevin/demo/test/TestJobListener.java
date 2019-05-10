package com.kevin.demo.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Description:    任务监听器
 * @Author:         Kevin
 * @CreateDate:     2019/5/8 2:37
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/8 2:37
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class TestJobListener implements JobListener {

    /**
     * 获取监听器名
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * 执行任务前调用
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    /**
     * 执行任务前，但是该任务被拒绝执行时调用
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     * 执行任务后调用
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
