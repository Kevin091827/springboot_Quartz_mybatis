package com.kevin.demo;

import com.kevin.demo.entity.MyJob;
import com.kevin.demo.entity.ScheduleQuartzJob;
import com.kevin.demo.mapper.TaskMapper;
import com.kevin.demo.service.QuartzService;
import com.kevin.demo.service.TaskService;
import com.kevin.demo.test.TestQuartz;
import com.kevin.demo.test.TestQuartzJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void contextLoads() {

        MyJob myJob = new MyJob();
        myJob.setTaskGroup("task1");
        myJob.setTaskName("task1");
        myJob.setCron("0/5 * * * * ?");
        //开启任务
        myJob.setStatus("1");
        Map<String,String> map = new HashMap<>();
        map.put("group",myJob.getTaskGroup());
        map.put("name",myJob.getTaskName());
        System.out.println("*********"+taskMapper.selectTaskByNameAndGroup(map)+"***********");


    }

}
