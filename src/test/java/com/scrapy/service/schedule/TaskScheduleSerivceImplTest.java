package com.scrapy.service.schedule;

import com.scrapy.service.schedule.task.ITaskScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskScheduleSerivceImplTest {

    @Autowired private ITaskScheduleService taskScheduleService;

/*    @Test
    void execution() {

        taskScheduleService.execute();

    }*/
}