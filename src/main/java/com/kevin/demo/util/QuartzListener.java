package com.kevin.demo.util;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QuartzListener implements JobListener {

    private String JOBLISTERNER_ALLJOB_NAME = "allListener";

    @Override
    public String getName() {
        return JOBLISTERNER_ALLJOB_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        log.info("job即将执行的通知");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        log.info("job拒绝执行的通知");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        log.info("job完成执行时的通知");
    }
}
