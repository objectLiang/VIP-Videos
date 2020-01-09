/*package com.liang.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Component
//@Async //可加在类或方法，开启异步事件的支持
public class SchedulerTask {
    
    Logger log = LoggerFactory.getLogger(SchedulerTask.class);
    
    //cron表达式：每天凌晨1点执行一次
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduled(){
    	demo.demo01();
    }
}*/