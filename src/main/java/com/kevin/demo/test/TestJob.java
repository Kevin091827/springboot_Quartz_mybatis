package com.kevin.demo.test;

import org.quartz.*;

import java.util.Date;

/**
 * @Description:    定时任务 --- 任务器
 * @Author:         Kevin
 * @CreateDate:     2019/5/7 20:58
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/7 20:58
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class TestJob implements Job {

    /**
     * Job类必须要有一个无参构造函数(当时用默认的Factory时)
     * 原因：》》》》》
     * Scheduler任务调度器在执行Job的Execute方法前必须创建一个Job的实例
     * 在方法执行完毕后，该实例的引用被丢弃，被虚拟机垃圾回收
     *
     * 后果：》》》》》
     * job在执行过程中数据属性是不会保留的
     * 因此要给job实例增加属性只有通过jodDataMap
     */
    public TestJob(){

    }

    /**
     * 具体执行的任务
     * @param jobExecutionContext  : 存储了调度器中的jobDetail的一些信息，例如job的实例信息和jobDataMap的一些信息
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取jobDeatil中的信息
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();

        //获取jobDetail中的jobDataMap信息
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //通过指定的key找到jobDataMap中的指定属性信息
        String value1 = jobDataMap.getString("key1");

        System.out.println("执行定时任务"+new Date());
    }

}
