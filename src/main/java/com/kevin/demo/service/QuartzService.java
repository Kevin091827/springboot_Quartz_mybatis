package com.kevin.demo.service;

import org.quartz.Scheduler;

/**
 * @Description:    DOTO
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 0:55
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 0:55
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public interface QuartzService {

    /**
     * 任务调度（动态获取调度器）
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     */
    Scheduler startJob(String group, String name, Class clazz, String cron);
}
