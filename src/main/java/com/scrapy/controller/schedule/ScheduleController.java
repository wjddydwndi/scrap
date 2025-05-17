package com.scrapy.controller.schedule;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class ScheduleController {
/*
    @Autowired
    private ITaskSchedulerService taskSchedulerService;

    @PostMapping("/add")
    public Response addTask(@RequestBody SchedulerTask schedulerTask) {

        Log.info("scheduler add, schedulerTask={}", JsonUtil.toJson(schedulerTask));
        try {
            int seq = taskSchedulerService.scheduleTask(schedulerTask);
            Log.info("scheduler add success, seq : {}", seq);
        } catch (Exception e) {
            Log.error("schedler add exception, e : {}", e.getMessage());
            return new Response(ResponseCode.err_response_internal_server.getCode(), "FAIL", e.getMessage());
        }

        return new Response(ResponseCode.response_success.getCode(), "OK");
    }

    @PostMapping("/update")
    public Response updateTask(@RequestBody SchedulerTask schedulerTask) {
        Log.info("scheduler update, schedulerTask={}", JsonUtil.toJson(schedulerTask));

        try {
            int seq = taskSchedulerService.updateTask(schedulerTask);
            Log.info("scheduler update success, seq : {}", seq);
        } catch (Exception e) {
            Log.error("schedler update exception, e : {}", e.getMessage());
            return new Response(ResponseCode.err_response_internal_server.getCode(), "FAIL", e.getMessage());
        }

        return new Response(ResponseCode.response_success.getCode(), "OK");
    }


    @PostMapping("/delete")
    public Response deleteTask(@RequestBody SchedulerTask schedulerTask) {
        Log.info("scheduler delete, schedulerTask={}", JsonUtil.toJson(schedulerTask));

        try {
            int seq = taskSchedulerService.deleteTask(schedulerTask);
            Log.info("scheduler delete success, seq : {}", seq);
        } catch (Exception e) {
            Log.error("schedler delete exception, e : {}", e.getMessage());
            return new Response(ResponseCode.err_response_internal_server.getCode(), "FAIL", e.getMessage());
        }

        return new Response(ResponseCode.response_success.getCode(), "OK");
    }*/
}
