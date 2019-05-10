package com.kevin.demo.service;


import com.kevin.demo.entity.MyJob;
import com.kevin.demo.util.AjaxResult;
import org.quartz.SchedulerException;

/**
 * @Description:    任务业务
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 1:26
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 1:26
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public interface TaskService {

    /**
     * 开启任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     */
    AjaxResult startTask(String group, String name, Class clazz, String cron) throws Exception;

    /**
     * 终止定时任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    AjaxResult stopTask(String group, String name, Class clazz, String cron) throws Exception;

    /**
     * 暂停任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    AjaxResult pauseTask(String group, String name, Class clazz, String cron) throws Exception;

    /**
     * 恢复任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    AjaxResult resumeTask(String group, String name, Class clazz, String cron) throws Exception;

    /**
     * 查看所有任务
     * @return
     */
    AjaxResult selectAllTask();

    /**
     * 更新任务执行频率
     * @param myJob
     * @return
     */
    AjaxResult updateCron(MyJob myJob);

    /**
     * 新增任务
     * @param myJob
     * @return
     */
    AjaxResult insertTask(MyJob myJob,Class clazz);


}
