package com.kevin.demo.service;

import com.kevin.demo.entity.MyJob;
import com.kevin.demo.mapper.TaskMapper;
import com.kevin.demo.util.AjaxResult;
import org.quartz.JobKey;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:    任务业务处理
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 1:27
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 1:27
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 开启任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @Override
    public AjaxResult startTask(String group, String name, Class clazz, String cron) throws Exception {

        //验证数据
        if(StringUtils.isEmpty(group)||StringUtils.isEmpty(name)
                || StringUtils.isEmpty(cron) || clazz == null){
            return new AjaxResult().error("参数有空，请求失败");
        }

        //封装参数
        Map<String,String> map = new HashMap<>();
        map.put("group",group);
        map.put("name",name);

        //先查数据看，看看当前任务是否正在执行
        MyJob currentJob = taskMapper.selectTaskByNameAndGroup(map);

        if(currentJob != null){
            //任务状态为 1 ----> 任务正在执行
            if("1".equals(currentJob.getStatus())){
                return new AjaxResult().error("当前任务正在执行，无需重复执行");
            }else if ("0".equals(currentJob.getStatus())){
                //获取scheduler
                Scheduler scheduler = quartzService.startJob(group,name,clazz,cron);

                //开启任务
                scheduler.start();

                //将任务存入数据库，状态为 1 --- 任务正在启动
                currentJob.setStatus("1");
                int i = taskMapper.insertTask(currentJob);
                if(i > 0){
                    log.info("任务开启成功，状态修改为1");
                }else {
                    log.info("任务启动成功,任务状态修改失败");
                }
                return new AjaxResult().ok("定时任务开启");
            }else{
                return new AjaxResult().error("任务出错了");
            }
        }else{
            //没有当前任务
            return new AjaxResult().error("任务不存在,请先添加任务");
        }
    }

    /**
     * 终止定时任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @Override
    public AjaxResult stopTask(String group, String name, Class clazz, String cron) throws Exception {
        //验证数据
        if(StringUtils.isEmpty(group)||StringUtils.isEmpty(name)
                || StringUtils.isEmpty(cron) || clazz == null){
            return new AjaxResult().error("参数有空，请求失败");
        }
        //封装参数
        Map<String,String> map = new HashMap<>();
        map.put("group",group);
        map.put("name",name);
        //先查数据看，看看当前任务是否正在执行
        MyJob currentJob = taskMapper.selectTaskByNameAndGroup(map);

        if(currentJob != null){
            //任务状态为 1 ----> 任务正在执行
            if("0".equals(currentJob.getStatus())){
                return new AjaxResult().error("当前任务没有执行，无法终止");
            }else if ("1".equals(currentJob.getStatus())){
                //获取scheduler
                Scheduler scheduler = quartzService.startJob(group,name,clazz,cron);

                //终止定时任务
                scheduler.shutdown();
                //将任务从数据库中删除(根据任务组名和任务名)
                int i = taskMapper.deleteTask(map);
                if(i > 0){
                    log.info("任务删除成功");
                }else {
                    log.info("任务删除失败");
                }
                return new AjaxResult().ok("定时任务终止");
            }else{
                return new AjaxResult().error("任务出错了");
            }
        }else{
            //没有当前任务
            return new AjaxResult().error("任务不存在,请先添加任务");
        }
    }

    /**
     * 暂停定时任务
     * @param group
     * @param name
     * @param clazz
     * @param cron
     * @return
     * @throws Exception
     */
    @Override
    public AjaxResult pauseTask(String group, String name, Class clazz, String cron) throws Exception {
        //验证数据
        if(StringUtils.isEmpty(group)||StringUtils.isEmpty(name)
                || StringUtils.isEmpty(cron) || clazz == null){
            return new AjaxResult().error("参数有空，请求失败");
        }

        //封装参数
        Map<String,String> map = new HashMap<>();
        map.put("group",group);
        map.put("name",name);

        //先查数据看，看看当前任务是否正在执行
        MyJob currentJob = taskMapper.selectTaskByNameAndGroup(map);

        if(currentJob != null){
            //任务状态为 1 ----> 任务正在执行
            if("0".equals(currentJob.getStatus())){
                return new AjaxResult().error("当前任务没有执行，无法终止");
            }else if ("1".equals(currentJob.getStatus())){
                //获取scheduler
                Scheduler scheduler = quartzService.startJob(group,name,clazz,cron);

                JobKey jobKey = new JobKey(name, group);
                //暂停任务
                scheduler.pauseJob(jobKey);

                //更新任务状态 0 --- 任务暂停
                currentJob.setStatus("0");
                //根据任务组名和任务名更新任务状态
                int i = taskMapper.updateTaskStatus(currentJob);
                if(i > 0){
                    log.info("任务状态更新成功");
                }else {
                    log.info("任务状态更新失败");
                }
                return new AjaxResult().ok("定时任务已经暂停");
            }else{
                return new AjaxResult().error("任务出错了");
            }
        }else{
            //没有当前任务
            return new AjaxResult().error("任务不存在,请先添加任务");
        }
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
    @Override
    public AjaxResult resumeTask(String group, String name, Class clazz, String cron) throws Exception {
        //验证数据
        if(StringUtils.isEmpty(group)||StringUtils.isEmpty(name)
                || StringUtils.isEmpty(cron) || clazz == null){
            return new AjaxResult().error("参数有空，请求失败");
        }


        //封装参数
        Map<String,String> map = new HashMap<>();
        map.put("group",group);
        map.put("name",name);

        //先查数据看，看看当前任务是否正在执行
        MyJob currentJob = taskMapper.selectTaskByNameAndGroup(map);

        if(currentJob != null){
            //任务状态为 1 ----> 任务正在执行
            if("0".equals(currentJob.getStatus())){
                return new AjaxResult().error("当前任务没有执行，无法恢复");
            }else if ("1".equals(currentJob.getStatus())){
                //获取scheduler
                Scheduler scheduler = quartzService.startJob(group,name,clazz,cron);

                JobKey jobKey = new JobKey(name, group);
                //暂停任务
                scheduler.resumeJob(jobKey);

                //更新任务状态 1 --- 任务恢复
                currentJob.setStatus("1");
                int i = taskMapper.updateTaskStatus(currentJob);
                if(i > 0){
                    log.info("任务状态更新成功");
                }else {
                    log.info("任务状态更新失败");
                }
                return new AjaxResult().ok("定时任务已经恢复执行");
            }else{
                return new AjaxResult().error("任务出错了");
            }
        }else{
            //没有当前任务
            return new AjaxResult().error("任务不存在,请先添加任务");
        }
    }

    /**
     * 查看所有任务
     * @return
     */
    @Override
    public AjaxResult selectAllTask() {
        List<MyJob> list = taskMapper.selectAllJob();
        return new AjaxResult().ok(list);
    }

    /**
     * 更新任务执行频度
     * @param myJob
     * @return
     */
    @Override
    public AjaxResult updateCron(MyJob myJob) {
       //先查数据库中有无当前任务
        //封装参数
        Map<String,String> map = new HashMap();
        map.put("group",myJob.getGroup());
        map.put("name",myJob.getName());
        MyJob curruntJob = taskMapper.selectTaskByNameAndGroup(map);
        if(curruntJob != null){
            int i = taskMapper.updateTaskCron(myJob);
            if(i > 0){
                return new AjaxResult().ok("任务频度更新成功");
            }else{
                return new AjaxResult().error("更新失败");
            }
        }else {
            return new AjaxResult().error("不存在当前任务");
        }
    }

    /**
     * 新增任务
     * @param myJob
     * @return
     */
    @Override
    public AjaxResult insertTask(MyJob myJob,Class clazz) {
        myJob.setGmt_create(new Date());
        myJob.setGmt_modify(new Date());
        //新增任务
        int i = taskMapper.insertTask(myJob);
        if(i > 0){
            //开启新增任务
            try {
                startTask(myJob.getGroup(),myJob.getName(),clazz,myJob.getCron());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new AjaxResult().ok("新增一个任务");
        }else{
            return new AjaxResult().error("新增失败");
        }

    }
}
