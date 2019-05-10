package com.kevin.demo;

import com.kevin.demo.service.QuartzService;
import com.kevin.demo.test.TestQuartz;
import com.kevin.demo.test.TestQuartzJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private QuartzService quartzService;

    @Test
    public void contextLoads() {

        Scheduler scheduler = quartzService.startJob("h1","t1", TestQuartzJob.class,"0/5 * * * * ?");
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
