package com.kevin.demo.controller;

import com.kevin.demo.entity.MyJob;
import com.kevin.demo.entity.ScheduleQuartzJob;
import com.kevin.demo.service.QuartzService;
import com.kevin.demo.service.TaskService;
import com.kevin.demo.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:    DOTO
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 12:49
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 12:49
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Controller
@ResponseBody
@Slf4j
public class QuartzController {

    @Autowired
    private TaskService taskService;

    /*******************************************
     缺陷：
            class:代表任务类，主要是任务需要执行的逻辑
            先写死是ScheduleQuartz.class
            新增加任务话就修改
     ***************************************/

    /**
     * 开启任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @RequestMapping("/start")
    public AjaxResult start(String group, String name, Class clazz, String cron) throws Exception {
        return taskService.startTask(group, name, ScheduleQuartzJob.class, cron);
    }

    /**
     * 终止任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/stop")
    public AjaxResult stop(String group, String name, Class clazz, String cron,int id) throws Exception {
        return taskService.stopTask(group, name, ScheduleQuartzJob.class, cron,id);
    }

    /**
     * 暂停任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @RequestMapping("/pause")
    public AjaxResult pause(String group, String name, Class clazz, String cron) throws Exception {
        return taskService.pauseTask(group, name, ScheduleQuartzJob.class, cron);
    }

    /**
     * 恢复任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @RequestMapping("/resume")
    public AjaxResult resume(String group, String name, Class clazz, String cron) throws Exception {
        return taskService.resumeTask(group, name, ScheduleQuartzJob.class, cron);
    }

    /**
     * 查询所有任务
     * @return
     */
    @RequestMapping("/selectAllTask")
    public AjaxResult selectAllTask(){
        return taskService.selectAllTask();
    }

    /**
     * 更新执行频度
     * @param group
     * @param name
     * @param cron
     * @return
     */
    @RequestMapping("/updateCron")
    public AjaxResult updateCron(int id ,String group, String name,String cron){
        MyJob myJob = new MyJob();
        myJob.setGroup(group);
        myJob.setName(name);
        myJob.setId(id);
        myJob.setCron(cron);
        return taskService.updateCron(myJob);
    }

    /**
     * 新增任务
     * @param myJob
     * @param clazz
     * @return
     */
    @RequestMapping("/insertTask")
    public AjaxResult insertTask(@ModelAttribute MyJob myJob,Class clazz){
        return taskService.insertTask(myJob,ScheduleQuartzJob.class);
    }
}
