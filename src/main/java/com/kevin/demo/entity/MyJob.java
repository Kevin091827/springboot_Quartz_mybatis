package com.kevin.demo.entity;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:    任务调度 -- 自定义job -- 对应数据库，主要用来保存任务信息到数据库当中
 * @Author:         Kevin
 * @CreateDate:     2019/5/9 23:45
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/9 23:45
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Data
public class MyJob implements Serializable {

    //任务id
    private int id;
    //任务组别
    private String taskGroup;
    //任务名字
    private String taskName;
    //cron表达式
    private String cron;
    //任务状态
    private String status;
    //任务创建时间
    private Date gmt_create;
    //任务修改时间
    private Date gmt_modify;

}
